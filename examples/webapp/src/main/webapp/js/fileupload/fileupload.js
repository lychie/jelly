$(function(){
	
	$("#simpleFileuploadForm").submit(function(){
		$(this).ajaxSubmit({
			type : 'POST',
			dataType : 'json',
			success : function(data){
				if(data.action == "success"){
					$("#image").attr("src", data.result);
				}else{
					alert(data.result);
				}
			}
		});
		return false;
	});
	
	$("#multiFileuploadForm").submit(function(){
		$(this).ajaxSubmit({
			type : 'POST',
			dataType : 'json',
			success : function(data){
				if(data.action == "success"){
					var url = data.result.split(",");
					for(var i = 0; i < url.length; i++){
						$("#image" + i).attr("src", url[i]);
					}
				}else{
					alert(data.result);
				}
			}
		});
		return false;
	});
	
	$("#md5FileuploadForm").submit(function(){
		$(this).ajaxSubmit({
			type : 'POST',
			dataType : 'json',
			success : function(data){
				if(data.action == "success"){
					$("#image").attr("src", data.result);
				}else{
					alert(data.result);
				}
			}
		});
		return false;
	});
});