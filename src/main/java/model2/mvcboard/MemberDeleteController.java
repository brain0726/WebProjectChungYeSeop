package model2.mvcboard;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import membership.MemberDAO;
import membership.MemberDTO;

@WebServlet("/Auth/Delete.do")
public class MemberDeleteController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		 req.setCharacterEncoding("UTF-8");

	        // 세션에서 로그인 사용자 정보 가져오기
	        MemberDTO dto = (MemberDTO) req.getSession().getAttribute("loginUser");

	        if (dto == null) {
	            // 비로그인 → 로그인 페이지로
	            resp.sendRedirect(req.getContextPath() + "/membership/login.jsp");
	            return;
	        }

	        String userId = dto.getId();

	        MemberDAO dao = new MemberDAO();
	        int result = dao.deleteMember(userId);
	        dao.close();
	        
	        if (result > 0) {
	            // 세션 삭제
	            req.getSession().invalidate();
	            
	            // 메인으로
	            resp.sendRedirect(req.getContextPath() + "/index.jsp");
	        } else {
	            resp.sendRedirect(req.getContextPath() + "/membership/modify.jsp");
	        }
	    }
	}
