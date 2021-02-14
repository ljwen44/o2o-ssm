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
	
	// 存放订单号的支付状态，键为订单号，值为布尔
	public static Map<String, Boolean> payMap = new ConcurrentHashMap<String, Boolean>(); 
	// 存放订单号对应的用户id，键为订单号，值为用户id
	public static Map<String , String> uidMap = new HashMap<String, String>();
	// 存放订单号对应的充值金额，键为订单号，值为充值金额
	public static Map<String, String> numMap = new HashMap<String, String>();
	
	// 获取二维码
	@ModelAttribute
	@RequestMapping(value = "/QRcode", method = RequestMethod.POST)
	public void getQrcode(HttpServletRequest req, HttpServletResponse res,
			String paynum, String uid) throws AlipayApiException {
		JSONObject json = new JSONObject();
		json.put("message", "");
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", "utf-8", AlipayConfig.alipay_public_key, "RSA2");
        //设置请求参数
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
        //商户订单号，商户网站订单系统中唯一订单号，必填
  		String out_trade_no = this.getUUID();
  		//付款金额，必填
  		String total_amount = paynum;
  		//订单名称，必填
  		String subject = "学币充值";
        model.setOutTradeNo(out_trade_no);
        model.setTotalAmount(total_amount);
        model.setSubject(subject);
//        model.setQrCodeTimeoutExpress("10m");
        request.setBizModel(model);
        //支付宝异步通知地址
        request.setNotifyUrl(AlipayConfig.notify_url);
        request.setReturnUrl(AlipayConfig.return_url);
        //调用接口
        AlipayTradePrecreateResponse response = alipayClient.execute(request);
 
        // 获取二维码失败，返回错误码
        if (!response.isSuccess()) {
            json.put("message", "获取二维码失败，请稍后重试!");
        } else {
        	payMap.put(out_trade_no, false);
        	numMap.put(out_trade_no, total_amount);
        	uidMap.put(out_trade_no, uid);
        	json.put("QRcode", response.getQrCode());
        	json.put("out_trade_no", out_trade_no);
        }
        this.writeJson(json.toString(), res);
	}
	
	// 获取二维码表单，实现页面跳转
//	@ModelAttribute
//	@RequestMapping(value = "/getQRcode", method = RequestMethod.POST)
//	public void generateQRcode (HttpServletRequest req, HttpServletResponse res,
//	        String paynum, String uid) throws AlipayApiException{
//		JSONObject json = new JSONObject();
//		json.put("message", "");
//        //实例化客户端
//        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", "utf-8", AlipayConfig.alipay_public_key, "RSA2");
//        //设置请求参数
//  		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
//  		alipayRequest.setReturnUrl(AlipayConfig.return_url);
//  		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
//  		
//  		//商户订单号，商户网站订单系统中唯一订单号，必填
//  		String out_trade_no = this.getUUID();
//  		//付款金额，必填
//  		String total_amount = paynum;
//  		//订单名称，必填
//  		String subject = "测试订单名称";
//  		//商品描述，可空
//  		String body = "测试商品描述";
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
	
	// 支付成功后的回调函数，用来存储数据
	@ModelAttribute
	@RequestMapping(value = "/alipayNotify", method = RequestMethod.POST)
	@ResponseBody
	public void alipayNotify (HttpServletRequest request, HttpServletResponse response) throws AlipayApiException, IOException{
		System.err.println("=支付宝异步返回支付结果开始");
        //1.从支付宝回调的request域中取值
        //获取支付宝返回的参数集合
        @SuppressWarnings("unchecked")
		Map<String, String[]> aliParams = request.getParameterMap();
        //用以存放转化后的参数集合
        Map<String, String> conversionParams = new HashMap<String, String>();
        for (Iterator<String> iter = aliParams.keySet().iterator(); iter.hasNext();) {
            String key = iter.next();
            String[] values = aliParams.get(key);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//            try {
//				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
            conversionParams.put(key, valueStr);
        }
        System.err.println("==返回参数集合：" + conversionParams);
        // 验签
        boolean signVerified = AlipaySignature.rsaCheckV1(conversionParams, 
        		AlipayConfig.alipay_public_key, "UTF-8",AlipayConfig.sign_type);
        if(signVerified){
            // 商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            
            // 支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            
            // 付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
            

            System.err.println("商户订单号="+out_trade_no);
            System.err.println("支付宝交易号="+trade_no);
            System.err.println("付款金额="+total_amount);
            
            
            int totalamount = Integer.parseInt(total_amount.split("\\.")[0]);
            String uid = uidMap.get(out_trade_no);
            // 充值记录
            String apid = this.getUUID();
        	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		String time = df.format(new Date());
    		int addAlipay = alipayService.addAlipay(apid, uid, time, total_amount, 1);
        	// 充值用户添加学币
        	int addStatus = userService.addUserCoin(uid, totalamount);
        	
        	System.err.println(addStatus + "   " + addAlipay);
            
            payMap.put(out_trade_no, true);
        } else {
        	System.err.println("验签失败!");
        }
	}

	// 确认支付
	@ModelAttribute
	@RequestMapping(value = "/confirmPay", method = RequestMethod.POST)
	public void confirmPay(HttpServletRequest request, HttpServletResponse response,
			String apid) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		
		/**
		 * 完成确认操作，读取payMap的状态，成功则返回true，失败返回错误信息
		 * **/
		boolean status = payMap.get(apid);
		if(!status) {
			json.put("message", "支付未完成");
		} else {
			json.put("coin", numMap.get(apid));
		}
		
		this.writeJson(json.toString(), response);
	}
	
	
	/**
	  * 支付宝向用户转账
	  * @param bizNo  逻辑单号
	  * @param amount 转账金额 "1.21"单位元
	  * @param account 支付宝账号
	  * @param userName 支付宝真实姓名
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
		vo.setRemark("支付宝转账");
		String json = new Gson().toJson(vo);
		// 设置请求参数
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
				System.err.println("学币减少状态:"+ delStatus + ", 记录添加状态:" + addStatus);
				jsonRes.put("success", "转账成功!");
				jsonRes.put("coin", coin);
			} else {
				jsonRes.put("message", response.getSubMsg());
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
			jsonRes.put("message", "转账失败!");
		}
		this.writeJson(jsonRes.toString(), res);
	}
}	
