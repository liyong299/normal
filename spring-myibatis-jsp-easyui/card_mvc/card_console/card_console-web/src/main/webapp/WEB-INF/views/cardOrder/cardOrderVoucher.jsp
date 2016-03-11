<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<form id="info" class="edit_box"  method="post" action="" style="padding:10px 10px" >
        <label> 凭证号：</label><easyui_ext:textBox id="voucherNo"  />
         <div style="float:left;padding-top:10px"> <div id="voucher-grid" style="width: 500px;"></div></div>
 </form>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
    $('#voucher-grid').datagrid({
    	 url:'queryVoucherList.do?orderno=${orderno}',
        pagination: true,
        singleSelect: true,
        pageSize: 10,
        columns: [[
            { field: 'orderno', title: '订单号', width: 200 },
            { field: 'voucherno', title: '凭证号', width: 200, formatter: encryption }
        ]],
        toolbar: [
                  {
                	  text: '查询', iconCls: 'icon-search',handler: searchVoucher
                  },
      	        {
      	            text: '导出列表', iconCls: 'icon-page_excel',handler: exportCardVoucherList

      	        }
        ]
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
        function encryption(value) {
            if (value.length > 0) {
                return "*****" + value.substring(5, value.length);
            }
        }
		function searchVoucher(){
			var  voucherNo = $("#voucherNo").textbox("getValue");
			$('#voucher-grid').datagrid('load', {
				voucherno: voucherNo
            });
		}

	       /* 导出模板关联的影院列表 */
		function exportCardVoucherList(){
			var  voucherNo = $("#voucherNo").textbox("getValue");
			var url = "exportCardVoucherList.do?orderno=${orderno}"+"&voucherno="+voucherNo;
			window.open(url,'卡券凭证列表','');
		}
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>