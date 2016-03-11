<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<form id="updateForm" class="edit_box" action="../cinema/updateCinemaType.do">
		<input name="id" value="${cinema.cinemano}"  type="hidden" />
		<div class="item">
			<label for="itemName">所属院线 ：<em>*</em></label> 
						
			<select id="cinemalineId"  name="cinemalineId" class="selectStyle"  style="width:150px;">	
					<option value="">--请选择--</option>
					<c:forEach items="${list1}" var="publ">
							<option value="${publ.cinemalineno}">${publ.cinemalinename}</option>
					</c:forEach>
			</select>	
	    	</div> 
		<div class="item">
			<label for="itemName">取票方式 ：<em>*</em></label> 
			<easyui_ext:comboBox id="getTicketType" style="width:150px;" name="getTicketType"  value="${cinema.getTicketType }" width="<%= com.mopon.helpers.ui.combobox.Width.SHORT %>"  data="<%= com.mopon.helpers.ui.combobox.Data.getTicketType() %>" ></easyui_ext:comboBox>
		</div>
		<div class="item">
			<label for="itemName">所属结算公司：<em>*</em></label> 
			<select id="settlOrgzainationId"  name="settlOrgzainationId" class="selectStyle" style="width:150px;" >	
					<option value="">--请选择--</option>
					<c:forEach items="${list}" var="publ1">
							<option value="${publ1.id}">${publ1.orgzainationName}</option>
					</c:forEach>
			</select>	
		</div>
		<div class="item">
			<label for="itemName">客户端供应显示：<em>*</em></label> 
			<%-- <easyui_ext:comboBox id="viewgoodstype" style="width:150px;" name="viewgoodstype"  value="" width="<%= com.mopon.helpers.ui.combobox.Width.SHORT %>"  data="<%= com.mopon.helpers.ui.combobox.Data.getViewGoodsTypeList() %>" ></easyui_ext:comboBox> --%>
			<easyui_ext:comboBox id="viewGoodsType" style="width:150px;" name="viewGoodsType"  value="" width="<%= com.mopon.helpers.ui.combobox.Width.SHORT %>"  data="${viewgoodstypelist}" ></easyui_ext:comboBox>
		</div>
		<div class="item item_row button">
			<a class="easyui-linkbutton" onclick="doEdit()">保存</a>
			<a class="easyui-linkbutton" onclick="Cancel()">取消</a>
		</div>
	</form>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
	$(function(){
		 var cinemalineId = "${cinemaline.cinemalineno}";
		$("#cinemalineId").val(cinemalineId);
		
		var settlOrgzainationId="${orgzaination.id}";
		$("#settlOrgzainationId").val(settlOrgzainationId);
		
		var getTicketType="${cinema.getTicketType}";
		$("#getTicketType").val(getTicketType); 
	
	});
	
	function doEdit() {	    
		$.ajax({
            cache: true,
            type: "POST",
            url:"../cinema/updateCinemaType.do",
            data:$('#updateForm').serialize(),// 你的formid
            dataType: "json",
            async: false,
            error: function(request) {
                alert("Connection error");
            },
            
            success: function(data) {
               if(data.success){
	            	   parent.Tips.Success("保存成功");
	            	   parent.datalist._temp.list_box.datagrid("reload");
            		   Cancel();
	               }else{
	            	   alert(data.errMsg);
	               }            
	            }
	        });
		}
	
	function Cancel(){
		parent.datalist._temp.layout_box.window("close");
	}
	
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>