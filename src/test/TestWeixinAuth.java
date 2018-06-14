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
import model.ErpFinalValue;
import model.EventPush;
import model.WeixinFinalValue;
import org.junit.Test;
import util.HttpClientUtils;


public class TestWeixinAuth {
	
	@Test
	public void testAuth() throws Exception {
		EventPushDao nep=new EventPushDao();
		EventPush ep=nep.GetcodeByOpenid("optwd1dL9WxSkAQtuhZGOpOqIq4k", "yong");
		System.out.println(ep.getOuterStr()+"-----"+ep.getCardId());

	    
	   
	}
	@Test
	public void aa(){

		String url="http://192.168.100.117:8080/erpget?token=EDF5634FFG78543";
		String msg= util.HttpRequest.sendPost(url,"");
		System.out.println(msg);

	}
	@Test
	public void array(){

		int[] i = {5,2,3,4,1,6,7,8,9};
		for(int b=0;b<9;b++)
		{
			if(i[b]>3) {
				System.out.print(i[b]);
			}
		}
	}
	
}
