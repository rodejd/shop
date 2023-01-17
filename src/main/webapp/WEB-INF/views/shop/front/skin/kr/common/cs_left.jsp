<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%----------------------------------------------------------------------------------------------
* 파일명 : cs_left.jsp
* 생성일 : 2017. 02. 07
* 작성자 : 이병환
* 설   명 : skin default1 고객센터 left 메뉴
* ==============================================
* 변경이력:
* DATE				AUTHOR			DESCRIPTION
* ---------------------------------------------------------------------------
* 20170207			이병환				최초작성
----------------------------------------------------------------------------------------------%>
<%@ include file="/WEB-INF/views/shop/taglib/taglib.jsp" %>

<!-- 고객센터 메뉴 -->
<table width="900" height="100%" align="${ shopAlign }<%//= ShopConfig.getProperty("shopAlign", "left") %>" cellpadding=0 cellspacing=0 border=0>
	<tr>
		<td>
			<table width=200 height=100% cellpadding=0 cellspacing=0 border=0 class="outline_both">
				<tr>		
					<td valign=top width=200 nowrap>
						<div><img src="/resources/shop/data/skin/season2/img/common/cs_left.gif"></div>
						<div style="text-align:center">
							<TABLE width=180 cellpadding=0 cellspacing=0 border=0>
							<TR>
								<TD><A HREF="../service/faq.jsp" onFocus="blur()"><img src="/resources/shop/data/skin/season2/img/common/cs_left_01.gif"></A></TD>
							</TR>
							<tr><td height=1 bgcolor="#E7E7E7"></td></tr>
							<TR>
								<TD><A HREF="../service/guide.jsp" onFocus="blur()"><img src="/resources/shop/data/skin/season2/img/common/cs_left_02.gif"></A></TD>
							</TR>
							<tr><td height=1 bgcolor="#E7E7E7"></td></tr>
							<TR>
								<TD><A HREF="../mypage/mypage_qna.jsp" onFocus="blur()"><img src="/resources/shop/data/skin/season2/img/common/cs_left_03.gif"></A></TD>
							</TR>
							<tr><td height=1 bgcolor="#E7E7E7"></td></tr>
							<TR>
								<TD><A HREF="../member/find_id.jsp" onFocus="blur()"><img src="/resources/shop/data/skin/season2/img/common/cs_left_04.gif"></A></TD>
							</TR>
							<tr><td height=1 bgcolor="#E7E7E7"></td></tr>
							<TR>
								<TD><A HREF="../member/find_pwd.jsp" onFocus="blur()"><img src="/resources/shop/data/skin/season2/img/common/cs_left_05.gif"></A></TD>
							</TR>
							<tr><td height=1 bgcolor="#E7E7E7"></td></tr>
							<TR>
								<TD><A HREF="../member/myinfo.jsp" onFocus="blur()"><img src="/resources/shop/data/skin/season2/img/common/cs_left_06.gif"></A></TD>
							</TR>
							<tr><td height=1 bgcolor="#E7E7E7"></td></tr>
							</TABLE>
						</div>
					</td>
				</tr>
			</table>	
		</td>

		<td valign=top width="100%" height=100% bgcolor="" background="" class=outline_side>


<!-- 관리자에게 SMS보내기 기능 : 관련파일은 '디자인관리 > 기타페이지디자인 > 기타/추가페이지(proc) > 관리자에게 SMS상담문의하기 - ccsms.htm' 에 있습니다. -->
<!-- 아래 기능은 기본적으로 회원들만 보이도록 되어있는 소스입니다.
만약 비회원들도 이 기능을 사용하게 하려면 아래 소스중에,  \{ # ccsms \}  요부분만 남겨놓고 아래위 소스를 삭제하시면 됩니다.
또한 이기능을 사용하려면 '회원관리 > SMS포인트충전' 에서 포인트충전이 되어있어야만 가능합니다. -->
