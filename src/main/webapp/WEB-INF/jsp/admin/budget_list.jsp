<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>财务费用管理-费用预算</title>
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
          
           <span class="con-span" style="margin-right: 30px;">城市  </span><input  type="text" name="search" id="search" placeholder="请输入..." style="width:166px;height:31px;line-height:31px;border:1px solid #aaa;" ></input>
           <a href="#" class="easyui-linkbutton" iconCls="icon-search"  data-options="selected:true" onclick="querycostBudgetManager()" >查询</a>
           
           
           
          <div class="opt-buttons" style="margin-bottom:2px;">
	            <a href="javascript:void(0)" id="addConfBtn" class="easyui-linkbutton"  iconCls="icon-add"  data-options="selected:true" onclick="opencostApplyForm()">新增</a>
	      </div>
	      <!-- <div style="float:right;margin-top:-37px;">
		      <a class="easyui-linkbutton exce-btn" onclick="excelReportActivitiesList()" href="javascript:void();" iconCls="icon-print">导出Excel</a>
		  </div>
		  <div style="clear: both;"></div> -->
        </div>
      </div>
        
       <div id="costBudgetManager" class="easyui-dialog" title="费用预算" data-options="closed:true,modal:true" style="width:550px;height:340px;padding:5px;">
		  <form  id="costApplyForm" action="" method="post"  enctype="multipart/form-data">
		       	<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-label">区域</td>
							<td class="kv-content">
								<select name="applyArea" id="applyArea" style="height:35px;width:200px;">
										<option value="华南" selected="selected">华南</option>
								</select> 
							</td>
						</tr>
						
						<tr>
							<td class="kv-label">城市</td>
							<td class="kv-content">
								<select name="cityId" id="cityId" style="height:35px;width:200px;">
										<option value="1" selected="selected">深圳</option>
										<option value="2" >广州</option>
										<option value="3" >长沙</option>
										<option value="4" >福州</option>
										<option value="5" >南昌</option>
										<option value="6" >佛山</option>
										<option value="7" >厦门</option>
										<option value="8" >东莞</option>
										<option value="9">区域运营</option>
										<option value="10" >珠海</option>
										<option value="11" >海口</option>
								</select>
							</td>
							
						</tr>
						<tr>
							<td class="kv-label">申请季度</td>
							<td class="kv-content">
							     <select name="applyQuarter" id="applyQuarter" style="height:35px;width:200px;">
										
										
										<option value="FY19-Q1" >FY19-Q1</option>
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
							<td class="kv-label" ><span id="couponTypeLabel">预算金额</span></td>
							<td class="kv-content">
								<input  name="budgetFee" id="budgetFee" class="easyui-numberbox" precision="2" style="height:35px;width:200px;"/>
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
	   
	   
	 <div id="costBudgetManager2" class="easyui-dialog" title="费用预算" data-options="closed:true,modal:true" style="width:550px;height:340px;padding:5px;">
		  <form  id="costApplyForm2" action="" method="post"  enctype="multipart/form-data">
		        <input type="hidden" id="id" name="id" value=""/>
		       	<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-label">区域</td>
							<td class="kv-content">
								<select name="applyArea" id="applyArea2" style="height:35px;width:200px;">
										<option value="华南" selected="selected">华南</option>
								</select> 
							</td>
						</tr>
						
						<tr>
							<td class="kv-label">城市</td>
							<td class="kv-content">
								<select name="cityId" id="cityId2" style="height:35px;width:200px;">
										
								</select>
							</td>
							
						</tr>
						<tr>
							<td class="kv-label">申请季度</td>
							<td class="kv-content">
							     <select name="applyQuarter" id="applyQuarter2" style="height:35px;width:200px;">
										
										
								 </select>
							</td>
						</tr>
						<tr>
							<td class="kv-label" ><span id="couponTypeLabel2">预算金额</span></td>
							<td class="kv-content">
								<input  name="budgetFee" id="budgetFee3" class="easyui-numberbox" precision="2" style="height:35px;width:200px;"/>
							</td>
							
						</tr>
							
						<tr>
							<td colspan="4">
								<div align="center" style="margin-top: 50px;">
									<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" data-options="selected:true" 
									   onclick="updateForm()" id="add_Ptype2" >保存</a>
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
	    
	    function querycostBudgetManager(){
	    	$('#businessActivitiesGrid').datagrid('load', { 
	    		"city":$("#search").val(),
	    	});
	    	
		} 
	    

	    
	    function opencostApplyForm(){
	    	//$("#costApplyForm").form("clear");
	    	//$("#status").select();
	    	$("#costBudgetManager").dialog("open");
	    }
	    
	    function updatecostBudgetManagerDlg(){
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
		        		$("#costBudgetManager").dialog("open");
		        	}else{
		        		$.messager.alert('提示消息',data.data,'error');
		        	}
		        	
		        },
		        error: function(XMLHttpRequest, textStatus, errorThrown) {
		        }
		    });
	    }
	    
	    function saveForm(){
    
    		$("#costApplyForm").form("submit",{
    			url:'${mainServer}/admin/budget/savebudget',  
    			ajax : true,
    		    onSubmit: function(){    
    		    	
    		    },    
    		    success:function(data){
    		    	data = typeof data == 'object' ? data : $.parseJSON(data);
    		    	
    		    	if(data.code==1010){
    		    		querycostBudgetManager();
    		    		$("#costBudgetManager").dialog("close");
    		    	}else{
    		    		$.messager.alert('提示消息',data.data,'error');
    		    	}
    		    }
    		});
    	}
	    
	    function updateForm(){
    
    		$("#costApplyForm2").form("submit",{
    			url:'${mainServer}/admin/budget/updateBudget',  
    			ajax : true,
    		    onSubmit: function(){    
    		    	
    		    },    
    		    success:function(data){
    		    	data = typeof data == 'object' ? data : $.parseJSON(data);
    		    	
    		    	if(data.code==1010){
    		    		querycostBudgetManager();
    		    		$("#costBudgetManager2").dialog("close");
    		    	}else{
    		    		$.messager.alert('提示消息',data.data,'error');
    		    	}
    		    }
    		});
    	}
	    
	    
	    
    	var SysconfigGrid = {
    		createGrid:function(grId){
    			return $('#'+grId).datagrid({
    				url : '${mainServer}/admin/budget/queryBudgetListPage',
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
            field:"id",
            checkbox: true,
            width:'2%',
            
        },
        {
            title:"申请季度",
            field:"applyQuarter",
            width:'15%'
        },{
            title:"区域",
            field:"applyArea",
            width:'15%'
        },{
            title:"城市",
            field:"city",
            width:'14%',
        },
        {
        	 title:"预算金额",
             field:"budgetFee",
             width:'15%'
        },
        {
       	 title:"操作人",
            field:"operator",
            width:'14%'
       },{
          title:"操作时间",
          field:"operatorTimeStr",
          width:'15%'
      },
       {
         	 title:"操作",
             field:"55",
             width:'9%',
             formatter:function(value, row){
            	return '<a href="javascript:void(0)" class="easyui-linkbutton"  data-options="selected:true" onclick="updateBudget('+row.id+')">编辑</a>';
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
	    
	    function updateBudget(id){
	    	$.ajax({
		        type: "POST",
		        url: "${mainServer}/admin/budget/queryById",
		        data:{
		        	id:id
		        },
		        async: false,
		        dataType: "json",
		        success: function(data) { 
		        	var obj=eval(data);
		        	if(obj.code==1010){  
		        		$("#id").val(obj.data.id);
		        		//添加城市下拉框
		        		var cityStr='';
		        		$("#cityId2").empty();
		        		$.each(obj.data.cityList,function(id,value){
		        			if(obj.data.cityId==id){
		        				cityStr+='<option value="'+id+'" selected="selected">'+value+'</option>';
		        			}else{
		        				cityStr+='<option value="'+id+'" >'+value+'</option>';
		        			}
		        		});
		        		$("#cityId2").append(cityStr);
		        		
		        		
		        		
		        		var applyQuarter='';
		        		$("#applyQuarter2").empty();
		        		$.each(obj.data.quarterList,function(id,value){
		        			if(obj.data.applyQuarter==id){
		        				applyQuarter+='<option value="'+id+'" selected="selected">'+value+'</option>';
		        			}else{
		        				applyQuarter+='<option value="'+id+'" >'+value+'</option>';
		        			}
		        		});
		        		$("#applyQuarter2").append(applyQuarter);
		        		
		        		$("#budgetFee3").numberbox("setValue",obj.data.budgetFee);
		        		
		        		$("#costBudgetManager2").dialog("open");
		        		
		        	}else{
		        		$.messager.alert('提示消息',data.msg,'error');
		        	}
		        },
		        error: function(XMLHttpRequest, textStatus, errorThrown) {
		        }
		    });
	    	
	    }
    </script>
</body>
</html>