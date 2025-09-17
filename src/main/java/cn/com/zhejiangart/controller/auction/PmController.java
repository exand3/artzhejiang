package cn.com.zhejiangart.controller.auction;

import cn.com.zhejiangart.model.dto.AuctionActivityDetailDTO;
import cn.com.zhejiangart.model.dto.AuctionItemDTO;
import cn.com.zhejiangart.model.dto.AuctionItemDetailDTO;
import cn.com.zhejiangart.model.vo.AuctionActivity;
import cn.com.zhejiangart.model.vo.AuctionBid;
import cn.com.zhejiangart.model.vo.AuctionItem;
import cn.com.zhejiangart.model.vo.Product;
import cn.com.zhejiangart.service.IAuctionActivityService;
import cn.com.zhejiangart.service.IAuctionBidService;
import cn.com.zhejiangart.service.IAuctionItemService;
import cn.com.zhejiangart.service.IProductService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * 拍卖管理控制器
 * 提供拍卖列表相关接口，包括预展、限时竞买、即刻拍、公益拍和历史拍卖等类型
 */
@RestController
@RequestMapping("/api/auction/pm")
@Tag(name = "拍卖管理接口", description = "提供拍卖列表相关功能")
public class PmController {

    @Autowired
    private IAuctionActivityService auctionActivityService;

    @Autowired
    private IAuctionItemService auctionItemService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IAuctionBidService auctionBidService;

    /**
     * 获取拍卖列表（统一接口）
     * 根据类型参数返回不同类型的拍卖列表
     *
     * @param type 拍卖类型：preview-预展, limited-限时竞买, instant-即刻拍, public-公益拍, history-历史
     * @param page 页码
     * @param size 每页大小
     * @return 拍卖列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取拍卖列表", description = "根据类型参数返回不同类型的拍卖列表，类型包括：preview-预展, limited-限时竞买, instant-即刻拍, public-公益拍, history-历史")
    public Page<AuctionItemDTO> getAuctionList(
            @Parameter(description = "拍卖类型") @RequestParam(required = false) String type,
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") Integer page,
            @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer size) {
        
        Page<AuctionItem> pageResult = new Page<>(page, size);
        QueryWrapper<AuctionItem> queryWrapper = new QueryWrapper<>();
        
        // 根据不同类型筛选数据
        if (type != null && !type.isEmpty()) {
            switch (type) {
                case "preview": // 预展
                    queryWrapper.eq("sale", "1");
                    break;
                case "limited": // 限时竞买
                    queryWrapper.eq("sale", "2");
                    // 可以根据需要添加更多限时竞买的筛选条件
                    break;
                case "instant": // 即刻拍
                    // 即刻拍的筛选条件
                    queryWrapper.apply("EXISTS (SELECT 1 FROM ea8_auction_activity aa WHERE aa.id = ea8_auction_item.auction_id AND aa.auction_type = '1')");
                    break;
                case "public": // 公益拍
                    // 公益拍的筛选条件，可能需要根据具体业务规则调整
                    queryWrapper.apply("EXISTS (SELECT 1 FROM ea8_auction_activity aa WHERE aa.id = ea8_auction_item.auction_id AND aa.name LIKE '%公益%')");
                    break;
                case "history": // 历史拍卖
                    List<String> endStatus = Arrays.asList("4", "5"); // 4已取消，5已结束
                    queryWrapper.in("status", endStatus);
                    break;
                default:
                    // 默认不筛选类型
                    break;
            }
        }
        
        // 执行分页查询
        Page<AuctionItem> resultPage = auctionItemService.page(pageResult, queryWrapper);
        
        // 转换为DTO
        Page<AuctionItemDTO> responsePage = new Page<>(page, size, resultPage.getTotal());
        List<AuctionItemDTO> dtoList = resultPage.getRecords().stream().map(this::convertToDTO).toList();
        responsePage.setRecords(dtoList);
        
        return responsePage;
    }

    /**
     * 获取预展列表
     *
     * @param page 页码
     * @param size 每页大小
     * @return 预展列表
     */
    @GetMapping("/preview")
    @Operation(summary = "获取预展列表")
    public Page<AuctionItemDTO> getPreviewList(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return getAuctionList("preview", page, size);
    }

    /**
     * 获取限时竞买列表
     *
     * @param page 页码
     * @param size 每页大小
     * @return 限时竞买列表
     */
    @GetMapping("/limited")
    @Operation(summary = "获取限时竞买列表")
    public Page<AuctionItemDTO> getLimitedList(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return getAuctionList("limited", page, size);
    }

