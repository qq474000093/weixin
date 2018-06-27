package model;

public class WeixinFinalValue {

	public final static String APPID="wx80a1e1aa208f08b0";
	public final static String APPSECRET="73bd4b829f8b1630a25dd5d4cf2112c3";
	public final static String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public final static String MENU_ADD="";
	
	public final static String MSG_TEXT_TYPE="text";
	public final static String MSG_EVENT_TYPE="event";
	public final static String EVENT_USER_GET_CARD="user_get_card";
	public final static String EVENT_USER_VIEW_CARD="user_view_card";

	
	public final static String AUTH_URL="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect ";
	public final static String AUTO_GET_OID="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	public final static String ACTIVATE_URL="https://api.weixin.qq.com/card/membercard/activate?access_token=TOKEN";
	
	
	
	
}
