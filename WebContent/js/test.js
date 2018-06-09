$(function(){
	//电话号码
	 
    var Userphone=document.getElementById("Userphone").value;
    //手机验证码
    var txt_Mobile_code=document.getElementById("txt_Mobile_code").value;
    //姓名
	var Username=document.getElementById("Username").value;
    //性别
    var sex=document.getElementsByName("txt_UserSex");
    var v_sex;
    for(var i=0;i<sex.length;i++){
        if(sex[i].checked){
            v_sex=sex[i].value;
        }
    }
  
	//输入框的正则判断（失去焦点时间）
	$("input").blur(function(){
		 var Userphone=document.getElementById("Userphone").value;
		 //输入框的正则判断
        //电话号码的正则：11位数字、必须输入、以1为开头、最后以0-9的9个整数结尾（正则表达式）
        var reg = /^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\d{8}$/;
        if (!reg.test(Userphone)) {
        	
            new TipBox({type:'tip',str:'请输入正确的电话号码!',clickDomCancel:true,setTime:10000500,hasBtn:true});
        
        }
	 });
	
	
	$("#button").click(function(){
		getcode();
		
	});
	$("#nextone").click(function(){
		next();
	});
	$("#up").click(function(){
		up();
	});
	$("#tip").click(function(){
		register();
	});	
	  //选择男女的按钮
    var man=document.getElementById("man");
    var women=document.getElementById("women");
    var txt_UserSex1=document.getElementById("txt_UserSex1");
    var txt_UserSex2=document.getElementById("txt_UserSex2");
    //默认样式的设置
    man.style.color='#00628b';
    man.style.fontSize='16px';
    //点击先生的单选按钮
     txt_UserSex1.onclick = function() {
        man.style.color='#00628b';
        man.style.fontSize='16px';
        women.style.color='#515151';
        women.style.fontSize='14px';
    }
    //点击女士的单选按钮
    txt_UserSex2.onclick = function() {
        women.style.color='#00628b';
        women.style.fontSize='16px';
        man.style.color='#515151';
        man.style.fontSize='14px';
    }
	
	
});



//随机验证码
var code="";  
creatcode();
function creatcode(){
	  for(var i=0;i<4;i++){  
 code+=Math.floor(Math.random()*10)  ;
 }
 
}
//发送短信




//得到code
function getcode(){
	var phone=$("#Userphone").val();
	code="";
	creatcode();
	 $.ajax({
	    url:"sendSms",
		data:{
			phone:phone,
			code:code
		
			},

		success:function(data){
			button.disabled = true;  //当点击后倒计时时候不能点击此按钮
	        var time = 60;  //倒计时60秒
	        var timer = setInterval(fun1, 1000);  //设置定时器
	        function fun1() {
	            time--;
	            if(time>=0) {
	                button.innerHTML = time + "s后重新发送";
	            }else{
	                button.innerHTML = "重新发送";
	                button.disabled = false;  //倒计时结束能够重新点击发送的按钮
	                clearTimeout(timer);  //清除定时器
	                time = 5;  //设置循环重新开始条件
	                
	            }    
		}
				
			}
		});
}


//点击下一步按钮

function  next() {
    //电话号码或者验证码有任何一条没输入，“下一步按钮失效”
    //电话号码
    var next=document.getElementById("next");
	var last=document.getElementById("last");
    var Userphone=document.getElementById("Userphone").value;
    //手机验证码
    var txt_Mobile_code=document.getElementById("txt_Mobile_code").value;
    if(txt_Mobile_code!=code){
        this.disabled=true;
        alert("输入的验证码有错误！");
    }else{
    next.style.display='block';
	last.style.display='none';
    }
    this.disabled=false;
}



function up() {
    var next=document.getElementById("next");
    var last=document.getElementById("last");
    next.style.display='none';
    last.style.display='block';
}



var sex=$(".sex");
var usex;
for(var i=0;i<sex.length;i++){
    if(sex[i].checked){
    	usex=sex[i].value;
         }
   }

function register(){
	var nickname="";
	var wxid="";
	var nickname=$("#nickname").val();
	var wxid=$("#openid").val();
	var umobile=$("#Userphone").val();
	var regname=$("#Username").val();

	
	$.ajax({
	    url:"enroll",
	    type:"POST",
		data:{
			usex:usex,	
			nickname:nickname,
			wxid:wxid,
			umobile:umobile,
			regname:regname
			
		},
     success:function(data){
    	
    if(data.lstate=="N"||data.lstate=="V"){
    	    		alert("注册成功!!");
    				window.location.href='https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzI4MjU2ODk3NQ==&scene=124&#wechat_redirect';
    	       }  
    else if(data.errorCode=="5001"){
    	alert("注册失败,请稍后再试");
    }
    else if(data.errorCode=="5101"){
    	alert("该微信号已绑定会员!");
    	window.location.href='https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzI4MjU2ODk3NQ==&scene=124&#wechat_redirect';
    }
    else if(data.errorCode="6002"){
    	alert("参数不合法!!");
    }
     }
		});
	}

