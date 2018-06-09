package controller;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;









import org.apache.http.conn.ConnectTimeoutException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import util.HttpClientUtils;

import com.alibaba.fastjson.JSON;
import com.youzan.open.sdk.client.auth.Token;
import com.youzan.open.sdk.client.core.DefaultYZClient;
import com.youzan.open.sdk.client.core.YZClient;
import com.youzan.open.sdk.client.oauth.OAuth;
import com.youzan.open.sdk.client.oauth.OAuthContext;
import com.youzan.open.sdk.client.oauth.OAuthFactory;
import com.youzan.open.sdk.client.oauth.OAuthType;
import com.youzan.open.sdk.gen.v3_0_0.api.YouzanUserBasicGet;
import com.youzan.open.sdk.gen.v3_0_0.api.YouzanUsersWeixinFollowerGet;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanUserBasicGetParams;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanUserBasicGetResult;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanUsersWeixinFollowerGetParams;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanUsersWeixinFollowerGetResult;
import com.youzan.open.sdk.gen.v3_0_1.api.YouzanCrmFansPointsGet;
import com.youzan.open.sdk.gen.v3_0_1.model.YouzanCrmFansPointsGetParams;
import com.youzan.open.sdk.gen.v3_0_1.model.YouzanCrmFansPointsGetResult;
@Controller
public class getJiFen {
	@RequestMapping("/getJifen")
	@ResponseBody
	//openid=oDweow5exks_VKpGjp8iejRIPleA
	//user_id=4058793467
	public YouzanCrmFansPointsGetResult getJifen(String code) throws  Exception{
		System.out.println(code);
	    String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx80a1e1aa208f08b0&secret=73bd4b829f8b1630a25dd5d4cf2112c3&code="+code+"&grant_type=authorization_code";
		Map returnMap = new HashMap();
	    String  retStr = HttpClientUtils.postParameters(url,"");
	    
			 returnMap = JSON.parseObject(retStr);
			 if(returnMap.containsKey("errcode")) {
				  
				 HttpClientUtils.get("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx80a1e1aa208f08b0&redirect_uri=http://abc.lingzhishouji.com/store/shop.html&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
			 }
			//String access_token=returnMap.get("refresh_token").toString();
			//String url1="https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=wx80a1e1aa208f08b0&grant_type=refresh_token&refresh_token="+access_token;
			 else {   String openid = returnMap.get("openid").toString();
		     System.out.println(openid);
			    //获取access token
			    OAuth oauth = OAuthFactory.create(OAuthType.SELF, new OAuthContext("a308d9fe2e4a668669", "c74b71cfe5bd3c2083f25958b9302b20", 19301140L));
				YZClient client = new DefaultYZClient(new Token(oauth.getToken().getAccessToken())); //new Sign(appKey, appSecret) 
				//通过openid获取有赞用户信息
				YouzanUsersWeixinFollowerGetParams youzanUsersWeixinFollowerGetParams = new YouzanUsersWeixinFollowerGetParams();
	            youzanUsersWeixinFollowerGetParams.setWeixinOpenid(openid);
	            YouzanUsersWeixinFollowerGet youzanUsersWeixinFollowerGet = new YouzanUsersWeixinFollowerGet();
				youzanUsersWeixinFollowerGet.setAPIParams(youzanUsersWeixinFollowerGetParams);
				YouzanUsersWeixinFollowerGetResult result = client.invoke(youzanUsersWeixinFollowerGet);
				Long userId=result.getUser().getUserId();
			    System.out.println(userId);
				//Long u=(long)4058793467L;
				//通过userId获取积分数据
				YouzanCrmFansPointsGetParams youzanCrmFansPointsGetParams = new YouzanCrmFansPointsGetParams();
                youzanCrmFansPointsGetParams.setFansId( (long)userId);
                YouzanCrmFansPointsGet youzanCrmFansPointsGet = new YouzanCrmFansPointsGet();
				youzanCrmFansPointsGet.setAPIParams(youzanCrmFansPointsGetParams);
				YouzanCrmFansPointsGetResult result1 = client.invoke(youzanCrmFansPointsGet);
				System.out.println(result1.getPoint());
				return result1;
				
			 }
			 return null; 
	}
	}

