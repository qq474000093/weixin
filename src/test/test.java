package test;










import java.io.IOException;
import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import dao.impl.EventPushDao;
import json.AccessToken;
import model.WeixinFinalValue;
import util.JsonUtil;


public class test  {
	
	@Test
public  void add() {
		  try {
			  CloseableHttpClient client=HttpClients.createDefault();
				String url=WeixinFinalValue.ACCESS_TOKEN_URL;
				url=url.replaceAll("APPID", WeixinFinalValue.APPID);
				url=url.replaceAll("APPSECRET", WeixinFinalValue.APPSECRET);
				HttpGet get=new HttpGet(url);
				CloseableHttpResponse resp=client.execute(get);
				int statusCode=resp.getStatusLine().getStatusCode();
				
				if(statusCode>=200&&statusCode<300) {
					HttpEntity entity=resp.getEntity();
					String content =EntityUtils.toString(entity);
					AccessToken at=JSON.parseObject(content, AccessToken.class);
					System.out.println(at.getAccess_token());
				}
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
		
		
	
}
