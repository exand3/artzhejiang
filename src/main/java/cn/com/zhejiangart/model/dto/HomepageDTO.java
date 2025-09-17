package cn.com.zhejiangart.model.dto;

import cn.com.zhejiangart.model.vo.Banner;
import cn.com.zhejiangart.model.vo.Product;
import cn.com.zhejiangart.model.vo.AuctionActivity;
import cn.com.zhejiangart.model.vo.User;
import cn.com.zhejiangart.model.vo.News;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "HomepageDTO", description = "首页数据DTO")
public class HomepageDTO {

    @Schema(description = "轮播图列表")
    private List<Banner> banner;

    @Schema(description = "热门艺术品列表")
    private List<Product> hotArtworks;

    @Schema(description = "拍卖活动列表")
    private List<AuctionActivity> auctions;

    @Schema(description = "艺术家列表")
    private List<User> artists;

    @Schema(description = "新闻列表")
    private List<News> news;
}