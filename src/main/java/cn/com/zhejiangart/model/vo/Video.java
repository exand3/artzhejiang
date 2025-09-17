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
 * 
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_video")
@Schema(name = "Video对象")
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "视频标题")
    private String name;

    @Schema(description = "视频连接")
    private String url;

    @Schema(description = "分类id")
    private Integer cateId;

    @Schema(description = "创建时间")
    private Integer createTime;

    @Schema(description = "删除时间")
    private Integer delTime;

    @Schema(description = "排序")
    private String sort;

    @Schema(description = "封面图")
    private String image;


}
