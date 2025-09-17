package cn.com.zhejiangart.controller.order;

import cn.com.zhejiangart.model.vo.Order;
import cn.com.zhejiangart.model.vo.Orderitem;
import cn.com.zhejiangart.service.IOrderService;
import cn.com.zhejiangart.service.IOrderitemService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户订单控制器
 * 提供用户相关的订单管理接口
 */
@RestController
@RequestMapping("/api/order/user")
@Tag(name = "用户订单接口", description = "提供用户相关的订单管理功能")
public class UserOrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderitemService orderitemService;

    /**
     * 获取当前用户订单列表
     *
     * @param page 页码
     * @param size 每页大小
     * @param status 订单状态
     * @return 用户订单列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取用户订单列表", description = "获取当前用户的订单列表，支持分页和状态筛选")
    public Page<Order> getUserOrderList(
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") Integer page,
            @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer size,
            @Parameter(description = "订单状态") @RequestParam(required = false) String status) {

        // 这里应该从上下文中获取当前用户ID，示例中使用固定值
        Integer userId = 1; // 实际项目中需要从认证信息中获取

        Page<Order> pageResult = new Page<>(page, size);
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);

        if (status != null && !status.isEmpty()) {
            queryWrapper.eq("status", status);
        }

        queryWrapper.orderByDesc("create_time");

        return orderService.page(pageResult, queryWrapper);
    }

    /**
     * 获取用户订单详情
     *
     * @param id 订单ID
     * @return 订单详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取用户订单详情", description = "根据订单ID获取当前用户的订单详情信息")
    public Order getUserOrderDetail(
            @Parameter(description = "订单ID") @PathVariable Integer id) {
        // 这里应该验证订单是否属于当前用户
        Integer userId = 1; // 实际项目中需要从认证信息中获取

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        queryWrapper.eq("user_id", userId);

        return orderService.getOne(queryWrapper);
    }

    /**
     * 获取订单项列表
     *
     * @param orderId 订单ID
     * @return 订单项列表
     */
    @GetMapping("/items")
    @Operation(summary = "获取订单项列表", description = "根据订单ID获取订单项列表")
    public Page<Orderitem> getOrderItems(
            @Parameter(description = "订单ID") @RequestParam Integer orderId,
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") Integer page,
            @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer size) {

        // 验证订单是否属于当前用户
        Integer userId = 1; // 实际项目中需要从认证信息中获取
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("id", orderId);
        orderQueryWrapper.eq("user_id", userId);
        Order order = orderService.getOne(orderQueryWrapper);

        if (order == null) {
            // 订单不存在或不属于当前用户
            Page<Orderitem> emptyPage = new Page<>(page, size);
            emptyPage.setRecords(java.util.Collections.emptyList());
            emptyPage.setTotal(0L);
            return emptyPage;
        }

        Page<Orderitem> pageResult = new Page<>(page, size);
        QueryWrapper<Orderitem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);

        return orderitemService.page(pageResult, queryWrapper);
    }
}