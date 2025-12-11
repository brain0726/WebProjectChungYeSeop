package model2.mvcboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freeboard.FreeBoardDAO;
import freeboard.FreeBoardDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/freeList.do")
public class FreeBoardListController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		FreeBoardDAO dao = new FreeBoardDAO();
		
		// 뷰에 전달할 매개변수 저장용 맵 생성
		Map<String, Object> map = new HashMap<String, Object>();
		
		String searchField = req.getParameter("searchField");
		String searchWord = req.getParameter("searchWord");
		
		if(searchWord != null) {
			// 쿼리스트링으로 전달받은 매개변수 중 검색어가 있다면 map에 저장
			map.put("searchField", searchField);
			map.put("searchWord", searchWord);
		}
		//게시물의 갯수 카운트를 위한 DAO의 메서드 실행
		int totalCount = dao.selectCount(map);
		//반환된 갯수는 Map에 저장
		map.put("totalCount", totalCount);
		
		//목록에 출력할 레코드 인출을 위한 메서드 실행
		List<FreeBoardDTO> boardLists = dao.selectList(map);
		dao.close();
		
		for(FreeBoardDTO f : boardLists) {
			System.out.println("제목(컨트롤러)="+ f.getTitle());
		}
		
		//View로 전달할 데이터는 request 영역에 저장
		//전달할 데이터를 request 영역에 저장 후 List.jsp로 포워드
		req.setAttribute("boardLists", boardLists);
		req.setAttribute("map", map);
		
		//JSP로 포워드해서 request영역에 저장된 데이터를 사용
		req.getRequestDispatcher("free.jsp")
			.forward(req, resp);
	}
}
