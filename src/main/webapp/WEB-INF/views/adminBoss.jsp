<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
   
   
    <link rel="stylesheet" href="<c:url value='/css/adminBoss.css' />"
    type="text/css">
    <link rel="stylesheet" href="<c:url value='/css/commonStyle.css' />"
	type="text/css">
    <!-- bootstrap -->
    <link
    href="<c:url value='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' />"
    rel="stylesheet">
    <!-- icon -->
    <link rel="stylesheet"
    href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css'/>" />
   <!-- jquery cnd -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    
  
		<!-- bootstrap -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js">
	</script>

</head>

<body>
	<div id="body">
		<div id="content">
			<!-- 引入共同的頁首 -->
			<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />
				

	<section class="info-box">
		<ul role="tablist">
			<li class="active" onclick="tab1(this)" role="tab" aria-selected="true" aria-setsize="3"
				aria-posinset="1" tabindex="0">會員管理</li>
			<li onclick="tab2()" role="tab" aria-selected="false" aria-setsize="3"
				aria-posinset="2" tabindex="0">銷售報表</li>
			<li onclick="tab3()" role="tab" aria-selected="false" aria-setsize="3"
				aria-posinset="3" tabindex="0">商品歸還明細</li>
		</ul>
		<div class="panels">
			<article class="active-panel" role="tabpanel" aria-hidden="false">
				<h2>會員列表</h2>

			</article>
			<article role="tabpanel" aria-hidden="true">
				<h2>報表管理</h2>

				<p>This tab hasn't got any Lorem Ipsum in it. But the content
					isn't very exciting all the same.</p>
			</article>
			<article role="tabpanel" aria-hidden="true">
				<h2>設備歸還</h2>

			</article>
		</div>
	</section>
	</div>
	<!-- 引入共同的頁尾 -->
	<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />
	</div>
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

		
		function tab1() {
			$.ajax({
				url : '${pageContext.request.contextPath}/adminBoss01Mark1',
				type : "POST",
				success : function(response) {
					console.log(response);
				}
			});
		}	

		function tab2() {
			$.ajax({
				url : '${pageContext.request.contextPath}/adminBoss01Mark2',
				type : "POST",
				success : function(response) {
					console.log(response);
				}
			});
		}	

		function tab3(obj) {
// 			$.ajax({
// 				url : '${pageContext.request.contextPath}/adminBoss01Mark3',
// 				type : "POST",
// 				success : function(response) {
// 				}
// 			});
			location.href ='${pageContext.request.contextPath}/rentOrderManage/${sessionScope.LoginOK}';
		}
	</script>
  
</body>
</html>