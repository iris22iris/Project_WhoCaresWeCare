<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
     <meta charset="UTF-8">
     <title>租賃設備結帳</title>   
     <!-- CSS -->
     <link rel="stylesheet" href="<c:url value='/css/_03rentCheckout.css' />"
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
	}

	//到貨方式選擇
	function delivery(){
		deliveryType = String(document.getElementById("deliveryType").value);
		switch(deliveryType){
		case "自取":
			document.getElementById("shippingFee").innerHTML=
				'<c:set var="shippingFee" value="${0}"/>${shippingFee}元';
			document.getElementById("subtotal").innerHTML=
				'<c:set var="subtotal" value="${OrdBean.ordTotal-OrdBean.discount}"/>${subtotal+shippingFee}元';
			break;
		case "宅配":
			document.getElementById("shippingFee").innerHTML=
				'<c:set var="shippingFee" value="${270}"/>${shippingFee}元';
			document.getElementById("subtotal").innerHTML=
				'<c:set var="subtotal" value="${OrdBean.ordTotal-OrdBean.discount}"/>${subtotal+shippingFee}元';
			break;
		}
		shippingFee = document.getElementById("shippingFee").innerHTML;
		subtotal = document.getElementById("subtotal").innerHTML;
	}


	//確認勾選同意
	$("#checkout").click(function(){
		if($("#memberRulesCheck").attr('checked')){
			  alert("已確認同意會員守則");
			  return true;
		}else{
			  alert("尚未同意會員守則")
		 	 return false;
		}
	})
		
	
	</script>
	
	</head>
<body>
    <!-- main start -->
    <div id="body">
      <div id="content">
  
       
       

      <!--商品結帳頁面-->
      <div class="title">
        <h1>
            <i class="fas fa-th-large px-2"></i>
            訂購明細
        </h1>
      </div>

      <!--商品清單 start-->
      <div class="row detail">
          <table>
              <tr class="detailTitle">
                  <th class="col-1">項次</th>
                  <th class="col-2">圖片</th>
                  <th class="col-2">商品名稱</th>
<!--              <th class="col-2">起訖日期</th> -->
                  <th class="col-1">租賃天數</th>
                  <th class="col-1">單價</th>
                  <th class="col-1">優惠折扣</th>
                  <th class="col-2">合計</th>
              </tr>
              
            <c:forEach varStatus="vs" var="rent" items='${rentItems}'>
              <tr class="detailList">
                  <td>${vs.index+1}</td>
                  <!-- 圖片 -->
                  <td><img src="<c:url value='/images/product/${rent.productBean.fileName}'/>"></td>
                  <!-- 商品編號/名稱 -->
                  <td>${rent.productBean.prodId}<br>${rent.productBean.prodName}</td>
				  <!-- 租賃天數 -->
                  <td>${rent.rentPeriod}</td>
                  <!-- 商品單價 -->
                  <td>${rent.rentProductBean.price}</td>
                  <!-- 優惠折扣 -->
                  <c:choose>
	                  <c:when test="${!empty rent.rentProductBean.promotionBean.discount}">
	                 	 <td style="color: red;">-${rent.rentProductBean.promotionBean.discount}元</td>
	                  </c:when>
	                  <c:otherwise>
	                  	 <td>無</td>
	                  </c:otherwise>
                  </c:choose>
                  <!-- 商品小計 -->
                  <td>${rent.prodTotal-rent.rentProductBean.promotionBean.discount}</td>
              </tr>
       		</c:forEach>
          </table>
      </div>
      <!--商品清單 end-->

      <hr style="margin:20px;">

	<form method='POST'
					action="${pageContext.request.contextPath}/rentOrderSubmit">
      <!-- 結帳資訊 start -->
      <div id="checkoutInfo">
        <div class="col-5 checkoutTitle">
            <h4>
              <i class="fas fa-crutches"></i>
              訂單備註:
            </h4>
            <textarea name="oMark" id="orderMark" cols="50" rows="5"></textarea>       
        </div>
        <div class="col-3 checkoutTitle">
            <div class="checkoutTop">
              <h4>運送方式:</h4>
              <select name="delivery" id="deliveryType" onchange="delivery()">
                 <option value="自取">自取-運費0元</option>
                 <option value="宅配">物流宅配-270元</option>      
              </select>
            </div>
            <div class="checkoutBottom">
              <h4>折扣碼:</h4>
              <input type="text" id="discountCode">
              <input type="button" value="輸入" onclick="changeDiscount()">
              <c:if test="${!empty OrdBean.discountCode}">
              <div style="color: red;">折扣碼優惠-${OrdBean.discount}元</div>
              </c:if>
            </div>
        </div>
        <div class="col-4 checkoutTitle">
            <h4>結帳金額:</h4>
            <div class="row count">
				<!--商品小計 -->
                <div class="col-4">商品小計:</div>
                <div class="col-4 price">${OrdBean.ordTotal}元</div>
                <div class="col-4 "></div>
                <!--運費 -->
                <div class="col-4">運費:</div>
                <div class="col-4 price" id="shippingFee">
                	<c:set var="shippingFee" value="${0}"/>${shippingFee}元
                </div>
                <div class="col-4 "></div>
                <!--折扣碼 -->
                <div class="col-4">折扣碼優惠:</div>
                <c:choose>
                <c:when test="${!empty OrdBean.discount}">
                <div class="col-4 price" style="color: red;">-${OrdBean.discount}元</div>
                <div class="col-4 "></div>
                <!--訂單金額 -->
                <div class="col-4">合計金額:</div>
                <c:set var="subtotal" value="${OrdBean.ordTotal-OrdBean.discount}"/>
                <div class="col-4 price" id="subtotal">
                 ${subtotal-shippingFee}元
                </div>
                <div class="col-4 "></div>
				</c:when>
				
				<c:otherwise>
				<c:set var="zero" value="${0}"/>
				<div class="col-4 price"><c:out value="${zero}"/>元</div>
				<div class="col-4 "></div>
                <!--訂單金額 -->
                <div class="col-4">合計金額:</div>
                <c:set var="subtotal" value="${OrdBean.ordTotal}"/>
                <div class="col-4 price" id="subtotal">
                 ${subtotal-shippingFee}元
                </div>
                <div class="col-4 "></div>
                </c:otherwise>
                </c:choose>
                
            </div>
        </div>
       </div>
        <!-- <div style='clear:both;'></div> -->
      <!-- 結帳資訊 end -->

      <hr style="margin:20px;">
      
      <!--宅配資訊 Start -->
      <div class="delivery row">
           
        <div class="col-7 address">
            <div class="addressTitle ">
                <h4>宅配資訊:</h4>
            </div>
            <div class="deliveryInfo">
                <p>
                    收件人:
                    <input name="reciName" type="text" class="memberName me-3" value="${OrdBean.customerBean.custName}">
                    連絡電話:
                    <input name="reciPhone" type="tel" value="${OrdBean.customerBean.phone}"></p>
                <p>
