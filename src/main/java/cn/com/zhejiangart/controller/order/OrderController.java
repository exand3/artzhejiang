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
 * 订单控制器
 * 提供订单管理相关接口
 */
@RestController
@RequestMapping("/api/order")
@Tag(name = "订单管理接口", description = "提供订单管理相关功能")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderitemService orderitemService;

    /**
     * 获取订单列表
     *
     * @param page 页码
     * @param size 每页大小
     * @param status 订单状态
     * @return 订单列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取订单列表", description = "获取订单列表，支持分页和状态筛选")
    public Page<Order> getOrderList(
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") Integer page,
            @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer size,
            @Parameter(description = "订单状态") @RequestParam(required = false) String status) {

        Page<Order> pageResult = new Page<>(page, size);
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();

        if (status != null && !status.isEmpty()) {
            queryWrapper.eq("status", status);
        }

        return orderService.page(pageResult, queryWrapper);
    }

    /**
     * 获取订单详情
     *
     * @param id 订单ID
     * @return 订单详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取订单详情", description = "根据订单ID获取订单详情信息")
    public Order getOrderDetail(
            @Parameter(description = "订单ID") @PathVariable Integer id) {
        return orderService.getById(id);
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

        Page<Orderitem> pageResult = new Page<>(page, size);
        QueryWrapper<Orderitem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);

        return orderitemService.page(pageResult, queryWrapper);
    }
}