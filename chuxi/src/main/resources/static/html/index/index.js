var layer;
layui.use([ 'element', 'carousel', 'form' ], function() {
	var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
	var carousel = layui.carousel;
	var form = layui.form;
	var layer = layui.layer;
	//常规轮播
	/**
	carousel.render({
		elem : '#test1',
		arrow : 'always',
		width : '100%',
		height : '500px',
		interval : 10000
	});
	**/
	init();
});

function init(){
	window.onscroll = function() {
		if (document.body.scrollTop || document.documentElement.scrollTop > 0) {
			document.getElementById('top').style.display = "block"
		} else {
			document.getElementById('top').style.display = "none"
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