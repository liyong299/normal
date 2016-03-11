<%@taglib prefix="e" uri="org.topteam/easyui" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<e:templateOverride name="title">欢迎</e:templateOverride>

<e:templateOverride name="head">
	<style>
	    * { font-family: "Microsoft YaHei", sans-serif; }
		body { margin: 0; padding: 0; font-size: 12px;}
		.top { height:84px; background-image: url(../resources/images/top_bg.jpg); position: relative; color: #fff; font-size: 14px; }
		.top-user { position: absolute; top:0px; right:0px; padding-top: 30px; padding-right: 25px; color: #fff }
		.top-user span { color: #fff; font-weight: bold; }
		.top-user a { color: #fff; text-decoration: none; }
		.top-user a:hover { text-decoration: underline; }
		
		.top-menu { position: absolute; list-style: none; overflow: hidden; margin:0; padding:0 1px 0 60px; bottom:0; }
		.top-menu li { display: inline; margin:0; padding:0; }
		.top-menu li a { padding: 6px 20px; margin-right: 5px; border-top-left-radius: 6px; border-top-right-radius: 6px; color: #fff; font-size: 15px; text-decoration: none; float: left; display: block; margin-left: -1px; position: relative; left: 1px; }
		.top-menu li a:hover { color: #777; background: #F2F2F2; }
		.top-menu li.selected a { 
		    background-color: #F2F2F2;
		    background: -webkit-linear-gradient(top,#ffffff 0,#F2F2F2 100%);
		    background: -moz-linear-gradient(top,#ffffff 0,#F2F2F2 100%);
		    background: -o-linear-gradient(top,#ffffff 0,#F2F2F2 100%);
		    background: linear-gradient(to bottom,#ffffff 0,#F2F2F2 100%);
		    background-repeat: repeat-x;
		    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#ffffff,endColorstr=#F2F2F2,GradientType=0);
		    color: #777; 
		    font-weight: bold;
		}
		.top-line { height: 4px; background: #F2F2F2;}
		/* Basic Grey */
		.basic-grey {
		    padding: 5px 15px 5px 20px;
		    color: #888;
		    text-shadow: 1px 1px 1px #FFF;
		}
		.basic-grey label {
		    display: block;
		    margin: 0px;
		    padding: 3px 0;
		    color: #777;
		}
		.basic-grey span {
		    padding-left: 5px;
		    color: #888;
		}
    </style>
</e:templateOverride>

<e:templateOverride name="body">
    <e:layout id="menu" fit="true">
	    <e:layoutUnit region="north" border="false" style="height:88px">
	         <div class="top">
	            <div class="top-user">
	                欢迎 <span>${fullName}</span>
	                | <a href="#" class="change-password">修改密码</a>
	                | <a href="login.html">退出</a>
	            </div>
	            <ul class="top-menu">
	                    <c:forEach items="${moduleList}" var="i">
							<li><a moduleid="${i.id }">${i.name }</a></li>
						</c:forEach>
	            </ul>
	        </div>
	        <div class="top-line"></div>
	    </e:layoutUnit>
	    <e:layoutUnit region="west" split="true" title="导航菜单" style="width: 220px;">
	    	<div id="left-tree"><!--div class="panel-loading">加载中...</div--></div>
	    </e:layoutUnit>
	    <e:layoutUnit region="center">
	    	<div id="tabs" class="easyui-tabs" data-options="fit:true,border:false">
	              <div title="我的工作台">
	                    <iframe id="desk" scrolling="auto" frameborder="0" style="width:100%;height:100%;margin-bottom:-4px;"></iframe>
	                </div>
	               <div title="导出任务">
	                    <iframe id="export" scrolling="auto" frameborder="0" style="width:100%;height:100%;margin-bottom:-4px;"></iframe>
	                </div>
	        </div>
	    </e:layoutUnit>
	    <e:layoutUnit region="south" collapsible="false" border="false" style="height:30px">
	    	<div style="text-align: center; line-height: 28px;">©版权所有 深圳市泰久信息系统有限公司</div>
	    </e:layoutUnit>
	    
	    <div id="tab-menu" class="easyui-menu" style="width: 150px; display: none;">
	        <div id="tab-menu-tabrefresh" data-options="iconCls:'icon-reload'">刷新</div>
	        <div id="tab-menu-openFrame">在新窗口打开</div>
	        <div id="tab-menu-tabcloseall">关闭所有</div>
	        <div id="tab-menu-tabcloseother">关闭其他标签页</div>
	        <div class="menu-sep"></div>
	        <div id="tab-menu-tabcloseright">关闭右边</div>
	        <div id="tab-menu-tabcloseleft">关闭左边</div>
	        <div id="tab-menu-tabclose">关闭</div>
	    </div>
	    <div id="change-password-win" class="easyui-window" title="修改密码" data-options="modal:true,closed:true,collapsible:false,minimizable:false,maximizable:false" style="width: 400px; height: 290px;">
	        <form class="basic-grey" action="../account/ChangePassword.do" method="post">
	            	<input name="userID" type="hidden" value="${userID}">
	            <p>
	                <label>原始密码：</label><input name="OldPassword" type="password" /><span>请输入原始密码。</span></p>
	            <p>
	                <label>新密码：</label><input name="NewPassword" type="password" /><span>请输入新密码。</span></p>
	            <p>
	                <label>确认新密码：</label><input name="ConfirmPassword" type="password" /><span>请重新输入新密码。</span></p>
	            <p>
	                <button type="submit" class="button">保存</button></p>
	        </form>
	    </div>
    </e:layout>
	
</e:templateOverride>

<e:templateOverride name="script">
    <script>
    
	    $('#tab-menu-tabrefresh').click(function () {
	        /*重新设置该标签 */
	        var url = $(".tabs-panels .panel").eq($('.tabs-selected').index()).find("iframe").attr("src");
	        $(".tabs-panels .panel").eq($('.tabs-selected').index()).find("iframe").attr("src", url);
	    });

	    //在新窗口打开该标签
	    $('#tab-menu-openFrame').click(function () {
	        var url = $(".tabs-panels .panel").eq($('.tabs-selected').index()).find("iframe").attr("src");
	        window.open(url);
	    });
	    
	    //关闭当前
	    $('#tab-menu-tabclose').click(function () {
	        var currtab_title = $('.tabs-selected .tabs-inner span').text();
	        $('#tabs').tabs('close', currtab_title);
	        if ($(".tabs li").length == 0) {
	            //open menu
	            $(".layout-button-right").trigger("click");
	        }
	    });

	    //全部关闭
	    $('#tab-menu-tabcloseall').click(function () {
	        $('.tabs-inner span').each(function (i, n) {
	            if ($(this).parent().next().is('.tabs-close')) {
	                var t = $(n).text();
	                $('#tabs').tabs('close', t);
	            }
	        });
	        //open menu
	        $(".layout-button-right").trigger("click");
	    });

	    //关闭除当前之外的TAB
	    $('#tab-menu-tabcloseother').click(function () {
	        var currtab_title = $('.tabs-selected .tabs-inner span').text();
	        $('.tabs-inner span').each(function (i, n) {
	            if ($(this).parent().next().is('.tabs-close')) {
	                var t = $(n).text();
	                if (t != currtab_title)
	                    $('#tabs').tabs('close', t);
	            }
	        });
	    });

	    //关闭当前右侧的TAB

	    $('#tab-menu-tabcloseright').click(function () {
	        var nextall = $('.tabs-selected').nextAll();
	        if (nextall.length == 0) {
	            $.messager.alert('提示', '前面没有了!', 'warning');
	            return false;
	        }
	        nextall.each(function (i, n) {
	            if ($('a.tabs-close', $(n)).length > 0) {
	                var t = $('a:eq(0) span', $(n)).text();
	                $('#tabs').tabs('close', t);
	            }
	        });
	        return false;
	    });

	    //关闭当前左侧的TAB
	    $('#tab-menu-tabcloseleft').click(function () {
	        var prevall = $('.tabs-selected').prevAll();
	        if (prevall.length == 0) {
	            $.messager.alert('提示', '后面没有了!', 'warning');
	            return false;
	        }
	        prevall.each(function (i, n) {
	            if ($('a.tabs-close', $(n)).length > 0) {
	                var t = $('a:eq(0) span', $(n)).text();
	                $('#tabs').tabs('close', t);
	            }
	        });
	        return false;
	    });

	    /*为选项卡绑定右键*/
	    $(".tabs li").live('contextmenu', function (e) {
	        /*选中当前触发事件的选项卡 */
	        var subtitle = $(this).text();
	        $('#tabs').tabs('select', subtitle);
	        //显示快捷菜单
	        $('#tab-menu').menu('show', {
	            left: e.pageX,
	            top: e.pageY
	        });
	        return false;
	    });

	    function openTab(title, href) {
	        if ($('#tabs').tabs('exists', title)) {
	            $('#tabs').tabs('select', title);
	        } else {
	            $('#tabs').tabs('add', {
	                title: title,
	                content: createFrame(href),
	                closable: true
	            });
	        }
	    }
	    
	    function closeTab(title){
	    	$('#tabs').tabs('close', title);
	    }
	    
	    /*打开已存在页面需要刷新时*/
	    function openTabrefresh(title, href, newTab) {
	        if ($('#tabs').tabs('exists', title) && !newTab) {
	            $('#tabs').tabs('select', title);
	            refreshTab({ tabTitle: title, url: href });
	        } else {
	            $('#tabs').tabs('add', {
	                title: title,
	                content: createFrame(href),
	                closable: true
	            });
	        }
	    }
	    /**     
	     * 刷新tab 
	     * @cfg  
	     *example: {tabTitle:'tabTitle',url:'refreshUrl'} 
	     *如果tabTitle为空，则默认刷新当前选中的tab 
	     *如果url为空，则默认以原来的url进行reload 
	     */
	    function refreshTab(cfg) {
	        var refresh_tab = cfg.tabTitle ? $('#tabs').tabs('getTab', cfg.tabTitle) : $('#tabs').tabs('getSelected');
	        if (refresh_tab && refresh_tab.find('iframe').length > 0) {
	            var _refresh_ifram = refresh_tab.find('iframe')[0];
	            var refresh_url = cfg.url ? cfg.url : _refresh_ifram.src;
	            _refresh_ifram.contentWindow.location.href = refresh_url;
	        }
	    }
	    function createFrame(url) {
	        return '<iframe scrolling="auto" frameborder="0" src="' + url + '" style="width:100%;height:100%;margin-bottom:-4px;"></iframe>';
	    }

	    $(".top-menu li > a").click(function () {
	        id = $(this).attr("moduleid");
	        text = $(this).text();
	        $(".top-menu li.selected").removeClass("selected");
	        $(this).parent("li").addClass("selected");
	        openTree(id);
	        return false;
	    });

	    function openTree(id) {
	    	$.ajaxRequest({
        		url: "getmenulist.do",
        		para: { moduleid: id },
        		success: function(result){
        			$('#left-tree').tree({
        				data: result,
        				onClick: function (node) {
        	                if (node.children.length == 0) {
        	                    openTab(node.text, "../" + node.controller + "/" + node.action + ".html");
        	                }
        	            }
        			});
        		}
        	}); 
	    }

	    $(".change-password").click(function () {
	        $("#change-password-win").window("open");
	    });

	    
	    $('#change-password-win form').form({
	        success: function (data) {
	        	
	        	debugger;
	            var data = eval('(' + data + ')')
	            if (data.isSuccess) {
	                $.messager.alert('提示', data.message, 'info');
	                setTimeout(function () { location.href = 'login.html' }, 2000);
	            }
	            else {
	                var msg = ErrorMessage(data.message);
	                $.messager.alert('提示', msg, 'error');
	            }
	        }
	    });

    
    	$(function(){
    		$(".top-menu li > a").eq(0).click();
    		/* $('#left-tree').tree({
    			data: [{
    				text: '基础系统',
    				lines: true,
    				state: 'open',
    				children: [
						{text: '支付渠道管理', attributes:{controller: "payChannel", action: "toPayChannelList"}},
						{text: '第三方卡管理', attributes:{controller: "thirdCardInfo", action: "toThirdCardInfoList"}},
						{text: '区域管理', attributes:{controller: "area", action: "toAreaList"}},
						{text: '影院管理', attributes:{controller: "cinema", action: "cinemaList"}},
						{text: '影片管理', attributes:{controller: "file", action: "tofileList"}},
						{text: '渠道管理', attributes:{controller: "channel", action: "toChannelList"}}
					]
    			},
    			{
    				text: '系统管理',
    				lines: true,
    				state: 'open',
    				children: [
						{text: '用户管理', attributes:{controller: "account", action: "index"}},
						{text: '角色管理', attributes:{controller: "role", action: "index"}},
					]
    			},
    			{
    				text: '通兑票管理',
    				lines: true,
    				state: 'open',
    				children: [
						{text: '单家通兑票', attributes:{controller: "sale", action: "intoSingleSale"}},
						{text: '多家通兑票管理', attributes:{controller: "ticketMultiply", action: "list"}},
						{text: '通兑票模板管理', attributes:{controller: "commonTemplate", action: "index"}},
						{text: '销售策略', attributes:{controller: "saleStrategy", action: "index"}},
						{text: '结算策略', attributes:{controller: "settlStrategy", action: "index"}},
						{text: '活动列表', attributes:{controller: "saleActive", action: "activeInit"}}
					]
    			},
    			{
    				text: '验证系统',
    				lines: true,
    				state: 'open',
    				children: [
						{text: '凭证管理', attributes:{controller: "voucher", action: "queryVoucherList"}}
					]
    			},
    			{
       				text: '订单系统',
       				lines: true,
       				state: 'open',
       				children: [
   						{text: '在线订单管理', attributes:{controller: "order", action: "orderInit"}},
   						{text: '退款订单管理', attributes:{controller: "order", action: "refundOrderInit"}},
   						{text: '异常订单管理', attributes:{controller: "order", action: "abnormalOrderInit"}},
   					]
    			},
    			{
    				text: '结算系统',
    				lines: true,
    				state: 'open',
    				children: [
						{text: '终端出票管理', attributes:{controller: "settlement", action: "queryVoucherValidList"}}
					]
    			},
    			{
       				text: '报表系统',
       				lines: true,
       				state: 'open',
       				children: [
   						{text: '影院结算金额总表', attributes:{controller: "rptCinemaSettlementMonthly", action: "toRptCinemaMonthlyList"}},
   						{text: '影院产品结算总表', attributes:{controller: "cinemaSettlProDaily", action: "toRptCinemaSettlProDailyList"}},
   						{text: '影院验证记录表', attributes:{controller: "rptSettlVouchervalidbase", action: "toRptSettlVouchervalidbaseList"}},
   						{text: '第三方卡结算报表', attributes:{controller: "rptThirdCardSettle", action: "init"}},
   						{text: '第三方卡对账报表', attributes:{controller: "thirdCardSettlDetails", action: "init"}},
   						{text: '线上业务收款渠道统计表', attributes:{controller: "rptPayChannleCash", action: "init"}},
   						{text: '院线通出入成本配比报表', attributes:{controller: "rptMarginMonthly", action: "init"}},
   						{text: '院线通残值报表', attributes:{controller: "rptOrderScrapDaily", action: "init"}}
   					]
    			}],
    			onClick: function (node) {
    				if (!node.children || node.children.length == 0) {
    	                openTab(node.text, "../" + node.attributes.controller + '/' + node.attributes.action + ".html");
    	            }
    	        }
    		});*/
    	}); 
    </script>
</e:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>