package controller;



import java.io.IOException;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.youzan.open.sdk.client.auth.Token;
import com.youzan.open.sdk.client.core.DefaultYZClient;
import com.youzan.open.sdk.client.core.YZClient;
import com.youzan.open.sdk.client.oauth.OAuth;
import com.youzan.open.sdk.client.oauth.OAuthContext;
import com.youzan.open.sdk.client.oauth.OAuthFactory;
import com.youzan.open.sdk.client.oauth.OAuthType;
import com.youzan.open.sdk.gen.v3_0_0.api.YouzanSalesmanAccountsGet;
import com.youzan.open.sdk.gen.v3_0_0.api.YouzanSalesmanCustomersGet;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanSalesmanAccountsGetParams;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanSalesmanAccountsGetResult;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanSalesmanCustomersGetParams;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanSalesmanCustomersGetResult;


@Controller 
public class FenXiaoYuan {

@RequestMapping("/getAll")
@ResponseBody
public YouzanSalesmanAccountsGetResult getAll(@RequestParam long pageNo,long PageSize){
	System.out.println(pageNo);
	System.out.println(PageSize);
	OAuth oauth = OAuthFactory.create(OAuthType.SELF, new OAuthContext("a308d9fe2e4a668669", "c74b71cfe5bd3c2083f25958b9302b20", 19301140L));
	System.out.println(oauth.getToken().getAccessToken());

	YZClient client = new DefaultYZClient(new Token(oauth.getToken().getAccessToken())); //new Sign(appKey, appSecret)
	YouzanSalesmanAccountsGetParams youzanSalesmanAccountsGetParams = new YouzanSalesmanAccountsGetParams();

	youzanSalesmanAccountsGetParams.setPageNo(pageNo);
	youzanSalesmanAccountsGetParams.setPageSize(PageSize);

	YouzanSalesmanAccountsGet youzanSalesmanAccountsGet = new YouzanSalesmanAccountsGet();
	youzanSalesmanAccountsGet.setAPIParams(youzanSalesmanAccountsGetParams);
	YouzanSalesmanAccountsGetResult result = client.invoke(youzanSalesmanAccountsGet);

	return result;
}
@RequestMapping("/getKehu")
@ResponseBody
public YouzanSalesmanCustomersGetResult getKehu(String phone,long pageNo1,long pageSize1){
	
	OAuth oauth = OAuthFactory.create(OAuthType.SELF, new OAuthContext("a308d9fe2e4a668669", "c74b71cfe5bd3c2083f25958b9302b20", 19301140L));
	YZClient client = new DefaultYZClient(new Token(oauth.getToken().getAccessToken())); //new Sign(appKey, appSecret)
	YouzanSalesmanCustomersGetParams youzanSalesmanCustomersGetParams = new YouzanSalesmanCustomersGetParams();youzanSalesmanCustomersGetParams.setFansId(0L);
	youzanSalesmanCustomersGetParams.setFansType(1L);
	youzanSalesmanCustomersGetParams.setMobile(phone);
	youzanSalesmanCustomersGetParams.setPageNo(pageNo1);
	youzanSalesmanCustomersGetParams.setPageSize(pageSize1);
	YouzanSalesmanCustomersGet youzanSalesmanCustomersGet = new YouzanSalesmanCustomersGet();
	youzanSalesmanCustomersGet.setAPIParams(youzanSalesmanCustomersGetParams);
	YouzanSalesmanCustomersGetResult result = client.invoke(youzanSalesmanCustomersGet);
	System.out.println(pageNo1+"---"+pageSize1);
   
	return result;
}



}
