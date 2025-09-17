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
 * 新闻表
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_news")
@Schema(name = "News对象", description = "新闻表")
public class News implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "所属分类")
    private Integer cateId;

    @Schema(description = "文章标题")
    private String name;

    @Schema(description = "文章描述")
    private String introduce;

    @Schema(description = "关键词")
    private String keyword;

    @Schema(description = "作者")
    private Integer adminId;

    @Schema(description = "排序")
    private String sort;

    @Schema(description = "新闻图片（小）")
    private String image;

    @Schema(description = "新闻详情图片")
    private String images;

    @Schema(description = "详情")
    private String detail;

    @Schema(description = "创建时间")
    private Integer createTime;

    @Schema(description = "修改时间")
    private Integer updateTime;

    @Schema(description = "0待审核，1审核通过，2审核拒绝")
    private String status;


}
