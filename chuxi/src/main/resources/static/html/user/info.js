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
				mui.alert(jsonObj.msg);
				break;
			case -3: //cookie 失效请重新登录
				mui.alert(jsonObj.msg, function() {
                    baseTools.gotoLogin();//去登录
                });
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
				$("#AREA").html(userInfo.PROVINCE+"&nbsp;"+userInfo.CITY);
				$("#HEADIMGURL").prop("src","../../user/downloadImage?fileAbs="+escape(userInfo.HEADIMGURL));
				//地区
				var userPicker = new mui.PopPicker({
					layer: 2
				});
				userPicker.setData(cityData);
				userPicker.pickers[0].setSelectedValue(userInfo.PROVINCE_ID);
				 var shi=userPicker.getSelectedItems()[0].children;
				//userPicker.pickers[1].setSelectedValue(userInfo.CITY_ID);//不起作用
				   for(var i=0;i<shi.length;i++){
					   if(shi[i].value==userInfo.CITY_ID)
                       {
						   userPicker.pickers[1].setSelectedIndex(i);
                       }
					   
				   }
				$('#showCityPicker').click(function(){
				  userPicker.show(function(items) {
					  $('#AREA').html(items[0].text+"&nbsp;"+items[1].text);
					  baseTools.xhrAjax({
							url : "../../user/updateUSER_INFO",
							async : false,
							dataType : 'json',
							contentType : 'application/json',
							params : JSON.stringify({
								OPENID : OPENID,
								PROVINCE_NEW : items[0].text,
								CITY_NEW : items[1].text
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
									 mui.alert('地区修改成功');
								}
							} ],
							callbackError : [ function(data, xhrArgs) {
								baseTools.hideMash(); //关闭遮罩
							} ]
						});
				  });
				});
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
								//提交服务器下载微信上图片到本地
								baseTools.xhrAjax({
									url : "../../user/updateUSER_INFO",
									async : false,
									dataType : 'json',
									contentType : 'application/json',
									params : JSON.stringify({
										OPENID : OPENID,
										SERVERID : serverId
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
											 mui.alert('头像修改成功');
										}
									} ],
									callbackError : [ function(data, xhrArgs) {
										baseTools.hideMash(); //关闭遮罩
									} ]
								});
							}
						});
					}
					});
          });
          wx.error(function(res){
              // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，
              //也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
              layer.msg(res);
          });
		} ],
		callbackError : [ function(data, xhrArgs) {
			baseTools.hideMash(); //关闭遮罩
		} ]
	});
}