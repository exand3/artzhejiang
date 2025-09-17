package cn.com.zhejiangart.service.impl;

import cn.com.zhejiangart.model.vo.AuctionOrder;
import cn.com.zhejiangart.mapper.AuctionOrderMapper;
import cn.com.zhejiangart.service.IAuctionOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 拍卖订单关联表 服务实现类
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Service
public class AuctionOrderServiceImpl extends ServiceImpl<AuctionOrderMapper, AuctionOrder> implements IAuctionOrderService {

}
