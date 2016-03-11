<%@taglib prefix="easyui" uri="org.topteam/easyui"%>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<easyui:templateOverride name="title">编辑活动</easyui:templateOverride>
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
			<input type="hidden" id="activeId" value="${activeId}" />
			<div class="item">
				<label>活动名称<span class="star">*</span>：</label>
				<easyui_ext:textBox id="actName" required="true" value="${active.actName}"/>
			</div>
			<div class="item">
				<label>活动类型<span class="star">*</span>：</label>
				<easyui_ext:comboBox id="actType" required="true"  name="actType" data="${activeTypeList}" value="${active.actType}"/>
			</div>
			<div class="item">
				<label>活动主题<span class="star">*</span>：</label>
				<easyui_ext:textBox id="actTheme" required="true" value="${active.actTheme}"/>
			</div>
			<div class="item">
				<label id="actPrice_tip">活动价格<span class="star">*</span>：</label>
				<easyui_ext:textBox id="actPrice" required="true" validType="currency" value="${activeGoods[0].actPriceStr}"/>
			</div>
			<div class="item">
				<label>参考价格<span class="star">*</span>：</label>
				<easyui_ext:textBox id="actNormalPrice" required="true" validType="currency" value="${activeGoods[0].actNormalPriceStr}" />
			</div>
			<div class="item">
				<label>活动补贴金额：</label>
				<easyui_ext:textBox id="actSubsidy" validType="currency" value="${active.actSubsidy}"/>
			</div>
			<div class="item">
				<label>销售服务人员：</label>
				<easyui_ext:textBox id="actSalesman" value="${active.actSalesman}" />
			</div>
			<div class="item">
				<label>活动联合举办单位：</label>
				<easyui_ext:textBox id="actCompany" value="${active.actCompany}" />
			</div>
			<br>
			<div class="item item_row" style="clear:both">
				<label>活动简介<span class="star">*</span>：</label>
				<easyui_ext:textBox multiline="true" height="100" width="400"  id="actSummary" required="true" validType="maxLength[140]" value="${active.actSummary}" />
			</div>
		</fieldset>
		<fieldset class="fieldset">
			<legend>参加活动条件</legend>
			<fieldset class="fieldset">
				<legend>有效时间</legend>
				<div class="items">
					<label>选择日期<span class="star" style="color:#c00;">*</span>：</label>
					<input id="beginDate" name="beginDate" type="text" class="easyui-datebox easyui-validatebox" required="required" validType="dateCompare['#beginDate','#endDate']"> -
					<input id="endDate" name="endDate" type="text" class="easyui-datebox easyui-validatebox" required="required" validType="dateCompare['#beginDate','#endDate']">
				</div>
				<div class="items">
					<label>选择星期（不选则不限制星期！）：</label>
					<label><input name="weeks" value="1" type="checkbox" />周一</label>
					<label><input name="weeks" value="2" type="checkbox" />周二</label>
					<label><input name="weeks" value="3" type="checkbox" />周三</label>
					<label><input name="weeks" value="4" type="checkbox" />周四</label>
					<label><input name="weeks" value="5" type="checkbox" />周五</label>
					<label><input name="weeks" value="6" type="checkbox" />周六</label>
					<label><input name="weeks" value="7" type="checkbox" />周日</label>
				</div>
				<div class="items">
					<label>选择时间段：
					</label> <select id="sHour" class="hour tselece">
						<option value="00">00</option>
						<option value="01">01</option>
						<option value="02">02</option>
						<option value="03">03</option>
						<option value="04">04</option>
						<option value="05">05</option>
						<option value="06">06</option>
						<option value="07">07</option>
						<option value="08">08</option>
						<option value="09">09</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
						<option value="13">13</option>
						<option value="14">14</option>
						<option value="15">15</option>
						<option value="16">16</option>
						<option value="17">17</option>
						<option value="18">18</option>
						<option value="19">19</option>
						<option value="20">20</option>
						<option value="21">21</option>
						<option value="22">22</option>
						<option value="23">23</option>
					</select> : <select id="sMin" class="min tselece">
						<option value="00">00</option>
						<option value="01">01</option>
						<option value="02">02</option>
						<option value="03">03</option>
						<option value="04">04</option>
						<option value="05">05</option>
						<option value="06">06</option>
						<option value="07">07</option>
						<option value="08">08</option>
						<option value="09">09</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
						<option value="13">13</option>
						<option value="14">14</option>
						<option value="15">15</option>
						<option value="16">16</option>
						<option value="17">17</option>
						<option value="18">18</option>
						<option value="19">19</option>
						<option value="20">20</option>
						<option value="21">21</option>
						<option value="22">22</option>
						<option value="23">23</option>
						<option value="24">24</option>
						<option value="25">25</option>
						<option value="26">26</option>
						<option value="27">27</option>
						<option value="28">28</option>
						<option value="29">29</option>
						<option value="30">30</option>
						<option value="31">31</option>
						<option value="32">32</option>
						<option value="33">33</option>
						<option value="34">34</option>
						<option value="35">35</option>
						<option value="36">36</option>
						<option value="37">37</option>
						<option value="38">38</option>
						<option value="39">39</option>
						<option value="40">40</option>
						<option value="41">41</option>
						<option value="42">42</option>
						<option value="43">43</option>
						<option value="44">44</option>
						<option value="45">45</option>
						<option value="46">46</option>
						<option value="47">47</option>
						<option value="48">48</option>
						<option value="49">49</option>
						<option value="50">50</option>
						<option value="51">51</option>
						<option value="52">52</option>
						<option value="53">53</option>
						<option value="54">54</option>
						<option value="55">55</option>
						<option value="56">56</option>
						<option value="57">57</option>
						<option value="58">58</option>
						<option value="59">59</option></select>
						-
						<select id="eHour" class="hour tselece">
						<option value="00">00</option>
						<option value="01">01</option>
						<option value="02">02</option>
						<option value="03">03</option>
						<option value="04">04</option>
						<option value="05">05</option>
						<option value="06">06</option>
						<option value="07">07</option>
						<option value="08">08</option>
						<option value="09">09</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
						<option value="13">13</option>
						<option value="14">14</option>
						<option value="15">15</option>
						<option value="16">16</option>
						<option value="17">17</option>
						<option value="18">18</option>
						<option value="19">19</option>
						<option value="20">20</option>
						<option value="21">21</option>
						<option value="22">22</option>
						<option value="23">23</option>
					</select> :
						<select id="eMin" class="min tselece">
						<option value="00">00</option>
						<option value="01">01</option>
						<option value="02">02</option>
						<option value="03">03</option>
						<option value="04">04</option>
						<option value="05">05</option>
						<option value="06">06</option>
						<option value="07">07</option>
						<option value="08">08</option>
						<option value="09">09</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
						<option value="13">13</option>
						<option value="14">14</option>
						<option value="15">15</option>
						<option value="16">16</option>
						<option value="17">17</option>
						<option value="18">18</option>
						<option value="19">19</option>
						<option value="20">20</option>
						<option value="21">21</option>
						<option value="22">22</option>
						<option value="23">23</option>
						<option value="24">24</option>
						<option value="25">25</option>
						<option value="26">26</option>
						<option value="27">27</option>
						<option value="28">28</option>
						<option value="29">29</option>
						<option value="30">30</option>
						<option value="31">31</option>
						<option value="32">32</option>
						<option value="33">33</option>
						<option value="34">34</option>
						<option value="35">35</option>
						<option value="36">36</option>
						<option value="37">37</option>
						<option value="38">38</option>
						<option value="39">39</option>
						<option value="40">40</option>
						<option value="41">41</option>
						<option value="42">42</option>
						<option value="43">43</option>
						<option value="44">44</option>
						<option value="45">45</option>
						<option value="46">46</option>
						<option value="47">47</option>
						<option value="48">48</option>
						<option value="49">49</option>
						<option value="50">50</option>
						<option value="51">51</option>
						<option value="52">52</option>
						<option value="53">53</option>
						<option value="54">54</option>
						<option value="55">55</option>
						<option value="56">56</option>
						<option value="57">57</option>
						<option value="58">58</option>
						<option value="59">59</option></select>
				</div>
			</fieldset>
			<!-- ------------------------------------------------------------选择商品 Start----------------------------------------------------------------------------- -->
			<fieldset class="fieldset">
				<legend>商品条件选择：</legend>
				<div class="items">
					<label>商品类型：</label>
					<label><input type="radio" value="0" name="productType" checked="checked"/>选座票</label>
					<label><input type="radio" value="1" name="productType" />通兑票</label>
				</div>
				<div class="items" id="cinema_area">
					<label>适用影院区域：</label>
			        <input id="areaSelect" name="areaSelect" value="选择" type="button" onclick="showAreaSelect()" />
			        <div id="areaSelectResult" style="overflow: auto;height:100px;">
			        	<c:forEach items="${activePlace}" var="place">
			        		<div hallCode="${place.hallno}" cinemaCode="${place.cinemano}" countyCode="${place.areaNo}"
			        			cinemaName="${place.cinemaName}" hallName="${place.hallName}">
			        			<c:if test="${place.hallName==''||null==place.hallName}">
			        				${place.cinemaName}
			        			</c:if>
			        			<c:if test="${place.hallName!=''&&null!=place.hallName}">
			        				${place.cinemaName}-${place.hallName}
			        			</c:if>
			        			&nbsp;&nbsp;&nbsp;&nbsp;
			        			<a href="javascript:void(0);" onclick="$(this, parent.document).parent().remove();" >删除</a>
			        		</div>
			        	</c:forEach>
					</div>
				</div>
				<div class="items" id="voucher_area" style="display: none">
					<label>匹配通兑票：</label>
					<div id="voucher_div" style="display: inline">
						<input type="hidden" id="goodsId" value="${activeGoods[0].goodsId}"/>
						<span id="goodsName">${activeGoods[0].goodsName}</span>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:void(0);" onclick="$(this, parent.document).parent().html('');" >删除</a>
					</div>
			        <input id="voucherSelect" name="voucherSelect" value="选择" type="button" onclick="showVoucherSelect()" />
				</div>
			</fieldset>
			<!-- ------------------------------------------------------------选择影片 Start----------------------------------------------------------------------------- -->
			<fieldset id="fd_film" class="fieldset">
				<legend class="fieldset">影片选择：</legend>
				<div class="items" id="film">
					<label>活动影片：</label>
					<div id="film_div" style="display: inline"></div>
			        <input id="filmSelect" name="areaSelect" value="选择" type="button" onclick="showFilmSelect()" />
				</div>
			</fieldset>
			<!-- ------------------------------------------------------------选择销售渠道 start----------------------------------------------------------------------------- -->
			<fieldset class="fieldset">
				<legend>参与渠道选择：</legend>
				<div class="items">
					<label>参与渠道<span class="star" style="color:#c00;">*</span>：</label>
					<div id="channelList">
						<c:forEach items="${baseChannel}" var="channel">
							<label><input type="checkbox" name="baseChannel" value="${channel.channelno}" />${channel.channelname}</label>
						</c:forEach>
					</div>
				</div>
			</fieldset>
		</fieldset>
		<!-- ------------------------------------------------------------选择支付渠道 Start----------------------------------------------------------------------------- -->
		<fieldset class="fieldset">
			<legend>支持的支付方式</legend>
			<div class="items">
				<label>支付方式<span class="star" style="color:#c00;">*</span>：</label>
				<div id="payChannelList">
					<c:forEach items="${payChannel}" var="channel">
						<label><input type="checkbox" name="payChannel" value="${channel.payChannelId}" />${channel.payChannelName}</label>
					</c:forEach>
				</div>
			</div>
		</fieldset>
		<div class="item item_row button">
			<a class="easyui-linkbutton" onclick="Save()">保存</a> <a
				class="easyui-linkbutton" onclick="Cancel()">取消</a>
		</div>
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
	
	$('#actType').combobox({
		onSelect: function(param){
			var actType = $("#actType").combobox("getValue");
			if ('1' == actType) {
				$('#actPrice_tip').html("活动价格：");
			} else if ('2' == actType) {
				$('#actPrice_tip').html("活动折扣：");
			} else if ('3' == actType) {
				$('#actPrice_tip').html("活动价格：");
			} else if ('4' == actType) {
				$('#actPrice_tip').html("减免金额：");
			}
		}
	});
	$(function () {
		iframe.Init();
		
		$.extend($.fn.validatebox.defaults.rules,{
			currency : {// 验证货币
		        validator : function(value) {
		            return /^\d+(\.\d+)?$/i.test(value);
		        },
		        message : '货币格式不正确'
		    },
		    date : {
		        validator : function(value) {
		         //格式yyyy-MM-dd或yyyy-M-d
		            return /^(?:(?!0000)[0-9]{4}([-]?)(?:(?:0?[1-9]|1[0-2])\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\1(?:29|30)|(?:0?[13578]|1[02])\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-]?)0?2\2(?:29))$/i.test(value);
		        },
		        message : '日期格式错误'
		    },
		    dateCompare : {// 日期比较
		    	validator : function(value, param){
		    		var d1 = $(param[0]).datebox('getValue');
		    		var d2 = $(param[1]).datebox('getValue');
		    		if (''==d1 || ''==d2 ) {
		    			return true;
		    		}
		    		$(param[0]).val(d1);
		    		$(param[1]).val(d2);
		    		d1 = $.fn.datebox.defaults.parser(d1);
		    		d2 = $.fn.datebox.defaults.parser(d2);
		    		return d2 >= d1;
		    	},
		    	message : '起始日期必须小于等于结束日期'
		    },
		    maxLength: {
                validator: function(value, param){
                    return param[0] >= value.length;
                },
                message: '限定输入最大{0}位字符.'
            }
		});
		init();
		Area.Init("provinceNo", "cityno", "areaNo");

		$('input[name="productType"]').change(function(){	
			if('0'==$(this).val()){
				$('#cinema_area').show();
				$('#fd_film').show();
				$('#voucher_area').hide();
				$('#voucher_div').html("");
			}else{
				$('#cinema_area').hide();
				$('#fd_film').hide();
				$('#voucher_area').show();
				$('#areaSelectResult').html("");
				$('#film_div').html("");
			}
		});
		
	});

	
	function showFilmDisplay(){	
		$('#layout_box').find("iframe").attr("src", "../saleActive/filmDisplay.html?isView=0&filmSelStr="+ $('#filmSelStr').val());
		$('#layout_box').window({
            title: "选择影片"
        });
	}
	
	function showAreaSelect(){
		$('#layout_box').find("iframe").attr("src", "areaSelect.do");
		$('#layout_box').window({
            title: "选择区域"
        });
	}

	function showVoucherSelect(){
		$('#layout_box').find("iframe").attr("src", "voucherSelect.do");
		$('#layout_box').window({
            title: "选择通兑票"
        });
	}

	function showFilmSelect(){
		$('#layout_box').find("iframe").attr("src", "filmSelect.do");
		$('#layout_box').window({
            title: "选择影片"
        });
	}
		function Save() {
			var flag = $('#info').form('validate');
			if(!flag){
			    return;
			}
			if(''==$("#actType").combobox("getValue")){
				alert('请选择活动类型。');
				return;
			}
			
			if(0==$('input[name="productType"]:checked').val()){
				if(''==$("#filmSelStr").val() || undefined==$("#filmSelStr").val() ){
					alert('请选择参与活动影片。');
					return;
				}
			}else if(1==$('input[name="productType"]:checked').val()){
				if(''==$("#goodsId").val() || undefined==$("#goodsId").val() ){
					alert('请选择参与活动商品。');
					return;
				}
			}
			if(0==$('input[name="baseChannel"]:checked').length){
				alert('请选择参与渠道。');
				return;
			}
			if(0==$('input[name="payChannel"]:checked').length){
				alert('请选择支付方式。');
				return;
			}
			var data = {
				actId : $("#activeId").val(),
				actName : $("#actName").textbox("getValue"),
				actType : $("#actType").combobox("getValue"),
				actTheme : $("#actTheme").textbox("getValue"),
				//actMemo : $("#actMemo").textbox("getValue"),
				actSummary : $("#actSummary").textbox("getValue"),
				actSalesman : $("#actSalesman").textbox("getValue"),
				actCompany : $("#actCompany").textbox("getValue"),
				actSubsidy : $("#actSubsidy").textbox("getValue"),
				beginDate : $("#beginDate").datebox("getValue"),
				endDate : $("#endDate").datebox("getValue"),
				weeks : getWeeks(),
				baseChannel : getBaseChannel(),
				payChannel : getPayChannel(),
				shour : $("#sHour").val(),
				smin : $("#sMin").val(),
				ehour : $("#eHour").val(),
				emin : $("#eMin").val(),
				cinemaSelects : getCinemaSelect(),
				actPrice : $("#actPrice").textbox("getValue"),
				actNormalPrice : $("#actNormalPrice").textbox("getValue"),
				filmno : $('#filmSelStr').val(),
				goodsType : $('input[name="productType"]:checked').val(),
				goodsId : $('#goodsId').val(),
				goodsName : $('#goodsName').html(),
				filmName : "",
				filmShowType:""
			};
			
			$.ajaxRequest({
				url : "updateActive.do",
				para : JSON.stringify(data),
				dataType: "json",
				contentType: "application/json; charset=gbk",
				success : function(result) {
					if (result.isSuccess) {
						parent.Tips.Success(result.message);
						parent.refreshTab({ tabTitle: "活动列表管理"});
						parent.closeTab('编辑活动');
					} else {
						parent.Tips.Error(result.message);
					}
				}
			});
		}

		function getCinemaSelect(){
			var result = [];
			$('#areaSelectResult').find('div').each(function(){
				var cinemaSelect = new Object();
				cinemaSelect.cinemaCode = $(this).attr('cinemaCode');
				cinemaSelect.provinceCode = $(this).attr('provinceCode');
				cinemaSelect.cityCode = $(this).attr('cityCode');
				cinemaSelect.countyCode = $(this).attr('countyCode');
				cinemaSelect.hallCode = $(this).attr('hallCode');
				cinemaSelect.hallName = $(this).attr('hallName');
				cinemaSelect.cinemaName = $(this).attr('cinemaName');
				result.push(cinemaSelect);
			});
			return result;
		}

		function getWeeks(){
		    var week = "";
		    $('input[name="weeks"]:checked').each(function(){ //遍历table里的全部checkbox
	        	week += $(this).val() + ","; //获取被选中的值
		    });
		    if(week.length > 0) //如果获取到
		    	week = week.substring(0, week.length - 1); //把最后一个逗号去掉
		    return week;
		}

		function getShowTypes(){
		    var showtype = "";
		    $('input[name^="filmshowtype"]:checked').each(function(){ //遍历table里的全部checkbox
		    	showtype += ($(this).val()).replace("ck","") + ","; //获取被选中的值,replace是因为选中的时候赋值了ck
		    });
		    if(showtype.length > 0) //如果获取到
		    	showtype = showtype.substring(0, showtype.length - 1); //把最后一个逗号去掉
		    return showtype;
		}

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
				$('#voucher_div').html("");
				$('input[name="productType"][value="0"]').attr("checked", "checked");
				
				if($("#film_div").html() ==""){
					var str = '${filmSelStr}';     
			     	var html = "<input type='hidden' id='filmSelStr' value='"+ str +"' />已选择影片<span id='selCount'>"+${checkedSAGCount}+"</span>部&nbsp;&nbsp;&nbsp;&nbsp;" +
					"<a href='javascript:void(0);' onclick='showFilmDisplay()' >详情</a>";
			
			    	$("#film_div").html(html);
				}
			}

