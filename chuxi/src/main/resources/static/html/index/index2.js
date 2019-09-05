var countdown = 120;
var layer;
layui.use([ 'element', 'carousel', 'form' ], function() {
	var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
	var carousel = layui.carousel;
	var form = layui.form;
	var layer = layui.layer;
	//自定义验证规则
	form.verify({
		PHONE : function(value) {
			var reg = /^(13[0-9]|14[0-9]|15[0-9]|166|17[0-9]|18[0-9]|19[8|9])\d{8}$/;
			if (!reg.test(value)) {
				return "请输入正确的手机号码";
			}
		},
		YZM : function(value) {
			var reg = /^\d{4}$/;
			if (!reg.test(value)) {
				return "请输入4位数字验证码";
			}
		},
		EMAIL : function(value) {
			var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
			if (!reg.test(value)) {
				return "请输入正确的邮箱地址";
			}
		},

		IDCARD : function(value) {
			var reg = /^((\d{18})|([0-9x]{18})|([0-9X]{18}))$/;
			if (!reg.test(value)) {
				return "请输入正确的身份证号";
			}
		}
	});
	//监听提交
	form.on('submit(smsForm)', function(data) {
		doSaveSmsForm();
		return false;
	});
	form.on('submit(form)', function(data) {
		layer.alert(JSON.stringify(data.field), {
			title : '录入信息'
		})
		return false;
	});

	//常规轮播
	carousel.render({
		elem : '#test1',
		arrow : 'always',
		width : '100%',
		height : '500px',
		interval : 10000
	});
	init();
});

function init() {
	/*//首先获取导航栏距离浏览器顶部的高度
	var top = $('.layui-nav').offset().top;
	//开始监控滚动栏scroll
	$(document).scroll(function(){
	//获取当前滚动栏scroll的高度并赋值
	var scrTop = $(window).scrollTop();
	//开始判断如果导航栏距离顶部的高度等于当前滚动栏的高度则开启悬浮
	if(scrTop >= top){
	$('.layui-nav').css({'position':'fixed','top':'0','width':'100%'});
	}else{//否则清空悬浮
	$('.layui-nav').css({'position':'','top':''});
	}
	});
	*/

	window.onscroll = function() {
		if (document.body.scrollTop || document.documentElement.scrollTop > 0) {
			document.getElementById('rTop').style.display = "block"
		} else {
			document.getElementById('rTop').style.display = "none"
		}
	};
	// 百度地图API功能
	var map = new BMap.Map("allmap"); // 创建Map实例
	var point = new BMap.Point(113.604517, 34.777567); //定义一个中心点坐标

	map.centerAndZoom(point, 12); //设定地图的中心点和坐标并将地图显示在地图容器中
	map.setCurrentCity("郑州市"); // 设置地图显示的城市 此项是必须设置的
	map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放
	//创建检索信息窗口对象
	var content = '<div style="margin:0;line-height:20px;padding:2px;">' +
		'<img src="./head.jpg" alt="" style="float:right;zoom:1;overflow:hidden;width:100px;height:100px;margin-left:3px;"/>' +
		'地址：世纪香熙园9号楼1单元2506<br/>电话：13683816984<br/>简介：除夕网络专业网站开发、公众号开发、小程序开发。' +
		'</div>';
	var searchInfoWindow = null;
	searchInfoWindow = new BMapLib.SearchInfoWindow(map, content, {
		title : "除夕网络", //标题
		width : 290, //宽度
		height : 105, //高度
		panel : "panel", //检索结果面板
		enableAutoPan : true, //自动平移
		enableMessage : false, //设置允许信息窗发送短息
		searchTypes : [
			//BMAPLIB_TAB_TO_HERE, //到这里去
			//BMAPLIB_TAB_FROM_HERE, //从这里出发
			//BMAPLIB_TAB_SEARCH, //周边检索
		]
	});
	var marker = new BMap.Marker(point);
	map.addOverlay(marker); // 将标注添加到地图中
	var label = new BMap.Label("除夕网络", {
		offset : new BMap.Size(18, 0)
	});
	label.addEventListener("click", function(e) {
		searchInfoWindow.open(point);
	});
	marker.setLabel(label);
	marker.addEventListener("click", function(e) {
		searchInfoWindow.open(point);
	});
	searchInfoWindow.open(point);
}



