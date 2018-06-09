package util;

import java.net.SocketTimeoutException;

import org.apache.http.conn.ConnectTimeoutException;

import json.AccessToken;
import model.WebAuthAccessToken;
import model.WeixinFinalValue;

public class WeixinUtil {

	public static  String getOpenId( String code) {
		String url=WeixinFinalValue.AUTO_GET_OID;
		url=url.replace("APPID", WeixinFinalValue.APPID)
				.replace("SECRET",WeixinFinalValue.APPSECRET)
				.replace("CODE",code );
	
		String msg=null;
		try {
			msg=HttpClientUtils.get(url);
			
			
		} catch (ConnectTimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketTimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msg;
	}
}
