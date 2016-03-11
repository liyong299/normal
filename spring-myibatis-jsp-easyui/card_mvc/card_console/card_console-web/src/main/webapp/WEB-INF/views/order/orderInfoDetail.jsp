<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> --%>
<easyui:templateOverride name="title">订单信息</easyui:templateOverride>
<easyui:templateOverride name="head">
 <style type="text/css">
    .border-table {
        border-collapse: collapse;
        border: none;
    }
    .border-table td {
        border: solid #000 1px;
    }
    .fieldset{
    	width: 95%; margin:0 auto; margin-top:5px; border:1px dashed #AAA;
    }
</style>
</easyui:templateOverride>

<easyui:templateOverride name="body">
	<form id="info" class="edit_box"  method="post" action="">
	<div align="left">
			<fieldset class="fieldset">
			<legend>订单信息:</legend>
			<table>
			<tr><td>
				<c:if test="${order == null}">
						<H4><font color="red">对不起，该订单没有明细信息。</font></H4>
				</c:if>
			</td></tr>
				<c:if test="${order != null}">
				<tr>
			    <td align="right">订单编号:</td>
			    <td><easyui_ext:textBox id="orderNo" value="${order.orderNo}"  readonly="true" /></td>
			    <td align="right">订单类型:</td>
			    <td><easyui_ext:textBox id="orderType" value="${order.orderTypeShow}"  readonly="true" /></td>
			    <td align="right">订单状态:</td>
			  	<td><easyui_ext:textBox id="status" value="${order.status}"  readonly="true" /></td>
			    <td align="right">原订单编号:</td>
			    <td><easyui_ext:textBox id="originalOrderNo" value="${order.originalOrderNo}"  readonly="true"/></td>
			  </tr>
			  <tr>
			  		<td align="right">订单金额:</td>
				    <td><easyui_ext:textBox id="amount" value="${order.normalAmount}"  readonly="true"/></td>
				    <td align="right">付款金额:</td>
				    <td><easyui_ext:textBox id="finalAmount" value="${order.normalFinalAmount}"  readonly="true"/></td>
				    <td align="right">实际支付金额:</td>
				    <td><easyui_ext:textBox id="realPayAmount" value="${order.normalRealPayAmount}"  readonly="true"/></td>
			  </tr>
			  <tr>
			    <td align="right">分销商结算金额: </td>
			    <td><easyui_ext:textBox id="disSettleAmount" value="${order.normalDisSettleAmount}"  readonly="true"/></td>
			    <td align="right">是否混合支付:</td>
			    <td>
			     <easyui_ext:textBox id="isMultPay" value="${order.isMultPayShow}"  readonly="true"/>
			    </td>
			    <td align="right">下单时间:</td>
			  	<td><easyui_ext:textBox id="orderTime" value="${order.orderTimeShow}"  readonly="true"/></td>
			  </tr>
			  <tr></tr>
			  <tr>
			  	<td align="right">活动ID:</td>
			  	<td><easyui_ext:textBox id="activityId" value="${order.activityIdShow}"  readonly="true" /></td>
			  	<td align="right">出票渠道:</td>
			  	<td><easyui_ext:textBox id="channleId" value="${order.channelname}"  readonly="true"/></td>
			  	<td align="right">下单用户编号:</td>
			  	<td><easyui_ext:textBox id="userName" value="${order.userName}"  readonly="true"/></td>
			  </tr>
			  <tr></tr>
			  <tr>
			  	<td align="right">接收凭证的手机号:</td>
			  	<td ><easyui_ext:textBox id="mobile" value="${order.mobile}"  readonly="true"/></td>
			  	<td align="right">渠道订单号:</td>
			  	<td><easyui_ext:textBox id="channelOrderNo" value="${order.channelOrderNo}"  readonly="true"/></td>
			  	<td align="right">SCEC订单号:</td>
			  	<td><easyui_ext:textBox id="scecOrderNo" value="${order.scecOrderNo}"  readonly="true"/></td>
			  	<td align="right">出票接入商:</td>
			  	<td><easyui_ext:textBox id="provider" value="${order.providerName}"  readonly="true"/></td>
			  </tr>
				</c:if>

			</table>
			</fieldset>

			 </div>
			<div align="left">
			<fieldset class="fieldset">
			<legend>订单明细:</legend>
			<c:if test="${order != null}">
					<table border="1" class="border-table">
						 	  <tr>
						 	  		<!-- <td align="center" width="100px">订单明细编号</td> -->
									<td align="center" width="100px">影院名称</td>
									<td  align="center" width="100px">商品名称	</td>
									<td  align="center" width="80px">商品类型</td>
									<td  align="center" width="150px">有效时间</td>
									<td align="center" width="50px">计价类型</td>
									<td align="center" width="50px">数量</td>
									<td align="center" width="50px">销售单价</td>
								<!-- 	<td align="center" width="50px">实付单价</td> -->
									<td align="center" width="50px">分销商结算单价</td>
									<td align="center" width="80px">兑换类型</td>
									<td align="center" width="300px">商品信息</td>
									<td  align="center" width="150px">第三方取票号</td>
							</tr>

							  	<c:forEach items="${order.orderInfoDetail}"  var="item">
								  <tr>
								  	    <%-- <td align="center">${item.detailId}</td> --%>
									    <c:if test="${item.goodsType=='4'}">
									    	<td align="center">无</td>
									    	 <td align="center">《${item.filmname}》兑换劵</td>
									    </c:if>
									    <c:if test="${item.goodsType != '4'}">
									    	<td align="center">${item.cinemaname}</td>
									    	 <td align="center">${item.goodsName}</td>
									    </c:if>

									    <c:if test="${item.goodsType=='1'}">
									      	<td align="center">选座票</td>
									    </c:if>
									    <c:if test="${item.goodsType=='2'}">
									      	<td align="center">单家通兑票</td>
									    </c:if>
									      <c:if test="${item.goodsType=='3'}">
									      	<td align="center">多家通兑票</td>
									    </c:if>
									     <c:if test="${item.goodsType=='4'}">
									      	<td align="center">兑换劵</td>
									    </c:if>
									    <td align="center">${item.validDateShow}</td>
									    <c:if test="${item.goodsType=='1' || item.goodsType=='2'|| item.goodsType=='4'}">
									    	<c:if test="${empty item.priceType}">
									      	<td align="center"></td>
										    </c:if>
										     <c:if test="${item.priceType=='0'}">
										      	<td align="center">常规价</td>
										    </c:if>
										     <c:if test="${item.priceType=='1'}">
										      	<td align="center">忙时价</td>
										    </c:if>
										     <c:if test="${item.priceType=='2'}">
										      	<td align="center">闲时价</td>
										    </c:if>
									    </c:if>
									    <c:if test="${item.goodsType=='3'}">
									    	<td align="center">参见模板计价类型</td>
									    </c:if>

									    <td align="center">${item.quantity}</td>
									    <td align="center">${item.normalPrice}</td>
									   <%--  <td align="center">${item.normalRealPayPrice}</td> --%>
									    <td align="center">${item.normalSattlePrice}</td>
									     <c:if test="${item.goodsType=='1' || item.goodsType=='2' || item.goodsType=='4'}">
									      	<c:if test="${item.showType=='0'}">
									      	<td align="center">2D</td>
										    </c:if>
										    <c:if test="${item.showType=='1'}">
										      	<td align="center">3D</td>
										    </c:if>
										    <c:if test="${item.showType=='2'}">
										      	<td align="center">IMAX2D</td>
										    </c:if>
										    <c:if test="${item.showType=='3'}">
										      	<td align="center">IMAX3D</td>
										    </c:if>
										    <c:if test="${item.showType=='4'}">
										      	<td align="center">中国巨幕2D</td>
										    </c:if>
										    <c:if test="${item.showType=='5'}">
										      	<td align="center">中国巨幕3D</td>
										    </c:if>
									    </c:if>

									     <c:if test="${item.goodsType=='3'}">
									     	<td align="center">参见模板兑换类型</td>
									     </c:if>
									     <c:if test="${item.customattr != null}">
									     	<td align="center">${item.customattr}</td>
									     </c:if>
									      <c:if test="${item.customattr == null}">
									     	<td align="center">无</td>
									     </c:if>

										<c:if test="${item.thirdVoucherNo != null}">
									     	<td align="center">${item.thirdVoucherNo}</td>
									     </c:if>
									      <c:if test="${item.thirdVoucherNo == null}">
									     	<td align="center">无</td>
									     </c:if>

								    </tr>
								   </c:forEach>
					</table>
			</c:if>
			</fieldset>
			</div>
			<br/>
			<hr/>
			<div align="left">
			<fieldset class="fieldset">
				<legend>支付明细:</legend>
				<c:if test="${order != null}">
						<table border="1" class="border-table">
						 	  <tr>
						 	     	<td align="center"  width="100px">支付订单号</td>
