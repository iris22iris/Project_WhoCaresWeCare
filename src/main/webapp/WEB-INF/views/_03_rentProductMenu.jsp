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
	<!-- 引入共同的頁首 -->
	<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />

	<!-- Main Start -->

	<!-- Slider-img Start -->
	<div class="container-fluid d-flex justify-content-center">
		<div id="carouselExampleInterval" class="carousel slide col-11"
			data-bs-ride="carousel">
			<div class="carousel-inner">
				<div class="carousel-item active" data-bs-interval="5000">
					<img src="<c:url value='/images/menuAd.png' />"
						class="d-block w-100" alt="ad1">
				</div>
				<div class="carousel-item" data-bs-interval="5000">
					<img src="<c:url value='/images/menuAd.png' />"
						class="d-block w-100 " alt="ad2">
				</div>
				<div class="carousel-item">
					<img src="<c:url value='/images/menuAd.png' />"
						class="d-block w-100" alt="ad3">
				</div>
			</div>
			<button class="carousel-control-prev" type="button"
				data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button"
				data-bs-target="#carouselExampleInterval" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Next</span>
			</button>
		</div>
	</div>

	<!-- Slider-img End -->

	<div class="row">
		<!-- Side-List Start -->
		<div class="col-2 ms-4" style="margin-top: 3cm;">
			<div class="text-center">
				<div class="d-flex flex-wrap justify-content-center my-3"
					style="background-color: white;">
					<div class="sideList-title mt-3">
						<h2 class="my-2">
							<i class="fas fa-hand-holding-medical"></i> 輔具類
						</h2>
					</div>
					<div class="sideList">
						<a href="#" style="color: #622d18; text-decoration: none;">拐杖-110cm</a><br>
						<a href="#" style="color: #622d18; text-decoration: none;">拐杖-120cm</a><br>
						<a href="#" style="color: #622d18; text-decoration: none;">拐杖-130cm</a><br>
						<a href="#" style="color: #622d18; text-decoration: none;">可折疊輪椅</a><br>
						<a href="#" style="color: #622d18; text-decoration: none;">不可折疊輪椅</a><br>
					</div>
					<div class="sideList-title">
						<h2 class="my-2">
							<i class="fas fa-shield-alt"></i> 護具類
						</h2>
					</div>
					<div class="sideList">
						<a href="#" style="color: #622d18; text-decoration: none;">護膝</a><br>
						<a href="#" style="color: #622d18; text-decoration: none;">護腰</a><br>
					</div>
					<div class="sideList-title">
						<h2 class="my-2">
							<i class="fas fa-clinic-medical"></i> 居家保健
						</h2>
					</div>
					<div class="sideList rounded-bottom">
						<a href="#" style="color: #622d18; text-decoration: none;">照護病床</a><br>
					</div>
				</div>
			</div>

			<div class="text-center">
				<div class="d-flex flex-wrap justify-content-center my-3"
					style="background-color: white;">
					<div class="sideList-title mt-3"
						style="background-color: chocolate;">
						<h2 class="my-2">
							<i class="fab fa-shopify"></i> 優惠活動
						</h2>
					</div>
					<div class="sideList rounded-bottom">
						<a href="#" style="color: #622d18; text-decoration: none;">特價專區</a><br>
					</div>
				</div>
			</div>

		</div>
		<!-- Side-List End -->


		<!-- Product Start -->
		<div class="rentProduct col-9">
			<div class="rentProductMenu-title">
				<h2>
					<i class="fas fa-th-large px-3"></i>商品列表
				</h2>
			</div>
			<div class="container-fluid d-flex justify-content-end">
				<select name="rentProductSort">
					<option value="傳送值">價格由低至高</option>
					<option value="傳送值">數量由低至高</option>
				</select>

			</div>
			<div class="container-fluid d-flex flex-wrap justify-content-center">
				<div class="row">
					<div class="col-3 mt-3">
						<div class="card text-center">

							<%-- 							<a href="#"><img src="${pageContext.request.contextPath}/images/product/A0001.jpg" --%>
							<!-- 								class="card-img-top" alt="..."></a> -->
							<a href="#"><img
								src="<c:url value='/images/product/A0001.jpg' />"
								class="card-img-top" alt="..."></a>
							<div class="card-body">
								<h5 class="card-title">產品名稱1</h5>
								<div class="card-text mb-2">
									租金: 150元/日<br> 庫存: 1個
								</div>
								<a href="#" class="btn btn-primary">前往租賃</a>
							</div>
						</div>
					</div>
					<div class="col-3 mt-3">
						<div class="card text-center">
							<a href="#"><img src="<c:url value='/images/product/A0002.jpg' />"
								class="card-img-top" alt="..."></a>
							<div class="card-body">
								<h5 class="card-title">產品名稱2</h5>
								<div class="card-text mb-2">
									租金: 120元/日<br> 庫存: 3個
								</div>
								<a href="#" class="btn btn-primary">前往租賃</a>
							</div>
						</div>
					</div>
					<div class="col-3 mt-3">
						<div class="card text-center">
							<a href="#"><img src="<c:url value='/images/product/A0003.jpg' />"
								class="card-img-top" alt="..."></a>
							<div class="card-body">
								<h5 class="card-title">產品名稱3</h5>
								<div class="card-text mb-2">
									租金: 60元/日<br> 庫存: 4個
								</div>
								<a href="#" class="btn btn-primary">前往租賃</a>
							</div>
						</div>
					</div>
					<div class="col-3 mt-3">
						<div class="card text-center">
							<a href="#"><img src="<c:url value='/images/product/A0004.jpg' />"
								class="card-img-top" alt="..."></a>
							<div class="card-body">
								<h5 class="card-title">產品名稱4</h5>
								<div class="card-text mb-2">
									租金: 100元/日<br> 庫存: 2個
								</div>
								<a href="#" class="btn btn-primary">前往租賃</a>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-3 mt-3">
						<div class="card text-center">
							<a href="#"><img src="<c:url value='/images/product/A0005.jpg' />"
								class="card-img-top" alt="..."></a>
							<div class="card-body">
								<h5 class="card-title">產品名稱4</h5>
								<div class="card-text mb-2">
									租金: 100元/日<br> 庫存: 5個
								</div>
								<a href="#" class="btn btn-primary">前往租賃</a>
							</div>
						</div>
					</div>
					<div class="col-3 mt-3">
						<div class="card text-center">
							<a href="#"><img src="<c:url value='/images/product/A0005.jpg' />"
								class="card-img-top" alt="..."></a>
							<div class="card-body">
								<h5 class="card-title">產品名稱5</h5>
								<div class="card-text mb-2">
									租金: 50元/日<br> 庫存: 3個
								</div>
								<a href="#" class="btn btn-primary">前往租賃</a>
							</div>
						</div>
					</div>
					<div class="col-3 mt-3">
						<div class="card text-center">
							<a href="#"><img src="<c:url value='/images/product/A0005.jpg' />"
								class="card-img-top" alt="..."></a>
							<div class="card-body">
								<h5 class="card-title">產品名稱6</h5>
								<div class="card-text mb-2">
									租金: 30元/日<br> 庫存: 1個
								</div>
								<a href="#" class="btn btn-primary">前往租賃</a>
							</div>
						</div>
					</div>
					<div class="col-3 mt-3">
						<div class="card text-center">
							<a href="#"><img src="<c:url value='/images/product/A0005.jpg' />"
								class="card-img-top" alt="..."></a>
							<div class="card-body">
								<h5>產品名稱6</h5>
								<div class="card-text mb-2">
									租金: 20元/日<br> 庫存: 2個
								</div>
								<a href="#" class="btn btn-primary">前往租賃</a>
							</div>
						</div>
					</div>
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
	<!-- Main End -->

	<!-- 引入共同的頁尾 -->
	<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />

	<!-- bootstrap -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js">
	</script>
</body>

</html>