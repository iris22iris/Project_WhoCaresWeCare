<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<c:url value='/css/_04buyProductMenu.css' />">
<link rel="stylesheet" href="<c:url value='/css/commonStyle.css' />"
	type="text/css">

<title>購物商城</title>

<!-- bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- icon -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
<!-- sweetAlert -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script src="<c:url value='/js/_04buyProductMenu.js' />"></script>
</head>

<body id="body">
	<div id="body">
		<!-- 引入共同的頁首 -->
		<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />

		<!-- Main Start -->

		<!-- imgAd Start -->
		<div class="menuAd d-flex justify-content-center">
			<img src="<c:url value='/images/menuAd.png' />" class="imageAd" alt="ad3">
		</div>
		<!-- imgAd End -->

		<!-- 商品列表start -->
		<div class="buyProductMenu-title">
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
									<a href="<c:url value='/buyMenu/${productType.prodType}' />">${productType.prodName}</a><br>
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>

				<div class="text-center">
					<div class="sideList-title mt-4 discount-title">優惠活動</div>
					<div class="sideList">
						<a href="#">福利品9折</a><br>
						<a href="#">父親節滿888元打8折</a><br>
					</div>
				</div>
			</div>

			<!-- Side-List End -->


			<!-- Product Start -->
			<div class="buyProduct col-9">
				<div class="container-fluid d-flex justify-content-end">
					<select name="buyProductSort">
						<option value="">請選擇排序條件</option>
						<option value="asc">價格由高至低</option>
						<option value="desc">價格由低至高</option>
					</select>
				</div>

				<div class="container-fluid d-flex flex-wrap justify-content-center">
					<div class="row">

						<c:forEach var='product' items='${products}'>
							<div class="col-3 mt-3 " id="cardWidth">
								<div class="card text-center">
									<a href="#"><img src="<c:url value='/images/product/${product.fileName}' />"
										class="card-img-top" id="productImg" alt="..."></a>
									<div class="card-body">
										<h5 class="card-title d-flex justify-content-around">
											${product.prodName}<i class="far fa-heart"
												onclick="track(this)"></i>
										</h5>
										<div class="card-text mb-2">價格: ${product.price}元</div>
										<div class="row-3 pt-2">
											<select class="form-select" style="width: 45%;"
												aria-label="Default select example">
												<option selected>數量</option>
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
											</select> <a href="#" class="btn btn-warning">加入購物車</a>
										</div>
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