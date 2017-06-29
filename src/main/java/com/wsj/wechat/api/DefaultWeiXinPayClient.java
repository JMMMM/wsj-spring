//package com.wsj.wx.api.api;
package com.wsj.wechat.api;
import com.wsj.wx.api.business.CloseOrderBusiness;
import com.wsj.wx.api.business.MicroPayBusiness;
import com.wsj.wx.api.business.MicroPayBusinessSimple;
import com.wsj.wx.api.business.OrderQueryBusinss;
import com.wsj.wx.api.business.PrePayBusiness;
import com.wsj.wx.api.business.RedpackBusiness;
import com.wsj.wx.api.business.RefundBusiness;
import com.wsj.wx.api.business.RefundQueryBusiness;
import com.wsj.wx.api.business.ReverseBusiness;
import com.wsj.wx.api.business.TransfersBusiness;
import com.wsj.wx.api.http.HttpClientHolder;
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
 * 微信支付默认客户端实现
 */
public class DefaultWeiXinPayClient implements WeiXinPayClient {

    /**
     * 微信支付请求持有类
     */
    private HttpClientHolder httpClientHolder;

    /**
     * 构造方法，需要传入微信支付配置信息
     *
     * @param configure 微信支付配置信息
     */
    public DefaultWeiXinPayClient() {
        this.httpClientHolder = new HttpClientHolder();
    }

    public WeiXinResult<PrePayResData> prePay(PrePayReqData prePayReqData) {
        return new PrePayBusiness(httpClientHolder).prePay(prePayReqData);
    }

    public WeiXinResult<OrderQueryResData> orderQuery(OrderQueryReqData orderQueryReqData) {
        return new OrderQueryBusinss(httpClientHolder).orderQuery(orderQueryReqData);
    }

    public WeiXinResult<CloseOrderResData> closeOrder(CloseOrderReqData closeOrderReqData) {
        return new CloseOrderBusiness(httpClientHolder).closeOrder(closeOrderReqData);
    }

    public WeiXinResult<RefundResData> refund(RefundReqData refundReqData) {
        return new RefundBusiness(httpClientHolder).refund(refundReqData);
    }

    public WeiXinResult<RefundQueryResData> refundQuery(RefundQueryReqData refundQueryReqData) {
        return new RefundQueryBusiness(httpClientHolder).refundQuery(refundQueryReqData);
    }

    public WeiXinResult<MicroPayResData> microPay(MicroPayReqData microPayReqData) {
        return new MicroPayBusiness(httpClientHolder).microPay(microPayReqData);
    }

    public WeiXinResult<MicroPayResData> microPaySimple(MicroPayReqData microPayReqData) {
        return new MicroPayBusinessSimple(httpClientHolder).microPay(microPayReqData);
    }

    public WeiXinResult<ReverseResData> reverse(ReverseReqData reverseReqData) {
        return new ReverseBusiness(httpClientHolder).reverse(reverseReqData);
    }

    @Override
    public TransfersResData transfers(TransfersReqData transfersReqData) {
    	return new TransfersBusiness(httpClientHolder).transfers(transfersReqData);
    }

	@Override
	public RedpackResData sendredpack(RedpackReqData redpackReqData) {
		return new RedpackBusiness(httpClientHolder).sendredpack(redpackReqData);
	}
    
}
