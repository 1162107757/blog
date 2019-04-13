$(function(){
	$.ajax({
		type : 'POST',
		dataType : 'json',
		url : './getAdminInfo',
		contentType : 'application/json',
		success : function(result){
			if(result != null){
				$("#userCode").text(result.adminCode);
			}
		}
	});
});

$("#loginOut").click(function(){
	layer.confirm('确定退出?', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type : 'POST',
			dataType : 'json',
			url : './loginout',
			data: {"loginCode":"admin"},
			success : function(result){
				if(result.success){
					window.location.href="login.jsp";
				}else{
					layer.msg(result.msg);
				}
			}
		});
		layer.close(index);
	});
});