package cn.com.zhejiangart.controller.user;

import cn.com.zhejiangart.model.dto.AuctionItemDTO;
import cn.com.zhejiangart.model.dto.OrderDetailDTO;
import cn.com.zhejiangart.model.dto.OrderItemDetailDTO;
import cn.com.zhejiangart.model.dto.OrderListItemDTO;
import cn.com.zhejiangart.model.vo.*;
import cn.com.zhejiangart.mapper.OrderMapper;
import cn.com.zhejiangart.mapper.OrderitemMapper;
import cn.com.zhejiangart.mapper.ProductMapper;
import cn.com.zhejiangart.mapper.AuctionBidMapper;
import cn.com.zhejiangart.mapper.AuctionItemMapper;
import cn.com.zhejiangart.mapper.AuctionActivityMapper;
import cn.com.zhejiangart.mapper.AuctionResultMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user/trade")
@Tag(name = "用户交易接口")
public class TradeController {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderitemMapper orderitemMapper;

    @Autowired
    private ProductMapper productMapper;
    
    @Autowired
    private AuctionBidMapper auctionBidMapper;
    
    @Autowired
    private AuctionItemMapper auctionItemMapper;
    
    @Autowired
    private AuctionActivityMapper auctionActivityMapper;
    
    @Autowired
    private AuctionResultMapper auctionResultMapper;

    @GetMapping("/orders")
    @Operation(summary = "获取订单列表")
    public IPage<OrderListItemDTO> getOrderList(
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") Integer page,
            @Parameter(description = "每页数量") @RequestParam(defaultValue = "10") Integer size,
            @Parameter(description = "订单状态") @RequestParam(required = false) String status,
            @Parameter(description = "产品名称模糊查询") @RequestParam(required = false) String productName) {

        // 查询订单
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        if (status != null && !status.isEmpty()) {
            orderQueryWrapper.eq("status", status);
        }
        orderQueryWrapper.orderByDesc("create_time");

        IPage<Order> orderPage = orderMapper.selectPage(new Page<>(page, size), orderQueryWrapper);
        IPage<OrderListItemDTO> resultPage = new Page<>(page, size, orderPage.getTotal());

        List<OrderListItemDTO> orderListItems = new ArrayList<>();

        for (Order order : orderPage.getRecords()) {
            OrderListItemDTO orderListItem = new OrderListItemDTO();
            orderListItem.setId(order.getId());
            orderListItem.setOrderNo(order.getOrderNo());
            orderListItem.setPrice(order.getTotalPrice());
            orderListItem.setCreateTime(order.getCreateTime());
            orderListItem.setStatus(order.getStatus());

            // 获取订单项
            QueryWrapper<Orderitem> itemQueryWrapper = new QueryWrapper<>();
            itemQueryWrapper.eq("order_id", order.getId());
            List<Orderitem> orderItems = orderitemMapper.selectList(itemQueryWrapper);

            // 处理产品名称模糊查询
            if (productName != null && !productName.isEmpty()) {
                List<Orderitem> filteredItems = orderItems.stream()
                        .filter(item -> item.getProductName() != null && item.getProductName().contains(productName))
                        .toList();
                if (filteredItems.isEmpty()) {
                    continue; // 如果没有匹配的产品，则跳过该订单
                }
            }

            // 获取订单项对应的产品信息
            List<String> authors = new ArrayList<>();
            List<String> sizes = new ArrayList<>();
            List<String> categories = new ArrayList<>();
            String firstProductImage = null;

            for (int i = 0; i < orderItems.size(); i++) {
                Orderitem item = orderItems.get(i);
                if (i == 0) {
                    firstProductImage = item.getImage();
                }

                Product product = productMapper.selectById(item.getProductId());
                if (product != null) {
                    authors.add(product.getGoodsCap() != null ? product.getGoodsCap() : "");
                    sizes.add(product.getGoodsRange() != null ? product.getGoodsRange() : "");
                    // 这里假设类别就是产品名称，如果项目中有专门的类别字段，需要相应修改
                    categories.add(product.getGoodsName() != null ? product.getGoodsName() : "");
                } else {
                    authors.add("");
                    sizes.add("");
                    categories.add("");
                }
            }

            orderListItem.setAuthors(String.join(";", authors));
            orderListItem.setSizes(String.join(";", sizes));
            orderListItem.setCategories(String.join(";", categories));
            orderListItem.setFirstProductImage(firstProductImage);

            orderListItems.add(orderListItem);
        }

        resultPage.setRecords(orderListItems);
        return resultPage;
    }
    /**
     * 获取订单详情
     *
     * @param orderId 订单ID
     * @return 订单详情DTO
     */
    @GetMapping("/order/detail")
    @Operation(summary = "获取订单详情")
    public OrderDetailDTO getOrderDetail(
            @Parameter(description = "订单ID") @RequestParam Integer orderId) {

        Order order = orderMapper.selectById(orderId);
        if (order == null) {
            return null;
        }

        OrderDetailDTO orderDetail = new OrderDetailDTO();
        BeanUtils.copyProperties(order, orderDetail);

        // 获取订单项
        QueryWrapper<Orderitem> itemQueryWrapper = new QueryWrapper<>();
        itemQueryWrapper.eq("order_id", orderId);
        List<Orderitem> orderItems = orderitemMapper.selectList(itemQueryWrapper);

        List<OrderItemDetailDTO> itemDetails = new ArrayList<>();
        for (Orderitem orderitem : orderItems) {
            OrderItemDetailDTO itemDetail = new OrderItemDetailDTO();
            itemDetail.setProductId(orderitem.getProductId());
            itemDetail.setProductName(orderitem.getProductName());
            itemDetail.setImage(orderitem.getImage());
            itemDetail.setPrice(orderitem.getPrice());
            itemDetail.setCount(orderitem.getCount());

            // 获取产品详细信息
            Product product = productMapper.selectById(orderitem.getProductId());
            if (product != null) {
                itemDetail.setAuthor(product.getGoodsCap());
                itemDetail.setSize(product.getGoodsRange());
                // 这里假设类别就是产品名称，如果项目中有专门的类别字段，需要相应修改
                itemDetail.setCategory(product.getGoodsName());
            }

            itemDetails.add(itemDetail);
        }

        orderDetail.setOrderItems(itemDetails);
        return orderDetail;
    }

