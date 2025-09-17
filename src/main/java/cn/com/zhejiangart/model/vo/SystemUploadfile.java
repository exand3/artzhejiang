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
 * 上传文件表
 * </p>
 *
 * @author author
 * @since 2025-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_system_uploadfile")
@Schema(name = "SystemUploadfile对象", description = "上传文件表")
public class SystemUploadfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "存储位置")
    private String uploadType;

    @Schema(description = "文件原名")
    private String originalName;

    @Schema(description = "物理路径")
    private String url;

    @Schema(description = "宽度")
    private String imageWidth;

    @Schema(description = "高度")
    private String imageHeight;

    @Schema(description = "图片类型")
    private String imageType;

    @Schema(description = "图片帧数")
    private Integer imageFrames;

    @Schema(description = "mime类型")
    private String mimeType;

    @Schema(description = "文件大小")
    private Integer fileSize;

    private String fileExt;

    @Schema(description = "文件 sha1编码")
    private String sha1;

    @Schema(description = "创建日期")
    private Integer createTime;

    @Schema(description = "更新时间")
    private Integer updateTime;

    @Schema(description = "上传时间")
    private Integer uploadTime;

    @Schema(description = "上传的人")
    private Integer userId;


}
