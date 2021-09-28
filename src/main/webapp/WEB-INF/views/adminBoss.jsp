<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en-US">
<!-- bootstrap -->
<link
	href="<c:url value='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' />"
	rel="stylesheet">
<!-- icon -->
<link rel="stylesheet"
	href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css'/>" />
<head>
<meta charset="utf-8">
<!-- 引入共同的頁首 -->
<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />
<title>Who Cares? We Care!</title>
<style>

/* General setup */
html {
	font-family: sans-serif;
}

* {
	box-sizing: border-box;
}

body {
	margin: 0;
}

/* info-box setup */
.info-box {
	width: 450px;
	height: 400px;
	margin: 0 auto;
}

/* styling info-box tabs */
ul[role="tablist"] {
	padding-left: 0;
	margin-top: 0;
}

li[role="tab"] {
	float: left;
	list-style-type: none;
	width: 150px;
	display: inline-block;
	line-height: 3;
	background-color: red;
	color: black;
	text-align: center;
}

li[role="tab"]:focus, li[role="tab"]:hover {
	background-color: #a60000;
	color: white;
}

li[role="tab"].active {
	background-color: #a60000;
	color: white;
}

/* styling info-box panels */
.info-box .panels {
	clear: both;
	position: relative;
	height: 352px;
}

.info-box article {
	background-color: #a60000;
	color: white;
	position: absolute;
	padding: 10px;
	height: 352px;
	top: 0;
	left: 0;
}

.info-box .active-panel {
	z-index: 1;
}
</style>
</head>
<body>

	<section class="info-box">
		<ul role="tablist">
			<li class="active" role="tab" aria-selected="true" aria-setsize="3"
				aria-posinset="1" tabindex="0">Tab 1</li>
			<li role="tab" aria-selected="false" aria-setsize="3"
				aria-posinset="2" tabindex="0">Tab 2</li>
			<li role="tab" aria-selected="false" aria-setsize="3"
				aria-posinset="3" tabindex="0">Tab 3</li>
		</ul>
		<div class="panels">
			<article class="active-panel" role="tabpanel" aria-hidden="false">
				<h2>The first tab</h2>

				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
					Pellentesque turpis nibh, porttitor nec venenatis eu, pulvinar in
					augue. Vestibulum et orci scelerisque, vulputate tellus quis,
					lobortis dui. Vivamus varius libero at ipsum mattis efficitur ut
					nec nisl. Nullam eget tincidunt metus. Donec ultrices, urna maximus
					consequat aliquet, dui neque eleifend lorem, a auctor libero turpis
					at sem. Aliquam ut porttitor urna. Nulla facilisi.</p>
			</article>
			<article role="tabpanel" aria-hidden="true">
				<h2>The second tab</h2>

				<p>This tab hasn't got any Lorem Ipsum in it. But the content
					isn't very exciting all the same.</p>
			</article>
			<article role="tabpanel" aria-hidden="true">
				<h2>The third tab</h2>

				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
					Pellentesque turpis nibh, porttitor nec venenatis eu, pulvinar in
					augue. And now an ordered list: how exciting!</p>

				<ol>
					<li>dui neque eleifend lorem, a auctor libero turpis at sem.</li>
					<li>Aliquam ut porttitor urna.</li>
					<li>Nulla facilisi</li>
				</ol>
			</article>
		</div>
	</section>

	<script>
		let tabs = document.querySelectorAll('.info-box li');
		let panels = document.querySelectorAll('.info-box article');

		for (let i = 0; i < tabs.length; i++) {
			let tab = tabs[i];
			setTabHandler(tab);
		}

		function setTab(e) {
			if (e.type === 'keypress' && e.keyCode !== 13) {
				return;
			}

			let tabPos = Number(this.getAttribute('aria-posinset')) - 1;
			for (let i = 0; i < tabs.length; i++) {
				if (tabs[i].getAttribute('class')) {
					tabs[i].removeAttribute('class');
				}

				tabs[i].setAttribute('aria-selected', 'false');
			}

			this.setAttribute('class', 'active');
			this.setAttribute('aria-selected', 'true');

			for (let i = 0; i < panels.length; i++) {
				if (panels[i].getAttribute('class')) {
					panels[i].removeAttribute('class');
				}

				panels[i].setAttribute('aria-hidden', 'true');
			}

			panels[tabPos].setAttribute('class', 'active-panel');
			panels[tabPos].setAttribute('aria-hidden', 'false');
		}

		function setTabHandler(tab) {
			tab.addEventListener('click', setTab);
			tab.addEventListener('keypress', setTab);
		}
	</script>
		<!-- 引入共同的頁尾 -->
		<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />
</body>
</html>
