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
     <!-- 引入共同的頁首 -->
	<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />
    <title>租賃設備清單</title>
</head>
<body>
<div id="body">
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
    <div class="row list-item">
        <div class="col">
          <img src="./images/product/A0001.jpg" alt="">
        </div>
        <div class="col-2" class="productName">
          <dl>
            <li class="productId">A122222222</li>
            <li> <a href="#商品網址" class="itemNameLink">多功能型摺疊輪椅</a></li>
<!-- 沒有符合活動或折扣的時候不會出現 -->
            <!-- <lable class="promoteTag">滿千折百</lable> -->
          </dl>
        </div>
        <div class="col">50元</div>
        <div class="col">30天</div>
        <div class="col">-</div>
        <div class="col">
          <dl>
<!-- 沒有折扣的時候不用刪除線(對照CSS檔) -->
            <li class="total">1500元</li>
            <li class="discountTotal">
<!-- 如果有折扣會顯示折扣後金額 --> 
            </li>
           </dl>
        </div>
        <div class="col-2">
          <dl>
            <li>起租日: 2021-08-31</li>
            <li>退租日: 2021-09-29</li>
          </dl>
        </div>
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
    <div class="row list-item">
      <div class="col">
        <img src="./images/product/A0001.jpg" alt="">
      </div>
      <div class="col-2" class="productName">
        <dl>
          <li class="productId">A122222222</li>
          <li> <a href="#商品網址" class="itemNameLink">多功能型摺疊輪椅</a></li>
          <lable class="promoteTag">滿千折百</lable>
        </dl>
      </div>
      <div class="col">50元</div>
      <div class="col">30天</div>
      <div class="col">100元</div>
      <div class="col">
        <dl>
          <li class="total">1500元</li>
          <li class="discountTotal">
            <!-- 如果有折扣會顯示折扣後 -->
            1400元
          </li>
         </dl>
      </div>
      <div class="col-2">
        <dl>
          <li>起租日: 2021-08-31</li>
          <li>退租日: 2021-09-29</li>
        </dl>
      </div>
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
    <div class="row list-item">
      <div class="col">
        <img src="./images/product/A0001.jpg" alt="">
      </div>
      <div class="col-2" class="productName">
        <dl>
          <li class="productId">A122222222</li>
          <li> <a href="#商品網址" class="itemNameLink">多功能型摺疊輪椅</a></li>
          <lable class="promoteTag">滿千折百</lable>
        </dl>
      </div>
      <div class="col">50元</div>
      <div class="col">30天</div>
      <div class="col">100元</div>
      <div class="col">
        <dl>
          <li class="total">1500元</li>
          <li class="discountTotal">
            <!-- 如果有折扣會顯示折扣後 -->
            1400元
          </li>
        </dl>
      </div>
      <div class="col-2">
        <dl>
          <li>起租日: 2021-08-31</li>
          <li>退租日: 2021-09-29</li>
        </dl>
      </div>
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
	
 <!-- bootstrap -->
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>   

</body>
</html>