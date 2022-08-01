$(document).ready(function(){
	ajaxGet2();
	function ajaxGet2(){
		$.ajax({
			type: "GET",		
			url: "http://localhost:3010/toystore/api/category/allForReal",
			success: function(result){
				$.each(result, function(i, cate){
					var content = '<li><a href="/toystore/store?cate='+cate.name+'"><span style=" font-size: 16px; font-weight: 900; ">'+cate.name+'</span></a></li>';
					var content2 ='<li><a  style="padding-right: 100px" href="store?cate='+cate.name+'">'+cate.name+'</a></li>'
					$('#danhmuc').append(content);		
					$('#danhmuc2').append(content2);	
				})					
			}	
		});
			
	}
})
