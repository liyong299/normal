<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<easyui:templateOverride name="title">区域设置</easyui:templateOverride>
<easyui:templateOverride name="head">
<script src="<%=request.getContextPath()%>/resources/scripts/common/area.js"></script>
	<style>
		.module{}
		.module a{margin:5px 0;width:100%;display:inline-block;text-align:center}
		.button_box{box-sizing: border-box;padding:5px;background-color: #e6e6e6; border-top: 1px solid #d4d4d4; text-align: right}
	</style>
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main" fit="true">
		<easyui:layoutUnit region="north" split="true" border="false" style="height:35px">
			<div class="module">
		        <input id="provinceNo" name="provinceNo" />
                <input id="cityno" name="cityno" />
                <input id="areaNo" name="areaNo" />
                <easyui_ext:textBox id="name" name="name" prompt="影院名称" />
                <input id="queryCinema" type="button" value="查询" onclick="queryCinema()" />
	    	</div>
	    </easyui:layoutUnit>
	    <easyui:layoutUnit region="center">
	    	<easyui:layout id="tree" fit="true">
		    	<easyui:layoutUnit region="north" style="height:385px">
		    		<div id="tree_authority"></div>
		    	</easyui:layoutUnit>
		    	<easyui:layoutUnit region="south" style="height:37px;border:none">
		    		<div class="button_box">
			            <a class="easyui-linkbutton" onclick="tree.Save()">选中</a>
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
						if(parent){
							result.push({
								menuId: parent.id,
								operatorId: n.id
							});
						}
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
				var _this = tree;
				var items = _this._temp.tree.tree("getChecked");
				var result_html = '';
				var parent_result_html = $("#areaSelectResult", parent.document).html();
				
				$.each(items, function(i, n){
					var temp_result_html="",tempStr="";
					if (n.children.length > 0) {

					} else {
						if(n.cinemaCode==""){

						}else{
							tempStr= "cinemaCode:"+n.cinemaCode +"hallCode:"+ n.hallCode;
							var pNode = tree._temp.tree.tree("getParent", n.target);
							if(pNode.id > -1 ){
								if (!pNode) {
									temp_result_html += '<div hallCode="'+n.hallCode+'" cinemaCode="'
									+n.cinemaCode+'" provinceCode="'+n.provinceCode+'" cityCode="'
									+n.cityCode+'" countyCode="'+n.countyCode+'" cinemaName="'+
									n.text+'" >'+n.text+
									'&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden" id="hideStr" value="'+ tempStr +'" />' +
					        		'<a href="javascript:void(0);" onclick="$(this, parent.document).parent().remove();">删除</a></div>';
								} else {
									temp_result_html += '<div hallCode="'+n.hallCode+'" cinemaCode="'
									+n.cinemaCode+'" provinceCode="'+n.provinceCode+'" cityCode="'
									+n.cityCode+'" countyCode="'+n.countyCode+'" cinemaName="'+
									pNode.text+'" hallName="'+n.text+'">'+pNode.text+'-'+n.text+
									'&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden" id="hideStr" value="'+ tempStr +'" />' +
					        		'<a href="javascript:void(0);" onclick="$(this, parent.document).parent().remove();">删除</a></div>';
								}
							}
							
						}
					}
					
					if(parent_result_html.indexOf(tempStr) == -1)
						parent_result_html += temp_result_html;
				});
				
				
				$("#areaSelectResult", parent.document).html(parent_result_html);
				//$("#layout_box", parent.document).window("close");
			},
			Cancel: function(){
				$("#layout_box", parent.document).window("close");
			}
		};
		function queryCinema(){
			var provinceNo = $("#provinceNo").textbox("getValue");
			var cityno = $("#cityno").textbox("getValue");
			var areaNo = $("#areaNo").textbox("getValue");
			var name = $("#name").textbox("getValue");
			$.ajaxRequest({
        		url: "queryCinemaByAreaCode.do",
        		para: {
        			provinceCode: provinceNo,
        			cityCode: cityno,
        			countyCode: areaNo,
        			name : name
				},
        		success: function(result){
        			if(result){
        				$('#tree_authority').tree({
   	        				checkbox: true,
   	        				data: result
   	        			});
  	        		}
        			else{
        				parent.Tips.Success(result.message);
        			}
        		}
        	});
		}
		$(function(){
			Area.Init("provinceNo", "cityno", "areaNo");
			tree.Init();
		});
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>