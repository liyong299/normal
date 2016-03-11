<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="singleSale_query_main" fit="true">
		<easyui_ext:searchCondition id="search_box">
				<easyui_ext:searchItem name="通兑票名称"><easyui_ext:textBox id="singleSale_query_name" name="name" value=""  /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="规格"><easyui_ext:comboBox id="singleSale_query_show_type" name="show_type" data="${showTypeList }" value=""  panelMaxHeight="200" /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="状态"><easyui_ext:comboBox id="singleSale_query_status" name="status" data="${statusList }" value=""  panelMaxHeight="200" /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="影院"><easyui_ext:comboBox id="singleSale_query_cinemano" name="cinemano" data="${cinemaList }" value=""  panelMaxHeight="200" /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="创建时间"><easyui:inputDate id="singleSale_query_create_date_bagin" name="create_date_begin" value=""  />到:<easyui:inputDate id="singleSale_query_create_date_end" name="create_date_end" value=""  /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="有效期限"><easyui:inputDate id="singleSale_query_valid_stime" name="valid_stime" value=""  />到:<easyui:inputDate id="singleSale_query_valid_etime" name="valid_etime" value=""  /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="有效期限(天)"><easyui_ext:textBox id="singleSale_query_valid_days_begin" name="valid_days_begin" value=""  />到:<easyui_ext:textBox id="singleSale_query_valid_days_end" name="valid_days_end" value=""  /></easyui_ext:searchItem>
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="datalist" striped="true" rownumbers="true" url="${pageContext.request.contextPath }/sale/querySingleSale.do?ticket_type=1" fit="true" pageSize="30" pagination="true" checkOnSelect="false" >
				<easyui:facet name="toolbar">
					<easyui_ext:facetButton id="btn_search" text="查询" plain="true" iconCls="icon-search"  onclick="datalist.Search()"/>
			    </easyui:facet>
			   <easyui:columns frozen="true">
			        <easyui:column field="ck" checkbox="true"/>
			        <easyui:column field="name" align="center" width="80" title="通兑票名称"/>
			        <easyui:column field="code" align="center" width="80" title="通兑票编码"/>
			        <easyui:column field="show_type" align="center" width="80" title="规格" formatter="function(value,row,index){
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
			        <easyui:column field="status" align="center" width="80" title="状态" formatter="function(value,row,index){
							if(value == '0'){
								return '待编辑';
							}else if(value == '1'){
								return '待信息审核';
							}else if(value == '-1'){
								return '信息审核拒绝';
							}else if(value == '2'){
								return '待财务审核';
							}else if(value == '-2'){
								return '财务审核拒绝';
							}else if(value == '3'){
								return '上架';
							}else if(value == '-3'){
								return '下架';
							}else {
								return value;
							}
							
						}"/>
			    </easyui:columns>
			    <easyui:columns>
			        <easyui:column field="is_special_price" align="center" width="80" title="是否特殊结算" formatter="function(value, rowData, index){
			        	if(value == 0){
			        		return '否';
			        	}else if(value== 1){
			        		return '是';
			        	}else{
			        		return value;
			        	}
			        }"/>
			        <easyui:column field="price_type" align="center" width="80" title="定价类型" formatter="function(value, rowData, index){
			        	if(value == 0){
			        		return '常规价';
			        	}else if(value == 1){
			        		return '忙时价';
			        	}else if(value == 2){
			        		return '闲时价';
			        	}else{
			        		return value;
			        	}
			        }"/>
			        <easyui:column field="valid_days" align="center" width="80" title="有效期(天)"/>
			        <easyui:column field="valid_stime" align="center" width="80" title="有效期起"/>
			        <easyui:column field="valid_etime" align="center" width="80" title="有效期止"/>
			        <easyui:column field="cinemaname" align="center" width="80" title="兑换影院"/>
			        <easyui:column field="create_date" align="center" width="80" title="创建时间"/>
			        <easyui:column field="check_date" align="center" width="80" title="审核时间"/>
			        <easyui:column field="checker" align="center" width="80" title="审核人"/>
			        <easyui:column field="memo" align="center" width="80" title="简要备注"/>
			         <easyui_ext:formatterColumn title="操作" param="value, rowData, index" >
			         	<easyui_ext:handlerColumn text="编辑" onclick="datalist.Edit" param="rowData.code" condition="rowData.status == '0' || rowData.status == '-1' || rowData.status == '-2' || rowData.status == '-3'" />
			         	<easyui_ext:handlerColumn text="信息审核" onclick="datalist.infoCheck" param="rowData.code" condition="rowData.status == '1'" />
			         	<easyui_ext:handlerColumn text="财务审核" onclick="datalist.financialCheck" param="rowData.code" condition="rowData.status == '2' " />
			         	<easyui_ext:handlerColumn text="上架" onclick="datalist.Shelves" param="rowData.code" condition="rowData.status == '-3' " />
			         	<easyui_ext:handlerColumn text="下架" onclick="datalist.Offshelves" param="rowData.code" condition="rowData.status == '3' " />
			         </easyui_ext:formatterColumn>
			        <%-- <easyui:column field="_handler" align="center" width="100" title="操作" formatter="function(value, rowData, index){
			        	
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
	
