package model2.mvcboard;

import java.io.IOException;

import freeboard.FreeBoardDAO;
import freeboard.FreeBoardDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/freeview.do")
public class ViewController  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int idx = Integer.parseInt(req.getParameter("idx"));

        FreeBoardDAO dao = new FreeBoardDAO();

        // 조회수 증가
        dao.updateVisitCount(idx);

        // 글 데이터 가져오기
        FreeBoardDTO dto = dao.selectView(idx);
        dao.close();

        req.setAttribute("dto", dto);  // JSP로 전달

        req.getRequestDispatcher("/freeList.do").forward(req, resp);
    }

}
