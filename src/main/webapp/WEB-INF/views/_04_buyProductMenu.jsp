<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form"%>

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
<script src="<c:url value='/js/addFavorite.js' />"></script>
</head>

<body>
	<div id="body">
		<div id="content">
			<!-- 引入共同的頁首 -->
			<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />

			<!-- Main Start -->

			<!-- imgAd Start -->
			<div class="menuAd d-flex justify-content-center">
				<img src="<c:url value='/images/menuAd.png' />" class="imageAd"
					alt="ad3">
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
				<jsp:include page="/WEB-INF/fragment/buySideMenu.jsp" />

				<!-- Product Start -->
				<div class="buyProduct col-9">

					<div class="container-fluid d-flex justify-content-end">
						<form action="<c:url value='${request.getRequestURI}' />"
							method="GET">
							<select name="sortType">
								<option selected>請選擇排序條件</option>
								<option value="price desc">價格由高至低</option>
								<option value="stock desc">數量由高至低</option>
								<option value="price asc">價格由低至高</option>
								<option value="stock asc">數量由低至高</option>
							</select> <input name="pageNo" type=hidden value="${1}"> <input
								type="submit" class="btn btn-warning" value="送出">
						</form>
					</div>

					<div
						class="container-fluid d-flex flex-wrap justify-content-center">
						<div class="row">

							<c:forEach var='product' items='${products}'>
								<div class="col-3 mt-3 " id="cardWidth">
									<div class="card text-center">
										<a href="#"><img
											src="<c:url value='/images/product/${product.fileName}' />"
											class="card-img-top" id="productImg" alt="..."></a>
										<div class="card-body">
											<h5 class="card-title d-flex justify-content-around">
												${product.prodName}<i class="far fa-heart"
													onclick="track(this)"></i>
											</h5>
											<div class="card-text mb-2">價格: ${product.price}元</div>

											<form class="row-3 pt-2"
												action="<c:url value='/buyMenu/addCart/${product.prodId}' />"
												method="POST">
												<select name="prodQTY" class="form-select"
													style="width: 45%;" aria-label="Default select example">
													<option value="-1">數量</option>
													<c:forEach var="amount" begin="1" end="${product.stock}">
														<option value="amount">${amount}</option>
													</c:forEach>
												</select> <input type="submit" class="btn btn-warning" value="加入購物車" />
											</form>
										</div>
									</div>
								</div>
							</c:forEach>

						</div>
					</div>

					<div class="mt-3">
						<nav aria-label="Page navigation">
							<ul class="pagination justify-content-center">
								<c:choose>
									<c:when test="${pageNo == 1}">
										<li class="page-item disabled"><a class="page-link"
											tabindex="-1" aria-disabled="true">上一頁</a></li>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${empty sortType}">
												<li class="page-item"><a class="page-link"
													href="<c:url value='${request.getRequestURI}?pageNo=${pageNo - 1}' /> ">
														上一頁 </a></li>
											</c:when>
											<c:otherwise>
												<li class="page-item"><a class="page-link"
													href="<c:url value='${request.getRequestURI}?sortType=${sortType}&pageNo=${pageNo - 1}' /> ">
														上一頁 </a></li>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>

								<c:forEach var="currentPage" begin="1" end="${totalPages}">
									<c:choose>
										<c:when test="${currentPage == pageNo}">
											<li class="page-item active"><a class="page-link">${currentPage}</a></li>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${empty sortType}">
													<li class="page-item"><a class="page-link"
														href="<c:url value='${request.getRequestURI}?pageNo=${currentPage}' /> ">${currentPage}</a></li>
												</c:when>
												<c:otherwise>
													<li class="page-item"><a class="page-link"
														href="<c:url value='${request.getRequestURI}?sortType=${sortType}&pageNo=${currentPage}' /> ">${currentPage}</a></li>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</c:forEach>

								<c:choose>
									<c:when test="${pageNo == totalPages}">
										<li class="page-item disabled"><a class="page-link"
											tabindex="-1" aria-disabled="true">下一頁</a></li>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${empty sortType}">
												<li class="page-item"><a class="page-link"
													href="<c:url value='${request.getRequestURI}?pageNo=${pageNo + 1}' /> ">
														下一頁 </a></li>
											</c:when>
											<c:otherwise>
												<li class="page-item"><a class="page-link"
													href="<c:url value='${request.getRequestURI}?sortType=${sortType}&pageNo=${pageNo + 1}' /> ">
														下一頁 </a></li>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>

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
	</div>
	<!-- bootstrap -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js">
		
	</script>
</body>

</html>