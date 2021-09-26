<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<title>租賃設備訂單管理</title>
<link rel="stylesheet"
	href="<c:url value='/css/_06rentOrderQuery.css' />">
<!-- bootstrap -->
<link
	href="<c:url value='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' />"
	rel="stylesheet">
<!-- icon -->
<link rel="stylesheet"
	href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css'/>" />
<!-- sweetAlert -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<!-- 引入共同的頁首 -->
<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />
<script defer
	src="https://use.fontawesome.com/releases/v5.0.10/js/all.js"></script>
<script defer src="https://use.fontawesome.com/4e44561671.js"></script>
<script>
	window.onload = function() {
		searchBox();
	}
</script>
</head>

<body>
	<!-- main start -->
	<div id="body">
		<div id="content">



			<!--title start 標題開始-->
			<div class="title ">
				<div class="rentitemtitle p-3">
					<i class="fas fa-th-large pe-2" aria-hidden="true"></i> 租賃設備訂單管理
				</div>
				<!-- header-search start 查詢訂單功能開始-->
				<div class=" header-search container">
					<form class="row align-items-center">
						<div class="col-3 d-flex justify-content-end">
							<label class="enter ">${customerBean.custName}您好，請輸入訂單編號查詢:</label>
						</div>
						<div class="col-3">
							<input type="search" class="form-control " id="enter"
								onkeyup="value=value.replace(/[^\d]/g,'')">
						</div>
						<div class="col-1">
							<button type="button" class="btn btn-warning "
								onclick="rentOrderSearch()">查詢</button>
						</div>
					</form>
				</div>
				<!-- header-search end 查詢訂單功能結束-->
			</div>
			<!-- title end 標題結束 -->
			<div id="searchResult" class="my-3 text-center">${orderNotFound}</div>
			<c:choose>
				<c:when test="${!empty ordBean}">

					<!-- queryForm start  訂單區塊開始-->
					<div class="queryForm container">

						<!-- top Container start 上方訂單框格 -->
						<div class=" col-12 topContainer p-3">
							<!-- itemcontainer  star  訂單進度條開始-->
							<div class="orderProgress">
								<div class="list">
									<li id="confirmed"><i
										class="fa fa-solid fa-list fa-2x " aria-hidden="true"></i>訂單成立</li>
									<li id="paid"><i
										class='fas fa-money-bill-alt fa-2x' aria-hidden="true"></i>已收款</li>
									<li id="processing"><i class="fa fa-archive fa-2x"
										aria-hidden="true"></i>出貨中</li>
									<li id="shipping"><i class="fa fa-truck fa-2x "
										aria-hidden="true"></i>配送中</li>
									<li id="arrived"><i class="fa fa-check-circle fa-2x"
										aria-hidden="true"></i>已送達</li>
								</div>
							</div>
							<!-- itemcontainer end  訂單進度條結束-->

							<!-- itemform  start  訂單表格開始-->
							<div class="itemForm row px-3">

								<div class=" itemFormTitle col-12">
									<div class="col-1"></div>
									<div class="col-2">訂單號碼</div>
									<div class="col-2">訂單日期</div>
									<div class="col-2">訂單金額</div>
									<div class="col-2">到貨方式</div>
									<div class="col-3">訂單備註</div>
								</div>

								<div class=" itemFormDetail col-12">
									<!-- accordion-header-button  start  訂單資訊 開始-->
									<div
										class="col-1 d-flex justify-content-center align-items-center">
										<button class="accordion-button collapsed" id="flush-heading"
											type="button" data-bs-toggle="collapse"
											data-bs-target="#flush-collapseOne" aria-expanded="false"
											aria-controls="flush-collapseOne"></button>
									</div>
									<div class="col-2">${ordBean.ordPK.category}${ordBean.ordPK.ordId}</div>
									<div class="col-2">${ordBean.orderDate}</div>
									<div class="col-2">${ordBean.ordTotal}</div>
									<div class="col-2">${ordBean.delivery}</div>
									<div class="col-3">${orderMark}</div>
									<!-- accordion-header-button  end  訂單資訊結束-->
								</div>
							</div>
							<!-- itemForm end -->

						</div>
						<!-- top Container end 上方訂單框格 -->


						<!-- bottom container start 下拉式表格(訂單詳細資訊) 開始-->
						<div class="col-12 accordion accordion-flush bottomContainer"
							id="accordionFlushExample">
							<div class="accordion-item">
								<div id="flush-collapseOne" class="accordion-collapse collapse"
									aria-labelledby="flush-headingOne"
									data-bs-parent="#accordionFlushExample">
									<!-- accordion-body  start  表格 開始-->
									<div class="accordion-body">
										<!-- order detail title start 表格表頭 開始-->
										<div class="row  orderDetailTitle">
											<div class="col-2">商品編號</div>
											<div class="col-2">商品名稱</div>
											<div class="col-2">租賃顧客編號</div>
											<div class="col-2">設備歸還日期</div>
											<div class="col-2">租賃狀態</div>
											<div class="col-2">設備歸還</div>
										</div>
										<!-- order detail title end 表格表頭 結束-->
										<c:forEach var="rentItem" items="${rentItems}" varStatus="vs">
											<c:choose>
												<c:when test="${!empty rentItem}">
													<!-- order detail start 表格內容開始-->
													<div class="row orderDetail">
														<div class="col-2">${rentItem.rentProductBean.prodId}</div>
														<div class="col-2">${rentItem.rentProductBean.prodName}</div>
														<div class="col-2">${ordBean.customerBean.custId}</div>
														<div class="col-2">${rentItem.returnDate}</div>
														<div class="col-2">${rentItem.rentStatus}</div>
														<div
															class="col-2 d-flex justify-content-evenly align-items-center">

															<c:choose>
																<c:when test="${rentItem.rentStatus == '租賃中'}">
																	<form method="POST"
																		action="<c:url value='/rentOrderManage/returnProduct' />"
																		enctype='multipart/form-data'>
																		<input type="submit" class="btn btn-warning"
																			onClick="timedMsg()" value="歸還"> <input
																			type="hidden" name="custId"
																			value="${customerBean.custId}" /> <input
																			type="hidden" name="category"
																			value="${ordBean.ordPK.category}" /> <input
																			type="hidden" name="ordId"
																			value="${ordBean.ordPK.ordId}" /> <input
																			type="hidden" name="prodSerialNum"
																			value="${rentItem.rentItemPK.prodSerialNum}" />
																	</form>
																</c:when>
															</c:choose>
														</div>
													</div>
													<!-- order detail end 表格內容結束-->

												</c:when>
											</c:choose>
										</c:forEach>
									</div>
									<!-- accordion-body  end  表格 結束-->
								</div>
							</div>
						</div>
						<!-- bottom container end 下拉式表格(訂單詳細資訊) 結束-->
					</div>
					<!-- queryForm end  訂單區塊結束-->
				</c:when>
			</c:choose>
			<!-- btn start 返回-->
			<div
				class=" button col-12 pb-3 d-flex justify-content-center align-items-center">
				<button type="button" class="btn btn-secondary" onClick="back()">返回</button>
			</div>
			<!-- btn end 返回-->
		</div>

		<!-- 引入共同的頁尾 -->
		<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />


	</div>
	<!-- main end -->


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
	<script src="<c:url value='/js/orderQuery.js' />"></script>
	<script type="text/javascript">
		function timedMsg() {
			Swal.fire({
				position : 'center',
				icon : 'success',
				title : '已歸還設備',
				showConfirmButton : false,
				timer : 1000
			})
		}

		function rentOrderSearch() {
			let ordId = document.getElementById("enter").value;
			if (ordId != null && ordId != "") {
				location.href = "?category=R" + "&ordId="
						+ document.getElementById("enter").value;
			} else {
				document.getElementById("searchResult").innerHTML = "請輸入合法的訂單編號";
			}
		}

		function back() {
			location = "<c:url value='/_05_member_management' />";
		}

		window.addEventListener('load', orderStatus("${ordBean.orderStatus}"));
	</script>

</body>

</html>