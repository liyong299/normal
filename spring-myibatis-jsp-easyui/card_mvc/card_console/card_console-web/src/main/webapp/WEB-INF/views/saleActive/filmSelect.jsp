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
			<easyui:datagrid id="datalist" url="../film/view.do" fit="true" pageSize="50" pagination="true" fitColumns="true" checkOnSelect="false" selectOnCheck="false">
				<easyui:facet name="toolbar">
					<easyui:button id="btn_search" text="查询" plain="true" iconCls="icon-search"  onclick="datalist.Search()" />		
			        <easyui:button id="btn_choose" plain="true" text="选中" iconCls="icon-ok"  onclick="datalist.ChooseFilm()"/>
			    </easyui:facet>
			    <easyui:columns>
			        <easyui:column field="ck" checkbox="true"/>
			        <easyui:column field="filmno" title="影片编号"/>
			        <easyui:column field="filmname" title="影片名称"/>
			        <easyui:column field="duration" title="时长"/>
			        <easyui:column field="publishdate" title="影片上映日期"/>
			        <easyui:column field="type" title="影片类型"/>
			        <easyui:column field="filmshowtypes" title="放映类型" formatter="datalist.formatter"/>
			    </easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
		<div id="layout_box" style="overflow:hidden" data-options="width: 400,height: 250,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false"><iframe frameborder="0" width="100%" height="100%"></iframe></div>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
		var datalist = {
			Init: function () {
	            var _this = List;
	            _this._temp.list_box = $("#list-grid");
	            _this._temp.layout_box = $("#layout_box");
	            _this._temp.layout_productbox = $("#layout_productbox");
	        },
	        Search: function () {
	            $('#datalist').datagrid('load', $(".search_box").serializeObject());
	        },
	        ChooseFilm: function () {
	        	var checkStr="";
	        	var selCount=0;
	        	var isOk = true;
	        	
	        	debugger;
	        	var oldSelFilmStr = $("#filmSelStr", parent.document).val();
	        	var oldSelCount = $("#selCount", parent.document).html();
	        	if(oldSelFilmStr == undefined || oldSelFilmStr == ""){
	        		oldSelFilmStr="";
	        	}else{
	        		oldSelFilmStr = oldSelFilmStr +"|"
	        	}
	        		
	        	if(oldSelCount == undefined || oldSelCount == "")
	        		oldSelCount=0;		

               	$('#datalist').datagrid('getPanel').find('div.datagrid-body tr').each(function(){ //循环grid中所有行
               		var tdfilmshowtype = $(this).children('td[field="filmshowtypes"]');	
               		var tdfilmno = $(this).children('td[field="filmno"]').children("div").text();
               		var tdfilmName = $(this).children('td[field="filmname"]').children("div").text();
               		var isCheckRow = $(this).children('td[field="ck"]').children("div").children("input").prop("checked");
               		
               		if(isCheckRow) //判断是否是选择的那一行
               		{
               			selCount += 1;
               			if(oldSelFilmStr.indexOf(tdfilmno) > -1)
               			{
             				alert('影片【'+tdfilmName+'】已选择过，需要先删除了才能再选。');
               				isOk = false;
               				return isOk;
               			}
               			
               			var showtype="";
               			tdfilmshowtype.children("div").children("input:checked").each(function () {
               				if($(this).val() !=	"")
               					showtype = showtype + $(this).val() +",";
                     	});
               			if(showtype == ""){
               				alert('请选择影片放映类型。');
               				isOk = false;
               				return isOk;
               			}
               			else	
               				showtype = showtype.substring(0, showtype.length - 1);
               			
               			checkStr = checkStr + tdfilmno +"." + showtype + "|";
               			
               		}
               		
                     return isOk; //不通过的时候跳出循环
               	}); 
               	
               	if(selCount == 0){
					alert('请选择影片。');
					return;
				}
               	
				if(isOk && selCount>0){
           			debugger;
					selCount = selCount + parseInt(oldSelCount); 
	                checkStr = oldSelFilmStr + checkStr.substring(0, checkStr.length - 1);
	 	        	var html = "<input type='hidden' id='filmSelStr' value='"+checkStr+"' />已选择影片<span id='selCount'>"+ selCount +"</span>部&nbsp;&nbsp;&nbsp;&nbsp;" +
	 				"<a href='javascript:void(0);' onclick='showFilmDisplay()' >详情</a>";
	 				// "<a href='javascript:void(0);' onclick='showFilmDisplay(&apos;"+checkStr+"&apos;)' >详情</a>";
		        	$("#film_div", parent.document).html(html);
		        	Cancel();
				}
				
	        },
	        formatter : function(value, rowData, index) {
				// 删除:0,待信息审核:1,信息审核拒绝:-1,待财务审核:2,财务审核拒绝:-2,上架:3,下架:4
				var html = "";
				html +="<input type=\'checkbox\'  name=\'filmshowtype0\' value=\'0\' >2D &nbsp;&nbsp;&nbsp;";
				html +="<input type=\'checkbox\'  name=\'filmshowtype1\' value=\'1\' >3D &nbsp;&nbsp;&nbsp;";
				html +="<input type=\'checkbox\'  name=\'filmshowtype2\' value=\'2\' >IMAX2D &nbsp;&nbsp;&nbsp;";
				html +="<input type=\'checkbox\'  name=\'filmshowtype3\' value=\'3\' >IMAX3D &nbsp;&nbsp;&nbsp;";
				html +="<input type=\'checkbox\'  name=\'filmshowtype4\' value=\'4\' >中国巨幕2D &nbsp;&nbsp;&nbsp;";
				html +="<input type=\'checkbox\'  name=\'filmshowtype5\' value=\'5\' >中国巨幕3D &nbsp;&nbsp;&nbsp;";
				return html;
			}
		};
		
		function Cancel(){
			parent.iframe._temp.layout_box.window("close");
		}
		
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>