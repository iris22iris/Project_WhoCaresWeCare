<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- CSS -->
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
	<script
		  src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <title>租賃設備清單</title>
    
    <script>
	window.onload = function() {
// 		searchBox();
		count();
		countDate();
	}

	
	//計算結帳明細(商品金額/優惠折抵/合計)
	function count(){
		//商品金額
		var showProductTotal = 0;
		var index = 0;
		$(".price").each(function(){
			var price = parseInt($(this).text());
			var days = parseInt($("#day"+index).text());
			price *= days
			showProductTotal += price
			index++;
		})
		$("#prouductSum").text(showProductTotal);

		//優惠折抵
		var showDiscountSum = 0;
		$(".discount").each(function(){
			var total2 = parseInt($(this).text());
			showDiscountSum += total2
		})
		$("#discountSum").text(showDiscountSum);
		
		//合計金額
		var showOrder = 0;
		showOrder = showProductTotal + showDiscountSum;
		$("#sum").text(showOrder); 

	}
	
	//計算租賃日期
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
			url : "<c:url value='/_03_rentCart/updateItem.do'/>",
			type : "POST",
			async: false,
			data : {
				prodId : prodId,
			},

			success: function() { 
				alert("WHYYYY");
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
        <div class="col">
        	<button type="submit" id="deleteBtn" onclick="confirmDelete()">
        		<i class="far fa-trash-alt" id="deleteIcon"></i>
        	</button>
        </div>
    </div>
    <c:forEach varStatus="vs" var='cart' items='${RentCart.content}'>
    <div class="row list-item" id="productItem${cart.value.rentProductBean.prodId}">
		<!--商品圖片 -->
        <div class="col">
           <a href="<c:url value='/_03_rentProduct?id=${cart.value.rentProductBean.prodId}' />">
           <img 
              src="<c:url value='/images/product/${cart.value.rentProductBean.fileName}' />" id="prodImg" >
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
        <div class="col price">${cart.value.rentProductBean.price}</div>
        <!--租賃天數 -->
        <div class="col" id="day${vs.index}">${cart.value.rentPeriod}</div>
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
            <div class="col discount" style="color:red;">-${cart.value.promotionBean.discount}</div>
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
            <li class="productPrice">${cart.value.productBean.price}元</li>
              <button  type="button" class="recommandBtn"  
              			onclick="location.href='${pageContext.request.contextPath}/_04_productPage?id=${cart.value.productBean.prodId}'">
              			前往購買
              </button>          
          </dl>
        </div>
        <div class="col">
          <input class="checkPid" type="checkbox" name="prodId" id="delete" value="${cart.value.rentProductBean.prodId}">
        </div>
    </div>
    </c:forEach>
</div>
	<!-- List end! -->


	<!-- count start! -->
	<div class="count container">
    <!-- bottom-left -->
  <div class="bottom-left">
    <h5>符合優惠活動：</h5>
    <ul type="circle">
    <c:forEach var="rentItems" items="${rentItems}">
      <li>${rentItems.promotionBean.promoContent}</li>
     </c:forEach>
    </ul>
  </div>
    <!-- bottom-right -->
    <div class="bottom-right">
      <div class="row count-row">
        <div class="col-6  count-header"> > 租賃金額：</div>
        <div class="col-6 count-item">
        <span id="prouductSum"></span>元
        </div>
      </div>
      <div class="row count-row">
        <div class="col-6 count-header"> > 優惠折抵：</div>
        <div class="col-6 count-item" style="color:brown;">
        <span id="discountSum"></span>元
        </div>
      </div>
      <div class="row count-row">
        <hr style="width:85%;">
        <div class="col-6 count-header pb-2"> > 合計金額：</div>
        <div class="col-6 count-item pb-2">
        <span id="sum"></span>元
        </div>
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
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/_05loginPopup3.js"></script>
</body>
</html>