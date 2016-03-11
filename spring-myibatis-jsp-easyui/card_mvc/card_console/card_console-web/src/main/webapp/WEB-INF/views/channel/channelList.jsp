<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main" fit="true">
		<easyui_ext:searchCondition id="search-form">
				<easyui_ext:searchItem name="渠道编号"><easyui_ext:textBox id="channelno" value="" ></easyui_ext:textBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="渠道名称"><easyui_ext:textBox id="channelname" value="" ></easyui_ext:textBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="渠道类型"><easyui_ext:comboBox id="type" value="" data="<%= com.mopon.helpers.ui.combobox.Data.getTepyList() %>" ></easyui_ext:comboBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="渠道级别"><easyui_ext:comboBox id="channellevel" value="" data="${channelLevelList}" ></easyui_ext:comboBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="开放状态"><easyui_ext:comboBox id="opened" value="" data="<%= com.mopon.helpers.ui.combobox.Data.getOpenedList() %>" ></easyui_ext:comboBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="销售状态"><easyui_ext:comboBox id="salable" value="" data="<%= com.mopon.helpers.ui.combobox.Data.getSalableList() %>" ></easyui_ext:comboBox></easyui_ext:searchItem>
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="datalist" url="view.do" fit="true" pageSize="30" pagination="true" singleSelect="true" fitColumns="true">
				<easyui:facet name="toolbar">
					<easyui:button id="btn_search" text="查询" plain="true" iconCls="icon-search"  onclick="datalist.Search()" />		
					<easyui_ext:facetButton id="btn_add" text="添加" plain="true" iconCls="icon-add" onclick="datalist.Add()"/>
			    </easyui:facet>
			    <easyui:columns>
			        <easyui:column field="ck" checkbox="true"/>
			        <easyui:column field="channelno" title="渠道编号"/>
			        <easyui:column field="channelname" title="渠道名称"/>
			        <easyui:column field="seckey" title="通讯秘钥"/>
			        <easyui:column field="type" title="渠道类型" formatter="function(value, rowData, index){
			      if(value == '1'){
								return '分销';
							}else if(value == '2'){
								return '自有';
							}else {
								return '未知';
							}
							
						}"/>
						
					<easyui:column field="channelLevel" title="渠道级别" formatter="function(value, rowData, index){
		      			if(value == '2'){
							return '子级';
						}else {                //scec同步过来的渠道默认都是父级，即使此字段为空
							return '父级'; 
						}}"/>
					<easyui:column field="parentChannelNo" title="父级渠道编号" />
			        <easyui:column field="opened" title="开放状态" formatter="function(value, rowData, index){if(value == true){return '开放';}else{return '关闭';}}"/>
			        <easyui:column field="salable" title="销售状态" formatter="function(value, rowData, index){if(value == true){return '开放';}else{return '关闭';}}"/>
			        <easyui:column field="settlType" title="结算方式" formatter="function(value,row,index){
							if(value == '0'){
								return '自销';
							}else if(value == '1'){
								return '采销';
							}else if(value == '2'){
								return '代销';
							}else {
								return '';
							}
							
						}"/>
			        <easyui:column field="dividedType" title="分成方式" formatter="function(value, rowData, index){if(value == '1'){return '单价结算';}else if(value=='2'){return '比例结算';}else{return ''}}"/>
			        <easyui:column field="unitPrice" title="分成单价"/>
			        <easyui:column field="rate" title="分成比例" />
			        <easyui:column field="channelKey" title="渠道关键字"/>
			        <easyui:column field="channelUrl" title="渠道域名地址"/>
			        <easyui:column field="dockingType" title="渠道对接类型" formatter="function(value,row,index){
							if(value == '1'){
								return 'API';
							}else if(value == '2'){
								return '内嵌';
							}else if(value == '3'){
								return '连接跳转';
							}else {
								return '';
							}
							
						}"/>
			        <easyui:column field="sendVoucherType" title="凭证下发方式" formatter = "datalist.sendTypeValue" />
			       <easyui_ext:formatterColumn title="操作" param="value, rowData, index">
			        	<easyui_ext:handlerColumn text="编辑" onclick="datalist.editPayChannel" param="'rowData.channelno'" />			        	
			        	<easyui_ext:handlerColumn text="开放状态开放" onclick="datalist.updateOpened" param="'rowData.channelno',true" condition="rowData.opened == false" />
			        	<easyui_ext:handlerColumn text="开放状态关闭" onclick="datalist.updateOpened" param="'rowData.channelno',false" condition="rowData.opened == true" />
			        	<easyui_ext:handlerColumn text="销售状态开放" onclick="datalist.updateSalable" param="'rowData.channelno',true" condition=" rowData.opened == true && rowData.salable == false" />
			        	<easyui_ext:handlerColumn text="销售状态关闭" onclick="datalist.updateSalable" param="'rowData.channelno',false" condition="rowData.opened == true && rowData.salable == true" />
			        	<easyui_ext:handlerColumn text="关联影院" onclick="datalist.openedAdd" param="'rowData.channelno'" condition="rowData.opened==true" />
			        	<easyui_ext:handlerColumn text="关联支付渠道" onclick="datalist.salableAdd" param="'rowData.channelno'" condition="rowData.salable==true" />
			        </easyui_ext:formatterColumn>
			    </easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
	<div id="layout_box" style="overflow:hidden" data-options="width: 650,height: 600,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false"><iframe frameborder="0" width="100%" height="100%"></iframe></div>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
