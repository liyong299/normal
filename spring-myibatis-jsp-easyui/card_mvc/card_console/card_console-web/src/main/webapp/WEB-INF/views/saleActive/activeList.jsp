<%@taglib prefix="easyui" uri="org.topteam/easyui"%>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false"%>

<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main" fit="true">
		<easyui_ext:searchCondition id="search_box">
			<easyui_ext:searchItem name="活动编号">
				<easyui_ext:textBox id="actId" name="actId" value="" />
			</easyui_ext:searchItem>
			<easyui_ext:searchItem name="活动名称">
				<easyui_ext:textBox id="actName" name="actName" value="" />
			</easyui_ext:searchItem>
			<easyui_ext:searchItem name="活动类型">
				<easyui_ext:comboBox id="actType" name="actType"
					data="${activeTypeList }" value="" />
			</easyui_ext:searchItem>
			<easyui_ext:searchItem name="活动状态">
				<easyui_ext:comboBox id="actStatus" name="actStatus"
					data="${activeStatusList }" value="" />
			</easyui_ext:searchItem>
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="datalist" striped="true" rownumbers="true"
				url="activeList.do" fit="true" pageSize="30" pagination="true"
				singleSelect="true">
				<easyui:facet name="toolbar">
					<easyui:button id="searchBtn" text="查询" plain="true"
						iconCls="icon-search" onclick="datalist.Search()" />
					<easyui:button id="addBtn" text="添加" plain="true"
						iconCls="icon-add" onclick="datalist.Add()" />
				</easyui:facet>
				<easyui:columns frozen="true">
					<easyui:column field="ck" checkbox="true" />
					<easyui:column field="actId" width="80" title="活动编号" />
					<easyui:column field="actName" width="80" title="活动名称" />
					<easyui:column field="actType" width="80" title="活动类型"
						formatter="datalist.actType" />
					<easyui:column field="actTheme" width="80" title="活动主题" />
					<easyui:column field="actStatus" width="80" title="状态"
						formatter="datalist.actStatus" />
				</easyui:columns>
				<easyui:columns>
					<easyui:column field="actSummary" width="80" title="活动简介" />
					<easyui:column field="actSalesman" width="80" title="销售服务人员" />
					<easyui:column field="actCompany" width="80" title="活动联合举办单位" />
					<easyui:column field="actSubsidy" width="80" title="活动别贴金额" />
					<easyui:column field="actCreator" width="80" title="创建人" />
					<easyui:column field="actCreatedTime" width="80" title="创建时间"
						formatter="datalist.dateFormat" />
					<easyui:column field="actModifier" width="80" title="修改人" />
					<easyui:column field="actModifiedTime" width="80" title="修改时间"
						formatter="datalist.dateFormat" />
					<easyui:column field="actChecker" width="80" title="审核人" />
					<easyui:column field="actCheckTime" width="80" title="审核时间"
						formatter="datalist.dateFormat" />
					<easyui:column field="_handler" title="操作"
						formatter="datalist.formatter" />
				</easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
	<div id="layout_box" style="overflow: hidden"
		data-options="width: 500,height: 600,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false">
		<iframe frameborder="0" width="100%" height="100%"></iframe>
	</div>
	<div id="authority_box" style="overflow: hidden"
		data-options="width: 500,height: 300,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false">
		<iframe frameborder="0" width="100%" height="100%"></iframe>
	</div>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
		var datalist = {
			_temp : {
				layout_box : null,
				list_box : null
			},
			Init : function() {
				var _this = datalist;
				_this._temp.layout_box = $("#layout_box");
				_this._temp.list_box = $('#datalist');
			},
			Search : function() {
				$('#datalist').datagrid('load',
						$("#search_box").serializeObject());
			},
			Add : function() {
				parent.openTab('添加活动', '../saleActive/addActive.do');
			},
			Edit : function(id) {//编辑
				parent.openTab('编辑活动', '../saleActive/editActive.do?actId='
						+ id);
			},
			View : function(id) {//查看
				parent.openTab('查看活动', '../saleActive/viewActive.do?actId='
						+ id);
			},
			UpDown : function(id, type) {//上下架
				$.ajaxRequest({
					url : "activeUpDown.do",
					para : {
						actId : id
					},
					success : function(result) {
						if (result.isSuccess) {
							datalist._temp.list_box.datagrid("reload");
							parent.Tips.Success(result.message);
							//Cancel();
						} else {
							parent.Tips.Error(result.message);
						}
					}
				});
			},
			SetRisk : function(id) {
				datalist._temp.layout_box.find("iframe").attr("src",
						"setRisk.do?actId=" + id);
				datalist._temp.layout_box.window({
					title : "设置风险控制"
				});
			},
			MsgCheck : function(id) {
				$.extend($.messager.defaults, {
					ok : "同意",
					cancel : "拒绝"
				});
				$.messager.confirm('活动信息审核', '活动信息审核', function(r) {
					if (r) {
						$.ajaxRequest({
							url : "msgCheck.do",
							para : {
								actId : id
							},
							success : function(result) {
								if (result.isSuccess) {
									datalist._temp.list_box.datagrid("reload");
									parent.Tips.Success(result.message);
									//Cancel();
								} else {
									parent.Tips.Error(result.message);
								}
							}
						});
					} else {
						$.ajaxRequest({
							url : "rejectMsgCheck.do",
							para : {
								actId : id
							},
							success : function(result) {
								if (result.isSuccess) {
									datalist._temp.list_box.datagrid("reload");
									parent.Tips.Success(result.message);
									//Cancel();
								} else {
									parent.Tips.Error(result.message);
								}
							}
						});
					}
				});
			},
			FinCheck : function(id) {
				$.extend($.messager.defaults, {
					ok : "同意",
					cancel : "拒绝"
				});
				$.messager.confirm('活动财务审核', '活动财务审核', function(r) {
					if (r) {
						$.ajaxRequest({
							url : "finCheck.do",
							para : {
								actId : id
							},
							success : function(result) {
								if (result.isSuccess) {
									datalist._temp.list_box.datagrid("reload");
									parent.Tips.Success(result.message);
									//Cancel();
								} else {
									parent.Tips.Error(result.message);
								}
							}
						});
					} else {
						$.ajaxRequest({
							url : "rejectFinCheck.do",
							para : {
								actId : id
							},
							success : function(result) {
								if (result.isSuccess) {
									datalist._temp.list_box.datagrid("reload");
									parent.Tips.Success(result.message);
									//Cancel();
								} else {
									parent.Tips.Error(result.message);
								}
							}
						});
					}
				});
			},
			formatter : function(value, rowData, index) {
				// 删除:0,待信息审核:1,信息审核拒绝:-1,待财务审核:2,财务审核拒绝:-2,上架:3,下架:4
				var html = "";
				html += "<a class=\'g-mlr-5\' onclick=\'datalist.SetRisk("
						+ rowData.actId + ")\'>设置风控</a>";
				html += "<a class=\'g-mlr-5\' onclick=\'datalist.View("
						+ rowData.actId + ")\'>查看</a>";
				if (rowData.actStatus != '1' && rowData.actStatus != '2' && rowData.actStatus != '3') {
					html += "<a class=\'g-mlr-5\' onclick=\'datalist.Edit("
						+ rowData.actId + ")\'>编辑</a>";
				}
				if (rowData.actStatus == '1') {
					html += "<a class=\'g-mlr-5\' onclick=\'datalist.MsgCheck("
						+ rowData.actId + ")\'>信息审核</a>";
				}
				if (rowData.actStatus == '2') {
					html += "<a class=\'g-mlr-5\' onclick=\'datalist.FinCheck("
							+ rowData.actId + ")\'>财务审核</a>";
				}
				if (rowData.actStatus == '3') {
					html += "<a class=\'g-mlr-5\' onclick=\'datalist.UpDown("
							+ rowData.actId + ")\'>下架</a>";
				} else {
					html += "<a class=\'g-mlr-5\' onclick=\'datalist.UpDown("
							+ rowData.actId + ")\'>上架</a>";
				}
				return html;
			},
			PrintFlag : function(value, rowData, rowIndex) {//设置颜色
				if (rowData.voucherno) {
					if (value == 1) {
						return "<div class='icon-ok' style='width:20px;height:20px;margin:0 auto;'></div>";
					} else {
						return "<div class='icon-cross' style='width:20px;height:20px;margin:0 auto;'></div>";
					}
				}
			},
			actType : function(value) {
				if ('1' == value) {
					return '价格活动';
				} else if ('2' == value) {
					return '折扣活动';
				} else if ('3' == value) {
					return '优惠支付';
				} else if ('4' == value) {
					return '下单立减';
				}else if ('5' == value) {
					return '预售活动';
				}
			},
			actStatus : function(value) {
				if ('1' == value) {
					return '待信息审核';
				} else if ('-1' == value) {
					return '待信息审核拒绝';
				} else if ('2' == value) {
					return '待财务审核';
				} else if ('-2' == value) {
					return '待财务审核拒绝';
				} else if ('3' == value) {
					return '上架';
				} else if ('4' == value) {
					return '下架';
				}
			},
			dateFormat : function(value) {
				if (0 >= value) {
					return "";
				}
				var date = new Date(value);
				return date.format("yyyy-MM-dd hh:mm:ss");
			}
		};
		Date.prototype.format = function(format) {
			var o = {
				"M+" : this.getMonth() + 1, //month
				"d+" : this.getDate(), //day
				"h+" : this.getHours(), //hour
				"m+" : this.getMinutes(), //minute
				"s+" : this.getSeconds(), //second
				"q+" : Math.floor((this.getMonth() + 3) / 3), //quarter
				"S" : this.getMilliseconds()
			//millisecond
			}

			if (/(y+)/.test(format)) {
				format = format.replace(RegExp.$1, (this.getFullYear() + "")
						.substr(4 - RegExp.$1.length));
			}

			for ( var k in o) {
				if (new RegExp("(" + k + ")").test(format)) {
					format = format.replace(RegExp.$1,
							RegExp.$1.length == 1 ? o[k] : ("00" + o[k])
									.substr(("" + o[k]).length));
				}
			}
			return format;
		}
		$(function() {
			datalist.Init();
		});
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp"%>