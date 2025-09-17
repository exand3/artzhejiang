package cn.com.zhejiangart.model.vo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 作品表
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ea8_product")
@Schema(name = "Product对象", description = "作品表")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "产品类别id")
    private Integer cateId;

    @Schema(description = "0下架，1上架")
    private Integer isSale;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "产品名称")
    private String goodsName;

    @Schema(description = "产品简介")
    private String goodsDesc;

    @Schema(description = "价格")
    private BigDecimal price;

    @Schema(description = "库存")
    private Integer inventory;

    @Schema(description = "logo（小图）")
    private String logoPic;

    @Schema(description = "作品展示大图")
    private String indexPic;

    @Schema(description = "商品编码")
    private String spu;

    @Schema(description = "作者")
    private String goodsCap;

    @Schema(description = "作品编号")
    private String goodsMel;

    @Schema(description = "上架时间")
    private Integer saleTime;

    @Schema(description = "尺寸")
    private String goodsRange;

    @Schema(description = "创作年代")
    private Integer goodsCare;

    @Schema(description = "品相")
    private String goodsCom;

    @Schema(description = "快递费用;0包邮，1不包邮")
    private String express;

    @Schema(description = "销量")
    private Integer volume;

    @Schema(description = "收藏量")
    private Integer collection;

    @Schema(description = "删除时间")
    private Integer deleteTime;

    @Schema(description = "拍卖时间")
    private Integer pmStartTime;

    @Schema(description = "拍卖结束时间")
    private Integer pmEndTime;

    @Schema(description = "保留价")
    private BigDecimal baoliu;

    @Schema(description = "起拍价")
    private BigDecimal startPrice;

    @Schema(description = "涨价幅度")
    private BigDecimal fudu;

    @Schema(description = "佣金")
    private BigDecimal yongjin;

    @Schema(description = "延长周期")
    private String zhouqi;

    @Schema(description = "重量")
    private String weight;

    @Schema(description = "产品属性id列表")
    private String attrList;

    @Schema(description = "审核:0.待审核1.通过2.拒绝")
    private String shenhe;

    @Schema(description = "拒绝原因")
    private String disagreeDetail;

    @Schema(description = "上传的人")
    private Integer userId;

    @Schema(description = "浏览量")
    private Integer browser;

    @Schema(description = "租金")
    private BigDecimal zujin;

    @Schema(description = "单位")
    private String unit;

    @Schema(description = "产品详情")
    private String content;

    @Schema(description = "猜你喜欢 0-喜欢 1-否")
    @TableField(value = "`like`")
    private Integer isLike;

    @Schema(description = "0：租赁 1：购买")
    private String isGroom;

    @Schema(description = "创建时间")
    private Integer createTime;

    @Schema(description = "修改时间")
    private Integer updatTime;

    @Schema(description = "点赞量")
    @TableField(value = "`like`")
    private Integer like;

    @Schema(description = "0否，1是")
    @TableField(value = "`frame`")
    private Integer frame;

    @Schema(description = "其他证明材料")
    @TableField(value = "`orther`")
    private String orther;


}