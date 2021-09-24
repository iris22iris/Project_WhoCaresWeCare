<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<!--   type="text/css" /> -->
<link rel="stylesheet" href="<c:url value='/css/commonStyle.css' />"
	type="text/css">

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

<!-- <script -->
<!-- 	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->
<script>
	function clicka(obj, objUrl) {
		if ('${sessionScope.LoginOK}' == 'null' || '${sessionScope.LoginOK}' == null || '${sessionScope.LoginOK}' == '') {
			if (confirm('請先登入')) {
				obj.href = '${pageContext.request.contextPath}/_05_login';
			} else {
				obj.href = '${pageContext.request.contextPath}/index';
			}
		} else {
			obj.href = '${pageContext.request.contextPath}/' + objUrl;
		}
	}
	
// 	window.onload = function() {
	function searchBox(){
		if('${sessionScope.searchProduct}'){		
			$('#searchProduct').autocomplete({
				select : function(event, ui) {
					"use strict";
					console.log('select event called');
				},
				source : '${sessionScope.searchProduct}'.substring(1,'${sessionScope.searchProduct}'.length-1).split(', '),
				minLength : 1,
				change : function(event, ui) {
					if (ui.item) {
						console.log("ui.item.value: " + ui.item.value);
					} else {
						console.log("ui.item.value is null");
					}
					console.log("this.value: " + this.value);
				}
			});
		} else {
			$.ajax({
				url : '${pageContext.request.contextPath}/searchProduct',
				type : "GET",
				success: function(data) { 
					if(data){
						$('#searchProduct').autocomplete({
							select : function(event, ui) {
								"use strict";
								console.log('select event called');
							},
							source : data,
							minLength : 1,
							change : function(event, ui) {
								if (ui.item) {
									console.log("ui.item.value: " + ui.item.value);
								} else {
									console.log("ui.item.value is null");
								}
								console.log("this.value: " + this.value);
							}
						});
					}
				},
			});
		}
	}
// }

	

	function searchProductMenu(obj, objUrl) {
		if ($('#searchProduct').val()) {
			window.location.href = '${pageContext.request.contextPath}/_01_searchResult/'
					+ $('#searchProduct').val();

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
					href="<c:url value='/dmMenu' />" style="color: white;"> 最新消息</a></li>
				<li class="nav-item"><a class="nav-link textSize "
					href="<c:url value='/rentMenu' />" style="color: white;"> 租賃設備</a></li>
				<li class="nav-item"><a class="nav-link textSize "
					href="<c:url value='/buyMenu' />" style="color: white;"> 購物商城</a></li>
				<li class="nav-item"><a class="nav-link textSize " id="cb"
					href=""
					style="color: white;"
					onclick='clicka(this,"_05_member_management")'> 會員中心</a></li>
				<!-- 可以增加功能為登入才會顯示會員中心這個連結 -->
				<%-- <li class="nav-item"><a class="nav-link textSize " id="cb"
					href=""
					style="color: white;"
					onclick='clicka(this,"_05_member_management")'> 會員中心</a></li>
					--%>
			</ul>


			<div class="dropdown">
				<ul
					class="nav-item  navbar-nav  me-auto mb-lg-0 left-menu dropdown-toggle"
					id="dropdownMenu1" data-bs-toggle="dropdown" aria-expanded="false">
					<li><a class="nav-link" style="padding: 0px;"> <img
							src="${pageContext.request.contextPath}/images/cartIcon.png"
							alt="購物車">
					</a></li>
				</ul>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
					<li class="nav-item"><a class="nav-link textSize"
						href="${pageContext.request.contextPath}/_03_rentItemList">租賃設備清單</a>
					</li>
					<li class="nav-item"><a class="nav-link textSize"
						href="${pageContext.request.contextPath}/_04_shoppingCart">商品購物車</a>
					</li>
				</ul>
			</div>

			<div class="dropdown">
				<ul
					class="nav-item  navbar-nav  me-auto mb-lg-0 left-menu  dropdown-toggle"
					id="dropdownMenu2" data-bs-toggle="dropdown" aria-expanded="false">
					<c:choose>
						<c:when test="${empty LoginOK}">
							<a class="nav-link " href="" style="padding: 0px;"> <img
								src="${pageContext.request.contextPath}/images/memberIcon.png"
								alt="會員">
							</a>
						</c:when>
						<c:otherwise>
							<a class="nav-link custImg" href="" style="padding: 0px;"> <img
								src="${pageContext.request.contextPath}/getMemberImg?custId=${sessionScope.LoginOK}">
							</a>
						</c:otherwise>
					</c:choose>
				</ul>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
					<li class="nav-item"><c:if test="${ empty LoginOK}">
							<a class="nav-link textSize"
								href="${pageContext.request.contextPath}/_05_login">登入</a>
						</c:if></li>
					<li class="nav-item"><a class="nav-link textSize"
						href="<c:url value='/_05_member_management'/>"
						onclick='clicka(this,"_05_member_management")'> 會員中心 </a></li>
					<li class="nav-item"><a class="nav-link textSize"
						href="<c:url value='/_02_contactUs'/>"
						onclick='clicka(this,"_02_contactUs")'> 聯絡我們</a></li>
					<c:if test="${! empty LoginOK}">
						<a class="nav-link textSize"
							href="${pageContext.request.contextPath}/_05_logout"> 登出 </a>
					</c:if>
				</ul>
			</div>


			<!-- Search -->
			<form class="d-flex">
				<input id="searchProduct" class="form-control me-2" type="text"
					placeholder="Search Product" aria-label="Search">
				<button id="searchSumbit" type="button" class="btn btn-outline-warning"
					onclick='searchProductMenu(this,"_01_searchResult")'>
					<i class="fas fa-search"></i>
				</button>
			</form>

		</div>
	</div>
</nav>
<!-- Main-header End -->