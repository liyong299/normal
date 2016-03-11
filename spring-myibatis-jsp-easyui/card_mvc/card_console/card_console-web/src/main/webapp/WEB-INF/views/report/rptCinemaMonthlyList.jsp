<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="head">
<script src="<%=request.getContextPath()%>/resources/scripts/common/area.js"></script>
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main" fit="true">
		<easyui_ext:searchCondition id="search_box">
				<easyui_ext:searchItem name="影院编号"><easyui_ext:textBox id="cinemano"  width="135px"  value="" ></easyui_ext:textBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="影院名称"><easyui_ext:textBox id="cinemaName"  width="135px"  value="" ></easyui_ext:textBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="对账金额"><easyui_ext:textBox id="checkAmountStart" width="135px"   ></easyui_ext:textBox>到<easyui_ext:textBox id="checkAmountEnd"  width="135px"  ></easyui_ext:textBox>元</easyui_ext:searchItem>
				<easyui_ext:searchItem name="付款金额"><easyui_ext:textBox id="payAmountStart" width="135px"    ></easyui_ext:textBox>到<easyui_ext:textBox id="payAmountEnd"  width="135px"   ></easyui_ext:textBox>元</easyui_ext:searchItem>
				<easyui_ext:searchItem name="结算月份">
				     <input id="settlmentDateStart" name="settlmentDateStart" type="hidden" />
                    <input id="settlmentDateEnd" name="settlmentDateEnd" type="hidden" />
                    <input class="easyui-combobox" data-options="width: '60'" id="StartYear" name="StartYear"></input>
                    <input class="easyui-combobox" data-options="width: '60'" id="StartMonth" name="StartMonth"></input>				    
				    到
				    <input class="easyui-combobox" data-options="width: '60'" id="EndYear" name="EndYear"></input>
                    <input class="easyui-combobox" data-options="width: '60'" id="EndMonth" name="EndMonth"></input>				    
				</easyui_ext:searchItem>
				<easyui_ext:searchItem name="所在区域">
			        <input id="provinceNo" name="provinceNo" />
                    <input id="cityno" name="cityno" />
                    <input id="areaNo" name="areaNo" />
				</easyui_ext:searchItem>
		</easyui_ext:searchCondition>
		
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="datalist" url="view.do" fit="true" pageSize="30" pagination="true" singleSelect="true"  showFooter="true">
				<easyui:facet name="toolbar">
					<easyui_ext:facetButton id="btn_search" text="查询" plain="true" iconCls="icon-search" onclick="datalist.Search()" />
			        <easyui_ext:facetButton id="btn_export" text="导出列表"  plain="true" iconCls="icon-page_excel"  onclick="datalist.Export()"/>
			        <easyui_ext:facetButton id="btn_printer" text="打印"  plain="true" iconCls="icon-printer"  onclick="datalist.PrintData()"/>
			    </easyui:facet>
			    <easyui:columns>
			        <easyui:column field="ck" checkbox="true"/>
			        <easyui:column field="cinemano" title="影院编码"/>
			        <easyui:column field="cinemaName" title="影院名称"/>
			        <easyui:column field="cityName" title="地区"/>
			        <easyui:column field="settlemntAmount" title="平台结算金额" formatter="MoneyFormatYuan"/>
			         <easyui:column field="checkAmount" title="对账金额" formatter="MoneyFormatYuan"/>
			        <easyui:column field="manualAmount" title="调账" formatter="MoneyFormatYuan"/>
			        <easyui:column field="payAmount" title="付款金额" formatter="MoneyFormatYuan"/>
			         <easyui:column field="settlmentDate" title="结算月份"/>
			        <easyui:column field="remark" title="备注"/>
			        <easyui_ext:formatterColumn title="操作" param="value, rowData, index">
			        <easyui_ext:handlerColumn text="标注" onclick="datalist.Tagging" param="index"  condition="rowData.cinemaName,rowData.cityName"/>
			        <easyui_ext:handlerColumn text="明细" onclick="datalist.Display" param="'rowData.cinemano','rowData.cinemaName','rowData.settlmentDate'" condition="rowData.cinemaName,rowData.cityName"/>
			        </easyui_ext:formatterColumn>
			    </easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
	<div id="layout_box" style="overflow:hidden" data-options="width: 600,height: 500,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false"><iframe frameborder="0" width="100%" height="100%"></iframe></div>
	<div id="tagging" style="padding-top:20px">
    <form class="edit_box">
        <input id="tagging_ID" name="tagging_ID" type="hidden" />
        <div class="item"><label>对账金额：</label><input class="easyui-textbox" id="tagging_CheckAmount" name="tagging_CheckAmount"></input></div>
        <div class="item"><label>调账：</label><input class="easyui-textbox" id="tagging_TransferAmount" name="tagging_TransferAmount"></input></div>
        <div class="item"><label>付款金额：</label><input class="easyui-textbox" id="tagging_PaymentAmount" name="tagging_PaymentAmount"></input></div>
        <div class="item item_row"><label>备注：</label><input class="easyui-textbox" data-options="width: '506', height: '50', multiline: true" id="tagging_Remark" name="tagging_Remark"></input></div>
    </form>
