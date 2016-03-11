<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">添加销售策略</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
    <easyui:layout id="test-layout" fit="true">
	    <easyui:layoutUnit region="north" border="false">
	    	<form id="info" class="edit_box"  method="post" action="" style="padding:0">
	    		<input id="id" type="hidden" value="" />
	    		<input id="cinemaNo" type="hidden" value="" />
		        <div class="item"><label>策略参考：</label>
			        <easyui:comboGrid id="settlList" url="getSettlStrategy.do?cinemaNo=${cinemaNo}" idField="id" textField="name" panelWidth="360" fitColumns="true" data="${settlList}" style="height:22px" >
					    <easyui:columns>
					        <%-- <easyui:column field="id" title="策略id" hidden="true"/> --%>
					        <easyui:column field="name" title="策略名称"/>
					        <easyui:column field="settlType" title="定价类型" formatter="function(value,rowData,index){
					        	if(value == '0'){
									return '常规价';
								}else if(value == '1'){                                     
									return '闲忙时价';
								}
					        }"/>
					      <easyui:column field="date" title="有效期限" align="center" rowspan="2" formatter="function(value, rowData, index){
					        	if(rowData.startDate !='' && rowData.startDate!=null){
					        		return rowData.startDate + ' ~ ' + rowData.endDate;
					        	}
					        }"/>
					    </easyui:columns>
					</easyui:comboGrid>
		       		<%-- <easyui_ext:comboBox id="settList" name="settlStrategyId" data="${settlList}" valueField="id" textField="name"/> --%>
		       		<span class="star">*</span> 
		       </div>
		        <div class="item"><label>策略名称：</label><easyui_ext:textBox id="name" /><span class="star">*</span></div>
		         <div class="item"><label>策略类型：</label>
					<input type="radio" name="strategyType" id="strategyType_0" value="0" >全局
					<input type="radio" name="strategyType" id="strategyType_1" value="1">分销商
				</div>
		        <div class="item"><label>定价类型：</label>
					<input type="radio" name="saleType" id="saleType_0" value="0">常规价
					<input type="radio" name="saleType" id="saleType_1" value="1">闲忙时价
				</div>
		        <div class="item"><label>有效期起：</label><input id="startDate" class="easyui-datebox" required="required"/><span class="star">*</span></div>
		        <div class="item"><label>有效期止：</label><input id="endDate" class="easyui-datebox" required="required"/><span class="star">*</span></div>
		        <div class="item item_row button">
		            <a class="easyui-linkbutton" onclick="Save()">保存</a>
		            <a class="easyui-linkbutton" onclick="Cancel()">取消</a>
		        </div>
		    </form>
	    </easyui:layoutUnit>
	    <easyui:layoutUnit region="center" border="false" style="padding-bottom:40px">
	    	<!-- <div style="font-size: 12px;font-weight: bold;color: #777;height: 22px;line-height: 16px;">设置规则</div> -->
	    	<table id="dg"></table>
	    </easyui:layoutUnit>
    </easyui:layout>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
		$(function(){
			//限定结束时间大于开始时间
            $('#endDate').datebox().datebox('calendar').calendar({
                validator: function(date){
                    var startDate = new Date($('#startDate').datebox('getValue'));
                    var d1 = new Date(startDate.getFullYear(), startDate.getMonth(), startDate.getDate());
                    return date >= d1;
                }
            });
			
			
			//初始化数据
            list.load();
			
			$("#id").val("${id}");
			$("#cinemaNo").val("${cinemaNo}");
			$("#settlList").combogrid("setValue", "${settlStrategyId}");
			$("#name").textbox("setValue", "${name}");
			$("#startDate").datebox("setValue", "${startDate}");
			$("#endDate").datebox("setValue", "${endDate}");
			var saleType = '${saleType}';
			if(saleType == '0'){
				$("#saleType_0").click();
				$("input[name='saleType']").attr("disabled", "disabled");
			}else if(saleType == '1' || saleType=='2'){
				$("#saleType_1").click();
				$("input[name='saleType']").attr("disabled", "disabled");
			}
			$("input[name='strategyType'][value='${strategyType}']").attr("checked",true); 
			$("input[name='strategyType']").removeAttr("disabled");
			
			//初始化数据
            list.init();
          	
            
		});
		
