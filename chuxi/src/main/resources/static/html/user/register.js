var form;
var layer;
layui.use([ 'element', 'form', 'layer' ], function() {
	form = layui.form;
	layer = layui.layer;
	//自定义验证规则
	form.verify({
		NAME : function(value) {
			if (value.length < 1) {
				return '昵称不能为空';
			}
		},
		PHONE : function(value) {
			if (value.length < 1) {
				return '手机号不能为空';
			}
			var reg = /^[1][3,4,5,7,8][0-9]{9}$/;
			if (!reg.test(value)) {
				return "请输入正确的手机号";
			}
		},
		YZM : function(value) {
			if (value.length < 1) {
				return '请输入验证码';
			}
			var reg = /^\d{4,4}$/;
			if (!reg.test(value)) {
				return '请输入4位数字验证码';
			}
		}
	});
	//监听提交
	form.on('submit(saveform)', function() {
		formData.SEX = $("input[name='SEX']:checked").val();
		formData.USER_TYPE = $("input[name='USER_TYPE']:checked").val();
		vueApp.doSave();
		return false;
	});
	form.on('select(PROVINCE)', function(data){   
		  getCity(data.value);
	});
	init();
});

//初始化
function init() {
	
	var OPENID = baseTools.getUrlParam("openid");
	if(OPENID==null){
			$("body").html("请在微信内打开网页");
			return ;
	}
	//查询省级数据
	baseTools.xhrAjax({
		url : "../../dm/selectDM_PROVINCE",
		async : false,
		dataType : 'json',
		contentType : 'application/json',
		params : JSON.stringify({
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
				//表单初始赋值
				var html="";
				for(var i=0;i<jsonObj.data.length;i++){
					html+="<option value=\""+jsonObj.data[i].NAME+"\">"+jsonObj.data[i].NAME+"</option>";
				}
				$("#PROVINCE").html(html);
				form.render();
			}
		} ],
		callbackError : [ function(data, xhrArgs) {
			baseTools.hideMash(); //关闭遮罩
		} ]
	});
	
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
				layer.alert("异常:" + jsonObj.msg, {'icon' : 5});
				break;
			case -3: //cookie 失效请重新登录
				layer.alert(jsonObj.msg, {'icon' : 5});
				baseTools.gotoLogin();//去登录
				break;
			default:
				  getCity(jsonObj.data[0].PROVINCE);
				  //表单初始赋值
				  form.val('userInfoForm', {
				    "NAME": jsonObj.data[0].NICKNAME,
				    "SEX": jsonObj.data[0].SEX,
				    "CITY": jsonObj.data[0].CITY,
				    "PROVINCE": jsonObj.data[0].PROVINCE,
				    "COUNTRY": jsonObj.data[0].COUNTRY,
				    
				  });
				  $("#HEADIMGURL").attr("src",jsonObj.data[0].HEADIMGURL);
				   
			}
		} ],
		callbackError : [ function(data, xhrArgs) {
			baseTools.hideMash(); //关闭遮罩
		} ]
	});
	
}
//获取市数据
function getCity(PROVINCE){
	if(PROVINCE==null||PROVINCE==""){
		return;
	}
	//查询市级数据
	baseTools.xhrAjax({
		url : "../../dm/selectDM_CITY",
		async : false,
		dataType : 'json',
		contentType : 'application/json',
		params : JSON.stringify({
			PROVINCE_NAME:PROVINCE
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
				//表单初始赋值
				var html="";
				for(var i=0;i<jsonObj.data.length;i++){
					html+="<option value=\""+jsonObj.data[i].NAME+"\">"+jsonObj.data[i].NAME+"</option>";
				}
				$("#CITY").html(html);
				form.render();
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
                debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
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