package model2.mvcboard;

import java.io.IOException;

import org.apache.catalina.connector.Response;
import org.apache.coyote.Request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import membership.MemberDAO;
import membership.MemberDTO;

@WebServlet("/Auth/Register.do")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String id = req.getParameter("user_id");
        String pw = req.getParameter("user_pw");
        String name = req.getParameter("user_name");
        String email = req.getParameter("user_email");

        MemberDTO dto = new MemberDTO();
        dto.setId(id);
        dto.setPass(pw);
        dto.setName(name);
        dto.setEmail(email);

        MemberDAO dao = new MemberDAO();
        int result = dao.insertMember(dto);

        dao.close();
        
        if (result > 0) {
            // 회원가입 성공 → 로그인 페이지로 이동
            resp.sendRedirect(req.getContextPath() + "/membership/login.jsp");
        } else {
            // 실패하면 다시 회원가입 페이지 또는 에러 페이지로 이동
            resp.sendRedirect(req.getContextPath() + "/Auth/Register.jsp?error=1");
        }
    }
}