var list = {
     editIndex: [undefined, undefined],
     displayType: "",
     settlStrategyId :'',
     current_edit_index: -1,
     init:function(){
    	 $('#dg').datagrid({
				title:'设置规则',
			    iconCls:'icon-edit',
			    //data:data,
			    url:"editSaleRule.do?saleStrategyId="+"${id}",
			    columns:[[
			        {field:'showType',title:'放映类型',width:100,align: 'center',rowspan: 2,formatter: function(value,row,index){
			        	if(value == '0'){
							return '2D';
						}else if(value == '1'){                                     
							return '3D';
						}else if(value == '2'){                                     
							return 'IMAX2D';
						}else if(value == '3'){                                     
							return 'IMAX3D';
						}else if(value == '4'){                                     
							return '中国巨幕2D';
						}else if(value == '5'){                                     
							return '中国巨幕3D';
						}
					}},
			        {field:'saleType',title:'定价类型',width:100,align: 'center',rowspan: 2,formatter: function(value,row,index){
			        	if(value == '0'){
							return '常规价';
						}else if(value == '1'){                                     
							return '忙时价';
						}else if(value == '2'){                                     
							return '闲时价';
						}
					}},
			        { title: '销售价格', colspan: 2},
			        {field:'pointAmount',title:'销售点数(点)',width:100,align: 'center',rowspan: 2,editor:{type:'textbox',options: {required: true, tipPosition: "bottom", validType: 'validate["pointAmount"]', missingMessage: "此项必填" }}},
			        {field:'paperAmount',title:'销售张数(张)',width:100,align: 'center',rowspan: 2},
			        {field:'exchangeAmount',title:'兑换2D张数(张)',width:100,align: 'center',rowspan: 2,editor:{type:'textbox',options: {tipPosition: "bottom", validType: 'validate["exchangeAmount"]'}, min: 0}}
			    ],
			    [
                {
                  field: 'type', title: '定价方式', width: 100, align: 'center',formatter: function(value,row,index){
			        	if(value == '0'){
							return '固定价';
						}else if(value == '1'){                                     
							return '固定加价';
						}else if(value == '2'){                                     
							return '折扣价';
						}
					}, editor: {
                      type: 'combobox',
                      options: {
                          valueField: 'value',
                          textField: 'text',
                          method: 'post',
                          url: 'getTypeDictionary.do',
                          required: true,
                          onSelect: function (record) {
                              list.displayType = record.value;
                          }
                      }
                  }
              },
              { field: 'salePrice', title: '销售价格', width: 100,align: 'center',formatter: function(value,row,index){
            	 if(row.type == '0' ){
             		 return value+'元';
             	 }else if(row.type == '1'){
             		 if(value.indexOf("-")!=-1){
							return value+'元';
              		 }else{
							return "+"+value+'元';
              		 }
					}else if(row.type == '2'){
						return value+'折';
					}else {
						return value;
					}
				  }, editor: { type:'textbox', options: {required: true, tipPosition: "bottom", validType: 'validate["salePrice"]', missingMessage: "此项必填" } }}
          		]],
			    onClickRow: function (index, rowdata) {
			    	var _this = list;
			    	if(_this.current_edit_index > -1){
			    		$(this).datagrid('endEdit', _this.current_edit_index);//关闭编辑框
			    	}
			    	
			    	if(_this.current_edit_index != index){
    			    	$(this).datagrid('beginEdit', index);//开启编辑框
    			    	_this.current_edit_index = index;
    			    }
			    	else{
			    		_this.current_edit_index = -1;
			    	}
		        },
		        onBeginEdit: function (index, row) {
                    var edSalePrice = $('#dg').datagrid('getEditor', { index: index, field: 'salePrice' });
                    $(edSalePrice.target).textbox('setValue', row.salePrice.replace("元", "").replace("折", "").replace("+", ""));
                    
                    list.displayType = row.type;
                },
		        onEndEdit: function (index, row, changes) {
		        	var edSalePrice = $('#dg').datagrid('getEditor', { index: index, field: 'salePrice' });
                    var edPointAmount = $('#dg').datagrid('getEditor', { index: index, field: 'pointAmount' });
                    var edExchangeAmount = $('#dg').datagrid('getEditor', { index: index, field: 'exchangeAmount' });
                   /*  var edType = $('#dg').datagrid('getEditor', { index: index, field: 'type' });
                    var Type = $(edType.target).combobox('getValue'); */
                    var SalePrice = $(edSalePrice.target).textbox('getValue');
                    var PointAmount = $(edPointAmount.target).textbox('getValue');
                    var ExchangeAmount = $(edExchangeAmount.target).textbox('getValue');
                    
                   /*  var unit = "";
                    var prefix = "";
                    if (Type == "0") {
                        unit = "元";
                        prefix = "";
                    } else if (Type == "1") {
                        unit = "元";
                        if (parseFloat(SalePrice) >= 0) {
                            prefix = "+";
                        }
                    } else if (Type == "2") {
                        unit = "折";
                        prefix = "";
                    } */
                    
		        	$('#dg').datagrid('updateRow', {
                        index: index,
                        row: {
                        	salePrice: SalePrice == "" ? "" : parseFloat(SalePrice).toFixed(2),
                            pointAmount: PointAmount == "" ? "" : parseFloat(PointAmount).toFixed(2),
                            exchangeAmount: ExchangeAmount == "" ? "" : parseFloat(ExchangeAmount).toFixed(2)
                        }
                    });
                }
			});
     },
     load: function (data) {
		$('#settlList').combogrid({
            onChange: function (newValue, oldValue) {
            	settlStrategyId = newValue;
            	$('#dg').datagrid({
    				title:'设置规则',
    			    iconCls:'icon-edit',
    			    //data:data,
    			    url:"querySaleRule.do?settlStrategyId="+settlStrategyId,
    			    columns:[[
    			        {field:'showType',title:'放映类型',width:100,rowspan: 2,formatter: function(value,row,index){
    			        	if(value == '0'){
								return '2D';
							}else if(value == '1'){                                     
								return '3D';
							}else if(value == '2'){                                     
								return 'IMAX2D';
							}else if(value == '3'){                                     
								return 'IMAX3D';
							}else if(value == '4'){                                     
								return '中国巨幕2D';
							}else if(value == '5'){                                     
								return '中国巨幕3D';
							}
    					}},
    			        {field:'saleType',title:'定价类型',width:100,rowspan: 2,formatter: function(value,row,index){
    			        	if(value == '0'){
								return '常规价';
							}else if(value == '1'){                                     
								return '忙时价';
							}else if(value == '2'){                                     
								return '闲时价';
							}
    					}},
    			        { title: '销售价格', colspan: 2},
    			        {field:'pointAmount',title:'销售点数(点)',width:100,rowspan: 2,editor:{type:'textbox',options: {required: true, tipPosition: "bottom", validType: 'validate["pointAmount"]', missingMessage: "此项必填" }} , align: 'right'},
    			        {field:'paperAmount',title:'销售张数(张)',width:100,rowspan: 2},
    			        {field:'exchangeAmount',title:'兑换2D张数(张)',width:100,rowspan: 2,editor:{type:'textbox',options: {tipPosition: "bottom", validType: 'validate["exchangeAmount"]'}, min: 0} , align: 'right'}
    			    ],
    			    [
                     {
                         field: 'type', title: '定价方式', width: 100, align: 'center',formatter: function(value,row,index){
    			        	if(value == '0'){
								return '固定价';
							}else if(value == '1'){                                     
								return '固定加价';
							}else if(value == '2'){                                     
								return '折扣价';
							}
    					}, editor: {
                             type: 'combobox',
                             options: {
                                 valueField: 'value',
                                 textField: 'text',
                                 method: 'post',
                                 url: 'getTypeDictionary.do',
                                 required: true,
                                 onSelect: function (record) {
                                     list.displayType = record.value;
                                 }
                             }
                         }
                     },
                     { field: 'salePrice', title: '销售价格', width: 100,formatter: function(value,row,index){
                    	 if(row.type == '0' ){
                     		 return value+'元';
                     	 }else if(row.type == '1'){
                     		 if(value.indexOf("-")!=-1){
        							return value+'元';
                      		 }else{
        							return "+"+value+'元';
                      		 }
    					}else if(row.type == '2'){
    						return value+'折';
    					}else {
    						return value;
    					}
     				  },editor: { type: 'textbox', options: { required: true, tipPosition: "bottom", validType: 'validate["salePrice"]', missingMessage: "此项必填" } }, align: 'right' }

                 ]],
    			    onClickRow: function (index, rowdata) {
    			    	var _this = list;
    			    	if(_this.current_edit_index > -1){
    			    		$(this).datagrid('endEdit', _this.current_edit_index);//关闭编辑框
    			    	}
    			    	
    			    	if(_this.current_edit_index != index){
        			    	$(this).datagrid('beginEdit', index);//开启编辑框
        			    	_this.current_edit_index = index;
        			    }
    			    	else{
    			    		_this.current_edit_index = -1;
    			    	}
    		        },
    		        onBeginEdit: function (index, row) {
                        var edSalePrice = $('#dg').datagrid('getEditor', { index: index, field: 'salePrice' });
                        $(edSalePrice.target).textbox('setValue', row.salePrice.replace("元", "").replace("折", "").replace("+", ""));
                        
                        list.displayType = row.type;
                    },
    		        onEndEdit: function (index, row, changes) {
    		        	var edSalePrice = $('#dg').datagrid('getEditor', { index: index, field: 'salePrice' });
                        var edPointAmount = $('#dg').datagrid('getEditor', { index: index, field: 'pointAmount' });
                        var edExchangeAmount = $('#dg').datagrid('getEditor', { index: index, field: 'exchangeAmount' });
                        /* var edType = $('#dg').datagrid('getEditor', { index: index, field: 'type' });
                        var Type = $(edType.target).combobox('getValue'); */
                        var SalePrice = $(edSalePrice.target).textbox('getValue');
                        var PointAmount = $(edPointAmount.target).textbox('getValue');
                        var ExchangeAmount = $(edExchangeAmount.target).textbox('getValue');
                        
    		        	$('#dg').datagrid('updateRow', {
                            index: index,
                            row: {
                            	salePrice: SalePrice == "" ? "" : parseFloat(SalePrice).toFixed(2),
                                pointAmount: PointAmount == "" ? "" : parseFloat(PointAmount).toFixed(2),
                                exchangeAmount: ExchangeAmount == "" ? "" : parseFloat(ExchangeAmount).toFixed(2)
                            }
                        });
                    }
    			});
            }
        });
     }
}
     
		$.extend($.fn.validatebox.defaults.rules, {
            validate: {
                validator: function (value, param) {
                    var result = true;
                    if (isNaN(value)) {
                        $.fn.validatebox.defaults.rules.validate.message = "必须是数字";
                        result = false;
                    }
                    else if (parseFloat(value) <= 0  && (list.displayType != "1" || list.displayType == "1" && param[0] != "salePrice")) {
                        $.fn.validatebox.defaults.rules.validate.message = "必须大于0";
                        result = false;
                    }

                    return result;
                }
            }
        });
        
		function endEdit(){
			var rows = $('#dg').datagrid('getRows');
			for ( var i = 0; i < rows.length; i++) {
				$('#dg').datagrid('endEdit', i);
			}
		}
		
		function Save(){
			endEdit();
			var isok = true;
			var id=$("#id").attr("value");
			var cinemaNo = $("#cinemaNo").attr("value");
			var settlStrategyId = $("#settlList").combogrid("getValue");
	        if (settlStrategyId == "") {
	            if (isok) {
	                parent.Tips.Error("策略参考不能为空！");
	            }
	            isok = false;
	        } 
	        
	        var params= function () {
                var result = [];
                var rows = $("#dg").datagrid('getRows');
                $.each(rows, function (i, n) {
                        result.push({
                            showType: this.showType,
                            saleType: this.saleType,
                            type: this.type,
                            salePrice: this.salePrice,
                            pointAmount: this.pointAmount,
                            paperAmount: this.paperAmount,
                            exchangeAmount: this.exchangeAmount
                        });
                });
                return result;
            };
	        
			var name = $("#name").textbox("getValue");
	        if (name == "") {
	            $("#name").textbox("showError", "策略名称不能为空！");
	            if (isok) {
	                parent.Tips.Error("策略名称不能为空！");
	            }
	            isok = false;
	        }else {
	            $("#name").textbox("closeError");
	        }
	        
	        var saleType = $("input[name='saleType']:checked").val();
	        var strategyType = $("input[name='strategyType']:checked").val();
	        
	        var startDate = $('#startDate').datebox('getValue');
	        if (startDate == "") {
	            if (isok) {
	                parent.Tips.Error("有效期始不能为空！");
	            }
	            isok = false;
	        }
	        
	        var endDate = $('#endDate').datebox('getValue');
	        if (endDate == "") {
	            if (isok) {
	                parent.Tips.Error("有效期止不能为空！");
	            }
	            isok = false;
	        }
	        
	        if(!isok){
	        	return false;
	        }
	        
	        var data = {
	        	params:params(),
	        	cinemaNo:cinemaNo,
	        	settlStrategyId:settlStrategyId,
       			name:name,
       			saleType:saleType,
       			strategyType:strategyType,
       			startDate:startDate,
       			endDate:endDate,
       			id:id
            };
	             
	        $.ajax({
	            cache: true,
	            type: "POST",
	            url:"edit.do",
	            data:data,// 你的formid
	            dataType: "json",
	            async: false,
	            error: function(request) {
	                alert("Connection error");
	            },
	            success: function(result) {
	               if(result.success){
	            	   parent.datalist._temp.list_box.treegrid("reload");
	        			parent.Tips.Success("成功");
	        			Cancel();
	               }else{
	            	   alert(result.errMsg);
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