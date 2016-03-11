<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">

	<div id="cc" class="easyui-layout" style="width:100%;height:100%;">
    <div data-options="region:'east',title:'选择影院'" style="width:250px;height:100%;">
    	<div>
        	<input id="tree_searchbox" value="" placeholder="影院/区域/名称/编号" />
        	<input type="button" onclick="sreach();" value="搜索" />
    	</div>
    	<table id="cinema_tree"></table>
    </div>
    <div data-options="region:'center'" style="padding:5px;background:#eee;">
    <easyui:layout id="main" fit="true">
		
		<input id="cinemaName" type="hidden" value="" />
		<div id="cinemaTitle" data-options="region:'north',title:'【当前影院: 全部】'"  style="height:20%;">
		<easyui_ext:searchCondition id="search-form" >
				<easyui_ext:searchItem name="策略名称"><easyui_ext:textBox id="name" width="156" value=""></easyui_ext:textBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="策略状态">
					<%-- <easyui:comboBox id="status" value="" > </easyui:comboBox> --%>
					<select id="status" class="easyui-combobox" name="status" style="width:156px;"> 
						<option value="">全部</option> 
						<option value="1">启用</option> 
						<option value="-1">停用</option> 
						<option value="2">待信息审核</option> 
						<option value="-2">信息审核拒绝</option> 
						<option value="3">待财务审核</option> 
						<option value="-3">财务审核拒绝</option> 
						<option value="4">草稿</option> 
						<option value="-9">删除</option> 
					</select> 
				</easyui_ext:searchItem>
				<easyui_ext:searchItem name="渠道编号"><easyui_ext:textBox id="chanelNo" width="156" value=""></easyui_ext:textBox></easyui_ext:searchItem>
				<br/>
				<easyui_ext:searchItem name="有效期起">
					<input id="startDate" name="startDate" class="easyui-datebox"></input>
				</easyui_ext:searchItem>
				<easyui_ext:searchItem name="有效期止">
					<input id="endDate" name="endDate" class="easyui-datebox"></input>
				</easyui_ext:searchItem>
				<input id="cinemaNo" name="cinemaNo" type="hidden" value="" />
		</easyui_ext:searchCondition>
		</div>
		<easyui:layoutUnit region="center" border="false">
			<easyui:treegrid id="datalist" url="view.do" idField="id" treeField="name" singleSelect="true" fit="true" pagination="false" >
				<easyui:facet name="toolbar">
					<easyui_ext:facetButton id="btn_search" text="查询" plain="true" iconCls="icon-search" onclick="datalist.Search()" />
			        <easyui_ext:facetButton id="btn_add" text="添加" plain="true" iconCls="icon-add" onclick="datalist.Add()" />
			    </easyui:facet>
			    <easyui:columns>	
			        <%-- <easyui:column field="ck" width="10" checkbox="true" rowspan="2"/> --%>
			        <easyui:column field="cinemaNo" width="10" title="影院编码" rowspan="2" hidden="true"/>
			        <easyui:column field="id" title="策略ID" width="10" rowspan="2" hidden="true"/>
			       <%--  <easyui:column field="settlStrategyId" width="10" title="结算策略ID" rowspan="2" hidden="true"/> --%>
			        <easyui:column field="name" title="策略/规则" align="left" width="160" rowspan="2" formatter="function(value,rowData,index){
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
							}else {
								return value;
							}
						}"/>
			         <easyui:column field="strategyType" title="策略类型" align="center" width="60" rowspan="2" formatter="function(value,rowData,index){
							if(value == '0'){
								return '全局';
							}else if(value == '1'){                                     
								return '分销商';
							}else {
								return value;
							}
						}"/>
			         <easyui:column field="saleType" title="定价类型" align="center" width="60" rowspan="2" formatter="function(value,rowData,index){
							if(value == '0'){
								return '常规价';
							}else if(value == '1'){                                     
								return '忙时价';
							}else if(value == '2'){
								return '闲时价';
							}else {
								return value;
							}
						}"/>
			         <easyui:column title="销售价格" width="135" colspan="2" align="center"/>
			         <easyui:column field="pointAmount" title="销售点数(点)" width="70" rowspan="2" align="center"/>
			         <easyui:column field="paperAmount" title="销售张数(张)" width="70" rowspan="2" align="center"/>
			         <easyui:column field="exchangeAmount" title="兑换2D张数(张)" width="90" rowspan="2" align="center"/>
			        <easyui:column field="date" title="有效期限" width="160" rowspan="2" align="center" formatter="function(value, rowData, index){
			        	if(rowData.startDate !='' && rowData.startDate!=null){
			        		return rowData.startDate + ' ~ ' + rowData.endDate;
			        	}
			        }"/>
			        <easyui:column field="status" align="center" title="状态" width="80" rowspan="2" formatter="function(value,rowData,index){
							if(value == '1'){
								return '启用';
							}else if(value == '-1'){                                     
								return '停用';
							}else if(value == '2'){
								return '待信息审核';
							}else if(value == '-2'){
								return '信息审核拒绝';
							}else if(value == '3'){
								return '待财务审核';
							}else if(value == '-3'){
								return '财务审核拒绝';
							}else if(value == '4'){
								return '草稿';
							}else if(value == '-9'){
								return '删除';
							}else {
								return value;
							}
						}"/>
			       <%--  <easyui:column field="_handler" title="操作" width="150" align="center" formatter="datalist.formatter" rowspan="2"/> --%>
			         <easyui_ext:formatterColumn title="操作" param="value, rowData, index" width="150" rowspan="2">
			        	<easyui_ext:handlerColumn text="渠道" onclick="datalist.EditChannel" param="rowData.id,'rowData.name'" condition="!rowData.isRuleStrategy && rowData.strategyType=='1'"/>
			        	<easyui_ext:handlerColumn text="编辑" onclick="datalist.Edit" param="rowData.id,rowData.cinemaNo" condition="rowData.status == '-2' || rowData.status == '-3'"/>
			        	<easyui_ext:handlerColumn text="信息审核" onclick="datalist.infomationView" param="rowData.id,rowData.status" condition="rowData.status == '2'" />
			        	<easyui_ext:handlerColumn text="财务审核" onclick="datalist.infomationView" param="rowData.id,rowData.status" condition="rowData.status == '3'" />
			        	<easyui_ext:handlerColumn text="编辑" onclick="datalist.Edit" param="rowData.id,rowData.cinemaNo" condition="!rowData.isRuleStrategy && rowData.status == '-1'"/>
			        	<easyui_ext:handlerColumn text="启用" onclick="datalist.strategyOnOffDel" param="rowData.id,rowData.status,rowData.isRuleStrategy" condition="rowData.status == '-1'" />
			        	<easyui_ext:handlerColumn text="禁用" onclick="datalist.strategyOnOffDel" param="rowData.id,rowData.status,rowData.isRuleStrategy" condition="rowData.status == '1'" />
			        	<easyui_ext:handlerColumn text="删除" onclick="datalist.strategyDel" param="rowData.id,rowData.isRuleStrategy" condition="rowData.status == '-1'" />
			        </easyui_ext:formatterColumn>
			    </easyui:columns>
			    <easyui:columns>
			    	<easyui:column field="type" title="定价方式" width="65" align="center" formatter="function(value,rowData,index){
							if(value == '0'){
								return '固定价';
							}else if(value == '1'){                                     
								return '固定加价';
							}else if(value == '2'){
								return '折扣价';
							}else {
								return value;
							}
						}"/>
			        <easyui:column field="salePrice" title="销售价格" width="70" align="center" formatter="function(value,rowData,index){
							if(rowData.type == '0'){
								return value+'元';
							}else if(rowData.type == '1'){
								if(value.indexOf('-')!=-1){
									return value+'元';
								}else{
									return '+'+value+'元';
								}
							}else if(rowData.type == '2'){
								return value+'折';
							}else {
								return value;
							}
						}"/>
			    </easyui:columns>
			</easyui:treegrid>
		</easyui:layoutUnit>
	</easyui:layout>
	<div id="layout_box" style="overflow:hidden" data-options="width: 788,height: 400,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false">
		<iframe frameborder="0" width="100%" height="100%"></iframe>
	</div>
    
    </div>
