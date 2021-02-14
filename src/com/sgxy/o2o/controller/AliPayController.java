package com.sgxy.o2o.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.google.gson.Gson;
import com.sgxy.o2o.basic.controller.BasicController;
import com.sgxy.o2o.dto.AlipayVo;
import com.sgxy.o2o.service.AlipayService;
import com.sgxy.o2o.service.UserService;
import com.sgxy.o2o.util.AlipayConfig;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/alipayController")
public class AliPayController extends BasicController{
	@Autowired
	@Qualifier("alipayService")
	public AlipayService alipayService;
	@Autowired
	@Qualifier("userService")
	public UserService userService;
	
	// ��Ŷ����ŵ�֧��״̬����Ϊ�����ţ�ֵΪ����
	public static Map<String, Boolean> payMap = new ConcurrentHashMap<String, Boolean>(); 
	// ��Ŷ����Ŷ�Ӧ���û�id����Ϊ�����ţ�ֵΪ�û�id
	public static Map<String , String> uidMap = new HashMap<String, String>();
	// ��Ŷ����Ŷ�Ӧ�ĳ�ֵ����Ϊ�����ţ�ֵΪ��ֵ���
	public static Map<String, String> numMap = new HashMap<String, String>();
	
	// ��ȡ��ά��
	@ModelAttribute
	@RequestMapping(value = "/QRcode", method = RequestMethod.POST)
	public void getQrcode(HttpServletRequest req, HttpServletResponse res,
			String paynum, String uid) throws AlipayApiException {
		JSONObject json = new JSONObject();
		json.put("message", "");
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", "utf-8", AlipayConfig.alipay_public_key, "RSA2");
        //�����������
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
        //�̻������ţ��̻���վ����ϵͳ��Ψһ�����ţ�����
  		String out_trade_no = this.getUUID();
  		//���������
  		String total_amount = paynum;
  		//�������ƣ�����
  		String subject = "ѧ�ҳ�ֵ";
        model.setOutTradeNo(out_trade_no);
        model.setTotalAmount(total_amount);
        model.setSubject(subject);
//        model.setQrCodeTimeoutExpress("10m");
        request.setBizModel(model);
        //֧�����첽֪ͨ��ַ
        request.setNotifyUrl(AlipayConfig.notify_url);
        request.setReturnUrl(AlipayConfig.return_url);
        //���ýӿ�
        AlipayTradePrecreateResponse response = alipayClient.execute(request);
 
        // ��ȡ��ά��ʧ�ܣ����ش�����
        if (!response.isSuccess()) {
            json.put("message", "��ȡ��ά��ʧ�ܣ����Ժ�����!");
        } else {
        	payMap.put(out_trade_no, false);
        	numMap.put(out_trade_no, total_amount);
        	uidMap.put(out_trade_no, uid);
        	json.put("QRcode", response.getQrCode());
        	json.put("out_trade_no", out_trade_no);
        }
        this.writeJson(json.toString(), res);
	}
	
	// ��ȡ��ά�����ʵ��ҳ����ת
//	@ModelAttribute
//	@RequestMapping(value = "/getQRcode", method = RequestMethod.POST)
//	public void generateQRcode (HttpServletRequest req, HttpServletResponse res,
//	        String paynum, String uid) throws AlipayApiException{
//		JSONObject json = new JSONObject();
//		json.put("message", "");
//        //ʵ�����ͻ���
//        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", "utf-8", AlipayConfig.alipay_public_key, "RSA2");
//        //�����������
//  		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
//  		alipayRequest.setReturnUrl(AlipayConfig.return_url);
//  		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
//  		
//  		//�̻������ţ��̻���վ����ϵͳ��Ψһ�����ţ�����
//  		String out_trade_no = this.getUUID();
//  		//���������
//  		String total_amount = paynum;
//  		//�������ƣ�����
//  		String subject = "���Զ�������";
//  		//��Ʒ�������ɿ�
//  		String body = "������Ʒ����";
//  		
//  		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
//  				+ "\"total_amount\":\""+ total_amount +"\"," 
//  				+ "\"subject\":\""+ subject +"\"," 
//  				+ "\"body\":\""+ body +"\"," 
//  				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
//  		String result = alipayClient.pageExecute(alipayRequest).getBody();
//  		uidMap.put(out_trade_no, uid);
//        json.put("result", result);
//        this.writeJson(json.toString(), res);
//	}
	
