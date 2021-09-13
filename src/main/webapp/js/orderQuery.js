function orderSearch() {
	location.href = "?category=B" + "&ordId=" + document.getElementById("enter").value;
}

function rentOrderSearch() {
	location.href = "?category=R" + "&ordId=" + document.getElementById("enter").value;
}

function orderStatus(orderStatus) {
	document.getElementById(orderStatus).className = "active";
}
