<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value='./css/_05member_management.css' />"
	type="text/css">
      <!-- bootstrap -->
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
      rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"></linkrel>
      <!-- icon -->
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" referrerpolicy="no-referrer" /> 

<!-- 引入共同的頁首 -->
	<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />





<title>會員專區</title>
</head>
<body id="body">
<!-- main start -->
    <div class="main col-12 m-5 d-flex justify-content-center  p-3 rounded">
        
        <!-- member profile start -->
        <div class="row ">

            <div class=" 
                col-12  col-sm-12 col-md-4 "  >
                <div class="col-12 ">
                    <div class="title border rounded-circle d-flex justify-content-center align-items-center ">
                        <h1>
                            會員資料
                        </h1>
                    </div>
                </div>

                <div class="col-12 ">
                    <div class="mt-5"><h1><i class="fas fa-caret-right"></i> 會員資料修改</h1></div>
                    <div class="mt-5"><h1><i class="fas fa-caret-right"></i>商品追蹤清單</h1></div>
                    <div class="mt-5"><h1><i class="fas fa-caret-right"></i>修改密碼</h1></div>
                </div>
            </div>
            <!-- member profile end -->
            
            <!-- order status start -->
            <div class="
                col-12 col-md-4 col-sm-12 bcglightgray text-dark p-3 rounded"  >
                <div class="col-12 bcglightgray">
                    <div class="title border border rounded-circle d-flex justify-content-center align-items-center ">
                        <h1>
                            訂單查詢
                        </h1>
                    </div>
                </div>

                <div class="col-12 bcglightgray">
                    <div class="mt-5 "><h1 class="bcglightgray"><i class="fas fa-caret-right bcglightgray"></i> 租賃記錄查詢</h1></div>
                    <div class="mt-5 "><h1 class="bcglightgray"><i class="fas fa-caret-right bcglightgray"></i> 購買記錄查詢</h1></div>
                    <div class="mt-5 "><h1 class="bcglightgray"><i class="fas fa-caret-right bcglightgray"></i> 預約記錄查詢</h1></div>
                 
                </div>
            </div>
            <!-- order status end -->
        
            <!-- customer service reply start -->
            <div class="
                col-12 col-md-4 col-sm-12  p-3 rounded"  >
                <div class="col-12 ">
                    <div class="title  border rounded-circle d-flex justify-content-center align-items-center ">
                        <h1>
                            客服回覆
                        </h1>
                    </div>
                </div>

                <div class="col-12 ">
                    <div class="mt-5"><h1><i class="fas fa-caret-right"></i> 客服記錄查詢</h1></div>
                </div>
            </div>
        </div>
        <!-- customer service reply end -->


    <!-- main end -->
    </div>

<!-- bootstrap -->
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<!-- 引入共同的頁尾 -->
	<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />

</body>
</html>