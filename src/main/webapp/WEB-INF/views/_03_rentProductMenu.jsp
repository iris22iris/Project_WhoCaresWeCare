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
	
<script src="<c:url value='/js/sortProducts.js' />"></script>

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
			<div class="rentProductMenu-title">
				<h2>
					<i class="fas fa-th-large px-3"></i>商品列表
				</h2>
			</div>
			<!-- 商品列表end -->

			<div class="row">
				<!-- Side-List Start -->
				<jsp:include page="/WEB-INF/fragment/rentSideMenu.jsp" />

				<!-- Product Start -->
				<div class="rentProduct col-9">
					<div class="container-fluid d-flex justify-content-end">
							<select name="sortType" onChange="sort(this)">
								<option selected disabled>請選擇排序條件</option>
								<option value="price desc">價格由高至低</option>
								<option value="stock desc">數量由高至低</option>
								<option value="price asc">價格由低至高</option>
								<option value="stock asc">數量由低至高</option>
							</select>
					</div>

					<div
						class="container-fluid d-flex flex-wrap justify-content-center">
						<div class="row">

							<c:forEach var='rentProductMap' items='${rentProductsMap}'>
								<div class="col-3 mt-3 " id="cardWidth">
									<div class="card text-center">
										<a href="<c:url value='/_03_rentProduct?id=${rentProductMap.key.prodId}' />"><img
											src="<c:url value='/images/product/${rentProductMap.key.fileName}' />"
											class="card-img-top" id="productImg" alt="..."></a>
										<div class="card-body">
											<h5 class="card-title">${rentProductMap.key.prodName}</h5>
											<div class="card-text mb-2">
												租金: ${rentProductMap.key.price}元/日<br>
												<c:choose>
													<c:when test="${rentProductMap.value != null}">
													庫存: ${rentProductMap.value}個<br>
													</c:when>
													<c:otherwise>
													庫存: ${rentProduct.stock}個<br>
													</c:otherwise>
												</c:choose>
											</div>

											<c:choose>
												<c:when test="${rentProductMap.value != null}">
													<c:choose>
														<c:when test="${rentProductMap.value > 0}">
															<a href="<c:url value='/_03_rentProduct?id=${rentProductMap.key.prodId}' />" class="btn btn-warning">前往租賃</a>
														</c:when>
														<c:otherwise>
															<a href="<c:url value='/_03_rentProduct?id=${rentProductMap.key.prodId}' />" class="btn btn-warning">前往預約</a>
														</c:otherwise>
													</c:choose>
												</c:when>

												<c:otherwise>
													<c:choose>
														<c:when test="${rentProduct.stock > 0}">
															<a href="<c:url value='/_03_rentProduct?id=${rentProductMap.key.prodId}' />" class="btn btn-warning">前往租賃</a>
														</c:when>
														<c:otherwise>
															<a href="<c:url value='/_03_rentProduct?id=${rentProductMap.key.prodId}' />" class="btn btn-warning">前往預約</a>
														</c:otherwise>
													</c:choose>
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