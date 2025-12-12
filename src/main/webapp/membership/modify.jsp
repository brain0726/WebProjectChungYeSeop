<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="membership.MemberDTO" %>
<%
    // 세션에서 로그인한 사용자 정보 받아오기
    MemberDTO dto = (MemberDTO) session.getAttribute("loginUser");

    if(dto == null){
        // 로그인 안 된 상태면 로그인 페이지로
        response.sendRedirect(request.getContextPath() + "/membership/login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>회원정보 수정</title>

  <link rel="stylesheet" type="text/css" href="../css/bootstrap.css" />
  <link href="../css/style.css" rel="stylesheet" />
</head>

<body class="sub_page">

  <div class="hero_area">
    <div class="bg-box">
      <img src="../images/hero-bg.jpg" alt="">
    </div>
    <jsp:include page="/header.jsp" />
  </div>

  <!-- 수정 화면 -->
  <section class="book_section layout_padding">
    <div class="container">
      <div class="heading_container">
        <h2>회원정보 수정</h2>
      </div>

      <div class="row">
        <div class="col-md-6">
          <div class="form_container">

            <form action="${pageContext.request.contextPath}/Auth/Modify.do" method="post">

              <div>
                <input type="text" name="user_id" class="form-control"
                       value="<%=dto.getId()%>" readonly />
              </div>

              <div>
                <input type="password" name="user_pw" class="form-control"
                       placeholder="새 비밀번호 입력 (변경 안 할 경우 비워두세요)" />
              </div>

              <div>
                <input type="text" name="user_name" class="form-control"
                placeholder="이름" 
                       value="<%=dto.getName()%>" required />
              </div>

              <div>
                <input type="email" name="user_email" class="form-control"
                placeholder="이메일" 
                       value="<%=dto.getEmail()%>" required />
              </div>
				
				<div class="d-flex justify-content-between mt-4">
              <div class="btn_box">
                <button type="submit">정보 수정</button>
              </div>

			</div>	          	    

            </form>
            <form action="${pageContext.request.contextPath}/Auth/Delete.do" method="post">
				    <button type="submit" class="btn btn-danger" 
				            onclick="return confirm('정말 탈퇴하시겠습니까?');">
				        회원 탈퇴
				    </button>
				</form>

          </div>
        </div>
      </div>

    </div>
  </section>

</body>
</html>
