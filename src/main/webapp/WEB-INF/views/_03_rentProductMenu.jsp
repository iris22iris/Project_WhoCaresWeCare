<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<c:url value='/css/_03rentProductMenu.css' />">
<link rel="stylesheet" href="<c:url value='/css/commonStyle.css' />"
	type="text/css">

<title>租賃設備</title>

<!-- bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- icon -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
</head>

<body id="body">
	<div id="body">
		<!-- 引入共同的頁首 -->
		<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />

		<!-- Main Start -->

		<!-- imgAd Start -->
		<div class="menuAd d-flex justify-content-center">
			<img src="/images/menuAd.png" class="imageAd" alt="ad3">
		</div>
		<!-- imgAd End -->

		<!-- 商品列表start -->
		<div class="rentProductMenu-title">
			<h2>
				<i class="fas fa-th-large px-3"></i>商品列表
			</h2>
		</div>
		<!-- 商品列表end -->

		<div class="row">
			<!-- Side-List Start -->
			<div class="col-2 ms-4">
				<div style="height: 60px;"></div>
				<div class="text-center">
					<c:forEach var="productType" items="${productTypes}" varStatus="vs">
						<c:choose>
							<c:when test="${productType.prodType.length() == 1}">
								<c:choose>
									<c:when test="${vs.first}">
										<div class="sideList-title">${productType.prodName}類</div>
									</c:when>
									<c:otherwise>
										<div class="sideList-title">${productType.prodName}類</div>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<div class="sideList">
									<a href="<c:url value='/rentMenu/${productType.prodType}' />">${productType.prodName}</a><br>
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>

				<div class="text-center">
					<div class="sideList-title mt-4 discount-title">優惠活動</div>
					<div class="sideList">
						<a href="#">特價專區</a><br>
					</div>
				</div>
			</div>

			<!-- Side-List End -->


			<!-- Product Start -->
			<div class="rentProduct col-9">
				<div class="container-fluid d-flex justify-content-end">
					<select name="rentProductSort">
						<option value="">請選擇排序條件</option>
						<option value="asc">價格由高至低</option>
						<option value="desc">價格由低至高</option>
					</select>
				</div>

				<div class="container-fluid d-flex flex-wrap justify-content-center">
					<div class="row">

						<c:forEach var='rentProduct' items='${rentProducts}'>
							<div class="col-3 mt-3">
								<div class="card text-center">
									<a href="#"><img
										src="<c:url value='${rentProduct.coverImage}' />"
										class="card-img-top" alt="..."></a>
									<div class="card-body">
										<h5 class="card-title">${rentProduct.prodName}</h5>
										<div class="card-text mb-2">
											租金: ${rentProduct.price}元/日<br>
										</div>
										<c:choose>
											<c:when test="${rentProduct.stock > 0}">
												<a href="#" class="btn btn-warning">前往租賃</a>
											</c:when>
											<c:otherwise>
												<a href="#" class="btn btn-warning">前往預約</a>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>
						</c:forEach>

					</div>
				</div>

				<div class="mt-3">
					<nav aria-label="Page navigation">
						<ul class="pagination justify-content-center">
							<li class="page-item disabled"><a class="page-link" href="#"
								tabindex="-1" aria-disabled="true">上一頁</a></li>
							<li class="page-item active"><a class="page-link">1</a></li>
							<li class="page-item"><a class="page-link" href="#">2</a></li>
							<li class="page-item"><a class="page-link" href="#">3</a></li>
							<li class="page-item"><a class="page-link" href="#">下一頁</a></li>
						</ul>
					</nav>
				</div>
			</div>
		</div>
		<!-- Product End -->
	</div>
	<!-- Main End -->
	<!-- 引入共同的頁尾 -->
	<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />

	<!-- bootstrap -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js">
		
	</script>
</body>

</html>