</div>
<div id="layout_print"><iframe frameborder="0" width="0%" height="0%"></iframe></div>
</easyui:templateOverride>

<easyui:templateOverride name="script">
	<script type="text/javascript">	
	
    var datalist = {
        _temp: {
            list_box: null,
            layout_box: null
        },
        Init: function () {
        	var _this = datalist;       	
            _this._temp.list_box = $("#datalist");
            _this._temp.layout_box = $("#layout_box");
        },
        Search: function () {
        	var settlmentDateStart = $("#settlmentDateStart").val();
        	var settlmentDateEnd = $("#settlmentDateEnd").val();
        	var arr1 = settlmentDateStart.split("-");
        	var arr2 = settlmentDateEnd.split("-");
        	if(arr1[1] < 10){
        		$("#settlmentDateStart").val(arr1[0] + "0" + arr1[1].charAt(arr1[1].length-1));
        	}
        	if(arr2[1] < 10){
        		$("#settlmentDateEnd").val(arr2[0] + "0" + arr2[1].charAt(arr2[1].length-1));
        	}
            $('#datalist').datagrid('load', $("#search_box").serializeObject());
        },
        Display: function (id, name, date) {        	
        	var url = encodeURI(encodeURI("../cinemaSettlProductDaily/ViewRptCinemaSettlProductDailyList.do?cinemano=" + id + "&cinemaName=" + name + "&settlmentDate=" + date));
            parent.openTabrefresh("影院" + name + "产品结算总表", url, true);
        },
        Tagging: function (index) {
            var row = $("#datalist").datagrid("getRows")[index];
            $("#tagging_ID").val(row.id);
            $("#tagging_CheckAmount").textbox("setValue", row.checkAmount);
            $("#tagging_TransferAmount").textbox("setValue", row.manualAmount);
            $("#tagging_PaymentAmount").textbox("setValue", row.payAmount);
            $("#tagging_Remark").textbox("setValue", row.remark);

            $('#tagging').dialog("open");
        },
        formatter: function(value, rowData, index){
        	var html = "";
        	if(rowData.cinemaName){
        		var data = "'" + rowData.cinemano + "'" + "," + "'" + rowData.cinemaName + "'" + "," + "'" + rowData.settlmentDate + "'";
        		html = "<a class=\'g-mlr-5\' onclick=\'datalist.Tagging(" + index +")\'>标注</a>" +  "<a class=\'g-mlr-5\' onclick=\"datalist.Display(" + data + ")\">明细</a>";
        	}
        	return html;
        },
        Export: function () {
            $.messager.confirm("系统提示", "确定导出数据？", function (b) {
                if (b) {
                    cinemano=$("#cinemano").val();
                    cinemaName=$("#cinemaName").val();
                    checkAmountStart=$("#checkAmountStart").val();
                    checkAmountEnd=$("#checkAmountEnd").val();
                    payAmountStart=$("#payAmountStart").val();
                    payAmountEnd=$("#payAmountEnd").val();
                    settlmentDateStart=$("#settlmentDateStart").val();
                    settlmentDateEnd=$("#settlmentDateEnd").val();
                    cityno=$("#cityno").combobox("getValue");
                    var url = "../rptCinemaSettlementMonthly/exportRptCinemaMonthly.do?cinemano=" + cinemano + "&cinemaName=" + cinemaName+ "&checkAmountStart=" + checkAmountStart
                    + "&checkAmountEnd=" + checkAmountEnd+ "&payAmountStart=" + payAmountStart+ "&payAmountEnd=" + payAmountEnd+ "&settlmentDateStart=" + settlmentDateStart
                    + "&settlmentDateEnd=" + settlmentDateEnd+ "&cityno=" + cityno;
        			window.open(url,'影院结算金额总表','');
                }
            });
        },
        PrintData: function () {
            $.messager.confirm("系统提示", "确定打印？", function (b) {
                if (b) {
                    var para = $(".search_box").serializeObject();
                    var data = "&cinemano=" + para.cinemano
                                + "&cinemaName=" + para.cinemaName
                                + "&checkAmountStart=" + para.checkAmountStart
                                + "&checkAmountEnd=" + para.checkAmountEnd
                                + "&payAmountStart=" + para.payAmountStart
                                + "&payAmountEnd=" + para.payAmountEnd
                                + "&settlmentDateStart=" + para.settlmentDateStart
                                + "&settlmentDateEnd=" + para.settlmentDateEnd
                                + "&cityno=" + para.cityno;
                    $("#layout_print iframe").attr("src", "../rptCinemaSettlementMonthly/toPrint.do" + "?" + data);
                }
            });
        }
    };

    $(function () {   	
    	Area.Init("provinceNo", "cityno", "areaNo");

        var years = new Array();
        var months = [
            { text: "1", value: 1 },
            { text: "2", value: 2 },
            { text: "3", value: 3 },
            { text: "4", value: 4 },
            { text: "5", value: 5 },
            { text: "6", value: 6 },
            { text: "7", value: 7 },
            { text: "8", value: 8 },
            { text: "9", value: 9 },
            { text: "10", value: 10 },
            { text: "11", value: 11 },
            { text: "12", value: 12 }
        ];
        var year = new Date().getFullYear();
        var month = new Date().getMonth();
        years.push({ text: year, value: year });
        for (var i = 1; i < 10; i++) {
            years.push({ text: year - i, value: year - i });
        }
        $("#StartYear").combobox({
            data: years,
            onSelect: function (record) {
                $("#settlmentDateStart").val(record.value + "-" + $("#StartMonth").combobox("getValue"));
            }
        });
        $("#StartYear").combobox("select", year);

        $("#StartMonth").combobox({
            data: months,
            onSelect: function (record) {
                $("#settlmentDateStart").val($("#StartYear").combobox("getValue") + "-" + record.value);
            }
        });
        
        $("#StartMonth").combobox("select", month);
       $("#settlmentDateStart").val(year + "-" + month);

        $("#EndYear").combobox({
            data: years,
            onSelect: function (record) {
                $("#settlmentDateEnd").val(record.value + "-" + $("#EndMonth").combobox("getValue"));
            }
        });
        $("#EndYear").combobox("select", year);

        $("#EndMonth").combobox({
            data: months,
            onSelect: function (record) {
                $("#settlmentDateEnd").val($("#EndYear").combobox("getValue") + "-" + record.value);
            }
        });
        $("#EndMonth").combobox("select", month);
        $("#settlmentDateEnd").val(year + "-" + month);

        datalist.Search();
        $('#tagging').dialog({
            title: '标注',
            width: 700,
            height: 350,
            closed: true,
            cache: false,
            modal: true,
            buttons: [
                {
                    text: '保存',
                    handler: function () {
                        var isok = true;

                        var id = $("#tagging_ID").val();

                        var CheckAmount = $("#tagging_CheckAmount").textbox("getValue");
                        CheckAmount = CheckAmount == "" ? 0 : CheckAmount;
                        if (!/^\d+\.?\d*$/.test(CheckAmount)) {
                            $("#tagging_CheckAmount").textbox("showError", "对账金额只能输入数字！");
                            isok = false;
                        }
                        else {
                            $("#tagging_CheckAmount").textbox("closeError");
                        }

                        var TransferAmount = $("#tagging_TransferAmount").textbox("getValue");
                        TransferAmount = TransferAmount == "" ? 0 : TransferAmount;
                        if (!/^\d+\.?\d*$/.test(TransferAmount)) {
                            $("#tagging_TransferAmount").textbox("showError", "调账只能输入数字！");
                            isok = false;
                        }
                        else {
                            $("#tagging_TransferAmount").textbox("closeError");
                        }

                        var PaymentAmount = $("#tagging_PaymentAmount").textbox("getValue");
                        PaymentAmount = PaymentAmount == "" ? 0 : PaymentAmount;
                        if (!/^\d+\.?\d*$/.test(PaymentAmount)) {
                            $("#tagging_PaymentAmount").textbox("showError", "付款金额只能输入数字！");
                            isok = false;
                        }
                        else {
                            $("#tagging_PaymentAmount").textbox("closeError");
                        }

                        var Remark = $("#tagging_Remark").textbox("getValue");
                        if (Remark.length > 150) {
                            $("#tagging_Remark").textbox("showError", "备注不能超过150个字符！");
                            isok = false;
                        }
                        else {
                            $("#tagging_Remark").textbox("closeError");
                        }

                        if (!isok) {
                            return;
                        }

                        var data = {
                            id: id,
                            checkAmount: CheckAmount,
                            manualAmount: TransferAmount,
                            payAmount: PaymentAmount,
                            remark: Remark
                        };

                        $.ajax({
                            type: 'post',
                            cache: false,
                            dataType: 'json',
                            url: "../rptCinemaSettlementMonthly/update.do",
                            data: data,
                            async: true,
                            success: function (result) {
                                if (result.success) {
                                    Tips.Success();
                                    datalist._temp.list_box.datagrid("reload");
                                    $("#tagging").dialog("close");
                                }
                                else {
                                    alert(result.errMsg);
                                }
                            },
                            error: function () {
                            },
                            complete: function () {
                            }
                        });
                    }
                },
                {
                    text: '关闭',
                    handler: function () {
                        $("#tagging").dialog("close");
                    }
                }
            ]
        });
    });
</script>

</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>