package com.wsj.wechat.api.business;

import static java.lang.Thread.sleep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wsj.wx.api.constant.WeiXinPayUrl;
import com.wsj.wx.api.http.HttpClientHolder;
import com.wsj.wx.api.http.HttpSend;
import com.wsj.wx.api.model.common.WeiXinResult;
import com.wsj.wx.api.model.micropay.MicroPayReqData;
import com.wsj.wx.api.model.micropay.MicroPayResData;
import com.wsj.wx.api.model.query.OrderQueryReqData;
import com.wsj.wx.api.model.query.OrderQueryResData;
import com.wsj.wx.api.model.reverse.ReverseReqData;
import com.wsj.wx.api.model.reverse.ReverseResData;
import com.wsj.wx.api.util.XmlUtil;

/**
 * 刷卡支付业务
 */
public class MicroPayBusiness extends BaseBusiness {

    private static Logger logger = LoggerFactory.getLogger(MicroPayBusiness.class);

    public MicroPayBusiness(HttpClientHolder httpClientHolder) {
        super(httpClientHolder);
    }

    public WeiXinResult<MicroPayResData> microPay(MicroPayReqData microPayReqData) {
        initCommonData(microPayReqData);
        String result = HttpSend.sendRequest(WeiXinPayUrl.MICRO_PAY_API, microPayReqData, httpClientHolder);
        MicroPayResData microPayResData = XmlUtil.getObjectFromXML(result, MicroPayResData.class);
        WeiXinResult<MicroPayResData> resData = dealResult(microPayResData, result);
        if (resData.isSuccess() || resData.isError()) {
            return resData;
        }
        String errorCode = microPayResData.getErr_code();//获取错误码
        String errorCodeDes = microPayResData.getErr_code_des();//获取错误描述
        logger.error("业务返回失败,err_code:{},err_code_des:{}", errorCode, errorCodeDes);
        //业务错误时错误码有好几种，商户重点提示以下几种
        if (errorCode.equals("AUTHCODEEXPIRE") || errorCode.equals("AUTH_CODE_INVALID") || errorCode.equals("NOTENOUGH")) {
            //2扣款明确失败
            //对于扣款明确失败的情况直接走撤销逻辑
            doReverseLoop(microPayReqData.getOut_trade_no());
            //以下几种情况建议明确提示用户，指导接下来的工作
            if (errorCode.equals("AUTHCODEEXPIRE")) {
                //表示用户用来支付的二维码已经过期，提示收银员重新扫一下用户微信“刷卡”里面的二维码
                logger.error("【支付扣款明确失败】原因是：" + errorCodeDes);
            } else if (errorCode.equals("AUTH_CODE_INVALID")) {
                //授权码无效，提示用户刷新一维码/二维码，之后重新扫码支付
                logger.error("【支付扣款明确失败】原因是：" + errorCodeDes);
            } else if (errorCode.equals("NOTENOUGH")) {
                //提示用户余额不足，换其他卡支付或是用现金支付
                logger.error("【支付扣款明确失败】原因是：" + errorCodeDes);
            }
            return resData;
        } else if (errorCode.equals("USERPAYING")) {
            //需要输入密码
            //表示有可能单次消费超过300元，或是免输密码消费次数已经超过当天的最大限制，这个时候提示用户输入密码，商户自己隔一段时间去查单，查询一定次数，看用户是否已经输入了密码
            if (doPayQueryLoop(3, microPayReqData.getOut_trade_no())) {
                logger.info("【需要用户输入密码、查询到支付成功】");
            } else {
                logger.info("【需要用户输入密码、在一定时间内没有查询到支付成功、走撤销流程】");
                doReverseLoop(microPayReqData.getOut_trade_no());
            }
        } else {
            //4)扣款未知失败
            if (doPayQueryLoop(3, microPayReqData.getOut_trade_no())) {
                logger.info("【支付扣款未知失败、查询到支付成功】");
            } else {
                logger.info("【支付扣款未知失败、在一定时间内没有查询到支付成功、走撤销流程】");
                doReverseLoop(microPayReqData.getOut_trade_no());
            }
        }
        return null;
    }

    private int waitingTimeBeforePayQueryServiceInvoked = 5000;

    /**
     * 由于有的时候是因为服务延时，所以需要商户每隔一段时间（建议5秒）后再进行查询操作，多试几次（建议3次）
     *
     * @param loopCount  循环次数，至少一次
     * @param outTradeNo 商户系统内部的订单号,32个字符内可包含字母, [确保在商户系统唯一]
     * @return 该订单是否支付成功
     * @throws InterruptedException
     */
    private boolean doPayQueryLoop(int loopCount, String outTradeNo) {
        //至少查询一次
        if (loopCount == 0) {
            loopCount = 1;
        }
        //进行循环查询
        for (int i = 0; i < loopCount; i++) {
            if (doOnePayQuery(outTradeNo)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 进行一次支付订单查询操作
     *
     * @param outTradeNo 商户系统内部的订单号,32个字符内可包含字母, [确保在商户系统唯一]
     * @return 该订单是否支付成功
     * @throws Exception
     */
    private boolean doOnePayQuery(String outTradeNo) {
        try {
            sleep(waitingTimeBeforePayQueryServiceInvoked);//等待一定时间再进行查询，避免状态还没来得及被更新
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        OrderQueryReqData orderQueryReqData = new OrderQueryReqData(outTradeNo);
        initCommonData(orderQueryReqData);
        WeiXinResult<OrderQueryResData> orderQueryResData = new OrderQueryBusinss(httpClientHolder).orderQuery(orderQueryReqData);
        if (orderQueryResData.isSuccess()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 由于有的时候是因为服务延时，所以需要商户每隔一段时间（建议5秒）后再进行查询操作，是否需要继续循环调用撤销API由撤销API回包里面的recall字段决定。
     *
     * @param outTradeNo 商户系统内部的订单号,32个字符内可包含字母, [确保在商户系统唯一]
     * @throws InterruptedException
     */
    private void doReverseLoop(String outTradeNo) {
        //初始化这个标记
        boolean needRecallReverse = true;
        //进行循环撤销，直到撤销成功，或是API返回recall字段为"Y"
        while (needRecallReverse) {
            if (doOneReverse(outTradeNo)) {
                break;
            }
        }
    }

    //每次调用撤销API的等待时间
    private int waitingTimeBeforeReverseServiceInvoked = 5000;

    /**
     * 进行一次撤销操作
     *
     * @param outTradeNo 商户系统内部的订单号,32个字符内可包含字母, [确保在商户系统唯一]
     * @return 该订单是否支付成功
     * @throws Exception
     */
    private boolean doOneReverse(String outTradeNo) {
        try {
            sleep(waitingTimeBeforeReverseServiceInvoked);//等待一定时间再进行查询，避免状态还没来得及被更新
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ReverseReqData reverseReqData = new ReverseReqData(outTradeNo);
        initCommonData(reverseReqData);
        WeiXinResult<ReverseResData> reverseResData = new ReverseBusiness(httpClientHolder).reverse(reverseReqData);
        if (reverseResData.getInfobean().getRecall().equals("Y")) {
            //表示需要重试
            return false;
        } else {
            //表示不需要重试，也可以当作是撤销成功
            return true;
        }
    }

}