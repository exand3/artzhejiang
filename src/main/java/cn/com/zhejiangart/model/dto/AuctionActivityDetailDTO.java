package cn.com.zhejiangart.model.dto;

import cn.com.zhejiangart.model.vo.AuctionActivity;
import cn.com.zhejiangart.model.vo.AuctionItem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Schema(name = "AuctionActivityDetailDTO", description = "拍卖场详情信息")
public class AuctionActivityDetailDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "拍卖场信息")
    private AuctionActivity activity;

    @Schema(description = "拍品列表")
    private List<AuctionItem> items;
}