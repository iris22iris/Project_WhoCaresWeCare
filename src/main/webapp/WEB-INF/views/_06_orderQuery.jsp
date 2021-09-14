<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<title>購買商城訂單查詢</title>
<link rel="stylesheet" href="<c:url value='/css/_06orderQuery.css' />">
<!-- bootstrap -->
<link
	href="<c:url value='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' />"
	rel="stylesheet">
<!-- icon -->
<link rel="stylesheet"
	href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css'/>" />
<!-- sweetAlert -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

<script defer
	src="https://use.fontawesome.com/releases/v5.0.10/js/all.js"></script>
<script defer src="https://use.fontawesome.com/4e44561671.js"></script>
<script src="<c:url value='/js/orderQuery.js' />"></script>
</head>

<body>
	<!-- main start -->
	<div id="body">
		<div id="content">

			<!-- 引入共同的頁首 -->
			<jsp:include page="/WEB-INF/fragment/topMVC.jsp" />

			<!--title start 標題開始-->
			<div class="title ">
				<div class="buyitemtitle p-3">
					<i class="fas fa-th-large pe-2" aria-hidden="true"></i> 購買商城訂單紀錄
				</div>
				<!-- header-search start 查詢訂單功能開始-->
				<div class=" header-search container">
					<form class="row align-items-center">
						<div class="col-3 d-flex justify-content-end">
							<label class="enter ">${customerBean.custName}您好，請輸入訂單編號查詢:</label>
						</div>
						<div class="col-3">
							<input type="search" class="form-control " id="enter">
						</div>
						<div class="col-1">
							<button type="button" class="btn btn-warning "
								onclick="orderSearch()">確認</button>
						</div>
					</form>
				</div>
				<!-- header-search end 查詢訂單功能結束-->
			</div>
			<!-- title end 標題結束 -->

			<c:choose>
				<c:when test="${!empty ordBean}">

					<!-- queryForm start  訂單區塊開始-->
					<div class="queryForm container">

						<!-- top Container start 上方訂單框格 -->
						<div class=" col-12 topContainer p-3">
							<!-- itemcontainer  star  訂單進度條開始-->
							<div class="orderProgress">
								<div class="list">
									<li id="orderStatus1"><i
										class="fa fa-solid fa-list fa-2x " aria-hidden="true"></i>訂單成立</li>
									<li id="orderStatus2"><i
										class='fas fa-money-bill-alt fa-2x' aria-hidden="true"></i>已收款</li>
									<li id="orderStatus3"><i class="fa fa-archive fa-2x"
										aria-hidden="true"></i>出貨中</li>
									<li id="orderStatus4"><i class="fa fa-truck fa-2x "
										aria-hidden="true"></i>配送中</li>
									<li id="orderStatus5"><i class="fa fa-check-circle fa-2x"
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
									<div class="col-2">付款方式</div>
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
									<div class="col-2">${ordBean.payment}</div>
									<div class="col-3">${ordBean.orderMark}</div>
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
											<div class="col-1">商品編號</div>
											<div class="col-4">商品圖片</div>
											<div class="col-2">商品名稱</div>
											<div class="col-1">數量</div>
											<div class="col-1">單項總額</div>
											<div class="col-2">評價</div>
										</div>
										<!-- order detail title end 表格表頭 結束-->
										<c:forEach var="buyItem" items="${buyItems}">
											<c:choose>
												<c:when test="${!empty buyItem}">
													<!-- order detail start 表格內容開始-->
													<div class="row orderDetail">
														<div class="col-1">${buyItem.productBean.prodId}</div>
														<div class="col-4 detailImg ">
															<img
																src="<c:url value='/images/product/${buyItem.productBean.fileName}' />"
																alt="商品圖片">

														</div>
														<div class="col-2">${buyItem.productBean.prodName}</div>
														<div class="col-1">${buyItem.prodQTY}</div>
														<div class="col-1">${buyItem.itemSum}元</div>
														<!-- 留下評價按鈕會跳出一個小視窗 -->
														<c:choose>
															<c:when test="${empty commentBean}">

																<div
																	class="col-2 d-flex justify-content-evenly align-items-center">
																	<button type="button" class="btn btn-warning"
																		data-bs-toggle="modal" data-bs-target="#exampleModal">
																		留下評價</button>
																</div>
															</c:when>
															<c:otherwise>
																<div
																	class="col-2 d-flex justify-content-evenly align-items-center">
																	<!-- 功能：留下評價以後，留下評價的btn就不能按了，然後修改評價的btn才可以按，裡面顯示已寫的評價，按下編輯按鈕才可以修改，另外需有取消按鈕跟確認修改按鈕 -->
																	<button type="button" class="btn btn-warning"
																		data-bs-toggle="modal" data-bs-target="#exampleModal">
																		修改評價</button>
																</div>
															</c:otherwise>
														</c:choose>
													</div>
													<!-- order detail end 表格內容結束-->
												</c:when>
											</c:choose>
										</c:forEach>
									</div>
									<!-- accordion-body  end  表格 結束-->

									<!-- modal fade  star  使用心得/評分小視窗 開始-->
									<div class="modal fade " id="exampleModal" tabindex="-1"
										aria-labelledby="exampleModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content ">
												<div class="modalTitle modal-body ">
													<h2 class="titleWord col-11" id="exampleModalLabel">使用心得/評分</h2>
													<button type="button" class="btn-close col-1"
														data-bs-dismiss="modal" aria-label="Close"></button>
												</div>
												<div class="modal-body">
													<div class="productName col-12">${buyItem.productBean.prodName}</div>
												</div>
												<div class="modal-body">
													<form class="rateForm col-12 ">
														<i class="fa fa-certificate fa-1x " aria-hidden="true"></i>
														<label>評分:</label><br>
														<div>
															<input type="radio" name="rate" value="5"> 5分 <input
																type="radio" name="rate" value="4"> 4分 <input
																type="radio" name="rate" value="3"> 3分 <input
																type="radio" name="rate" value="2"> 2分 <input
																type="radio" name="rate" value="1"> 1分<br>
														</div>
													</form>
												</div>
												<div class="modal-body">
													<i class="fa fa-edit fa-1x " aria-hidden="true"></i> <label>評價:</label>
													<div class="col-12 comment">
														<textarea name="description" id="inputDescription"
															cols="30" rows="5"></textarea>
													</div>
												</div>
												<div class="modalBtn modal-footer">
													<form>
														<input type="button" class="btn btn-warning" value="送出評價"
															onClick="timedMsg(this)">
														<button type="button" class="btn btn-secondary"
															data-bs-toggle="modal" data-bs-target="#exampleModal">
															取消</button>
													</form>
												</div>
											</div>
										</div>
									</div>
									<!-- modal fade  end  使用心得/評分小視窗 結束-->
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
				<button type="submit" class="btn btn-secondary">返回</button>
			</div>
			<!-- btn end 返回-->
		</div>

		<!-- 引入共同的頁尾 -->
		<jsp:include page="/WEB-INF/fragment/bottomMVC.jsp" />


	</div>
	<!-- main end -->


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript">
		function timedMsg() {
			Swal.fire({
				position : 'center',
				icon : 'success',
				title : '已送出評價',
				showConfirmButton : false,
				timer : 1000
			})
		}

		window.addEventListener('load', orderStatus(`${ordBean.orderStatus}`));
	</script>

</body>

</html>