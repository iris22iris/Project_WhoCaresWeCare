<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<!--   type="text/css" /> -->
<link rel="stylesheet" href="<c:url value='/css/commonStyle.css' />"
	type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
function clicka(obj){
	if('<%= session.getAttribute("LoginOK") %>' != 'null'){
		obj.href="<c:url value='_05_member_management' />";
		} else if(confirm('請先登入')) {
			obj.href="<c:url value='_05_login' />";
		}
}

</script>


<head>
<meta charset="UTF-8">

</head>
<!-- Main-header Start! -->
<nav class="navbar navbar-expand-lg navbar-light">
	<div class="container-fluid">
		<a class="navbar-brand w-25" href="<c:url value='/index' />"> <img
			src="<c:url value='/images/whiteLogo.png' />" alt="回到首頁" id="logo">
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
					href="<c:url value='' />" style="color: white;"> 關於我們</a></li>
				<li class="nav-item"><a class="nav-link textSize "
					href="<c:url value='/rentMenu' />" style="color: white;"> 租賃設備</a></li>
				<li class="nav-item"><a class="nav-link textSize "
					href="<c:url value='/buyMenu' />" style="color: white;"> 購物商城</a></li>
				<li class="nav-item"><a class="nav-link textSize "
					href="<c:url value='' />" style="color: white;"> 客服中心</a></li>
				<!-- 可以增加功能為登入才會顯示會員中心這個連結 -->
				<li class="nav-item"><a class="nav-link textSize" id="cb"
					style="color: white;" onclick="clicka(this)">會員中心</a></li>
			</ul>
			<!-- right menu -->
			<div class="nav-item">
				<a class="nav-link" href="<c:url value='#購物車' />"
					style="padding: 0px;"> <!-- 選擇租賃設備清單/購物車清單 --> <img
					src="<c:url value='/images/cartIcon.png' />" alt="購物車">
				</a>
			</div>

			<!-- 可以寫登入後換成會員圖片的功能 -->
			<div class="dropdown">
				<ul
					class="nav-item  navbar-nav  me-auto mb-lg-0 left-menu  dropdown-toggle"
					id="dropdownMenu2" data-bs-toggle="dropdown" aria-expanded="false">
					<a class="nav-link " href="" style="padding: 0px;"> <img
						src="<c:url value='/images/memberIcon.png' />" alt="會員"></a>
				</ul>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
					<li class="nav-item"><a class="nav-link textSize"
						href="<c:url value='_05_login' />" style="color: rgb(34, 4, 4);">
							登入</a></li>
					<li class="nav-item"><a class="nav-link textSize"
						href="_05member_management.html" style="color: rgb(34, 4, 4);">
							會員中心</a></li>
					<li class="nav-item"><a class="nav-link textSize"
						href="_05member_management.html" style="color: rgb(34, 4, 4);">
							訂單查詢</a></li>
					<div class="nav-item">
						<c:if test="${! empty LoginOK}">
							<a class="nav-link textSize" href="<c:url value='_05_logout'/>"
								style="color: rgb(34, 4, 4);"> 登出</a>
						</c:if>
					</div>
				</ul>
			</div>
			</dd>

			<!-- 			<div class="nav-item"> -->
			<%-- 				<a class="nav-link" href="<c:url value='_05_login' />" --%>
			<!-- 					style="padding: 0px;"> <img -->
			<%-- 					src="<c:url value='./images/memberIcon.png' />" alt="會員"> --%>
			<!-- 				</a> -->
			<!-- 			</div> -->

			<!-- 			<div class="nav-item"> -->
			<%-- 			<c:if test="${! empty LoginOK}"> --%>
			<%-- 				<a class="nav-link" href="<c:url value='_05_logout' />" --%>
			<!-- 					style="padding: 0px;"> <img -->
			<%-- 					src="<c:url value='./images/_05_logout.jpg' />" alt="登出"></a> --%>
			<%-- 			</c:if> --%>
			<!-- 			</div> -->

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