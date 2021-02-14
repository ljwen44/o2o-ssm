package com.sgxy.o2o.util;

import java.util.HashMap;
import java.util.Set;

import org.junit.Test;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;

public class Phone {
	
	public int sendCode(String phone, String code) {
		int flag = 1; // 发送标志
		//生产环境请求地址：app.cloopen.com
        String serverIp = "app.cloopen.com";
        //请求端口
        String serverPort = "8883";
        //主账号,登陆云通讯网站后,可在控制台首页看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN
        String accountSId = "8aaf0708762cb1cf01764529fdae08ae";
        String accountToken = "e765a45a1ef94baf8f5ae41f51ee18c1";
        //请使用管理控制台中已创建应用的APPID
        String appId = "8aaf0708762cb1cf01764529fe8508b5";
        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init(serverIp, serverPort);
        sdk.setAccount(accountSId, accountToken);
        sdk.setAppId(appId);
        sdk.setBodyType(BodyType.Type_JSON);
        //发送短信至手机号
        String to = phone;
        //短信模板
        String templateId= "1";
        //自己传入验证码，5分钟内到期
        String[] datas = {code,"5"};
        HashMap<String, Object> result = sdk.sendTemplateSMS(to,templateId,datas);
        if("000000".equals(result.get("statusCode"))){
        	System.err.println(phone + "::" + code);
            //正常返回输出data包体信息（map）
            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for(String key:keySet){
                Object object = data.get(key);
                System.out.println(key +" = "+object);
            }
        }else{
            //异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
            flag = 0;
        }
        return flag;
	}
}