// 			var shwoTypes =  "${activeGoods[0].filmShowType}";
// 			if(shwoTypes){
// 				var showtype = shwoTypes.split(',');
// 				$('input[name^="filmshowtype"]').each(function(){
// 					if(-1 != $.inArray($(this).val(), showtype))
// 						$(this).attr("checked","checked");
// 				});
// 			}
		}

		function getPayChannel(){
			var channel = "";
		    $('input[name="payChannel"]:checked').each(function(){ //遍历table里的全部checkbox
		    	channel += $(this).val() + ","; //获取被选中的值
		    });
		    if(channel.length > 0) //如果获取到
		    	channel = channel.substring(0, channel.length - 1); //把最后一个逗号去掉
		    return channel;
		}

		function getBaseChannel(){
			var channel = "";
		    $('input[name="baseChannel"]:checked').each(function(){ //遍历table里的全部checkbox
		    	channel += $(this).val() + ","; //获取被选中的值
		    });
		    if(channel.length > 0) //如果获取到
		    	channel = channel.substring(0, channel.length - 1); //把最后一个逗号去掉
		    return channel;
		}

		function Cancel() {
			$.messager.confirm('确认','确认要关闭？',function(r){
				if(r){
					parent.closeTab('编辑活动');
				}
			});
		}
		
		function changeStyle(obj){  
			var value = $("#"+obj.id).val();
			 if(value.indexOf("ck") > -1){   
					$("#"+obj.id).val(value.replace("ck",""));
				   	$("#"+obj.id).attr("checked",false);
			  } else {  
					$("#"+obj.id).val(value + "ck");
					$("#"+obj.id).attr("checked","checked");
			 }  
		} 
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp"%>