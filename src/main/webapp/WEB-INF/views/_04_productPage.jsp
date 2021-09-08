<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 數字格式化標籤 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
<title>商品頁面: ${product.prodName}</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<!-- bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- jquery cnd -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- css -->
<link rel="stylesheet" href="<c:url value='/css/_04productPage.css' />">

<link rel="stylesheet" href="<c:url value='/css/commonStyle.css' />"
	type="text/css">

<!-- icon -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />

<!-- 圖片切換 -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

<!-- 引入script -->
<script src="<c:url value='/js/_04productPage.js' />"></script>

</head>

<body>
	<div id="body">
		<div id="content">
	
		<!-- header start -->
		<!-- 引入共同的頁首 -->
		<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />
		<!-- header end -->

		<!-- main start -->
		<div class="container-fluid d-flex">
<!-- 		<div class="allPage"> -->


		<!-- Side-List Start -->
		<jsp:include page="/WEB-INF/fragment/buySideMenu.jsp"/>
	
	<!-- main-right-side start -->
			<div class="productPage col-9 ">
				<div class="catagory col-11">
					<i class="fas fa-th-large px-2"></i>
					<a href="#大分類">商品大分類</a>
					<i class="fas fa-angle-right"></i>
					<a href="#小分類">商品小分類:${product.productTypeBean.prodName}</a>
					<hr>
				</div>

				<!-- 商品圖片&名稱 start -->
				<div class="productShell col-11">
					<div class="pictureandinfo d-flex ">
						<div class="productPicture col-4">
							<div id="carouselExampleDark"
								class="carousel carousel-dark slide" data-bs-ride="carousel">
								<!-- 商品大圖片 start -->

								<div class="carousel-inner" style="border-radius: 10px">
									<div class="carousel-item active" data-bs-interval="10000">
										<img src="<c:url value='/images/product/${product.fileName}' />"
											class="img1" alt="...">
										<div class="carousel-caption d-none d-md-block"></div>
									</div>

									<div class="carousel-item" data-bs-interval="2000">
										<img src="<c:url value='/images/forProductPage_2.png' />"
											class="img2" alt="...">
										<div class="carousel-caption d-none d-md-block"></div>
									</div>

									<div class="carousel-item">
										<img src="<c:url value='/images/forProductPage_3.png' />"
											class="img3" alt="...">
										<div class="carousel-caption d-none d-md-block"></div>
									</div>
								</div>
								<!-- 商品大圖片 end -->
							</div>
							<!-- 商品小圖片 start -->
							<div  class="smallImage">
                                <div>
							<input type="image" data-bs-target="#carouselExampleDark"
								data-bs-slide-to="0" name="submit_Btn" id="submit_Btn"
								src="<c:url value='/images/product/${product.fileName}' />"
								style="border-radius: 10px"> <input type="image"
								data-bs-target="#carouselExampleDark" data-bs-slide-to="1"
								name="submit_Btn" id="submit_Btn"
								src="<c:url value='/images/forProductPage_2.png' />"
								style="border-radius: 10px"> <input type="image"
								data-bs-target="#carouselExampleDark" data-bs-slide-to="2"
								name="submit_Btn" id="submit_Btn"
								src="<c:url value='/images/forProductPage_3.png' />"
								style="border-radius: 10px">
						</div>
					</div>
						<!-- 商品小圖片 end -->
				</div>
					<!-- 商品狀態 start -->
						<div class="productInfo col-8">
							<form id="form1" name="form1" method="GET" action="">
								<label for="fname" class="eee">商品編號${product.prodId}</label><br>
								<h2>商品名稱:${product.prodName}</h2>
								<h1 id="commodityname"></h1>
								<br> <label for="fname">衛部醫器製壹字第000936號</label><br> <label
									for="fname">價格: ${product.price}元</label><br> <label for="fname">數量</label>
								: &nbsp; <input type="number" name="" min="0" max="10" value="1"
									style="border-radius: 6px"><br>
								<!-- <label for="fname">租賃數量</label> : &nbsp; <input type="number" name="" min="0" max="10" value="1"><br> -->
									<label for="fname">庫存數量:${product.stock}個</label> 
								<label for="fname">商品規格</label> :<br> <label for="fname">運送方式</label>
								: <input type="checkbox"
									style="height: 15px; margin-right: -15px;">物流宅配<input
									type="checkbox" style="height: 15px; margin-right: -15px;">店到店<br>
								<br>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<c:choose> 
						
						  <c:when test="${product.stock >0 }">   
							<a href="<c:url value='/_04_buyCheckout' />">
							<button class="btn btn-outline-secondary " 
							 role="button" 							
							type="button" class="btn btn-secondary" >直接購買</button>
							
						 	</a>	
						  </c:when> 
						
								
						  <c:otherwise>   
							<button class="btn btn-outline-secondary " data-bs-toggle="modal" href="#exampleModalToggle" role="button"  type="button" class="btn btn-secondary" data-bs-dismiss="modal">已售完</button>
					   	  </c:otherwise> 
							
						</c:choose>
								
								
								
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button class="btn btn-outline-secondary">加入購物車</button>
							</form>
						</div>
						<!-- 商品狀態 end -->
					</div>
				</div>

				<!-- 商品詳情 start -->
				<div class="productDet col-12">
					<div>
						<div class="col ml-4 mt-2">
							<h3 class="fw-bold">商品詳情</h3>
						</div>
						<p>1. 此產品符合長照2.0/身心障礙者輔具補助項目【EC02輪椅-B款(輕量化
							量產型)、EC04輪椅附加功能-A款(具利於移位功能)】 2. 如欲了解長照2.0補助申請資訊，請撥打1966長照服務專線諮詢。
							介護+自推：能透過介護者推送，或由使用者自行推送。 ● 6 種座寬可選 (臀部寬度)：36cm / 39cm / 42cm /
							45cm / 48cm / 51cm ● 4 式座高可調 (小腿長度)：42cm / 45cm / 48cm / 51cm ● 2
							段座深可調 (大腿長度)：41cm / 44cm ● 3 種仰角可調 (軀幹角度)：0度 / 3度 / 6度 產品規格</p>
						<p>座寬 : 36cm / 39cm / 42cm / 45cm / 48cm / 51cm (可選) 座深 : 41cm
							/ 44cm (可調) 背高 : 37cm 前座高 : 42cm / 45cm / 48cm / 51cm (可調) 座仰角 :
							0度 / 3度 / 6度 (可調) 車體全寬 : 座寬 + 21cm 車體全長 : 103cm 車體全高 : 89cm /
							92cm / 95cm / 98cm (根據不同座高) 收合尺寸 : 84cm x 36cm x 68cm (折背、拆腳、拆後輪)
							全車重 : 19.7kg (以座寬45cm為量測基準) 車體重 : 12.2kg (以座寬45cm為量測基準 / 不含腳架、後輪)
							最大荷重 : 150kg 網路販售第一等級醫療器材揭載事項 中文品名："NOVA" 輪椅(未滅菌)
							衛字核可字號：衛署醫器製壹字第000936號 (到期日：115/01/10) 藥商名稱：光星骨科復健器材股份有限公司
							製造廠：光星骨科復健器材股份有限公司二廠 製造廠址：臺中市神岡區豐洲路1096、1102號 販賣藥商名稱：樂齡生活事業股份有限公司
							販賣藥商登記地址：台北市大安路一段179號 販賣業藥商許可執照：北市衛藥販(安)字第620102V969號
							諮詢專線：(02)2577-5025</p>
					</div>
				</div>
				<!-- 商品詳情 end -->



				<div class="row d-flex col-12">
					<!-- 評價顯示 start -->
					<div class="row">
					</div>
					<div class="product-rate-title col-6">
						<h2>
							<i class="fas fa-th-large px-3"></i>商品評價
						</h2>
					</div>
					<div class="score col-6">
					評分
					<c:set value="0" var="sum"/>
					<c:set value="0" var="commentscount"/>            
					<c:forEach items="${comments}" var="comment">                 
					<c:set value="${sum+comment.rate}" var="sum"/> 
					<c:set value="${commentscount+1}" var="commentscount"/> 
					</c:forEach>
					<fmt:formatNumber value="${sum/commentscount}" pattern=".0" type="number"/>
					/ ${commentscount}人評價</div>
					<!-- 評價顯示 end -->
				</div>



				<div class="col-12 d-flex">
					<!-- 留言板頭像 start -->
				
					<div id="parent">
					<!-- 各項區塊 start -->
					<c:forEach items="${comments}" var="comment">
						<div id="box">
							<em>				  
             <tr style="text-align: center;font-size: 10px;">
                <td> ${comment.customerBean.custName}</td>
               <td> ${comment.classify}</td>
               <td>${comment.rate}</td>                                         
               <td> <fmt:formatDate value="${comment.commentDate}" pattern="yyyy年MM月dd日HH点mm分ss秒" /></td>  
                                                     
             </tr>
             <br>        
			 </em>
						</div>
						 </c:forEach>
					<!-- 各項區塊 end -->
					</div>
					
					<!-- 留言板 end -->
				</div>
			</div>
		</div>

		<!-- footer Start! -->
		<!-- 引入共同的頁尾 -->
		<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />
		<!-- footer End! -->

	</div>

</div>
</body>

</html>