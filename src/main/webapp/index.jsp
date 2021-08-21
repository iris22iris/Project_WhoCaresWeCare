<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value='./css/index.css' />"
	type="text/css">
<link rel="stylesheet" href="<c:url value='./css/commonStyle.css' />"
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
	
	<title>Who Cares? We Care!</title>
</head>
<body id="body">

	<!-- 廣告圖片大小&button顏色 還須調整 -->
	<!-- Slider-img Start! -->
	<div id="carouselExampleInterval" class="carousel slide"
		data-bs-ride="carousel">
		<div class="carousel-inner">
			<div class="carousel-item active" data-bs-interval="5000">
				<img src="<c:url value='./images/indexAd1.jpg' />"
					class="d-block w-100" alt="ad1">

			</div>
			<div class="carousel-item" data-bs-interval="5000">
				<img src="<c:url value='./images/indexAd2.jpg' />"
					class="d-block w-100" alt="ad2">

			</div>
			<div class="carousel-item">
				<img src="<c:url value='./images/indexAd3.jpg' />"
					class="d-block w-100" alt="ad3">
			</div>
		</div>
		<button class="carousel-control-prev" type="button"
			data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#carouselExampleInterval" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Next</span>
		</button>
	</div>
	<!-- Slider-img End -->

	<div class="newsAndProduct">
		<!-- News Start! -->
		<div class="news px-4">
			<div class="newsAndProduct-Head">
				<h1>
					<i class="fas fa-th-large px-2"></i>最新消息
				</h1>
			</div>
			<!-- 功能：從最新消息裡抓第一篇的Head & Content並限制顯示字數 -->
			<div id="newsHead">2021年公益團體聯歡晚會</div>
			<div id="newsContent">護Cares?WeCare!為2021年公益團體聯歡晚會贊助廠商，
				活動當天提供100盒與中衛獨家聯名的口罩，給前一百名捐款的善心人士。活動資訊…</div>
			<div id="newsBotton" class="d-md-flex justify-content-md-end">
				<!-- 按鈕顏色需調整 -->
				<button type="button" class="btn btn-outline-light mt-3" href="#">
					看更多...</button>
			</div>
		</div>
		<!-- News End -->

		<!-- product-card Start! -->
		<div class="product-card pe-4">
			<div class="newsAndProduct-Head">
				<h1>
					<i class="fas fa-th-large px-2"></i>精選商品
				</h1>
			</div>
			<div class="container pb-4">
				<div class="row px-6">
					<div class="col-12 col-sm-6 col-md-3 mt-2">
						<div class="card" style="width: 105%;">
							<a href="#商品網址"> <img
								src="<c:url value='./images/product/A0002.jpg' />"
								class="card-img-top" alt="...">
							</a>
							<div class="card-body">
								<h5 class="card-title">輔助功能型摺疊輪椅</h5>
								<div class="row-2">
									<p class="card-text">$8888</p>
								</div>
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
					<div class="col-12 col-sm-6 col-md-3 mt-2">
						<div class="card" style="width: 105%;">
							<a href="#商品網址"> <img
								src="<c:url value='./images/product/A0001.jpg' />"
								class="card-img-top" alt="...">
							</a>
							<div class="card-body">
								<h5 class="card-title">輔助功能型摺疊輪椅</h5>
								<div class="row-2">
									<p class="card-text">$8888</p>
								</div>
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
					<div class="col-12 col-sm-6 col-md-3 mt-2">
						<div class="card" style="width: 105%;">
							<a href="#商品網址"> <img
								src="<c:url value='./images/product/A0003.jpg' />"
								class="card-img-top" alt="...">
							</a>
							<div class="card-body">
								<h5 class="card-title">輔助功能型摺疊輪椅</h5>
								<div class="row-2">
									<p class="card-text">$8888</p>
								</div>
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
					<div class="col-12 col-sm-6 col-md-3 mt-2">
						<div class="card" style="width: 105%;">
							<a href="#商品網址"> <img
								src="<c:url value='./images/product/A0004.jpg' />"
								class="card-img-top" alt="...">
							</a>
							<div class="card-body">
								<h5 class="card-title">輔助功能型摺疊輪椅</h5>
								<div class="row-2">
									<p class="card-text">$8888</p>
								</div>
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
					<div class="col-12 col-sm-6 col-md-3 mt-2">
						<div class="card" style="width: 105%;">
							<a href="#商品網址"> <img
								src="<c:url value='./images/product/A0005.jpg' />"
								class="card-img-top" alt="...">
							</a>
							<div class="card-body">
								<h5 class="card-title">輔助功能型摺疊輪椅</h5>
								<div class="row-2">
									<p class="card-text">$8888</p>
								</div>
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
					<div class="col-12 col-sm-6 col-md-3 mt-2">
						<div class="card" style="width: 105%;">
							<a href="#商品網址"> <img
								src="<c:url value='./images/product/B0001.png' />"
								class="card-img-top" alt="...">
							</a>
							<div class="card-body">
								<h5 class="card-title">輔助功能型摺疊輪椅</h5>
								<div class="row-2">
									<p class="card-text">$8888</p>
								</div>
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
					<div class="col-12 col-sm-6 col-md-3 mt-2">
						<div class="card" style="width: 105%;">
							<a href="#商品網址"> <img
								src="<c:url value='./images/product/A0003.jpg' />"
								class="card-img-top" alt="...">
							</a>
							<div class="card-body">
								<h5 class="card-title">輔助功能型摺疊輪椅</h5>
								<div class="row-2">
									<p class="card-text">$8888</p>
								</div>
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
					<div class="col-12 col-sm-6 col-md-3 mt-2">
						<div class="card" style="width: 105%;">
							<a href="#商品網址"> <img
								src="<c:url value='./images/product/A0004.jpg' />"
								class="card-img-top" alt="...">
							</a>
							<div class="card-body">
								<h5 class="card-title">輔助功能型摺疊輪椅</h5>
								<div class="row-2">
									<p class="card-text">$8888</p>
								</div>
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
				</div>
			</div>
		</div>
		<!-- Product-card End -->
	</div>





	<!-- 引入共同的頁尾 -->
	<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />



</body>
</html>
