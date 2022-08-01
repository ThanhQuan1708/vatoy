<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<link rel="stylesheet" href="Frontend/css/detailsp.css">
</head>
	<script type="text/javascript">	  
	$(document).ready(function() { 
	  	var priceConvert = accounting.formatMoney(${product.getPrice()})+' VND';
		document.getElementById("priceConvert").innerHTML= priceConvert;
		  
	  });
	</script>
<style>
	.important{
		font-weight: bold;
		font-size: 15px;
	}
	.details p{
		padding: 5px;
	}
	.product-title{
		font-weight: bold;
		font-size: 20px;
	}
	.price{
		padding: 5px;
		font-size: 17px;
		font-weight: bold;
	}
</style>
<body>
	<div class="container">
		<div class="card">
			<div class="container-fliud">
				<div class="wrapper row">
					<div class="preview col-md-6">
						<div class="preview-pic tab-content">
						  <div class="tab-pane active" id="pic-1"><img src="/toystore/img/${product.getId()}.png" /></div>
						</div>		
					</div>
					<div class="details col-md-6" style="margin-top: 90px">
						<p style="display:none" id="spid">${product.getId()}</p>
						<h2 class="product-title">${product.getName()}</h2>
						<h2 class="price" style="text-transform: uppercase; font-size: 18px">Mô tả sản phẩm</h2>
						<p class="product-description"><span class="important">Thể loại:</span> ${product.category.name}</p>
						<p class="product-description"><span class="important">Thương hiệu:</span> ${product.brand.name}</p>
						<p class="product-description"><span class="important">THÔNG TIN CHUNG:</span> ${product.getDescription()}</p>
						<p class="product-description"><span class="important">Xuất xứ:</span> ${product.getOrigin()}</p>
						<p class="product-description"><span class="important">Vật liệu :</span> ${product.getMaterial()}</p>
						<p class="product-description"><span class="important">Chủ đề:</span> ${product.getTopic()}</p>
						<h4 class="price" id ="blabla">Giá bán: <span id="priceConvert"></span></h4>
						<div class="action">
							<button class="add-to-cart btn btn-warning" type="button">
							<span class="glyphicon glyphicon-shopping-cart pull-center"></span> Giỏ hàng</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

<script src="<c:url value='/js/client/detailspAjax.js'/>" ></script>	 