// 	var opened;
	var datalist = {
			Init: function () {
	            var _this = datalist;
	
	            _this._temp.list_box = $("#datalist");
	            _this._temp.layout_box = $("#layout_box");
	        },
	        _temp: {
	        	list_box: null,
	        	layout_box: null
	        },
	        
	        Search: function () {
	            $('#datalist').datagrid('load', $(".search_box").serializeObject());
	        },
	        Add: function(){
	 			$("#layout_box iframe").attr("src", '../channel/toAddChannel.html');
	             $('#layout_box').window({
	                 title: "新增渠道"
	             });
	        	
	        },
	        Deleteasyui: function(){  	
	        },
	        sendTypeValue:function(value,rowData,index){
	        	var strs = value.split(',');
	        	var result='';
	        	for(var i=0;i<strs.length;i++){
	        		if(strs[i]==0){
	        			result +="不下发,";
	        		}else if(strs[i]==1){
	        			result +="短信,";
	        		}else if(strs[i]==2){
	        			result +="彩信,";
	        		}else if(strs[i]==3){
	        			result +="微信,";
	        		}
	        	}
	        	
	        	if(result.length>0){
	        		result = result.substring(0,result.length-1);
	        	}
	        	
	        	return result;
	        },	        
	      //关联影院
	 	   openedAdd: function(channelno) {
	    	$("#layout_box iframe").attr("src", '../channel/toSavaCinema.html?channelno=' + channelno);
	        $('#layout_box').window({
	            title: "关联影院"
	        });
	    },
	 	
	 	//关联影院
	 	 salableAdd:function (channelno) {
	    	$("#layout_box iframe").attr("src", '../channel/toSavaChannelPayChannel.html?channelno=' + channelno);
	        $('#layout_box').window({
	            title: "关联支付渠道"
	        });
	    },

	    			editPayChannel:function (channelno){
	 			$("#layout_box iframe").attr("src", '../channel/toEditChannel.html?channelno=' + channelno);
	             $('#layout_box').window({
	                 title: "编辑渠道"
	             });
	 		},
	 		//渠道状态设置
	 		updateType:function (channelno,type){
	 			var str;
	 			if(type == "1"){
	 				str = "分销";
	 			}else if(type == "2"){
	 				str = "自有";
	 			}
	 			
	 			$.messager.confirm("提示","确认" + str + "吗？",function(b){
	 				if(b){
	 					$.ajax({
	 						    cache: true,
	 				            type: "POST",
	 				            data:"channelno="+channelno+"&type="+type,
	 				            url:"../channel/updateChannel.do" ,			            
	 				            dataType: "json",
	 				            async: false,
	 				            error: function(request) {
	 				                alert("启用失败，请联系管理员！");
	 				            },
	 				            success: function(data) {
	 				               if(data.success){
	 				            	   datalist._temp.list_box.datagrid("reload");
	 	     		        		   Tips.Success("成功");
	 				               }else{
	 				            	   alert(data.errMsg);
	 				               }            
	 				            }
	 					});
	 				}
	 			});			
	 		},
	 		//开放状态设置
	 			updateOpened:function (channelno,opened){
	 			var seat;
	 			if(opened == true){
	 				seat = "开放";
	 			}else if(opened == false){
	 				seat = "关闭";
	 			}
	 			
	 			$.messager.confirm("提示","确认" + seat + "吗？",function(a){
	 				if(a){
	 					$.ajax({
	 						    cache: true,
	 				            type: "POST",
	 				            data:"channelno="+channelno+"&opened="+opened,
	 				            url:"../channel/updateChannel.do" ,			            
	 				            dataType: "json",
	 				            async: false,
	 				            error: function(request) {
	 				                alert("开放失败，请联系管理员！");
	 				            },
	 				            success: function(data) {
	 				               if(data.success){
	 				            	   datalist._temp.list_box.datagrid("reload");
	 	     		        		   Tips.Success("成功");
	 				               }else{
	 				            	   alert(data.errMsg);
	 				               }            
	 				            }
	 					});
	 				}
	 			});		
	 			
	 		},
	 		
	 			//销售状态设置
	 			updateSalable:function (channelno,salable){
	 			var sale;
	 			if(salable == true){
	 				sale = "开放";
	 			}else if(salable == false){
	 				sale = "关闭";
	 			}
	 			
	 			$.messager.confirm("提示","确认选择" + sale + "吗？",function(c){
	 				if(c){
	 					$.ajax({
	 						    cache: true,
	 				            type: "POST",
	 				            data:"channelno="+channelno+"&salable="+salable,
	 				            url:"../channel/updateChannel.do" ,			            
	 				            dataType: "json",
	 				            async: false,
	 				            error: function(request) {
	 				                alert("开放失败，请联系管理员！");
	 				            },
	 				            success: function(data) {
	 				               if(data.success){
	 				            	   datalist._temp.list_box.datagrid("reload");
	 	     		        		   Tips.Success("成功");
	 				               }else{
	 				            	   alert(data.errMsg);
	 				               }            
	 				            }
	 					});
	 				}
	 			});		
	 			}
	 		}
	 		
	 		
	 		
	 		$(function(){
	 			datalist.Init();
	 		});
	 	
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>