$(function(){
	$("#load").click(function(){
		if(isUnload()){
			load();
		}else{
			alert("--- 数据已经加载 ---");
		}
	});
	checkLoadStatus();
});

function isUnload(){
	return $("#load").attr("class") == "enabled-text";
}

function load(){
	$.ajax({
		url : "demo/json",
		type : "POST",
		error : function(){
			alert("请求失败!");
		},
		success : function(json){
			$("#jsonData").val(json);
			$("#load").removeClass("enable-text").addClass("disabled-text");
		}
	});
}