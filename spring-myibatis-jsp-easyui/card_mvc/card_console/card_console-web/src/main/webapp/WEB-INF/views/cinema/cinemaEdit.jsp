<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<style>
	em{color:red;}
	.items{
		padding:5px;
		margin-left:20px;
	}
	.items label{
		display:inline-block;
	}
</style>

<easyui:templateOverride name="title">供应设置</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
 <form  id="updateForm" class="edit_box"  action="../cinema/saveBaseCinema.do" >
            <div class="items">
                    <input id="cinemano" name="cinemano" type="hidden" value="${cinema.cinemano}" />
					<label style="width: 120px" for="itemName">影院名称：<em></em></label> 
					<span>${cinema.cinemaname}</span>
			</div>
			
<!-- 			<!-- 选座票 --> 
<!-- 			<div class="items"> -->
<%--              <input id="seatFlag" name="seatFlag" value="0"  type="checkbox" <c:if test="${cinema.seatFlag == 1}">checked</c:if> /> 供应选座票 --%>
<!-- 			</div> -->
<!--          <div class="items"> -->
<!--         	<label style="width: 120px" for="itemName">规格<em>*</em>：</label> -->
<!-- 			<input  id="goodsShowtype0" name="goodsShowtype" value="0"  type="checkbox" />2D -->
<!-- 			<input  id="goodsShowtype1" name="goodsShowtype" value="1"  type="checkbox"   />3D -->
<!-- 			<input  id="goodsShowtype2"  name="goodsShowtype" value="2"  type="checkbox"   />	IMAX2D -->
<!-- 			<input  id="goodsShowtype3" name="goodsShowtype" value="3"  type="checkbox"   />IMAX3D -->
<!-- 			<input  id="goodsShowtype4" name="goodsShowtype" value="4"  type="checkbox"   />中国巨幕2D -->
<!-- 		    <input   id="goodsShowtype5" name="goodsShowtype" value="5"  type="checkbox"  />	中国巨幕3D -->
<!--         </div> -->
        
        
        <div class="items">
         <input id="commonFlag" name="commonFlag"  value="1" type="checkbox"    onclick="mm()"  <c:if test="${cinema.commonFlag ==1 }">checked</c:if> /> 供应通兑票
        </div>         
           
           <div  class="items">
        	<label style="width: 120px" for="itemName">规格<em>*</em>：</label>
			<input id="goodsShowtype10"  name="goodsShowtype1" value="0"  type="checkbox"  onclick="ss(this)" />2D
			<input id="goodsShowtype11" name="goodsShowtype1" value="1"  type="checkbox"  onclick="ss(this)" />3D
			<input id="goodsShowtype12"  name="goodsShowtype1" value="2"  type="checkbox"  onclick="ss(this)" />	IMAX2D
			<input id="goodsShowtype13" name="goodsShowtype1" value="3"  type="checkbox"   onclick="ss(this)"/>IMAX3D
			<input id="goodsShowtype14" name="goodsShowtype1" value="4"  type="checkbox"   onclick="ss(this)"/>中国巨幕2D
		    <input id="goodsShowtype15"  name="goodsShowtype1" value="5"  type="checkbox"   onclick="ss(this)"/>	中国巨幕3D
        </div>
         <div class="items">
        	<label style="width: 120px" for="itemName">定价类型<em>*</em>：</label> 
			<input id="priceType_0" name="priceType" value="0" type="radio"  <c:if test="${cinema.priceType == 0 }">checked</c:if> />常规价
			<input id="priceType_1"  name="priceType" value="1"  type="radio"  <c:if test="${cinema.priceType == 1 }">checked</c:if> />闲忙时价
        </div>
         
            <input id="status" name="status" type="hidden" value="${cinema.status}" />
			<input id="seatSale" name="seatSale" type="hidden" value="${cinema.seatSale}" />
			<input id="commonSale" name="commonSale" type="hidden" value="${cinema.commonSale}" />
