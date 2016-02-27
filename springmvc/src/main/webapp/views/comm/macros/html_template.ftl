<#macro head> 
<!-- Header -->
<header id="header">
	<h1 id="logo"><a href="${ctx}/index.ftl">FOREVER LOVE</a></h1>
	<h2 ></h2>
	<nav id="nav">
		<ul>
			<li><a href="${ctx}/index.ftl" class="button">首页</a></li>
			<li>
				<a href="#"  class="button" >倾城之恋</a>
				<ul>
					<li><a href="${ctx}/user/men">男人世界</a></li>
					<li><a href="${ctx}/user/men">女人天堂</a></li>
					<li>
						<a href="#">曾经的美</a>
						<ul>
							<li><a href="#">南山偶遇</a></li>
							<li><a href="#">梧桐故事</a></li>
							<li><a href="#">荔香的夜色</a></li>
							<li><a href="#">你的我</a></li>
						</ul>
					</li>
				</ul>
			</li>
			<li><a href="${ctx}/views/elements.ftl"  class="button">非诚勿扰</a></li>
            <li><a href="${ctx}/views/elements.ftl"  class="button" >童话世界</a></li>
			<li><a href="#" >登录</a>/<a href="#">注册</a></li>
		</ul>
	</nav>
</header>
</#macro>

<#macro footer>
<!-- Footer -->
<footer id="footer">
	<ul class="icons">
		<li><a href="#" class="icon alt fa-twitter"><span class="label">Twitter</span></a></li>
		<li><a href="#" class="icon alt fa-facebook"><span class="label">Facebook</span></a></li>
		<li><a href="#" class="icon alt fa-linkedin"><span class="label">LinkedIn</span></a></li>
		<li><a href="#" class="icon alt fa-instagram"><span class="label">Instagram</span></a></li>
		<li><a href="#" class="icon alt fa-github"><span class="label">GitHub</span></a></li>
		<li><a href="#" class="icon alt fa-envelope"><span class="label">Email</span></a></li>
	</ul>
	<ul class="copyright">
		<li>&copy; Untitled. All rights reserved.</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
	</ul>
</footer>
</#macro>
