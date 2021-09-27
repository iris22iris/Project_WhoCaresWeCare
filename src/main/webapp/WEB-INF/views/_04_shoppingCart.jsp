<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!-- CSS -->
	<link rel="stylesheet" href="<c:url value='/css/_04shoppingCart.css' />"
		type="text/css">
	<link rel="stylesheet" href="<c:url value='/css/commonStyle.css' />"
		type="text/css">
	<!-- bootstrap -->
	<link
		href="<c:url value='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' />"
		rel="stylesheet">
	<!-- icon -->
	<link rel="stylesheet"
		href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css'/>" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- 引入共同的頁首 -->
<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />
<title>購物車清單</title>

<script>

    window.onload = function() {
    	searchBox();
		count();
	}
	
	//計算結帳明細(商品金額/優惠折抵/合計)
	function count(){
		//商品金額
		var showProdTotal = 0;
		$(".productPrice").each(function(){
			var total = parseInt($(this).text());
			var QTY = parseInt($(".num").text());
			total *= QTY
			showProdTotal += total
		})
		$("#itemSum").text(showProdTotal);

		//優惠折抵
		var showDiscountSum = 0;
		$(".discount").each(function(){
			var total2 = parseInt($(this).text());
			showDiscountSum += total2
		})
		$("#discountSum").text(showDiscountSum);

		//折扣碼優惠
		var showDiscountCode = 0;
		if($("#showDiscount")[0].style.display == "block"){
		 showDiscountCode = parseInt($(".showDiscountSum").text());
		}
		
		//合計金額
		var showOrder = 0;
		showOrder = showProdTotal + showDiscountSum - showDiscountCode;
		$("#order").text(showOrder); 

	}
	
    //刪除勾選商品
    function confirmDelete() {
		if (confirm("確認移除勾選之商品? ") ) {
			var checkObj = [];

			for(var i=0 ; i < $(".checkPid").length; i++ ){
				if($(".checkPid")[i].checked){
					checkObj.push($(".checkPid")[i]);
					}
			}
			var prodId = "";
			for(var i=0 ; i <checkObj.length;i++){
				if(i != checkObj.length-1)
					prodId += checkObj[i].getAttribute("value")+",";
				else
					prodId += checkObj[i].getAttribute("value");
			}

		$.ajax({
			url : "<c:url value='/_04_shoppingCart/updateItem.do' />",
			type : "POST",
			async: false,
			data : {
				prodId : prodId,
			},

			success: function() { 
				
				if(prodId.indexOf(",") != -1){
					var prodIdList = prodId.split(',');
					for (var x = 0; x < prodIdList.length; x++) {
						$('#productItem' + prodIdList[x]).remove();
					}
					count();
				}
				$('#productItem' + prodId).remove();
				count();
			}
		});
	  }
	}

	//輸入折扣碼
	function findDiscount(){
		$.ajax({
			url: "<c:url value='/inputCode.do' />",
			type: "POST",
			async : false,
			data: {
				discountCode : document.getElementById('discountCode').value,
			},
			dataType : "text"
			,
			//因為使用dataType所以返回值res.discount -> undefined 需再解決
			success: function(res) {
				$(".showDiscountSum").val(res.discount);
				$("#showDiscount")[0].style.display="block";
				count();
			},

			error: function() {
				alert("輸入的折扣碼有誤")
				$("#showDiscount")[0].style.display="none";
			},
			
		});
	}


	//連結結帳
	function checkout(){
		location.href="${pageContext.request.contextPath}/BuyCheckout/"+'<%=session.getAttribute("LoginOK")%>'
	
	}
