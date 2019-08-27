var OPENID="";
var userInfo;
$(function() {
	init();
});
//初始化
function init() {
	OPENID = baseTools.getUrlParam("OPENID");
	if(OPENID==null){
			$("body").html("请在微信内打开网页");
			return ;
	}
	//查询用户基础信息
	baseTools.xhrAjax({
		url : "../../user/selectUSER_INFO",
		async : false,
		dataType : 'json',
		contentType : 'application/json',
		params : JSON.stringify({
			OPENID : OPENID
		}),
		callback : [ function(jsonObj, xhrArgs) {
			switch (parseInt(jsonObj.code)) {
			case -1: //异常
				layer.alert("异常:" + jsonObj.msg, {'icon' : 5});
				break;
			case -3: //cookie 失效请重新登录
				layer.alert(jsonObj.msg, {'icon' : 5});
				baseTools.gotoLogin();//去登录
				break;
			default:
				userInfo=jsonObj.data[0];
				$("#NICKNAME").html(userInfo.NICKNAME);
				if(userInfo.SEX=="1"){
					$("#SEX").html("男");
				}else{
					$("#SEX").html("女");
				}
				$("#PHONE").html(userInfo.PHONE);
				
			}
		} ],
		callbackError : [ function(data, xhrArgs) {
			baseTools.hideMash(); //关闭遮罩
		} ]
	});
} 
//修改名字
function updateName(){
	window.location.href="updateName.html?OPENID="+OPENID+"&NICKNAME="+escape(userInfo.NICKNAME);
}
//修改性别
function updateSex(){
	window.location.href="updateSex.html?OPENID="+OPENID+"&SEX="+userInfo.SEX;
}
//修改手机
function updatePhone(){
	window.location.href="updatePhone.html?OPENID="+OPENID+"&PHONE="+userInfo.PHONE;
}