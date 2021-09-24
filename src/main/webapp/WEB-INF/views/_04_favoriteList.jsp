<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">

<title>購買商品追蹤清單</title>
<!-- CSS -->
<link rel="stylesheet" href="<c:url value='/css/_04favoritelist.css' />"
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
			<!-- 引入共同的頁首 -->
			<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />
<script>
	window.onload = function() {
			searchBox();
		$.ajax({
			url : '/Whocares/quereyFavorite',
			type : "POST",
			async: false,
			data : {
				FK_Customer_ID : <%=session.getAttribute("LoginOK")%>,
			},
			success : function(response) {
				if(response){
					let list = '<div class="row followListTitle">'
						+'<div class="col-2">追蹤</div>'
						+'<div class="col-2">商品圖片</div>'
						+'<div class="col-2">商品編號</div>'
						+'<div class="col-3">商品名稱</div>'
						+'<div class="col-1">價格</div>'
						+'<div class="col-2">加入購物車</div>'
						+'</div>';
					response.forEach(function (productItem) {
						list+='<div class="row listItem d-flex" id="deleteIt'+productItem[0]+'">';
						list+='<button type="button" class="btn col-2" data-bs-toggle="modal" data-bs-target="#exampleModal'+productItem[0]+'">';
						list+='	<i class="fas fa-heart h1"></i>';
						list+='</button>';
						list+='<div class="modal fade" id="exampleModal'+productItem[0]+'" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">';
						list+='<div class="modal-dialog modal-dialog-centered">';
						list+='<div class="modal-content">';
						list+='<div class="modal-header">';
						list+='<h5 class="modal-title" id="exampleModalLabel">注意!</h5>';
						list+='<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>';
						list+='</div>';
						list+='<div class="modal-body">是否取消追蹤這個項目</div>';
						list+='<div class="modal-footer">';
						list+='<button type="button" class="btn btn-warning" onclick="deleteProduct('+productItem[0]+')">確定刪除</button>';
						list+='<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>';
						list+='</div>';
						list+='</div>';
						list+='</div>';
						list+='</div>';
						list+='<div class="col-2">';
						list+='<img src="/Whocares/images/product/'+productItem[3]+'" class="productImg" alt="">';
						list+='</div>';
						list+='<div class="col-2" id="prodId'+productItem[0]+'">'+productItem[0]+'</div>';
						list+='<div class="col-3" id="prodName'+productItem[0]+'">'+productItem[1]+'</div>';
						list+='<div class="col-1" id="price'+productItem[0]+'">'+productItem[2]+'TWD</div>';
						list+='<div class="col-2">';
						list+='<i class="fas fa-cart-plus h1"></i>';
						list+='</div>';
						list+='</div>';
					});
					$('#favoriteList').append(list);
				}
			}
		});
	}

	function deleteProduct(prodId){
		$.ajax({
			url : '/Whocares/deleteFavorite',
			type : "POST",
			async: false,
			data : {
				FK_Customer_ID : <%=session.getAttribute("LoginOK")%>,
				FK_Product_ID:prodId					
			},
			success: function() { 
				$('#deleteIt'+prodId).remove();
				$('#your-modal-id').modal('hide');
				$('body').removeClass('modal-open');
				$('.modal-backdrop').remove();
				$('#test').css("overflow-y","scroll");
			}
		});
	}
</script>
</head>
<body id="test">
	<!-- main start -->
	<div id="body">
		<div id="content">
			

			<!-- title start-->
			<div class="title container">
				<h2>
					<i class="fas fa-th-large px-3"></i>商品追蹤清單
				</h2>
			</div>
			<!-- title end -->

			<!-- followList start -->

			<div class="container" id=favoriteList>
<!-- 				listItem start -->
				<div class="row listItem d-flex">
<!-- 					是否取消已追蹤商品項目彈出視窗 -->
					<div class="modal fade" id="exampleModal" tabindex="-1"
						aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-dialog-centered">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">注意!</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">是否取消追蹤這個項目</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-warning">確定刪除</button>
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">取消</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- listItem end -->
			</div>
			<!-- followList end -->
							<!-- goShopBtn start -->
				<div class="row goShop">
					<div class="col-12 goShopBtn">
						<a href="<c:url value='/buyMenu' />">
							<button type="button" class="btn btn-warning ">再去逛逛</button>
						</a>
					</div>
				</div>
				<!-- goShopBtn end -->
		</div>
		<!-- 引入共同的頁尾 -->
		<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />


	</div>
	<!-- main end -->

	<!-- bootstrap -->
	<script
		src="
   		<c:url value='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' />">
		
	</script>

</body>
</html>