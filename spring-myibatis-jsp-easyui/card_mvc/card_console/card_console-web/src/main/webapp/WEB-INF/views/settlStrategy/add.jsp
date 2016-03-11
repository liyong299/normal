<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">添加结算策略</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<form id="info" class="edit_box"  method="post" action="">
        
        <div class="item" style="float: left;margin: 5px 0;width: 350px;min-height: 24px;line-height: 23px;max-height: 24px;"><label>策略名称：</label><easyui_ext:textBox id="name" /><span class="star">*</span></div>
        <div class="item"><label>定价类型：</label><input type="radio" name="settlType" id="type1" value="0" style="margin-top: 5px;" />常规价
	                <input type="radio" name="settlType" id="type2" value="1" style="margin-top: 5px;" />闲忙时</div>
        <div class="item"><label>有效期起：</label><easyui:inputDate id="startDate" name="startDate" value=""  required="required"/></div>
        <div class="item"><label>有效期止：</label><easyui:inputDate id="endDate" name="endDate" value=""  required="required"/></div>
        <iframe id="ruleBox" style="width: 100%; height: 340px; border: none;">
        </iframe>
        <div class="item item_row button">
            <a class="easyui-linkbutton" onclick="Save()">保存</a>
            <a class="easyui-linkbutton" onclick="Cancel()">取消</a>
        </div>
    </form>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
	$(function () {
		 //限定结束时间大于开始时间
        $('#endDate').datebox().datebox('calendar').calendar({
            validator: function(date){
                var startDate = new Date($('#startDate').datebox('getValue'));
                var d1 = new Date(startDate.getFullYear(), startDate.getMonth(), startDate.getDate());
                return date >= d1;
            }
        });
		
        //初始化控件
        init();
       
        
    });
    var init = function () {
        var id="";
        var cinemaNo ="${cinemaNo}";
        var settlType = "";
    	$("input:radio").attr("disabled", "disabled");
			
        if (id == "") {
            $.ajax({
                type: 'post',
                cache: false,
                dataType: 'json',
                url: "<%=request.getContextPath()%>/settlStrategy/getSettlType.do",
                data: {
                    cinemaNo: cinemaNo
                },
                async: false,
                success: function (data) {
                    if (!data.isSuccess) {
                        parent.Tips.Error(data.Message);
                    } else {
                    	settlType = data.total;
                    }
                }
            });
        }
        if(settlType==2){
        	settlType = 1;
        }
        $("input:radio[value=" + settlType + "]").attr("checked", "checked");
        $("#ruleBox").attr("src", "<%=request.getContextPath()%>/settlStrategy/ruleAdd.html?cinemaNo="+cinemaNo);
    }
	   var submitFlag = false;
		function Save(){
			if (submitFlag) {
				return;
			}
			submitFlag = true;
			var cinemaNo ="${cinemaNo}";
			var isok = true;
			 var params = $("#ruleBox")[0].contentWindow.createParams();
			var name = $("#name").textbox("getValue");
	        if (name == "") {
	            $("#name").textbox("showError", "策略名称不能为空！");
	            if (isok) {
	                parent.Tips.Error("策略名称不能为空！");
	            }
	            isok = false;
	        }
	        else if (name.length > 30) {
	            $("#name").textbox("showError", "策略名称不能超过30个字符！");
	            if (isok) {
	                parent.Tips.Error("策略名称不能超过30个字符！");
	            }
	            isok = false;
	        }
	        else {
	            $("#name").textbox("closeError");
	        }
	        var settlType =$('input:radio:checked').val();
	        var startDate = $("#startDate").combobox("getValue");
	        var endDate = $("#endDate").combobox("getValue");
	        if(params==false){
	        	isok = false;
	        }
	        if(!isok){
	        	return false;
	        }
	        var data = {
       			name: name,
       			cinemaNo:cinemaNo,
       			settlType: settlType,
       			startDate: startDate,
       			endDate: endDate,
       			params:params
            };
	      
	      
	           $.ajaxRequest({
	        		url: "add.do",
	        		para: data,
	        		success: function(result){
	        			submitFlag = false;
	        			if(result.isSuccess){
	        				parent.datalist._temp.list_box.treegrid("reload");
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
	</script>
	
</easyui:templateOverride>
<%@include file="/WEB-INF/views/share/include.jsp" %>