<!--                     地址(城市):  -->
<!--                     <select name="city" class="select me-3" id="city"> -->
<!--                         <option value="0">台北市</option>  -->
<!--                         <option value="1">新北市</option>  -->
<!--                         <option value="2">桃園市</option>  -->
<!--                     </select> -->
<!--                     收件時段: -->
<!--                     <select name="checktime" class="select" id="time"> -->
<!--                         <option value="0">早上(9:00-12:00)</option>  -->
<!--                         <option value="1">中午(12:00-13:30)</option>  -->
<!--                         <option value="2">下午(13:30-17:00)</option>      -->
<!--                      </select> -->
                </p>        
                <p>
                    地址(路名):
                    <input name="reciAddress" type="text" class="addressInput" value="${OrdBean.customerBean.address}">
                </p>
        </div>
       </div>
        <div class="col-5 ">
          <div class="address" >
              <div class="addressTitle ">
                <h4>來店自取:</h4>
              </div>
              <div class="deliveryInfo">
<!--                 <select class="city select"> -->
<!--                 <option value="TPE">台北總店</option> -->
<!--                 <option value="NEW-TPE">三重分店</option> -->
<!--                 </select> -->
                <p>請於營業時間來店自取</p>
                <p>逾期未取仍收取租賃費用</p>
              </div>    
          </div>
        </div>
 
      </div>
      <!--宅配資訊 End -->
         <!-- <div style='clear:both;'></div> -->
 
      <div class="col-2 final mx-auto">
            <div class="form-check" >
            <input type="checkbox" id="memberRulesCheck">
             我已閱讀購買須知並同意
            </div>
            <div class="btn checkOutBtn w-100" >
            <input name="custId" type="hidden"  value="${OrdBean.customerBean.custId}"/>
            <input name="payment" type="hidden"  value="線上刷卡"/>
            <button class="me-3" onclick="location.href=`${pageContext.request.contextPath}/rentMenu`">繼續購買</button>
            <button type="submit" id="checkout">確認結帳</button>
            </div>
      </div>
  	</form>
     

      </div>
      	<!-- 引入共同的頁尾 -->
		<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />
    </div>
    <!-- main end -->


   <!-- bootstrap -->
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>   
</body>
</html>
