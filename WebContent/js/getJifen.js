$(function(){
	
	jifen();
});

function jifen(){
	var code=getQueryVariable("code");
	 $.ajax({
		type:"GET",
		url:"getJifen",
		data:{
			code:code
			},
		success:function(data){
			$("#myjifen").html(data.point);
			}
	        });
}

//获取url参数
function getQueryVariable(variable)
{
       var query = window.location.search.substring(1);
       var vars = query.split("&");
       for (var i=0;i<vars.length;i++) {
               var pair = vars[i].split("=");
               if(pair[0] == variable){return pair[1];}
       }
       return(false);
}