</div>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
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
	        	/* var cinemaNo = $("#cinemaNo").attr("value");
	        	var name = $("#name").textbox("getValue");
	        	$('#datalist').treegrid('load', {cinemaNo:cinemaNo,name:name,startDate:startDate,endDate:endDate,status:status,chanelNo:chanelNo}); */
	        	$('#datalist').treegrid('load',$('#search-form').serializeObject());
	        },
	        Add: function(){
	        	var cinemaNo = $("#cinemaNo").attr("value");
	        	if(cinemaNo){
		        	$("#layout_box iframe").attr("src", "add.html?cinemaNo="+cinemaNo);
		            $('#layout_box').window({
		                title: "添加销售策略"
		            });
	        	}else{
	        		alert("您还未选择影院");
	        	}
	            
	        },
	        Edit: function(id,cinemaNo) {
	        	//var cinemaNo = $("#cinemaNo").attr("value");
	        	if(cinemaNo!=null){
		        	$("#layout_box iframe").attr("src", "edit.html?id=" + id+"&cinemaNo="+cinemaNo);
		            $('#layout_box').window({
		                title: "编辑销售策略"
		            });
	        	}else{
	        		alert("您还未选择影院");
	        	}
	        },
	        EditChannel: function(id,name) {
	        	
	        	$("#layout_box iframe").attr("src",encodeURI(encodeURI("editChannel.html?saleStrategyId=" + id+"&strategyName="+name)));
	            $('#layout_box').window({
	                title: "关联渠道"
	            });
	        },
	        strategyOnOffDel: function(id,status,isRuleStrategy){
	        	if(status == '-1'){
					// 启用
					$.messager.confirm('提示','确认启用？',function(r){    
					    if (r){    
					    	submitFn('strategyOnOffDel.do',{id:id,status:status,isRuleStrategy:isRuleStrategy});    
					    }    
					});
				}else if(status == '1'){
					// 停用
					$.messager.confirm('提示','确认停用？',function(r){    
					    if (r){    
					    	submitFn('strategyOnOffDel.do',{id:id,status:status,isRuleStrategy:isRuleStrategy});  
					    }    
					});
				}
	        },
	        strategyDel:function(id,isRuleStrategy){
	        	var status = "-9";
	        	 //删除策略
				$.messager.confirm('提示','确认删除？',function(r){    
				    if (r){    
				    	submitFn('strategyOnOffDel.do',{id:id,status:status,isRuleStrategy:isRuleStrategy});    
				    }    
				});
	        },
	        infomationView: function(id,status){
	        	$("#layout_box iframe").attr("src", "infomationView.html?id=" + id+"&status="+status);
	        	if(status == '3'){
					// 财务审核
					$("#layout_box iframe").attr("src", "infomationView.html?id=" + id+"&status="+status);
		            $('#layout_box').window({
		                title: "销售策略财务审核"
		            });
				}else if(status == '2'){
					// 信息审核
					$("#layout_box iframe").attr("src", "infomationView.html?id=" + id+"&status="+status);
		            $('#layout_box').window({
		                title: "销售策略信息审核"
		            });
				}
	        }/* ,
	        formatter: function(value, rowData, index){
	        	var text = "";
	        	if(!rowData.isRuleStrategy && rowData.strategyType=='1'){
	        	text += "<a class=\'g-mlr-5\' onclick=\'datalist.EditChannel(" + rowData.id +", \"" + rowData.name+ "\")\'>编辑渠道</a>&nbsp;";
	        	}
	        	if(rowData.status == '-2' || rowData.status == '-3'){
	        		text += "<a class=\'g-mlr-5\' onclick=\'datalist.Edit(" + rowData.id + ", " + rowData.cinemaNo+ ")\'>编辑</a>";
	        	}
	        	if(rowData.status == '2'){
	        		text += "<a class=\'g-mlr-5\' onclick=\'datalist.infomationView(" + rowData.id +", " + rowData.status+ ")\'>信息审核</a>";
	        	}
	        	if(rowData.status == '3'){
	        		text += "<a class=\'g-mlr-5\' onclick=\'datalist.infomationView(" + rowData.id +", " + rowData.status+ ")\'>财务审核</a>";
	        	}
	        	if(rowData.status == '-1'){
	        		if(!rowData.isRuleStrategy){
		        		text += "<a class=\'g-mlr-5\' onclick=\'datalist.strategyOnOffDel(" + rowData.id +", " + rowData.status+ ", " + rowData.isRuleStrategy+ ")\'>启用</a>&nbsp";
		        		text += "<a class=\'g-mlr-5\' onclick=\'datalist.Edit(" + rowData.id + ", " + rowData.cinemaNo+ ")\'>编辑</a>&nbsp;"; 
		        		text += "<a class=\'g-mlr-5\' onclick=\'datalist.strategyDel(" + rowData.id + ", " + rowData.isRuleStrategy+ ")\'>删除</a>";
	        		}else{
	        			text += "<a class=\'g-mlr-5\' onclick=\'datalist.strategyOnOffDel(" + rowData.id +", " + rowData.status+ ", " + rowData.isRuleStrategy+ ")\'>启用</a>&nbsp";
	        			text += "<a class=\'g-mlr-5\' onclick=\'datalist.strategyDel(" + rowData.id + ", " + rowData.isRuleStrategy+ ")\'>删除</a>";
	        		}
	        	}
	        	if(rowData.status == '1'){
	        		text += "<a class=\'g-mlr-5\' onclick=\'datalist.strategyOnOffDel(" + rowData.id +", " + rowData.status+ ", " + rowData.isRuleStrategy+ ")\'>停用</a>";
	        	}
	        	return text; 
	        } */
		};
		
		function submitFn(url,params){
			$.post(url,params,function(data){
				//console.info(JSON.stringify(data));
				if(data){
					data = JSON.parse(data);
					$.messager.alert('提示',data.message,data["isSuccess"]?'info':'error',function(){
						$('#datalist').treegrid('reload');
					});
				}else{
					$.messager.alert('提示','操作失败!');    
				}
			});
		}
		
		$(function(){
			datalist.Init();
			tree.load();
			
		});
		var tree = {
			load:function(){
				$('#cinema_tree').tree({
				url : 'getCinemaTree.do',
				idField : 'id',
				treeField : 'text',
				parentField : 'pid',
				fit : true,
				fitColumns : true,
				border : false,
				Columns : [ [ {
					title : '编号',
					field : 'id',
					width : 150,
					hidden : true
				}, {
					field : 'text',
					title : '资源名称',
					width : 200
				} ] ],
				onClick: function (node) {					 
					 tree.selected(node);
				 },
				 onLoadSuccess:function(node,data){
					 tree.expandTo($('#cinema_tree').tree('getChildren')[2]);//获取第一个影院并展开
				 }
				});	
			},
			expandTo:function(node){
				$('#cinema_tree').tree('expandTo', node.target).tree('select', node.target);
				tree.selected(node);
			},
			selected:function(node){
				if ($(node).tree('isLeaf', node.target)) {
					 $('#datalist').treegrid('load', {cinemaNo:node.id});
					 $("#cinemaNo").val(node.id);
					 $("#cinemaName").val(node.text);
					 $('#cinemaTitle').panel({
		                title: "【当前影院：" + (node.text == "" ? "全部" : node.text) + "】【影院编号：" + (node.id == "" ? "全部" : node.id) + "】"
		             });
					 $("#name").textbox("setValue", "");
					 $("#status").combobox("setValue", "");
					 $("#chanelNo").textbox("setValue", "");
					 $("#startDate").datebox("setValue", "");
					 $("#endDate").datebox("setValue", "");
				 } 
			}
		};
		function sreach() {
			var value=$('#tree_searchbox').val();
            if (value == '' || value==null) {
                tree.load();
            } else {
                $('#cinema_tree').tree({
                    url:encodeURI(encodeURI("searchCinemaTree.do?value="+value)) ,
                    idField : 'id',
    				treeField : 'text',
    				parentField : 'pid',
    				fit : true,
    				fitColumns : true,
    				border : false,
    				Columns : [ [ {
    					title : '编号',
    					field : 'id',
    					width : 150,
    					hidden : true
    				}, {
    					field : 'text',
    					title : '资源名称',
    					width : 200
    				} ] ]
                });
            }
        }
	</script>
	<script type="text/javascript">
	/**
	 * @author 夏悸
	 * 
	 * @requires jQuery,EasyUI
	 * 
	 * 扩展tree，使其支持平滑数据格式
	 */
	$.fn.tree.defaults.loadFilter = function(data, parent) {
		var opt = $(this).data().tree.options;
		var idFiled, textFiled, parentField;
		if (opt.parentField) {
			idFiled = opt.idFiled || 'id';
			textFiled = opt.textFiled || 'text';
			parentField = opt.parentField;
			var i, l, treeData = [], tmpMap = [];
			for (i = 0, l = data.length; i < l; i++) {
				tmpMap[data[i][idFiled]] = data[i];
			}
			for (i = 0, l = data.length; i < l; i++) {
				if (tmpMap[data[i][parentField]]
						&& data[i][idFiled] != data[i][parentField]) {
					if (!tmpMap[data[i][parentField]]['children'])
						tmpMap[data[i][parentField]]['children'] = [];
					data[i]['text'] = data[i][textFiled];
					tmpMap[data[i][parentField]]['children'].push(data[i]);
				} else {
					data[i]['text'] = data[i][textFiled];
					treeData.push(data[i]);
				}
			}
			return treeData;
		}
		return data;
	};

</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>