    /**
     * 获取用户参拍的拍品列表
     * 
     * @param page 页码
     * @param size 每页数量
     * @param activityId 活动ID
     * @param itemName 拍品名称
     * @return 分页的拍品列表
     */
    @GetMapping("/auction/items")
    @Operation(summary = "查看我参拍的拍品")
    public IPage<AuctionItemDTO> getMyAuctionItems(
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") Integer page,
            @Parameter(description = "每页数量") @RequestParam(defaultValue = "10") Integer size,
            @Parameter(description = "活动ID") @RequestParam(required = false) Integer activityId,
            @Parameter(description = "拍品名称") @RequestParam(required = false) String itemName) {
        
        // 创建分页对象
        IPage<AuctionBid> bidPage = new Page<>(page, size);
        
        // 查询当前用户的出价记录
        QueryWrapper<AuctionBid> bidQueryWrapper = new QueryWrapper<>();
        // 这里假设从上下文中获取用户ID，实际项目中需要根据认证信息获取
        // bidQueryWrapper.eq("user_id", userId);
        bidQueryWrapper.orderByDesc("bid_time");
        
        // 执行分页查询
        IPage<AuctionBid> resultPage = auctionBidMapper.selectPage(bidPage, bidQueryWrapper);
        
        // 创建返回页面对象
        IPage<AuctionItemDTO> responsePage = new Page<>(page, size, resultPage.getTotal());
        List<AuctionItemDTO> auctionItemDTOs = new ArrayList<>();
        
        // 获取所有出价记录中的拍品ID
        List<Integer> productIds = resultPage.getRecords().stream()
                .map(AuctionBid::getProductId)
                .distinct()
                .collect(Collectors.toList());
        
        if (!productIds.isEmpty()) {
            // 查询拍品信息
            QueryWrapper<AuctionItem> itemQueryWrapper = new QueryWrapper<>();
            itemQueryWrapper.in("product_id", productIds);
            
            if (activityId != null) {
                itemQueryWrapper.eq("auction_id", activityId);
            }
            
            List<AuctionItem> auctionItems = auctionItemMapper.selectList(itemQueryWrapper);
            
            // 过滤拍品名称
            if (itemName != null && !itemName.isEmpty()) {
                auctionItems = auctionItems.stream()
                        .filter(item -> {
                            Product product = productMapper.selectById(item.getProductId());
                            return product != null && product.getGoodsName() != null && 
                                   product.getGoodsName().contains(itemName);
                        })
                        .collect(Collectors.toList());
            }
            
            // 转换为DTO对象
            for (AuctionItem auctionItem : auctionItems) {
                Product product = productMapper.selectById(auctionItem.getProductId());
                if (product == null) continue;
                
                AuctionItemDTO dto = new AuctionItemDTO();
                dto.setId(auctionItem.getId());
                dto.setActivityId(auctionItem.getAuctionId());
                dto.setName(product.getGoodsName());
                dto.setCategory(product.getGoodsName()); // 类别字段暂定为商品名称
                dto.setSize(product.getGoodsRange());
                dto.setImage(product.getLogoPic());
                dto.setStartingPrice(auctionItem.getStartingPrice());
                dto.setAuctionStartTime(auctionItem.getStartTime());
                dto.setStatus(auctionItem.getStatus());
                
                auctionItemDTOs.add(dto);
            }
        }
        
        responsePage.setRecords(auctionItemDTOs);
        return responsePage;
    }
    
