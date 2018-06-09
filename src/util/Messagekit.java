package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSONObject;
import com.sun.xml.internal.txw2.Document;

import dao.EventPushService;
import dao.impl.EventPushDao;
import model.EventPush;
import model.WeixinFinalValue;

public class Messagekit {
	
	@SuppressWarnings("unchecked")
	//将xml转换为map格式
	public static Map<String,String>reqMsg2Map(HttpServletRequest req) throws IOException, Exception{
		String xml=req2xml(req);
		Map<String,String>maps=new HashMap<String,String>();
		org.dom4j.Document document=DocumentHelper.parseText(xml);
		Element root = document.getRootElement();
		List<Element>eles=root.elements();
		for(Element e:eles) {
			maps.put(e.getName(), e.getTextTrim());
		}
		return maps;
		
	}
	private static String req2xml(HttpServletRequest req) throws IOException {
		BufferedReader br=null;
		br=new BufferedReader(new InputStreamReader(req.getInputStream()));
		String str=null;
		StringBuffer sb=new StringBuffer();
		while((str=br.readLine())!=null) {
			sb.append(str);
		}
		return sb.toString();
	}
	//处理消息类型
	public static String handlerMsg(Map<String, String> msgMap) {
		// TODO Auto-generated method stub
		//得到type类型
		String msgType=msgMap.get("MsgType");
		//如果是点击事件
		if(msgType.equals(WeixinFinalValue.MSG_EVENT_TYPE)) {
			//得到event类型
			String eventType=msgMap.get("Event");
			//领取卡卷事件
			if(eventType.equals(WeixinFinalValue.EVENT_USER_GET_CARD)) {
				return handlerEvent_User_Get_Card(msgMap);
			}
			
		}
		//如果是文本事件
		else if(msgType.equals(WeixinFinalValue.MSG_TEXT_TYPE)) {
			
		}
		return null; 
	}
	//处理领取事件
	private static String handlerEvent_User_Get_Card(Map<String, String> msgMap) {
		EventPushService epdao=new EventPushDao();
		EventPush ep=new EventPush();
		String MsgType=msgMap.get("MsgType");
		String FromUserName=msgMap.get("FromUserName");
		String Event=msgMap.get("Event");
		String CardId=msgMap.get("CardId");
		String UserCardCode=msgMap.get("UserCardCode");
		String IsReturnBack=msgMap.get("IsReturnBack");
		String OuterStr=msgMap.get("OuterStr");
		String ulevel=null;
		if(OuterStr.equals("young")){
			ulevel="1";
		}else if (OuterStr.equals("black")){
			ulevel="5";
		}else if (OuterStr.equals("wing")){
			ulevel="6";
		}
		ep.setUlevel(ulevel);
		ep.setMsgType(MsgType);
		ep.setFromUserName(FromUserName);
		ep.setEvent(Event);
		ep.setCardId(CardId);
		ep.setUserCardCode(UserCardCode);
		ep.setIsReturnBack(IsReturnBack);
		ep.setOuterStr(OuterStr);
		int i=epdao.GetUserCardPush(ep);
		if(i==0) {
			epdao.GetUserCardPush(ep);
		}else if(i==1){
			return null;
		}
		return null;
	} 
	//转换数据为json
	public static JSONObject getJsonObject(HttpServletRequest request) throws IOException{
		String resultStr = "";
		        String readLine;
		        StringBuffer sb = new StringBuffer();
		        BufferedReader responseReader = null;
		        OutputStream outputStream = null;
		        try {
		            responseReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		            while ((readLine = responseReader.readLine()) != null) {
		                sb.append(readLine).append("\n");
		            }
		            responseReader.close();
		            resultStr = sb.toString();
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		            if (outputStream != null) {
		            outputStream.close();
		            }
		        } 
		return JSONObject.parseObject(resultStr);
		}
}
