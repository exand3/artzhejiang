package cn.com.zhejiangart.controller.art;

import cn.com.zhejiangart.mapper.ProductMapper;
import cn.com.zhejiangart.model.dto.ProductDetailDTO;
import cn.com.zhejiangart.model.dto.ProductListDTO;
import cn.com.zhejiangart.model.po.ProductListPO;
import cn.com.zhejiangart.model.vo.Product;
import cn.com.zhejiangart.util.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 艺术品控制器
 */
@RestController
@RequestMapping("/api/art/products")
@Tag(name = "艺术品相关接口")
public class ProductController {

    @Resource
    private ProductMapper productMapper;

    /**
     * 获取艺术品列表
     *
     * @param productListPO 查询参数
     * @return 艺术品列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取艺术品列表", description = "展示艺术品列表，支持分页和多种查询条件")
    public Result<?> getProductList(ProductListPO productListPO) {

        try {
            // 构建分页对象
            Page<Product> page = new Page<>(productListPO.getPageNum(), productListPO.getPageSize());

            // 构建查询条件
            QueryWrapper<Product> queryWrapper = new QueryWrapper<>();

            // 二级类别ID
            if (productListPO.getCateId() != null) {
                queryWrapper.eq("cate_id", productListPO.getCateId());
            }

            // 价格区间
            if (productListPO.getMinPrice() != null) {
                queryWrapper.ge("price", BigDecimal.valueOf(productListPO.getMinPrice()));
            }
            if (productListPO.getMaxPrice() != null) {
                queryWrapper.le("price", BigDecimal.valueOf(productListPO.getMaxPrice()));
            }

            // 尺寸
            if (productListPO.getGoodsRange() != null && !productListPO.getGoodsRange().isEmpty()) {
                queryWrapper.like("goods_range", productListPO.getGoodsRange());
            }

            // 品相
            if (productListPO.getGoodsCom() != null && !productListPO.getGoodsCom().isEmpty()) {
                queryWrapper.eq("goods_com", productListPO.getGoodsCom());
            }

            // 创作年代
            if (productListPO.getGoodsCare() != null) {
                queryWrapper.eq("goods_care", productListPO.getGoodsCare());
            }

            // 类型（购买/租赁）
            if (productListPO.getIsGroom() != null && !productListPO.getIsGroom().isEmpty()) {
                queryWrapper.eq("is_groom", productListPO.getIsGroom());
            }

            // 只查询上架的商品
            queryWrapper.eq("is_sale", 1);

            // 执行分页查询
            Page<Product> productPage = productMapper.selectPage(page, queryWrapper);

            // 构建返回数据
            ProductListDTO productListDTO = new ProductListDTO();
            productListDTO.setRecords(productPage.getRecords());
            productListDTO.setTotal(productPage.getTotal());
            productListDTO.setPageNum((int) page.getCurrent());
            productListDTO.setPageSize((int) page.getSize());
            productListDTO.setTotalPage((int) productPage.getPages());

            return Result.success(productListDTO);
        } catch (Exception e) {
            return Result.error(500, "获取艺术品列表失败：" + e.getMessage());
        }
    }

    /**
     * 获取艺术品详情
     *
     * @param id 艺术品ID
     * @return 艺术品详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取艺术品详情", description = "根据ID获取艺术品详情，并返回猜你喜欢的作品列表")
    public Result<?> getProductDetail(
            @Parameter(description = "艺术品ID", required = true) @PathVariable Integer id) {
        try {
            // 查询艺术品详情
            Product product = productMapper.selectById(id);
            if (product == null) {
                return Result.error(404, "艺术品不存在");
            }

            // 增加浏览量
            product.setBrowser(product.getBrowser() != null ? product.getBrowser() + 1 : 1);
            productMapper.updateById(product);

            // 查询猜你喜欢的作品（相同类型且上架的4个作品）
            QueryWrapper<Product> guessQueryWrapper = new QueryWrapper<>();
            guessQueryWrapper.eq("cate_id", product.getCateId());
            guessQueryWrapper.eq("is_sale", 1);
            guessQueryWrapper.ne("id", id); // 排除当前作品
            guessQueryWrapper.last("LIMIT 4");
            List<Product> guessProducts = productMapper.selectList(guessQueryWrapper);
            
            // 转换为列表项格式
            List<ProductDetailDTO.ProductListItem> guessYouLikeProducts = guessProducts.stream()
                    .map(ProductDetailDTO.ProductListItem::fromProduct)
                    .collect(Collectors.toList());

            // 构建返回数据
            ProductDetailDTO productDetailDTO = new ProductDetailDTO();
            productDetailDTO.setProduct(product);
            productDetailDTO.setGuessYouLikeProducts(guessYouLikeProducts);

            return Result.success(productDetailDTO);
        } catch (Exception e) {
            return Result.error(500, "获取艺术品详情失败：" + e.getMessage());
        }
    }
}