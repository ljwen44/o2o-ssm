package com.sgxy.o2o.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.sgxy.o2o.mapper.AliPayMapper;
import com.sgxy.o2o.util.AlipayConfig;

@Service("alipayService")
public class AliPayServiceImpl implements AlipayService{
	@Autowired
	public AliPayMapper alipayMapper;
	
	@Override
	public Boolean aliNotify(Map<String, String> conversionParams) {
        //签名验证(对支付宝返回的数据验证，确定是支付宝返回的)
        boolean signVerified = false;
        try {
            //调用SDK验证签名
	        String alipayPublicKey = AlipayConfig.alipay_public_key;
	        String charset = AlipayConfig.charset;
	        String signType = AlipayConfig.sign_type;
 
            signVerified = AlipaySignature.rsaCheckV1(conversionParams, alipayPublicKey, charset, signType);
            //对验签进行处理.
            if (signVerified) {
                //验签通过 获取交易状态
                String tradeStatus = conversionParams.get("trade_status");
 
                //只处理支付成功的订单: 修改交易表状态,支付成功
                //只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
                if (tradeStatus.equals("TRADE_SUCCESS") ||tradeStatus.equals("TRADE_FINISHED")) {
                    return true;
                } else {
                    return false;
                }
            } else{  //验签不通过
                return false;
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return false;
	}

	
	@Override
	public int addAlipay(String apid, String uid, String time, String paynum, Integer type) {
		// TODO Auto-generated method stub
		return alipayMapper.addAlipay(apid, uid, time, paynum, type);
	}
}