</script>
</head>
<body>
	<!-- main start -->
	<div id="body">
		<div id="content">



			<!-- title Start -->
			<div class="title container">
				<h3>
					<i class="fas fa-th-large pe-2"></i> 購物車清單
				</h3>
			</div>
			<!-- title End -->

			<!-- shoppingCart Start -->
			<div class="cart container">

				<!--Left CartList Start -->
				<div class="col-8 cartLeft">
					<div class="cartTitle">
						<div class="col-2">
							<i class="far fa-trash-alt"></i>
						</div>
						<div class="col-10">購物明細</div>
					</div>

					<c:forEach var='buyItems' items='${buyItems}'>
						<div class="cartList"
							id="productItem${buyItems.productBean.prodId}">
							<div class="col-2">
								<input type="checkbox" name="prodId"
									value="${buyItems.productBean.prodId}" class="checkPid">
							</div>
							<div class="col-3">
								<a
									href="<c:url value='/_04_productPage?id=${buyItems.productBean.prodId}' />"><img
									class="productImg"
									src="<c:url value='/images/product/${buyItems.productBean.fileName}' />">
								</a>
							</div>
							<div class="col-5 cartContent">
								<div></div>
								<div class="col-12 productId">商品編號${buyItems.productBean.prodId}</div>
								<div class="col-12 productName">
									<a
										href="<c:url value='/_04_productPage?id=${buyItems.productBean.prodId}' />">
										${buyItems.productBean.prodName} </a>
								</div>
								<c:choose>
									<c:when
										test="${empty buyItems.productBean.promotionBean.promoteId}">
										<div class="col-12" hidden="true">商品標籤</div>
									</c:when>
									<c:otherwise>
										<div class="col-12">
											<span class="promotag">
												${buyItems.productBean.promotionBean.promoTag} </span>
										</div>
									</c:otherwise>
								</c:choose>
								<div class="col-12">數量</div>
								<div class="col-12">折抵</div>
								<div class="col-12">小計</div>
							</div>
							<div class="col-2 cartSum">
								<div></div>
								<div class="col-12 hidden"></div>

								<!--商品單價 -->
								<div class="col-12 productPrice">
									${buyItems.productBean.price}元</div>

								<!-- 有符合活動才會顯示標籤 -->
								<c:choose>
									<c:when
										test="${empty buyItems.productBean.promotionBean.promoteId}">
										<div class="col-12 hidden" hidden="true"></div>
									</c:when>
									<c:otherwise>
										<div class="col-12 hidden"></div>
									</c:otherwise>
								</c:choose>

								<!--商品數量 -->
								<div class="col-12 num">
									<!--                     調整數量功能待完成 -->
									<!--                         <i class="far fa-plus-square pe-2 mb-1"></i> -->
									${buyItems.prodQTY}
									<!--                         <i class="far fa-minus-square ps-2 mb-1"></i> -->
								</div>

								<!-- 單品折抵金額 -->
								<c:choose>
									<c:when
										test="${!empty buyItems.productBean.promotionBean.promoteId}">
										<div class="col-12 discount" style="color: crimson;">-${buyItems.productBean.promotionBean.discount}元</div>
									</c:when>
									<c:otherwise>
										<div class="col-12">無</div>
									</c:otherwise>
								</c:choose>

								<!--商品小計 -->
								<div class="col-12 itemTotal">${buyItems.itemSum - buyItems.productBean.promotionBean.discount}元
								</div>
							</div>
						</div>
					</c:forEach>

					<div class="deleteBtn">
						<button type="submit" onclick="confirmDelete()">刪除</button>
					</div>
				</div>
				<!--Left CartList End -->

				<!--Right Count Start -->
				<div class="col-3 cartRight">
					<div class="amountTitle">結帳明細</div>
					<div class="amount">

						<div class="col-6 amountItem">商品總金額：</div>
						<div class="col-6 price">
							<span id="itemSum"></span>元
						</div>

						<div class="col-6 amountItem">優惠合計：</div>
						<div class="col-6 price">
							<span id="discountSum" style="color: crimson;"></span>元
						</div>

						<div class="col-4 amountItem">折扣碼：</div>
						<div class="col-8 price">
							<form class="discountForm">
								<input type="text" placeholder="尚未輸入折扣代碼" id="discountCode">
								<input type="button" value="送出" onclick="findDiscount()" />
							</form>
						</div>
						<!-- enter submit以後從hidden改為顯示 -->
						<div class="col-12 submitMsg" id="showDiscount">
							折扣碼優惠 <span class="showDiscountSum">${ordBean.discount}</span>元
						</div>
						<div class="col-12">
							<hr style="size: 5px;">
						</div>

						<div class="col-6 amountItem">合計金額：</div>
						<div class="col-6 price">
							<span id="order"></span>元
						</div>
					</div>
					<div class="checkoutBtn col-12 mt-3">
						<div id="contentpopup"></div>
						<button onclick="location.href=`${pageContext.request.contextPath}/buyMenu`">繼續逛逛</button>
						<c:choose>
							<c:when test="${ empty sessionScope.LoginOK}">
								<button data-bs-toggle="modal" data-bs-target="#exampleModal">結帳去</button>
							</c:when>
							<c:otherwise>
								<button onclick="checkout()">結帳去</button>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<!--Right Count End -->

			</div>
			<!-- shoppingCart End -->


		</div>
		<!-- 引入共同的頁尾 -->
		<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />

	</div>
	<!-- main end -->

	<!-- bootstrap -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/_05loginPopup3.js"></script>

</body>
</html>