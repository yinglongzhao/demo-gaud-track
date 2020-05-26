var marker,lineArr;
var polyline,passedPolyline;
var markers=[];
$(function(){
	
	var json_data,json_info;
	var queryType = -1;
    var geolocation;
    
    // 初始化地图
	function init(){
        //加载地图，调用浏览器定位服务
        map.plugin('AMap.Geolocation', function() {
        geolocation = new AMap.Geolocation({
            enableHighAccuracy: true,//是否使用高精度定位，默认:true
            timeout: 10000,          //超过10秒后停止定位，默认：无穷大
            buttonPosition: 'RB',    //定位按钮停靠位置，默认：'RB'，左下角
            zoomToAccuracy: false    //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
        });
        map.addControl(geolocation);
        geolocation.getCurrentPosition();

        AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
        AMap.event.addListener(geolocation, 'error', onError);      //返回定位出错信息

        //解析定位结果
        function onComplete(data) {
            console.log("当前坐标：" + "[ "+data.position.lng +","+data.position.lat+" ]"); 
        }
        //解析定位错误信息
        function onError(data) {
            //alert("定位失败！" + data.info);
        }
        });
    }
    init(); 

    function setCity(){

    	AMap.event.addDomListener(document.getElementById('query'),'click',function(){
        var cityName = document.getElementById('city').value;       
        if (!cityName) {
            cityName = '北京';
        }
        AMap.event.removeListener(geolocation);
        map.setCity(cityName);
    	});
  
    };
    setCity();
      
    // 数据通信  
	function submitData(){
        $("#j-refresh").click(function(){
                map.clearMap();
                //alert(queryType); 
                if($('#vehicel option:selected').val() == "all"){
                   queryType = "1";
                   $("#queryType").val("1");
                   //alert( $("#queryType").val());
                }else if($('#vehicel option:selected').val() == "one" && $('#show option:selected').val() == "loc"){
                    queryType = "2"; 
                    $("#queryType").val("2");
                }else if($('#vehicel option:selected').val() == "one" && $('#show option:selected').val() == "trace"){
                    queryType = "3"; 
                    $("#queryType").val("3");
                }else{
                    queryType = -1;
                }
             $.ajax({
                   
                    type:"POST",
                    url: "MobileServlet",       
                    dataType:"json",
                    data:{
                        queryType: $("#queryType").val(),
                        carid:$("#num").val(), //识别号
                        start_time:$("#start").val(), //初始时间
                        end_time:$("#end").val() //结束时间
                    },
                   
                    success:function(data,textStatus){
                        json_data = data;
                        switch(queryType) {
                            case "1":
                                allVeh(json_data);
                                break;
                            case "2":
                                allVeh(json_data);
                                break;
                            case "3":
                                routePlan(json_data);
                                break;
                            default:
                                map.clearMap();
                        }                                 
                    },
                    error: function(data,textStatus){
                        console.log(data);
                        console.log(textStatus);
                    }                  
                  });            
            });
    }
    submitData();

    // 全部车辆
    function allVeh(json_data){
        //getData(json_data);
        var data = json_data;
       
        if($('#vehicel option:selected').val() == "all" || $('#vehicel option:selected').val() == "one"){
        //filterAll(); 
        // 位置筛选，分为三种等级
        $('#j-confirm').click(function(){
                switch($('#rank option:selected').val()) {
                    case "allto": return filterAll();
                        break;
                    case "gps": return filterGPS(); 
                        break;
                    case "cell": return filterCell();
                        break;
                    case "lgu": return filterGgu();
                        break;
                    default: return false;
                }

                function filterAll(){
                    map.clearMap();  // 清除地图覆盖物
                    var markers = data.forEach(function(marker){
                    if(marker.state == "1" || marker.state == "2"){
                        var mark1 = new AMap.Marker({
                        map: map,
                        icon: "web/assets/images/gps.gif",
                        position: [marker.GPS_longitude,marker.GPS_latitude],
                        offset:new AMap.Pixel(-10,6)
                       });
                        writeLngLat(mark1,marker); 
                    }
                    if(marker.state == "3"){
                        var mark2 = new AMap.Marker({
                        map: map,
                        icon: "web/assets/images/cell.gif",
                        position: [marker.GPS_longitude,marker.GPS_latitude],
                        offset:new AMap.Pixel(-10,6)
                       });
                        writeLngLat(mark2,marker); 
                    }
                    if(marker.state == "4"){
                        var mark3 = new AMap.Marker({
                        map: map,
                        icon: "web/assets/images/lgu.gif",
                        position: [marker.GPS_longitude,marker.GPS_latitude],
                        offset:new AMap.Pixel(-10,6)
                       }); 
                       writeLngLat(mark3,marker);
                    } 
                  });     
                }

                function filterGPS(){
                    var markers = data.filter(function(marker){
                    // 根据请求的识别码过滤数组
                    if(marker.state == "1" || marker.state == "2") 
                        return marker;
                    });

                    map.clearMap();
                    markers = markers.forEach(function(marker){
                        var mark= new AMap.Marker({
                            map: map,
                            icon: "web/assets/images/gps.gif",
                            position: [marker.GPS_longitude,marker.GPS_latitude],
                            offset:new AMap.Pixel(-10,6)
                        });
                    	writeLngLat(mark,marker);
                    });
                }

                function filterCell(){
                    var markers = data.filter(function(marker){
                    // 根据请求的识别码过滤数组
                    if(marker.state == "3") 
                        return marker;
                    });

                    map.clearMap();
                    markers = markers.forEach(function(marker){
                        var mark=new AMap.Marker({
                            map: map,
                            icon: "web/assets/images/cell.gif",
                            position: [marker.GPS_longitude,marker.GPS_latitude],
                            offset:new AMap.Pixel(-10,6)
                        });
                    	writeLngLat(mark,marker);
                    });
                }

                function filterGgu(){
                    var markers = data.filter(function(marker){
                    // 根据请求的识别码过滤数组
                    if(marker.state == "4") 
                        return marker;
                    });

                    map.clearMap();
                    markers = markers.forEach(function(marker){
                        var mark=new AMap.Marker({
                            map: map,
                            icon: "web/assets/images/lgu.gif",
                            position: [marker.GPS_longitude,marker.GPS_latitude],
                            offset:new AMap.Pixel(-10,6)
                        });
                    	writeLngLat(mark,marker);
                    });
                }   
            });
        }
    }
    
    // 路径规划，实现轨迹回放
    function routePlan(json_data){
            //getData(json_data);
            var routes = json_data;
            var points =[];
            $.each(routes,function(index,item){
                if(item.GPS_longitude != "" && item.GPS_latitude != "" ){
                    points.push(new AMap.LngLat(item.GPS_longitude,item.GPS_latitude));
                }
            });
            //alert(points);       
            $('#j-confirm').click(function(){
                if($('#show option:selected').val() == "trace"){
                    map.clearMap();
                    driving(points); //路径规划
                    //traceBack(points);  //轨迹回放
                   
                function driving(data){
                 
                    $.each(data,function(index){
                        var start = new AMap.Marker({
                            map: map,
                            position: data[0],
                            icon: "web/assets/images/start.gif",
                            offset:new AMap.Pixel(-30,-60)
                        });
                        var end = new AMap.Marker({
                            map: map,
                            position: data[data.length - 1],
                            icon: "web/assets/images/end.gif",
                            offset:new AMap.Pixel(-30,-60)
                        });
                    	writeLngLat(start,routes[0]);
                        writeLngLat(end,routes[data.length - 1]);
                   });

                    //驾车导航模块
                    AMap.plugin(["AMap.Driving"], function () {
                        drivingOption = {
                            policy: AMap.DrivingPolicy.LEAST_TIME,
                            map: map,
                            hideMarkers: true,
                            showTraffic: false
                        };

                        $.each(data, function (index) {
                            var driving = new AMap.Driving(drivingOption); //构造驾车导航类
                            driving.search(data[index], data[index + 1]);
                        });
                    });
                }            
            }
        });       
    }

	// 写入当前经纬度
	function writeLngLat(marker,info){
	// 当鼠标过渡到某一辆车前面，显示该车的经纬度，显示该车的车辆信息
		marker.content="车牌号："+ info.devID +"<br>"+"位置：" + info.GPS_longitude+","+info.GPS_latitude;
    	//给Marker绑定单击事件
   		marker.on('mouseover', markerOver);
   		marker.on('click', markerClick);
    	var infoWindow = new AMap.InfoWindow();
		function markerOver(e){
    		infoWindow.setContent(e.target.content);
    		infoWindow.open(map, e.target.getPosition());
		}
		function markerClick(e){
			document.getElementById('r-lng').value = e.target.getPosition().lng;
			document.getElementById('r-lat').value = e.target.getPosition().lat;
			document.getElementById('r-info').innerHTML = "车牌号: "+info.devID + "\n"+ "等级: "+info.state+"\n"+"时间: "+info.time;
		}

	}
	
	function loadguiji(arr){

		if(polyline){
			polyline.clearMap;
		}
		var line = [];
		for(var p in arr.list){
			var tmp = [];
			tmp.push(arr.list[p].jingdu);
			tmp.push(arr.list[p].weidu);
			line.push(tmp);
		}
		marker = line;
		lineArr = line;
	    marker = new AMap.Marker({
	        map: map,
	        position: line[0],
	        icon: "https://webapi.amap.com/images/car.png",
	        offset: new AMap.Pixel(-26, -13),
	        autoRotation: true,
	        angle:-90,
	    });
	    markers.push(marker)
		AMap.event.addDomListener(document.getElementById('liebiao'), 'click', function() {
			    // 设置缩放级别和中心点
			    map.setZoomAndCenter(14, line[0]);
			    // 在新中心点添加 marker 
			    var marker = new AMap.Marker({
			        map: map,
			        position: line[0]
			    });
		});
		console.log(line)
		if(line.length<2){
			alert("该路线没有移动")
		}
		//设置连接线样式 polyline passedPolyline
		 polyline = new AMap.Polyline({
			map: map,
    		path: line,          //设置线覆盖物路径
    		strokeColor: "#28F", //线颜色
            //strokeOpacity: 1,       //线透明度
            strokeWeight: 10,        //线宽
            //strokeStyle: "solid",   //线样式
            showDir:true,
            //strokeDasharray: [10, 5] //补充线样式
		});
		
	     passedPolyline = new AMap.Polyline({
	        map: map,
	        // path: lineArr,
	        strokeColor: "#AF5",  //线颜色
	        // strokeOpacity: 1,     //线透明度
	        strokeWeight: 6,      //线宽
	        // strokeStyle: "solid"  //线样式
	    });
		
		polyline.setMap(map);
	}
	
	 $("#liebiao").on("click",".luxian",function(){ 
		 if(marker){
			 marker.setMap(null);
		 }
		 console.log(markers)
		 map.remove(markers);
		 if(polyline){
				polyline.setMap(null);
		 }
		 if(passedPolyline){
				polyline.setMap(null);
		 }
		 
		 var data = canshu;
		 var luxian = $(this).attr("data");
		 loadguiji(canshu[luxian])
		 console.log(canshu[luxian]);
		 $("#sudu").text(canshu[luxian].speed + "m/s");
		 $("#lucheng").text(canshu[luxian].distance + "m");
		 $("#shijian").text(canshu[luxian].speed2 + "s");
	 })
});

marker.on('moving', function (e) {
    passedPolyline.setPath(e.passedPath);
});

map.setFitView();

function startAnimation () {
    marker.moveAlong(lineArr, 200);
}

function pauseAnimation () {
    marker.pauseMove();
}

function resumeAnimation () {
    marker.resumeMove();
}

function stopAnimation () {
    marker.stopMove();
}


	