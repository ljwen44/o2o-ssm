package com.sgxy.o2o.basic.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import java.util.regex.Pattern;


@Controller
public class BasicController {
	private Logger log = Logger.getLogger(BasicController.class); 
	/**
	 * JSON��ʽ��Ӧ����
	 * @param json json��ʽ�ַ���
	 * @param response ��Ӧ��Ϣ
	 */
	public void writeJson(String json,HttpServletResponse response){
		PrintWriter out =null;
		try {
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
			out.print(json);
			//out.write();
			out.flush();
			out.close();
		} catch (Exception e) {
			if(out!=null){
				out.close();
			}
			e.printStackTrace();
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw, true));
		}
	}
	/**
	 * �ж�ҳ���Ƿ����
	 * @param request �ͻ�������
	 * @return boolean true��ҳ����� ,false��ҳ��û�й���
	 */
	public boolean sessionTimeout(HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session.getAttribute("id")==null || ((String)session.getAttribute("id")).equals("")){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * ����UUID
	 * @return UUID
	 */
	public String getUUID(){
		String uuid = UUID.randomUUID().toString();   
		uuid = uuid.replace("-", "");
		return uuid;  
	}
	/**
	 * ��ȡ��ǰ����ʱ��
	 * @return ��ǰ����ʱ��
	 * @throws ParseException 
	 */
	public Date getDate() {
		SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date changedate=null;
		try {
			changedate = df.parse(df.format(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			log.error("��ȡ��ǰ����ʱ�����", e);
		}
		return changedate;
	}
	
	public String removeHtmlTag(String inputString) {
		if (inputString == null)
		return null;
		String htmlStr = inputString; // ��html��ǩ���ַ���
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		java.util.regex.Pattern p_special;
		java.util.regex.Matcher m_special;
		try {
		//����script��������ʽ{��<script[^>]*?>[\\s\\S]*?<\\/script>
		String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
		//����style��������ʽ{��<style[^>]*?>[\\s\\S]*?<\\/style>
		String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
		// ����HTML��ǩ��������ʽ
		String regEx_html = "<[^>]+>";
		// ����һЩ�����ַ���������ʽ �磺&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		String regEx_special = "\\&[a-zA-Z]{1,10};";

		p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // ����script��ǩ
		p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // ����style��ǩ
		p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // ����html��ǩ
		p_special = Pattern.compile(regEx_special, Pattern.CASE_INSENSITIVE);
		m_special = p_special.matcher(htmlStr);
		htmlStr = m_special.replaceAll(""); // ���������ǩ
		
		p_special = Pattern.compile("\\s*|\t|\r|\n");
		m_special = p_special.matcher(htmlStr);
		htmlStr = m_special.replaceAll("");
		
		textStr = htmlStr;
		
		} catch (Exception e) {
		e.printStackTrace();
		}
		return textStr;// �����ı��ַ���
		}

	
	public  String getStringDate(Date date) {
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String dateString = formatter.format(date);
		  return dateString;
	} 
	/**
	 * ��ȡmd5��
	 * @param str
	 * @return
	 */
	public String md5(String str) {
		if(str==null){
			return "";
		}
		// ��ȡժҪ����
		MessageDigest m = null;
		try {
			// MD5ժҪ����
			m = MessageDigest.getInstance("MD5");
			// ���±��ĸ�������λԪ��
			m.update(str.getBytes("UTF8"));
			// ����֧��ժҪ�쳣
		} catch (NoSuchAlgorithmException e) {
			// ����һ��MD5��Ϣ�ĸ� ��ʱ�����
			e.printStackTrace();
			// ����֧���ַ����쳣
		} catch (UnsupportedEncodingException e) {
			// ���±��ĸ�������λԪ�� ��ʱ�����
			e.printStackTrace();
		}
		// ������ʹ��λԪ��ı�����������,Ȼ�������ժ����
		byte s[] = m.digest();
		// System.out.println(s); // ������ܺ��λԪ��
		// ��������ַ�������
		StringBuilder result = new StringBuilder("");
		// ������ժ
		for (int i = 0; i < s.length; i++) {
			// ����ʮ������ת��
			result.append(Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6));
		}
		// ���ؼ��ܽ��
		return result.toString();

	}
}
