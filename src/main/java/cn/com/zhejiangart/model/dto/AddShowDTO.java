package cn.com.zhejiangart.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "AddShowDTO", description = "添加展览DTO")
public class AddShowDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "展览标题")
    private String title;

    @Schema(description = "展览描述")
    private String description;

    @Schema(description = "展览时间")
    private String showTime;

    @Schema(description = "展览地点")
    private String location;
}