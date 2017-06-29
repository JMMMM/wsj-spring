package com.wsj.wechat.api;

import com.wsj.wx.api.model.close.CloseOrderReqData;
import com.wsj.wx.api.model.close.CloseOrderResData;
import com.wsj.wx.api.model.common.WeiXinResult;
import com.wsj.wx.api.model.micropay.MicroPayReqData;
import com.wsj.wx.api.model.micropay.MicroPayResData;
import com.wsj.wx.api.model.prepay.PrePayReqData;
import com.wsj.wx.api.model.prepay.PrePayResData;
import com.wsj.wx.api.model.query.OrderQueryReqData;
import com.wsj.wx.api.model.query.OrderQueryResData;
import com.wsj.wx.api.model.query.RefundQueryReqData;
import com.wsj.wx.api.model.query.RefundQueryResData;
import com.wsj.wx.api.model.redpack.RedpackReqData;
import com.wsj.wx.api.model.redpack.RedpackResData;
import com.wsj.wx.api.model.refund.RefundReqData;
import com.wsj.wx.api.model.refund.RefundResData;
import com.wsj.wx.api.model.reverse.ReverseReqData;
import com.wsj.wx.api.model.reverse.ReverseResData;
import com.wsj.wx.api.model.transfers.TransfersReqData;
import com.wsj.wx.api.model.transfers.TransfersResData;

/**
 * 微信支付客户端
 */
public interface WeiXinPayClient {

    /**
     * 预支付接口
     *
     * @param prePayReqData 预支付参数
     * @return 预支付返回值，只有成功时才返回PrePayResData对象，失败时返回null,调用时需要做判断
     */
    public WeiXinResult<PrePayResData> prePay(PrePayReqData prePayReqData);

    /**
     * 订单查询
     *
     * @param orderQueryReqData 订单查询参数
     * @return 订单查询结果
     */
    public WeiXinResult<OrderQueryResData> orderQuery(OrderQueryReqData orderQueryReqData);

    /**
     * 关闭订单
     *
     * @param closeOrderReqData 关闭订单参数
     * @return 订单查询结果
     */
    public WeiXinResult<CloseOrderResData> closeOrder(CloseOrderReqData closeOrderReqData);

    /**
     * 申请退款
     *
     * @param refundReqData 申请退款参数
     * @return 申请退款结果
     */
    public WeiXinResult<RefundResData> refund(RefundReqData refundReqData);

    /**
     * 退款查询
     *
     * @param refundQueryReqData 退款查询参数
     * @return 查询结果
     */
    public WeiXinResult<RefundQueryResData> refundQuery(RefundQueryReqData refundQueryReqData);

    /**
     * 刷卡支付
     *
     * @param microPayReqData 刷卡支付参数
     * @return 刷卡支付结果
     */
    public WeiXinResult<MicroPayResData> microPay(MicroPayReqData microPayReqData);

    /**
     * 刷卡支付
     * 客户端实现支付查询
     *
     * @param microPayReqData 刷卡支付参数
     * @return 刷卡支付结果
     */
    public WeiXinResult<MicroPayResData> microPaySimple(MicroPayReqData microPayReqData);

    /**
     * 刷卡支付撤销订单
     *
     * @param reverseReqData 刷卡支付撤销订单参数
     * @return 刷卡支付撤销订单结果
     */
    public WeiXinResult<ReverseResData> reverse(ReverseReqData reverseReqData);
    
    /**
     * 企业付款方法
     *
     * @param transfersReqData 企业付款参数
     * @return 企业付款结果
     */
    public TransfersResData transfers(TransfersReqData transfersReqData);

    /**
     * 发送普通红包方法
     *
     * @param redpackReqData 发送红包参数
     * @return 红包发送结果
     */
    public RedpackResData sendredpack(RedpackReqData redpackReqData);
}
