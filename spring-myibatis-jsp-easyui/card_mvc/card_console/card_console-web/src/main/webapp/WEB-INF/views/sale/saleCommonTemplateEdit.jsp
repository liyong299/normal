<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">编辑多家通兑票</easyui:templateOverride>
<easyui:templateOverride name="head">
<script src="<%=request.getContextPath()%>/resources/scripts/common/area.js"></script>
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<form id="info" class="edit_box"  method="post" action="" style="padding-top:0" >
	<div id="tt" class="easyui-tabs">
	<div title="基本信息">

        <div class="item item_row"><label> 产品模版编号：</label><easyui_ext:textBox id="templateId" readonly="true" /><span class="star">*</span></div>
        <div class="item item_row"><label>产品模版名称：</label><easyui_ext:textBox id="templateName" /><span class="star">*</span></div>
        <div class="item item_row"><label>产品类型：</label><easyui_ext:comboBox id="productType" value="0" data="<%= com.mopon.helpers.ui.combobox.Data.getProductType1() %>" /></div>
        <div class="item item_row"><label>通兑类型：</label>
			 <input type="checkbox" id="CommonType0" name="CommonType" value="0" /><span onclick="Ischecked('CommonType0')">2D</span>
             <input type="checkbox" id="CommonType1" name="CommonType" value="1" /><span onclick="Ischecked('CommonType1')">3D</span>
             <input type="checkbox" id="CommonType2" name="CommonType" value="2" /><span onclick="Ischecked('CommonType2')">IMAX2D</span>
             <input type="checkbox" id="CommonType3" name="CommonType" value="3" /><span onclick="Ischecked('CommonType3')">IMAX3D</span>
             <input type="checkbox" id="CommonType4" name="CommonType" value="4" /><span onclick="Ischecked('CommonType4')">中国巨幕2D</span>
             <input type="checkbox" id="CommonType5" name="CommonType" value="5" /><span onclick="Ischecked('CommonType5')">中国巨幕3D </span>
		</div>
		<div class="item"><label>模板类型：</label>
			<input name="templateType" id="templateType1" type="radio" value="0" checked />不通用
            <input name="templateType" id="templateType2" type="radio" value="1" />通用
		</div>
		<div class="item item_row"><label>备注：</label>
			<span class="textbox" style="width: 504px; height: 48px;">
    		<textarea class="textbox-text validatebox-text" id="memo" autocomplete="off" placeholder="" style="margin-left: 0px; margin-right: 0px; height: 40px; width: 496px;"></textarea>
			</span>
		</div>


    </div>

        <div title="影院模板设置">
			<div class="item item_row"><label>选择区域：</label>
			        	<%-- <easyui_ext:comboBox id="ProvinceNo" data="<%= com.mopon.helpers.ui.combobox.Data.getProductType() %>" />
			        	<easyui_ext:comboBox id="CityNo" data="<%= com.mopon.helpers.ui.combobox.Data.getProductType() %>" />
			        	<easyui_ext:comboBox id="AreaNo" data="<%= com.mopon.helpers.ui.combobox.Data.getProductType() %>" /> --%>
			        	<easyui_ext:comboBox  id="provinceNo" name="provinceNo"  panelMaxHeight="200" />
			        	<easyui_ext:comboBox  id="cityno" name="cityno"  panelMaxHeight="200" />
			        	<easyui_ext:comboBox id="areaNo" name="areaNo"  panelMaxHeight="200" />
			        	<input type="hidden" id="h_ProvinceNo" value="" />
			            <input type="hidden" id="h_cityNo" value="" />
			            <input type="hidden" id="h_areaNo" value="" />
			             <input type="hidden" id="h_cinemaName" value="" />
			             <input type="hidden" id="h_showType" value="" />
			        	</div>
			        	 <div class="item item_row">
				        	 <label>影院名称：</label><easyui_ext:textBox id="cinemaName" />
				        	 <a class="easyui-linkbutton" id="btn-query-cinema" onclick="getcinemaSreach()">查询</a>
			        	 </div>
			        	 <div style="float:left;width:450px;">
			        	 	<div id="cinema-grid" style="width:400px;"></div>
			        	 </div>

        				<div style="float:left;">
			        	 	 <div id="select-cinema-grid" style="width: 480px;"></div>
			        	  </div>
         </div>
        <div title="查看已关联影院">
        	 <div style="float:left;">
        	 			<label>影院编码：</label><easyui_ext:textBox id="cinemano1" />
						 <label>影院名称：</label><easyui_ext:textBox id="cinemaName1" />
			        	  <div id="select-cinema-grid-view" style="width: 480px;"></div>
			  </div>
        </div>
        </div>



      <div class="item item_row button">
            <a class="easyui-linkbutton" onclick="Edit()">保存</a>
            <a class="easyui-linkbutton" onclick="Cancel()">取消</a>
        </div>
 </form>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
	var Indexrow='undefined';
	/* 已选择的影院面板 */

		$('#select-cinema-grid').datagrid({
	    	/*  url:'querySelectCinems.do?templateId=${templateId}', */
	        pagination: false,
	        singleSelect: true,
	        pageSize: 10,
	        columns: [[

	            { field: 'cinemano', title: '影院编码', width:60 },
	            { field: 'cinemaName', title: '影院名称', width: 110 },
	            { field: 'goodsShowtype', title: '通兑类型',width: 0,hidden:true },
	            { field: 'goodsType', title: '产品类型', width: 0, hidden:true },
	            { field: 'priceType', title: '价格类型', width: 0, hidden:true },
	            { field: 'isSpecialPrice', title: '是否特殊结算价',width: 0,hidden:true },
	            { field: 'goodsShowtypeValue', title: '产品&结算价',width: 170 },
	            { field: 'spPrice', title: '特殊结算价', width: 60,editor: { type: 'textbox', options: { tipPosition: "bottom", validType: 'validate["spPrice"]'} }, align: 'right' },
	            { field: '_operate_', title: '操作', width: 30,formatter: selectOperate}
	        ]],
	        onClickRow: function (index, rowdata) {
	            if(Indexrow==='undefined')
	        	{
	       			 $(this).datagrid('beginEdit', index);
	       			 Indexrow=index;
	        	}
	        else if(Indexrow===index)
	        	{
	        	 $(this).datagrid('endEdit', index);
	        	 Indexrow='undefined';
	        	}
	        else
	        	{
	        		  $(this).datagrid('endEdit', Indexrow);
	        		  $(this).datagrid('beginEdit', index);
	       			 Indexrow=index;
	        	}
	        },
	        onBeginEdit:function(index, row){
	        	var edSalePrice = $('#select-cinema-grid').datagrid('getEditor', { index: index, field: 'spPrice' });
	        	$(edSalePrice.target).textbox('setValue', row.spPrice);
	        },
	        onEndEdit:function(index, row, changes){
	        	var edSalePrice = $('#select-cinema-grid').datagrid('getEditor', { index: index, field: 'spPrice' });
	        	var spPrice = $(edSalePrice.target).textbox('getValue');
	        	$('#select-cinema-grid').datagrid('updateRow', {
	                index: index,
	                row: {
	                	spPrice: spPrice == "" ? "" : parseFloat(spPrice).toFixed(2)
	                }
	            });
	        }
	    });

		$('#select-cinema-grid-view').datagrid({
			url:'queryTemplCinemas.do?templateId=${templateId}',
	        pagination: true,
	        singleSelect: true,
	        pageSize: 10,
	        columns: [[

	            { field: 'cinemano', title: '影院编码', width:60 },
	            { field: 'cinemaName', title: '影院名称', width: 110 },
	            { field: 'goodsShowtype', title: '通兑类型',width: 0,hidden:true },
	            { field: 'goodsType', title: '产品类型', width: 0, hidden:true },
	            { field: 'priceType', title: '价格类型', width: 0, hidden:true },
	            { field: 'isSpecialPrice', title: '是否特殊结算价',width: 0,hidden:true },
	            { field: 'goodsShowtypeValue', title: '产品&结算价',width: 170 },
	            { field: 'spPrice', title: '特殊结算价', width: 60,editor: { type: 'textbox', options: { tipPosition: "bottom", validType: 'validate["spPrice"]'} }, align: 'right' },
	            { field: '_operate_', title: '操作', width: 30,formatter: delCinemas}
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


	 function endEditing()
     {
        if(editIndex == undefined)
        {
        	return true;
        	}//如果为undefined的话，为真，说明可以编辑
        if($('#select-cinema-grid').datagrid('validateRow',editIndex))
        {
           $ ('#select-cinema-grid').datagrid('endEdit',editIndex);
            editIndex = undefined;
            return true;//重置编辑行索引对象，返回真，允许编辑
         }
        else//否则，为假，返回假，不允许编辑
        {
        	return false;
        }
     }
	/* 选择影院面板 */
    $('#cinema-grid').datagrid({
        url: 'getCinemalist.do',
        singleSelect: true,
        pagination: true,
        pageSize: 10,
        remoteSort: false,
        columns: [[
            { field: 'cinemano', title: '影院编码', width: 70, sortable: true },
            { field: 'cinemaName', title: '影院名称', width: 130, sortable: true },
            { field: 'goodsShowtype', title: '通兑类型', width: 0, sortable: true ,hidden:true },
            { field: 'goodsType', title: '产品类型', width: 0, sortable: true ,hidden:true },
            { field: 'priceType', title: '价格类型', width: 0, sortable: true ,hidden:true },
            { field: 'goodsShowtypeValue', title: '产品&结算价', width: 170, sortable: true }

        ]],
        toolbar: [
        {
            text: '本页添加', iconCls: 'icon-add',handler: onPageRow

        }, '-', {
            text: '全部添加', iconCls: 'icon-add',handler:GetcinemaAll
        }
        ],
        onSelect: function (index, row) {
            if (true) {
                var selectData = $("#select-cinema-grid").datagrid('getData');
                var cinemaNo = '';
                var indexData = 0;
                $.each(selectData.rows, function (i, val) {
                    if (val.cinemano == row.cinemano &&  val.goodsShowtype == row.goodsShowtype
           		 && val.goodsType == row.goodsType && val.priceType == row.priceType ) {
                        cinemaNo = val.cinemano;
                        indexData = i;
                        return;
                    }
                });
                if (cinemaNo!=='') {
                    $("#select-cinema-grid").datagrid('deleteRow', indexData);
                    $('#select-cinema-grid').datagrid('appendRow', row);
                }
                else {
                    $("#select-cinema-grid").datagrid('appendRow', row);
                }
            }
        }
    });
		$(function(){
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
		function Edit(){
			if(Indexrow!=='undefined')
			{
				$('#select-cinema-grid').datagrid('endEdit', Indexrow);
			}
			var isok = true;
			var templateId = $("#templateId").textbox("getValue");
	        if (templateId == "") {
	            $("#templateId").textbox("showError", "模板编号不能为空！");
	            if (isok) {
	                parent.Tips.Error("模板编号不能为空！");
	            }
	            isok = false;
	        }
	        else if (templateId.length > 20) {
	            $("#templateId").textbox("showError", "模板编号不能超过20个字符！");
	            if (isok) {
	                parent.Tips.Error("模板编号不能超过20个字符！");
	            }
	            isok = false;
	        }
	        else {
	            $("#templateId").textbox("closeError");
	        }
			var templateName = $("#templateName").textbox("getValue");
	        if (templateName == "") {
	            $("#templateName").textbox("showError", "模板名称不能为空！");
	            if (isok) {
	                parent.Tips.Error("模板名称不能为空！");
	            }
	            isok = false;
	        }
	        else if (templateName.length > 20) {
	            $("#templateName").textbox("showError", "模板名称不能超过20个字符！");
	            if (isok) {
	                parent.Tips.Error("模板名称不能超过20个字符！");
	            }
	            isok = false;
	        }
	        else {
	            $("#templateName").textbox("closeError");
	        }
	        var data={
	        		templateId:templateId,
	        		templateName:templateName,
	        		productType:$("#productType").combobox("getValue"),
	        		templateType:$("input[name='templateType']:checked").val(),
	        		memo:$("#memo").val(),
	        		templShowtype:getCommonType(),
	        		templCinemas:getSelCinemas()
	        };
	        $.ajaxRequest({
	        	url:"edit.do",
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


        function selectOperate(value, row, index) {
                    return "<a  onClick='delCinema(\"" + row.cinemano + "\",\""+row.goodsShowtype+"\",\""+row.goodsType+"\",\""+row.priceType+"\")'>删除</a>";
        }
        function delCinema( cinemaNo,goodsShowtype,goodsType,priceType) {
            var indexData = 0;
            var selectData = $("#select-cinema-grid").datagrid('getData');
            $.each(selectData.rows, function (i, val) {
                if (val != null) {
                    if (val.cinemano == cinemaNo && val.goodsShowtype==goodsShowtype && val.goodsType== goodsType && val.priceType== priceType) {
                        $("#select-cinema-grid").datagrid('deleteRow', i);
                        return;
                    }
                }
            })
        }

        function delCinemas(value, row, index){
        	console.log(row);
        	 return "<a  onClick='delCinemaFromTempl(\"" + row.templateId + "\",\"" + row.cinemano + "\",\""+row.goodsShowtype+"\",\""+row.goodsType+"\",\""+row.priceType+"\",\""+row.isSpecialPrice+"\")'>删除</a>";
        }

        function delCinemaFromTempl(templateId,cinemano,goodsShowtype,goodsType,priceType,isSpecialPrice) {
        	$.messager.confirm("提示","确认删除该记录吗？",function(b){
        		var data={
    	        		templateId:templateId,
    	        		cinemano:cinemano,
    	        		goodsShowtype:goodsShowtype,
    	        		goodsType:goodsType,
    	        		priceType:priceType,
    	        		isSpecialPrice:isSpecialPrice
    	        };
    	        $.ajaxRequest({
    	        	url:"deleteTemplCinems.do",
    	        	para:data,
    	        	success:function(result){
    	        	if(result.isSucceed){
    	        		$('#select-cinema-grid-view').datagrid("reload");
    	        		parent.Tips.Success("删除成功");

    	        	}
    	        	else{
    	        		parent.Tips.Error(result.message);
    	        		}
    	        	}
    	        });
        	});

        }

      /*   查询影院范围 */
        function getcinemaSreach() {
            var provinceNo = $("#provinceNo").combobox('getValue');
            var cityNo = $("#cityno").combobox('getValue');
            var areaNo = $("#areaNo").combobox('getValue');
            var cinemaName = $("#cinemaName").val();
           var showTypes = getCommonType();
           var goodsType=$("#productType").combobox('getValue');

             /* $("#h_ProvinceNo").val(provinceNo);
            $("#h_cityNo").val(cityNo);
            $("#h_areaNo").val(areaNo);
            $("#h_cinemaName").val(cinemaName); */

            $('#cinema-grid').datagrid('load', {
            	provinceno: provinceNo,
            	cityno: cityNo,
            	countyno: areaNo,
                cinemaName: cinemaName,
                goodsShowtypes: showTypes,
                goodsType:goodsType,
                rad: Math.random()
            });
        }
     /*  获取通兑类型（2D，3D....） */
        function getCommonType() {
            var commonType = '';
            $("input[name='CommonType']:checked").each(function (i) {
                commonType += this.value + ",";
            });
            return commonType.substring(0, (commonType.length - 1));
        }
     /*获取已选择 影院编码，放映类型 */
        function   getSelCinemas()
        {
        	var selectData = $("#select-cinema-grid").datagrid('getData');
        	var ret="";
        	$.each(selectData.rows,function(i,val){
        		var sp=val.spPrice;
        		if(sp==null || sp=="")//没编辑完成或着是没有设置情况
        			{
        			sp=-100;//0：非特殊结算价，1：特殊结算价
        			}
        		ret+=val.cinemano+'|'+val.goodsShowtype+'|'+val.goodsType+'|'+val.priceType+'|'+sp+',';
        	})
        	return ret;
        }
     /* 本页添加 */
        function onPageRow() {
                var selectData = $("#select-cinema-grid").datagrid('getData');
                var allData = $("#cinema-grid").datagrid('getData');
                var copyRows = [];
                for (var j = 0; j < selectData.rows.length; j++) {
                    copyRows.push(selectData.rows[j]);
                }
                $.each(allData.rows, function (i, val) {
                    for (var z = 0; z < copyRows.length; z++) {
                        var index = $('#select-cinema-grid').datagrid('getRowIndex', copyRows[z]);
                        if (val.cinemano == copyRows[z].cinemano &&  val.goodsShowtype == copyRows[z].goodsShowtype
                       		 && val.goodsType == copyRows[z].goodsType && val.priceType == copyRows[z].priceType) {
                            $("#select-cinema-grid").datagrid('deleteRow', index);
                        }
                    }

                });
                $.each(allData.rows, function (i, val) {
                    $("#select-cinema-grid").datagrid('appendRow', val);
                })
        }
     /* 全部添加 */
        function GetcinemaAll() {
	        var data={
	        		templateId:$("#TemplateID").val(),
	        		provinceno:$("#provinceNo").combobox('getValue'),
	        		cityno:$("#cityno").combobox('getValue'),
	        		countyno:$("#areaNo").combobox('getValue'),
	        		cinemaName:$("#cinemaName").val(),
	        		goodsShowtypes: getCommonType(),
	        		 goodsType:$("#productType").combobox("getValue"),
	        };
	        $.ajaxRequest({
	        	url:"getAllSelectCinemalist.do",
	        	para:data,
	        	success:function(result){
	        		var selectData = $("#select-cinema-grid").datagrid('getData');
	                $.each(result.rows, function (i, val) {
	                	var bol=false;
	                    for (var z = 0; z < selectData.rows.length; z++) {
	                        if (val.cinemano== selectData.rows[z].cinemano &&  val.goodsShowtype == selectData.rows[z].goodsShowtype
	                        		&& val.goodsType == selectData.rows[z].goodsType && val.priceType == selectData.rows[z].priceType) {
	                        	bol=true;
	                        	break;
	                        }
	                    }
	                    if(!bol)
	                    	{
                        	$("#select-cinema-grid").datagrid('appendRow', {cinemano:val.cinemano,cinemaName:val.cinemaName,goodsShowtype:val.goodsShowtype,
                        		isSpecialPrice:'',goodsShowtypeValue:val.goodsShowtypeValue,goodsType:val.goodsType,priceType:val.priceType});
	                    	}
	                });
	        	}
	        });
        }

        /* 查询关联的影院列表 */
		function searchCinemaList(){
			var  templateId = $("#templateId").textbox("getValue");
			var  cinemano = $("#cinemano1").textbox("getValue");
			var  cinemaName = $("#cinemaName1").textbox("getValue");
			$('#select-cinema-grid-view').datagrid('load', {

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
		$(function(){
			Area.Init("provinceNo", "cityno", "areaNo");
		});
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>