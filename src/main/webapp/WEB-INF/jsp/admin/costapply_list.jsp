<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>财务费用管理-费用申请</title>
<link href="${resource_path}/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="${resource_path}/custom/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="${resource_path}/custom/uimaker/icon.css">

<script type="text/javascript">
	var mainServer = '${mainServer}';
</script>
</head>
<body>
<body>
      <table id="businessActivitiesGrid" style="width:100%;height:529px;" title="">
      
      </table>
      
     <div id="businessActivitiesToolbar" style="padding:0 30px;">
       <div class="conditions">
          
           <span class="con-span" style="margin-right: 30px;">姓名  </span><input  type="text" name="search" id="search" placeholder="请输入..." style="width:166px;height:31px;line-height:31px;border:1px solid #aaa;" ></input>
           <a href="#" class="easyui-linkbutton" iconCls="icon-search"  data-options="selected:true" onclick="queryCouponManager()" >查询</a>
           
           
           
          <div class="opt-buttons" style="margin-bottom:2px;">
	            <a href="javascript:void(0)" id="addConfBtn" class="easyui-linkbutton"  iconCls="icon-add"  data-options="selected:true" onclick="opencostApplyForm()">新增</a>
	      </div>
	      <div style="float:right;margin-top:-37px;">
		      <a class="easyui-linkbutton exce-btn" onclick="excelReportActivitiesList()" href="javascript:void();" iconCls="icon-print">导出Excel</a>
		  </div>
		  <div style="clear: both;"></div>
        </div>
      </div>
        
       <div id="couponManager" class="easyui-dialog" title="费用申请" data-options="closed:true,modal:true" style="width:800px;height:440px;padding:5px;">
		  <form  id="costApplyForm" action="" method="post"  enctype="multipart/form-data">
		        <input type="hidden" id="ulaId" name="ulaId" value=""/>
		       	<table class="kv-table">
					<tbody>
					    <tr>
							
							
							<td class="kv-label">姓名</td>
							<td class="kv-content">
							     <input class="easyui-textbox" readonly name=realname style="height:35px;margin:0 0;width:80%;" value="${loginInfo.realname}"/>
							</td>
							
							<td class="kv-label">申请季度</td>
							<td class="kv-content">
							     <select name="applyQuarter" id="applyQuarter" style="height:35px;width:200px;">
										<option value="FY19-Q1" selected="selected">FY19-Q1</option>
										<option value="FY19-Q2" >FY19-Q2</option>
										<option value="FY19-Q3" >FY19-Q3</option>
										<option value="FY19-Q4" >FY19-Q4</option>
										
										<option value="FY20-Q1" >FY20-Q1</option>
										<option value="FY20-Q2" >FY20-Q2</option>
										<option value="FY20-Q3" >FY20-Q3</option>
										<option value="FY20-Q4" >FY20-Q4</option>
										
										<option value="FY21-Q1" >FY21-Q1</option>
										<option value="FY21-Q2" >FY21-Q2</option>
										<option value="FY21-Q3" >FY21-Q3</option>
										<option value="FY21-Q4" >FY21-Q4</option>
										
										<option value="FY22-Q1" >FY22-Q1</option>
										<option value="FY22-Q2" >FY22-Q2</option>
										<option value="FY22-Q3" >FY22-Q3</option>
										<option value="FY22-Q4" >FY22-Q4</option>
										
										<option value="FY23-Q1" >FY23-Q1</option>
										<option value="FY23-Q2" >FY23-Q2</option>
										<option value="FY23-Q3" >FY23-Q3</option>
										<option value="FY23-Q4" >FY23-Q4</option>
										
								 </select>
							</td>
						</tr>
						<tr>
							<td class="kv-label">区域</td>
							<td class="kv-content">
								<select name="applyArea" id="applyArea" style="height:35px;width:200px;">
										<option value="华南" selected="selected">华南</option>
								</select> 
							</td>
							<td class="kv-label">城市</td>
							<td class="kv-content">
								<select name="cityId" id="cityId" style="height:35px;width:200px;">
										<!-- <option value="1" selected="selected">深圳</option>
										<option value="2" >广州</option>
										<option value="3" >长沙</option>
										<option value="4" >福州</option>
										<option value="5" >南昌</option>
										<option value="6" >佛山</option>
										<option value="7" >厦门</option>
										<option value="8" >东莞</option>
										<option value="9">区域运营</option>
										<option value="10" >珠海</option>
										<option value="11" >海口</option> -->
										
										<c:forEach items="${cityList }" var="city">
										   <option value="${city.city_id }" >${city.city}</option>
										</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td class="kv-label">部门</td>
							<td class="kv-content">
							     <select name="deptName" id="deptName" style="height:35px;width:200px;">
										<option value="财务部" >财务部</option>
										<option value="测评事业部" >测评事业部</option>
										<option value="分公司管理" >分公司管理</option>
										<option value="行业拓展部" >行业拓展部</option>
										<option value="教育培训部" >教育培训部</option>
										<option value="人事行政部" >人事行政部</option>
										<option value="市场公关部" >市场公关部</option>
										<option value="销售部" >销售部</option>
										<option value="销售管理" >销售管理</option>
										<option value="销售运营中心" >销售运营中心</option>
										<option value="校园招聘事业部" >校园招聘事业部</option>
										<option value="业务线运营部" >业务线运营部</option>
										<option value="卓聘高端事业部" >卓聘高端事业部</option>
										
								</select>
							</td>
							
							<td class="kv-label">费用类型</td>
							<td class="kv-content">
							     <select name="costTypeName" id="costTypeName" style="height:35px;width:200px;">
										<option value="办公费" selected="selected">办公费</option>
										<option value="交通费" >交通费</option>
										<option value="业务招待费" >业务招待费</option>
										<option value="差旅费" >差旅费</option>
										<option value="会议费" >会议费</option>
										<option value="电话费" >电话费</option>
										<option value="培训费" >培训费</option>
										<option value="房租费" >房租费</option>
										<option value="物业费">物业费</option>
										<option value="水电费" >水电费</option>
										<option value="招聘费" >招聘费</option>
										<option value="快递费" >快递费</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="kv-label" ><span id="couponTypeLabel">预算金额</span></td>
							<td class="kv-content">
								<input  name="totalFee" id="totalFee" class="easyui-numberbox" precision="2" style="height:35px;width:200px;"/>
							</td>
							<td class="kv-label">实际使用金额</td>
							<td class="kv-content">
								<input  name="realFee" id="realFee" class="easyui-numberbox" precision="2" style="height:35px;width:200px;"/>
							</td>
						</tr>
						
						<tr>
							<td class="kv-label" ><span id="couponTypeLabel">备注</span></td>
							<td class="kv-content" colspan="3">
								<textarea rows="4" cols="50"  name="remark" id="remark"></textarea>
							</td>
							
						</tr>
						
							
						<tr>
							<td colspan="4">
								<div align="center" style="margin-top: 50px;">
									<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" data-options="selected:true" 
									   onclick="saveForm()" id="add_Ptype" >保存</a>
								</div>
							</td>
						</tr>
					</tbody>
				</table> 
			</form>	
	   </div>
     
    <script type="text/javascript" src="${resource_path}/custom/jquery.min.js"></script>
	<script type="text/javascript" src="${resource_path}/custom/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${resource_path}/custom/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${resource_path}/js/ux/toolbar.js"></script>
	<script type="text/javascript" src="${resource_path}/datePicker/WdatePicker.js"></script>
    
    <script type="text/javascript">
    	var mainServer = '${mainserver}';
    	
    	var boxDataGrid;
	    $(function(){
	    	boxDataGrid = SysconfigGrid.createGrid('businessActivitiesGrid');
	    });	
	    
	    function queryCouponManager(){
	    	$('#businessActivitiesGrid').datagrid('load', { 
	    		"realName":$("#search").val(),
	    	});
	    	
		} 
	    

	    
	    function opencostApplyForm(){
	    	//$("#costApplyForm").form("clear");
	    	//$("#status").select();
	    	$("#couponManager").dialog("open");
	    }
	    
	    function updateCouponManagerDlg(){
	    	var selectedRow = $('#businessActivitiesGrid').datagrid("getSelected");
	    	
	    	$.ajax({
		        type: "POST",
		        url: "queryumbrellabyid",
		        data:{
		        	umbrellaId:selectedRow.ulaId
		        },
		        async: false,
		        dataType: "json",
		        success: function(data) { 
		        	var obj=eval(data);
		        	
		        	if(obj.code==1010){  
		        		$("#ulaCode").textbox("setValue",obj.data.ulaCode);
		        		$("#ulaId").val(obj.data.ulaId);
		        		$("#ulaPrice").numberbox("setValue",obj.data.ulaPrice);
		        		$("#longitude").numberbox("setValue",obj.data.longitude);
		        		$("#latitude").numberbox("setValue",obj.data.latitude);
		        		$("#address").textbox("setValue",obj.data.address);
		        		$("#couponManager").dialog("open");
		        	}else{
		        		$.messager.alert('提示消息',data.data,'error');
		        	}
		        	
		        },
		        error: function(XMLHttpRequest, textStatus, errorThrown) {
		        }
		    });
	    }
	    
	    function saveForm(){
	    	
	    	//var ulaCode=$("#totalFee").textbox("getValue");
	    	
	    	var totalFee=$("#totalFee").numberbox("getValue");
	    	var realFee=$("#realFee").numberbox("getValue");
	    	
	    	
    		//判断是否选择了商户
    		if(totalFee==''){
    			$.messager.alert('提示消息','请填写预算金额','info');
    			return ;
    		}
    		
    		if(realFee==''){
    			$.messager.alert('提示消息','请填写实际使用金额','info');
    			return ;
    		}
    
    		$("#costApplyForm").form("submit",{
    			url:'${mainServer}/admin/costApply/saveCostApply',  
    			ajax : true,
    		    onSubmit: function(){    
    		    	
    		    },    
    		    success:function(data){
    		    	data = typeof data == 'object' ? data : $.parseJSON(data);
    		    	
    		    	if(data.code==1010){
    		    		queryCouponManager();
    		    		$("#couponManager").dialog("close");
    		    	}else{
    		    		$.messager.alert('提示消息',data.data,'error');
    		    	}
    		    }
    		});
    	}
	    
    	var SysconfigGrid = {
    		createGrid:function(grId){
    			return $('#'+grId).datagrid({
    				url : '${mainServer}/admin/costApply/queryApplyCostListPage',
    				rownumbers:true,
                    singleSelect:false,
                    pagination:true,
                    autoRowHeight:true,
                    fit:true,
                    fitColumns : true, //自动使列适应表格宽度以防止出现水平滚动。
                    striped:true,
                    checkOnSelect:true, //点击某一行时，则会选中/取消选中复选框
                    selectOnCheck:true, //点击复选框将会选中该行
                    collapsible:true,
                    align:'center',
    				columns : applyColumns,
    				toolbar : '#businessActivitiesToolbar',
    				onBeforeLoad : function(param) {
    					$('#businessActivitiesGrid').datagrid('clearSelections');
    				},
    				onLoadSuccess:function(data){
    					initCUIDBtn();
    				},
    				onSelect:function(rowIndex, rowData){
    					initCUIDBtn();
    				},
    				onUnselect:function(rowIndex, rowData){
    					initCUIDBtn();
    				}
    			});
    		}
    	};
    	
    	var applyColumns=[ [ {
            title:"",
            field:"costId",
            checkbox: true,
            width:'2%',
            
        },
        {
            title:"申请季度",
            field:"applyQuarter",
            width:'7%'
        },{
            title:"申请时间",
            field:"applyTimeStr",
            width:'9%'
        },{
            title:"区域",
            field:"applyArea",
            width:'8%'
        },{
            title:"城市",
            field:"city",
            width:'7%',
           
        },{
            title:"部门",
            field:"deptName",
            width:'8%',
        },{
            title:"姓名",
            field:"realName",
            width:'8%',
        },{
            title:"费用类型",
            field:"costTypeName",
            width:'8%',
            /* formatter:function(value, row){
            	if(value==0){
            		return "正常";
            	}else if(value==1){
            		return "故障";
            	}
            } */
        },
        {
        	 title:"预算金额",
             field:"totalFee",
             width:'6%'
        },
        {
       	 title:"实际使用金额",
            field:"realFee",
            width:'8%'
       },
       {
      	 title:"差额",
           field:"shengyuFee",
           width:'6%'
      },
      {
       	 title:"备注",
            field:"remark",
            width:'14%'
       },
       {
         	 title:"操作",
             field:"55",
             width:'8%',
             formatter:function(value, row){
            	var roleId="${loginInfo.role_id}";
            	if(roleId==2){
            		return '<a href="javascript:void(0)" class="easyui-linkbutton"  data-options="selected:true" onclick="deleteCostApply('+row.costId+')">删除</a>';
            	}
	         	
            } 
         }]];
    	
    	function initCUIDBtn() {
    		var rows = boxDataGrid.datagrid('getSelections');
    		if(rows.length > 1){//多行情况
    			boxDataGrid.datagrid("disableToolbarBtn", 'updConfBtn');
    			boxDataGrid.datagrid("disableToolbarBtn", 'deleteConfBtn');
    		}else if(rows.length == 1){
    			boxDataGrid.datagrid("enableToolbarBtn", 'updConfBtn');
    			boxDataGrid.datagrid("enableToolbarBtn", 'deleteConfBtn');
    		}else if(rows.length == 0){
    			boxDataGrid.datagrid("disableToolbarBtn", 'updConfBtn');
    			boxDataGrid.datagrid("disableToolbarBtn", 'deleteConfBtn');
    		}
    	}
    	
    	
	    function excelReportActivitiesList(){
	    	
        	var selected = $('#businessActivitiesGrid').datagrid("getRows");
        	if(selected.length == 0){
        		$.messager.alert('提示消息','没有数据可以导出!','info');
        		return ;
        	}
	    	
	    	window.location.href="${mainServer}/admin/costApply/downloadCostApply?realName="+$("#search").val();
	    }
	    
	    function deleteCostApply(costId){
	    	$.messager.confirm('删除确认框','确定删除该条记录吗？',function(r){
	    	    if (r){
	    	    	$.ajax({
	    		        type: "POST",
	    		        url: "${mainServer}/admin/costApply/updateCostApplyStatus",
	    		        data:{
	    		        	costId:costId
	    		        },
	    		        async: false,
	    		        dataType: "json",
	    		        success: function(data) { 
	    		        	var obj=eval(data);
	    		        	if(obj.code==1010){  
	    		        		queryCouponManager();
	    		        	}else{
	    		        		$.messager.alert('提示消息',data.msg,'error');
	    		        	}
	    		        	
	    		        },
	    		        error: function(XMLHttpRequest, textStatus, errorThrown) {
	    		        }
	    		    });
	    	    }
	    	});
	    }
    </script>
</body>
</html>