package action.weixinpeoject;

import com.alibaba.fastjson.JSON;
import model.ErpFinalValue;
import model.WebAuthAccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import util.HttpClientUtils;
import util.JsonUtil;
import util.WeixinUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class Couponing {
    @RequestMapping("/Couponing")
    @ResponseBody
    public JSON Couponing(HttpServletRequest request, HttpServletResponse resp) throws Exception {
        String openid=request.getParameter("openid");
        String used=request.getParameter("used");
                JSON js=null;
                if(openid!=null&&used!=null){
                    String url=ErpFinalValue.YOUHUIURL;
                    url=url.replace("WXID",openid)
                            .replace("USED",used);

                    String cpmsg=HttpClientUtils.get(url);
                    js=JSON.parseObject(cpmsg);
                }
                System.out.println(js);
                return js;
    }

    @RequestMapping(value="/youhui",method =RequestMethod.GET)
    public String youhui(ModelMap mod, HttpServletRequest req, HttpServletResponse resp){
        String code = req.getParameter("code");
        String msg = null;
        if (code != null) {
            msg = WeixinUtil.getOpenId(code);
            WebAuthAccessToken at = (WebAuthAccessToken) JsonUtil.string2Obj(msg, WebAuthAccessToken.class);
            if (at.getAccess_token() == null && at.getOpenid() == null) {
                new RedirectView("http://wx.58lz.com/weixin/youhui.do");
            }
            String openid=at.getOpenid();
            mod.put("openid",openid);
        }
        return "couponing";
    }
}
