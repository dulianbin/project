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
          
           <span class="con-span" style="margin-right: 30px;">搜索  </span><input  type="text" name="search" id="search" placeholder="请输入..." style="width:166px;height:31px;line-height:31px;border:1px solid #aaa;" ></input>
           <a href="#" class="easyui-linkbutton" iconCls="icon-search"  data-options="selected:true" onclick="queryCouponManager()" >查询</a> 
           <c:if test="${loginInfo.role_id==3 }">
              <a href="#" class="easyui-linkbutton" iconCls="icon-search"  data-options="selected:true" onclick="openCityApplyCostMoney()" style="margin-left:50px;">城市预算余额查询</a>
           </c:if>
           
          
	      <div style="float:right;margin-top:25px;">
		      <a class="easyui-linkbutton exce-btn" onclick="excelReportActivitiesList()" href="javascript:void();" iconCls="icon-print">导出Excel</a>
		  </div>
		  <div style="clear: both;"></div>
        </div>
      </div>
      
     <div id="businessActivitiesToolbar2" style="padding:0 20px;">
       <div class="conditions">
	      <div style="float:right;margin-top:10px;">
		      <a class="easyui-linkbutton exce-btn" onclick="excelReportActivitiesList2()" href="javascript:void();" iconCls="icon-print">导出Excel</a>
		  </div>
		  <div style="clear: both;"></div>
        </div>
      </div>
      
      <div id="businessActivitiesToolbar3" style="padding:0 20px;">
       <div class="conditions">
          <span class="con-span" style="margin-right: 30px;">财年年份  </span>
          <select name="applyQuarter" id="applyQuarter" style="height:28px;width:120px;">
										<option value="FY19-Q" selected="selected">FY19财年</option>
										<option value="FY20-Q" >FY20财年</option>
										<option value="FY21-Q" >FY21财年</option>
										<option value="FY22-Q" >FY22财年</option>
										<option value="FY23-Q" >FY22财年</option>
								 </select>
          <a href="#" class="easyui-linkbutton" iconCls="icon-search"  data-options="selected:true" onclick="queryCouponManager2()" >查询</a> 
	      <div style="float:right;margin-top:10px;">
		      <a class="easyui-linkbutton exce-btn" onclick="excelYearBudgetFeeList()" href="javascript:void();" iconCls="icon-print">导出Excel</a>
		  </div>
		  <div style="clear: both;"></div>
        </div>
      </div>
      
        
       <div id="couponManager" class="easyui-dialog" title="各部门申请费用统计" data-options="closed:true,modal:true" style="width:1000px;height:500px;padding:5px;">
		 <table id="businessActivitiesGrid2" style="width:100%;height:529px;" title="">
         
         </table>
	   </div>
	   
	   <div id="couponManager3" class="easyui-dialog" title="城市预算余额查询" data-options="closed:true,modal:true" style="width:1000px;height:550px;padding:5px;">
		 <table id="businessActivitiesGrid3" style="width:100%;height:529px;" title="">
         
         </table>
	   </div>
	   
	   
       <input type="hidden" id="citySelectd">
       <input type="hidden" id="quartySelectd">
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
	    		"search":$("#search").val(),
	    	});
	    	
		} 
	    
	    function queryCouponManager2(){
	    	$('#businessActivitiesGrid3').datagrid('load', { 
	    		"year":$("#applyQuarter").val(),
	    	});
	    	
		} 
	    

	    
    	var SysconfigGrid = {
    		createGrid:function(grId){
    			return $('#'+grId).datagrid({
    				url : '${mainServer}/admin/report/calCityCostListPage',
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
    	
    	 
    	var SysconfigGrid2 = {
        		createGrid:function(grId,quarter,city){
        			return $('#'+grId).datagrid({
        				url : '${mainServer}/admin/report/queryQuarterAllFee?city='+city+"&quarter="+quarter,
        				rownumbers:true,
                        singleSelect:false,
                        pagination:false,
                        autoRowHeight:true,
                        fit:true,
                        fitColumns : true, //自动使列适应表格宽度以防止出现水平滚动。
                        striped:true,
                        checkOnSelect:true, //点击某一行时，则会选中/取消选中复选框
                        selectOnCheck:true, //点击复选框将会选中该行
                        collapsible:true,
                        align:'center',
        				columns : [ [ 
        		        {
        		            title:"城市",
        		            field:"city",
        		            align:'center',
        		            width:'12%',
        		           
        		        },{
        		            title:"部门",
        		            field:"dept_name",
        		            align:'center',
        		            width:'12%',
        		        },{
        		            title:quarter+"1",
        		            field:"quarter_1",
        		            align:'center',
        		            width:'10%',
        		        },{
        		            title:quarter+"2",
        		            field:"quarter_2",
        		            align:'center',
        		            width:'10%',
        		        },
        		        {
        		        	 title:quarter+"3",
        		             field:"quarter_3",
        		             align:'center',
        		             width:'10%'
        		        },
        		        {
        		       	    title:quarter+"4",
        		         	align:'center',
        		            field:"quarter_4",
        		            width:'10%'
        		       },{
	       		            title:"申请总费用",
	    		            field:"apply_allfee",
	    		            align:'center',
	    		            width:'10%',
    		           },
    		           {
          		            title:"年度总预算",
        		            field:"budget_fee_sum",
        		            align:'center',
        		            width:'12%',
        		       },{
	    		            title:"申请费用占比",
	    		            field:"fee_rante",
	    		            align:'center',
	    		            width:'12%',
    		           }]],
    		            toolbar : '#businessActivitiesToolbar2',
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
    	
    	
    	var SysconfigGrid3 = {
        		createGrid:function(grId){
        			return $('#'+grId).datagrid({
        				url : '${mainServer}/admin/report/queryYearBudgetFeeList',
        				rownumbers:true,
                        singleSelect:false,
                        pagination:false,
                        autoRowHeight:true,
                        width:'800px',
                        height:'600px',
                        fit:true,
                        fitColumns : true, //自动使列适应表格宽度以防止出现水平滚动。
                        striped:true,
                        checkOnSelect:true, //点击某一行时，则会选中/取消选中复选框
                        selectOnCheck:true, //点击复选框将会选中该行
                        collapsible:true,
                        showFooter:true,
                        align:'center',
                       /*  rowStyler:function(index,row){   
                            if (row.city=='年度预算剩余'){   
                                return 'background-color:red;';   
                            }   
                        }, */
        				columns : [ [ 
        		        {
        		            title:"城市",
        		            field:"city",
        		            align:'center',
        		            width:'9%',
        		            styler: function (value, row, index) {
        		            	if(value=='年度预算剩余' || value=='年度实际剩余'){
        		            		return 'background-color:#ccc';
        		            	}else{
        		            		return value;
        		            	}
        		                
        		            }
        		        },{
        		            title:"季度",
        		            field:"apply_quarter",
        		            align:'center',
        		            width:'8%',
        		            formatter:function(value, row,index){
        		            	if((row.city=='年度预算剩余' || row.city=='年度实际剩余') && (index==0 || index==1)){
        		            		return "";
        		            	}else{
        		            		return value;
        		            	}
        		            }
        		        },{
        		            title:"深圳",
        		            field:"sz",
        		            align:'center',
        		            width:'8%',
        		            formatter:function(value, row){
        		            	if(value==undefined){
        		            		return "0";
        		            	}else{
        		            		return value;
        		            	}
        		            }
        		        },{
        		            title:"广州",
        		            field:"gz",
        		            align:'center',
        		            width:'8%',
        		            formatter:function(value, row){
        		            	if(value==undefined){
        		            		return "0";
        		            	}else{
        		            		return value;
        		            	}
        		            }
        		        },{
        		            title:"长沙",
        		            field:"cs",
        		            align:'center',
        		            width:'7%',
        		            formatter:function(value, row){
        		            	if(value==undefined){
        		            		return "0";
        		            	}else{
        		            		return value;
        		            	}
        		            }
        		        },
        		        {
        		        	 title:"福州",
        		             field:"fz",
        		             align:'center',
        		             width:'7%',
       		            	 formatter:function(value, row){
            		            	if(value==undefined){
            		            		return "0";
            		            	}else{
            		            		return value;
            		            	}
            		          }
        		        },
        		        {
        		       	    title:"南昌",
        		            field:"nc",
        		            align:'center',
        		            width:'7%',
        		            formatter:function(value, row){
        		            	if(value==undefined){
        		            		return "0";
        		            	}else{
        		            		return value;
        		            	}
        		            }
        		       },{
	       		            title:"佛山",
	    		            field:"fs",
	    		            align:'center',
	    		            width:'7%',
	    		            formatter:function(value, row){
        		            	if(value==undefined){
        		            		return "0";
        		            	}else{
        		            		return value;
        		            	}
        		            }
    		           },
    		           {
          		            title:"厦门",
        		            field:"xm",
        		            align:'center',
        		            width:'7%',
        		            formatter:function(value, row){
        		            	if(value==undefined){
        		            		return "0";
        		            	}else{
        		            		return value;
        		            	}
        		            }
        		       },{
	    		            title:"东莞",
	    		            field:"dg",
	    		            align:'center',
	    		            width:'7%',
	    		            formatter:function(value, row){
        		            	if(value==undefined){
        		            		return "0";
        		            	}else{
        		            		return value;
        		            	}
        		            }
    		           },{
	    		            title:"区域运营",
	    		            field:"qyyy",
	    		            align:'center',
	    		            width:'8%',
	    		            formatter:function(value, row){
        		            	if(value==undefined){
        		            		return "0";
        		            	}else{
        		            		return value;
        		            	}
        		            }
    		           },{
	    		            title:"珠海",
	    		            field:"zh",
	    		            align:'center',
	    		            width:'8%',
	    		            formatter:function(value, row){
        		            	if(value==undefined){
        		            		return "0";
        		            	}else{
        		            		return value;
        		            	}
        		            }
    		           },{
	    		            title:"海口",
	    		            field:"hk",
	    		            align:'center',
	    		            width:'7%',
	    		            formatter:function(value, row){
        		            	if(value==undefined){
        		            		return "0";
        		            	}else{
        		            		return value;
        		            	}
        		            }
    		           }]],
    		            toolbar : '#businessActivitiesToolbar3',
        				onBeforeLoad : function(param) {
        					$('#businessActivitiesGrid').datagrid('clearSelections');
        				},
        				onLoadSuccess:function(data){
        					//initCUIDBtn();
        					/* var merges = [{
        						index:0,
        						rowspan:2
        					},{
        						index:2,
        						rowspan:2
        					}]; */
        					
        					var merges = [{
        						index:1,
        						rowspan:4
        					},{
        						index:5,
        						rowspan:4
        					},{
        						index:9,
        						rowspan:4
        					},{
        						index:13,
        						rowspan:4
        					},{
        						index:17,
        						rowspan:4
        					}];
        					for(var i=0; i<merges.length; i++)
        						$('#'+grId).datagrid('mergeCells',{
        							index:merges[i].index,
        							field:'city',
        							rowspan:merges[i].rowspan
        					});
        					
        					
        					var merges_a = [{
                                index: 0,
                                colspan: 2
                            },{
                                index: 22,
                                colspan: 2
                            }];
                            for (var i = 0; i < merges_a.length; i++)
                                $('#'+grId).datagrid('mergeCells', {

                                    index: merges_a[i].index,
                                    field: 'city',
                                    colspan: merges_a[i].colspan

                            });
                            
                            var panel = $(this).datagrid('getPanel');   
                            var tr = panel.find('div.datagrid-body tr');  
                            $(tr).css("height","27px");
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
            title:"时间",
            field:"apply_quarter",
            align:'center',
            width:'11%'
        },{
            title:"区域",
            field:"apply_area",
            align:'center',
            width:'11%'
        },{
            title:"城市",
            field:"city",
            align:'center',
            width:'10%',
           
        },{
            title:"预算额度",
            field:"budget_fee1",
            align:'center',
            width:'11%',
        },{
            title:"已审金额",
            field:"total_fee_sum",
            align:'center',
            width:'11%',
        },{
            title:"实际使用金额",
            field:"real_fee_sum",
            align:'center',
            width:'12%',
        },
        {
        	 title:"可申请余额",
             field:"can_apply_fee",
             align:'center',
             width:'12%'
        },
        {
       	 title:"实际余额",
            field:"real_shengyu_fee",
            align:'center',
            width:'8%'
       },
       {
         	 title:"操作",
             field:"55",
             align:'center',
             width:'11%',
             formatter:function(value, row){
            	 var apply_quarter=row.apply_quarter;
            	 var city=row.city;
            	 var quarter=apply_quarter.substring(0,apply_quarter.length-1);
            	 return '<a href="javascript:void(0)" class="easyui-linkbutton"  data-options="selected:true" onclick="openWindow(\''+city+'\',\''+quarter+'\')">各部门申请统计</a>';
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
	    	
	    	window.location.href="${mainServer}/admin/report/downloadCityCost?search="+$("#search").val();
	    }
	    
	    function excelReportActivitiesList2(){
	    	
        	var selected = $('#businessActivitiesGrid2').datagrid("getRows");
        	if(selected.length == 0){
        		$.messager.alert('提示消息','没有数据可以导出!','info');
        		return ;
        	}
	    	
	    	window.location.href="${mainServer}/admin/report/downloadQuarterAllFee?city="+$("#citySelectd").val()+"&quarter="+$("#quartySelectd").val();
	    }
	    
        function excelYearBudgetFeeList(){
	    	window.location.href="${mainServer}/admin/report/downloadYearBudgetFeeList?year="+$("#applyQuarter").val();
	    }
	    
	    
	    
	    
	    
	    function openWindow(city,quarter){
	    	$("#couponManager").dialog("open");
	    	var boxDataGrid = SysconfigGrid2.createGrid('businessActivitiesGrid2',quarter,city);
	    	$("#quartySelectd").val(quarter);
       	    $("#citySelectd").val(city);
	    }
	    
	    function openCityApplyCostMoney(){
            
	    	$("#couponManager3").dialog("open");
	    	var boxDataGrid = SysconfigGrid3.createGrid('businessActivitiesGrid3');
	    }
    </script>
</body>
</html>