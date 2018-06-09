package model;

public class EventPush {
	private int id;
    
	private String MsgType;
	private String FromUserName;
	private String Event;
	private String CardId;
	private String UserCardCode;
	private String IsReturnBack;
	private String OuterStr;
	private  String ulevel;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	public String getCardId() {
		return CardId;
	}
	public void setCardId(String cardId) {
		CardId = cardId;
	}
	public String getUserCardCode() {
		return UserCardCode;
	}
	public void setUserCardCode(String userCardCode) {
		UserCardCode = userCardCode;
	}
	public String getIsReturnBack() {
		return IsReturnBack;
	}
	public void setIsReturnBack(String isReturnBack) {
		IsReturnBack = isReturnBack;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getOuterStr() {
		return OuterStr;
	}
	public void setOuterStr(String outerStr) {
		OuterStr = outerStr;
	}
	public String getUlevel() {
		return ulevel;
	}

	public void setUlevel(String ulevel) {
		this.ulevel = ulevel;
	}
}
