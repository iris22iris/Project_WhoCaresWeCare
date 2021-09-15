<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
     <meta charset="UTF-8">
     <title>租賃設備結帳</title>   
     <!-- CSS -->
     <link rel="stylesheet" href="<c:url value='/css/_04buyCheckout.css' />"
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
                  <th class="col-1">租賃天數</th>
                  <th class="col-1">單價</th>
                  <th class="col-1">折抵</th>
                  <th class="col-2">合計</th>
              </tr>
              <tr class="detailList">
                  <td>1</td>
                  <td><img src="<c:url value='/images/product/A0003.jpg'/>"></td>
                  <td>A100002<br>手動輪椅</td>
                  <td>40</td>
                  <td>50</td>
                  <td>20</td>
                  <td>1950</td>
              </tr>
              <tr class="detailList">
                  <td>2</td>
                  <td><img src="<c:url value='/images/product/A0002.jpg' />"></td>
                  <td>A100002<br>手動輪椅</td>
                  <td>40</td>
                  <td>50</td>
                  <td>20</td>
                  <td>1950</td>
              </tr>
              <tr class="detailList">
                  <td>3</td>
                  <td><img src="<c:url value='/images/product/A0001.jpg' />"></td>
                  <td>A100002<br>手動輪椅</td>
                  <td>40</td>
                  <td>50</td>
                  <td>20</td>
                  <td>1950</td>
                </tr>
          </table>
      </div>
      <!--商品清單 end-->

      <hr style="margin:20px;">

      <!-- 結帳資訊 start -->
      <div id="checkoutInfo">
        <div class="col-5 checkoutTitle">
            <h4>
              <i class="fas fa-crutches"></i>
              訂單備註:
            </h4>
            <textarea id="orderMark" cols="60" rows="5"></textarea>       
        </div>
        <div class="col-3 checkoutTitle">
            <div class="checkoutTop">
              <h4>運送方式:</h4>
              <select name="deliveryType" id="deliveryType">
                 <option value="0">自取</option> 
                 <option value="1">物流宅配 270元</option>      
              </select>
            </div>
            <div class="checkoutBottom">
              <h4>折扣碼:</h4>
              <input type="text" id="discountCode">
            </div>
        </div>
        <div class="col-4 checkoutTitle">
            <h4>結帳金額:</h4>
            <div class="row count">
                <div class="col-4">商品金額:</div>
                <div class="col-4 price">6000元</div>
                <div class="col-4 "></div>
                <div class="col-4">運費:</div>
                <div class="col-4 price">0元</div>
                <div class="col-4 "></div>
                <div class="col-4">優惠折抵:</div>
                <div class="col-4 price">-150元</div>
                <div class="col-4 "></div>
                <div class="col-4">合計金額:</div>
                <div class="col-4 price">5850元</div>
                <div class="col-4 "></div>

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
            <form class="deliveryInfo">
                <p>
                    收件人:
                    <input type="text" class="memberName me-3" value="預設會員名字">
                    連絡電話:
                    <input type="tel" value="預設會員電話"></p>
                <p>
                    地址(城市): 
                    <select name="city" class="select me-3" id="city">
                        <option value="0">台北市</option> 
                        <option value="1">新北市</option> 
                        <option value="2">桃園市</option> 
                    </select>
                    收件時段:
                    <select name="checktime" class="select" id="time">
                        <option value="0">早上(9:00-12:00)</option> 
                        <option value="1">中午(12:00-13:30)</option> 
                        <option value="2">下午(13:30-17:00)</option>     
                     </select>
                </p>        
                <p>
                    地址(路名):
                    <input type="text" class="addressInput" value="預設會員地址">
                </p>
            </form>
        </div>
      
        <div class="col-5 ">
          <div class="address" >
              <div class="addressTitle ">
                <h4>來店自取:</h4>
              </div>
              <div class="deliveryInfo">
                <select class="city select">
                <option value="TPE">台北總店</option>
                <option value="NEW-TPE">三重分店</option>
                </select>
                <p>請於 {} 營業時間來店自取</p>
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
            <button class="me-3">繼續購買</button>
            <button type="submit" id="checkout" >確認結帳</button>
            </div>
      </div>
  
     

      </div>
      	<!-- 引入共同的頁尾 -->
		<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />
    </div>
    <!-- main end -->


   <!-- bootstrap -->
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>   
</body>
</html>
