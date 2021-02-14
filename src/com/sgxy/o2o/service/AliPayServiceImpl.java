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
        //ǩ����֤(��֧�������ص�������֤��ȷ����֧�������ص�)
        boolean signVerified = false;
        try {
            //����SDK��֤ǩ��
	        String alipayPublicKey = AlipayConfig.alipay_public_key;
	        String charset = AlipayConfig.charset;
	        String signType = AlipayConfig.sign_type;
 
            signVerified = AlipaySignature.rsaCheckV1(conversionParams, alipayPublicKey, charset, signType);
            //����ǩ���д���.
            if (signVerified) {
                //��ǩͨ�� ��ȡ����״̬
                String tradeStatus = conversionParams.get("trade_status");
 
                //ֻ����֧���ɹ��Ķ���: �޸Ľ��ױ�״̬,֧���ɹ�
                //ֻ�н���֪ͨ״̬ΪTRADE_SUCCESS��TRADE_FINISHEDʱ��֧�����Ż��϶�Ϊ��Ҹ���ɹ���
                if (tradeStatus.equals("TRADE_SUCCESS") ||tradeStatus.equals("TRADE_FINISHED")) {
                    return true;
                } else {
                    return false;
                }
            } else{  //��ǩ��ͨ��
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
