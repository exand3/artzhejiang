package cn.com.zhejiangart.service.impl;

import cn.com.zhejiangart.model.vo.Order;
import cn.com.zhejiangart.service.IWeChatPayService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 微信支付服务实现类
 */
@Service
public class WeChatPayServiceImpl implements IWeChatPayService {

    @Override
    public String createPayment(Order order) {
        // 实际项目中需要调用微信支付API创建支付订单
        // 这里仅作为示例返回模拟数据
        return "weixin://wxpay/bizpayurl?pr=example";
    }

    @Override
    public boolean handlePaymentNotify(String notifyData) {
        // 实际项目中需要解析微信支付回调数据并处理
        // 这里仅作为示例返回true
        return true;
    }

    @Override
    public String queryPaymentStatus(String orderNo) {
        // 实际项目中需要调用微信支付API查询支付状态
        // 这里仅作为示例返回模拟数据
        return "SUCCESS"; // 支付成功
    }

    @Override
    public boolean refund(Order order, BigDecimal amount) {
        // 实际项目中需要调用微信支付API处理退款
        // 这里仅作为示例返回true
        return true;
    }
}