<div id="singleSale_edit_div" style="overflow:hidden" data-options="width: 800,height: 600,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false"><iframe frameborder="0" width="100%" height="100%"></iframe></div>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
		// 请求根路径
		var basePath = "${pageContext.request.contextPath}";
		var datalist = {
				_temp: {	        	
		        	layout_box: null,
		        	list_box:null
		        },
			Init: function () {
	            var _this = datalist;
	
	            _this._temp.list_box = $("#datalist");
	            _this._temp.layout_box = $("#layout_box");
	            _this._temp.layout_productbox = $("#layout_productbox");
	        },
	        Search: function () {
	            $('#datalist').datagrid('load', $(".search_box").serializeObject());
	        },
	        Edit: function(id){//编辑
	        	// 编辑
				//parent.openTab('单家通兑编辑', basePath+'/sale/intoSaleSingleEdit/'+idObj+'.html');
				$("#singleSale_edit_div iframe").attr("src", basePath+'/sale/salesingleedit/'+id+'.html');
	            $('#singleSale_edit_div').window({
	                title: "单家通兑编辑"
	            });
	        },
	        infoCheck:function(id){//信息审核
	        	$("#singleSale_edit_div iframe").attr("src", basePath+'/sale/salesinglecheck/'+id+'.html');
	            $('#singleSale_edit_div').window({
	                title: "单家通兑信息审核"
	            });
	        },
	        financialCheck:function(id){//财务审核
	        	$("#singleSale_edit_div iframe").attr("src", basePath+'/sale/salesinglemoneycheck/'+id+'.html');
	            $('#singleSale_edit_div').window({
	                title: "单家通兑财务审核"
	            });
	        },
	        Shelves:function(id){//上架
	        	$.messager.confirm('提示','确认上架？',function(r){    
				    if (r){    
				    	submitFn('sale/shelves.do',{code:id});    
				    }    
				});
	        },
	        Offshelves:function(id){//下架
	        	$.messager.confirm('提示','确认下架？',function(r){    
				    if (r){    
				    	submitFn('sale/offshelves.do',{code:id});     
				    }    
				});
	        }
		};
		$(function(){
			datalist.Init();
			//var pageObj = $('#singleSale_query_datalist').datagrid('getPager');
			
			// 查询按钮
			/* $('#singleSale_query_searchBtn').on('click',function(event){
				var params = $("#singleSale_query_search_form").serializeObject();
				$('#singleSale_query_datalist').datagrid({
					queryParams: params
				});
				$('#singleSale_query_datalist').datagrid('reload');
			});
			
			// 监听编辑/上下架/审核 
			$('#singleSale_query_main').on('click','a[cloumnid]',function(event,rowData){
				
				var idType = $(this).attr('id');
				var idObj = $(this).attr('cloumnid');
				
				if(idType == 'singleSale_shelves_btn'){
					// 上架
					
				}else if(idType == 'singleSale_offshelves_btn'){
					// 下架
					
				}else if(idType == 'singleSale_moneycheck_btn'){
					// 财务审核
					
				}else if(idType == 'singleSale_infocheck_btn'){
					// 信息审核
					
				}else if(idType == 'singleSale_edit_btn'){
					
				}
				
			}); */
			
		});
		
		function submitFn(url,params){
			$.post(basePath+'/'+url,params,function(data){
				//console.info(JSON.stringify(data));
				if(data){
					data = JSON.parse(data);
					$.messager.alert('提示',data.message,data["isSuccess"]?'info':'error',function(){
						$('#datalist').datagrid('reload');
					});
				}else{
					$.messager.alert('提示','操作失败!');    
				}
			});
		}
		
		function closeWin(){
			 $('#singleSale_edit_div').window('close');
			 // 刷新页面
			 $('#datalist').datagrid('reload');
		}
		
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>