package service;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import model.WeixinFinalValue;
import util.HttpClientUtils;

@Service
public class WUserService {
	
	public void queryByopenid(String openid) {
		
	}
	
	public String queryOpenidBuCode(String code) {
		String url=WeixinFinalValue.AUTO_GET_OID;
		 url=url.replace("APPID",WeixinFinalValue.APPID )
		.replace("SECRET", WeixinFinalValue.APPSECRET)
		.replace("CODE", code);
		try {
			String json=HttpClientUtils.get(url);
			String openid=JSON.parseObject(json).get("openid").toString();
			return openid;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
	}

}
