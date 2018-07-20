<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>智联招聘报销费用管理系统</title>
<link href="${resource_path}/css/base.css" rel="stylesheet">
<link href="${resource_path}/css/platform.css" rel="stylesheet">
<link rel="stylesheet" href="${resource_path}/custom/uimaker/easyui.css">
<script type="text/javascript">
	var mainServer = '${mainServer}';
</script>
<style type="text/css"> 
.ftx04{ color:#F00;padding-right: 5px;}
</style>
</head> 
<body>
    <div class="container">
        <div id="pf-hd">
            <div class="pf-logo">
                <img src="${imgFileServer}/main/main_logo.png" alt="logo">
            </div>
            <div class="pf-nav-wrap"><div class="pf-nav-ww"></div> </div>

            <div class="pf-user">
                <div class="pf-user-photo">
                    <img src="${imgFileServer}/main/user.png" alt="">
                </div>
                <h4 class="pf-user-name ellipsis">${username}</h4>
                <a href="${mainServer}/logout" class="easyui-linkbutton" style="margin-top:15px;"  data-options="selected:true">退出</a>

                <%-- <div class="pf-user-panel">
                    <ul class="pf-user-opt">
                        <li>
                            <a href="javascript:void(0);" onclick="userInfo()">
                                <i class="iconfont">&#xe60d;</i>
                                <span class="pf-opt-name">用户信息</span>
                            </a>
                        </li>
                        <li class="pf-modify-pwd">
                            <a href="javascript:void(0);" onclick="modify()">
                                <i class="iconfont">&#xe634;</i>
                                <span class="pf-opt-name">修改密码</span>
                            </a>
                        </li>
                        <li class="pf-logout">
                            <a href="${mainServer}/admin/logout">
                                <i class="iconfont">&#xe60e;</i>
                                <span class="pf-opt-name">退出</span>
                            </a>
                        </li>
                    </ul>
                </div> --%>
            </div>
        </div>
		
		<div id="dlg" class="easyui-dialog" title="修改密码" data-options="closed:true,modal:true" style="width:500px;height:195px;padding:10px;">
        	<form action="updatePwd" id="password_info" method="post">
			      	<table class="kv-table">
						<tbody>
							<tr>
								<td class="kv-label"><b class="ftx04">*</b>旧密码:</td>
								<td class="kv-content" colspan="1"> 
									<input type="password" id="oldPassword" name="oldPassword"  style = "width:95%;height:20px;" autocomplete="off">
							    </td>
							</tr>
							
							<tr>
								<td class="kv-label"><b class="ftx04">*</b>新密码:</td>
								<td class="kv-content" colspan="1"> 
									<input type="password" id="password" name="password"  style = "width:95%;height:20px;" autocomplete="off">
							    </td>
							</tr>
							
							<tr>
								<td class="kv-content" colspan="2">
									<div style="padding:2px" align="center">
		    	                        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">确定</a>
		    	                        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="cancelForm()">取消</a>
		                            </div>
		                         </td>
							</tr>
							</tbody>
					</table>
					
			</form>
		
     	</div>
     	
     	<div id="userdlg" class="easyui-dialog" title="用户信息" data-options="closed:true,modal:true" style="width:750px;height:200px;padding:10px;">
        	<form id="user_info" >
			      <table class="kv-table">
						<tbody>
							<tr>
								<td class="kv-label">员工用户名</td>
								<td class="kv-content">
									<input  type="text" name="userName" id="userName" style="height:35px;width:90%;" disabled="true"/>
								</td>
								<td class="kv-label">创建时间</td>
								<td class="kv-content">
									<input  type="text" name="createTime" id="createTime" style="height:35px;width:90%;" disabled="true"/>
								</td>
							</tr>
							<tr>
								<td class="kv-label">手机号码</td>
								<td class="kv-content">
									<input  type="text" name="mobile" id="mobile" style="height:35px;width:90%;" disabled="true"/>
								</td>
								<td class="kv-label">性别</td>
								<td class="kv-content">
								<select class="easyui-combobox" style="width:92%;height: 40px;" name="sex" id="sex" data-options="editable:false,required:true" disabled="true">
		                            <option value="0" selected="selected">男</option>
		                            <option value="1">女</option>
	                        	</select>
								</td>
							</tr>
						</tbody>
					</table>
					
			</form>
		
    	 </div>
		
        <div id="pf-bd">
            <div id="pf-sider">
                <h2 class="pf-model-name">
                    <span class="iconfont">&#xe64a;</span>
                    <span class="pf-name"></span>
                    <span class="toggle-icon"></span>
                </h2>
                  
                  <c:forEach items="${menus}" var="menuMap" varStatus="status">
                     <ul class="sider-nav">
                     <c:choose>
                       <c:when test="${status.index==0 }">
                         <li class="current">
	                        <a href="javascript:;">
	                            <span class="iconfont sider-nav-icon">&#xe620;</span>
	                            <span class="sider-nav-title"> ${menuMap.key }  </span>
	                            <i class="iconfont">&#xe642;</i>
	                        </a>
	                        <ul class="sider-nav-s">
	                           <c:forEach items="${menuMap.value}" var="menu" varStatus="status2">
	                               <c:if test="${status2.index==0 }">
	                                  <li class="active" url="${menu.link }" onclick="addTab(this)" title="${menu.name }"><a href="javascript:void(0);">${menu.name }</a></li>
	                               </c:if>
	                               <c:if test="${status2.index !=0 }">
	                                  <li url="${menu.link }" onclick="addTab(this)" title="${menu.name }"><a href="javascript:void(0);">${menu.name }</a></li>
	                               </c:if>
	                           </c:forEach>
	                        </ul>
	                     </li>
                       </c:when>
                       <c:otherwise>
	                         <li>
		                        <a href="javascript:;">
		                            <span class="iconfont sider-nav-icon">&#xe620;</span>
		                            <span class="sider-nav-title"> ${menuMap.key }</span>
		                            <i class="iconfont">&#xe642;</i>
		                        </a>
		                        <ul class="sider-nav-s">
		                           <c:forEach items="${menuMap.value}" var="menu" varStatus="status2">
		                             <c:if test="${status2.index==0 }">
	                                  <li url="${menu.link }" onclick="addTab(this)" title="${menu.name }"><a href="javascript:void(0);">${menu.name }</a></li>
	                               </c:if>
	                               <c:if test="${status2.index !=0 }">
	                                  <li url="${menu.link }" onclick="addTab(this)" title="${menu.name }"><a href="javascript:void(0);">${menu.name }</a></li>
	                               </c:if>
		                           </c:forEach>
		                        </ul>
		                     </li>
                       </c:otherwise>
                     </c:choose>
                     </ul>
                  </c:forEach> 
            </div>

            <div id="pf-page">
                <div class="easyui-tabs1" style="width:100%;height:100%;">
                  <div title="首页" style="padding:10px 5px 5px 10px;">
                    <c:if test="${roleid==2 }">
                      <iframe class="page-iframe" src="${mainServer }/admin/order/report" frameborder="no" border="no" height="100%" width="100%" scrolling="auto"></iframe>
                    </c:if>
                    <c:if test="${roleid!=2 }">
                      <iframe class="page-iframe" src="toindex" frameborder="no" border="no" height="100%" width="100%" scrolling="auto"></iframe>
                    </c:if>
                  </div>
                  
                </div>
            </div>
        </div>
        
        <div id="tabsMenu" style="width: 120px;display:none;">
			<div type="refresh">刷新</div>
			<div class="menu-sep"></div>
			<div type="close">关闭</div>
			<div type="closeOther">关闭其他</div>
			<div type="closeAll">关闭所有</div>
		</div>

        <div id="pf-ft">
            <div class="system-name">
              <i class="iconfont">&#xe6fe;</i>
              <span>信息管理系统&nbsp;v1.0</span>
            </div>
            <div class="copyright-name">
              <span>CopyRight&nbsp;2018&nbsp;&nbsp;https://www.zhaopin.com/&nbsp;版权所有</span>
              <i class="iconfont">&#xe6ff;</i>
            </div>
        </div>
    </div>

    <script type="text/javascript" src="${resource_path}/custom/jquery.min.js"></script>
    <script type="text/javascript" src="${resource_path}/custom/jquery.easyui.min.js"></script>
    <!-- <script type="text/javascript" src="js/menu.js"></script> -->
    <script type="text/javascript" src="${resource_path}/js/main.js"></script>
    <script type="text/javascript" src="${resource_path}/js/jquery.md5.js"></script>
    <!--[if IE 7]>
      <script type="text/javascript">
        $(window).resize(function(){
          $('#pf-bd').height($(window).height()-76);
        }).resize();
        
      </script>
    <![endif]-->  

    
    <script type="text/javascript">
    
    function modify() {
    	$('#password_info').form('clear');
    	$('#dlg').dialog('open');
    }
    function userInfo() {
    	$('#user_info').form('clear');
    	$('#userdlg').dialog('open');
		$.ajax({
 			   type: 'POST',
 			   url: "${mainServer}/admin/index/querystaffbyid",
			    dataType:'json',  
			    success:function(data) { 
		        	var staffInfo=data.data;
		        	$("#userName").val(staffInfo.loginname);
		        	$("#mobile").val(staffInfo.mobile);
		        	$("#sex").combobox('setValue', staffInfo.sex);
		        	$('#createTime').val(staffInfo.createTimeStr);
 			     },  
 			     error : function() {  
 			          alert("异常！");  
 			     } 
 		   });
    }
    function cancelForm() {
    	$('#dlg').dialog('close');
    }
    function submitForm(){
    	
    	var oldPassword=$("#oldPassword").val();
    	var password=$("#password").val();
    	if(oldPassword==''){
    		$.messager.alert('提示消息','当前密码不能为空','error');
    		return ;
    	}
    	
    	if(password==''){
    		$.messager.alert('提示消息','新密码不能为空','error');
    		return ;
    	}
    	
    	
    	if(oldPassword==password){
    		$.messager.alert('提示消息','当前密码与新密码相同','error');
    		return ;
    	}
    	
    	var password = $.md5(password);
    	var oldPassword = $.md5(oldPassword);
    	
    	
    	$.ajax({
			   type: 'POST',
			   url: "${mainServer}/admin/index/updatepwd",
			   dataType:'json', 
			   data:{
				   oldPassword:oldPassword,
				   password:password
			   },
			   success:function(data) { 
		        	if(data.code==1010){
		        		$.messager.alert('提示消息','修改密码成功','info');
		        		//cancelForm();
		        	}else{
		        		$.messager.alert('提示消息',data.data,'error');
		        	}
			     },  
			   error : function() {  
			          alert("异常！");  
			     } 
		   });
    	
    	
    	
    	
		/* $('#password_info').form('submit',{
			url:'${mainServer}/admin/index/updatePwd',  
			ajax : true,
		    onSubmit: function(){    
		    	 return $(this).form('validate');
		    },    
		    success:function(data){
		    	data = typeof data == 'object' ? data : $.parseJSON(data);
		    	if(data.result != -1){
	    			$.messager.alert('提示消息',data.msg,'info');
	    			$("#dlg").dialog("close");
		    	}else{
		    		$.messager.alert('提示消息',data.msg,'error');
		    	}
		    }
		}); */
    }
    
    
    $(window).resize(function(){
          $('.tabs-panels').height($("#pf-page").height()-46);
          $('.panel-body').height($("#pf-page").height()-76)
    }).resize();

    var page = 0,
        pages = ($('.pf-nav').height() / 70) - 1;

    if(pages === 0){
      $('.pf-nav-prev,.pf-nav-next').hide();
    }
    
    $(document).on('click', '.pf-nav-prev,.pf-nav-next', function(){
      if($(this).hasClass('disabled')) return;
      if($(this).hasClass('pf-nav-next')){
        page++;
        $('.pf-nav').stop().animate({'margin-top': -70*page}, 200);
        if(page == pages){
          $(this).addClass('disabled');
          $('.pf-nav-prev').removeClass('disabled');
        }else{
          $('.pf-nav-prev').removeClass('disabled');
        }
        
      }else{
        page--;
        $('.pf-nav').stop().animate({'margin-top': -70*page}, 200);
        if(page == 0){
          $(this).addClass('disabled');
          $('.pf-nav-next').removeClass('disabled');
        }else{
          $('.pf-nav-next').removeClass('disabled');
        }
        
      }
    });

    </script>
</body> 
</html>