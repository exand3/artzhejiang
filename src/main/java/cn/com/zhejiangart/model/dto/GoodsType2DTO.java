package cn.com.zhejiangart.model.dto;

import cn.com.zhejiangart.model.vo.Goodstype2;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "GoodsType2DTO", description = "艺术分类DTO")
public class GoodsType2DTO {

    @Schema(description = "艺术分类列表")
    private List<Goodstype2> goodsType2;
}