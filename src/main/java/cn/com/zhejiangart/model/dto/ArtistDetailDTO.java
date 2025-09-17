package cn.com.zhejiangart.model.dto;

import cn.com.zhejiangart.model.vo.User;
import cn.com.zhejiangart.model.vo.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "ArtistDetailDTO", description = "美术家详情DTO")
public class ArtistDetailDTO {

    @Schema(description = "美术家详情")
    private User artist;

    @Schema(description = "相关作品列表")
    private List<Product> relatedProducts;
}