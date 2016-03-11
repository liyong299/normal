<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<easyui:templateOverride name="title">查看影院基本信息</easyui:templateOverride>
<easyui:templateOverride name="head">
<script src="<%=request.getContextPath()%>/resources/scripts/common/area.js"></script>
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<form id="info" class="edit_box"  method="post" action="" style="padding-top:0" >
	<div id="tt" class="easyui-tabs">
	<div title="影院信息">

        <div class="item"><label> 影院编号：</label><span>${baseCinema.cinemano}</span></div>
        <div class="item"><label>影院网址：</label><span>${baseCinema.url}</span></div>
    	<div class="item "><label> 影院名称：</label><span>${baseCinema.cinemaname} </span></div>
        <div class="item "><label>影厅数量：</label><span>${baseCinema.hallcount}</span></div>
        <div class="item "><label> 省份编码：</label><span>${baseCinema.provinceno}</span></div>
        <div class="item "><label>省份名称：</label><span>${baseCinema.province}</span></div>
        <div class="item "><label> 城市编码：</label><span>${baseCinema.cityno}</span></div>
        <div class="item "><label>城市名称：</label><span>${baseCinema.city}</span></div>
		<div class="item "><label>辖区编码：</label><span>${baseCinema.countyno}</span></div>
		<div class="item "><label>辖区名称：</label><span>${baseCinema.county}</span></div>
		<div class="item "><label>客服电话：</label><span>${baseCinema.tel}</span></div>
		<div class="item "><label>综合评分：</label><span>${baseCinema.grade}</span></div>
		<div class="item "><label>经度：</label><span>${baseCinema.longitude}</span></div>
		<div class="item "><label>纬度：</label><span>${baseCinema.latitude}</span></div>
		<div class="item item_row"><label>影院简介：</label>
		<input class="easyui-textbox" data-options="width: '506', height: '50', multiline: true" id="intro" name="intro"  readonly="true"  value="${baseCinema.intro}" />
		</div>
		<div class="item item_row"><label>影院特色：</label>
		<input class="easyui-textbox" data-options="width: '506', height: '50', multiline: true" id="feature" name="feature"  readonly="true"    value="${baseCinema.feature}" />
		</div>
		<div class="item item_row"><label>终端位置：</label>
		<input class="easyui-textbox" data-options="width: '506', height: '50', multiline: true" id="devicepos" name="devicepos"  readonly="true"   value="${baseCinema.devicepos}" />
		</div>
		<div class="item item_row"><label>公交路线：</label>
		<input class="easyui-textbox" data-options="width: '506', height: '50', multiline: true" id="busline" name="busline" readonly="true"    value="${baseCinema.busline}" />
		</div>
		<div class="item item_row"><label>影院地址：</label>
		<input class="easyui-textbox" data-options="width: '506', height: '50', multiline: true" id="address" name="address" readonly="true"    value="${baseCinema.address}" />
		</div>
		<div class="item item_row"><label>短信类型：</label>
		<input class="easyui-textbox" data-options="width: '506', height: '50', multiline: true" id="printmode" name="printmode" readonly="true"   value="${baseCinema.printmode}" />
		</div>
		<div class="item item_row">
                    <label>取票方式：</label>
                    <input id="TicketsWay_0" name="TicketsWay" type="radio" readonly="true" /><label for="TicketsWay_0">平台凭证</label>
                    <input id="TicketsWay_1" name="TicketsWay" type="radio"  readonly="true"  /><label for="TicketsWay_1">接入商取票号</label>
                    <input id="TicketsWay_2" name="TicketsWay" type="radio"  readonly="true" /><label for="TicketsWay_2">平台凭证和接入商取票号</label>
        </div>	
		  <div class="item ">
                    <label>影院状态：</label>
                    <input id="status_0" name="status" type="radio"  readonly="true"  /><label for="status_0">禁用</label>
                    <input id="status_1" name="status" type="radio"  readonly="true"  /><label for="status_1">启用</label>
             
                </div>
        <div class="item">
                    <label>通兑票结算类型：</label>
                    <input id="priceType_0" name="priceType" type="radio"  readonly="true"   /><label for="priceType_0">常规价</label>
                    <input id="priceType_1" name="priceType" type="radio"  readonly="true"  /><label for="priceType_1">闲忙时价</label>
        </div>
		<div class="item ">
                    <label>供应设置：</label>
                    <input id="commonFlag" name="CommonSettlType" type="radio" readonly="true"  /><label for="commonFlag">供应通兑票</label>
        </div>
        <div class="item ">
                    <label>客户端供应显示：</label>
                    <c:if test="${baseCinema.viewGoodsType==0 }">
                    	<span>选座票,通兑票</span>
                    </c:if>
                     <c:if test="${baseCinema.viewGoodsType==1}">
                    	<span>选座票</span>
                    </c:if>
                     <c:if test="${baseCinema.viewGoodsType==2}">
                    	<span>通兑票</span>
                    </c:if>
        </div>
		<div class="item item_row">
                    <label>影院图片：</label>
                    <div style="float: left">
                        <div>
                            <img id="CinemaImage"    src="${baseCinema.logo}"  style="width: 100px" />
                        </div>
                    </div>
                </div>

		<div class="item item_row">
                    <label>自动取票机图片：</label>
                    <div style="float: left">
                        <div>
                            <img id="PosImage"   src="${baseCinema.deviceimg}"   style="width: 100px"  />
                        </div>
                    </div>
                </div>
    </div>
    
        <div title="影厅信息">
        	<div class="item item_row">
        	
				<table style="border: 1px;">
				<thead >
                <tr>
                    <th align="left">影厅编码</th>
                    <th align="left">影厅名称</th>
                    <th align="left">座位数量</th>
                    <th align="left">影院编码</th>
                </tr>
            </thead>               
                     <c:forEach var="item" items="${list}">
                     <tr>
                         <td  align="left">${item.hallno}</td>
                         <td  align="left">${item.hallname}</td>
                         <td  align="left">${item.seatcount}</td>
                         <td  align="left">${item.cinemano}</td>                      
                     </tr>
                     </c:forEach>
                     </table>
        </div>
 </div>
   </div>  
        <div class="item item_row button">
            <a class="easyui-linkbutton" id="btn_cancel" onclick="Cancel()">关闭</a>
        </div>
    
 </form>
</easyui:templateOverride>	
<easyui:templateOverride name="script">
	<script>
		function Cancel(){
			parent.datalist._temp.layout_box.window("close");
		}
		
		$(function(){
			var status = "${baseCinema.status}";
			var priceType="${baseCinema.priceType}";
			var getTicketType="${baseCinema.getTicketType}";
			var commonFlag="${baseCinema.commonFlag}";
			if(status == "0"){
				$("#status_0").attr("checked","true");
			}else if(status == "1"){
				$("#status_1").attr("checked","true");
			}
			if(priceType=="0"){
				$("#priceType_0").attr("checked","true");
			}else if(priceType=="1"){
				$("#priceType_1").attr("checked","true");
			}
			if(getTicketType=="0"){
				$("#TicketsWay_0").attr("checked","true");
			}else if(getTicketType=="1"){
				$("#TicketsWay_1").attr("checked","true");
			}else if(getTicketType=="2"){
				$("#TicketsWay_2").attr("checked","true");
			}
			
			if(commonFlag=="0"){
				$("#commonFlag").attr("checked","true");
			}else if(commonFlag=="1"){
				$("#commonFlag").attr("checked","true");
			}
			
			});
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>