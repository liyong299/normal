<%@page import="com.mopon.helpers.ui.textbox.Width"%>
<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">添加模板</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<div class="easyui-layout" fit="true">
		<div data-options="region:'center',split:true" style="border:0" >
			<div id="basic_information" class="edit-box">
				<div class="item item_row"><label>模板编号：</label><easyui_ext:textBox id="txt_id" /><span class="star">*</span></div>
				<div class="item item_row"><label>模板名称：</label><easyui_ext:textBox id="txt_name" /><span class="star">*</span></div>
				<div class="item item_row">
					<label>通用价格（元）：</label>
					<easyui_ext:textBox id="txt_common_price" /><span class="star">*</span>
				</div>
				<!-- <div class="item item_row">
					<label>是否通用：</label>
					<input id="common_0" name="common" type="radio" value="0" checked="checked"/><label for="common_0">不通用</label>
					<input id="common_1" name="common" type="radio" value="1" /><label for="common_1">通用</label>
				</div> -->
				<div class="item item_row"><label>备注：</label><textarea id="txt_memo" class="easyui-textbox" name="memo" data-options="multiline: true, width: '50%', height: '55px'"></textarea></div>
			</div>
			<div id="choose_cinema" class="edit-box" style="display:none">
				<easyui_ext:searchCondition id="search_box">
					<input id="templateid" name="templateid" type="hidden" />
					<easyui_ext:searchItem name="影院编号"><easyui_ext:textBox id="cinemano" /></easyui_ext:searchItem>
					<easyui_ext:searchItem name="影院名称"><easyui_ext:textBox id="cinemaname" /></easyui_ext:searchItem>
					<easyui_ext:searchItem name="选座票销售状态"><easyui_ext:comboBox id="seatSaleStatus" data="<%= com.mopon.helpers.ui.combobox.Data.getSeatSaleList() %>" /></easyui_ext:searchItem>
					<easyui_ext:searchItem name="是否关联"><easyui_ext:comboBox id="status" data="<%= com.mopon.helpers.ui.combobox.Data.getRelation() %>" /></easyui_ext:searchItem>
					<easyui_ext:searchItem name="区域" classStyle="item item_row"><input id="provinceNo" name="provinceno" />
		                <input id="cityno" name="cityno" />
		                <input id="areaNo" name="countyno" />
					</easyui_ext:searchItem>
				</easyui_ext:searchCondition>
				<easyui:layoutUnit region="center" border="false">
					<easyui:datagrid id="datalist" pageSize="10" pagination="true" fitColumns="true" singleSelect="true">
						<easyui:facet name="toolbar">
							<easyui:button id="btn_search" text="查询" plain="true" iconCls="icon-search"  onclick="operation.Search()" />
							<easyui:button id="btn_batchSetConnect" text="本页关联" plain="true" iconCls="icon-link"  onclick="operation.BatchSetConnect()" />
							<easyui:button id="btn_allBatchSetConnect" text="全部关联" plain="true" iconCls="icon-link"  onclick="operation.AllBatchSetConnect()" />		
					    </easyui:facet>
					    <easyui:columns>
					        <easyui:column field="cinemano" title="影院编码"/>
					        <easyui:column field="cinemaname" title="影院名称"/>
					        <easyui:column field="priceShow" title="价格范围（元）"/>
					        <%-- <easyui_ext:formatterColumn title="操作" param="value, rowData, index">
					        	<easyui_ext:handlerColumn text="关联" onclick="operation.SetConnect" param="rowData.cinemano" condition="rowData.id == ''" />
					        	<easyui_ext:handlerColumn text="取消关联" onclick="operation.SetBreak" param="rowData.id" condition="rowData.id != ''" />
					        	<easyui_ext:handlerColumn text="设置价格" onclick="operation.ShowSpecialPrice" param="rowData.cinemano" condition="rowData.id != ''" />
					        </easyui_ext:formatterColumn> --%>
					        <easyui:column align="center" field="_handler" title="操作" formatter="operation._handler" />
					    </easyui:columns>
					</easyui:datagrid>
				</easyui:layoutUnit>
			</div>
		</div>
		<div data-options="region:'south'" style="border:0">
			<div class="edit-box">
				<div class="bottom">
		            <a id="btn_save" class="easyui-linkbutton" onclick="operation.Save()">保 存</a>
		            <a id="btn_cancel" class="easyui-linkbutton" onclick="operation.Cancel()">取 消</a>
		            <a id="btn_close" class="easyui-linkbutton" onclick="operation.Cancel()">关 闭</a>
		        </div>
	        </div>
		</div>
	</div>
	<div id="layout_box" style="overflow:hidden" data-options="width: 400,height: 300,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false"><iframe frameborder="0" width="100%" height="100%"></iframe></div>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script src="<%=request.getContextPath()%>/resources/scripts/common/area.js"></script>
	<script>
		var operation = {
				_temp: {
		        	list_box: null,
		        	layout_box: null
		        },
				Init: function (){
					var _this = operation;
					
		            _this._temp.list_box = $("#datalist");	
		            _this._temp.layout_box = $("#layout_box");
		            
		            Area.Init("provinceNo", "cityno", "areaNo");
		            
		            $("#btn_close").hide();
				},
				Search: function () {
					var _this = operation;
					var options = _this._temp.list_box.datagrid('options');
	                if (!options.url) {
	                    options.url = "getCimenas.do";
	                }
	                _this._temp.list_box.datagrid('load', $(".search_box").serializeObject());
		        },
		        Save: function(){
		        	var isok = true;
		        	
		        	var id = $("#txt_id").textbox("getValue");
		        	if(id == ""){
		        		$("#txt_id").textbox("showError", "编号不能为空！");
		        		if(isok){
			        		parent.Tips.Error("编号不能为空！");
			        	}
			        	isok = false;
		        	}
		        	else if(id.length > 50){
		        		$("#txt_id").textbox("showError", "编号不能超过20个字符！");
		        		if(isok){
			        		parent.Tips.Error("编号不能超过20个字符！");
			        	}
			        	isok = false;
		        	}
		        	else if(id.indexOf("_") === 0){
		        		$("#txt_id").textbox("showError", "编号首字符不能为“_”！");
		        		if(isok){
			        		parent.Tips.Error("编号首字符不能为“_”！");
			        	}
			        	isok = false;
		        	}
		        	else if(!/[^\u4e00-\u9fa5]+/.test(id)){
		        		$("#txt_id").textbox("showError", "编号不能包含中文！");
		        		if(isok){
			        		parent.Tips.Error("编号不能包含中文！");
			        	}
			        	isok = false;
		        	}
		        	else {
			            $("#txt_id").textbox("closeError");
			        }
		        	
		        	var name = $("#txt_name").textbox("getValue");
		        	if(name == ""){
		        		$("#txt_name").textbox("showError", "名称不能为空！");
		        		if(isok){
			        		parent.Tips.Error("名称不能为空！");
			        	}
			        	isok = false;
		        	}
		        	else if(name.length > 25){
		        		$("#txt_name").textbox("showError", "名称不能超过25个字符！");
		        		if(isok){
			        		parent.Tips.Error("名称不能超过25个字符！");
			        	}
			        	isok = false;
		        	}
		        	else {
			            $("#txt_name").textbox("closeError");
			        }
		        	
		        	var common_price = $("#txt_common_price").textbox("getValue");
		        	if(common_price == ""){
		        		$("#txt_common_price").textbox("showError", "请输入通用价格！");
		        		
		        		if(isok){
			        		parent.Tips.Error("请输入通用价格！");
			        	}
		        		isok = false;
		        	}
		        	else if(!/^\d+(\.\d{0,2})?$/.test(common_price)){
						$("#txt_common_price").textbox("showError", "请输入最多带两位小数的正确通用价格！");
		        		
		        		if(isok){
			        		parent.Tips.Error("请输入最多带两位小数的正确通用价格！");
			        	}
		        		isok = false;
		        	}
		        	else if(parseFloat(common_price) <= 0){
						$("#txt_common_price").textbox("showError", "请输入大于0的通用价格！");
		        		
		        		if(isok){
			        		parent.Tips.Error("请输入大于0的通用价格！");
			        	}
		        		isok = false;
		        	}
		        	else{
		        		$("#txt_common_price").textbox("closeError");
		        	}
		        	

		        	var memo = $("#txt_memo").textbox("getValue");
		        	if(memo.length > 150){
		        		$("#txt_memo").textbox("showError", "备注不能超过150个字符！");
		        		if(isok){
			        		parent.Tips.Error("备注不能超过150个字符！");
			        	}
			        	isok = false;
		        	}
		        	else {
			            $("#txt_memo").textbox("closeError");
			        }
		        	
		        	if(!isok){
		        		return false;
		        	}

		        	var data = {
		        			templateid: id,
		        			templatename: name,
		        			common_price: common_price * 100,
		        			memo: memo
		        	};
		        	
		        	$.ajaxRequest({
		        		url: "templateAdd.do",
		        		para: data,
		        		success: function(result){
		        			if(result.isSuccess){
		        				parent.operation._temp.list_box.datagrid("reload");
			        			parent.Tips.Success("成功");
			        			
			        			$("#templateid").val(data.templateid);
			        			
			        			$("#basic_information").hide();
			        			$("#choose_cinema").show();
			        			
			        			operation.Search();
			        			$("#btn_save").hide();
			        			$("#btn_cancel").hide();
			        			$("#btn_close").show();
			        			
			        			$('#datalist').datagrid({url:"getCimenas.do"});
			        		}
		        			else{
		        				parent.Tips.Error(result.message);
		        			}
		        		}
		        	});
		        },
		        SetConnect: function(cinemano){
		        	$.ajaxRequest({
     	        		url: "setConnect.do",
     	        		para: { templateid: $("#templateid").val(), cinemano: cinemano },
     	        		success: function(result){
     	        			if(result.isSuccess){
     	        				operation._temp.list_box.datagrid("reload");
     		        			Tips.Success("关联成功");
     		        		}
     	        			else{
     		        			Tips.Success("关联失败");
     		        		}
     	        		}
     	        	});
		        },
		        BatchSetConnect:  function(){
		        	$.messager.confirm("系统提示", "确定本页全部关联？", function (b) {
		        		if (b) {
			        		var rows = operation._temp.list_box.datagrid("getRows");
				        	$.each(rows, function(i, n){
				        		if(n.id == 0) {
					        		$.ajaxRequest({
					        			async: false,
				     	        		url: "setConnect.do",
				     	        		para: { templateid: $("#templateid").val(), cinemano: n.cinemano },
				     	        		success: function(result){
				     	        			/* if(result.isSuccess){
				     	        				
				     		        		} */
				     	        		}
				     	        	});
				        		}
				        	});
				        	
				        	operation.ChangeTemplateID();
			        		operation._temp.list_box.datagrid("reload");
			        		Tips.Success("关联完成");
		        		}
		        	});
		        },
		        AllBatchSetConnect: function(){
		        	$.messager.confirm("系统提示", "确定全部关联？", function (b) {
		        		if (b) {
				        	$.ajaxRequest({
		     	        		url: "setBatchConnect.do",
		     	        		para: $(".search_box").serializeObject(),
		     	        		success: function(result){
		     	        			if(result.isSuccess){
		     	        				operation.ChangeTemplateID();
		     	        				operation._temp.list_box.datagrid("reload");
		     		        			Tips.Success("关联成功" + result.result + "条");
		     		        		}
		     	        			else{
		     		        			Tips.Error(result.message);
		     		        		}
		     	        		}
		     	        	});
		        		}
		        	});
		        },
		        SetBreak: function(id){
		        	$.ajaxRequest({
     	        		url: "setBreak.do",
     	        		para: { id: id },
     	        		success: function(result){
     	        			if(result.isSuccess){
     	        				operation._temp.list_box.datagrid("reload");
     		        			Tips.Success("取消关联成功");
     		        		}
     	        			else{
     		        			Tips.Success("取消关联失败");
     		        		}
     	        		}
     	        	});
		        },
		        Cancel: function(){
		        	parent.operation._temp.layout_box.window("close");
		        },
		        ShowSpecialPrice: function(cinemano){
		        	var _this = operation;
		        	
		        	_this._temp.layout_box.find("iframe").attr("src", "setSpecialPrice.html?templateid=" + $("#templateid").val() + "&cinemano=" + cinemano);
		            $('#layout_box').window({
		                title: "设置特殊价格"
		            });
		        },
		        ChangeTemplateID: function(){
	        		var templateid = $("#templateid").val();
	        		var status = "${template.status}";
	        		if(status == "4" && templateid.indexOf("_temp_") <0){
        				$("#templateid").val("_temp_" + templateid);
        				//更改表格内部的参数
        				operation._temp.list_box.datagrid('options').queryParams.templateid = "_temp_" + templateid;
        			}
	        		
	        		parent.operation._temp.list_box.datagrid("reload");
	        	},
		        _handler: function(value, rowData, index){
	        		var html='';
	        		if(rowData.id == ''){
	        			html += '<a class=\'g-mlr-5\' onclick=\'operation.SetConnect(\"' + rowData.cinemano + '\")\' >关联</a>';
	        		}
	        		if(rowData.id != ''){
	        			html += '<a class=\'g-mlr-5\' onclick=\'operation.SetBreak(\"' + rowData.id + '\")\' >取消关联</a>';
	        		}
	        		if(rowData.id != ''){
	        			html += '<a class=\'g-mlr-5\' onclick=\'operation.ShowSpecialPrice(\"' + rowData.cinemano + '\")\' >设置价格</a>';
	        		}
	        		return html;
	        	}
		};
	
		$(function(){
			operation.Init();
		});
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>