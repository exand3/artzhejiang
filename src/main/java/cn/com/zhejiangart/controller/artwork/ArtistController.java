package cn.com.zhejiangart.controller.artwork;

import cn.com.zhejiangart.mapper.UserMapper;
import cn.com.zhejiangart.mapper.ProductMapper;
import cn.com.zhejiangart.model.dto.ArtistListDTO;
import cn.com.zhejiangart.model.dto.ArtistDetailDTO;
import cn.com.zhejiangart.model.po.ArtistListPO;
import cn.com.zhejiangart.model.vo.User;
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
import java.util.List;
import java.util.stream.Collectors;

/**
 * 美术家控制器
 */
@RestController
@RequestMapping("/api/artwork/artists")
@Tag(name = "美术家相关接口")
public class ArtistController {

    @Resource
    private UserMapper userMapper;
    
    @Resource
    private ProductMapper productMapper;

    /**
     * 获取美术家列表
     *
     * @param artistListPO 查询参数
     * @return 美术家列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取美术家列表", description = "展示美术家列表，支持分页查询")
    public Result<?> getArtistList(ArtistListPO artistListPO) {
        try {
            // 构建分页对象
            Page<User> page = new Page<>(artistListPO.getPageNum(), artistListPO.getPageSize());

            // 构建查询条件
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            // 只查询艺术家用户（根据实际业务确定如何区分艺术家）
            queryWrapper.eq("identify", "1"); // 1表示艺术家

            // 执行分页查询
            Page<User> userPage = userMapper.selectPage(page, queryWrapper);

            // 转换为列表项格式
            List<ArtistListDTO.ArtistListItem> records = userPage.getRecords().stream()
                    .map(ArtistListDTO.ArtistListItem::fromUser)
                    .collect(Collectors.toList());

            // 构建返回数据
            ArtistListDTO artistListDTO = new ArtistListDTO();
            artistListDTO.setRecords(records);
            artistListDTO.setTotal(userPage.getTotal());
            artistListDTO.setPageNum((int) page.getCurrent());
            artistListDTO.setPageSize((int) page.getSize());
            artistListDTO.setTotalPage((int) userPage.getPages());

            return Result.success(artistListDTO);
        } catch (Exception e) {
            return Result.error(500, "获取美术家列表失败：" + e.getMessage());
        }
    }

    /**
     * 获取美术家详情
     *
     * @param id 美术家ID
     * @return 美术家详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取美术家详情", description = "根据ID获取美术家详情，包括个人简介和相关作品")
    public Result<?> getArtistDetail(
            @Parameter(description = "美术家ID", required = true) @PathVariable Integer id) {
        try {
            // 查询美术家详情
            User artist = userMapper.selectById(id);
            if (artist == null) {
                return Result.error(404, "美术家不存在");
            }

            // 查询美术家的相关作品（限制4个）
            QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
            productQueryWrapper.eq("user_id", id);
            productQueryWrapper.eq("is_sale", 1); // 只查询上架的作品
            productQueryWrapper.last("LIMIT 4"); // 限制4个作品
            List<Product> relatedProducts = productMapper.selectList(productQueryWrapper);

            // 构建返回数据
            ArtistDetailDTO artistDetailDTO = new ArtistDetailDTO();
            artistDetailDTO.setArtist(artist);
            artistDetailDTO.setRelatedProducts(relatedProducts);

            return Result.success(artistDetailDTO);
        } catch (Exception e) {
            return Result.error(500, "获取美术家详情失败：" + e.getMessage());
        }
    }
}