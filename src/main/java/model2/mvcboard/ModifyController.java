package model2.mvcboard;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import membership.MemberDAO;
import membership.MemberDTO;
import utils.JSFunction;

@WebServlet("/Auth/Modify.do")
public class ModifyController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("UserId")==null) {
			JSFunction.alertLocation(resp, "로그인 후 이용해주세요.", "${pageContext.request.contextPath}/membership/LoginForm.jsp");
			return;
		}
		
		req.getRequestDispatcher("${pageContext.request.contextPath}/membership/modify.jsp")
			.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

	    String id = req.getParameter("user_id");
	    String pw = req.getParameter("user_pw");
	    String name = req.getParameter("user_name");
	    String email = req.getParameter("user_email");

	    MemberDAO dao = new MemberDAO();

	    // 비밀번호 변경 안 했으면 기존 비번 유지
	    if(pw == null || pw.equals("")) {
	        pw = dao.getMemberDTO(id).getPass();
	    }

	    MemberDTO dto = new MemberDTO();
	    dto.setId(id);
	    dto.setPass(pw);
	    dto.setName(name);
	    dto.setEmail(email);

	    dao.updateMember(dto);
	    dao.close();

	    // 세션 정보도 업데이트
	    req.getSession().setAttribute("loginUser", dto);

	    resp.sendRedirect(req.getContextPath() + "/index.jsp");

	}
}
