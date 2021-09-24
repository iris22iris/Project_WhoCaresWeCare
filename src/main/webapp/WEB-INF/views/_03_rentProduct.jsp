<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 數字格式化標籤 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 計算文字處理標籤 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
		
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value='/css/_03rentProduct.css' />"
	type="text/css">
<link rel="stylesheet" href="<c:url value='/css/commonStyle.css' />"
	type="text/css">

<title>租賃設備頁面: ${rentProduct.prodName}</title>

<!-- bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- jquery cnd -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- icon -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />

<!-- 圖片切換 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<!-- 引入共同的頁首 -->
		<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />

<script>
	window.onload = function() {
		searchBox();
	}
</script>
</head>

<body>
			<!-- 取其stock -->									 
			<c:set value="0" var="stocksum"/>					         
			<c:forEach items="${allserialstocks}" var="serialstock">  				            
			<c:set value="${stocksum+serialstock.stock}" var="stocksum"/> 								
			</c:forEach>
			<!-- 計算評價相關資料 -->	
			<c:set value="0" var="sum"/>
			<c:set value="0" var="commentscount"/>            
			<c:forEach items="${comments}" var="comment">                 
			<c:set value="${sum+comment.rate}" var="sum"/> 
			<c:set value="${commentscount+1}" var="commentscount"/> 
			</c:forEach>
 <div id="contentpopup">
            

        </div>
	<div id="body">
	 <div id="content">
		<!-- header start -->
		
		<!-- header end -->

		<!-- main start -->
		<div class="container-fluid d-flex">
		<!-- Side-List Start -->
		<jsp:include page="/WEB-INF/fragment/rentSideMenu.jsp" />	 
			
		<!-- Side-List End -->

			<!-- Main-right-side start -->
			<div class="productPage col-9 ">
				
				<!-- 商品分類title start -->
				<div class="catagory">
					<i class="fas fa-th-large px-2"></i>
					${maincategorys[0].prodName}類
					
					<i class="fas fa-angle-right"></i>			
					<a href="<c:url value='/rentMenu/${rentProduct.productTypeBean.prodType}' />">					
						${rentProduct.productTypeBean.prodName}</a>
				</div>
				<!-- 商品分類title end -->

				
				<!-- 商品圖片&名稱 start -->
				<div class="productShell col-11">
					<div class="pictureandinfo">
						<div class="productPicture col-4">
							<div id="carouselExampleDark"
								class="carousel carousel-dark slide" data-bs-ride="carousel">
								<!-- 商品大圖片 start -->

								<div class="carousel-inner" style="border-radius: 10px">
									<div class="carousel-item active" data-bs-interval="10000">
										<img src="<c:url value='/images/product/${rentProduct.fileName}' />"
											class="img1" alt="...">
										<div class="carousel-caption d-none d-md-block"></div>
									</div>

									<div class="carousel-item" data-bs-interval="2000">
										<img src="<c:url value='/images/forProductPage_2.png' />"
											class="img2" alt="...">
										<div class="carousel-caption d-none d-md-block"></div>
									</div>

									<div class="carousel-item">
										<img src="<c:url value='/images/forProductPage_2.png' />"
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
								src="<c:url value='/images/product/${rentProduct.fileName}' />"
								style="border-radius: 10px"> <input type="image"
								data-bs-target="#carouselExampleDark" data-bs-slide-to="1"
								name="submit_Btn" id="submit_Btn"
								src="<c:url value='/images/forProductPage_2.png' />"
								style="border-radius: 10px"> <input type="image"
								data-bs-target="#carouselExampleDark" data-bs-slide-to="2"
								name="submit_Btn" id="submit_Btn"
								src="<c:url value='/images/forProductPage_2.png' />"
								style="border-radius: 10px">
								</div>
							</div>
							<!-- 商品小圖片 end -->
						</div>

						<!-- 商品基本資訊 start -->
						<div class="productInfo col-7">
							<form id="form1" name="form1" method="GET" action="">
								<div class="smallStyle col-12">
									編號${rentProduct.prodId} 
								</div>
								
								<div class="col-12">
								<h2>${rentProduct.prodName}</h2>
								</div>
								
								<div class="smallStyle">衛部醫器製壹字第000936號</div> 
								
								<c:if test="${!empty rentProduct.promotionBean}">
									<div>
										<span id="commodityname" >
											${rentProduct.promotionBean.promoTag}
										</span>
									</div>
								</c:if>																

								<div class="col-12 hidden"></div>

								<div class="normalStyle">
									<div class="col-4">
									租賃價格：</div>
									<div class="col-4 price">
									${rentProduct.price} 元/日
									</div>
								</div>

							<c:choose> 
						  		  <c:when test="${stocksum >0 }">   
								<div class="normalStyle">
									<div class="col-4">
									租賃天數:</div>
									<div class="col-4">
									<input type="number" onkeydown="if(event.keyCode==13){return false;}"  min="0" max="365" value="0" id="days"
									 onchange="ShowDays(this.id)">
									</div>
								</div>																						
								<div class="normalStyle">
									<div class="col-4">
									預計租賃期間:</div>
									<div class="col-4" id = "expectedrentperiod">
									
									<script type="text/javascript">
									 let t = new Date();//今天時間
									 let t_s = t.getTime();//轉化為時間戳毫秒數
									
									 function ShowDays(x){
										　let Days=document.getElementById(x).value;
										
										//t.setTime(t_s + 1000 * 60 * 60 * 24 *(日期) );設定預約起始日
										 let begin = new Date();
									 let end = new Date();
										begin.setTime  (t_s + 1000 * 60 * 60 * 24 * 7);
										
										end.setTime (begin.getTime()+ 1000 * 60 * 60 * 24 * (Days));
																										
										document.getElementById("expectedrentperiod").innerText="開始: "+(formatDate(begin))+"\r"+"結束: "+(formatDate(end));
									 }
									 const formatDate = (date)=>{
											let formatted_date = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() 
											 return formatted_date;										 
											}
														
									</script>
									
											
																									
									</div>
								</div> 
							</c:when>
						</c:choose>
								<div class="normalStyle">
									<div class="col-4">
									庫存數量:</div>
									<div class="col-4">
									
									${stocksum}個
																		
									</div>
								</div> 
								<c:choose> 
						  		  <c:when test="${stocksum == 0 }">  
								<div class="normalStyle">
									<div class="col-4">
									目前等待人數:</div>
									<div class="col-4">									
									${fn:length(reservations)}位
									</div>
								</div> 
								 </c:when> 
								 </c:choose> 
								<div class="col-12 d-flex align-items-start">
								<c:choose> 
						  		  <c:when test="${stocksum >0 }">   
									<div class="submitBtn col-4 d-flex justify-content-center">	
									<a href="<c:url value='/_03_rentItemList' />">
									<button class="btn btn-outline-warning " 
									 role="button" type="button">
									直接租賃</button>
						 			</a>
						 			</div>	
						  		  </c:when> 

						  		  <c:otherwise>
								<div class="submitBtn col-4 d-flex justify-content-center">  
									<button class="btn btn-outline-warning " 
									data-bs-toggle="modal" href="#exampleModalToggle" 
									role="button"  type="button" data-bs-dismiss="modal">
										預約候補
									</button>
								</div>
					   	 		  </c:otherwise> 
								</c:choose> 
								<div class="submitBtn col-4 d-flex justify-content-center ">  
									<button type="button" class="btn btn-outline-warning ms-3" data-bs-toggle="modal" data-bs-target="#exampleModal" >
										加入購物車
									</button>
								</div>
								</div> 
							</form>
						</div>
						<!-- 商品基本資訊 end -->
					</div>
				</div>
				<!-- 商品圖片&名稱 end -->


				<!-- 商品詳情 start -->
				<div class="productContent col-11">
					<div class="contentTitle">
						商品詳情 
					</div>
					<p>${rentProductDescription}</p>
					
					<img src="<c:url value='/images/old.jpg' />" alt="">
					<br>
					
					
				</div>
				<!-- 商品詳情 end -->



				<!-- 評價顯示 start -->
				<div class="row d-flex col-12">
					<div class="product-rate-title col-6">
						<h3>
							<i class="fas fa-th-large px-3"></i>
							商品評價
						</h3>
					</div>
					<div class="score col-5">
					評分
		
					<fmt:formatNumber value="${sum/commentscount}" pattern=".0" type="number"/>
					/ ${commentscount}人評價</div>
				</div>

				<!-- 評價內容start -->
				<div class="col-11" >
					<!-- <div style="margin-left: 50px;">
						<a href="#未登入先導至singin_singup"> <img
							src="<c:url value='/images/memberIcon.png' />" alt="會員"
							style="color: rgb(0, 68, 255);">
						</a>
					</div> -->
					
					<c:forEach items="${comments}" var="comment" varStatus="status">
						<div class="comment row">
						  <div class="memberImg no-gutters col-2">
						  	<img src="<c:url value='/getMemberImg?custId=${comment.customerBean.custId}'/>" alt="...">
						  </div>
             			  <div class="col-1"> ${comment.customerBean.custName}</div>
             			  <div class="col-1"> ${comment.rate}分</div>                                         
             			  <div class="col-2">
             			  		評價時間<br> 
							   <fmt:formatDate value="${comment.commentDate}" 
								pattern="yyyy年MM月dd日" />
						 </div>
						 <div class="col-6">							
							${rentProductComments[status.index]}
						 </div>
						</div>
					</c:forEach>
				</div>
				<!-- 評價內容end -->
				<!-- 評價顯示 end -->
				</div>
			</div>
			<!-- main-right-side end -->
		


		<!-- 預約表單畫面 start -->
		<div class="modal fade" id="exampleModalToggle" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
        <div class="modal-dialog ">
          <div class="modal-content">
	            <div class="modal-header">
	                <h2 class="modal-title p-3 col-12 d-flex justify-content-evenly " id="exampleModalLabel">登記預約</h2>  
	            </div>
            	<div class="containerPOPUP p-3  ">
            	
			<form:form method='POST' modelAttribute="reservation" class="row g-3 form">
	         <fieldset>
	      	                	                
	           
	                <div class="form-group d-flex align-items-center">
	                  	<div class="col-4 justify-content-evenly p-1  d-flex align-items-center   ">
							<label style=font-size:22px  for="prodName" class="form-label">商品名稱 :</label>
						</div>
						
						<div class="col-8 justify-content-evenly p-1  d-flex align-items-center ">
							 <label style=font-size:21px; for="prodName" class="form-label">${rentProduct.prodName}</label>
						</div>
	        		</div>
					
	          
	                    
	                <div class="form-group d-flex align-items-center">
	                  	<div class="col-4 justify-content-evenly p-1  d-flex align-items-center   ">
							<label style=font-size:22px  for="waitNum" class="form-label">目前候補人數 :</label>
						</div>
						
						<div class="col-8 justify-content-evenly p-1  d-flex align-items-center ">
							 <label style=font-size:21px; for="waitNum" class="form-label">${fn:length(reservations)}位</label>
						</div>
	        		</div>
	                 <br>  
	                  	                	                 
            	</div>
            	
            <div class="form-group">	
            <div class="hyperlink  col-12 d-flex justify-content-evenly align-items-center">
                <div >
	               <input  type='submit' class='reservationbtn btn  btn-warning' data-bs-target="#exampleModalToggle2" 
	               data-bs-toggle="modal" data-bs-dismiss="modal"value="確認" />
	               <button type="button" class=" reservationbtn btn btn-secondary"  data-bs-dismiss="modal">取消</button>
                </div>
            </div>
            </div> 
            
            
			</fieldset>       
	</form:form>
            
            
            <div class="col-12 justify-content-evenly p-1  d-flex align-items-center">
                當商品到貨時,請於收到通知信48小時內完成預約
            </div>
            <div class="col-12 justify-content-evenly p-1  d-flex align-items-center">
            	若逾期需重新預約候補
            </div>
          </div>
        </div>
        
        
        
        
         
        
    </div>
    <!-- 預約表單畫面 end -->
    
    <!-- 預約完成結果畫面 start -->
      
		<!-- 預約完成結果畫面 end -->
	</div>
		
	
	
	<!-- 引入共同的頁尾 -->
	<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />
	
	
	</div>
	<!-- main end -->


<script type="text/javascript" src="./js/_05loginPopup3.js"></script>

</body>

</html>