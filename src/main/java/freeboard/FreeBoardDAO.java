package freeboard;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;
import model2.mvcboard.MVCBoardDTO;

public class FreeBoardDAO extends DBConnPool {
	
	//데이터베이스에서 리스트 불러오기
	public List<FreeBoardDTO> selectList(Map<String,Object> map){
		List<FreeBoardDTO> board = new Vector<FreeBoardDTO>();
		String query = "SELECT * FROM freeboard " ;
		if(map.get("searchWord") !=null) {
			query += " WHERE " + map.get("searchField")
				+ " LIKE '%" + map.get("searchWord")+ "%' ";
		}
		query += " ORDER BY idx DESC ";
		
		System.out.println("퀴리="+query);
	
		try {
			//쿼리문 실행을 위한 preparedStatement 인스턴스 생성
			psmt = con.prepareStatement(query);
			//반환되는 결과는 ResultSet 인스턴스에 저장
			rs = psmt.executeQuery();
			//인출된 레코드의 갯수만큼 반복
			while (rs.next()) {
				//하나의 레코드 저장을 위해 DTO인스턴스 생성
				FreeBoardDTO dto = new FreeBoardDTO();
				
				System.out.println("제목="+ rs.getString(2));
				
				//DTO의 멤버변수에 각 컬럼의 값 저장
				dto.setIdx(rs.getInt(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setId(rs.getString(4));
				dto.setVisitcount(rs.getInt(5));
				dto.setPostdate(rs.getDate(6));
				
				//하나의 레코드에 저장
				board.add(dto);
			}
		}
		catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		
		return board;
	}
	
	//글쓰기
	public int insertWrite(FreeBoardDTO dto) {
	      int result = 0;
	      try {
	         String query = 
	               " INSERT INTO freeboard ( "
	         + " idx, id, title, content) "
	               +" VALUES ( " 
	         + " seq_board_num.NEXTVAL,?,?,?) ";
	            
	         psmt = con.prepareStatement(query);
	         psmt.setString(1, dto.getId());
	         psmt.setString(2, dto.getTitle());
	         psmt.setString(3, dto.getContent());
	         /*
	          * insert, update, delete와 같은 쿼리문은 실행 후 성공한
	          * 행의 갯수를 반환하므로 int형의 반환값을 가지게 된다.*/
	         result = psmt.executeUpdate();
	      }
	      catch (Exception e) {
	         System.out.println("게시물 입력 중 예외발생");
	         e.printStackTrace();
	      }
	      return result;
	   }
	
	//조회수 카운트
	public int selectCount(Map<String, Object> map) {
		//게시물의 갯수 초기화
		int totalCount = 0;
		//카운트를 위한 쿼리문 작성
		String query = "SELECT COUNT(*) FROM freeboard ";
		//검색을 위한 파라미터가 있는 경우 where절을 동적으로 추가
		if (map.get("searchWord") !=null){
			query += " WHERE " + map.get("searchField") + " "
					+ " LIKE '%" + map.get("searchWord") +"%'";
		}
		try {
			/*인파라미터가 없는 정적 쿼리문이므로 Statement
			인터페이스를 통해*/
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			//정수값이므로 getInt()함수로 결과값 인출
			totalCount = rs.getInt(1);
		}
		catch (Exception e) {
			System.out.println("게시물 카운트 중 예외 발생");
			e.printStackTrace();
		}
		
		return totalCount;
	}
	
	// 조회수 증가
	public void updateVisitCount(int idx) {
	    String query = "UPDATE freeboard SET visitcount = visitcount + 1 WHERE idx=?";

	    try {
	        psmt = con.prepareStatement(query);
	        psmt.setInt(1, idx);
	        psmt.executeUpdate();
	    } catch (Exception e) {
	        System.out.println("조회수 증가 중 예외 발생");
	        e.printStackTrace();
	    }
	}

	// 단일 게시글 조회
	public FreeBoardDTO selectView(int idx) {
	    FreeBoardDTO dto = new FreeBoardDTO();

	    String query = "SELECT * FROM freeboard WHERE idx=?";
	    try {
	        psmt = con.prepareStatement(query);
	        psmt.setInt(1, idx);
	        rs = psmt.executeQuery();

	        if (rs.next()) {
	            dto.setIdx(rs.getInt("idx"));
	            dto.setTitle(rs.getString("title"));
	            dto.setContent(rs.getString("content"));
	            dto.setId(rs.getString("id"));
	            dto.setVisitcount(rs.getInt("visitcount"));
	            dto.setPostdate(rs.getDate("postdate"));
	        }
	    } catch (Exception e) {
	        System.out.println("게시글 상세보기 중 예외 발생");
	        e.printStackTrace();
	    }
	    return dto;
	}

}
