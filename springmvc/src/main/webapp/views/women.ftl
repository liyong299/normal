<!DOCTYPE HTML>
<!--
	Landed by HTML5 UP
	html5up.net | @n33co
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
        <@style.meta />  
		<@style.css />
        <@style.turn1 />
	</head>
	<body>
		<div id="page-wrapper">

			<@html.head />

			<!-- Main -->
			<div id="main" class="wrapper style1" style="padding:0.5em 0 0.5em 0;">
				<div class="container">
					<header class="major" style="margin:0 0 1em 0;">
						<p> Men are like this book, you can read it.&nbsp;<small style="margin-left: 10px;font-size: smaller;">男人像本书，你可以去阅读他</small></p>
					</header>
					<section id="banner" style="height:646px;">
                    <div class="flipbook-viewport">
						<div class="container2" style="height:480px;">
							<div class="flipbook" style="margin-left: auto;  margin-right: auto;">
							<div style="background-image:url(${ctx}/style/turns/basic/pages/1.jpg)"></div>
							<div style="background-image:url(${ctx}/style/turns/basic/pages/2.jpg)"></div>
							<div style="background-image:url(${ctx}/style/turns/basic/pages/3.jpg)"></div>
							<div style="background-image:url(${ctx}/style/turns/basic/pages/4.jpg)"></div>
							<div style="background-image:url(${ctx}/style/turns/basic/pages/5.jpg)"></div>
							<div style="background-image:url(${ctx}/style/turns/basic/pages/6.jpg)"></div>
							<div style="background-image:url(${ctx}/style/turns/basic/pages/7.jpg)"></div>
							<div style="background-image:url(${ctx}/style/turns/basic/pages/8.jpg)"></div>
							<div style="background-image:url(${ctx}/style/turns/basic/pages/9.jpg)"></div>
							<div style="background-image:url(${ctx}/style/turns/basic/pages/10.jpg)"></div>
							<div style="background-image:url(${ctx}/style/turns/basic/pages/11.jpg)"></div>
							<div style="background-image:url(${ctx}/style/turns/basic/pages/12.jpg)"></div>
						    </div>
					    </div>
				    </div>
                    </section>
				</div>
			</div>
            
            <!--<div style="height:350px;width:100%;"></div>->
			<@html.footer />

		</div>

			<@style.js />
			<script type="text/javascript" src="${ctx}/style/turns/extras/modernizr.2.5.3.min.js"></script>
			<script type="text/javascript">
							
				function loadApp() {
					// Create the flipbook
					$('.flipbook').turn({
							// Width
							width:922,
							// Height
							height:750,
							// Elevation
							elevation: 50,
							// Enable gradients
							gradients: true
							// Auto center this flipbook
							//,autoCenter: true
					});
				}
				
				// Load the HTML4 version if there's not CSS transform
				yepnope({
					test : Modernizr.csstransforms,
					yep: ['${ctx}/style/turns/lib/turn.js'],
					nope: ['${ctx}/style/turns/lib/turn.html4.min.js'],
					both: ['${ctx}/style/turns/basic/css/basic.css'],
					complete: loadApp
				});
				
				</script>
	</body>
</html>