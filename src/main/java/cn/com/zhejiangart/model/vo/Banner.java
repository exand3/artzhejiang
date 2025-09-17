package cn.com.zhejiangart.model.vo;

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
 * 轮播图
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_banner")
@Schema(name = "Banner对象", description = "轮播图")
public class Banner implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "图片")
    private String image;

    @Schema(description = "图片标题")
    private String title;

    @Schema(description = "标题2")
    private String titlee;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "创建时间")
    private String createTime;

    @Schema(description = "修改时间")
    private String updateTime;

    @Schema(description = "排序")
    private String sort;

    @Schema(description = "类型")
    private String type;

    @Schema(description = "跳转的id")
    private Integer tzid;

    @Schema(description = "展示的 1 小程序 2 网站")
    private Integer showOn;


}
