<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<link rel="stylesheet" href="Frontend/css/searchResult.css">
<script src="Frontend/js/jquery.min.js"></script>
<script src="Frontend/js/responsiveslides.min.js"></script>
<script src="js/client/accounting.js"></script>
<script src="<c:url value='/js/client/store.js'/>" ></script>
</head>

<script type="text/javascript">	  
	$(document).ready(function() { 
		 $(".grid_1_of_4 .changeToVND").each(function() {
			  var value = accounting.formatMoney($(this).text())+ ' VND';
			  $(this).html(value);
			});
		
		 
	  });
	</script>




<body>
	<div class="clear"> </div>
	<div class="wrap">
		    <div class="content">	    	
		    <div class="content-grids">
		    	    	
		    	<c:if test = "${list.size() == 0}">
		    		<div class="section group">
		    			<h4>Không tìm thấy kết quả nào</h4>
		    		</div>
		    	</c:if>
		    	<c:forEach var="product" items="${list}" varStatus="loop">
		    		<c:if test = "${loop.index != list.size()-1}">
         				<c:if test = "${loop.index%4 == 0}">
         					<div class="section group">
         					<div class="grid_1_of_4 images_1_of_4 products-info" style=" width: 232px; height: 400px;"><a href="product?id=${product.id}">
									<img style="width: 225px; height: 230px" src="/toystore/img/${product.id}.png">
									<h3 style="font-weight: bold; height: 43.2px">${product.name}</h3></a>
									<h3 class="changeToVND">${product.price}</h3>
									<button onClick="addToCart(${product.id})" class="btn btn-warning"><span class= "glyphicon glyphicon-shopping-cart pull-center"></span> Giỏ hàng</button>
									<h3></h3>
							</div>
         				</c:if>
         				
         				<c:if test = "${loop.index%4 != 0}">
         					<c:if test = "${loop.index%4 == 3}">
         						<div class="grid_1_of_4 images_1_of_4 products-info" style=" width: 232px; height: 400px;"><a href="product?id=${product.id}">
									<img style="width: 225px; height: 230px"src="/toystore/img/${product.id}.png">
									<h3 style="font-weight: bold;height: 44px">${product.name}</h3></a>
									<h3 class="changeToVND">${product.price}</h3>
									<button onClick="addToCart(${product.id})" class="btn btn-warning"><span class= "glyphicon glyphicon-shopping-cart pull-center"></span> Giỏ hàng</button>
									<h3></h3>
							</div>
								</div>
							</c:if>
							
							<c:if test = "${loop.index%4 != 3}">
								<div class="grid_1_of_4 images_1_of_4 products-info" style=" width: 232px; height: 400px;"><a href="product?id=${product.id}">
									<img style="width: 225px; height: 230px" src="/toystore/img/${product.id}.png">
									<h3 style="font-weight: bold;height: 44px">${product.name}</h3></a>
									<h3 class="changeToVND">${product.price}</h3>
									<button onClick="addToCart(${product.id})" class="btn btn-warning"><span class= "glyphicon glyphicon-shopping-cart pull-center"></span> Giỏ hàng</button>
									<h3></h3>
							</div>
							</c:if>
         				</c:if>  				
     				</c:if>
     				<c:if test = "${loop.index == list.size()-1}">
     					<c:if test = "${loop.index%4 == 0}">
     						<div class="section group">
         					<div class="grid_1_of_4 images_1_of_4 products-info" style=" width: 232px; height: 400px;"><a href="product?id=${product.id}">
									<img style="width: 225px; height: 230px" src="/toystore/img/${product.id}.png">
									<h3 style="font-weight: bold;height: 44px">${product.name}</h3></a>
									<h3 class="changeToVND">${product.price}</h3>
									<button onClick="addToCart(${product.id})" class="btn btn-warning"><span class= "glyphicon glyphicon-shopping-cart pull-center"></span> Giỏ hàng</button>
									<h3></h3>
							</div>
							</div>
     					</c:if>
     					<c:if test = "${loop.index%4 != 0}">
     						<div class="grid_1_of_4 images_1_of_4 products-info" style=" width: 232px; height: 400px;"><a href="product?id=${product.id}">
									<img style="width: 225px; height: 230px" src="/toystore/img/${product.id}.png">
									<h3 style="font-weight: bold;height: 44px">${product.name}</h3></a>
									<h3 class="changeToVND">${product.price}</h3>
									<button onClick="addToCart(${product.id})" class="btn btn-warning"><span class= "glyphicon glyphicon-shopping-cart pull-center"> </span>Giỏ hàng</button>
									<h3></h3>
							</div>
							</div>
     					</c:if>
     				</c:if>    				
		    	</c:forEach>
		    	<c:if test = "${list.size() != 0}">
		    		<div class="paging">
            		<c:if test = "${currentPage != 1}">
                		<a href="/toystore/store?page=${currentPage-1}&range=${range}&brand=${brand}&category=${category}&recAge=${recAge}">Back</a>
                	</c:if>
                	<c:if test = "${currentPage == 1}">	
        				<a class="current">1</a>
        			</c:if>
        			<c:if test = "${currentPage != 1}">	
        				<a href="/toystore/store?page=1&range=${range}&brand=${brand}&category=${category}&recAge=${recAge}">1</a>
        			</c:if>
        			
        			<c:forEach var="pag" items="${pageList}" varStatus="loop">
            			<c:if test = "${currentPage == pag}">	
        					<a class="current">${pag}</a>
        				</c:if>
        				<c:if test = "${currentPage != pag}">	
        					<a href="/toystore/store?page=${pag}&range=${range}&brand=${brand}&category=${category}&recAge=${recAge}">${pag}</a>
        				</c:if>
      				</c:forEach>

            		<c:if test = "${currentPage != totalPage}">
                		<a href="/toystore/store?page=${currentPage+1}&range=${range}&brand=${brand}&category=${category}&recAge=${recAge}">Next</a>
                	</c:if>
				</div>
		    	</c:if>
					
		    
			</div>
			
    
		    	</div>
		    	<div class="content-sidebar">
		    		<h4>Lọc sản phẩm</h4>		    			
						<form>
						<input type="hidden" name="name" value="${name}">
        				<p>Mức giá</p>
        				<div class="select-range">
        					<select name="range" class="form-control">
								<option value="">Tất cả giá</option>
								<option value="duoi-100.000">Dưới 100.000</option>
								<option value="tu-100.000-den-200.000">100.000 đến 200.000</option>
								<option value="tu-200.000-den-500.000">100.000 đến 500.000</option>
								<option value="tren-500.000">trên 500.000</option>
        					</select>
        				</div>
							<p>Danh mục</p>
							<div class="select-range">
								<select name="brand" class="form-control">
									<option value="">Tất cả danh mục</option>
									<c:forEach var="brand" items="${brand}" varStatus="loop">
										<option value="${brand}">${brand}</option>
									</c:forEach>
								</select>
							</div>
							<p>Hãng sản xuất</p>
							<div class="select-range">
								<select name="recAge" class="form-control">
									<option value="">Tất cả nhà sản xuất</option>
									<c:forEach var="recAge" items="${rec}" varStatus="loop">
										<option value="${recAge}">${recAge}</option>
									</c:forEach>
								</select>
							</div>
        					<input type="hidden" name="category" value="${cate}">
        					<input class="search-submit" type="submit" value="Lọc sản phẩm">
						</form>
						
		    	</div>
		    </div>
	<div class="clear"> </div>
</body>
</html>