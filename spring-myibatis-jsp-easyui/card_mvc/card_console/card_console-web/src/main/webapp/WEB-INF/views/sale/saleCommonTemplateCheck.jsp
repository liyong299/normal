<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">添加多家通兑票</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<form id="info" class="edit_box"  method="post" action="" style="padding-top:0" >
	<div id="tt" class="easyui-tabs">
	<div title="基本信息">

        <div class="item item_row"><label> 产品模版编号：</label><easyui_ext:textBox id="templateId" readonly="true" /><span class="star">*</span></div>
        <div class="item item_row"><label>产品模版名称：</label><easyui_ext:textBox id="templateName"  readonly="true" /><span class="star">*</span></div>
        <div class="item item_row"><label>产品类型：</label><easyui_ext:comboBox id="productType" readonly="true" value="0" data="<%= com.mopon.helpers.ui.combobox.Data.getProductType1() %>" /></div>
        <div class="item item_row"><label>通兑类型：</label>
			 <input type="checkbox" id="CommonType0" name="CommonType" value="0"  disabled="disabled" /><span onclick="Ischecked('CommonType0')">2D</span>
             <input type="checkbox" id="CommonType1" name="CommonType" value="1"  disabled="disabled" /><span onclick="Ischecked('CommonType1')">3D</span>
             <input type="checkbox" id="CommonType2" name="CommonType" value="2"  disabled="disabled" /><span onclick="Ischecked('CommonType2')">IMAX2D</span>
             <input type="checkbox" id="CommonType3" name="CommonType" value="3"  disabled="disabled" /><span onclick="Ischecked('CommonType3')">IMAX3D</span>
             <input type="checkbox" id="CommonType4" name="CommonType" value="4"  disabled="disabled" /><span onclick="Ischecked('CommonType4')">中国巨幕2D</span>
             <input type="checkbox" id="CommonType5" name="CommonType" value="5"  disabled="disabled" /><span onclick="Ischecked('CommonType5')">中国巨幕3D </span>
		</div>
		<div class="item"><label>模板类型：</label>
			<input name="templateType" id="templateType1" type="radio" value="0" disabled="disabled" />不通用
            <input name="templateType" id="templateType2" type="radio" value="1" disabled="disabled" />通用
		</div>
		<div class="item item_row"><label>备注：</label>
			<span class="textbox" style="width: 504px; height: 48px;">
    		<textarea disabled="disabled" class="textbox-text validatebox-text" id="memo" autocomplete="off" placeholder="" style="margin-left: 0px; margin-right: 0px; height: 40px; width: 496px;"></textarea>
			</span>
		</div>


    </div>

        <div title="影院模板设置">
        	 <div>
        	 <label>影院编码：</label><easyui_ext:textBox id="cinemano1" />
			  <label>影院名称：</label><easyui_ext:textBox id="cinemaName1" />
        	  <div style="float:left;"> <div id="select-cinema-grid" style="width: 500px;"></div></div>
        	 </div>
        </div>
 </div>
         <div id="checker"  style="display:none;" class="item item_row button">
            <a class="easyui-linkbutton" onclick="Check(2)">信息审核通过</a>
            <a class="easyui-linkbutton" onclick="Check(5)">信息审核拒绝</a>
            <a class="easyui-linkbutton" onclick="Cancel()">关闭</a>
      </div>
        <div id="finance"   style="display:none;" class="item item_row button">
            <a class="easyui-linkbutton" onclick="Check(1)">财务审核通过</a>
            <a class="easyui-linkbutton" onclick="Check(3)">财务审核拒绝</a>
            <a class="easyui-linkbutton" onclick="Cancel()">关闭</a>
        </div>
 </form>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
	/* 已选择的影院面板 */
    $('#select-cinema-grid').datagrid({
    	 url:'queryTemplCinemas.do?templateId=${templateId}',
        pagination: true,
        singleSelect: true,
        pageSize: 10,
        columns: [[
            { field: 'cinemano', title: '影院编码', width: 90 },
            { field: 'cinemaName', title: '影院名称', width: 130 },
            { field: 'goodsShowtype', title: '通兑类型',width: 0,hidden:true },
            { field: 'isSpecialPriceValue', title: '特殊结算价',width: 60 },
            { field: 'goodsShowtypeValue', title: '产品&结算价',width: 200 },
        ]],
        toolbar: [
                  {
                	  text: '查询', iconCls: 'icon-search',handler: searchCinemaList
                  },
      	        {
      	            text: '导出列表', iconCls: 'icon-page_excel',handler: exportCinemaList

      	        }
        ]
    });
		$(function(){
			if("${checker}"==="1")
				{
					$("#checker").show();
					$("#finance").hide();
				}
			else if("${checker}"==="2")
				{
					$("#checker").hide();
					$("#finance").show();
				}
			else
			{
				$("#checker").hide();
				$("#finance").hide();
			}
			$("#templateId").textbox("setValue", "${templateId}");
			$("#templateName").textbox("setValue", "${templateName}");
			$("#productType").combobox("setValue", "${productType}");
			if("${templateType}"==="0")
			{
				$("input[name='templateType'][value=0]").attr("checked",true);
			}
			else
			{
				$("input[name='templateType'][value=1]").attr("checked",true);
			}
			$("#memo").val( "${memo}");
			if("${templShowtypes}"!=="")
			{
				var arr=new Array();
				arr="${templShowtypes}".split(',');
				for(var i=0;i<arr.length;i++)
				{
				$("input:checkbox[value="+arr[i]+"]").attr('checked','true');
				}
			}

		});
		function Check(status){
	        var data={
	        		templateId:templateId.value,
	        		status:status
	        };
	        $.ajaxRequest({
	        	url:"check.do",
	        	para:data,
	        	success:function(result){
	        	if(result.isSucceed){
	    		   	parent.datalist._temp.list_box.datagrid("reload");
	        		parent.Tips.Success("成功");
	        		Cancel();
	        	}
	        	else{
	        		parent.Tips.Error(result.message);
	        		}
	        	}
	        });
		}
		function Cancel(){
			parent.datalist._temp.layout_box.window("close");
		}

		 /* 查询关联的影院列表 */
		function searchCinemaList(){
			var  templateId = $("#templateId").textbox("getValue");
			var  cinemano = $("#cinemano1").textbox("getValue");
			var  cinemaName = $("#cinemaName1").textbox("getValue");
			$('#select-cinema-grid').datagrid('load', {

				cinemano: cinemano,
				cinemaName: cinemaName
            });
		}

	       /* 导出模板关联的影院列表 */
		function exportCinemaList(){
			var  templateId = $("#templateId").textbox("getValue");
			var url = "../commonTemplate/exportCinemaList.do?templateId=" + templateId;
			window.open(url,'模板影院列表','');
		}
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>