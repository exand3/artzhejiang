package cn.com.zhejiangart.model.vo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * <p>
 * 会员表
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_user")
@Schema(name = "User对象", description = "会员表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "电子邮箱")
    private String email;

    @Schema(description = "密码：默认为123456")
    private String password;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "性别")
    private Boolean gender;

    @Schema(description = "生日")
    private LocalDate birthday;

    @Schema(description = "余额")
    private BigDecimal money;

    @Schema(description = "积分")
    private BigDecimal score;

    @Schema(description = "保证金")
    private BigDecimal deposit;

    @Schema(description = "登录时间")
    private Long logintime;

    @Schema(description = "登录IP")
    private String loginip;

    @Schema(description = "创建时间")
    private Long createtime;

    @Schema(description = "更新时间")
    private Long updatetime;

    @Schema(description = "unionid")
    private String unionid;

    @Schema(description = "openid")
    private String openid;

    @Schema(description = "身份证正面")
    private String cardFront;

    @Schema(description = "身份证反面")
    private String cardBack;

    @Schema(description = "身份证号")
    private String cardNumber;

    @Schema(description = "电子签名")
    private String cardNature;

    @Schema(description = "审核是否通过，0待审核，1已通过，2已拒绝")
    private String shenhe;

    @Schema(description = "身份，0普通用户，1艺术家，2企业店铺")
    private String identify;

    @Schema(description = "营业执照")
    private String cardImage;

    @Schema(description = "社会信用代码")
    private String cardCard;

    @Schema(description = "银行卡账号")
    private String bankCard;

    @Schema(description = "开户行")
    private String bankAddress;

    @Schema(description = "个人简介")
    private String identityDesc;

    @Schema(description = "真名")
    private String realName;

    @Schema(description = "0正常，1封禁")
    private String forbidden;

    @Schema(description = "粉丝数量")
    private Integer fans;

    @Schema(description = "关注数量")
    private Integer follow;

    @Schema(description = "作品数量限制")
    private Integer productLimit;

    @Schema(description = "删除时间")
    private Integer deleteTime;

    @Schema(description = "专业")
    private Integer major;

    @Schema(description = "审核通过的时间")
    private String shenheTime;

    @Schema(description = "上级")
    private Integer promoter;

//  清除用户所有敏感信息
    public User cleanSensitiveInfo() {
        User user = new User();
        user.setId(this.id);
        user.setNickname(this.nickname);
        user.setAvatar(this.avatar);
        return user;
    }
}
