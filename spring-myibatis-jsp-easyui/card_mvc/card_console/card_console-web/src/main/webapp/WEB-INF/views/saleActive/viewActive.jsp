<%@taglib prefix="easyui" uri="org.topteam/easyui"%>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<easyui:templateOverride name="title">查看活动</easyui:templateOverride>
<easyui:templateOverride name="head">
<script src="<%=request.getContextPath()%>/resources/scripts/common/area.js"></script>
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
	<form id="info" class="edit_box" method="post" action="">
		<fieldset class="fieldset">
			<legend>活动基本信息</legend>
			<div class="item">
				<label>活动名称：</label>${active.actName}
			</div>
			<div class="item">
				<label>活动类型：</label>
				<c:if test="${active.actType==1}">
					价格活动
				</c:if>
				<c:if test="${active.actType==2}">
					折扣活动
				</c:if>
				<c:if test="${active.actType==3}">
					优惠支付
				</c:if>
				<c:if test="${active.actType==4}">
					下单立减
				</c:if>
				<c:if test="${active.actType==5}">
					预售活动
				</c:if>
			</div>
			<div class="item">
				<label>活动主题：</label>${active.actTheme}
			</div>
			<div class="item">
				<c:if test="${active.actType==1 || active.actType==5}">
					<label>活动价格：</label>${activeGoods[0].actPriceStr}
				</c:if>
				<c:if test="${active.actType==2}">
					<label>活动折扣：</label>${activeGoods[0].actPriceStr}
				</c:if>
				<c:if test="${active.actType==3}">
					<label>活动价格：</label>${activeGoods[0].actPriceStr}
				</c:if>
				<c:if test="${active.actType==4}">
					<label>减免金额：</label>${activeGoods[0].actPriceStr}
				</c:if>
			</div>
			<div class="item">
				<label>参考价格：</label>${activeGoods[0].actNormalPriceStr}
			</div>
			<div class="item">
				<label>活动补贴金额：</label>${active.actSubsidy}
			</div>
			<div class="item">
				<label>销售服务人员：</label>${active.actSalesman}
			</div>
			<div class="item">
				<label>活动联合举办单位：</label>${active.actCompany}
			</div>
			<div class="item item_row" style="clear:both">
				<label>活动简介：</label>${active.actSummary}
			</div>
		</fieldset>
		<fieldset class="fieldset">
			<legend>参加活动条件</legend>
			<fieldset class="fieldset">
				<legend>有效时间</legend>
				<div class="items">
					<label>日期：</label>
					<input id="beginDate" readonly="readonly" name="beginDate" type="text" class="easyui-datebox"> -
					<input id="endDate"  readonly="readonly" name="endDate" type="text" class="easyui-datebox">
				</div>
				<div class="items">
					<label>星期：</label>
					<label><input name="weeks" value="1" disabled="disabled" type="checkbox" />周一</label>
					<label><input name="weeks" value="2" disabled="disabled" type="checkbox" />周二</label>
					<label><input name="weeks" value="3" disabled="disabled" type="checkbox" />周三</label>
					<label><input name="weeks" value="4" disabled="disabled" type="checkbox" />周四</label>
					<label><input name="weeks" value="5" disabled="disabled" type="checkbox" />周五</label>
					<label><input name="weeks" value="6" disabled="disabled" type="checkbox" />周六</label>
					<label><input name="weeks" value="7" disabled="disabled" type="checkbox" />周日</label>
				</div>
				<div class="items">
					<label>时间段：</label>
					<span id="dateTime">${activeDateTime.startTime}~${activeDateTime.stopTime}</span>
				</div>
			</fieldset>
			<fieldset id="fd_film" class="fieldset">
				<legend>影片：</legend>
				<div class="items" id="film_div"></div>
			</fieldset>
			<fieldset class="fieldset">
				<legend>商品：</legend>
				<div class="items">
					<label>商品类型：</label>
					<label><input type="radio" value="0" disabled="disabled" name="productType" checked="checked"/>选座票</label>
					<label><input type="radio" value="1" disabled="disabled" name="productType" />通兑票</label>
				</div>
				<div class="items" id="cinema_area">
					<label>适用影院区域：</label>
			        <div id="areaSelectResult" style="overflow: auto;height:100px;">
			        	<c:forEach items="${activePlace}" var="place">
			        		<div hallCode="${place.hallno}" cinemaCode="${place.cinemano}" countyCode="${place.areaNo}"
			        			cinemaName="${place.cinemaName}" >
			        			<c:if test="${place.hallName==''||null==place.hallName}">
			        				${place.cinemaName}
			        			</c:if>
			        			<c:if test="${place.hallName!=''&&null!=place.hallName}">
			        				${place.cinemaName}-${place.hallName}
			        			</c:if>
			        		</div>
			        	</c:forEach>
					</div>
				</div>
				<div class="items" id="voucher_area" style="display: none">
					<label>匹配通兑票：</label>
					<input type="hidden" id="goodsId" value="${activeGoods[0].goodsId}"/>
					<span id="goodsName">${activeGoods[0].goodsName}</span>
				</div>
			</fieldset>
			<fieldset class="fieldset">
				<legend>参与渠道：</legend>
				<div class="items">
					<label>参与渠道：</label>
					<div id="channelList">
						<c:forEach items="${baseChannel}" var="channel">
							<label><input type="checkbox" name="baseChannel" readonly="readonly" disabled="disabled" value="${channel.channelno}" />${channel.channelname}</label>
						</c:forEach>
					</div>
				</div>
			</fieldset>
		</fieldset>
		<fieldset class="fieldset">
			<legend>支持的支付</legend>
			<div class="items">
				<label>支付方式：</label>
				<div id="payChannelList">
					<c:forEach items="${payChannel}" var="channel">
						<label><input type="checkbox" name="payChannel" readonly="readonly" disabled="disabled" value="${channel.payChannelId}" />${channel.payChannelName}</label>
					</c:forEach>
				</div>
			</div>
		</fieldset>
	</form>
	<div id="layout_box" style="overflow:hidden" data-options="width: 1000,height: 450,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false"><iframe frameborder="0" width="100%" height="100%"></iframe></div>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
	var iframe = {
			Init: function () {
	            var _this = iframe;
	
	            _this._temp.list_box = $("#datalist");
	            _this._temp.layout_box = $("#layout_box");
	        },
	        _temp: {
	        	list_box: null,
	        	layout_box: null
	        }
	}
	
	$(function () {
		iframe.Init();
		init();
		Area.Init("provinceNo", "cityno", "areaNo");

		$('input[name="productType"]').change(function(){
			if('0'==$(this).val()){
				$('#cinema_area').show();
				$('#voucher_area').hide();
			}else{
				$('#cinema_area').hide();
				$('#voucher_area').show();
			}
		});
	});

		function init(){
			$("#beginDate").datebox("setValue","${activeDateTime.beginDate}");
			$("#endDate").datebox("setValue","${activeDateTime.endDate}");
			var weeks = "${activeDateTime.weeks}";
			if(weeks){
				var week = weeks.split(',');
				$('input[name="weeks"]').each(function(){
					if(-1 != $.inArray($(this).val(), week))
						$(this).attr("checked","checked");
				});
			}
			var startTime = "${activeDateTime.startTime}";
			var stopTime = "${activeDateTime.stopTime}";
			if (startTime && stopTime) {
				var shour = startTime.split(':')[0];
				var smin = startTime.split(':')[1];
				var ehour = stopTime.split(':')[0];
				var emin = stopTime.split(':')[1];
				$('#sHour').find('option').each(function(){
					if(shour == $(this).val())
						$(this).attr("selected","selected");
				});
				$('#sMin').find('option').each(function(){
					if(smin == $(this).val())
						$(this).attr("selected","selected");
				});
				$('#eHour').find('option').each(function(){
					if(ehour == $(this).val())
						$(this).attr("selected","selected");
				});
				$('#eMin').find('option').each(function(){
					if(emin == $(this).val())
						$(this).attr("selected","selected");
				});
			}

			var baseChannel = "${chekcedChannels}";
			if(baseChannel){
				var chekcedChannels = baseChannel.split(',');
				$('input[name="baseChannel"]').each(function(){
					if(-1 != $.inArray($(this).val(), chekcedChannels))
						$(this).attr("checked","checked");
				});
			}

			var payChannel = "${chekcedPayChannels}";
			if(payChannel){
				var chekcedChannels = payChannel.split(',');
				$('input[name="payChannel"]').each(function(){
					if(-1 != $.inArray($(this).val(), chekcedChannels))
						$(this).attr("checked","checked");
				});
			}

			var goodsType = '${activeGoods[0].goodsType}';
			if('1'==goodsType){
				$('#cinema_area').hide();
				$('#fd_film').hide();
				$('#voucher_area').show();
				$('input[name="productType"][value="1"]').attr("checked", "checked");
			}else{
				$('#cinema_area').show();
				$('#fd_film').show();
				$('#voucher_area').hide();
				$('input[name="productType"][value="0"]').attr("checked", "checked");
				
				if($("#film_div").html() ==""){
					var str = '${filmSelStr}';
			     	var html = "<input type='hidden' id='filmSelStr' value='"+ str +"' />已选择影片<span id='selCount'>"+${checkedSAGCount}+"</span>部&nbsp;&nbsp;&nbsp;&nbsp;" +
					"<a href='javascript:void(0);' onclick='showFilmDisplay()' >详情</a>";
			
			    	$("#film_div").html(html);
				}
			}

		}
		
		function showFilmDisplay(){	
			$('#layout_box').find("iframe").attr("src", "../saleActive/filmDisplay.html?isView=1&filmSelStr="+ $('#filmSelStr').val());
			$('#layout_box').window({
	            title: "选择影片"
	        });
		}

	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp"%>