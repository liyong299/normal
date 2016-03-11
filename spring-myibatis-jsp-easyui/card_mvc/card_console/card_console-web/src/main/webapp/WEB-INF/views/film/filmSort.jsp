<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta charset="UTF-8">
	<title>兜有院线通后台管理系统 - 影片排序</title>
	<link href="<%=request.getContextPath()%>/resources/css/common/common.css" rel="stylesheet" type="text/css"/> 
    <link href="<%=request.getContextPath()%>/resources/css/base.css" rel="stylesheet" type="text/css"/> 
	<link href="<%=request.getContextPath()%>/resources/css/themes/easyui.css" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath()%>/resources/css/themes/icon.css" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath()%>/resources/css/themes/color.css" rel="stylesheet" type="text/css"/>
    <script src="<%=request.getContextPath()%>/resources/scripts/jquery-1.11.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/scripts/jquery-migrate-1.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/scripts/jquery.easyui.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/scripts/easyui-lang-zh_CN.js"></script>
	<script src="<%=request.getContextPath()%>/resources/scripts/jquery.serialize-object.js"></script>
	<script src="<%=request.getContextPath()%>/resources/scripts/jeasyui.js"></script>
	<script src="<%=request.getContextPath()%>/resources/scripts/base.js"></script>
	<script src="<%=request.getContextPath()%>/resources/scripts/jquery-ui.js"></script>
	
	<script type="text/javascript">
		
	function Map(){
		 this.keys = new Array();
		 this.data = new Array();

		 
		 this.put = function(key,value){
		  if(this.data[key] == null){
		   this.keys.push(value);
		  }
		  this.data[key] = value;
		 };

		 
		 this.get = function(key){
		  return this.data[key];
		 };

		 
		 this.remove = function(key){
		  this.keys.remove(key);
		  this.data[key] = null;
		 };

		 
		 this.isEmpty = function(){
		  return this.keys.length == 0;
		 };

		 
		 this.size = function(){
		  return this.keys.length;
		 };
		}
	
	
	//var data ={"total":30,"rows":[{"cast":"沈腾 / 马丽 / 尹正 / 艾伦 / 王智 / 田雨 / 宋阳 / 常远 / 李萍 / 李立群 / 张一鸣","country":"中国大陆","director":"闫非 / 彭大魔","duration":104,"filmname":"夏洛特烦恼","filmno":"001X03222015","highlight":".","id":13626,"intro":"昔日校花秋雅（王智 饰）的婚礼正在隆重举行，学生时代暗恋秋雅的夏洛（沈腾 饰）看着周围事业成功的老同学，心中泛起酸味，借着七分醉意大闹婚礼现场，甚至惹得妻子马冬梅（马丽 饰）现场发飙，而他发泄过后却在马桶上睡着了。梦里他重回校园，追求到他心爱的女孩、让失望的母亲重展笑颜、甚至成为无所不能的流行乐坛巨星…… \r\n　　醉生梦死中，他发现身边人都在利用自己，只有马冬梅是最值得珍惜的……","language":"汉语普通话","md5":"cc1541726ea842bcadef5ec11ed9f6f6","poster":"http://183.57.42.24:9998/csp_resources/201509091118550650.jpg","publishdate":"2015-09-30","publishdate_end":"","publishdate_start":"","publisher":"五洲电影","score":0,"showtypes":"2D","sortno":1,"stills":"http://183.57.42.24:9998/csp_resources/201509091124464880.jpg,http://183.57.42.24:9998/csp_resources/201509091124472130.jpg,http://183.57.42.24:9998/csp_resources/201509091124471110.jpg,http://183.57.42.24:9998/csp_resources/201509091124469180.jpg,http://183.57.42.24:9998/csp_resources/201509091124467980.jpg,http://183.57.42.24:9998/csp_resources/201509091124466380.jpg,http://183.57.42.24:9998/csp_resources/201509091124465770.jpg,http://183.57.42.24:9998/csp_resources/201509091124464280.jpg,http://183.57.42.24:9998/csp_resources/201509091124467040.jpg,http://183.57.42.24:9998/csp_resources/201509091124470170.jpg","type":"喜剧"},{"cast":"徐峥 / 赵薇 / 包贝尔 / 杜鹃 / 葛民辉 / 李璨琛 / 王晶","country":"中国大陆","director":"徐峥","duration":113,"filmname":"港囧","filmno":"001X04172015","highlight":".","id":13627,"intro":"徐来和小舅因不同目的来到香港，展开了一段阴差阳错，啼笑皆非的旅程，最终两人获得友谊并懂得了人生真谛。本片深挖香港文化与大陆文化的差异，是一部融入搞笑和地方文化特色于一炉的公路喜剧。","language":"国语","md5":"b9b43d1d0fbdc7f7379383614a7bfb10","poster":"http://183.57.42.24:9998/csp_resources/201509081120159040.jpg","publishdate":"2015-09-25","publishdate_end":"","publishdate_start":"","publisher":"山南光线影业有限公司\r\n华夏电影发行有限责任公司\r\n天津猫眼文化传媒有限公司","score":0,"showtypes":"2D/中国巨幕/IMAX/4DX","sortno":2,"stills":"http://183.57.42.24:9998/csp_resources/201509081120395770.jpg,http://183.57.42.24:9998/csp_resources/201509081120399270.jpg,http://183.57.42.24:9998/csp_resources/201509081120396390.jpg,http://183.57.42.24:9998/csp_resources/201509081120394910.jpg,http://183.57.42.24:9998/csp_resources/201509081120397600.jpg,http://183.57.42.24:9998/csp_resources/201509081120397190.jpg,http://183.57.42.24:9998/csp_resources/201509081120398400.jpg","type":"喜剧"},{"cast":"孙坚 / 周泓 / 金恩圣 / 李彩英","country":"中国大陆","director":"Pakphum Wongjinda /","duration":90,"filmname":"魔镜","filmno":"001X04872015","highlight":".","id":13628,"intro":"深夜，年轻女孩在朋友聚会后酒驾不幸路遇车祸，独自养伤的她欣喜又羞涩的与一位年轻陌生男子微信互聊起来。一来一往中男子与她互生情愫，相约互换照片，女孩精心打扮后自拍照片通过手机发送过去，却收到了诡异的照片和回复……\r\n四岁的孙子在爷爷奶奶的看护下长大，老人相继过世，但在迟迟还没有学会说话的孙子眼里他们并没有离去。他还是如常地用爷爷奶奶给他的零花钱去胡同买爱吃的东西，而买东西的钱都是旧钞。儿子的这些怪异举动让爸爸妈妈感到不安，他们决定揭开谜团……\r\n在一个拍摄民国鬼片题材的电影剧组里，新进组的一位临时年轻女演员被分配扮演女鬼，可拍摄过程中却随之发生了一系列诡异事件，剧组上下被吓得魂飞魄散。杀青之后导演解释为影片达到恐怖效果准备的“秘密武器”，庆祝之时，回顾种种细节，却惊天发现令人毛骨悚然的事情……","language":"汉语普通话 / 韩语 / 泰语","md5":"b2536600c2999a04f0b55b30fa341505","poster":"http://183.57.42.24:9998/csp_resources/201509091052021360.jpg","publishdate":"2015-09-25","publishdate_end":"","publishdate_start":"","publisher":".","score":0,"showtypes":"2D","sortno":3,"stills":"http://183.57.42.24:9998/csp_resources/201508131002274920.jpg,http://183.57.42.24:9998/csp_resources/201508131002270960.jpg,http://183.57.42.24:9998/csp_resources/201508131002275580.jpg,http://183.57.42.24:9998/csp_resources/201508131002272250.jpg,http://183.57.42.24:9998/csp_resources/201508131002274320.jpg,http://183.57.42.24:9998/csp_resources/201508131002271680.jpg,http://183.57.42.24:9998/csp_resources/201508131002272920.jpg,http://183.57.42.24:9998/csp_resources/201508131002273580.jpg","type":"惊悚"},{"cast":"高云翔 / 熊乃瑾 / 刘言语 / 马少骅","country":"中国","director":"刘观伟 / 辛成江","duration":91,"filmname":"人皮拼图","filmno":"001X05412014","highlight":".","id":9509,"intro":"市内发生多起剥皮连环杀人案，女编辑苏苏（熊乃瑾 饰）为追踪报道在男友陈默（高云翔 饰）的帮助下暗中查访，恐怖事件接二连三发生在两人身上，贴在门上的三角形人皮、被解刨的动物尸体、早年丧子的孤寡老人……随着苏苏越来越深入的调查，事件越发的诡异和扑朔迷离，就在真相渐渐浮出水面之际，又一个更为可怕的秘密即将显现……\r\n传说千年的古老人皮诅咒将为世人揭开…","language":"汉语普通话","md5":"dfe894e646b6fcc9ff22a7a6d6961ab6","poster":"http://183.57.42.24:9998/csp_resources/201509091511032340.jpg","publishdate":"2015-09-18","publishdate_end":"","publishdate_start":"","publisher":"中影数字","score":0,"showtypes":"2D","sortno":0,"stills":"http://183.57.42.24:9998/csp_resources/201509091511237750.jpg,http://183.57.42.24:9998/csp_resources/201509091511236720.jpg,http://183.57.42.24:9998/csp_resources/201509091511237210.jpg","type":"悬疑 / 惊悚"},{"cast":"吕聿来 / 谢君豪 / 张睿家 / 黎一墨 / 何佩瑜 / 时诗 / 张陆 / 杜玉明","country":"中国大陆","director":"宁敬武","duration":90,"filmname":"墓穴迷城","filmno":"001X02422015","highlight":".","id":13629,"intro":"建筑公司测绘员方子华无意得知自己的身世之谜与一座古墓有关，父亲留给自己的遗物是一块价值连城的玉珏和一把神秘的金刚杵。方子华的朋友们决定和他一起进入古墓，揭开身世之谜。","language":"汉语普通话","md5":"afde6bf2198fb008a0799e108e36ad5d","poster":"http://183.57.42.24:9998/csp_resources/201509060953531010.jpg","publishdate":"2015-09-18","publishdate_end":"","publishdate_start":"","publisher":"华夏公司","score":0,"showtypes":"2D","sortno":0,"stills":"","type":"惊悚"},{"cast":"西恩·潘 / 杰丝敏·特丽卡 / 哈维尔·巴登 / 雷·温斯顿 / 马克·里朗斯 / 伊德里斯·艾尔巴 / 彼得·弗兰森 / 马克·沙尔丹 / 埃米利奥·布阿勒 / 卡拉·佩雷斯 / 托尼·科尔维略 / 安吉拉·富恩特","country":"英国/法国","director":"皮埃尔·莫瑞尔","duration":115,"filmname":"使命召唤","filmno":"075X00632015","highlight":".","id":13630,"intro":"国际特种部队间谍吉姆（西恩·潘 Sean Penn 饰），为了他的爱人而想要退出组织，但天不从人愿，就在他完成最后一项“任务”时，为了确保自身安全与公司利益，他必须要人间蒸发匿身他处，与心爱的人从此分开。离开组织在欧洲流亡多年后，吉姆以为能够脱离危险，就此过着平静的生活；无奈人生并非他想像中单纯，在刚果被神秘人物追杀的他，被迫放下所有，展开逃亡。逃亡途中他辗转联系到昔日战友，并寻求他们的协助，却也因此发现追杀行动里暗藏着不为人知的祕密，而现在，唯有找出追杀他的幕后主使，才能给他一条生路……","language":"国语/英语","md5":"5266710cbdd503ff4e41817f6dfd4fa8","poster":"http://183.57.42.24:9998/csp_resources/201509091106016330.jpg","publishdate":"2015-09-18","publishdate_end":"","publishdate_start":"","publisher":"中影数字,华夏公司","score":0,"showtypes":"2D","sortno":0,"stills":"http://183.57.42.24:9998/csp_resources/201509091106562320.jpg,http://183.57.42.24:9998/csp_resources/201509091106557670.jpg,http://183.57.42.24:9998/csp_resources/201509091106559100.jpg,http://183.57.42.24:9998/csp_resources/201509091106560290.jpg,http://183.57.42.24:9998/csp_resources/201509091106558440.jpg,http://183.57.42.24:9998/csp_resources/201509091106561110.jpg,http://183.57.42.24:9998/csp_resources/201509091106559700.jpg,http://183.57.42.24:9998/csp_resources/201509091106561760.jpg","type":"剧情 / 动作 / 犯罪"},{"cast":"全智贤 / 李政宰 / 河正宇 / 吴达洙 / 赵镇雄 / 李璟荣 / 崔德门 / 金义城 / 朴炳垠 / 陈庆 / 金海淑 / 曹承佑","country":"韩国","director":"崔东勋","duration":140,"filmname":"暗杀","filmno":"010X00562015","highlight":".","id":13631,"intro":"上世纪初，韩国笼罩在日本统治的阴影下，与此同时，很多韩国抵抗活动以中国为据点以躲避日本当局。 \r\n　　1933年的上海，一个暗杀日本指挥官的秘密命令交由安玉允（全智贤 饰）执行，她是个已经被判处死刑的一流狙击手。为了这个任务，为临时政府工作的特工廉锡镇（李政宰 饰）从监狱里解救了安玉允和她的同伴。但 与此同时这个重大计划被一个内部间谍和职业杀手泄露给了日本当局，夏威夷·皮斯托（河正宇 饰）因此被雇佣去除掉安玉允。而暗杀小组抵达韩国时对职业杀手的追击毫不知情。安玉允有可能完成这个不可能做到的任务吗?","language":"韩语","md5":"9d149633bac67f5bfb8d36397244cd33","poster":"http://183.57.42.24:9998/csp_resources/201509061545145240.jpg","publishdate":"2015-09-17","publishdate_end":"","publishdate_start":"","publisher":".","score":0,"showtypes":"2D","sortno":0,"stills":"http://183.57.42.24:9998/csp_resources/201509061545393470.jpg,http://183.57.42.24:9998/csp_resources/201509061545389440.jpg,http://183.57.42.24:9998/csp_resources/201509061545390650.jpg,http://183.57.42.24:9998/csp_resources/201509061545391450.jpg,http://183.57.42.24:9998/csp_resources/201509061545390140.jpg,http://183.57.42.24:9998/csp_resources/201509061545392090.jpg,http://183.57.42.24:9998/csp_resources/201509061545392680.jpg,http://183.57.42.24:9998/csp_resources/201509061545394060.jpg","type":"剧情 / 动作 / 历史 / 犯罪"},{"cast":"林家栋 / 秦岚 / 任达华 / 高圣远 / 刘威 / 苗雅宁 / 高鑫 / 屈菁菁 / 任山","country":"中国大陆","director":"黄岳泰","duration":94,"filmname":"别有动机","filmno":"001X02412015","highlight":".","id":13632,"intro":"原本生活安稳幸福的富家千金叶霜（秦岚 饰），因海龟老公凌锋（高圣远 饰）和女儿的意外失踪，生活变得阴云密布。叶霜与父亲叶城（任达华 饰）商议后报警，却和自己的前男朋友姚杰（林家栋 饰）再度重逢。 \r\n　　在追查过程中，姚杰发现这起千万赎金的绑架案迷雾重重，擅长从细枝末节入手查案的他将矛头对准了叶城，而此时叶城的行迹也变得可疑诡谲。就当迷雾似乎散去时，叶城却意外死亡，一起20年前凶案随之也浮出水面，逐渐漏出端倪的惊人秘密背后，神秘绑匪竟然别有动机…","language":"汉语普通话 / 粤语","md5":"46ae0f0a0e8d96e9c286307c77a5a584","poster":"http://183.57.42.24:9998/csp_resources/201509091101402450.jpg","publishdate":"2015-09-17","publishdate_end":"","publishdate_start":"","publisher":"中影数字","score":0,"showtypes":"2D","sortno":0,"stills":"http://183.57.42.24:9998/csp_resources/201509091101500230.jpg,http://183.57.42.24:9998/csp_resources/201509091101497460.jpg,http://183.57.42.24:9998/csp_resources/201509091101498240.jpg,http://183.57.42.24:9998/csp_resources/201509091101501370.jpg,http://183.57.42.24:9998/csp_resources/201509091101496390.jpg,http://183.57.42.24:9998/csp_resources/201509091101492450.jpg,http://183.57.42.24:9998/csp_resources/201509091101494880.jpg,http://183.57.42.24:9998/csp_resources/201509091101493590.jpg,http://183.57.42.24:9998/csp_resources/201509091101499420.jpg,http://183.57.42.24:9998/csp_resources/201509091101502100.jpg","type":"动作 / 悬疑 / 犯罪"},{"cast":"亚当·桑德勒 / 凯文·詹姆斯 / 米歇尔·莫纳汉 / 彼特·丁克拉奇 / 乔什·加德 / 布莱恩·考克斯 / 马特·林茨 / 肖恩·宾 / 简·科拉克斯基 / 丹·艾克罗伊德 / 艾菲恩·克洛科特 / 莱妮·卡赞 /","country":"美国 / 中国大陆 / 加拿大","director":"克里斯·哥伦布","duration":106,"filmname":"像素大战","filmno":"051X00602015","highlight":".","id":13633,"intro":"作为1980年代的小孩，山姆·布伦纳（亚当·桑德勒 Adam Sandler 饰）, 威尔·库珀（凯文·詹姆斯 Kevin James 饰），勒德洛·莱门梢夫（乔什·加德 Josh Gad 饰）和“火焰爆破枪”埃迪·普兰特（彼特·丁克拉奇 Peter Dinklage 饰）拯救过世界成千上万次——在游戏机室里二十五美分玩一次的游戏里。现在，他们必须得真刀真枪作战了。 \r\n　　在像素大战中，当外星人发现经典街机游戏视频流并且误以为这是在宣战后，他们袭击了地球，运用游戏作为他们袭击策略——而现在美国总统库珀必须召集他旧日的街机战友一起拯救世界免受吃豆人、大金刚、小蜜蜂、蜈蚣还有太空入侵者毁灭。加入他们一起战斗的，还有一名向街机斗士提供外星人战斗专用武器的专家，维奥莱特·冯·帕顿中校（米歇尔·莫纳汉 Michelle Monaghan 饰）。他们的唯一目标是，战胜8比特像素外星人，保卫地球……","language":"英语","md5":"e3c055408af16d04a5b438b51ce8aa72","poster":"http://183.57.42.24:9998/csp_resources/201509061550073580.jpg","publishdate":"2015-09-15","publishdate_end":"","publishdate_start":"","publisher":".","score":0,"showtypes":"3D/中国巨幕/IMAX","sortno":0,"stills":"http://183.57.42.24:9998/csp_resources/201509061550222100.jpg,http://183.57.42.24:9998/csp_resources/201509061550221370.jpg,http://183.57.42.24:9998/csp_resources/201509061550223300.jpg,http://183.57.42.24:9998/csp_resources/201509061550222740.jpg,http://183.57.42.24:9998/csp_resources/201509061550224520.jpg,http://183.57.42.24:9998/csp_resources/201509061550225240.jpg,http://183.57.42.24:9998/csp_resources/201509061550225900.jpg,http://183.57.42.24:9998/csp_resources/201509061550223880.jpg","type":"喜剧 / 动作 / 科幻"},{"cast":"桑德拉·布洛克 / 乔恩·哈姆 / 迈克尔·基顿 / 艾莉森·珍妮 / 史蒂夫·库根 / 珍妮弗·桑德斯 / 杰弗里·拉什 / 史蒂夫·卡瑞尔 / 皮埃尔·科芬 / 凯蒂·米克松 / 迈克尔·贝亚蒂 / 真田广之 / 大","country":"美国","director":"凯尔·巴尔达 / 皮埃尔·科芬","duration":91,"filmname":"小黄人大眼萌","filmno":"051X00622015","highlight":".","id":13634,"intro":"从地球诞生之初，一种奇怪的生物便出现在这颗蓝色的星球上。他们不断进化、蜕变，终于成为我们所熟悉的小黄人的模样。小黄人们毕生寻找邪恶的老大，以成为他的部属为荣，但是千百万年来的努力最终化成一场空。经历了一段长久的沉寂，名叫凯文的小黄人决定再度上路，他带着热爱音乐的斯图尔特、成事不足败事有余的鲍勃，漂洋过海来到了1968年的美国。三个小家伙偶然听说奥兰多即将召开“恶人大会”，于是忙不迭地赶了过去，并且阴差阳错成为女魔头斯嘉丽·杀人狂（桑德拉·布洛克 Sandra Bullock 配音）的随从。很快斯嘉丽交给他们一个任务，那就是窃取英国女王的王冠。\r\n不知轻重的小家伙们笑嘻嘻地出征，等待他们的将是前所未有的大冒险……","language":"英语","md5":"64e8546ec600b30bcbc0c988cc320d22","poster":"http://183.57.42.24:9998/csp_resources/201509091025489620.jpg","publishdate":"2015-09-13","publishdate_end":"","publishdate_start":"","publisher":".","score":0,"showtypes":"3D/中国巨幕3D/IMAX3D","sortno":0,"stills":"http://183.57.42.24:9998/csp_resources/201508310930453390.jpg,http://183.57.42.24:9998/csp_resources/201508310930452460.jpg,http://183.57.42.24:9998/csp_resources/201508310930460080.jpg,http://183.57.42.24:9998/csp_resources/201508310930456120.jpg,http://183.57.42.24:9998/csp_resources/201508310930457480.jpg,http://183.57.42.24:9998/csp_resources/201508310930450330.jpg,http://183.57.42.24:9998/csp_resources/201508310930454750.jpg,http://183.57.42.24:9998/csp_resources/201508310930451580.jpg,http://183.57.42.24:9998/csp_resources/201508310930448960.jpg,http://183.57.42.24:9998/csp_resources/201508310930458830.jpg","type":"喜剧 / 动画"},{"cast":"芦芳生 / 杨采钰","country":"中国","director":"霍建起","duration":105,"filmname":"1980年代的爱情","filmno":"001X00492015","highlight":".","id":9248,"intro":"关雨波（芦芳生 饰）大学毕业后分回故乡的小镇政府工作，颓废消极的他在小镇借酒浇愁，邂逅了在供销社当营业员的成丽雯（杨采钰 饰）。\r\n雯是关的高中同学，当年关暗恋着雯，并偷放过一封情书在雯的书包里。1978年高考结束，全校仅关考上省城大学，从此两人境遇不同，渐渐没了联系。而今重逢昔日的初恋对象，关内心激动，而雯却保持着距离。因彼此身份悬殊，雯反而显得孤傲难近。\r\n在这寂寞小镇，两个青年互相寻找着温情。雯虽然在生活中关心他，鼓励他重新振作，但雯的内心情感始终理性的封闭着。关重新发现着这个女人的一切善美，并试图闯进她的生活……\r\n1980年代的爱情，那段时光留在每个过来人心底里的，是久经复苏的浪漫人性和绝美的纯情。\r\n影片改编自同名小说《1980年代的爱情》。","language":"汉语普通话","md5":"4254db75588ff1606319663cbd590dcc","poster":"http://183.57.42.24:9998/csp_resources/201509091640151460.jpg","publishdate":"2015-09-11","publishdate_end":"","publishdate_start":"","publisher":".","score":0,"showtypes":"2D","sortno":0,"stills":"http://183.57.42.24:9998/csp_resources/201509071625449570.jpg,http://183.57.42.24:9998/csp_resources/201509071625452560.jpg,http://183.57.42.24:9998/csp_resources/201509071625451970.jpg,http://183.57.42.24:9998/csp_resources/201509071625453280.jpg,http://183.57.42.24:9998/csp_resources/201509071625450660.jpg,http://183.57.42.24:9998/csp_resources/201509071625448210.jpg,http://183.57.42.24:9998/csp_resources/201509071625446850.jpg,http://183.57.42.24:9998/csp_resources/201509071625451330.jpg","type":"剧情 / 爱情"},{"cast":"韩兆 / 刘桦 / 买红妹 / 曹随风 / 潘长江 / 雷牧 / 殷叶子","country":"中国","director":"韩兆","duration":88,"filmname":"斗地主","filmno":"001X00532015","highlight":".","id":9264,"intro":"民国初年，鱼龙混杂、三教九流聚集的黑桃城出了件蹊跷事：青楼“人间天上”的头牌艺人、才貌双绝的春天姑娘莫名其妙地失踪了，一时间众说纷纭。实际上，春天是被为富不仁的地主王大猫霸占，欲娶为小老婆。王大猫此前曾有过七房姨太太，奇怪的是除了大太太红桃，其余的都是短命鬼。死因各不相同，结果只有一个……春天姑娘自打进了地主家，瞬间便命悬一线，岌岌可危。 \r\n　　善良与智慧并存的青年顺子设巧计来到王大猫家做长工，风骚的大太太红桃一眼便看上了这个俊朗有型的小伙子，天天用美人计勾引顺子；师爷方片则抱着不可告人的目的，对顺子软硬兼施、威逼利诱；地主王大猫虽不动声色，却暗中吩咐手下替顺子准备后事。原来，黑桃城的K大帅要与东北军开战，通知地主们筹集粮饷，不交钱粮就杀人。王大猫舍命不舍财，想出了狸猫换太子的鬼主意，顺子便是他找来的替死鬼 \r\n　　其实顺子来地主家当长工的本意是为了营救春天姑娘而来，他和春天姑娘自幼定亲、青梅竹马。在顺子的安排下，二人多次逃跑计划均告失败，顺子的意图也被地主知晓。王大猫威胁顺子必须替他去死，否则就杀死春天。眼看期限已到，军阀老K下令对拒不交纳粮饷的地主执行枪决。但他并不知道，押赴刑场的“地主”其实是长工顺子。顺子凭借过人的智慧化险为夷，重返大王庄，决心与地主王大猫好好斗一斗……","language":"汉语普通话","md5":"63fa46e0d7b0cffc41beb2c276790f00","poster":"http://183.57.42.24:9998/csp_resources/201508311723011160.jpg","publishdate":"2015-09-11","publishdate_end":"","publishdate_start":"","publisher":"中影数字","score":0,"showtypes":"2D","sortno":0,"stills":"http://183.57.42.24:9998/csp_resources/201508311725472590.jpg,http://183.57.42.24:9998/csp_resources/201508311725461240.jpg,http://183.57.42.24:9998/csp_resources/201508311725473530.jpg,http://183.57.42.24:9998/csp_resources/201508311725460730.jpg,http://183.57.42.24:9998/csp_resources/201508311725452680.jpg,http://183.57.42.24:9998/csp_resources/201508311725494070.jpg,http://183.57.42.24:9998/csp_resources/201508311725453380.jpg,http://183.57.42.24:9998/csp_resources/201508311725468800.jpg","type":"喜剧"},{"cast":"解惠清 / 刘之冰 / 方青卓 / 茹萍 / 李歌 / 李卫东 / 梁辰羽","country":"中国大陆","director":"方义华 / 董振业","duration":99,"filmname":"东莞女孩","filmno":"001X03612015","highlight":".","id":13635,"intro":"她，叫林美凤，和所有女孩一样怀揣着梦想 ，坐上了南下的火车。 \r\n　　四岁那年，她的记忆还是模糊的，病魔夺去了母亲年轻的生命，听小姨说，下葬那天母亲是抱着父亲的照片下葬的，从此，谁也不愿意提及美凤的父亲。长成人的美凤，寻找父亲家人是她的一种动力，听邻居说，父亲的家在东莞长安镇。 \r\n　　来到了长安镇，美凤第一份工作是做收银员，闲下来她都会打听林姓的下落。后来，辗转到了食品工厂工作。在直到美凤兼职做了酒店的服务员，她的人生发生了重大的改变，遭遇一次“脱”与“不脱”的冲突……","language":"汉语普通话","md5":"4cf0e90374cc396809378bc8aeacb78e","poster":"http://183.57.42.24:9998/csp_resources/201508241105558020.jpg","publishdate":"2015-09-11","publishdate_end":"","publishdate_start":"","publisher":"中影数字","score":0,"showtypes":"2D","sortno":0,"stills":"http://183.57.42.24:9998/csp_resources/201508241107006090.jpg,http://183.57.42.24:9998/csp_resources/201508241107004750.jpg,http://183.57.42.24:9998/csp_resources/201508241107004100.jpg,http://183.57.42.24:9998/csp_resources/201508241107006690.jpg,http://183.57.42.24:9998/csp_resources/201508241107002810.jpg,http://183.57.42.24:9998/csp_resources/201508241107001610.jpg,http://183.57.42.24:9998/csp_resources/201508241107007300.jpg,http://183.57.42.24:9998/csp_resources/201508241107008190.jpg","type":"剧情"},{"cast":"王传君 / 董维嘉 / 陈亦飞 / 戚九洲 / 李佑晨 / 吴岱融","country":"中国大陆","director":"邓科","duration":92,"filmname":"星语心愿之再爱","filmno":"001X04232015","highlight":".","id":13636,"intro":"“洋葱头”升级暖男学霸（王传君 饰），“秋男”晋升霸气师姐（董维嘉 饰），你也许会猜中开头，但你永远猜不到结局。\r\n情未了，爱难追。连一个拥抱都无法给与，我却是这世上最爱你的那一个；就算命运一定要让我们生死相隔，我也要找到一种方式回到你身旁守护你……","language":"汉语普通话","md5":"50c2ee5c3dd0c512559be2f21718420f","poster":"http://183.57.42.24:9998/csp_resources/201508211608413400.jpg","publishdate":"2015-09-11","publishdate_end":"","publishdate_start":"","publisher":".","score":0,"showtypes":"2D","sortno":0,"stills":"http://183.57.42.24:9998/csp_resources/201508211609152700.jpg,http://183.57.42.24:9998/csp_resources/201508211609199760.jpg,http://183.57.42.24:9998/csp_resources/201508211609245890.jpg,http://183.57.42.24:9998/csp_resources/201508211609163320.jpg,http://183.57.42.24:9998/csp_resources/201508211609212670.jpg,http://183.57.42.24:9998/csp_resources/201508211609207580.jpg,http://183.57.42.24:9998/csp_resources/201508211609219970.jpg,http://183.57.42.24:9998/csp_resources/201508211609180420.jpg","type":"爱情"},{"cast":"马恩然 / 王馥荔 / 孔祥玉 / 张绍刚 / 张炜迅 / 刘琳 / 叮当 / 程前 / 娜仁花 / 杨立新","country":"中国大陆","director":"丁荫楠 / 丁震","duration":100,"filmname":"启功","filmno":"001X01812015","highlight":".","id":13637,"intro":"文革期间，一生兢兢业业以教育为毕生事业的书画家启功（马恩然 饰）平静的生活被打破。显赫的帝胄家世，即使摘掉了右派的帽子，在那个特殊的年代也依旧避免不了浩浩荡荡的思想改造运动，由于擅长书法，红卫兵队长刘雨辰安排启功负责摘抄大字报。一切教学研究、书法创作活动的中止，带给启功生活上的窘迫令他一度开始怀疑自己的创作，甚至想烧掉自己毕生研究的心血，身患重病不离不弃的妻子鼓励启功继续坚持自己的事业。红卫兵的搜查让启功销毁不少珍贵的文物字画，尘封的往事随着字画浮现眼前。 \r\n　　启功少年时代接受祖父的艺术熏陶，痴迷于作画，后拜书画大师溥心畲为师潜心学习，颇有成就，宗亲纷纷来求画却因字丑不许他落款，启功从此发奋练习书法。中年时期的启功被辅仁大学校长陈垣先生提携，介绍进入辅仁中学教国文，因学历不够被辞退，走投无路之际，陈垣校长又伸出援手，破格让他到辅仁大学当助教，陈垣校长悉心指导栽培启功，师生情谊真挚感人，启功更以“学为人师，行为世范”当做自己一生所求。 \r\n　　改革开放后，启功先生成为了享誉国内外的大师，他笔耕不辍，继续为教育事业奋斗。为了恩泽下一代年轻人，他将自己书画作品义卖所得的资金成立了励耘奖学金，启功先生高尚的品格，伟大的情怀堪称圣贤。","language":"汉语普通话","md5":"5013860d8bed90cd210852481f5bdc29","poster":"http://183.57.42.24:9998/csp_resources/201509021514581370.jpg","publishdate":"2015-09-10","publishdate_end":"","publishdate_start":"","publisher":".","score":0,"showtypes":"2D","sortno":0,"stills":"http://183.57.42.24:9998/csp_resources/201509021515200790.jpg,http://183.57.42.24:9998/csp_resources/201509021515202090.jpg,http://183.57.42.24:9998/csp_resources/201509021515202680.jpg,http://183.57.42.24:9998/csp_resources/201509021515200070.jpg,http://183.57.42.24:9998/csp_resources/201509021515199330.jpg,http://183.57.42.24:9998/csp_resources/201509021515198320.jpg,http://183.57.42.24:9998/csp_resources/201509021515197150.jpg,http://183.57.42.24:9998/csp_resources/201509021515201380.jpg","type":"剧情 / 传记"}]};
		var data= ${file};
			
		(function($){
			$.extend($.fn.datagrid.defaults, {
				onBeforeDrag: function(row){},	// return false to deny drag
				onStartDrag: function(row){},
				onStopDrag: function(row){},
				onDragEnter: function(targetRow, sourceRow){},	// return false to deny drop
				onDragOver: function(targetRow, sourceRow){},	// return false to deny drop
				onDragLeave: function(targetRow, sourceRow){},
				onBeforeDrop: function(targetRow, sourceRow, point){},
				onDrop: function(targetRow, sourceRow, point){},	// point:'append','top','bottom'
			});
			
			$.extend($.fn.datagrid.methods, {
				enableDnd: function(jq, index){
					return jq.each(function(){
						var target = this;
						var state = $.data(this, 'datagrid');
						state.disabledRows = [];
						var dg = $(this);
						var opts = state.options;
						if (index != undefined){
							var trs = opts.finder.getTr(this, index);
						} else {
							var trs = opts.finder.getTr(this, 0, 'allbody');
						}
						trs.draggable({
							disabled: false,
							revert: true,
							cursor: 'pointer',
							proxy: function(source) {
								var index = $(source).attr('datagrid-row-index');
								var tr1 = opts.finder.getTr(target, index, 'body', 1);
								var tr2 = opts.finder.getTr(target, index, 'body', 2);
								var p = $('<div style="z-index:9999999999999"></div>').appendTo('body');
								tr2.clone().removeAttr('id').removeClass('droppable').appendTo(p);
								tr1.clone().removeAttr('id').removeClass('droppable').find('td').insertBefore(p.find('td:first'));
								$('<td><span class="tree-dnd-icon tree-dnd-no" style="position:static">&nbsp;</span></td>').insertBefore(p.find('td:first'));
								p.find('td').css('vertical-align','middle');
								p.hide();
								return p;
							},
							deltaX: 15,
							deltaY: 15,
							onBeforeDrag:function(e){
								if (opts.onBeforeDrag.call(target, getRow(this)) == false){return false;}
								if ($(e.target).parent().hasClass('datagrid-cell-check')){return false;}
								if (e.which != 1){return false;}
								opts.finder.getTr(target, $(this).attr('datagrid-row-index')).droppable({accept:'no-accept'});
							},
							onStartDrag: function() {
								$(this).draggable('proxy').css({
									left: -10000,
									top: -10000
								});
								var row = getRow(this);
								opts.onStartDrag.call(target, row);
								state.draggingRow = row;
							},
							onDrag: function(e) {
								var x1=e.pageX,y1=e.pageY,x2=e.data.startX,y2=e.data.startY;
								var d = Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
								if (d>3){	// when drag a little distance, show the proxy object
									$(this).draggable('proxy').show();
									var tr = opts.finder.getTr(target, parseInt($(this).attr('datagrid-row-index')), 'body');
									$.extend(e.data, {
										startX: tr.offset().left,
										startY: tr.offset().top,
										offsetWidth: 0,
										offsetHeight: 0
									});
								}
								this.pageY = e.pageY;
							},
							onStopDrag:function(){
								for(var i=0; i<state.disabledRows.length; i++){
									var index = dg.datagrid('getRowIndex', state.disabledRows[i]);
									if (index >= 0){
										opts.finder.getTr(target, index).droppable('enable');
									}
								}
								state.disabledRows = [];
								var index = dg.datagrid('getRowIndex', state.draggingRow);
								dg.datagrid('enableDnd', index);
								opts.onStopDrag.call(target, state.draggingRow);
							}
						}).droppable({
							accept: 'tr.datagrid-row',
							onDragEnter: function(e, source){
								if (opts.onDragEnter.call(target, getRow(this), getRow(source)) == false){
									allowDrop(source, false);
									var tr = opts.finder.getTr(target, $(this).attr('datagrid-row-index'));
									tr.find('td').css('border', '');
									tr.droppable('disable');
									state.disabledRows.push(getRow(this));
								}
							},
							onDragOver: function(e, source) {
								var targetRow = getRow(this);
								if ($.inArray(targetRow, state.disabledRows) >= 0){return;}
								var pageY = source.pageY;
								var top = $(this).offset().top;
								var bottom = top + $(this).outerHeight();
								
								allowDrop(source, true);
								var tr = opts.finder.getTr(target, $(this).attr('datagrid-row-index'));
								tr.children('td').css('border','');
								if (pageY > top + (bottom - top) / 2) {
									tr.children('td').css('border-bottom','1px solid red');
								} else {
									tr.children('td').css('border-top','1px solid red');
								}
								
								if (opts.onDragOver.call(target, targetRow, getRow(source)) == false){
									allowDrop(source, false);
									tr.find('td').css('border', '');
									tr.droppable('disable');
									state.disabledRows.push(targetRow);
								}
							},
							onDragLeave: function(e, source) {
								allowDrop(source, false);
								var tr = opts.finder.getTr(target, $(this).attr('datagrid-row-index'));
								tr.children('td').css('border','');
								opts.onDragLeave.call(target, getRow(this), getRow(source));
							},
							onDrop: function(e, source) {
								var sourceIndex = parseInt($(source).attr('datagrid-row-index'));
								var destIndex = parseInt($(this).attr('datagrid-row-index'));
								
								var tr = opts.finder.getTr(target, $(this).attr('datagrid-row-index'));
								var td = tr.children('td');
								var point =  parseInt(td.css('border-top-width')) ? 'top' : 'bottom';
								td.css('border','');
								
								var rows = dg.datagrid('getRows');
								var dRow = rows[destIndex];
								var sRow = rows[sourceIndex];
								if (opts.onBeforeDrop.call(target, dRow, sRow, point) == false){
									return;
								}
								insert();
								opts.onDrop.call(target, dRow, sRow, point);
								
								function insert(){
									var row = $(target).datagrid('getRows')[sourceIndex];
									var index = 0;
									if (point == 'top'){
										index = destIndex;
									} else {
										index = destIndex+1;
									}
									if (index < sourceIndex){
										dg.datagrid('deleteRow', sourceIndex).datagrid('insertRow', {
											index: index,
											row: row
										});
										dg.datagrid('enableDnd', index);
									} else {
										dg.datagrid('insertRow', {
											index: index,
											row: row
										}).datagrid('deleteRow', sourceIndex);
										dg.datagrid('enableDnd', index-1);
									}
								}
								

								sort();
								
								function sort(){
									var rows = $(target).datagrid('getRows');
									//讲新的顺序按倒序赋值给所选影片
									for(var i=0; i<rows.length; i++){
										var tmpIndex=rows.length-i;
										rows[i].sortno=tmpIndex;
									}
									
									$.ajax({
							            cache: true,
							            type: "POST",
							            url:"../film/sortFilm.do",
							            data : {rows : JSON.stringify(rows)}, 
							            dataType: "json",
							            async: false,
							            error: function(request) {
							                alert("Connection error");
							            },
							            success: function(data) {
							               if(data.success){
								            	   parent.Tips.Success(data.resultMsg);
								            	   parent.datalist._temp.list_box.datagrid("reload");
								               }else{
								            	   alert(data.errMsg);
								               }            
								            }
								        });
								}
								
							}
						});
						
						function allowDrop(source, allowed){
							var icon = $(source).draggable('proxy').find('span.tree-dnd-icon');
							icon.removeClass('tree-dnd-yes tree-dnd-no').addClass(allowed ? 'tree-dnd-yes' : 'tree-dnd-no');
						}
						function getRow(tr){
							return opts.finder.getRow(target, $(tr));
						}
					});
				}
				
			});	
	
		})(jQuery);	
		
	</script>
</head>
<body  class="easyui-layout">
	<table class="easyui-datagrid" id="dg" style="width:780px;height:450px" data-options="
				rownumbers:true,
				singleSelect:true,
				data:data,
				onLoadSuccess:function(){
					$(this).datagrid('enableDnd');
				}
			">
		<thead>
			<tr>
<!-- 				<th data-options="field:'sortno',width:80">排序编号</th> -->
<!-- 				<th data-options="field:'filmno',width:120">影片编号</th> -->
				<th data-options="field:'id',width:120" hidden="true">编码</th>
				<th data-options="field:'filmname',width:120">影片名称</th>
<!-- 				<th data-options="field:'duration',width:60,align:'right'">时长</th> -->
				<th data-options="field:'publishdate',width:100,align:'right'">影片上映日期</th>
<!-- 				<th data-options="field:'publisher',width:150">发行商</th> -->
				<th data-options="field:'director',width:120,align:'center'">导演</th>
				<th data-options="field:'cast',width:200">演员</th>
<!-- 				<th data-options="field:'score',width:80,align:'right'">影片评分</th> -->
<!-- 				<th data-options="field:'type',width:80,align:'right'">影片类型</th> -->
				

			</tr>
		</thead>
	</table>
</body>
</html>

