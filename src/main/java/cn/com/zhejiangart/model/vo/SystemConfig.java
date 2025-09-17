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
 * 系统配置表
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_system_config")
@Schema(name = "SystemConfig对象", description = "系统配置表")
public class SystemConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "变量名")
    private String name;

    @Schema(description = "分组")
    private String group;

    @Schema(description = "变量值")
    private String value;

    @Schema(description = "备注信息")
    private String remark;

    private Integer sort;

    @Schema(description = "创建时间")
    private Integer createTime;

    @Schema(description = "更新时间")
    private Integer updateTime;


}
