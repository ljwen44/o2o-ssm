package com.sgxy.o2o.util;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	
	public static String app_id = "2021000117605569";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
//    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCS26tLv0aMahsruCXIrFGEs+GYWccPVYiGIkHcxVAqs43NoAhwl8rIbBtz1s+3soJn4uvLGXfeUxEqvsDisLsQKtot/l5Ruj8E0thfGyAeoBumsaHYWOE1y/9mwi8Q3PmqDTDKIuQWwKQg+/vwzDro2CyDXvW6RvcacCStLl/zOafaGJaWsNDzabM0zA3J3D1QiGqfLNUyI5cpfWKUATvqvUBwHFaNb2wR1J6OGxicH0Ph09mPTCggy0DPfjAwB6+zhLD0X5IGpdT8/wP0VgjOorLqk01EIttL/RFLixzBXYYS2feOOr/FExOWUrqPgZ3cSDFPc4Y55+LYe4xf4cKHAgMBAAECggEANKqg2M5k75BR5DDrI/dgWLXl7qwrrj0fc/MvKYx9foLcFOUSgcZ+C7hxc+6Sma9kAvunUjP0//9MRccZB3MX8ulbS2lQzAB9/y3aOOlScIk8wWAsyZi+U3uwQVrcZDDLPIf0elle8SXu9oFHzgiAC+cSt55Dg6VVkt6q2IysDl6QYgf6mKTmGgVMQJaKuujowhdA8/w3o2hDPRxAkDvbM9ca2ua42jEF2KUkZZg9v4BqBABbAQaNErxRXidkkBRx+CQaeyZwOqFZA+mAgSxVtEmnuxqORi22MDLYl4ZJYVOxlLAhrZBiBQ9khvieKf/sAC9WJGf8ihe2UnmXFlKfsQKBgQDSVsvK0gAkBdm+we7hVzlVO0+9vqz0zAbOa4Yvw/YCMpWKX0r+ft822Rr+SRcnVNP6LdOaIMCsYhQkfo1hs2kbqCoWzZLPL5rLXmsjytTp8hqpp74MHC/+He7MdStbfMZaK4Xnu9r3CR/bs79Of47V9StSQKPW324jCvKcFkrdXwKBgQCyvQkl/iQoXAx4yl0Lfm/CoqrvLOKFShwO59zXxShvBLEC0rnMM566ERlsN/ASQ1EQ6BKZ4zor/S2NMrTZFGygQlC3VD47cCCIJAYIU0ce64asqHGINXzlrUQDb7oeLs4lRB9UG8Prv3PvrTEFMHuFmcNuguNpUMdc2xjA4qwD2QKBgER6/8Sb1HHl+buSdkylUkQ+mDqljrfwiTLBbSG2Ai06/NbdKZRsCA5aZuBtOX2Tr6QfMWpXyYMWN4qeLTem6syXVoSwD2EnFZWquuUfT2yPZ0DfHQ/iSXnJkwq8Bw61M/r9eo406hhNvzKPdfR/sShVtvIDm5QEkF4WXWCWHW+vAoGAbLRPRg4Ei48//o6EfCMPcX5hG0lfEtV5rw8Q3yGHb82YojDq0uhqFpujrI9wsIrEWNXryi/KB0rZU1CNcKDdV7I9SOIxXxDYmDPgf1u2m3V+2K8xLTfql/0CqlxfA1BQC5Ycv5iLVWuA5Tmd4JwV47EajLGKAVkhhrWxnk29dDECgYEAzbayFtMB6HGerXkN7ZCd/3XC79sqqeBI3L/eTmOZz1Imihjq4RlNgF0bs3RopnTn7e4iDaDA343Slj2ga5y7tjMZH0zDwIKkoHvFJZA+cYvxRYe8Cv1lZM/SXYIBFRo/13rBOJ0qA4c4y4Q3exswfquvswxcax9fBpOux7CLn2U=";
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCu/gNyzwu5j5G8N9Bpku8+VxGQNLpPeCCEC5ehdneiEPeHUDF2QIlWztBNQIfTQwLqD2Sl4oAHZsyDsrovr9YUsyMHjIo9NPK7xJ0SIAtHbbvSEWS3ptsR3GoThv8DqrMi8ZnPdL3ek9/5oib9XqPL3LqAeXeKNtoAAM6A36OKUyJcLRYHLGIU/2viNuHuPIn7/U2GkNFWx8xtz0WDeHkgasUJTGxEj3zkABysJ3XRyJv9fKErJilJE1yTMf5v4L3mtiEuVk7wiuXhzi2OWyveYPaY9qtwEzy6H9RvHVxf1Bh279rOoMlyuhPqLGTBCAVKtPVyGdCBvtPpprbgNGsnAgMBAAECggEAdYaV7FKPzlVVDHLz3yPW2CiD3DT9eAf21pgugztcR5VbmR5Lyd64HnwRvAB9+G4GFAq4jwya7d2lt0Ge+A4BGq/g8934rfjUmTUIRLcOvC8/uGsEGK/Qa6N7/pdoyB6p5usaYRFHv6fTAgZcZ0hbe7xH6acqmMIUEXJ5PmZ0oJ3rq4/AGmWGpZX8EK6RPuk9V+WJf6ewTHB/9gy7g43OLDrsn6tDrVTnCOjoD+uAMut3bfKgH+4aojPRYhUrSqzYo/kDeL41KEFbt7MsFBeShHwkq7ffcJFjZAD7VWiztUc/V/jelF8k/KegirHF/wB+JT0AS9YDNrvQR64dQ7MNGQKBgQD0kx8li7el0Z7PSmiTjJq0wMF4OgCGhjIkXxdsvDLyYcYAFMF9rVLlA1QEvtEHXb21O7f2uvUfQbtWM3sHVgT2jsAIPoNHK5PbUMat75TMf6w5vcUwZnBh9xpn/mRthGNRBJKdBh8+6XPuZ8u5e7B2Q38Vj2lWGRuJtuoYZ8GORQKBgQC3KsCk4GptyNmD2lJzXSInfTUi7r3PYfYvg3lsj3+J6JPuq+AyCarC9xIdlldp6Ix+8+pI1tWiY1eD9rG3g27wNZJ4GlY4mvaWeBKXXz2izvXrQLq1BErEuNFBkpn++VeFg1Wr1316t6MeCJoeD4iVJH6BDyQKOLfQq/GvtHnQewKBgQDAQ+f4XB3C/UcbLiOH2sDD18Q//1jqaBSDAz4m/MwQP0yoQ6jYY6kG39bs5BTu9YJtNsUn8VFBgSsEWPQekEDWkuHjmUEaceeKQbXuhqkzGisKOWUK53zxho/7sMDCV5C74Bxh1K47hsrcYDyHU/UlA2qkSFlGlfZQDLt69BX1AQKBgBdfmkOv64Tk63Ta6EiWauRVIvG5O3l6S7sWcOk8io7jofUD89EO0L6eXq5t3+vA5CTTJWN0HsDhImrRkS4U1JRMkctHuRbyF2NuIRk16xyagmEp2ml9Cbl+yB0xAvu5f8Sm1Mjf78A4LNWJTXlmjAxUItx/PR8cy8ASIP5F7oF/AoGAF6B/cnO3YPP9OBT0AB2LFdT+mXvOxhUj9Ch1bJovItFInYcn8pYVvUbfOhY18kYccaDGlrxbUyQN0rHby8wbBmyKyYMrPgtPwpNDtlMgUXycD2XLb2i0DU/ctehsgHR9wacLV9/rEThM3BcjWfA1wYTHj+r31qHXSpjKRmiKzQk=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtBGMrrKWeFCcHEn86eyFSizQ/19iG45cI7ppyE7q5lGbyKqAlanA9oBKD2lTbWIqsfPLR+KcUfGW1MeB2/V8awPEkjwHNNA+iJCPFdqFlsASRP7FaxlWLHwz1kF3PRpJE3NpUwThbhtx1P9JkW5lJNxL8/tWWD/LvBW51NZryq+C+ZTUMWBweepVYP889N/KQq3Cq69VgYNwLTw5cKtZHbo7lCWQO4eExxD5Fpdrlt+R/VXJ+4eaGD6Gz5ykI7qrBiZeHLHiy9hUi+5POl5/WQKWLnUnOP6HhcqZWSZbRo8QO8mvsWIz4/MlgOwdWkPuyx8XUSIsqIzhfAcYfdpjAwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://xyxtcy.natappfree.cc/o2o/alipayController/alipayNotify";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://xyxtcy.natappfree.cc/o2o/alipayController/alipayNotify";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "E:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

