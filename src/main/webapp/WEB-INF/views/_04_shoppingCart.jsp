<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
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

    <title>搜尋結果{搜尋關鍵字}</title>
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
             <div class="cartList">
                <div class="col-2">
                    <input type="checkbox" id="deleteCart">
                </div>
                <div class="col-3">
                    <img class="productImg" 
                    src="<c:url value='/images/product/A0001.jpg' />" alt="A0001">
                </div>
                <div class="col-5 cartContent">
                    <div></div>
                    <div class="col-12 productId">A10001</div>
                    <div class="col-12 productName">
                        多功能款收折式手動輪椅
                    </div>
                    <div class="col-12" hidden="true">商品標籤</div>
                    <div class="col-12">數量</div>
                    <div class="col-12">折抵</div>
                    <div class="col-12">總金額</div>
                </div>
                <div class="col-2 cartSum">
                    <div></div>
                    <div class="col-12 hidden"></div>
                    <div class="col-12">
                        1000元
                    </div>
                    <div class="col-12 hidden" hidden="true"></div>
                    <div class="col-12 num">
                        <i class="far fa-plus-square pe-2 mb-1"></i>
                        1
                        <i class="far fa-minus-square ps-2 mb-1"></i>
                    </div>
                    <div class="col-12">-50元</div>
                    <div class="col-12">950元</div>
                </div>
             </div>
             <div class="cartList">
                <div class="col-2">
                    <input type="checkbox" id="deleteCart">
                </div>
                <div class="col-3">
                    <img class="productImg" 
                    src="<c:url value='/images/product/A0001.jpg' />" alt="A0001">
                </div>
                <div class="col-5 cartContent">
                    <div></div>
                    <div class="col-12 productId">A10001</div>
                    <div class="col-12 productName">
                        多功能款收折式手動輪椅
                    </div>
                    <div class="col-12" hidden="true">商品標籤</div>
                    <div class="col-12">數量</div>
                    <div class="col-12">折抵</div>
                    <div class="col-12">總金額</div>
                </div>
                <div class="col-2 cartSum">
                    <div></div>
                    <div class="col-12 hidden"></div>
                    <div class="col-12">
                        1000元
                    </div>
                    <div class="col-12 hidden" hidden="true"></div>
                    <div class="col-12 num">
                        <i class="far fa-plus-square pe-2 mb-1"></i>
                        1
                        <i class="far fa-minus-square ps-2 mb-1"></i>
                    </div>
                    <div class="col-12">-50元</div>
                    <div class="col-12">950元</div>
                </div>
             </div>
             <div class="cartList">
                <div class="col-2">
                    <input type="checkbox" id="deleteCart">
                </div>
                <div class="col-3">
                    <img class="productImg" 
                    src="<c:url value='/images/product/A0001.jpg' />" alt="A0001">
                </div>
                <div class="col-5 cartContent">
                    <div></div>
                    <div class="col-12 productId">A10001</div>
                    <div class="col-12 productName">
                        多功能款收折式手動輪椅
                    </div>
                    <div class="col-12" hidden="true">商品標籤</div>
                    <div class="col-12">數量</div>
                    <div class="col-12">折抵</div>
                    <div class="col-12">總金額</div>
                </div>
                <div class="col-2 cartSum">
                    <div></div>
                    <div class="col-12 hidden"></div>
                    <div class="col-12">
                        1000元
                    </div>
                    <div class="col-12 hidden" hidden="true"></div>
                    <div class="col-12 num">
                        <i class="far fa-plus-square pe-2 mb-1"></i>
                        1
                        <i class="far fa-minus-square ps-2 mb-1"></i>
                    </div>
                    <div class="col-12">-50元</div>
                    <div class="col-12">950元</div>
                </div>
             </div>
             <div class="deleteBtn">
                 <button type="submit">刪除</button>
             </div>
         </div>
         <!--Left CartList End -->

         <!--Right Count Start -->
         <div class="col-3 cartRight">
             <div class="amountTitle">結帳明細</div>
             <div class="amount">
                <div class="col-6 amountItem">商品金額：</div>
                <div class="col-6 price">3000元</div>
                <div class="col-6 amountItem">優惠折抵：</div>
                <div class="col-6 price">-150元</div>
                <div class="col-4 amountItem">折扣碼：</div>
                <div class="col-8 price">
                    <input type="text" placeholder="尚未輸入折扣代碼" id="discountCode">
                </div>
                <!-- enter submit以後從hidden改為顯示 -->
                <div class="col-12 submitMsg" hidden="true">您輸入的折扣碼有誤/歡慶88節:折抵100元</div>
                <div class="col-12"><hr style="size:5px;"></div>
                
                <div class="col-6 amountItem">合計金額：</div>
                <div class="col-6 price">2850元</div>
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