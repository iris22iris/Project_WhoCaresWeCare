<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- CSS -->
<link rel="stylesheet"
	href="<c:url value='/css/_06reservationQuery.css' />" type="text/css">
<link rel="stylesheet" href="<c:url value='/css/commonStyle.css' />"
	type="text/css">
<!-- bootstrap -->
<link
	href="<c:url value='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' />"
	rel="stylesheet">
<!-- icon -->
<link rel="stylesheet"
	href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css'/>" />
			<!-- 引入共同的頁首 -->
			<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />
<script>
	window.onload = function() {
		searchBox();
	}
	</script>
</head>
<title>預約序號查詢</title>
</head>
<body>
	<!-- main start -->
	<div id="body">
		<div id="content">

			

			<!-- title start-->
			<div class="title">
				<div class="reservationTitle ">
					<li><i class="fas fa-th-large p-3" aria-hidden="true"></i>
						預約紀錄查詢</li>
				</div>
				<!-- 搜尋start -->
				<div class="container search pb-3">
					<form class="row align-items-center">
						<div class="col-3  d-flex justify-content-end">
							<label>${customerBean.custName}您好，請輸預約編號查詢：</label>
						</div>
						<div class="col-3">
							<input type="search" class="form-control" id="enter"
								onkeyup="value=value.replace(/[^\d]/g,'')">
						</div>
						<div class="col-1">
							<button type="button" class="btn btn-warning"
								onClick="reservationSearch()">查詢</button>
						</div>
					</form>
				</div>
				<!-- 搜尋end -->
			</div>
			<!-- title end -->
			<div id="searchResult" class="my-3 text-center">${reservationNotFound}</div>
			<!-- 預約欄位 start -->
			<div class="container">


				<%-- 				<c:forEach var="reservation" items="${reservations}"> --%>
				<c:choose>
					<c:when test="${!empty reservation}">
						<div class="row list">
							<div class="col-md-3">
								<div class="reservationNum">
									<li class="numTitle">預約序號</li>
									<c:choose>
										<c:when test="${reservation.waitNum <= totalStock}">
											<li class="number">可租</li>
											<a class="link"
												href="<c:url value='/_03_rentProduct?id=${reservation.rentProductBean.prodId}' />"><i
												class="far fa-hand-point-right"></i> 前往預約 </a>
										</c:when>
										<c:otherwise>
											<li class="number">${reservation.waitNum}</li>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<div class="col-md-5">
								<div class="reservationInfo">
									<li>預約編號: <span>${reservation.category}${reservation.reservationId}</span></li>
									<li>預約日期: <span>${reservation.reserveDate}</span></li>
									<li>商品編號: <span>${reservation.rentProductBean.prodId}</span></li>
									<li>商品名稱: <span><a class="infoLink"
											href="<c:url value='/_03_rentProduct?id=${reservation.rentProductBean.prodId}' />">${reservation.rentProductBean.prodName}</a>
									</span>
									</li>
								</div>
							</div>
							<div class="col-md-4">
								<img class="img"
									src="<c:url value='/images/product/${reservation.rentProductBean.fileName}' />"
									alt="">
							</div>
						</div>
					</c:when>
				</c:choose>
				<%-- 				</c:forEach> --%>

			</div>
			<!-- 預約欄位 end -->


			<!-- btn start 返回-->
			<div
				class=" button col-12 pb-3 d-flex justify-content-center align-items-center">
				<button type="button" class="btn btn-secondary" onClick="back()">返回</button>
			</div>
			<!-- btn end 返回-->

		</div>

		<!-- 引入共同的頁尾 -->
		<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />

	</div>
	<!-- main end -->

	<!-- bootstrap -->
	<script
		src="<c:url value='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' />"></script>
	<script>
		function reservationSearch() {
			let reservationId = document.getElementById("enter").value;
			if (reservationId != null && reservationId != "") {
				location.href = "?reservationId="
						+ document.getElementById("enter").value;
			} else {
				document.getElementById("searchResult").innerHTML = "請輸入合法的預約編號";
			}
		}

		function back() {
			location = "<c:url value='/_05_member_management' />";
		}
	</script>
</body>
</html>