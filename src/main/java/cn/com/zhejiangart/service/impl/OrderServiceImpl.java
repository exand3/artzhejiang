package cn.com.zhejiangart.service.impl;

import cn.com.zhejiangart.model.vo.Order;
import cn.com.zhejiangart.mapper.OrderMapper;
import cn.com.zhejiangart.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
