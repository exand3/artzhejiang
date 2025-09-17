package cn.com.zhejiangart.model.vo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 我要举办展览
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_show")
@Schema(name = "Show对象", description = "我要举办展览")
public class Show implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "地点")
    private String address;

    @Schema(description = "作者")
    private Integer userId;

    @Schema(description = "概述")
    private String desc;

    @Schema(description = "海报")
    private String logo;

    @Schema(description = "个人简介")
    private String identifyDesc;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "个人作品")
    private String product;

    @Schema(description = "详情")
    private String images;

    @Schema(description = "0筹备中，1进行中，2已结束")
    private String status;

    @Schema(description = "价格")
    private BigDecimal price;

    @Schema(description = "开始时间")
    private LocalDateTime startTime;

    @Schema(description = "结束时间")
    private LocalDateTime endTime;

    @Schema(description = "每天闭馆时间")
    private String biguan;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "0待审核，1已通过，2已拒绝")
    private String shenhe;

    @Schema(description = "拒绝原因")
    private String refuse;


}
