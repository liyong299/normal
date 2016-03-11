<%@page import="com.mopon.helpers.ui.textbox.Width"%>
<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<easyui:templateOverride name="title">查看模板</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<div class="easyui-layout" fit="true">
		<div data-options="region:'center',split:true" style="border:0" >
			<easyui:tabs id="tabs">
				<easyui:tab title="基本信息">
					<div class="edit-box">
						<div class="item item_row"><label>模板名称：</label><span>${template.templatename}</span></div>
						<div class="item item_row">
							<label>通用价格（元）：</label><label>${template.common_price / 100}</label>
						</div>
						<div class="item item_row"><label>备注：</label><textarea id="txt_memo" class="easyui-textbox" name="memo" data-options="multiline: true, width: '50%', height: '55px'">${template.memo}</textarea></div>
					</div>
				</easyui:tab>
				<easyui:tab title="关联影院">
					<easyui_ext:searchCondition id="search_box">
						<input id="templateid" name="templateid" type="hidden" value="${template.templateid}" />
						<input id="status" name="status" type="hidden" value="1" />
						<easyui_ext:searchItem name="影院编号"><easyui_ext:textBox id="cinemano" /></easyui_ext:searchItem>
						<easyui_ext:searchItem name="影院名称"><easyui_ext:textBox id="cinemaname" /></easyui_ext:searchItem>
						<easyui_ext:searchItem name="区域" classStyle="item item_row"><input id="provinceNo" name="provinceno" />
		                <input id="cityno" name="cityno" />
		                <input id="areaNo" name="countyno" />
					</easyui_ext:searchItem>
					</easyui_ext:searchCondition>
					<easyui:layoutUnit region="center" border="false">
						<easyui:datagrid id="datalist" pageSize="10" pagination="true" fitColumns="true" singleSelect="true">
							<easyui:facet name="toolbar">
								<easyui:button id="btn_search" text="查询" plain="true" iconCls="icon-search"  onclick="operation.Search()" />		
						    </easyui:facet>
						    <easyui:columns>
						        <easyui:column field="cinemano" title="影院编码"/>
						        <easyui:column field="cinemaname" title="影院名称"/>
						        <easyui:column field="priceShow" title="价格范围（元）"/>
						    </easyui:columns>
						</easyui:datagrid>
					</easyui:layoutUnit>
				</easyui:tab>
			</easyui:tabs>
		</div>
		<div data-options="region:'south'" style="border:0">
			<div class="edit-box">
				<div class="bottom">
					<c:if test="${template.status == 2}">
					<a class="easyui-linkbutton" onclick="operation.InformationCheckOK()">信息审核通过</a>
					<a class="easyui-linkbutton" onclick="operation.InformationCheckNO()">信息审核拒绝</a>
					</c:if>
		            <a class="easyui-linkbutton" onclick="operation.Cancel()">关闭</a>
		        </div>
	        </div>
		</div>
	</div>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script src="<%=request.getContextPath()%>/resources/scripts/common/area.js"></script>
	<script>
		var operation = {
				_temp: {
					tag: 0,
		        	list_box: null,
		        	layout_box: null
		        },
				Init: function (){
					var _this = operation;
					
		            _this._temp.list_box = $("#datalist");	
		            _this._temp.layout_box = $("#layout_box");
		            
		            Area.Init("provinceNo", "cityno", "areaNo");
		            
		            $("#tabs").tabs({
		            	onSelect: function(title, index){
		            		if(index = 1 && operation._temp.tag == 0) {
		            			operation._temp.tag = 1;
		            			operation.Search();
		            		}
		                }
		            });
				},
				Search: function () {
					var _this = operation;
					var options = _this._temp.list_box.datagrid('options');
	                if (!options.url) {
	                    options.url = "getCimenas.do";
	                }
	                _this._temp.list_box.datagrid('load', $(".search_box").serializeObject());
		        },
		        Cancel: function(){
		        	parent.operation._temp.layout_box.window("close");
		        },
		        InformationCheckOK: function(){
		        	$.messager.confirm("系统提示", "确定审核通过？", function (b) {
		                 if (b) {
		                	 $.ajaxRequest({
		 		        		url: "informationCheckOK.do",
		 		        		para: {templateid: $("#templateid").val()},
		 		        		success: function(result){
		 		        			if(result.isSuccess){
		 		        				parent.operation._temp.list_box.datagrid("reload");
		 			        			parent.Tips.Success("成功");
		 			        			operation.Cancel();
		 			        		}
		 		        			else{
		 		        				parent.Tips.Error(result.message);
		 		        			}
		 		        		}
		 		        	});
		                 }
		        	 });
		        },
		        InformationCheckNO: function(){
		        	$.messager.confirm("系统提示", "确定审核拒绝？", function (b) {
		                 if (b) {
		                	 $.ajaxRequest({
		 		        		url: "informationCheckNO.do",
		 		        		para: {templateid: $("#templateid").val()},
		 		        		success: function(result){
		 		        			if(result.isSuccess){
		 		        				parent.operation._temp.list_box.datagrid("reload");
		 			        			parent.Tips.Success("成功");
		 			        			operation.Cancel();
		 			        		}
		 		        			else{
		 		        				parent.Tips.Error(result.message);
		 		        			}
		 		        		}
		 		        	});
		                 }
		        	 });
		        }
		};
	
		$(function(){
			operation.Init();
		});
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>