<!-- 						 	     	<td align="center"  width="100px">渠道号</td> -->
									<td align="center"  width="120px">支付渠道</td>
									<td  align="center" width="100px">支付金额</td>
									<td  align="center" width="150px">支付时间</td>
									<td  align="center" width="150px">统一支付流水号</td>
									<td  align="center" width="150px">支付状态</td>
									<td  align="center" width="150px">卡劵凭证号</td>

							</tr>

								<c:if test="${empty order.orderPayItem}">
									<tr>
										<td colspan="7" align="center" ><font color="red">暂无该订单的支付明细</font></td>
									</tr>
									</c:if>
									<c:forEach   items="${order.orderPayItem}"  var="orderPayItem">
										<tr>
											 <td align="center">${orderPayItem.payId}</td>
											  <td align="center">${orderPayItem.payChannelName}</td>
<%-- 											  <td align="center">${orderPayItem.productTypeName}</td> --%>
											  <td align="center">${orderPayItem.normalAmount}</td>
											  <td align="center">${orderPayItem.payTimeShow}</td>
											  <td align="center">${orderPayItem.transactionId}</td>
											   <c:if test="${orderPayItem.payStatus == '0'}">
											  	 <td align="center">未支付</td>
											  </c:if>
											   <c:if test="${orderPayItem.payStatus == '1'}">
											  	 <td align="center">支付成功</td>
											  </c:if>
											  <td align="center">${orderPayItem.cardNo}</td>
										</tr>
									</c:forEach>


						</table>
				</c:if>
			</fieldset>
			</div>

			<!-- <div align="center"><input type="button"  value="返回" onclick="backOrder();"/></div> -->
			<div class="item item_row button">

            <a class="easyui-linkbutton" onclick="Cancel()">取消</a>
        </div>

	</form>

