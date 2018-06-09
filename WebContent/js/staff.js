$(function(){
	f1();
	$("#pageSize").change(function(){
		f1();
		
	});
	
	$("#pageNo").change(function(){
	     f1();
    });
	$("#pageSize1").change(function(){
		f2();
    });
	
	$("#pageNo1").change(function(){
		f2();
    });
});

function f2(){
	
	var pageNo1=$("#pageNo1").val();
	var pageSize1=$("#pageSize1").val();
	var phone=$("#hidden").val();
	
	 $.ajax({
		type:"GET",
		url:"getKehu",
		data:{
			phone:phone,
			pageNo1:pageNo1,
			pageSize1:pageSize1
			},

		success:function(data){
			
			var html="";
			var obj=data.customers;
			
			for(var i=0;i<obj.length;i++){
				var y=i+1;
				html+="<tr><td  colspan='2'>"+y+"</td><td  colspan='3'><a>"+obj[i].nickname+"</a></td><td  colspan='2'><img src="+obj[i].avatar+"></td></tr>";
				
			}
			
			$("#kehu").html(html);
			$("#total_results1 span").html(data.total_results);
			//清空之前的数据
			$("#pageNo1 option").remove();
			//动态添加定量的下拉列表
			for (var i=1;i<=Math.ceil(data.total_results/pageSize1);i++){
				if(i>0){
                 $("#pageNo1").append("<option >"+i+"</option>")
	        }
            }
			
			
			//下拉列表的显示值展示
			$(".page1").val(pageNo1);
			}
	        });
}

function f1(){
	var pageNo=$("#pageNo").val();
    var PageSize=$("#pageSize").val();
	$.ajax({
		type:"GET",
		url:"getAll",
		data:{
			PageSize:PageSize,
			pageNo:pageNo
		},
		success:function(data){
			if(data){
				var html="";
				var obj=data.accounts;
				for(var i=0;i<obj.length;i++){
					var n=i+1;
					html+="<tr><td>"+n+"</td><td><a  class='nickname'>"+obj[i].nickname+"</a></td><td class='mobile'>"+obj[i].mobile+"</td><td>"+obj[i].order_num+"</td><td>"+obj[i].money+"</td><td>"+obj[i].created_at+"</td></tr>";
				}
				$("#body").html(html);
				$("#tiaoshu").html(data.total_results);
				//清空之前的数据
				$("#pageNo option").remove();
				//动态添加定量的下拉列表
				for (var i=1;i<=Math.ceil(data.total_results/PageSize);i++){
					$("#pageNo").append("<option >"+i+"</option>")
				}
				//下拉列表的显示值展示
				$(".page").val(pageNo);
}
			$(".nickname").unbind("click");
			$(".nickname").click(function(){
				var pageNo1=$("#pageNo1").val();
				var pageSize1=$("#pageSize1").val();
				var phone=$(this).parent().parent().find(".mobile").html();
				 $("#ruku_look").css("display","block");
				 $("#xiaoshouyuan").css("display","none");
				 $.ajax({
					type:"GET",
					url:"getKehu",
					data:{
						phone:phone,
						pageNo1:pageNo1,
						pageSize1:pageSize1
						},
						success:function(data){
						var html="";
						var obj=data.customers;
						for(var i=0;i<obj.length;i++){
							var y=i+1;
							html+="<tr><td  colspan='2'>"+y+"</td><td  colspan='3'><a>"+obj[i].nickname+"</a></td><td  colspan='2'><img src="+obj[i].avatar+"></td></tr>";
							
						}
						$("#kehu").html(html);
						$("#total_results1 span").html(data.total_results);
						//清空之前的数据
						$("#pageNo option").remove();
						//动态添加定量的下拉列表
						for (var i=1;i<=Math.ceil(data.total_results/pageSize1);i++){
							if(i>1){
                             $("#pageNo1").append("<option >"+i+"</option>")
							}
							}
						//下拉列表的显示值展示
						$(".page1").val(pageNo1);
						}
				});
				 //隐藏域中放入手机号
				 $("#hidden").val(phone);
			});
		}
	});
}
