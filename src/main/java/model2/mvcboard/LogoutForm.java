package model2.mvcboard;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Auth/Logout.do")
public class LogoutForm extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String prevURL = req.getHeader("referer");
		
        session.invalidate();   // 세션 전체 삭제
        if(prevURL == null) {
            prevURL = req.getContextPath() + "/index.jsp"; // 혹시 null이면 대비
        }
        resp.sendRedirect(prevURL);

	}
}