<%--
		<h4>订单信息</h4>
        <div class="item">
       		 <label>订单编号：</label><easyui_ext:textBox id="orderNo" value="${order.orderNo}"  readonly="true" />
       			<label>订单类型：</label><easyui_ext:textBox id="orderType" value="${order.orderType}"  readonly="true" />
       			<label>原订单编号：</label><easyui_ext:textBox id="originalOrderNo" value="${order.originalOrderNo}"  readonly="true"/>
       			<label>订单金额(：</label><easyui_ext:textBox id="amount" value="${order.amount}"  readonly="true"/>
        </div>
        <div class="item"><easyui_ext:textBox id="payChanenlId" value="${order.payChanenlId}"  readonly="true"/>
       		<label>付款金额：</label><easyui_ext:textBox id="finalAmount" value="${order.finalAmount}"  readonly="true"/>
       			<label>实际支付金额：</label><easyui_ext:textBox id="realPayAmount" value="${order.realPayAmount}"  readonly="true"/>
       			<label>是否混合支付：</label><easyui_ext:textBox id="isMultPay" value="${order.isMultPay}"  readonly="true"/>
       			<label>分销商结算金额：</label><easyui_ext:textBox id="disSettleAmount" value="${order.disSettleAmount}"  readonly="true"/>
        </div>
         <div class="item">
       			<label>活动ID：</label><easyui_ext:textBox id="activityId" value="${order.activityId}"  readonly="true"/>
       			<label>下单时间：</label><easyui_ext:textBox id="orderTime" value="${order.orderTimeShow}"  readonly="true"/>
       			<label>出票渠道：</label><easyui_ext:textBox id="channleId" value="${order.channleId}"  readonly="true"/>
       			<label>下单用户编号：</label><easyui_ext:textBox id="userName" value="${order.userName}"  readonly="true"/>
        </div>
        <div class="item">
        		<label>接收凭证的手机号：</label><easyui_ext:textBox id="mobile" value="${order.mobile}"  readonly="true"/>
        	    <label>状态：</label><easyui_ext:textBox id="status" value="${order.status}"  readonly="true"/>
        	 	<label>渠道订单号：</label><easyui_ext:textBox id="channelOrderNo" value="${order.channelOrderNo}"  readonly="true"/>
        </div>--%>
      <%--   <hr/>
        <h4>订单明细</h4>
         <div class="item">
        		<label>影院编号：</label><easyui_ext:textBox id="cinemano" value="${order.cinemano}"  readonly="true"/>
        	    <label>商品编号：</label><easyui_ext:textBox id="goodsid" value="${order.goodsid}"  readonly="true"/>
        	 	<label>商品自定义属性：</label><easyui_ext:textBox id="customattr" value="${order.customattr}"  readonly="true"/>
        </div>
         <div class="item">
        		<label>有效期：</label><easyui_ext:textBox id="validDate" value="${order.validDateShow}"  readonly="true"/>
        		<label>兑换类型 : </label><easyui_ext:textBox id="showTypepriceType" value="${order.priceType}"  readonly="true"/>
        	 	<label>销售单价：</label><easyui_ext:textBox id="price" value="${order.price}"  readonly="true"/>
        </div>
         <div class="item">
        		<label>实付单价：</label><easyui_ext:textBox id="realPayPrice" value="${order.realPayPrice}"  readonly="true"/>
        	    <label>分销商结算单价：</label><easyui_ext:textBox id="sattlePrice" value="${order.sattlePrice}"  readonly="true"/>
        	 	<label>数量：</label><easyui_ext:textBox id="quantity" value="${order.quantity}"  readonly="true"/>
        </div> --%>
     <%--      <hr/>
        <h4>支付明细</h4>
         <div class="item">
        		<label>支付渠道：</label><easyui_ext:textBox id="payChanenlId" value="${order.payChanenlId}"  readonly="true"/>
        	    <label>支付金额：</label><easyui_ext:textBox id="amount1" value="${order.amount1}"  readonly="true"/>
        	 	<label>支付时间：</label><easyui_ext:textBox id="payTime" value="${order.payTimeShow}"  readonly="true"/>
        </div> --%>

</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
	function backOrder(){
		window.history.go(-1);
	}
	function numFormat(value){
    	if(value == 0 ||value == null){
    		return "";
    	}else{
    		return value;
    	}
    }
	function Cancel(){
		parent.datalist._temp.layout_box1.window("close");
	}


	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>