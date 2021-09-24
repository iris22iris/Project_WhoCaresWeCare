<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link rel="stylesheet"
	href="<c:url value='/css/_04buyProductMenu.css' />">
	<link rel="stylesheet" href="<c:url value='/css/_01searchResult.css' />"
	type="text/css">
<%-- 	<script src="<c:url value='/js/addFavorite.js' />"></script> --%>
	<!-- bootstrap -->
	<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
	<!-- icon -->
	<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />

	<title>搜尋結果 ${searchProduct}</title>
				<!-- 引入共同的頁首 -->
				<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />
	<script>
	window.onload = function() {
		searchBox();
	}
	</script>

</head>
<body>
	<!-- main start -->
	<div id="body">
		<div id="content">

			

			<!-- Search Result Title Start -->
<!-- 			<div class="container"> -->
<!-- 				<div class="searchResultTitle"> -->
<!-- 					<h3> -->
<%-- 						<i class="fas fa-th-large pe-3"></i>關於 ${searchProduct} 的相關商品: --%>
<!-- 					</h3> -->
<!-- 					<select class="sequence" name="sequence"> -->
<!-- 						<option>搜尋結果排序</option> -->
<!-- 						<option value="傳送值">價格↓由高至低</option> -->
<!-- 						<option value="傳送值">價格↑由低至高</option> -->
<!-- 						<option value="傳送值">數量↓由高至低</option> -->
<!-- 						<option value="傳送值">數量↑由低至高</option> -->
<!-- 					</select> -->
<!-- 				</div> -->
<!-- 			</div> -->
			<!-- Search Result Title End -->


			<!-- Search Result Content Start -->
			<div class="container">
				<!-- foreach要跑兩個:rent/buy -->
				<div class="row searchResultContent">
<!-- 					<div class="col-3 mt-3"> -->
<!-- 						<div class="card text-center"> -->
<%-- 							<a href="#"><img src="<c:url value='/images/product/A0001.jpg' />" --%>
<!-- 								id="productImg" class="card-img-top" alt="..."></a> -->
<!-- 							<div class="card-body"> -->
<!-- 								<h5 class="card-title">可攜式摺疊輪椅</h5> -->
<!-- 								<div class="card-text mb-2"> -->
<!-- 									150 元/日<br> 庫存: 1個 -->
<!-- 								</div> -->
<!-- 								如果庫存為零會改成顯示前往預約候補 -->
<!-- 								<a href="#" class="btn btn-warning">前往租賃</a> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
				<c:forEach var='productList' items='${productList}' >
					<div class="col-3 mt-3" >
						<div class="card text-center">
							<a href="<c:url value='/_04_productPage?id=${productList.prodId}' />">
							<img src="<c:url value='/images/product/${productList.fileName}' />"
								id="productImg" class="card-img-top" alt="..."></a>
								
							<div class="card-body ">
								<h5 class="card-title productName">
									${productList.prodName}<i class="far fa-heart ps-4" onclick="track(this)"></i>
								</h5>
								<div class="card-text mb-2">
									價格: ${productList.price}元<br>庫存:${productList.stock}台
								</div>
									<form class="row-3 pt-2">
										<select id="prodQTY${productList.prodId}" name="prodQTY" class="form-select prodQTY" style="width: 45%;"
											aria-label="Default select example">
											<option selected disabled>數量</option>
											<c:forEach var="amount" begin="1" end="${productList.stock}" >
												<option id="optionID" value="${productList.prodId}" >${amount}</option>
											</c:forEach>
										</select> 
										<Input type='hidden' name='prodId' value='${productList.prodId}'>
										<Input type='hidden' name='prodQTY' value='${amount}'>
										<Input type='hidden' name='flag' value= 1>
										<Input type='hidden' name='searchProduct' value='${searchProduct}'>
										<input type="submit" id="cart${productList.prodId}" class="btn btn-warning" value="加入購物車" onclick='addProduct(${productList.prodId})' disabled="disabled"/>
									</form>
								</div>
						</div>
					</div>
					
				</c:forEach>	
					
				</div>
			</div>
			<!-- Search Result Content End -->

		</div>
		
		<!-- 引入共同的頁尾 -->
		<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />
		
	</div>
	<!-- main end -->
8
	<script>
	
		let cartButton = '${cartButton}';
		function track(heart) {
			if (heart.className == "far fa-heart ps-4") {
				heart.className = "fas fa-heart ps-4";
				Swal.fire({
					position : 'center',
					icon : 'success',
					title : '商品已加入追蹤清單',
					showConfirmButton : false,
					timer : 1000
				})
			} else {
				heart.className = "far fa-heart ps-4";
				Swal.fire({
					position : 'center',
					icon : 'error',
					title : '商品已取消追蹤',
					showConfirmButton : false,
					timer : 1000
				})
			}
		}

		cartButton = JSON.parse(cartButton);
		cartButton.forEach(function(part, index) {
			$("#prodQTY" + this[index]).change(function(){
				  if ($("#prodQTY" + cartButton[index]).val() != undefined) {
						$("#cart" + cartButton[index]).attr('disabled', false);
					} 
			  });
			  
			}, cartButton);

	  
		function addProduct(prodId){

			$.ajax({
				url : '${pageContext.request.contextPath}/_01_searchResult/cart',
				type : "POST",
				async: false,
				data : {
					prodId : prodId,
					prodQTY: $('#prodQTY'+prodId).find("option:selected").text(),			
				},
				success: function(success) {
					 
				}
			});
		}

	</script>
	<!-- bootstrap -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>