    /**
     * 获取用户已拍下的拍品列表
     * 
     * @param page 页码
     * @param size 每页数量
     * @param activityId 活动ID
     * @param itemName 拍品名称
     * @return 分页的拍品列表
     */
    @GetMapping("/auction/won-items")
    @Operation(summary = "查看已拍下的拍品")
    public IPage<AuctionItemDTO> getWonAuctionItems(
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") Integer page,
            @Parameter(description = "每页数量") @RequestParam(defaultValue = "10") Integer size,
            @Parameter(description = "活动ID") @RequestParam(required = false) Integer activityId,
            @Parameter(description = "拍品名称") @RequestParam(required = false) String itemName) {
        
        // 查询当前用户获胜的拍卖结果
        QueryWrapper<AuctionResult> resultQueryWrapper = new QueryWrapper<>();
        // 这里假设从上下文中获取用户ID，实际项目中需要根据认证信息获取
        // resultQueryWrapper.eq("winner_id", userId);
        resultQueryWrapper.eq("status", "sold"); // 只查询已售出的拍品
        
        IPage<AuctionResult> resultPage = new Page<>(page, size);
        resultPage = auctionResultMapper.selectPage(resultPage, resultQueryWrapper);
        
        // 创建返回页面对象
        IPage<AuctionItemDTO> responsePage = new Page<>(page, size, resultPage.getTotal());
        List<AuctionItemDTO> auctionItemDTOs = new ArrayList<>();
        
        // 获取所有获胜的拍卖品ID
        List<Integer> auctionItemIds = resultPage.getRecords().stream()
                .map(AuctionResult::getAuctionItemId)
                .collect(Collectors.toList());
        
        if (!auctionItemIds.isEmpty()) {
            // 查询拍品信息
            QueryWrapper<AuctionItem> itemQueryWrapper = new QueryWrapper<>();
            itemQueryWrapper.in("id", auctionItemIds);
            
            if (activityId != null) {
                itemQueryWrapper.eq("auction_id", activityId);
            }
            
            List<AuctionItem> auctionItems = auctionItemMapper.selectList(itemQueryWrapper);
            
            // 过滤拍品名称
            if (itemName != null && !itemName.isEmpty()) {
                auctionItems = auctionItems.stream()
                        .filter(item -> {
                            Product product = productMapper.selectById(item.getProductId());
                            return product != null && product.getGoodsName() != null && 
                                   product.getGoodsName().contains(itemName);
                        })
                        .toList();
            }
            
            // 转换为DTO对象
            for (AuctionItem auctionItem : auctionItems) {
                Product product = productMapper.selectById(auctionItem.getProductId());
                if (product == null) continue;
                
                AuctionItemDTO dto = new AuctionItemDTO();
                dto.setId(auctionItem.getId());
                dto.setActivityId(auctionItem.getAuctionId());
                dto.setName(product.getGoodsName());
                dto.setCategory(product.getGoodsName()); // 类别字段暂定为商品名称
                dto.setSize(product.getGoodsRange());
                dto.setImage(product.getLogoPic());
                dto.setStartingPrice(auctionItem.getStartingPrice());
                dto.setAuctionStartTime(auctionItem.getStartTime());
                dto.setStatus(auctionItem.getStatus());
                
                auctionItemDTOs.add(dto);
            }
        }
        
        responsePage.setRecords(auctionItemDTOs);
        return responsePage;
    }
    

}