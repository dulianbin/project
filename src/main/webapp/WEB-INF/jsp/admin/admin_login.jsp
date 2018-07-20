<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>智联招聘报销费用管理系统-登录</title> 
<link rel="stylesheet" href="${resource_path}/css/base.css">
<link rel="stylesheet" href="${resource_path}/css/login/login.css?v=123">
<script type="text/javascript" src="${resource_path}/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${resource_path}/js/jquery.md5.js"></script>
<script type="text/javascript" src="${resource_path}/custom/jquery.easyui.min.js"></script>
<link href="${resource_path}/custom/uimaker/easyui.css" rel="stylesheet"/>

</head>
<body>
	<div class="login-hd">
		<div class="left-bg"></div>
		<div class="right-bg"></div>
		<div class="hd-inner">
			<span class="logo"></span>
			<!-- <span class="split"></span>  -->
			<span class="sys-name" style="color:#fff;">智联招聘报销费用管理系统</span>
		</div>
	</div>
	<div class="login-bd">
		<div class="bd-inner">
			<div class="inner-wrap">
				<div class="lg-zone">
					<div class="lg-box">
						<div class="lg-label"><h4>用户登录</h4></div>
						<div class="alert alert-error" id="alertError">
			              <i class="iconfont">&#xe62e;</i>
			              <span id="errorTip">请输入用户名</span>
			            </div>
						<form action="admin/dologin">
							<div class="lg-username input-item clearfix">
								<i class="iconfont">&#xe60d;</i>
								<input type="text" id="loginname" placeholder="账号/邮箱">
							</div>
							<div class="lg-password input-item clearfix">
								<i class="iconfont">&#xe634;</i>
								<input type="password" id="password" placeholder="请输入密码">
							</div>
							<%-- <div class="lg-check clearfix">
								<div class="input-item">
									<i class="iconfont">&#xe633;</i>
									<input type="text" id="code" placeholder="验证码">
								</div>
								<span class="check-code"><img src="${mainServer}/ImageServlet" style="cursor: pointer;width:118px;" id="safecodeimg" onclick="changeimg()"></span>
							</div> --%>
							<!-- <div class="tips clearfix">
								<label><input type="checkbox" checked="checked">记住用户名</label>
								<a href="javascript:;" class="register">立即注册</a>
								<a href="javascript:;" class="forget-pwd">忘记密码？</a>
							</div> -->
							<div class="enter">
								<a href="#" class="purchaser" id="tologin" style="margin-left: 113px;">登&nbsp;&nbsp;&nbsp;&nbsp;录</a>
							</div>
						</form>
					</div>
				</div>
				<div class="lg-poster"></div>
			</div>
		</div>
	</div>
	<div class="login-ft">
		<div class="ft-inner">
			
			<div class="address">Copyright&nbsp;©&nbsp;2018&nbsp;-&nbsp;2020&nbsp;智联招聘 &nbsp;版权所有</div>
			<div class="other-info">建议使用IE8及以上版本浏览器&nbsp;粤ICP备&nbsp;09003055号&nbsp;E-mail：dulianbin588@163.com</div>
		</div>
	</div>
	
	<script type="text/javascript">
	    var mainServer="${mainServer}";
	   
	   $(function(){
		   $("#alertError").hide();
		   $("#tologin").click(function(){
			   checklogin();
		   });
		   
		   $('#password').bind('keyup', function(event) {
				if (event.keyCode == "13") {
					checklogin();
				}
			}); 
	   });
	   
	   
	   
	   function checklogin(){
		   var loginname=$("#loginname").val();
		   var password=$("#password").val();
		  // var code=$("#code").val();
		   //var passwd = $.md5(password);
		   if(loginname == ''){
			   $("#alertError").show();
			   $("#errorTip").html("请输入用户名")
			   return ;
		   }
		   if(password == ''){
			   $("#alertError").show();
			   $("#errorTip").html("请输入密码");
			   return ;
		   }
		   /* if(code == ''){
			   $("#alertError").show();
			   $("#errorTip").html("请输入验证码");
			   return ;
		   } */
		   $.messager.progress(); 
		    $.ajax({
			   type: 'POST',
			   url: "${mainServer}/checklogin",
			   data: {
			    	loginname:loginname,
			    	password:password
			    } ,
			    dataType:'json',  
			    success:function(data) { 
			    	$.messager.progress('close');
			        if(data.code ==1010 ){  
			        	$("#password").val('');
			        	window.location.href="${mainServer}/main";  
			        }else{  
			        	$("#alertError").show();
			        	$("#errorTip").html(data.errormsg);  
			        }  
			     },  
			     error : function() {  
			          alert("异常！");  
			     } 
		   });
	   }
	</script>
</body>
</html>