<%-- 			<input id="goodsType" name="goodsType" type="hidden" value="${cinemaStype.goodsType}" /> --%>
			<input id="cinemaname" name="cinemaname" type="hidden" value="${cinema.cinemaname}" />
                 
         <div class="item item_row button"> 
            <a class="easyui-linkbutton" onclick="doEdit()">保存</a>
            <a class="easyui-linkbutton" onclick="Cancel()">取消</a>
        </div>
    </form>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
	 $(function(){
			var tdp = ${tdp};  	
			if(tdp!=null && tdp!=""){
				for(i=0;i<tdp.length;i++){
					$("#goodsShowtype1" + tdp[i]).click();
				}
			}
	 });
	 //供应通兑票全选全部选的ＪＳ方法
			var zt=true;
			function mm()
			{
			var a = document.getElementsByTagName("input");
			for (var i=0; i<a.length; i++) 
				if (a[i].type == "checkbox") a[i].checked = zt;
			 	if(zt==true){
			 		zt=false;
			 		}else{
			 		zt=true;
			 	}			 
			}
 		 //选取了规格之后供应通兑票状态必须打钩
			function ss(obj)
			{
                if(obj.checked){
                	$('#commonFlag').attr("checked","true");
                }else{
                	var chekBoxs = document.getElementsByName("goodsShowtype1");
                	var flag = false;
                	for(i=0;i<chekBoxs.length;i++){
                		if(chekBoxs[i].checked){
                			flag = true;
                		}
                	};
                	if(!flag){	
                		$('#commonFlag').attr("checked",false);
                	}
                }
			}



	function doEdit() {
		var isok = true;
		 var tests = document.getElementsByName('priceType');
		var num = 0;
		for (i = 0; i < tests.length; i++) {
		if (tests[i].checked) {
		num = 1;
		}
		}
		if (num != 1){
			parent.Tips.Error("请选择定价类型！"); 
       		 return;
		}
		if(!isok){
        	return false;
        }
        //转换规格值，定义两个数值，SCorpMode为选座票规格，CCorpMode为通兑票规格
		var CorpMode="";
		var SCorpMode="";
	    var CCorpMode="";
		var commonFlag=$("[name=commonFlag]:checked").val();
		var seatFlag=$("[name=seatFlag]:checked").val();
		if(seatFlag){			
			SCorpMode+="S:";
			  $.each($("[name=goodsShowtype]:checked"), function (i, n) {
			    	SCorpMode += ","+$(n).val() ;
		        });
		};
		if(commonFlag){			
			CCorpMode+="C:";
			  $.each($("[name=goodsShowtype1]:checked"), function (i, n) {
				  CCorpMode += ","+ $(n).val();
		        });
		}		
		//S:0,1,2,3,4,5|C:0,1,2,3,4,5
		CorpMode = SCorpMode +"|"+CCorpMode;
	  
 		var priceType = $("[name=priceType]:checked").val();	
		var cinemano = '${cinema.cinemano }';
		var goodsType =  $("#goodsType").val();
		var commonSale =  $("#commonSale").val();
		var status = $("#status").val();
		var seatSale = $("#seatSale").val();
		var cinemaname=$("#cinemaname").val();

		var param = {commonFlag:commonFlag,seatFlag:seatFlag,cinemano:cinemano,priceType:priceType,goodsShowtype:CorpMode,status:status,seatSale:seatSale,commonSale:commonSale,goodsType:goodsType,cinemaname:cinemaname};
			$.ajax({
	            cache: true,
	            type: "POST",
	            url:"../cinema/editBaseCinema.do",
	            data:param,// 你的formid
	            dataType: "json",
	            async: false,
	            
	            error: function(request) {
	                alert("保存失败，请联系管理员");
	            },
	            success: function(data) {
	               if(data.success){
	            	   $.messager.show(data.resultMsg);
	            	   parent.Tips.Success("保存成功");
	            	   parent.datalist._temp.list_box.datagrid("reload");
            		   Cancel();
	               }else{
	            	   alert(data.errMsg);
	               }            
	            }
	        });

	};	
	   
	
	function Cancel(){
		parent.datalist._temp.layout_box.window("close");
	}
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>