//获取验证码
function getCode() {
	var phone = $("#PHONE").val()
	var reg = /^[1][3,4,5,7,8][0-9]{9}$/;
	if (!reg.test(phone)) {
		mui.alert("请输入正确的手机号");
		return;
	}
	settime(); //按钮倒计时
	//调用服务发送验证码
	baseTools.xhrAjax({
		url : "../../sms/getCode",
		async : false,
		dataType : 'json',
		contentType : 'application/json',
		params : JSON.stringify({
			PHONE : phone,
			EXPIRE_COUNT : "600",
			SIGN_NAME : '除夕网络',
			TPL_NAME : 'SMS_173341302'
		}),
		callback : [ function(jsonObj, xhrArgs) {
			switch (parseInt(jsonObj.code)) {
			case -1: //异常
				layer.alert(jsonObj.msg, {
					icon : 5
				});
				break;
			case -3: //cookie 失效请重新登录
				layer.alert(jsonObj.msg, {
					icon : 5
				}, function(index) {
					baseTools.gotoLogin(); //去登录
					layer.close(index);
				});
				break;
			default:
				//成功
				layer.alert('验证码已经发送到手机，请注意查收', {
					icon : 6
				});

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
		$("#YZMBUTTON").removeClass("layui-btn-disabled");
		countdown = 120;
		return;
	} else {
		$("#YZMBUTTON").prop('disabled', "true");
		$("#YZMBUTTON").html(countdown + "秒后重发");
		$("#YZMBUTTON").addClass("layui-btn-disabled");
		countdown--;
	}
	setTimeout(function() {
		settime()
	}, 1000);
}
//短信保存
function doSaveSmsForm() {
	baseTools.xhrAjax({
		url : "../../sms/checkCode",
		async : false,
		dataType : 'json',
		contentType : 'application/json',
		params : JSON.stringify({
			PHONE : $("#PHONE").val(),
			YZM : $("#YZM").val()
		}),
		callback : [ function(jsonObj, xhrArgs) {
			switch (parseInt(jsonObj.code)) {
			case -1: //异常
				layer.alert(jsonObj.msg, {
					icon : 5
				});
				break;
			case -3: //cookie 失效请重新登录
				layer.alert(jsonObj.msg, {
					icon : 5
				}, function(index) {
					baseTools.gotoLogin(); //去登录
					layer.close(index);
				});
				break;
			default:
				//成功
				layer.alert('验证码输入正确！', {
					icon : 6
				});

			}
		} ],
		callbackError : [ function(data, xhrArgs) {
			baseTools.hideMash(); //关闭遮罩
		} ]
	});
}
//回到顶部
function toTop() {
	window.scrollTo('0', '0');
	document.getElementById('rTop').style.display = "none"
}
//弹出框
function openAlert() {
	layer.alert('你好呀');
}
//弹出框
function openAlertIcon() {
	layer.alert('你好呀', {
		icon : 1
	}, function() {
		layer.alert('你好呀', {
			icon : 2
		}, function() {
			layer.alert('你好呀', {
				icon : 3
			}, function() {
				layer.alert('你好呀', {
					icon : 4
				}, function() {
					layer.alert('你好呀', {
						icon : 5
					}, function() {
						layer.alert('你好呀', {
							icon : 6
						}, function(index) {
							layer.close(index);
						});
					});
				});
			});
		});
	});
}

function openAlertTitle(){
	layer.alert('你好呀', {
		icon : 6,
		titile:"自定义标题"
	}, function(index) {
		layer.close(index);
	});
}