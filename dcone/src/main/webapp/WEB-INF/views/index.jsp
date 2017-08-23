<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>主界面</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">  
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="css/normalize.css" />
	<link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/demo_styles.css">
    <link rel="stylesheet" href="css/demo4.css">
    <link rel="stylesheet" type="text/css" href="css/default.css">
    <script src="js/vendor/modernizr-2.6.2.min.js"></script>
	<!--[if IE]>
		<script src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
	<![endif]-->
<script type="text/javascript">
	$(document).ready(function(){
	  $("#back").click(function(){
	    $.post("signout1",{
	    	
	    },function(data,status){
	    	 window.location.href="login"; 
	    }); 
		});
	});
</script>
<style type="text/css">
/* body.dm-demo4 {
  background-image:url("img/space.jpg");
  background-size: cover;
}
 */body.dm-demo4 .overlay {
  background-image:url("img/space.jpg");
  background-size: cover;
}
body.dm-demo4 .nav {
	margin-top:100px;
}
body.dm-demo4 .nav-el {
	border-radius: 30px;
}
.backbutton{
	width:100%;
	height:5%;
	border: 2px solid;
	background-color: #4B0082;
}
button.ie{
	background-image:url("img/backclose.jpg");
	background-size:cover;
	width:3%;
	height:30px;
	margin-left: 97%;
	margin-top: 0%;
	margin-bottom: 0%
}
</style>
</head>
<body class="dm-demo4">
<div style="position:absolute; width:100%; height:100%; z-index:-1">    
	<img src="img/space.jpg" height="100%" width="100%"/>    
	</div> 
	<div class="backbutton">
	<button id="back" class="ie">&nbsp;</button>
	</div>
	<div class="htmleaf-container" >
		<header class="htmleaf-header" style="position: absolute;left: 10px">
		<h1>红包系统</h1>
		</header>
		<!-- Demo Navigation -->
        <nav class="nav clearfix">
            <button class="nav-el" id="el-topleft" data-id="ov-topleft"><div style="position:absolute; width: 200px;height:200px;"><div style="position:absolute;top: 50px;left: 50px"><h6>聊天室</h6></div><span class="icon-mail"></span></div></button>
            <button class="nav-el" id="el-topright" data-id="ov-topright"><div style="position:absolute; width: 200px;height:200px;"><div style="position:absolute;top: 50px;left: 35px"><h6>节目打赏</h6></div><span class="icon-tv"></span></div></button>
            <button class="nav-el" id="el-btmleft" data-id="ov-btmleft"><div style="position:absolute; width: 200px;height:200px;"><div style="position:absolute;top: 50px;left: 50px"><h6>抢红包</h6></div><span class="icon-banknote"></span></div></button>
            <button class="nav-el" id="el-btmright" data-id="ov-btmright"><div style="position:absolute; width: 200px;height:200px;"><div style="position:absolute;top: 50px;left: 35px"><h6>账户设置</h6></div><span class="icon-user"></span></div></button>
        </nav>

        <!-- Overlay (hidden by default)-->
        <section class="overlay" id="ov-topleft">
        	<iframe style="width: 100%;height: 100%;" src="chatbox">
           
           </iframe>
            <button class="close"><span class="mfg-cancel"></span></button>
        </section>

        <section class="overlay" id="ov-topright">
 			<iframe style="width: 100%;height: 100%" src="programmenu"></iframe>
            <button class="close"><span class="mfg-cancel"></span></button>
        </section>
        <section class="overlay" id="ov-btmleft">
            <iframe style="width: 100%;height: 100%" src="grab"></iframe>
            <button class="close"><span class="mfg-cancel"></span></button>
        </section>

        <section class="overlay" id="ov-btmright">
            <iframe style="width: 100%;height: 100%" src="accountforuser"></iframe>

            <button class="close"><span class="mfg-cancel"></span></button>
        </section>

        <div class="browser_msg">
            <p>对不起，你的浏览器不支持CSS3特性！</a></p>
        </div>
		<!-- <div class="related">
		    <h3>如果你喜欢这个插件，那么你可能也喜欢:</h3>
		    <a href="http://www.htmleaf.com/jQuery/Menu-Navigation/201504111659.html">
			  <img src="related/1.jpg" width="300" alt="jQuery和CSS3超酷3D页面切换导航菜单插件"/>
			  <h3>jQuery和CSS3超酷3D页面切换导航菜单插件</h3>
			</a>
			<a href="http://www.htmleaf.com/jQuery/Layout-Interface/201503251572.html">
			  <img src="related/2.jpg" width="300" alt="58种jQuery模拟CSS3过渡页面切换特效"/>
			  <h3>58种jQuery模拟CSS3过渡页面切换特效</h3>
			</a>
		</div> -->
	</div>
	
    <script>window.jQuery || document.write('<script src="js/vendor/jquery-1.10.2.min.js"><\/script>')</script>
    <script src="js/plugins.js"></script>
    <script src="js/demo.js"></script>
</body>
</html>