    /**
     * 获取即刻拍列表
     *
     * @param page 页码
     * @param size 每页大小
     * @return 即刻拍列表
     */
    @GetMapping("/instant")
    @Operation(summary = "获取即刻拍列表")
    public Page<AuctionItemDTO> getInstantList(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return getAuctionList("instant", page, size);
    }

    /**
     * 获取公益拍列表
     *
     * @param page 页码
     * @param size 每页大小
     * @return 公益拍列表
     */
    @GetMapping("/public")
    @Operation(summary = "获取公益拍列表")
    public Page<AuctionItemDTO> getPublicList(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return getAuctionList("public", page, size);
    }

    /**
     * 获取历史拍卖列表
     *
     * @param page 页码
     * @param size 每页大小
     * @return 历史拍卖列表
     */
    @GetMapping("/history")
    @Operation(summary = "获取历史拍卖列表")
    public Page<AuctionItemDTO> getHistoryList(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return getAuctionList("history", page, size);
    }

    /**
     * 获取拍卖场详情
     * 包含拍卖场信息和相关的拍品列表
     *
     * @param id 拍卖场ID
     * @return 拍卖场详情和拍品列表
     */
    @GetMapping("/activity/{id}")
    @Operation(summary = "获取拍卖场详情", description = "获取拍卖场详情，包括拍卖场信息和相关的拍品列表")
    public AuctionActivityDetailDTO getActivityDetail(
            @Parameter(description = "拍卖场ID") @PathVariable Integer id) {
        
        // 获取拍卖场信息
        AuctionActivity activity = auctionActivityService.getById(id);
        if (activity == null) {
            return null;
        }

        // 获取相关的拍品列表
        QueryWrapper<AuctionItem> itemQueryWrapper = new QueryWrapper<>();
        itemQueryWrapper.eq("auction_id", id);
        List<AuctionItem> items = auctionItemService.list(itemQueryWrapper);

        AuctionActivityDetailDTO detailDTO = new AuctionActivityDetailDTO();
        detailDTO.setActivity(activity);
        detailDTO.setItems(items);
        
        return detailDTO;
    }

    /**
     * 获取拍品详情
     * 包含拍品信息、关联的商品信息和出价记录
     *
     * @param id 拍品ID
     * @param page 页码
     * @param size 每页大小
     * @return 拍品详情
     */
    @GetMapping("/item/{id}")
    @Operation(summary = "获取拍品详情", description = "获取拍品详情，包括拍品信息、关联的商品信息和出价记录")
    public AuctionItemDetailDTO getItemDetail(
            @Parameter(description = "拍品ID") @PathVariable Integer id,
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") Integer page,
            @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer size) {
        
        // 获取拍品信息
        AuctionItem item = auctionItemService.getById(id);
        if (item == null) {
            return null;
        }

        // 获取关联的商品信息
        Product product = null;
        if (item.getProductId() != null) {
            product = productService.getById(item.getProductId());
        }

        // 获取出价记录
        Page<AuctionBid> bidPage = new Page<>(page, size);
        QueryWrapper<AuctionBid> bidQueryWrapper = new QueryWrapper<>();
        bidQueryWrapper.eq("product_id", item.getProductId());
        bidQueryWrapper.orderByDesc("bid_time");
        Page<AuctionBid> bids = auctionBidService.page(bidPage, bidQueryWrapper);

        AuctionItemDetailDTO detailDTO = new AuctionItemDetailDTO();
        detailDTO.setItem(item);
        detailDTO.setProduct(product);
        detailDTO.setBids(bids);
        
        return detailDTO;
    }

    /**
     * 将AuctionItem转换为AuctionItemDTO
     * 
     * @param auctionItem 拍卖品对象
     * @return 拍卖品DTO对象
     */
    private AuctionItemDTO convertToDTO(AuctionItem auctionItem) {
        AuctionItemDTO dto = new AuctionItemDTO();
        BeanUtils.copyProperties(auctionItem, dto);
        
        // 设置额外的属性
        dto.setId(auctionItem.getId());
        dto.setActivityId(auctionItem.getAuctionId());
        dto.setStartingPrice(auctionItem.getStartingPrice());
        dto.setAuctionStartTime(auctionItem.getStartTime());
        dto.setStatus(auctionItem.getStatus());
        
        // 获取关联的商品信息
        if (auctionItem.getProductId() != null) {
            Product product = productService.getById(auctionItem.getProductId());
            if (product != null) {
                dto.setName(product.getGoodsName());
                dto.setCategory(product.getGoodsName());
                dto.setSize(product.getGoodsRange());
                dto.setImage(product.getLogoPic());
            }
        }
        
        return dto;
    }

}