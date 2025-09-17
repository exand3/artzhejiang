package cn.com.zhejiangart.model.vo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 大赛表
 * </p>
 *
 * @author author
 * @since 2025-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_number_list")
@Schema(name = "NumberList对象", description = "大赛表")
public class NumberList implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @Schema(description = "期数")
    private Integer number;

    @Schema(description = "主题")
    private String theme;

    @Schema(description = "对该期的描述")
    private String contonts;

    @Schema(description = "开始时间")
    private Integer startTime;

    @Schema(description = "结束时间")
    private Integer endTime;

    private String picn;

    @Schema(description = "收集作品结束时间")
    private Integer collectiontime;

    @Schema(description = "评选作品结束时间")
    private Integer selectiontime;

    @Schema(description = "0 未删除 1 已删除")
    private BigDecimal isDel;

    @Schema(description = "承办方")
    private String contractor1;

    @Schema(description = "承办方图片")
    private String piccon1;

    @Schema(description = "承办方")
    private String contractor2;

    @Schema(description = "承办方图片")
    private String piccon2;

    private String judgeId;


}
