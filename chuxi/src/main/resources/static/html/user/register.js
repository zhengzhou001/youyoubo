var form;
var layer;
var countdown = 120;
var OPENID="";
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
		url : "../../wx/selectWX_USER",
		async : false,
		dataType : 'json',
		contentType : 'application/json',
		params : JSON.stringify({
			OPENID : OPENID
		}),
		callback : [ function(jsonObj, xhrArgs) {
			switch (parseInt(jsonObj.code)) {
			case -1: //异常
				mui.alert(jsonObj.msg);
				break;
			case -3: //cookie 失效请重新登录
				mui.alert(jsonObj.msg, function() {
                    baseTools.gotoLogin();//去登录
                });
				break;
			default:
				  //表单初始赋值
				  $("#NICKNAME").val(jsonObj.data[0].NICKNAME);
 				  $("input[name='SEX'][value="+jsonObj.data[0].SEX+"]").prop("checked", "checked");
 				 $("#HEADIMGURL").prop("src",jsonObj.data[0].HEADIMGURL);
 				  $("#AREA").html(jsonObj.data[0].PROVINCE+"&nbsp;"+jsonObj.data[0].CITY);
 				 $("#PROVINCE").val(jsonObj.data[0].PROVINCE);
 				 $("#CITY").val(jsonObj.data[0].CITY);
 				    //地区
 					var userPicker = new mui.PopPicker({
 						layer: 2
 					});
 					userPicker.setData(cityData);
 					userPicker.pickers[0].setSelectedValue(jsonObj.data[0].PROVINCE_ID);
 					 var shi=userPicker.getSelectedItems()[0].children;
 					//userPicker.pickers[1].setSelectedValue(userInfo.CITY_ID);//不起作用
				   for(var i=0;i<shi.length;i++){
					   if(shi[i].value==jsonObj.data[0].CITY_ID)
                       {
						   userPicker.pickers[1].setSelectedIndex(i);
                       }
					   
				   }
				   $('#showCityPicker').click(function(){
						  userPicker.show(function(items) {
							  $('#AREA').html(items[0].text+"&nbsp;"+items[1].text);
							  $("#PROVINCE").val(items[0].text);
				 				 $("#CITY").val(items[1].text);
						  });
				  });
 				  
			}
		} ],
		callbackError : [ function(data, xhrArgs) {
			baseTools.hideMash(); //关闭遮罩
		} ]
	});
	
}
//换头像
function changeImg(){
	//查询签名
	baseTools.xhrAjax({
		url : "../../config",
		async : false,
		dataType : 'json',
		contentType : 'application/json',
		params : JSON.stringify({
			url:window.location.href.split('#')[0]
 		}),
		callback : [ function(data, xhrArgs) {
			wx.config({
                debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                appId: data.appId, // 必填，公众号的唯一标识
                timestamp: data.timestamp, // 必填，生成签名的时间戳
                nonceStr: data.nonceStr, // 必填，生成签名的随机串
                signature: data.signature,// 必填，签名，见附录1
                jsApiList: ['chooseImage', 'uploadImage', 'downloadImage', 'previewImage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
            });
			wx.ready(function(){
                //layer.msg("jssdk初始化成功");
                // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，
                //所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
				wx.chooseImage({
					count: 1, // 默认9
					sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
					success: function (res) {
						var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
						$("#HEADIMGURL").prop("src",localIds[0]);
						wx.uploadImage({
							localId: localIds[0], // 需要上传的图片的本地ID，由chooseImage接口获得
							isShowProgressTips: 1, // 默认为1，显示进度提示
							success: function (res) {
								var serverId = res.serverId; // 返回图片的服务器端ID
								$("#SERVERID").val(serverId);
							}
						});
					}
					});
            });
            wx.error(function(res){
                // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，
                //也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
            	mui.alert(res);
            });
		} ],
		callbackError : [ function(data, xhrArgs) {
			baseTools.hideMash(); //关闭遮罩
		} ]
	});
}
//保存
function doSave(){
	var NICKNAME=$("#NICKNAME").val();
	if(NICKNAME.length<1||NICKNAME.length>10){
		mui.alert("昵称必须是1-10个字符");
		return;
	}
	var reg = /^[1][3,4,5,7,8][0-9]{9}$/;
	var PHONE=$("#PHONE").val();
	if (!reg.test(PHONE)) {
		mui.alert("请输入正确手机号");
		return;
	}
	var yzm = $("#YZM").val();
	if(yzm.length!=4){
		mui.alert("请输入4位验证码");
		return;
	} 
	 
	baseTools.xhrAjax({
		url : "../../user/doSave",
		async : false,
		dataType : 'json',
		contentType : 'application/json',
		params : JSON.stringify({
			SERVERID:$("#SERVERID").val(),
			NICKNAME:NICKNAME,
 			SEX:$("input[name='SEX']:checked").val(),
 			HEADIMGURL:$("#HEADIMGURL")[0].src,
			OPENID:OPENID,
			PHONE:PHONE,
			YZM:yzm,
			CITY:$("#CITY").val(),
			COUNTRY:"中国",
			PROVINCE:$("#PROVINCE").val()
			
		}),
		callback : [ function(jsonObj, xhrArgs) {
			switch (parseInt(jsonObj.code)) {
			case -1: //异常
				mui.alert(jsonObj.msg);
				break;
			case -3: //cookie 失效请重新登录
				mui.alert(jsonObj.msg, function() {
                    baseTools.gotoLogin();//去登录
                });
				break;
			default:
				mui.alert('会员办理成功', function () { 
					window.location.href="info.html?OPENID="+OPENID;
				});
				 
			}
		} ],
		callbackError : [ function(data, xhrArgs) {
			baseTools.hideMash(); //关闭遮罩
		} ]
	});
}
//获取验证码
function getCode(){
	var phone = $("#PHONE").val()
	var reg = /^[1][3,4,5,7,8][0-9]{9}$/;
	if (!reg.test(phone)) {
		mui.alert("请输入正确的手机号");
		return;
	}
	settime();//按钮倒计时
	//调用服务发送验证码
	baseTools.xhrAjax({
		url : "../../sms/getCode",
		async : false,
		dataType : 'json',
		contentType : 'application/json',
		params : JSON.stringify({PHONE:phone,EXPIRE_COUNT:"600",SIGN_NAME : '波波工作室',TPL_NAME : 'SMS_172205823'}),
		callback : [ function(jsonObj, xhrArgs) {
			switch (parseInt(jsonObj.code)) {
			case -1: //异常
				mui.alert(jsonObj.msg);
				break;
			case -3: //cookie 失效请重新登录
				mui.alert(jsonObj.msg, function() {
                    baseTools.gotoLogin();//去登录
                });
				break;
			default:
				//成功
				mui.alert('验证码已经发送到手机，请注意查收');

			}
		} ],
		callbackError : [ function(data, xhrArgs) {
			baseTools.hideMash(); //关闭遮罩
		} ]
	});
}
//倒计时
function settime() {
	if (countdown == 0) {
		$("#YZMBUTTON").removeAttr("disabled");
		$("#YZMBUTTON").html("获取验证码");
		countdown = 120;
		return;
	} else {
		$("#YZMBUTTON").prop('disabled',"true");
		$("#YZMBUTTON").html(countdown+"秒后重发");
		countdown--;
	}
	setTimeout(function() {
		settime()
	}, 1000);
}