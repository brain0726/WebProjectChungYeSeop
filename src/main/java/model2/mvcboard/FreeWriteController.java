package model2.mvcboard;

import java.io.IOException;

import freeboard.FreeBoardDAO;
import freeboard.FreeBoardDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import membership.MemberDTO;
import utils.JSFunction;

@WebServlet("/freeWrite.do")
public class FreeWriteController extends HttpServlet{
	
	/*@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("UserId")==null) {
			JSFunction.alertLocation(resp, "로그인 후 이용해주세요.", "${pageContext.request.contextPath}/membership/LoginForm.jsp");
			return;
		}
		
		req.getRequestDispatcher("${pageContext.request.contextPath}/free.jsp")
			.forward(req, resp);	
	}*/
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 로그인 확인
		
		HttpSession session = req.getSession();
		/*
		if(session.getAttribute("UserId")==null) {
			JSFunction.alertLocation(resp, "로그인 후 이용해주세요.",
					"${pageContext.request.contextPath}/membership/LoginForm.jsp");
			return;
		}*/
		
		// 파일 업로드 외 처리 ===========================
		FreeBoardDTO dto = new FreeBoardDTO();
		dto.setId(((MemberDTO)session.getAttribute("loginUser")).getId());
		dto.setTitle(req.getParameter("title"));
		dto.setContent(req.getParameter("content"));
		
		// DAO를 통해 DB에 게시 내용 저장
		FreeBoardDAO dao = new FreeBoardDAO();
		
		System.out.println("아이디:"+ dto.getId());
		//insert 쿼리문 실행
		int result = dao.insertWrite(dto);

		
		//자원 해제
		dao.close();
		
		//성공 or 실패?
		if (result ==1) { // 글쓰기 성공
			resp.sendRedirect("freeList.do");
		}
		else { // 글쓰기 실패
			JSFunction.alertLocation(resp, "글쓰기에 실패했습니다.", "/freeWrite.do");
		}
	}
}
