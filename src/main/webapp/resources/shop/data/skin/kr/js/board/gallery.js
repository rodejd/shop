$(function(){
	var pageNo =  2;
	$(window).scroll(function(){
		// 스크롤이 최하단까지 내려간 경우
		if($(window).scrollTop() == $(document).height() - $(window).height()){
			if($('#page_totalCnt_').val() != (pageNo-1)){
				$.ajax({
					data : {
						"page_no": pageNo,
						"id":$('#id').val(),
						"pageViewNum" : $('#pageViewNum_').val(), 
						"searchMode": $('#searchMode').val(),
						"search_word":$('#search_word').val()
					},
					dataType : "text",
					type :"post",
					url:'/shop/skin/default/board/connectingDB2.jsp',
					success:function(data){
						var $items = $(data);
						$('.list-type01').append($items).masonry('appended', $items);
					}, error:function(data){
						alert('error : '+data);
					}
				});
				pageNo++;
			}else{
				return false;
			}	
		}
	});
});

function viewAllReply(art_no, rep_no, id){
	// key값(artno : 글번호, repno : 댓글번호)
	$('#review_list'+art_no).css("display", "none");
		$.ajax({
			data : {"no":art_no,"sno":rep_no, "id" : id},
			dataType : "json",
			type :"POST",
			url:'/shop/skin/default/board/connectingDB.jsp',
			success:function(data){
				var indx = data.list.length;
				var html = "<ul>";
				for(var i=0; i<indx; i++){
					html += "<li>";
					html += "<div class=\"review-type01\">";
					html += "<div class=\"user-info-wrap\">";
					html += "<div class=\"user-picture\">";    
					html += "<img src=\"/shop/data/skin/season2/member/profile/"+ data.list[i].profile+ "\" alt=\"\" onerror='this.onerror=null;this.src=\"/shop/data/skin/season2/images/user.gif\"';>";
					html += "</div>";
					html += "<dl class=\"user-info\">";
					html +=	"<dt>"+data.list[i].name+"</dt>";
					html += "<dd class=\"write-day\">"+data.list[i].fregdt+"</dd>";
					html += "</dl>";
					html += "</div>";
					html += "<div class=\"user-txt\">"+strCut(data.list[i].memo,36)+"</div>";
					html += "</div>";
					html += "</li>";
				}
				html += "</ul>";
				html +=	"<ul class=\"detail-link-box\">";
				html +=	"<li class=\"review-pre-close bg-black\">";
				html += "<a href=\"javascript:void(0)\" onclick=\"foldAllReply("+art_no+","+rep_no+",'"+id+"')\" class=\"txt-white\">";
				html += "<span class=\"i-balloon-w\">Comment</span>";
				html += "</a>";
				html += "</li>";
				html += "<li class=\"detail-go bg-yellow\">";
				html += "<a href=\"view.jsp?id="+id+"&no="+art_no+"&mode=${mode}\">";
				html += "<span class=\"i-spoon-b\">View Magazine</span>";
				html += "</a>";
				html += "</li>";
				html +=	"</ul>";
				$('#review_list'+art_no).html(html);
				$('#review_list'+art_no).css("display", "block");
			}, error : function(data) {
				console.log("error"+data);
			}
		});
}
	
// 댓글보기닫기(글번호, 댓글번호)
function foldAllReply(art_no, rep_no, id){
	$('#review_list'+art_no).html("");
}