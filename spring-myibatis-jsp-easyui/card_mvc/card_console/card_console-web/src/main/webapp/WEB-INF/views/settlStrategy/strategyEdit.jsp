<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>


<easyui:templateOverride name="title">编辑通兑票结算策略</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="script">
    <script type="text/javascript">
        var id = "";
        var cinemaNo = "${strategy.cinemaNo}";
        var settlType = "";
        $(function () {
        	//限定结束时间大于开始时间
            $('#endDate').datebox().datebox('calendar').calendar({
               validator: function(date){
                   var startDate = new Date($('#startDate').datebox('getValue'));
                   var d1 = new Date(startDate.getFullYear(), startDate.getMonth(), startDate.getDate());
                   return date >= d1;
               }
           });             	
        	
            //初始化数据
            load();
            //初始化控件
            init();
         
            
        });

        var load = function () {
        	$("#name").textbox("setValue", "${strategy.name}");
            $("#startDate").textbox("setValue", "${strategy.startDate}");
            $("#endDate").textbox("setValue", "${strategy.endDate}");
            $("input:radio[value=" + "${strategy.settlType}" + "]").attr("checked", "checked");
            settlType = "${strategy.settlType}";
            cinemaNo = "${strategy.cinemaNo}";
            id = "${strategy.id}";
  
        }

        var init = function () {
        	 id = "${strategy.id}";
        	settlType = "${strategy.settlType}";
            $("input:radio").attr("disabled", "disabled");
            $("input:radio[value=" + settlType + "]").attr("checked", "checked");
            $("#ruleBox").attr("src", "../settlStrategy/ruleEdit.html?id="+id);
        }

        function Save(){
        	id = "${strategy.id}";
            cinemaNo ="${strategy.cinemaNo}";
            var status = "${strategy.status}";
            if(status==-3){
            	status = 3;
            }else{
            	status = 2;
            }
		    var params = $("#ruleBox")[0].contentWindow.createParams();
			var name = $("#name").textbox("getValue");
			if (validate(params)) {
				var data = {
						id:id,
		       			name: name,
		       			status:status,
		       			cinemaNo:cinemaNo,
		       			settlType: $('input:radio:checked').val(),
		       			startDate: $("#startDate").combobox("getValue"),
		       			endDate: $("#endDate").combobox("getValue"),
		       			params:params
		            };
				$.ajaxRequest({
	        		url: "strategyEdit.do",
	        		para: data,
	        		success: function(result){
	        			if(result.isSuccess){
	        				parent.datalist._temp.list_box.treegrid("reload");
		        			parent.Tips.Success("操作成功");
		        			Cancel();
		        		}
	        			else{
	        				parent.Tips.Error(result.message);
	        			}
	        		}
	        	});
			
			}
        }
        
        
        
       /*  var save = function () {
        	cinemaNo = "${strategy.cinemaNo}";
            var params = $("#ruleBox")[0].contentWindow.createParams();
            if (validate(params)) {
                $.ajax({
                    type: 'post',
                    cache: false,
                    dataType: 'json',
                    url: id == "" ? "/settlStrategy/Add.do" : "/settlStrategy/Update.do",
                    data: {
                        id: id,
                        settlType: $('input:radio:checked').val(),
                        cinemaNo: cinemaNo,
                        name: $("#name").textbox("getValue"),
                        StartDate: $("#txt_startDate").datebox("getValue"),
                        EndDate: $("#txt_endDate").datebox("getValue"),
                        models: params
                    },
                    async: true,
                    success: function (data) {
                        if (!data.IsSucceed) {
                            parent.Tips.Error(data.Message);
                        } else {
                            parent.Tips.Success("操作成功");
                            cancel();

                            if (id == "") {
                                parent.treeGrid.search();
                                return;
                            }

                            parent.treeGrid.treeGrid.treegrid('update', {
                                id: id,
                                row: {
                                    Status: data.Result[0].Status,
                                    ValidLimit: data.Result[0].ValidLimit,
                                    name: data.Result[0].name
                                }
                            });

                            $.each(data.Result[0].children, function () {
                                if (parent.treeGrid.treeGrid.treegrid('find', this.ID)) {
                                    parent.treeGrid.treeGrid.treegrid('update', {
                                        id: this.ID,
                                        row: {
                                            SettlPrice: this.SettlPrice
                                        }
                                    });
                                } else {
                                    parent.treeGrid.treeGrid.treegrid('append', {
                                        parent: id,
                                        data: [{
                                            RowType: "Rule",
                                            ID: this.ID,
                                            name: this.name,
                                            SettlType: this.SettlType,
                                            SettlPrice: this.SettlPrice,
                                            ValidLimit: "",
                                            Status: this.Status
                                        }]

                                    });
                                }
                            });
                        }
                    }
                });
            } 
        }*/

        function Cancel(){
			parent.datalist._temp.layout_box.window("close");
		}

        var validate = function (params) {
            var isOk = true;

            if ($("#name").textbox("getValue") == "") {
                $("#name").textbox("showError", "请输入策略名称");
                isOk = false;
            } else if ($("#name").textbox("getValue").length > 25) {
                $("#name").textbox("showError", "不能超过25个汉字");
                isOk = false;
            } else {
                $("#name").textbox("closeError");
            }
            if ($("#startDate").datebox("getValue") == "") {
                $("#startDate").textbox("showError", "请输入开始时间");
                isOk = false;
            } else {
                $("#startDate").textbox("closeError");
            }
            if ($("#endDate").datebox("getValue") == "") {
                $("#endDate").textbox("showError", "请输入到期时间");
                isOk = false;
            } else {
                $("#endDate").textbox("closeError");
            }
            if (params == false) {
                isOk = false
            }

            return isOk;
        }
    </script>
</easyui:templateOverride>

<easyui:templateOverride name="body">

	<div data-options="region:'center',border:false">
	    <form class="edit_box" style="padding-bottom: 0;">
	        <div class="item">
	            <label>策略名称：</label><input class="easyui-textbox" id="name" name="name"></input><span class="star">*</span>
	        </div>
	        <div class="item">
	            <label>定价类型：</label><input type="radio" name="settlType" id="type1" value="0" checked style="margin-top: 5px;" />常规价
	                <input type="radio" name="settlType" id="type2" value="1" style="margin-top: 5px;" />闲忙时
	        </div>
	        <div class="item">
	            <label>有效期起：</label><input id="startDate" name="startDate" type="text" class="easyui-datebox" required="required"><span class="star">*</span>
	        </div>
	        <div class="item">
	            <label>有效期止：</label><input id="endDate" name="endDate" type="text" class="easyui-datebox" required="required"><span class="star">*</span>
	        </div>
	        <iframe id="ruleBox" style="width: 100%; height: 340px; border: none;"></iframe>
	        <div class="item item_row button">
	            <a class="easyui-linkbutton"  onclick="Save()">保存</a>
	            <a class="easyui-linkbutton"  onclick="Cancel()">取消</a>
	        </div>
	    </form>
	</div>
</easyui:templateOverride>
<%@include file="/WEB-INF/views/share/include.jsp" %>