package cn.com.zhejiangart.model.dto;

import cn.com.zhejiangart.model.vo.AuctionBid;
import cn.com.zhejiangart.model.vo.AuctionItem;
import cn.com.zhejiangart.model.vo.Product;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "AuctionItemDetailDTO", description = "拍卖品详情信息")
public class AuctionItemDetailDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "拍卖品信息")
    private AuctionItem item;

    @Schema(description = "商品信息")
    private Product product;

    @Schema(description = "出价记录")
    private Page<AuctionBid> bids;
}