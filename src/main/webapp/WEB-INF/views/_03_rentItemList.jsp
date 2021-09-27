<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value='/css/_03rentItemList.css' />"
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

    <title>租賃設備清單</title>
    
    <script>
	window.onload = function() {
		countDate();
		searchBox();
	}

	function countDate(){
			var startDate = null;
			var startIndex = 0;
			$(".startDate").each(function(){
			var start=$(this).text();
			startDate = start.substring(0,10)
			$("#startDate"+startIndex).text(startDate);
			startIndex++;
		})
			
			var endDate = null;
			var endIndex = 0;
			$(".returnDate").each(function(){
			var end=$(this).text();
			endDate = end.substring(0,10)
			$("#returnDate"+endIndex).text(endDate);
			endIndex++;
		})
			

	}
	</script>
	
</head>
<body>
<div id="body">
	<div id="content">
	 
     <!-- 引入共同的頁首 -->
	<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />

	<!-- title start! -->
	<div class="title container">
  		<i class="fas fa-shopping-cart pe-2"></i>租賃設備清單
	</div>
	<!-- title end! -->

	<!-- List start! -->
	<div class="list container">
    <div class="row list-head">
        <div class="col">商品圖片</div>
        <div class="col-2">商品名稱</div>
        <div class="col">租金/日</div>
        <div class="col">租賃天數</div>
        <div class="col">折扣金額</div>
        <div class="col">小計</div>
        <div class="col-2">租賃起訖日期</div>
        <div class="col">新品價格</div>
        <div class="col"><i class="far fa-trash-alt" id="deleteIcon"></i></div>
    </div>
    <c:forEach varStatus="vs" var='cart' items='${RentCart.content}'>
    <div class="row list-item">
		<!--商品圖片 -->
        <div class="col">
           <a href="<c:url value='/_03_rentProduct?id=${cart.value.rentProductBean.prodId}' />">
<!--            <img  -->
<%--               src="<c:url value='/images/product/${cart.value.rentProductBean.fileName}' />" id="prodImg" > --%>
           </a>
        </div>
        <!--商品名稱 -->
        <div class="col-2" class="productName">
          <dl class="productNameList">
            <li class="productId">${cart.value.rentProductBean.prodId}</li>
            <li> <a href="<c:url value='/_03_rentProduct?id=${cart.value.rentProductBean.prodId}' />" 
            		class="itemNameLink">${cart.value.rentProductBean.prodName}</a></li>
		    <!--商品活動標籤 -->
			<!-- 沒有符合活動或折扣的時候不會出現 -->
			<c:choose>
            <c:when test="${empty cart.value.promotionBean.promoteId}">
            	<lable class="promoteTag" hidden="true"></lable>
            </c:when>
            <c:otherwise>
              	<lable class="promoteTag">${cart.value.promotionBean.promoTag}</lable>
             </c:otherwise>
             </c:choose>
          </dl>
        </div>
        <!--單日租金 -->
        <div class="col">${cart.value.rentProductBean.price}</div>
        <!--租賃天數 -->
        <div class="col">${cart.value.rentPeriod}</div>
        <!--折價金額 -->
        <c:choose>
          <c:when test="${empty cart.value.promotionBean.promoteId}">
        	<div class="col">無</div>
        	<!--商品小計 -->
	        <div class="col">
			${cart.value.prodTotal}元
	        </div>
          </c:when>
          <c:otherwise>
            <div class="col" style="color:red;">-${cart.value.promotionBean.discount}</div>
            <!--商品小計 -->
	        <div class="col">
	          <dl>
	            <li class="total">${cart.value.prodTotal}元</li>
	            <li class="discountTotal">
	            ${cart.value.prodTotal - cart.value.promotionBean.discount}元
	            </li>
	           </dl>
	        </div>
          </c:otherwise>
         </c:choose>
        
        <!--租賃起訖日期 -->
        <div class="col-2">
          <dl>
          <div class="startDate" hidden="true">${cart.value.startDate}</div>
          <div class="returnDate"  hidden="true">${cart.value.returnDate}</div>
            <li>起租日:<span id="startDate${vs.index}"></span></li>
            <li>退租日:<span id="returnDate${vs.index}"></span></li>
          </dl>
        </div>
        <!--新品價格 -->
        <div class="col">
          <dl>
            <li class="productPrice">999元</li>
              <button  type="button" class="recommandBtn" href="#">
              前往購買
              </button>          
          </dl>
        </div>
        <div class="col">
          <input type="checkbox" name="delete" id="delete">
        </div>
    </div>
    </c:forEach>
</div>
	<!-- List end! -->


	<!-- count start! -->
	<div class="count container">
    <!-- bottom-left -->
  <div class="bottom-left">
    <h5>已符合：</h5>
    <ul type="circle">
      <li>8/8滿千折百，全館任意消費滿千折百</li>
      <li>全館消費滿3000免運</li>
      <li></li>
    </ul>
  </div>
    <!-- bottom-right -->
    <div class="bottom-right">
      <div class="row count-row">
        <div class="col-6  count-header"> > 租賃金額：</div>
        <div class="col-6 count-item">5000元</div>
      </div>
      <div class="row count-row">
        <div class="col-6 count-header"> > 優惠折抵：</div>
        <div class="col-6 count-item">100元</div>
      </div>
      <div class="row count-row">
        <hr style="width:85%;">
        <div class="col-6 count-header pb-2"> > 合計金額：</div>
        <div class="col-6 count-item pb-2">4900元</div>
      </div>
      <div class="row">
        <div class="col-12">
          <button type="submit" class="btn btn-warning me-2 my-2">結帳</button>
          <button type="button" class="btn btn-secondary my-2">繼續購物</button>
        </div></div>
    </div>
	</div>
	<!-- count end! -->
		
		
		</div>
	
	
	<!-- 引入共同的頁尾 -->
	<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />

</div>
  
	
 <!-- bootstrap -->
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>   

</body>
</html>