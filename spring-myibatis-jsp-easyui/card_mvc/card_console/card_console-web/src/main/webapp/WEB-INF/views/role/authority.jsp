<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<easyui:templateOverride name="title">添加用户</easyui:templateOverride>
<easyui:templateOverride name="head">
	<style>
		.module{}
		.module a{margin:5px 0;width:100%;display:inline-block;text-align:center}
		.button_box{box-sizing: border-box;padding:5px;background-color: #e6e6e6; border-top: 1px solid #d4d4d4; text-align: right}
	</style>
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main" fit="true">
		<easyui:layoutUnit region="west" split="true" title="模块" border="false" style="width: 110px;">
			<div class="module">
				<c:forEach items="${moduleList}" var="i">
					<a moduleid="${i.id }">${i.name }</a>
				 </c:forEach>
	    	</div>
	    </easyui:layoutUnit>
	    <easyui:layoutUnit region="center">
	    	<easyui:layout id="tree" fit="true">
		    	<easyui:layoutUnit region="north" style="height:425px">
		    		<div id="tree_authority"></div>
		    	</easyui:layoutUnit>
		    	<easyui:layoutUnit region="south" style="height:37px;border:none">
		    		<div class="button_box">
			            <a class="easyui-linkbutton" onclick="tree.Save()">保存</a>
			            <a class="easyui-linkbutton" onclick="tree.Cancel()">关闭</a>
			        </div>
		    	</easyui:layoutUnit>
	    	</easyui:layout>
	    </easyui:layoutUnit>
    </easyui:layout>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
		var tree = {
			_temp: {
				tree: null,
				roleID: "${roleID}",
				moduleID: 0
			},
			Init: function(){
				var _this = tree;
				_this._temp.tree = $("#tree_authority");
				$(".module a").click(_this.GetMenu).eq(0).click();
				
				_this._temp.tree.tree({
					onclck: function(node){
						_this.CheckAllChildNode(node.target);
					}
				});
			},
			GetMenu: function(){
				var  moduleid = $(this).attr("moduleid");
				tree._temp.moduleID = moduleid;
				$.ajaxRequest({
   	        		url: "getauthority.do",
   	        		para: { roleid: tree._temp.roleID, moduleid: moduleid },
   	        		success: function(result){
   	        			$('#tree_authority').tree({
   	        				checkbox: true,
   	        				data: result
   	        			});
   	        		}
   	        	});
			},
			CheckAllChildNode: function(target){
				var _this = tree;
				var root = _this._temp.tree.tree('getNode', target);
				var childNodes = _this._temp.tree.tree('getChildren', target);
				var check = "check";
				if(!root.checked){
					check = "uncheck";
				}
				_this._temp.tree.tree(check, target);
			 	if (childNode.length > 0) {
                    for (var i = 0; i < childNode.length; i++) {
                   		_this._temp.tree.tree(check, childNodes[i].target);
                    }
                }
			},
			GetSelectedOperator: function(){
				var _this = tree;
				
				var result = new Array();
				var items = _this._temp.tree.tree("getChecked");
				$.each(items, function(i, n){
					if(n.children.length == 0){
						var parent = _this._temp.tree.tree("getParent", n.target);
						result.push({
							menuId: parent.id,
							operatorId: n.id
						});
					}
				});
				return result;
			},
			GetSelectedMenu: function(){
				var _this = tree;
				
				var result = new Array();
				var items = _this._temp.tree.tree("getChecked");
				$.each(items, function(i, n){
					
					if(n.children.length == 0){
						var parent = _this._temp.tree.tree("getParent", n.target);
						
						var b = true;
						$.each(result, function(j, m){
							if(m.menuId == parent.id){
								b = false;
							}
						});
						if(b){
							result.push({
								menuId: parent.id
							});
							
							parent = _this._temp.tree.tree("getParent", parent.target);
							$.each(result, function(j, m){
								if(m.menuId == parent.id){
									b = false;
								}
							});
							if(b){
								result.push({
									menuId: parent.id
								});
							}
						}
					}
				});
				if(result.length > 0){
					result.push({
						menuId: tree._temp.moduleID
					});
				}
				return result;
			},
			Save: function(){
				var operatorList = tree.GetSelectedOperator();
				var operatorList_data = new Array();
				$.each(operatorList, function(i, n){
					operatorList_data.push(n.menuId + "-" + n.operatorId);
				});
				
				var menuList = tree.GetSelectedMenu();
				var menuList_data = new Array();
				$.each(menuList, function(i, n){
					menuList_data.push(n.menuId);
				});
				
				$.ajaxRequest({
   	        		url: "saveauthority.do",
   	        		para: {
   	        			roleid: tree._temp.roleID,
   	        			moduleid: tree._temp.moduleID,
   	        			operatorList: operatorList_data.length > 0 ? operatorList_data : ["0"],
   	        			menuList: menuList_data.length > 0 ? menuList_data : ["0"]
					},
   	        		success: function(result){
   	        			if(result.isSuccess){
	   	        			parent.Tips.Success("成功");
	   	        		}
   	        			else{
   	        				parent.Tips.Success(result.message);
   	        			}
   	        		}
   	        	});
			},
			Cancel: function(){
				parent.datalist._temp.authority_box.window("close");
			}
		};
		
		$(function(){
			tree.Init();
		});
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>