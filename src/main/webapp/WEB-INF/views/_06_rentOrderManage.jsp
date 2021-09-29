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
							<label class="enter ">${customerBean.custName}您好，請選擇欲管理的訂單:</label>
						</div>
						<div class="col-3">
							<select name="sortType" class="orderSelect" onChange="rentOrderSearch(this.value)">
								<option selected disabled>請選擇訂單編號</option>
								<c:forEach var="ord" items="${ordBeanList}">
									<option value="${ord.ordPK.ordId}">R${ord.ordPK.ordId}</option>
								</c:forEach>
							</select>
						</div>
					</form>
				</div>
				<!-- header-search end 查詢訂單功能結束-->
			</div>
			<!-- title end 標題結束 -->
			<div id="searchResult" class="my-3 text-center">${orderNotFound}</div>
			<c:choose>
				<c:when test="${!empty ordBean}">

						<!-- bottom container start 下拉式表格(訂單詳細資訊) 開始-->
							<div class="col-12 bottomContainer"
										id="accordionFlushExample">
							<div class="">
								<div id="" class="">
									<div class="accordion-body">
										<!-- order detail title start 表格表頭 開始-->
										<div class="row  orderDetailTitle">
											<div class="col-2">商品編號</div>
											<div class="col-2">商品名稱</div>
											<div class="col-2">設備租賃日期</div>
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
														<div class="col-2">${rentItem.startDate}</div>
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
									<!--end  表格 結束-->
								</div>
							</div>
						</div>
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

		function rentOrderSearch(ordId) {
			if (ordId != null && ordId != "") {
				location.href = "?category=R" + "&ordId=" + ordId;
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