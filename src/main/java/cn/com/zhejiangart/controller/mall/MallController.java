package cn.com.zhejiangart.controller.mall;

import cn.com.zhejiangart.model.vo.Goodstype;
import cn.com.zhejiangart.model.vo.Product;
import cn.com.zhejiangart.service.IGoodstypeService;
import cn.com.zhejiangart.service.IProductService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 周边商场控制器
 * 提供周边商品列表展示功能
 */
@RestController
@RequestMapping("/api/mall")
@Tag(name = "周边商场接口", description = "提供周边商品列表展示功能")
public class MallController {

    @Autowired
    private IGoodstypeService goodstypeService;

    @Autowired
    private IProductService productService;

    /**
     * 获取周边商品列表
     * goodstype中没有pid的为相关的周边分类，用id在回表查product
     *
     * @param page 页码
     * @param size 每页大小
     * @param categoryId 分类ID（可选）
     * @return 商品列表
     */
    @GetMapping("/products")
    @Operation(summary = "获取周边商品列表", description = "获取周边商品列表，goodstype中没有pid的为相关的周边分类，用id在回表查product")
    public Page<Product> getMallProducts(
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") Integer page,
            @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer size,
            @Parameter(description = "分类ID") @RequestParam(required = false) Integer categoryId) {

        Page<Product> pageResult = new Page<>(page, size);
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();

        // 如果指定了分类ID，则直接使用该分类ID查询商品
        if (categoryId != null) {
            queryWrapper.eq("cate_id", categoryId);
        } else {
            // 获取所有没有pid的分类（即pid为null的分类）作为周边分类
            QueryWrapper<Goodstype> categoryWrapper = new QueryWrapper<>();
            categoryWrapper.isNull("pid");
            List<Goodstype> categories = goodstypeService.list(categoryWrapper);

            // 提取分类ID
            List<Integer> categoryIds = categories.stream()
                    .map(Goodstype::getId)
                    .toList();

            // 使用分类ID查询商品
            if (!categoryIds.isEmpty()) {
                queryWrapper.in("cate_id", categoryIds);
            } else {
                // 如果没有找到相关分类，则返回空结果
                pageResult.setRecords(List.of());
                pageResult.setTotal(0L);
                return pageResult;
            }
        }

        // 只查询上架的商品
        queryWrapper.eq("is_sale", 1);

        // 按照创建时间倒序排列
        queryWrapper.orderByDesc("create_time");

        return productService.page(pageResult, queryWrapper);
    }

    /**
     * 获取周边商品分类列表
     * 返回所有没有pid字段的分类作为周边商品分类
     *
     * @return 分类列表
     */
    @GetMapping("/categories")
    @Operation(summary = "获取周边商品分类列表", description = "获取周边商品分类列表，返回所有没有pid字段的分类")
    public List<Goodstype> getMallCategories() {
        QueryWrapper<Goodstype> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("pid");
        return goodstypeService.list(queryWrapper);
    }
}