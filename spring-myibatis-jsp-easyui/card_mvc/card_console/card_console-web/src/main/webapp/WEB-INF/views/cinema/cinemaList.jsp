<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main" fit="true">
		<easyui_ext:searchCondition id="search-form">

				<easyui_ext:searchItem name="影院编号"><easyui_ext:textBox id="cinemano" value="" ></easyui_ext:textBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="影院名称"><easyui_ext:textBox id="cinemaname" value="" ></easyui_ext:textBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="影院状态"><easyui_ext:comboBox id="status" value="" data="<%= com.mopon.helpers.ui.combobox.Data.getStatusList() %>" ></easyui_ext:comboBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="选座票状态"><easyui_ext:comboBox id="seatSale" value="" data="<%= com.mopon.helpers.ui.combobox.Data.getSeatSaleList() %>" ></easyui_ext:comboBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="通兑票状态"><easyui_ext:comboBox id="commonSale" value="" data="<%= com.mopon.helpers.ui.combobox.Data.getcommonSaleList() %>" ></easyui_ext:comboBox></easyui_ext:searchItem>
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="datalist" url="view.do" fit="true" pageSize="30" pagination="true" singleSelect="true" fitColumns="true">
				<easyui:facet name="toolbar">
					<easyui_ext:facetButton id="btn_search" text="查询" plain="true" iconCls="icon-search"  onclick="datalist.Search()" />		
			    </easyui:facet>
			    <easyui:columns>
			        <easyui:column field="ck" checkbox="true"/>
			        <easyui:column field="cinemano" title="影院编码"/>
			        <easyui:column field="cinemaname" title="影院名称"/>
			        <easyui:column field="hallcount" title="影厅数量"/>
			        <easyui:column field="province" title="所属省"/>
			        <easyui:column field="city" title="所属市"/>
			        <easyui:column field="county" title="所属区"/>
			        <easyui:column field="address" title="影院地址"/>
			        <easyui:column field="viewGoodsType" title="客户端供应显示"  formatter="function(value, rowData, index){if(value == '0'){return '选座票，通兑票';}else if(value == '1'){return '选座票'}else if(value == '2'){return '通兑票';}else{return '';}}"/>
			        <easyui:column field="commonFlag" title="是否供应通兑票"  formatter="function(value, rowData, index){if(value == '1'){return '供应';}else{return '没供应';}}"/>
			        <easyui:column field="status" title="影院状态"  formatter="function(value, rowData, index){if(value == '1'){return '启用';}else{return '禁用';}}"/>
			        <easyui:column field="seatSale" title="选座票状态"  formatter="function(value, rowData, index){if(value == '1'){return '选座票启售';}else{return '选座票停售';}}"/>
			        <easyui:column field="commonSale" title="通兑票状态"  formatter="function(value, rowData, index){if(value == '1'){return '通兑票启售';}else{return '通兑票停售';}}"/>
			        <easyui_ext:formatterColumn title="操作" param="value, rowData, index">
			        	<easyui_ext:handlerColumn text="查看" onclick="datalist.showDetail" param="'rowData.cinemano'" />
			        	<easyui_ext:handlerColumn text="供应设置" onclick="datalist.editPayChannel" param="'rowData.cinemano'" />
			        	<easyui_ext:handlerColumn text="编辑" onclick="datalist.editcinematype" param="'rowData.cinemano'"/>
			        	<easyui_ext:handlerColumn text="启用" onclick="datalist.updateStatus" param="'rowData.cinemano','1'" condition="rowData.status == '0' " />
			        	<easyui_ext:handlerColumn text="禁用" onclick="datalist.updateStatus" param="'rowData.cinemano','0'" condition="rowData.status == '1'" />
			        	<easyui_ext:handlerColumn text="选座票启售" onclick="datalist.updateSeatSale" param="'rowData.cinemano','1'" condition="rowData.seatSale == '0'" />
			        	<easyui_ext:handlerColumn text="选座票停售" onclick="datalist.updateSeatSale" param="'rowData.cinemano','0'" condition="rowData.seatSale == '1'" />
			            <easyui_ext:handlerColumn text="通兑票启售" onclick="datalist.updateCommonSale" param="'rowData.cinemano','1'" condition="rowData.commonSale == '0'" />
			        	<easyui_ext:handlerColumn text="通兑票停售" onclick="datalist.updateCommonSale" param="'rowData.cinemano','0'" condition="rowData.commonSale == '1'" />
			        </easyui_ext:formatterColumn>
			    </easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
	<div id="layout_box" style="overflow:hidden" data-options="width: 800,height: 500,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false"><iframe frameborder="0" width="100%" height="100%"></iframe></div>
	

