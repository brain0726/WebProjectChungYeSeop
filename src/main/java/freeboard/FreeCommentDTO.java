package freeboard;

import java.sql.Date;

public class FreeCommentDTO {
	private String Id;
    private int boardIdx;
    private String parentId;
    private String content;
    private Date postdate;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public int getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(int boardIdx) {
		this.boardIdx = boardIdx;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPostdate() {
		return postdate;
	}
	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}
    
    
}
