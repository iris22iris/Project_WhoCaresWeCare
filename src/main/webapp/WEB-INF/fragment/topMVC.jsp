<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <link href="<c:url value='/css/eDM.css' />" rel="stylesheet" --%>
<!--   type="text/css" /> -->
<%-- <script src="<c:url value='/javascript/jquery-1.9.1.js' />"></script> --%>
<%-- <link href="<c:url value='/javascript/eDM.js' />" rel="stylesheet" --%>
<!--   type="text/css" /> -->
<link rel="stylesheet" href="<c:url value='./css/index.css' />"
	type="text/css">

<!-- Main-header Start! -->
<nav class="navbar navbar-expand-lg navbar-light">
	<div class="container-fluid">
		<a class="navbar-brand w-25" href="index.html"> <img
			src="<c:url value='./images/whiteLogo.png' />" alt="回到首頁" id="logo">
		</a>

		<!-- rwd toggler start -->
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<!-- toggler end -->
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<!-- 有空的話可以寫個功能是到這一頁前面就會有拐杖ICON,其他的則不會有 -->
			<!-- left Menu -->
			<ul class="navbar-nav  me-auto mb-lg-0 left-menu">
				<li class="nav-item "><a class="nav-link textSize "
					href="_01aboutus.html" style="color: white;"> 關於我們</a></li>
				<li class="nav-item"><a class="nav-link textSize "
					href="_03rentProductMenu.html" style="color: white;"> 租賃設備</a></li>
				<li class="nav-item"><a class="nav-link textSize " href="#"
					style="color: white;"> 購物商城</a></li>
				<li class="nav-item"><a class="nav-link textSize "
					href="_02q_a.html" style="color: white;"> 客服中心</a></li>
				<!-- 可以增加功能為登入才會顯示會員中心這個連結 -->
				<li class="nav-item"><a class="nav-link textSize px-3"
					href="_05member_management.html" style="color: white;"> 會員中心</a></li>
			</ul>
			<!-- right menu -->
			<ul>
				<li class="nav-item"><a class="nav-link" href="#"
					style="padding: 0px;"> <!-- 選擇租賃設備清單/購物車清單 --> <img
						src="<c:url value='./images/cartIcon.png' />" alt="購物車"></a></li>
			</ul>
			<ul>
				<!-- 可以寫登入後換成會員圖片的功能 -->
				<li class="nav-item"><a class="nav-link" href="_05login.html"
					style="padding: 0px;"> <img
						src="<c:url value='./images/memberIcon.png' />" alt="會員"></a></li>
			</ul>

			<!-- Search -->
			<form class="d-flex">
				<input class="form-control me-2" type="search"
					placeholder="Search Product" aria-label="Search">
				<button class="btn btn-outline-warning" type="submit">
					<i class="fas fa-search"></i>
				</button>
			</form>
		</div>
	</div>
</nav>
<!-- Main-header End -->