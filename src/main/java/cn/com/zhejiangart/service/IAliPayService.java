package cn.com.zhejiangart.service;

import cn.com.zhejiangart.model.vo.Order;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 支付宝服务接口
 */
public interface IAliPayService {

    /**
     * 创建支付宝支付订单
     *
     * @param order 订单信息
     * @return 支付URL或二维码内容
     */
    String createPayment(Order order);

    /**
     * 处理支付宝支付回调
     *
     * @param notifyData 回调数据
     * @return 处理结果
     */
    boolean handlePaymentNotify(String notifyData);

    /**
     * 查询支付状态
     *
     * @param orderNo 订单号
     * @return 支付状态
     */
    String queryPaymentStatus(String orderNo);

    /**
     * 退款处理
     *
     * @param order 订单信息
     * @param amount 退款金额
     * @return 退款结果
     */
    boolean refund(Order order, BigDecimal amount);
}