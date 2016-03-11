<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">通兑票结算策略管理</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<div id="cc" class="easyui-layout" style="width:100%;height:100%;">
	<div data-options="region:'east',title:'选择影院',split:true" style="width:250px;height:100%;">
		<div>
        	<input id="tree_searchbox" value="" placeholder="影院/区域/名称/编号" />
        	<input type="button" onclick="treeSreach();" value="搜索" />
    	</div>
		 <table id="right_tree" class="tree"></table>
	</div>
		<div data-options="region:'center'" style="padding:5px;background:#eee;border-style: 0px;border: 0px">
		<easyui:layout id="strategy_query_main" fit="true">
			
			<div data-options="region:'north',title:'全部影院',split:true,collapsible:false"  style="height:20%;border: 0px;" id="mynorth">
				<easyui_ext:searchCondition id="search-form">
						<easyui_ext:searchItem name="策略名称"><easyui_ext:textBox id="name" width="160px"/></easyui_ext:searchItem>
						<easyui_ext:searchItem name="策略状态"><easyui_ext:comboBox id="status" name="status" width="160px" data="<%= com.mopon.controller.settlStrategy.SaleCommonSettlStrategyController.getDemoList() %>" /></easyui_ext:searchItem>
						<br/>
						<easyui_ext:searchItem name="有效期起"><easyui:inputDate id="startDate" name="startDate" value="" style="width:160px;"/></easyui_ext:searchItem>
						<easyui_ext:searchItem name="有效期止"><easyui:inputDate id="endDate" name="endDate" value=""  style="width:160px;"/></easyui_ext:searchItem>
						<input id="cinemaNo" name="cinemaNo" type="hidden" value=""/>
				</easyui_ext:searchCondition>
			</div>
				<easyui:layoutUnit region="center" border="false">
				<easyui:treegrid id="datalist" url="strategyList.do" fit="true" idField="id"  treeField="name" singleSelect="true" >
					<easyui:facet name="toolbar">
						<easyui_ext:facetButton id="btn_search" text="查询" plain="true" iconCls="icon-search" onclick="datalist.Search()" />
				        <easyui_ext:facetButton id="btn_add" text="添加" plain="true" iconCls="icon-add" onclick="datalist.Add()" />
				    </easyui:facet>
				    <easyui:columns>
				      	<easyui:column field="id" title="策略id" width="10" align="center" hidden="true"/>
				      	<easyui:column field="rowType" title="策略/规则" width="10" align="center" hidden="true"/>
				        <easyui:column field="name" align="left" title="策略/规则" width="180" formatter="function(value,rowData,index){
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
				        <easyui:column field="settlType" align="center" title="定价类型" width="80" formatter="function(value,rowData,index){
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
				        <easyui:column field="settlPrice" align="center" width="80"  title="结算价格" />
				        <easyui:column field="date" align="center" width="180" title="有效期限"  formatter="function(value, rowData, index){
				        	if(rowData.startDate !='' && rowData.startDate!=null){
				        		return rowData.startDate + ' ~ ' + rowData.endDate;
				        	}
			       		 }"/>
				         <easyui:column field="status" align="center" width="80" title="状态" formatter="function(value,row,index){
								if(value == '1'){
									return '启用';
								}else if(value == '-1'){
									return '禁用';
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
							
						<easyui_ext:formatterColumn field="_handler" title="操作" width="140px" align="center" param="value, rowData, index">
		        			<easyui_ext:handlerColumn text="编辑"  onclick="datalist.EditStrategy" param="rowData.id" condition="rowData.rowType=='1' && (rowData.status == '-2' || rowData.status == '-3'||rowData.status == '-1')"/>
							<easyui_ext:handlerColumn text="信息审核" onclick="datalist.Infocheck" param="rowData.id" condition="rowData.rowType=='1'&&rowData.status == '2'" />
							<easyui_ext:handlerColumn text="财务审核" onclick="datalist.Moneycheck" param="rowData.id" condition="rowData.rowType=='1'&&rowData.status == '3'" />
							<easyui_ext:handlerColumn text="启用" onclick="datalist.StatStrategy" param="rowData.id" condition="rowData.rowType=='1'&&rowData.status == '-1'" />
				        	<easyui_ext:handlerColumn text="禁用" onclick="datalist.OffStrategy" param="rowData.id" condition="rowData.rowType=='1'&&rowData.status == '1'" />
				        	<easyui_ext:handlerColumn text="删除" onclick="datalist.DeleteStrategy" param="rowData.id" condition="rowData.rowType=='1'&&rowData.status == '-1'"/>
				        	<easyui_ext:handlerColumn text="启用" onclick="datalist.StatRule" param="rowData.id" condition="rowData.rowType=='2'&&rowData.status == '-1'" />
				        	<easyui_ext:handlerColumn text="禁用" onclick="datalist.OffRule" param="rowData.id" condition="rowData.rowType=='2'&&rowData.status == '1'" />
				        	<easyui_ext:handlerColumn text="删除" onclick="datalist. DeleteRule" param="rowData.id" condition="rowData.rowType=='2'&&rowData.status == '-1'"/>
			        	</easyui_ext:formatterColumn>
				        <%-- <easyui:column field="_handler" align="center" width="140" title="操作" formatter="function(value, rowData, index){
				        	
				        	var text = '&nbsp;&nbsp;&nbsp;&nbsp;';
				        	if(rowData.rowType=='1' ){
					        	if(rowData.rowType=='1' && (rowData.status == '-2' || rowData.status == '-3')){
					        		text += '<a cloumnid='+rowData.id+' id=singleSale_edit_btn >编辑</a>&nbsp;&nbsp;&nbsp;';
					        	}
					        	if(rowData.rowType=='1'&&rowData.status == '2'){
					        		text += '<a cloumnid='+rowData.id+' id=singleSale_infocheck_btn onclick=datalist.infomationReview(rowData.id)>信息审核</a>&nbsp;&nbsp;&nbsp;&nbsp;';
					        	}
					        	if(rowData.status == '3'){
					        		text += '<a cloumnid='+rowData.id+' id=singleSale_moneycheck_btn>财务审核</a>&nbsp;&nbsp;&nbsp;&nbsp;';
					        	}
					        	if(rowData.status == '-1'){
					        		text += '<a cloumnid='+rowData.id+' id=singleSale_shelves_btn>启用</a>&nbsp;&nbsp;&nbsp;';
					        	}
					        	if(rowData.status == '1'){
					        		text += '<a cloumnid='+rowData.id+' id=singleSale_offshelves_btn>停用</a>&nbsp;&nbsp;&nbsp;&nbsp;';
					        	}if(rowData.status == '-1'){
					        		
					        		text += '<a cloumnid='+rowData.id+' id=singleSale_edit_btn >编辑</a>&nbsp;&nbsp;&nbsp;';
					        	}if(rowData.status == '-1'){
					        		text += '<a cloumnid='+rowData.id+' id=singleSale_delete_btn>删除</a>&nbsp;&nbsp;&nbsp;&nbsp;';
					        	}
				        	}else if(rowData.rowType=='2'){
				        		if(rowData.status == '-1'){
					        		text += '<a cloumnid='+rowData.id+' id=singleSale_rule_btn>启用</a>&nbsp;&nbsp;&nbsp;';
					        	}
					        	if(rowData.status == '1'){
					        		text += '<a cloumnid='+rowData.id+' id=singleSale_offrule_btn>停用</a>&nbsp;&nbsp;&nbsp;&nbsp;';
					        	}if(rowData.status == '-1'){
					        		text += '<a cloumnid='+rowData.id+' id=singleSale_deleterule_btn>删除</a>&nbsp;&nbsp;&nbsp;&nbsp;';
					        	}
				        	}
				
				        	return text;
				       }" /> --%>
				    </easyui:columns>
				</easyui:treegrid>
			</easyui:layoutUnit>
		</easyui:layout>
		<div id="layout_box" style="overflow:hidden" data-options="width: 820,height: 450,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false"><iframe frameborder="0" width="100%" height="100%"></iframe></div>
		</div>
	</div>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
	// 请求根路径
	var basePath = "<%=request.getContextPath()%>";
	
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
	        	//刷新
	            $('#datalist').treegrid('load', $("#search-form").serializeObject());
	        },
	        Add: function(){
	        	//添加
	        	var cinemaNo = $("#cinemaNo").attr("value");
	        	if(cinemaNo==""){
	        		alert("请先选择一个影院");
	        		return;
	        	}else{
		        	$("#layout_box iframe").attr("src", "add.html?cinemaNo="+cinemaNo);
		            $('#layout_box').window({
		                title: "添加结算策略"
		            });
	        	}
	        }, 
	        StatStrategy:function(idObj){
	        	//启用策略
	        	$.messager.confirm('提示','确认启用？',function(r){    
				    if (r){    
				    	submitFn('settlStrategy/strategyUp.do',{id:idObj});    
				    }    
				});
	        },
	        OffStrategy:function(idObj){
	        	//停用策略
				$.messager.confirm('提示','确认停用？',function(r){    
				    if (r){    
				    	submitFn('settlStrategy/strategyOff.do',{id:idObj});     
				    }    
				});
	        },
	        Infocheck:function(idObj){
	        	// 信息审核
				$("#layout_box iframe").attr("src", basePath+'/settlStrategy/intoInfoCheck.html?id='+idObj);
	            $('#layout_box').window({
	                title: "结算策略信息审核"
	            });
	        },
	        Moneycheck:function(idObj){
	        	// 财务审核
				$("#layout_box iframe").attr("src", basePath+'/settlStrategy/intoMoneycheck.html?id='+idObj);
	            $('#layout_box').window({
	                title: "结算策略财务审核"
	            });
	        },
	        EditStrategy:function(idObj){
	        	//编辑策略
	        	$("#layout_box iframe").attr("src", basePath+'/settlStrategy/strategyEdit.html?id='+idObj);
	            $('#layout_box').window({
	                title: "结算策略编辑"
	            });
	        },
	       DeleteStrategy:function(idObj){
	    	 //删除策略
				$.messager.confirm('提示','确认删除？',function(r){    
				    if (r){    
				    	submitFn('settlStrategy/deleteStrategy.do',{id:idObj});    
				    }    
				});
	       },
	      StatRule:function(idObj){
	    	// 启用规则
				$.messager.confirm('提示','确认启用？',function(r){    
				    if (r){    
				    	submitFnRule('settlStrategy/strategyUpdateRule.do',{id:idObj,status:"1"});    
				    }    
				}); 
	      },
	      OffRule:function(idObj){
	    	//停用规则
				$.messager.confirm('提示','确认启用？',function(r){    
				    if (r){    
				    	submitFnRule('settlStrategy/strategyUpdateRule.do',{id:idObj,status:"-1"});    
				    }    
				});
	      },
	      DeleteRule:function(idObj){
	    	//删除规则
				$.messager.confirm('提示','确认删除？',function(r){    
				    if (r){    
				    	submitFnRule('settlStrategy/deleteRule.do',{id:idObj});    
				    }    
				}); 
	      }
		};
	
	var tree={
			load:function(){
				$('#right_tree').tree({
					url : '../settlStrategy/getTree.do',
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
						 tree.expandTo($('#right_tree').tree('getChildren')[2]);//获取第一个影院并展开
					 }
				});				
			},
			expandTo:function(node){
				$('#right_tree').tree('expandTo', node.target).tree('select', node.target);
				tree.selected(node);
			},
			selected:function(node){
				 if ($(node).tree('isLeaf', node.target)) {
					 $('#datalist').treegrid('load', {cinemaNo:node.id});
					 $("#cinemaNo").val(node.id);
					 $("#cinemaName").val(node.text);
				 }
				/*  $('#mynorth').layout('panel', 'left').panel({ title: "【当前影院：" + (node.id != "" ? "全部" : node.text) + "】【影院编号：" + (node.id != "" ? "全部" : node.id) + "】" }); */
			 $('#mynorth').panel({
	                    title: "【当前影院：" + (node.id == "" ? "全部" : node.text) + "】【影院编号：" + (node.id == "" ? "全部" : node.id) + "】"}); 
				
			 $("#name").textbox("setValue", "");
			 $("#status").combobox("setValue", "");
			 $("#startDate").datebox("setValue", "");
			 $("#endDate").datebox("setValue", "");
			}
	}
		
		$(function(){

			tree.load();
				  
			datalist.Init();
		});
	function treeSreach() {
		var value=$('#tree_searchbox').val();
        if (value == '' || value==null) {
            tree.load();
        } else {
            $('#right_tree').tree({
                url: encodeURI(encodeURI("../settlStrategy/searchTree.do?value="+value)),
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
	
	function submitFnRule(url,params){

		$.post(basePath+'/'+url,params,function(data){
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
	
		function submitFn(url,params){
			$.post(basePath+'/'+url,params,function(data){
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