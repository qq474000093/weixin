package action.weixinpeoject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.WeixinContext;
import util.Messagekit;
import util.Sha1;

@Controller
public class WeixinController {
     
	private static final String TOKEN = "lingzhi";
	
	@RequestMapping(value="/wget",method=RequestMethod.GET)
	public void init(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        String echostr = req.getParameter("echostr");
        String[] arrs= {WeixinController.TOKEN,nonce,timestamp};
        Arrays.sort(arrs);
        StringBuffer sb=new StringBuffer();
        for(String a:arrs) {
        	sb.append(a);
        }
        String sha1=Sha1.sha1(sb.toString());
        System.out.println(sha1.equals(signature));
        
        if(sha1.equals(signature)) {
        	resp.getWriter().println(echostr);
        }
	}
	
	@RequestMapping(value="/wget",method=RequestMethod.POST)
	public void getInfo(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		//将xml转换成map
		Map<String,String>msgMap=Messagekit.reqMsg2Map(req);
		System.out.println(msgMap);
		//处理map中的type类型
		String respCon=Messagekit.handlerMsg(msgMap);
	}
	@RequestMapping("/at")
	public void testAccessToken(HttpServletResponse resp) throws IOException {
		resp.getWriter().println(WeixinContext.getAccessToken());
	}
}
