package cn.com.zhejiangart.model.dto;

import cn.com.zhejiangart.model.vo.Newcate;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "NewsCateDTO", description = "新闻分类DTO")
public class NewsCateDTO {

    @Schema(description = "新闻分类列表")
    private List<Newcate> news;
}