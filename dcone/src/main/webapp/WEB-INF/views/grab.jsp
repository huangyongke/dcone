<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-3.2.1.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
body{
background-image:url("img/packetback.jpg");
background-size: cover;
background-position: 80% 25%;
}
.outter1{
    border:2px solid;
	position:absolute;
	z-index:10;
	background-image:url("img/redpacket.jpg");
	background-size:cover;
	width:500px;
	height:300px;
	top:20%;
	left:30%;
	border-color:#FAFAD2 !important;
	outline:0;
	-webkit-box-shadow:insert 0 ipx 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,223,0.6);
	-moz-box-shadow:insert 0 ipx 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,223,0.6);
	box-shadow:insert 0 ipx 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,223,0.6);
}
.outter2{
    border:2px solid;
	position:absolute;
	z-index:10;
	background-image:url("img/waitpacket.gif");
	background-size:cover;
	width:500px;
	height:300px;
	top:20%;
	left:30%;
	border-color:#FAFAD2 !important;
	outline:0;
	-webkit-box-shadow:insert 0 ipx 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,223,0.6);
	-moz-box-shadow:insert 0 ipx 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,223,0.6);
	box-shadow:insert 0 ipx 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,223,0.6);
	display: none;
}
.outter3{
	border:2px solid;
	position:absolute;
	z-index:10;
	background-image:url("img/openpacket.jpg");
	background-size:cover;
	width:300px;
	height:300px;
	top:20%;
	left:37%;
	border-color:#FAFAD2 !important;
	outline:0;
	-webkit-box-shadow:insert 0 ipx 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,223,0.6);
	-moz-box-shadow:insert 0 ipx 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,223,0.6);
	box-shadow:insert 0 ipx 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,223,0.6);
	display: none;
}
.inner{
	position:absolute;
	left:38%;
	margin-top:18%;
	font-size: 22px;
	color: #FEED4C;
}
.out{
	position:absolute;
	left:39%;
	top:15%;
	background-color: #666666;
	width: 300px;
	text-align: center;
	font-size:large;
	display: none;
}
</style>
<script>
$(document).ready(function(){
	$("#grab").click(function(){
		$("#grab").hide();
		$("#wait").show();
		setTimeout( function(){
			$.post("grabmoney",{
				round:"1"
			},
			function(data,status){
					if(data==0){
						$("#wait").hide();
						$("#grab").show();
						$(".out").html("红包已经抢完");
						$(".out").show();
						setTimeout(() => {
							$(".out").hide();
						}, 3000);
					} else if(data==3){
						$("#wait").hide();
						$("#grab").show();
						$(".out").html("抢红包还未开始");
						$(".out").show();
						setTimeout(() => {
							$(".out").hide();
						}, 3000);
					}
					else{
						$('#span1').html(data+"元");
						$("#wait").hide();
						$("#get").show();
					}
			});
		}, Math.random()*5000);
	});
	$("#get").click(function(){
		$("#get").hide();
		$("#grab").show();
	});
});

</script>
</head>
<body>
<div class="out"></div>
<div class="outter1" id="grab"></div>
<div class="outter2" id="wait"></div>
<div class="outter3" id="get">
<span id="span1" class="inner">30元</span>
</div>
</body>
</html>