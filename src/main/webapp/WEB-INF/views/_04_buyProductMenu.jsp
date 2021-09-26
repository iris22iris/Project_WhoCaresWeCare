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
	href="<c:url value='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' />"
	rel="stylesheet">
<!-- icon -->
<link rel="stylesheet"
	href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css'/>" />
<!-- sweetAlert -->
<!-- sweet alert -->
<script
	src="<c:url value='https://cdn.jsdelivr.net/npm/sweetalert2@9'/>"></script>
<%-- 	<script src="<c:url value='/js/addFavorite.js' />"></script> --%>
<script src="<c:url value='/js/sortProducts.js' />"></script>
			<!-- 引入共同的頁首 -->
			<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />

<script>
	window.onload = function() {
			searchBox();
		$.ajax({
			url: '/Whocares/quereyFavoriteBYCustomerID',
			type: "POST",
			data: {
				FK_Customer_ID:<%=session.getAttribute("LoginOK")%>,
				},
			success: function(response) { 
				response.forEach(function (item) {
					$("#heartFavorite" + item.fk_Product_ID).attr("class","fas fa-heart");
		           });
			},
			error: function() { }
		});
	}

	function addCart(){
		Swal.fire({
			position : 'center',
			icon : 'success',
			title : '商品已加入購物車',
			showConfirmButton : false,
			timer : 3000
		})
	}
	
	function track(heart, prodId) {
		if(<%=session.getAttribute("LoginOK")%> != null) {
			if (heart.className == "far fa-heart") {
				$.ajax({
					url: '/Whocares/addFavorite',
					type: "POST",
					data: {
						FK_Customer_ID:<%=session.getAttribute("LoginOK")%>,
						FK_Product_ID:prodId,
						},
					success: function() { 
						heart.className = "fas fa-heart";
						Swal.fire({
							position : 'center',
							icon : 'success',
							title : '商品已加入追蹤清單',
							showConfirmButton : false,
							timer : 1000
						})
					},
					error: function() { }

				});
				
			} else {
				$.ajax({
					url: '/Whocares/deleteFavorite',
					type: "POST",
					data: {
						FK_Customer_ID:<%=session.getAttribute("LoginOK")%>,
						FK_Product_ID:prodId,
						},
					success: function() { 
						heart.className = "far fa-heart";
						Swal.fire({
							position : 'center',
							icon : 'error',
							title : '商品已取消追蹤',
							showConfirmButton : false,
							timer : 1000
						})
					},
					error: function() { }

				});
				
			}
		} else {
			if (confirm('請先登入') == true){ 
				window.location.href='_05_login'
			} else {
				return false; 
				}
		}
	}
</script>

</head>

<body>
	<div id="body">
		<div id="content">
			

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

							<c:forEach var='product' items='${products}'>
								<div class="col-3 mt-3 " id="cardWidth">
									<div class="card text-center">
										<c:if test="${!empty product.promotionBean}">
											<div class="card-promotion">${product.promotionBean.promoTag}</div>
										</c:if>
										<a href="<c:url value='/_04_productPage?id=${product.prodId}' />"><img
											src="<c:url value='/images/product/${product.fileName}' />"
											class="card-img-top" id="productImg" alt="..."></a>
										<div class="card-body">
											<h5 class="card-title d-flex justify-content-around">
												${product.prodName}<i class="far fa-heart"
													id="heartFavorite${product.prodId}"
													onclick="track(this,${product.prodId})"></i>
											</h5>
											<div class="card-text mb-2">價格: ${product.price}元</div>

											<form class="row-3 pt-2"
												action="<c:url value='/buyMenu/addCart/${product.prodId}' />"
												method="POST">
												<select name="prodQTY" class="form-select"
													style="width: 45%;" aria-label="Default select example">
													<option selected value="1">數量</option>
													<c:forEach var="amount" begin="1" end="${product.stock}">
														<option value="${amount}">${amount}</option>
													</c:forEach>
												</select>
												<Input type='hidden' name='prodId' value='${product.prodId}'>
												<input type="submit" class="btn btn-warning" value="加入購物車" onclick="addCart()" />
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