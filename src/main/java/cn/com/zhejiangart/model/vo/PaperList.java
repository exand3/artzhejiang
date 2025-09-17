package cn.com.zhejiangart.model.vo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 投稿作品表
 * </p>
 *
 * @author author
 * @since 2025-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_paper_list")
@Schema(name = "PaperList对象", description = "投稿作品表")
public class PaperList implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @Schema(description = "大赛id")
    private Integer numberId;

    @Schema(description = "会员iD")
    private Integer userId;

    @Schema(description = "审核状态 0待审 1通过 2拒绝")
    private BigDecimal start;

    @Schema(description = "拒绝的备注")
    private String disagreeDetail;

    @Schema(description = "投票数")
    private Integer counts;

    @Schema(description = "作品封面")
    private String picp;

    @Schema(description = "0未删 1已删")
    private BigDecimal isDel;

    @Schema(description = "作者")
    private String author;

    @Schema(description = "手机")
    private String phone;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "作品名")
    private String wname;

    @Schema(description = "作品类型")
    private String wtype;

    @Schema(description = "尺寸")
    private String wsize;

    @Schema(description = "材质")
    private String wmaterial;

    private Integer likes;

    @Schema(description = "作品简介")
    private String zuoDetail;

    @Schema(description = "访问量")
    private Integer visits;

    @Schema(description = "作品图片")
    private String wpic1;

    @Schema(description = "作品图片")
    private String wpic2;

    @Schema(description = "作品图片")
    private String wpic3;

    @Schema(description = "作品图片")
    private String wpic4;

    private String wpic5;

    @Schema(description = "出生日期")
    private LocalDateTime birth;

    @Schema(description = "身份证id")
    private String sfid;

    @Schema(description = "作者简介")
    private String personDetail;

    @Schema(description = "获奖名次")
    private Integer prizeStatus;

    @Schema(description = "作品设置id")
    private String zpbute;


}
