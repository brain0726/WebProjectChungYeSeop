package freeboard;

import java.util.ArrayList;
import java.util.List;

import common.DBConnPool;

public class FreeCommentDAO extends DBConnPool {

	// 댓글 목록 불러오기
    public List<FreeCommentDTO> getComments(int boardIdx) {
        List<FreeCommentDTO> list = new ArrayList<>();

        String sql = "SELECT * FROM free_comment "
                   + "WHERE board_idx=? ORDER BY parent_id NULLS FIRST, id";

        try {
            psmt = con.prepareStatement(sql);
            psmt.setInt(1, boardIdx);
            rs = psmt.executeQuery();

            while (rs.next()) {
                FreeCommentDTO dto = new FreeCommentDTO();
                dto.setId(rs.getString("id"));
                dto.setBoardIdx(rs.getInt("boardidx"));
                dto.setParentId(rs.getString("parentid"));
                dto.setContent(rs.getString("content"));
                dto.setPostdate(rs.getDate("postdate"));
                list.add(dto);
            }
        } catch (Exception e) {
            System.out.println("댓글 조회 오류");
            e.printStackTrace();
        }
        return list;
    }

    // 댓글 저장
    public int insertComment(FreeCommentDTO dto) {
        int result = 0;
        String sql = "INSERT INTO free_comment "
                + "(id, boardidx, parentid, content) "
                + "VALUES (?, seq_comment_id.NEXTVAL, ?, ?)";

        try {
            psmt = con.prepareStatement(sql);
            psmt.setString(1, dto.getId());
            if (dto.getParentId() == null)
                psmt.setNull(2, java.sql.Types.INTEGER);
            else
                psmt.setString(2, dto.getParentId());
            psmt.setString(3, dto.getContent());
            result = psmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("댓글 저장 오류");
            e.printStackTrace();
        }

        return result;
    }
}
