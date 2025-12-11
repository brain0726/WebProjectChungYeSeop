package model2.mvcboard;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import membership.MemberDAO;
import membership.MemberDTO;

@WebServlet("/Auth/LoginForm.do")
public class LoginForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("user_id");
        String pw = req.getParameter("user_pw");
        
        System.out.println("입력된 ID: " + id);
        System.out.println("입력된 PW: " + pw);
        
        MemberDAO dao = new MemberDAO(req.getServletContext());
        MemberDTO dto = dao.getMemberDTO(id, pw);
        dao.close();


        if(dto.getId() != null) {
        	HttpSession session = req.getSession();
        	session.setAttribute("loginUser", dto);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } 
        else {
        	
            resp.sendRedirect(req.getContextPath() + "/membership/login.jsp?error=1");
        }
	}
}
