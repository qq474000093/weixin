package timetask;


import java.io.IOException;
import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.youzan.open.sdk.util.json.JsonUtils;

import json.AccessToken;
import json.ErrorEntity;
import model.WeixinContext;
import model.WeixinFinalValue;
import util.JsonUtil;

@Component
public class RefreshAccrssTokenTask extends  TimerTask {
	public void refreshToken() {
		HttpGet get =null;
		CloseableHttpResponse resp=null;
		CloseableHttpClient client=null;
		
	   try {
			 client=HttpClients.createDefault();
			String url=WeixinFinalValue.ACCESS_TOKEN_URL;
			url=url.replaceAll("APPID", WeixinFinalValue.APPID);
			url=url.replaceAll("APPSECRET", WeixinFinalValue.APPSECRET);
			get=new HttpGet(url);
		    resp=client.execute(get);
			int statusCode=resp.getStatusLine().getStatusCode();
			
			if(statusCode>=200&&statusCode<300) {
				HttpEntity entity=resp.getEntity();
				String content =EntityUtils.toString(entity);
				try {
					AccessToken at=(AccessToken)JsonUtil.string2Obj(content, AccessToken.class);
					WeixinContext.setAccessToken(at.getAccess_token());
				} catch (Exception e) {
				    ErrorEntity err=JSON.parseObject(content, ErrorEntity.class);
				    System.out.println(err.getErrcode());
				    refreshToken();
				}
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(resp!=null)
				try {
					resp.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(client!=null)
				try {
					resp.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	@Override
	public void run() {
		refreshToken();
		
	}

}
