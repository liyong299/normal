<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main" fit="true">
		<easyui_ext:searchCondition id="search-form">
				<easyui_ext:searchItem name="微信影院编号"><easyui_ext:textBox id="wxcinemano" value=""></easyui_ext:textBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="兜有影院编号"><easyui_ext:textBox id="cinemano" value="" ></easyui_ext:textBox></easyui_ext:searchItem><br />
				<easyui_ext:searchItem name="影院名称"><easyui_ext:textBox id="cinemaname" value="" ></easyui_ext:textBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="院线名称"><easyui_ext:textBox id="cinemalinename" value="" ></easyui_ext:textBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="联系电话"><easyui_ext:textBox id="telephone" value="" ></easyui_ext:textBox></easyui_ext:searchItem><br />
				<easyui_ext:searchItem name="所在区域">
				<easyui_ext:comboBox id="provinceno"  value=""   style="width:120px;" url="../area/getProvince.do" valueField="areaNo" textField="areaName"></easyui_ext:comboBox>
				<easyui_ext:comboBox id="cityno"  value=""  style="width:80px;"></easyui_ext:comboBox>
				<easyui_ext:comboBox id="areano"  value=""   style="width:80px;"></easyui_ext:comboBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="影院地址"><easyui_ext:textBox id="address" value="" ></easyui_ext:textBox></easyui_ext:searchItem>
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="datalist" url="wxcmplist.do" fit="true" pageSize="30" pagination="true" singleSelect="true" >
				<easyui:facet name="toolbar">
					<easyui:button id="btn_search" text="查询" plain="true" iconCls="icon-search"  onclick="datalist.Search()" />		
			        <easyui:button id="btn_add" text="添加" plain="true" iconCls="icon-add" onclick="datalist.Add()"></easyui:button>
			        <easyui:button id="btn_edit" plain="true" text="编辑" iconCls="icon-edit"  onclick="editWXcmmap()"></easyui:button>
			        <easyui:button id="btn_enable" text="删除" plain="true" iconCls="icon-delete" onclick="removeWXcmmap()"></easyui:button>
			    </easyui:facet>
			    <easyui:columns>
			        <easyui:column field="ck" checkbox="true"/>
			        <easyui:column field="id" title="ID"/>
			        <easyui:column field="wxcinemano" title="微信影院编码"  width="100px"/>
			        <easyui:column field="cinemano" title="兜有影院编码"  width="100px"/>
			        <easyui:column field="cinemaname" title="影院名称"  width="200px"/>
			        <easyui:column field="cinemalinename" title="所属院线" width="100px"/>
			         <easyui:column field="telephone" title="电话" width="100px"/>
			        <easyui:column field="provinceno" title="所属省" width="150px"/>
			        <easyui:column field="cityno" title="所属市" width="100px"/>
			        <easyui:column field="areano" title="所属区" width="100px"/>
			        <easyui:column field="address" title="影院地址" width="250px"/>
			    </easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
	<div id="layout_box" style="overflow:hidden" data-options="width: 650,height: 500,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false">
		<iframe frameborder="0" width="100%" height="100%"></iframe>
	</div>
	
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
	
	var datalist = {
			Init: function () {
				 	//var _this = List;		            
		            //_this._temp.layout_box = $("#layout_box");
	        },
	        Search: function () {
	        	//$.messager.alert("Pop info","you click Search!")
	            $('#datalist').datagrid('load', $(".search_box").serializeObject());
	        },
	        Add: function(){
	        	$("#layout_box iframe").attr("src", "../cgmapping/wxcinemaadd.html");
	            $('#layout_box').window({
	                title: "添加微信影院映射"
	            });
	        },
	        Deleteasyui: function(){
	        }
		};	
	
		$(function(){
			datalist.Init();
			$('#provinceno').combobox({
				onSelect: function (record) {					
					$('#cityno').combobox({
						url:"../area/getCityByProvinceId.do?areaNo="+record.areaNo,
						valueField:'areaNo',
						textField:'areaName',
						onSelect:function(rec){							
							$('#areano').combobox({
								url:"../area/getArea.do?id="+rec.areaNo,
								valueField:'areaNo',
								textField:'areaName'								
							});
						}
					});
				}
			});
		});
	
		function editWXcmmap()
		{
			var obj = $('#datalist').datagrid('getSelections')[0];
			if(obj == null){
				$.messager.alert('信息提示','请选择要编辑的微信影院映射信息！','success',null);
				return;
			}
			var id = obj.id;
			
			$("#layout_box iframe").attr("src", "../cgmapping/wxcinemaedit.html?id=" + id);
            $('#layout_box').window({
                title: "编辑微信影院映射"
            });			
		}
		
		function removeWXcmmap()
		{
			var obj = $('#datalist').datagrid('getSelections')[0];
			if(obj == null){
				$.messager.alert('信息提示','请选择要删除的微信影院映射信息！','success',null);
				return;
			}
			var id = obj.id;
			$.messager.confirm("提示","确认删除该微信影院映射吗？",function(data){
				if(data){
					
					$.ajax({
						type: "POST",
						data:"id="+id,
						url:"../cgmapping/deletewxcinema.do" ,			            
			            dataType: "json",
			            async: false,
			            error: function(request) {
			                $.messager.alert("Connection error");
			            },
			            success: function(data) {
			            	if(data.success){
				            	   $.messager.alert('信息提示','操作成功','success',function(){
				            		   parent.refreshTab("微信影院映射");
				            	   });				            	  
				               }else{
				            	   $.messager.alert(data.errMsg);
				               }		            	
			            }
					});					
				}
			});
		}
	</script>
</easyui:templateOverride>
<%@include file="/WEB-INF/views/share/include.jsp" %>