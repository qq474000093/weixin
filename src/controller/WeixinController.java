package controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import util.Sha1;

@Controller
public class WeixinController {
     
	private static final String TOKEN = "lingzhi";
	
	@RequestMapping("/init")
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
        System.out.println("sss");
        if(sha1.equals(signature)) {
        	resp.getWriter().println(echostr);
        }
	}
	@RequestMapping("/haha")
	
	public void test() {
		System.out.println(1111);
	}
}
