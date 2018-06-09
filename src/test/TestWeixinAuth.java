package test;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.alibaba.fastjson.JSON;
import com.aliyuncs.http.HttpRequest;

import dao.impl.EventPushDao;
import model.EventPush;
import model.WeixinFinalValue;
import org.junit.Test;


public class TestWeixinAuth {
	
	@Test
	public void testAuth() throws Exception {
		EventPushDao nep=new EventPushDao();
		EventPush ep=nep.GetcodeByOpenid("optwd1dL9WxSkAQtuhZGOpOqIq4k", "yong");
		System.out.println(ep.getOuterStr()+"-----"+ep.getCardId());

	    
	   
	}
	@Test
	public void aa(){
		System.out.println(11);
	}
	
}
