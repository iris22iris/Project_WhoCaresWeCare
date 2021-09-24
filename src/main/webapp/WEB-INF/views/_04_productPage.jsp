<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 數字格式化標籤 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
<title>商品頁面: ${product.prodName}</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<!-- bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- jquery cnd -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- css -->
<link rel="stylesheet" href="<c:url value='/css/_04productPage.css' />">

<link rel="stylesheet" href="<c:url value='/css/commonStyle.css' />"
	type="text/css">

<!-- icon -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />

<!-- 圖片切換 -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

<!-- 引入script -->
<script src="<c:url value='/js/_04productPage.js' />"></script>
		<!-- 引入共同的頁首 -->
		<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />
<script>
	window.onload = function() {
		searchBox();
	}
</script>
</head>

<body>
	<div id="body">
		<div id="content">
	
		<!-- header start -->
		
		<!-- header end -->

		<!-- main start -->
		<div class="container-fluid d-flex">
<!-- 		<div class="allPage"> -->


		<!-- Side-List Start -->
		<jsp:include page="/WEB-INF/fragment/buySideMenu.jsp"/>
	
	<!-- main-right-side start -->
			<div class="productPage col-9 ">

				<!-- 商品分類title start -->
				<div class="catagory">
					<i class="fas fa-th-large px-2"></i>
					${maincategorys[0].prodName}類
					<i class="fas fa-angle-right"></i>
					
					<a href="<c:url value='/buyMenu/${product.productTypeBean.prodType}' />">					
						${product.productTypeBean.prodName}</a>
				</div>
				<!-- 商品分類title end -->

				<!-- 商品圖片&名稱 start -->
				<div class="productShell col-11">
					<div class="pictureandinfo">
						<div class="productPicture col-4">
							<div id="carouselExampleDark"
								class="carousel carousel-dark slide" data-bs-ride="carousel">
								<!-- 商品大圖片 start -->

								<div class="carousel-inner" style="border-radius: 10px">
									<div class="carousel-item active" data-bs-interval="10000">
										<img src="<c:url value='/images/product/${product.fileName}' />"
											class="img1" alt="...">
										<div class="carousel-caption d-none d-md-block"></div>
									</div>

									<div class="carousel-item" data-bs-interval="2000">
										<img src="<c:url value='/images/${product.fileName2}' />"
											class="img2" alt="...">
										<div class="carousel-caption d-none d-md-block"></div>
									</div>

									<div class="carousel-item">
										<img src="<c:url value='/images/${product.fileName3}' />"
											class="img3" alt="...">
										<div class="carousel-caption d-none d-md-block"></div>
									</div>
								</div>
								<!-- 商品大圖片 end -->
							</div>
							<!-- 商品小圖片 start -->
							<div  class="smallImage">
                                <div>
							<input type="image" data-bs-target="#carouselExampleDark"
								data-bs-slide-to="0" name="submit_Btn" id="submit_Btn"
								src="<c:url value='/images/product/${product.fileName}' />"
								style="border-radius: 10px"> <input type="image"
								data-bs-target="#carouselExampleDark" data-bs-slide-to="1"
								name="submit_Btn" id="submit_Btn"
								src="<c:url value='/images/forProductPage_2.png' />"
								style="border-radius: 10px"> <input type="image"
								data-bs-target="#carouselExampleDark" data-bs-slide-to="2"
								name="submit_Btn" id="submit_Btn"
								src="<c:url value='/images/forProductPage_3.png' />"
								style="border-radius: 10px">
						</div>
					</div>
						<!-- 商品小圖片 end -->
				</div>

					<!-- 商品基本資訊 start -->
						<div class="productInfo col-7">
							<form id="form1" name="form1" method="GET" action="">
								<div class="smallStyle col-12">
									編號${product.prodId}
								</div>
								
								<div class="col-12">
								<h2>${product.prodName}</h2>
								</div>

								<div class="smallStyle">衛部醫器製壹字第000936號</div> 
								
								<c:if test="${!empty product.promotionBean}">
									<div>
										<span id="commodityname" >
											${product.promotionBean.promoTag}
										</span>
									</div>
								</c:if>
								

								<div class="col-12 hidden"></div>

								<div class="normalStyle">
									<div class="col-4">
									購買價格:</div> 
									<div class="col-4 price">
									${product.price}元
									</div>
								</div>

								<div class="normalStyle">
									<div class="col-4">
									選購數量:</div>
									<div class="col-4">
									<input type="number" min="1" max="10" value="1">
									</div>
								</div>

								<div class="normalStyle">
									<div class="col-4">
									庫存數量:</div>
									<div class="col-4">
									${product.stock}個 
									</div>
								</div> 	
																																					
									<div class="submitBtn col-8">	   															 														  		
							
									<button class="btn btn-outline-warning ms-3">
										加入購物車
									</button>
									</div>
								
							</form>
							</div>
						</div>
						<!-- 商品基本資訊 end -->
					</div>
					
						<!-- 商品詳情 start -->
				<div class="productContent col-11">
					<div class="contentTitle">
						商品詳情
					</div>
					<p>${productDescription}</p>
					
					<img src="<c:url value='/images/old.jpg' />" alt="">
					<br>
					
					
				</div>
				<!-- 商品詳情 end -->
					
				

			



				<!-- 評價顯示 start -->
				<div class="row d-flex col-12">
					<div class="product-rate-title col-6">
						<h3>
							<i class="fas fa-th-large px-3"></i>
							商品評價
						</h3>
					</div>
					<div class="score col-5">
					評分
					<c:set value="0" var="sum"/>
					<c:set value="0" var="commentscount"/>            
					<c:forEach items="${comments}" var="comment">                 
					<c:set value="${sum+comment.rate}" var="sum"/> 
					<c:set value="${commentscount+1}" var="commentscount"/> 
					</c:forEach>
					<fmt:formatNumber value="${sum/commentscount}" pattern=".0" type="number"/>
					/ ${commentscount}人評價</div>
				</div>


				<!-- 評價內容start -->
				<div class="col-11 ">
					<c:forEach items="${comments}" var="comment" varStatus="status">
						<div class="comment row">
						  <div class="memberImg no-gutters col-2">
						  	<img src="<c:url value='/getMemberImg?custId=${comment.customerBean.custId}'/>" alt="...">
						  </div>
             			  <div class="col-1"> ${comment.customerBean.custName}</div>
             			  <div class="col-1"> ${comment.rate}分</div>                                         
             			  <div class="col-2">
             			  		評價時間<br> 
							   <fmt:formatDate value="${comment.commentDate}" 
								pattern="yyyy年MM月dd日" />
						 </div>
						 <div class="col-6">
						${productComments[status.index]}
						 </div>
						</div>
					</c:forEach>
				</div>
				<!-- 評價內容end -->
				<!-- 評價顯示 end -->
			</div>
		</div>
</div>
		<!-- footer Start! -->
		<!-- 引入共同的頁尾 -->
		<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />
		<!-- footer End! -->

	</div>


</body>

</html>