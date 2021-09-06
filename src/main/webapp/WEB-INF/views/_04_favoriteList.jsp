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
</head>
<body>
<!-- main start -->
  <div id="body">
  	<div id="content">
	<!-- 引入共同的頁首 -->
	<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />

    <!-- title start-->
      <div class="title container">
         <h2><i class="fas fa-th-large px-3"></i>商品追蹤清單</h2>
      </div>
     <!-- title end -->

     <!-- followList start -->
      <div class="container">
          <div class="row followListTitle" >
              <div class="col-2">追蹤</div>
              <div class="col-2">商品圖片</div>
              <div class="col-2">商品編號</div>
              <div class="col-3">商品名稱</div>
              <div class="col-1">價格</div>
              <div class="col-2">加入購物車</div>
          </div>
          
            <!-- listItem start -->
          <div class="row listItem d-flex">

              <!-- 取消已追蹤商品項目 -->
              <button type="button" class="btn col-2" data-bs-toggle="modal" data-bs-target="#exampleModal">
                            <i class="fas fa-heart h1"></i>
              </button> 
              <!-- 是否取消已追蹤商品項目彈出視窗 -->
              <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">注意!</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                是否取消追蹤這個項目
                                </div>
                                <div class="modal-footer">
                                  <button type="button" class="btn btn-warning">確定刪除</button>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                                </div>
                            </div>
                            </div>
              </div> 
              
              <div class="col-2">
                  <img src="<c:url value='/images/product/A0001.jpg' />" 
                  class="productImg" alt="">
              </div>
              <div class="col-2">A10000012</div>
              <div class="col-3">多功能收折式手動輪椅</div>
              <div class="col-1">900元</div>
              <div class="col-2"><i class="fas fa-cart-plus h1"></i></div>
         </div>
         <!-- listItem end -->
         
         <!-- goShopBtn start -->
         <div class="row goShop">
           <div class="col-12 goShopBtn">
             <button type="button" class="btn btn-warning ">再去逛逛</button>
           </div>
         </div>
         <!-- goShopBtn end -->

          </div>
          <!-- followList end -->
         </div>
     <!-- 引入共同的頁尾 -->
	<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />
         
         
  </div>
  <!-- main end -->
  
     <!-- bootstrap -->
   <script src="
   		<c:url value='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' />">
   	</script>   

</body>
</html>