	// ֧���ɹ���Ļص������������洢����
	@ModelAttribute
	@RequestMapping(value = "/alipayNotify", method = RequestMethod.POST)
	@ResponseBody
	public void alipayNotify (HttpServletRequest request, HttpServletResponse response) throws AlipayApiException, IOException{
		System.err.println("=֧�����첽����֧�������ʼ");
        //1.��֧�����ص���request����ȡֵ
        //��ȡ֧�������صĲ�������
        @SuppressWarnings("unchecked")
		Map<String, String[]> aliParams = request.getParameterMap();
        //���Դ��ת����Ĳ�������
        Map<String, String> conversionParams = new HashMap<String, String>();
        for (Iterator<String> iter = aliParams.keySet().iterator(); iter.hasNext();) {
            String key = iter.next();
            String[] values = aliParams.get(key);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // ����������δ����ڳ�������ʱʹ�á����mysign��sign�����Ҳ����ʹ����δ���ת��
//            try {
//				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
            conversionParams.put(key, valueStr);
        }
        System.err.println("==���ز������ϣ�" + conversionParams);
        // ��ǩ
        boolean signVerified = AlipaySignature.rsaCheckV1(conversionParams, 
        		AlipayConfig.alipay_public_key, "UTF-8",AlipayConfig.sign_type);
        if(signVerified){
            // �̻�������
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            
            // ֧�������׺�
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            
            // ������
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
            

            System.err.println("�̻�������="+out_trade_no);
            System.err.println("֧�������׺�="+trade_no);
            System.err.println("������="+total_amount);
            
            
            int totalamount = Integer.parseInt(total_amount.split("\\.")[0]);
            String uid = uidMap.get(out_trade_no);
            // ��ֵ��¼
            String apid = this.getUUID();
        	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		String time = df.format(new Date());
    		int addAlipay = alipayService.addAlipay(apid, uid, time, total_amount, 1);
        	// ��ֵ�û����ѧ��
        	int addStatus = userService.addUserCoin(uid, totalamount);
        	
        	System.err.println(addStatus + "   " + addAlipay);
            
            payMap.put(out_trade_no, true);
        } else {
        	System.err.println("��ǩʧ��!");
        }
	}

	// ȷ��֧��
	@ModelAttribute
	@RequestMapping(value = "/confirmPay", method = RequestMethod.POST)
	public void confirmPay(HttpServletRequest request, HttpServletResponse response,
			String apid) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		
		/**
		 * ���ȷ�ϲ�������ȡpayMap��״̬���ɹ��򷵻�true��ʧ�ܷ��ش�����Ϣ
		 * **/
		boolean status = payMap.get(apid);
		if(!status) {
			json.put("message", "֧��δ���");
		} else {
			json.put("coin", numMap.get(apid));
		}
		
		this.writeJson(json.toString(), response);
	}
	
	
	/**
	  * ֧�������û�ת��
	  * @param bizNo  �߼�����
	  * @param amount ת�˽�� "1.21"��λԪ
	  * @param account ֧�����˺�
	  * @param userName ֧������ʵ����
	  * @return
	  */
	@ModelAttribute
	@RequestMapping(value = "/drawal", method = RequestMethod.POST)
	public void alipay2User(HttpServletRequest req, HttpServletResponse res,
			String amount,String account,String userName, String uid){
		JSONObject jsonRes = new JSONObject();
		jsonRes.put("message", "");
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, 
				AlipayConfig.merchant_private_key, "json", "utf-8", 
				AlipayConfig.alipay_public_key, "RSA2");
		
		String bizNo = this.getUUID();
		AlipayVo vo = new AlipayVo();
		vo.setOut_biz_no(bizNo);
		vo.setPayee_type("ALIPAY_LOGONID");
		vo.setAmount(amount);
		vo.setPayee_account(account);
		vo.setPayer_show_name(userName);
		vo.setPayee_real_name(userName);
		vo.setRemark("֧����ת��");
		String json = new Gson().toJson(vo);
		// �����������
		AlipayFundTransToaccountTransferRequest alipayRequest = new AlipayFundTransToaccountTransferRequest();
		alipayRequest.setBizContent(json);
		AlipayFundTransToaccountTransferResponse response=null;
		try {
			response = alipayClient.execute(alipayRequest);
			System.err.println(JSON.toJSONString(response));
			if("10000".equals(response.getCode())){
				Integer coin = Integer.parseInt(amount);
				int delStatus = userService.delUserCoin(uid, coin);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    		String time = df.format(new Date());
				int addStatus = alipayService.addAlipay(bizNo, uid, time, amount, 0);
				System.err.println("ѧ�Ҽ���״̬:"+ delStatus + ", ��¼���״̬:" + addStatus);
				jsonRes.put("success", "ת�˳ɹ�!");
				jsonRes.put("coin", coin);
			} else {
				jsonRes.put("message", response.getSubMsg());
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
			jsonRes.put("message", "ת��ʧ��!");
		}
		this.writeJson(jsonRes.toString(), res);
	}
}	
