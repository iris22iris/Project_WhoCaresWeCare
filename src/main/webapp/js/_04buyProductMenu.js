function track(heart) {
	if (heart.className == "far fa-heart") {
		heart.className = "fas fa-heart";
		Swal.fire({
			position: 'center',
			icon: 'success',
			title: '商品已加入追蹤清單',
			showConfirmButton: false,
			timer: 1000
		})
	} else {
		heart.className = "far fa-heart";
		Swal.fire({
			position: 'center',
			icon: 'error',
			title: '商品已取消追蹤',
			showConfirmButton: false,
			timer: 1000
		})
	}

}