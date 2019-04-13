package top.hjie.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="comment")
public class Comment implements Serializable {

	private static final long serialVersionUID = -2677731572374414027L;
	
	@Id
	@Column(name="commentId")
	private String commentId;
	@Column(name="articleId")
	private String articleId;
	@Column(name="userId")
	private String userId;
	@Column(name="commentTime")
	private Date commentTime;
	@Column(name="commentContent")
	private String commentContent;
	@Column(name="parentCommentId")
	private String parentCommentId;
	
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getParentCommentId() {
		return parentCommentId;
	}
	public void setParentCommentId(String parentCommentId) {
		this.parentCommentId = parentCommentId;
	}
	
}
