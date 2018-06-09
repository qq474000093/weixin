package model;

public class WeixinContext {
	private static String accessToken;

	public static String getAccessToken() {
		return accessToken;
	}

	public static void setAccessToken(String accessToken) {
		WeixinContext.accessToken = accessToken;
	}
	
	

}
