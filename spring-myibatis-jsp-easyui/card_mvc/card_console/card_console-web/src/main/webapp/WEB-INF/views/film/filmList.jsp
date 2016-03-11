<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main" fit="true">
		<easyui_ext:searchCondition id="search-form">
				<easyui_ext:searchItem name="影片编号"><easyui_ext:textBox id="filmno" value="" ></easyui_ext:textBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="影片名称"><easyui_ext:textBox id="filmname" value="" ></easyui_ext:textBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="影片上映日期"><easyui:inputDate id="publishdate_start" name="publishdate_start" value=""  />到:<easyui:inputDate id="publishdate_end" name="publishdate_end" value=""  /></easyui_ext:searchItem>
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="datalist" url="view.do" fit="true" pageSize="30" pagination="true" singleSelect="false" fitColumns="true">
				<easyui:facet name="toolbar">
					<easyui_ext:facetButton id="btn_search" text="查询" plain="true" iconCls="icon-search"  onclick="datalist.Search()" />
<%-- 					<easyui_ext:facetButton id="btn_sort" text="影片排序" plain="true" iconCls="icon-table_sort"  onclick="datalist.Sort()" />		 --%>
			    </easyui:facet>
			    <easyui:columns>
			        <easyui:column field="ck" checkbox="true"/>
			        <easyui:column field="filmno" title="影片编号"/>
			        <easyui:column field="filmname" title="影片名称"/>
			        <easyui:column field="duration" title="时长"/>
			        <easyui:column field="publishdate" title="影片上映日期"/>
			        <easyui:column field="publisher" title="发行商"/>
			        <easyui:column field="director" title="导演"/>
			        <easyui:column field="cast" title="演员"/>
			        <easyui:column field="sortno" title="推荐排序"/>
			        <easyui:column field="score" title="影片评分"/>
			        <easyui:column field="type" title="影片类型"/>   
			        <easyui_ext:formatterColumn title="操作" param="value, rowData, index">
			        	<easyui_ext:handlerColumn text="编辑" onclick="datalist.editfile" param="rowData.id" />
			        </easyui_ext:formatterColumn>
			    </easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
		<div id="layout_box_edit" style="overflow:hidden" data-options="width: 480,height: 300,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false"><iframe frameborder="0" width="100%" height="100%"></iframe></div>
		<div id="layout_box_sort" style="overflow:hidden" data-options="width: 800,height: 500,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false"><iframe frameborder="0" width="100%" height="100%"></iframe></div>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
	var datalist = {
			Init: function () {
	            var _this = datalist;
	
	            _this._temp.list_box = $("#datalist");
	            _this._temp.layout_box_sort = $("#layout_box_sort");
	            _this._temp.layout_box_edit = $("#layout_box_edit");
	        },
	        _temp: {
	        	list_box: null,
	        	layout_box_edit: null,
	        	layout_box_sort: null
	        },
	        
	               
	        Search: function () {
	            $('#datalist').datagrid('load', $(".search_box").serializeObject());
	        },
	        Add: function(){
	        },
	        Deleteasyui: function(){  	
	        },
	        
	        
// 	        formatter: function(value, rowData, index){
// 	        	var html = ""; 
	        	
// 	        	html += "<a class=\'g-mlr-5\' onclick=\'editfile(" + rowData.id + ")\'>编辑</a>";
	        	
// 	        	return html;
// 	        }
// 		};
	     Sort:function (){
	    	var ids=[];
	    	var rows= $('#datalist').datagrid('getSelections');
	    	if(rows != null && rows.length > 0){
		    	for(var i=0;i<rows.length;i++){
		    		ids.push(rows[i].id);
		    	}    	 
	    	}
	    	else{
	    		alert("请选择最新需要排序的影片！");
	    		return;
	    	}
	    	
    		$("#layout_box_sort iframe").attr("src", '../film/filmSort.html?id='+ids);
            $('#layout_box_sort').window({
                title: "影片排序"
            })
         },

	    editfile:function (id){
			$("#layout_box_edit iframe").attr("src", '../film/filmEdit.html?id=' + id);
	        $('#layout_box_edit').window({
	            title: "编辑影片信息"
	        });
		}
	}
	
	$(function(){
		datalist.Init();
	});
		
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>