</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
	var datalist = {
			Init: function () {
	            var _this = datalist;
	
	            _this._temp.list_box = $("#datalist");	
	            _this._temp.layout_box = $("#layout_box");
	        },
	        _temp: {
	        	list_box: null,
	        	layout_box: null
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
// 	        	html += "<a class=\'g-mlr-5\' onclick=\'showDetail(" + rowData.cinemano + ")\'>查看</a>";
	        	
// 	        	html += "<a class=\'g-mlr-5\' onclick=\'editPayChannel(" + rowData.cinemano + ")\'>供应设置</a>";
	        	
// 	        	html += "<a class=\'g-mlr-5\' onclick=\'editcinematype(" + rowData.cinemano + ")\'>编辑</a>";
	        	
// 	        	if(rowData.status == 0){
// 	        		html += "<a class=\'g-mlr-5\' onclick=\'updateStatus(" + rowData.cinemano +',' + '1' + ")\'>启用</a>";
// 	        	}
// 	        	else if(rowData.status ==1){
// 	        		html += "<a class=\'g-mlr-5\' onclick=\'updateStatus(" + rowData.cinemano +',' + '0' + ")\'>禁用</a>";
// 	        	}
	        	
// 	        	if(rowData.seatSale == 0){
// 	        		html += "<a class=\'g-mlr-5\' onclick=\'updateSeatSale(" + rowData.cinemano +',' + '1' + ")\'>选座票启售</a>";
// 	        	}
// 	        	else if(rowData.seatSale ==1){
// 	        		html += "<a class=\'g-mlr-5\' onclick=\'updateSeatSale(" + rowData.cinemano +',' + '0' + ")\'>选座票停售</a>";
// 	        	}
	        	
// 	        	if(rowData.commonSale == 0){
// 	        		html += "<a class=\'g-mlr-5\' onclick=\'updateCommonSale(" + rowData.cinemano +',' + '1' + ")\'>通兑票启售</a>";
// 	        	}
// 	        	else if(rowData.commonSale ==1){
// 	        		html += "<a class=\'g-mlr-5\' onclick=\'updateCommonSale(" + rowData.cinemano +',' + '0' + ")\'>通兑票停售</a>";
// 	        	}
	        	
// 	        	return html;
// 	        }
// 		};
	
	    showDetail: function (cinemano){
    	var url = encodeURI(encodeURI("../cinema/cinemaDetailed.html?cinemano="+cinemano)); 	 
    	 $("#layout_box iframe").attr("src", url);
         $('#layout_box').window({
             title: "影院详细记录表"
         });
	},
	
		updateStatus: function(id,flag){
			var str;
			if(flag == "1"){
				str = "启用";
			}else if(flag == "0"){
				str = "禁用";
			}
			
			$.messager.confirm("提示","确认" + str + "吗？",function(b){
				if(b){
					$.ajax({
						    cache: true,
				            type: "POST",
				            data:"id="+id+"&status="+flag,
				            url:"../cinema/updateBaseCinema.do" ,			            
				            dataType: "json",
				            async: false,
				            error: function(request) {
				                alert("启用失败，请联系管理员！");
				            },
				            success: function(data) {
				               if(data.success){
				            	   datalist._temp.list_box.datagrid("reload");
	     		        		   Tips.Success("成功");
				               }else{
				            	   alert(data.errMsg);
				               }            
				            }
					});
				}
			});			
		},
		//起售状态设置
			updateSeatSale:function (id,seatSale){
			var seat;
			if(seatSale == "1"){
				seat = "启售";
			}else if(seatSale == "0"){
				seat = "停售";
			}
			
			$.messager.confirm("提示","确认" + seat + "吗？",function(a){
				if(a){
					$.ajax({
						    cache: true,
				            type: "POST",
				            data:"id="+id+"&seatSale="+seatSale,
				            url:"../cinema/updateBaseCinema.do" ,			            
				            dataType: "json",
				            async: false,
				            error: function(request) {
				                alert("启用失败，请联系管理员！");
				            },
				            success: function(data) {
				               if(data.success){
				            	   datalist._temp.list_box.datagrid("reload");
	     		        		   Tips.Success("成功");
				               }else{
				            	   alert(data.errMsg);
				               }            
				            }
					});
				}
			});		
			
		},
		
		//业务状态
		//起售状态设置
			updateCommonSale:function (id,commonSale){
			var sale;
			if(commonSale == "1"){
				sale = "启售";
			}else if(commonSale == "0"){
				sale = "停售";
			}
			
			$.messager.confirm("提示","确认选择" + sale + "吗？",function(c){
				if(c){
					$.ajax({
						    cache: true,
				            type: "POST",
				            data:"id="+id+"&commonSale="+commonSale,
				            url:"../cinema/updateBaseCinema.do" ,			            
				            dataType: "json",
				            async: false,
				            error: function(request) {
				                alert("启用失败，请联系管理员！");
				            },
				            success: function(data) {
				               if(data.success){
				            	   datalist._temp.list_box.datagrid("reload");
	     		        		   Tips.Success("成功");
				               }else{
				            	   alert(data.errMsg);
				               }            
				            }
					});
				}
			});		
			
		},
		
	
		editPayChannel:function (cinemano){
			$("#layout_box iframe").attr("src", '../cinema/toEditBaseCinema.html?id=' + cinemano);
            $('#layout_box').window({
                title: "编辑供应设置"
            });
		},
				
		editcinematype:function (cinemano){
			$("#layout_box iframe").attr("src", '../cinema/toEditCinemaType.html?id=' + cinemano);
            $('#layout_box').window({
                title: "编辑"
            });
		}
		}

		function closeWin(){
			 $('#datalist').window('close');
			 // 刷新页面
			 $('#layout_box').datagrid('reload');
		}
		$(function(){
			datalist.Init();
		});
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>