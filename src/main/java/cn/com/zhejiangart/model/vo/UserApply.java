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
 * 用户审核资料表
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_user_apply")
@Schema(name = "UserApply对象", description = "用户审核资料表")
public class UserApply implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    @Schema(description = "对应goodstype的id")
    private Integer categoryId;

    @Schema(description = "经营者资质介绍资料")
    private String qualificationFiles;

    @Schema(description = "对应goodstype2的id")
    private Integer type;

    @Schema(description = "相关组品资料（如书画、紫砂、陶瓷、工艺）及作者介绍")
    private String worksDataFiles;

    @Schema(description = "10款商品详情介绍")
    private String worksImages;


}
