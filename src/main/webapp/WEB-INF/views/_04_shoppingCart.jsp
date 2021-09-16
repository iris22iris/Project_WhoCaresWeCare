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

    <title>購物車清單</title>
    
    <script>

	window.onload = function(){
		count();
	}
	
	//計算結帳明細(商品金額/優惠折抵/合計)
	function count(){
		//商品金額
		var showTotal = 0;
		$(".productPrice").each(function(){
			var total = parseInt($(this).text());
			showTotal += total
		})
		$("#itemSum").text(showTotal);

		//優惠折抵
		var showSum = 0;
		$(".discount").each(function(){
			var total = parseInt($(this).text());
			showSum += total
		})
		$("#discountSum").text(showSum);

		//合計金額
		var showOrder = showTotal + showSum;
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
    </script>
</head>
<body>
    <!-- main start -->
  <div id="body">
    <div id="content">

	<!-- 引入共同的頁首 -->
	<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />
        
     <!-- title Start -->
     <div class="title container">
         <h3>
            <i class="fas fa-th-large pe-2"></i>
            購物車清單
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
             <div class="cartList" id="productItem${buyItems.productBean.prodId}">
                <div class="col-2">
                    <input type="checkbox" name="prodId" value="${buyItems.productBean.prodId}" class="checkPid">
                </div>
                <div class="col-3">
                    <img class="productImg" 
                    src="<c:url value='/images/product/${buyItems.productBean.fileName}' />">
                </div>
                <div class="col-5 cartContent">
                    <div class="col-12 productId">商品編號${buyItems.productBean.prodId}</div>
                    <div class="col-12 productName">
                        ${buyItems.productBean.prodName} 
                   </div>
                   <c:choose>
                   <c:when test="${empty buyItems.productBean.promotionBean.promoteId}">
                    <div class="col-12" hidden="true">商品標籤</div>
                    </c:when>
                   <c:otherwise>
                     <div class="col-12">
                            <span class="promotag" >
                            ${buyItems.productBean.promotionBean.promoTag}
                        </span>
                    </div>
                   </c:otherwise>
                    </c:choose>
                    <div class="col-12">數量</div>
                    <div class="col-12">折抵</div>
						<%-- itemSum應為 扣減${折抵}的小計 尚未完成 --%>
                    <div class="col-12">小計</div>
                </div>
                <div class="col-2 cartSum">
                    <div></div>
                    <div class="col-12 hidden"></div>
                    <div class="col-12 productPrice">
                        ${buyItems.productBean.price}元
                    </div>
					<!-- 有符合活動才會顯示標籤 -->
                    <c:choose>
                   <c:when test="${empty buyItems.productBean.promotionBean.promoteId}">
                    <div class="col-12 hidden" hidden="true"></div>
                    </c:when>
                   <c:otherwise>
                     <div class="col-12 hidden"></div>
                   </c:otherwise>
                    </c:choose>
                    <div class="col-12 num">
<!--                     調整數量功能待完成 -->
<!--                         <i class="far fa-plus-square pe-2 mb-1"></i> -->
                        ${buyItems.prodQTY}
<!--                         <i class="far fa-minus-square ps-2 mb-1"></i> -->
                    </div>
                    <!-- 折抵金額 -->
                    <c:choose>
                   <c:when test="${!empty buyItems.productBean.promotionBean.promoteId}">
                    <div class="col-12 discount" style="color:crimson;">-${buyItems.productBean.promotionBean.discount}元</div>
                    </c:when>
                   <c:otherwise>
                     <div class="col-12">無</div>
                   </c:otherwise>
                    </c:choose>
                    <div class="col-12 itemTotal">
                    ${buyItems.itemSum - buyItems.productBean.promotionBean.discount}元
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
                <span id="discountSum" style="color:crimson;"></span>元
                </div>
                <div class="col-4 amountItem">折扣碼：</div>
                <div class="col-8 price">
                    <input type="text" placeholder="尚未輸入折扣代碼" id="discountCode">
                </div>
                <!-- enter submit以後從hidden改為顯示 -->
                <div class="col-12 submitMsg" hidden="true">您輸入的折扣碼有誤/歡慶88節:折抵100元</div>
                <div class="col-12"><hr style="size:5px;"></div>
                
                <div class="col-6 amountItem">合計金額：</div>
                <div class="col-6 price">
                <span id="order"></span>元
                </div>
            </div>
                <div class="checkoutBtn col-12 mt-3">
                    <button >繼續逛逛</button>
                    <button type="submit">結帳去</button>
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
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>