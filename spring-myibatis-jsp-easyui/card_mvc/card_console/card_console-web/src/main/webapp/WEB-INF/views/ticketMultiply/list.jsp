<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main" fit="true">
		<easyui_ext:searchCondition id="search-form">
		        <easyui_ext:searchItem name="通兑票编码"><easyui_ext:textBox id="code" name="code" value=""  /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="通兑票名称"><easyui_ext:textBox id="name" name="name" value=""  /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="模版名称"><easyui_ext:comboBox id="template_id" data="${templateList }" name="template_id" value=""  /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="状态"><easyui_ext:comboBox id="status" data="${statusList }" value="" name="status" /></easyui_ext:searchItem>
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="datalist" url="view.do" fit="true" pageSize="30" pagination="true"  checkOnSelect="false">
				<easyui:facet name="toolbar">
					<easyui_ext:facetButton id="btn_search" text="查询" plain="true" iconCls="icon-search" onclick="datalist.Search()" />
					<easyui_ext:facetButton id="btn_search2" text="添加" plain="true" iconCls="icon-add" onclick="datalist.Add()"/>
					<easyui_ext:facetButton id="btn_search3" text="删除" plain="true" iconCls="icon-delete" onclick="datalist.Delete()"/>
			    </easyui:facet>
			     <easyui:columns frozen="true">
			        <easyui:column field="ck" checkbox="true"/>
			        <easyui:column field="code" width="80" title="通兑票编号"/>
			        <easyui:column field="name" width="80" title="通兑票名称"/>
			        <easyui:column field="channel_type" width="80" title="渠道类型" formatter="function(value,row,index){
			        	if(value=='0'){
			        		return '全部渠道'
			        	}else{
			        		return '限制渠道'
			        	}
			        	
			        }"/>
			        <easyui:column field="statusValue" width="80" title="状态"/>
			    </easyui:columns>
			    <easyui:columns>
			    	<easyui:column field="sale_price" width="80" title="销售价格" formatter="MoneyFormatYuan"/>
			        <easyui:column field="price_type" width="80" title="定价类型" formatter="function(value,rowData,index){
			        	if(value=='0'){
			        		return '常规价';
			        	}else if(value=='1'){
			        		return '忙时价';
			        	}else if(value=='2'){
			        		return '闲时价';
			        	}
			        }"/>
			        <easyui:column field="valid_days" width="80" title="有效期(天)"/>
			        <easyui:column field="valid_stime" width="80" title="有效期起"/>
			        <easyui:column field="valid_etime" width="80" title="有效期止"/>
			        <easyui:column field="template_name" width="80" title="兑换模版"/>
			        <easyui:column field="create_date" width="80" title="创建时间"/>
			        <easyui:column field="check_date" width="80" title="审核时间"/>
			        <easyui:column field="checker" width="80" title="审核人"/>
			        <easyui:column field="memo" width="80" title="简要说明"/>
			        <easyui_ext:formatterColumn title="操作" param="value, rowData, index" >
			         	<easyui_ext:handlerColumn text="编辑" onclick="datalist.Edit" param="rowData.code" condition="rowData.status == '0' || rowData.status == '-1' || rowData.status == '-2' || rowData.status == '-3'" />
			         	<easyui_ext:handlerColumn text="信息审核" onclick="datalist.infoCheck" param="rowData.code" condition="rowData.status == '1'" />
			         	<easyui_ext:handlerColumn text="财务审核" onclick="datalist.financialCheck" param="rowData.code" condition="rowData.status == '2' " />
			         	<easyui_ext:handlerColumn text="上架" onclick="datalist.Shelves" param="rowData.code" condition="rowData.status == '-3' " />
			         	<easyui_ext:handlerColumn text="下架" onclick="datalist.Offshelves" param="rowData.code" condition="rowData.status == '3' " />
			         </easyui_ext:formatterColumn>
			        <%-- <easyui:column field="_handler" width="80" title="操作" formatter="function(value, rowData, index){
			        	
			        	var text = '&nbsp;&nbsp;&nbsp;&nbsp;';
			        	if(rowData.status == '0' || rowData.status == '-1' || rowData.status == '-2' || rowData.status == '-3'){
			        		text += '<a cloumnid='+rowData.code+' id=singleSale_edit_btn>编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;';
			        	}
			        	if(rowData.status == '1'){
			        		text += '<a cloumnid='+rowData.code+' id=singleSale_infocheck_btn>信息审核</a>&nbsp;&nbsp;&nbsp;&nbsp;';
			        	}
			        	if(rowData.status == '2'){
			        		text += '<a cloumnid='+rowData.code+' id=singleSale_moneycheck_btn>财务审核</a>&nbsp;&nbsp;&nbsp;&nbsp;';
			        	}
			        	if(rowData.status == '-3'){
			        		text += '<a cloumnid='+rowData.code+' id=singleSale_shelves_btn>上架</a>&nbsp;&nbsp;&nbsp;&nbsp;';
			        	}
			        	if(rowData.status == '3'){
			        		text += '<a cloumnid='+rowData.code+' id=singleSale_offshelves_btn>下架</a>&nbsp;&nbsp;&nbsp;&nbsp;';
			        	}
			
			        	return text;
			       }" /> --%>
			    </easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
	var basePath = "${pageContext.request.contextPath}";
		var datalist = {
				_temp: {	        	
		        	layout_box: null,
		        	list_box:null
		        },
			Init: function () {
	            var _this = List;
	
	            _this._temp.list_box = $("#list-grid");
	            _this._temp.layout_box = $("#layout_box");
	            _this._temp.layout_productbox = $("#layout_productbox");
	        },
	        Search: function () {
	            $('#datalist').datagrid('load', $(".search_box").serializeObject());
	        },
	        Add: function(){
	        	parent.openTab('添加多家通兑票', '../ticketMultiply/add.html');
	        },
	        Edit:function(id){
	        	parent.openTab('多家通兑票编辑', basePath+'/ticketMultiply/update.html?code='+id);
	        },
	        Delete: function(){
	        	var obj = $('#datalist').datagrid('getSelections');
				var info = "";
			    var msg = "通兑票：<br/>";
			    var isbo = true;
			    var newStatus = -9;
				if(obj.length == 0){
					$.messager.alert('信息提示','请选择要删除的多家通兑票！','success',null);
					return;
				}
				$.messager.confirm("提示","确认删除吗？",function(b){
					if(b){
						  $.each(obj, function () {//循环获得订单号并检查通兑票
		                        if (this.status == -1 || this.status == 4 || this.status == -2 || this.status == -3 || this.status ==0) {
		                            if (info == "") {
		                                info = this.code;
		                            }
		                            else {
		                                info += "," + this.code;
		                            }
		                        }
		                        else {
		                            isbo = false;
		                            msg += "[" + this.name + "][" + this.code + "]<br/>";
		                        }
		                    });
						  if (isbo) {
		                        var url = '../ticketMultiply/delete.do';
		                        $.get(url, { 'codes': info, 'status': newStatus, '_': Math.random() }, function (data) {
		                            data = eval("(" + data + ")");
		                            if (data.success) {
		                              
		                                $('#datalist').datagrid('reload');
		                            }
		                            else {
		                                var msg = data.errMsg;
		                                $.messager.alert('提示', msg);
		                            }
		                        });
		                    }
		                    else {
		                        msg += "选择信息状态不是草稿、下架、审核拒绝状态不可以删除";
		                        $.messager.alert('提示', msg);
		                    }
					}
				});	
	        },
	        infoCheck:function(id){//信息审核
	        	parent.openTab('信息审核', basePath+'/ticketMultiply/redirect/infoAudit.html?code='+id);
	        },
	        financialCheck:function(id){//财务审核
	        	parent.openTab('财务审核', basePath+'/ticketMultiply/redirect/financeAudit.html?code='+id);
	        },
	        Shelves:function(id){//上架
	        	$.messager.confirm('提示','确认上架？',function(r){    
				    if (r){    
				    	submitFn('ticketMultiply/shelves.do',{code:id});    
				    }    
				});
	        },
	        Offshelves:function(id){//下架
	        	$.messager.confirm('提示','确认下架？',function(r){    
				    if (r){    
				    	submitFn('ticketMultiply/offshelves.do',{code:id});     
				    }    
				});
	        }
		};

		
	$(function(){
			// 监听编辑/上下架/审核 
		datalist.Init();
			
		});
		
		function submitFn(url,params){
			$.post(basePath+'/'+url,params,function(data){
				console.info(JSON.stringify(data));
				if(data){
					data = JSON.parse(data);
						$.messager.alert('提示',data.message);
						$('#datalist').datagrid('reload');
				}else{
					$.messager.alert('提示','操作失败!');    
				}
			});
		}
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>