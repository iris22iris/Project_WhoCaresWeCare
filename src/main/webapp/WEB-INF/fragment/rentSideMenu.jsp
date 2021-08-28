<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <link rel="stylesheet"
	href="<c:url value='/css/_03rentProductMenu.css' />"> -->
	
		<div class="col-2 ms-4">
				<div style="height: 60px;"></div>
				<div class="text-center">
					<c:forEach var="productType" items="${productTypes}" varStatus="vs">
						<c:choose>
							<c:when test="${productType.prodType.length() == 1}">
								<c:choose>
									<c:when test="${vs.first}">
										<div class="sideList-title">${productType.prodName}類</div>
									</c:when>
									<c:otherwise>
										<div class="sideList-title">${productType.prodName}類</div>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<div class="sideList">
									<a href="<c:url value='/rentMenu/${productType.prodType}' />">${productType.prodName}</a><br>
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>

				<div class="text-center">
					<div class="sideList-title mt-4 discount-title">優惠活動</div>
					<div class="sideList">
						<a href="#">特價專區</a><br>
					</div>
				</div>
			</div>