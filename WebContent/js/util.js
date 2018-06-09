$(function() {
     //获取页面的值得原生JS     全部设置成全局变量，方便后面函数的调用
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
      
        //短信验证码
           var code="";  
           creatcode();
         function creatcode(){
         	  for(var i=0;i<4;i++){  
            code+=Math.floor(Math.random()*10)  ;
            }
            
         }
         //发送短信
         function code(){
        	 var Userphone=document.getElementById("Userphone").value;
        	 code="";
         	creatcode();
         	$.ajax({
         		
     		type:"POST",
     		url:"sendSms",
     		
     		data:{
     			code:code,
     			phone:Userphone
     			},
     			
     		
     	        });
        	}
         

  
    //点击获取验证码的按钮，进行倒计时的函数
    var button = document.getElementById("button");
 
    button.onclick = function() {
    	code();
    	
        button.disabled = true;  //当点击后倒计时时候不能点击此按钮
        var time = 8;  //倒计时60秒
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
  
    };
   
    
    
    
    
   
    //点击上一步按钮
    var up = document.getElementById("up");
   up.onclick = function() {
        var next=document.getElementById("next");
        var last=document.getElementById("last");
        next.style.display='none';
        last.style.display='block';
    }
    
    
});