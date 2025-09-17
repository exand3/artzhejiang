package cn.com.zhejiangart.controller.index;

import cn.com.zhejiangart.mapper.BannerMapper;
import cn.com.zhejiangart.mapper.Goodstype2Mapper;
import cn.com.zhejiangart.mapper.NewcateMapper;
import cn.com.zhejiangart.mapper.ProductMapper;
import cn.com.zhejiangart.mapper.AuctionActivityMapper;
import cn.com.zhejiangart.mapper.NewsMapper;
import cn.com.zhejiangart.mapper.UserMapper;
import cn.com.zhejiangart.model.dto.HomepageDTO;
import cn.com.zhejiangart.model.dto.GoodsType2DTO;
import cn.com.zhejiangart.model.dto.NewsCateDTO;
import cn.com.zhejiangart.model.dto.ShopcartCountDTO;
import cn.com.zhejiangart.model.vo.Banner;
import cn.com.zhejiangart.model.vo.Goodstype2;
import cn.com.zhejiangart.model.vo.Newcate;
import cn.com.zhejiangart.model.vo.Product;
import cn.com.zhejiangart.model.vo.AuctionActivity;
import cn.com.zhejiangart.model.vo.News;
import cn.com.zhejiangart.model.vo.User;
import cn.com.zhejiangart.util.Result;
import cn.com.zhejiangart.util.ThreadUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页控制器
 */
@RestController
@RequestMapping("/api/index")
@Tag(name = "首页相关接口")
public class HomeController {

    @Resource
    private BannerMapper bannerMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private AuctionActivityMapper auctionActivityMapper;

    @Resource
    private NewsMapper newsMapper;

    @Resource
    private Goodstype2Mapper goodstype2Mapper;

    @Resource
    private NewcateMapper newcateMapper;

    @Resource
    private UserMapper userMapper;

    /**
     * 获取首页数据
     *
     * @return 首页数据
     */
    @GetMapping("/homepage")
    @Operation(summary = "获取首页数据", description = "获取首页推荐内容，包括热门艺术品、即将开始的拍卖和最新资讯")
    public Result<?> getHomepage() {
        try {
            HomepageDTO homepageDTO = new HomepageDTO();

            // 获取轮播图
            List<Banner> banners = bannerMapper.selectList(null);
            homepageDTO.setBanner(banners);

            // 获取热门艺术品（这里简单获取前4个商品作为示例）
            List<Product> hotArtworks = productMapper.selectList(null);
            if (hotArtworks.size() > 4) {
                hotArtworks = hotArtworks.subList(0, 4);
            }
            homepageDTO.setHotArtworks(hotArtworks);

            // 获取即将开始的拍卖（这里简单获取前4个拍卖活动作为示例）
            List<AuctionActivity> auctions = auctionActivityMapper.selectList(null);
            if (auctions.size() > 4) {
                auctions = auctions.subList(0, 4);
            }
            homepageDTO.setAuctions(auctions);

            // 获取艺术家（这里简单获取前4个用户作为示例）
            List<User> artists = userMapper.selectList(null);
            if (artists.size() > 4) {
                artists = artists.subList(0, 4);
            }
            homepageDTO.setArtists(artists);

            // 获取最新资讯（这里简单获取前4个新闻作为示例）
            List<News> newsList = newsMapper.selectList(null);
            if (newsList.size() > 4) {
                newsList = newsList.subList(0, 4);
            }
            homepageDTO.setNews(newsList);

            return Result.success(homepageDTO);
        } catch (Exception e) {
            return Result.error(500, "获取首页数据失败：" + e.getMessage());
        }
    }

    /**
     * 获取艺术分类
     *
     * @return 艺术分类列表
     */
    @GetMapping("/goodsType2")
    @Operation(summary = "获取艺术分类", description = "获取首页艺术分类")
    public Result<?> getGoodsType2() {
        try {
            List<Goodstype2> goodsType2List = goodstype2Mapper.selectList(null);
            
            GoodsType2DTO goodsType2DTO = new GoodsType2DTO();
            goodsType2DTO.setGoodsType2(goodsType2List);
            
            return Result.success(goodsType2DTO);
        } catch (Exception e) {
            return Result.error(500, "获取艺术分类失败：" + e.getMessage());
        }
    }

    /**
     * 获取新闻分类
     *
     * @return 新闻分类列表
     */
    @GetMapping("/newscate")
    @Operation(summary = "获取新闻分类", description = "获取首页新闻分类")
    public Result<?> getNewsCate() {
        try {
            List<Newcate> newsCateList = newcateMapper.selectList(null);
            
            NewsCateDTO newsCateDTO = new NewsCateDTO();
            newsCateDTO.setNews(newsCateList);
            
            return Result.success(newsCateDTO);
        } catch (Exception e) {
            return Result.error(500, "获取新闻分类失败：" + e.getMessage());
        }
    }

    /**
     * 获取购物车数量
     *
     * @return 购物车数量
     */
    @GetMapping("/shopcartCount")
    @Operation(summary = "获取购物车数量", description = "获取购物车商品数量")
    public Result<?> getShopcartCount() {
        try {
            // 这里应该从数据库或缓存中获取实际的购物车数量
            // 目前作为示例返回0
            ShopcartCountDTO shopcartCountDTO = new ShopcartCountDTO();
            shopcartCountDTO.setCount(0);
            
            return Result.success(shopcartCountDTO);
        } catch (Exception e) {
            return Result.error(500, "获取购物车数量失败：" + e.getMessage());
        }
    }
}