package cn.com.zhejiangart.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

@Data
@Schema(name = "ShopcartCountDTO", description = "购物车数量DTO")
public class ShopcartCountDTO {

    @Schema(description = "购物车商品数量")
    private Integer count;
}