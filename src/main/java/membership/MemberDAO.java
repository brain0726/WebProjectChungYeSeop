package membership;

import common.JDBConnect;
import jakarta.servlet.ServletContext;

public class MemberDAO extends JDBConnect{
	
	public MemberDAO() {
	    super();   // JDBConnect 기본 생성자 호출(있을 경우)
	}


	public MemberDAO( String drv, String url, String id, String pass) {
		super(drv, url, id, pass);
	}
	
	public MemberDAO(ServletContext application) {
		super(application);
	}
	
	//회원정보 조회(아이디 비밀번호 두개 필요)
	public MemberDTO getMemberDTO(String uid, String upass) {
		//회원인증을 위한 쿼리문 실행 후 
		MemberDTO dto = new MemberDTO();
		String query = "SELECT * FROM webmember WHERE id=? AND pass=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, uid);
			psmt.setString(2, upass);
			//쿼리문 실행 후 ResultSet 인스턴스 반환
			rs = psmt.executeQuery();
			//반환된 ResultSet에 레코드가 저장되어 있다면..
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString(3));
				dto.setEmail(rs.getString(4));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	//아이디 조회 (아이디만 필요)
	public MemberDTO getMemberDTO(String uid) {
	    MemberDTO dto = new MemberDTO();
	    String query = "SELECT * FROM webmember WHERE id=?";

	    try {
	        psmt = con.prepareStatement(query);
	        psmt.setString(1, uid);

	        rs = psmt.executeQuery();

	        if (rs.next()) {
	            dto.setId(rs.getString("id"));
	            dto.setPass(rs.getString("pass"));
	            dto.setName(rs.getString("name"));
	            dto.setEmail(rs.getString("email"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return dto;
	}
	
	//회원가입
	public int insertMember(MemberDTO dto) {
	    int result = 0;

	    try {
	        String query = "INSERT INTO webmember (id, pass, name, email) VALUES (?, ?, ?, ?)";
	        psmt = con.prepareStatement(query);
	        psmt.setString(1, dto.getId());
	        psmt.setString(2, dto.getPass());
	        psmt.setString(3, dto.getName());
	        psmt.setString(4, dto.getEmail());
	        result = psmt.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return result;
	}
	
	//회원정보 수정
	public int updateMember(MemberDTO dto) {
	    int result = 0;
	    String sql = "UPDATE webmember SET pass=?, name=?, email=? WHERE id=?";

	    try {
	        psmt = con.prepareStatement(sql);
	        psmt.setString(1, dto.getPass());
	        psmt.setString(2, dto.getName());
	        psmt.setString(3, dto.getEmail());
	        psmt.setString(4, dto.getId());
	        result = psmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}
	
	//회원 탈퇴
	public int deleteMember(String id) {
		int result = 0;
		try {
			String query = "DELETE FROM webmember WHERE id=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("게시물 삭제 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
}
