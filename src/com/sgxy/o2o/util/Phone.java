package com.sgxy.o2o.util;

import java.util.HashMap;
import java.util.Set;

import org.junit.Test;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;

public class Phone {
	
	public int sendCode(String phone, String code) {
		int flag = 1; // ���ͱ�־
		//�������������ַ��app.cloopen.com
        String serverIp = "app.cloopen.com";
        //����˿�
        String serverPort = "8883";
        //���˺�,��½��ͨѶ��վ��,���ڿ���̨��ҳ�������������˺�ACCOUNT SID�����˺�����AUTH TOKEN
        String accountSId = "8aaf0708762cb1cf01764529fdae08ae";
        String accountToken = "e765a45a1ef94baf8f5ae41f51ee18c1";
        //��ʹ�ù������̨���Ѵ���Ӧ�õ�APPID
        String appId = "8aaf0708762cb1cf01764529fe8508b5";
        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init(serverIp, serverPort);
        sdk.setAccount(accountSId, accountToken);
        sdk.setAppId(appId);
        sdk.setBodyType(BodyType.Type_JSON);
        //���Ͷ������ֻ���
        String to = phone;
        //����ģ��
        String templateId= "1";
        //�Լ�������֤�룬5�����ڵ���
        String[] datas = {code,"5"};
        HashMap<String, Object> result = sdk.sendTemplateSMS(to,templateId,datas);
        if("000000".equals(result.get("statusCode"))){
        	System.err.println(phone + "::" + code);
            //�����������data������Ϣ��map��
            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for(String key:keySet){
                Object object = data.get(key);
                System.out.println(key +" = "+object);
            }
        }else{
            //�쳣�������������ʹ�����Ϣ
            System.out.println("������=" + result.get("statusCode") +" ������Ϣ= "+result.get("statusMsg"));
            flag = 0;
        }
        return flag;
	}
}
