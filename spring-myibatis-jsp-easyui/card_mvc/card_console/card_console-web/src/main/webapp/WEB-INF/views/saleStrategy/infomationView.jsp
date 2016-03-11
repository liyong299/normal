<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">添加销售策略</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
    <easyui:layout id="test-layout" fit="true">
	    <easyui:layoutUnit region="north" border="false">
	    	<form id="info" class="edit_box"  method="post" action="" style="padding:0">
	    		<input id="id" type="hidden" value="" />
	    		<input id="cinemaNo" type="hidden" value="" />
		        <div class="item"><label>策略参考：</label>
			        <easyui:comboGrid id="settlList" url="getSettlStrategy.do?cinemaNo=${cinemaNo}" idField="id" textField="name" panelWidth="360" fitColumns="true" data="${settlList}" style="height:22px">
					    <easyui:columns>
					        <%-- <easyui:column field="id" title="策略id" hidden="true"/> --%>
					        <easyui:column field="name" title="策略名称"/>
					        <easyui:column field="settlType" title="定价类型" formatter="function(value,rowData,index){
					        	if(value == '0'){
									return '常规价';
								}else if(value == '1'){                                     
									return '闲忙时价';
								}
					        }"/>
					      <easyui:column field="date" title="有效期限" align="center" rowspan="2" formatter="function(value, rowData, index){
					        	if(rowData.startDate !='' && rowData.startDate!=null){
					        		return rowData.startDate + ' ~ ' + rowData.endDate;
					        	}
					        }"/>
					    </easyui:columns>
					</easyui:comboGrid>
		       		<%-- <easyui_ext:comboBox id="settList" name="settlStrategyId" data="${settlList}" valueField="id" textField="name"/> --%>
		       		<span class="star">*</span> 
		       </div>
		        <div class="item"><label>策略名称：</label><easyui_ext:textBox id="name" /><span class="star">*</span></div>
		        <div class="item"><label>策略类型：</label>
					<input type="radio" name="strategyType" id="strategyType_0" value="0" >全局
					<input type="radio" name="strategyType" id="strategyType_1" value="1">分销商
				</div>
		        <div class="item"><label>定价类型：</label>
					<input type="radio" name="saleType" id="saleType_0" value="0">常规价
					<input type="radio" name="saleType" id="saleType_1" value="1">闲忙时价
				</div>
		        <div class="item"><label>有效期起：</label><input id="startDate" class="easyui-datebox" required="required"/><span class="star">*</span></div>
		        <div class="item"><label>有效期止：</label><input id="endDate" class="easyui-datebox" required="required"/><span class="star">*</span></div>
		       <div class="item item_row button">
		            <a class="easyui-linkbutton"  onclick="view(true)">通过</a>
		            <a class="easyui-linkbutton"  onclick="view(false)">拒绝</a>
		            <a class="easyui-linkbutton" onclick="Cancel()">取消</a>
		        </div>
		    </form>
	    </easyui:layoutUnit>
	    <easyui:layoutUnit region="center" border="false" style="padding-bottom:40px">
	    	<!-- <div style="font-size: 12px;font-weight: bold;color: #777;height: 22px;line-height: 16px;">设置规则</div> -->
	    	<table id="dg"></table>
	    </easyui:layoutUnit>
    </easyui:layout>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
		$(function () {
	        //初始化数据
	        load();
	
	        //初始化控件
	        init();
	    });
		
		var load = function(){
			$("#id").val("${saleStrategy.id}");
			/* $("#cinemaNo").val("${cinemaNo}"); */
			$("#settlList").combogrid("setValue", "${saleStrategy.settlStrategyId}");
			$("#name").textbox("setValue", "${saleStrategy.name}");
			$("#startDate").datebox("setValue", "${saleStrategy.startDate}");
			$("#endDate").datebox("setValue", "${saleStrategy.endDate}");
			var saleType = '${saleStrategy.saleType}';
			if(saleType == '0'){
				$("#saleType_0").click();
				$("input[type=radio]").attr("disabled", "disabled");
			}else if(saleType == '1' || saleType=='2'){
				$("#saleType_1").click();
				$("input[type=radio]").attr("disabled", "disabled");
			}
			$("input[name='strategyType'][value='${saleStrategy.strategyType}']").attr("checked",true); 
			
			$('#dg').datagrid({
				title:'设置规则',
			    iconCls:'icon-edit',
			    //data:data,
			    url:"editSaleRule.do?saleStrategyId="+"${saleStrategy.id}",
			    columns:[[
			        {field:'showType',title:'放映类型',width:100,align: 'center',rowspan: 2,formatter: function(value,row,index){
			        	if(value == '0'){
							return '2D';
						}else if(value == '1'){                                     
							return '3D';
						}else if(value == '2'){                                     
							return 'IMAX2D';
						}else if(value == '3'){                                     
							return 'IMAX3D';
						}else if(value == '4'){                                     
							return '中国巨幕2D';
						}else if(value == '5'){                                     
							return '中国巨幕3D';
						}
					}},
			        {field:'saleType',title:'定价类型',width:100,rowspan: 2,align: 'center',formatter: function(value,row,index){
			        	if(value == '0'){
							return '常规价';
						}else if(value == '1'){                                     
							return '忙时价';
						}else if(value == '2'){                                     
							return '闲时价';
						}
					}},
			        { title: '销售价格',align: 'center', colspan: 2},
			        {field:'pointAmount',title:'销售点数(点)',width:100,align: 'center',rowspan: 2},
			        {field:'paperAmount',title:'销售张数(张)',width:100,align: 'center',rowspan: 2},
			        {field:'exchangeAmount',title:'兑换2D张数(张)',width:100,align: 'center',rowspan: 2}
			    ],
			    [
                 {
                     field: 'type', title: '定价方式', width: 100, align: 'center',formatter: function(value,row,index){
			        	if(value == '0'){
							return '固定价';
						}else if(value == '1'){                                     
							return '固定加价';
						}else if(value == '2'){                                     
							return '折扣价';
						}
					}
                 },
                 { field: 'salePrice', title: '销售价格', width: 100,align: 'center',formatter: function(value,row,index){
                	 if(row.type == '0' ){
                		 return value+'元';
                	 }else if(row.type == '1'){
                		 if(value.indexOf("-")!=-1){
 							return value+'元';
                 		 }else{
 							return "+"+value+'元';
                 		 }
					}else if(row.type == '2'){
						return value+'折';
					}else {
						return value;
					}
 				  }}
             ]]
			});
			
		};
		
		var init = function () {
            $("#settlList").combogrid("readonly");
            $("#name").textbox("readonly");
            $("#startDate").datebox("readonly");
            $("#endDate").datebox("readonly");
        };
	
        function view(flg){
        	var status = '${status}';//2 信息审核 3 财务审核
        	if(flg==true){//通过
        		if(status=='2'){
        			status = '3';
        		}else if(status=='3'){
        			status = '1';
        		}else if(status=='-9'){
        			status = '2';
        		}
        	}else{
        		if(status=='2'){
        			status = '-2';
        		}else if(status=='3'){
        			status = '-3';
        		}else if(status=='-9'){
        			status = '-9';
        		}
        	}
        	
        	var id = "${saleStrategy.id}";
        	var data = {
           			id:id,
           			status:status
                 };
        	 $.ajaxRequest({
         		url: "updateInfomationView.do",
         		para: data,
         		success: function(result){
         			if(result.isSuccess){
         				parent.datalist._temp.list_box.treegrid("reload");
 	        			parent.Tips.Success("操作成功");
 	        			Cancel();
 	        		}else{
         				parent.Tips.Error(result.message);
         			}
         		}
         	});
        	 
        }
        
		function Cancel(){
			parent.datalist._temp.layout_box.window("close");
		}
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>