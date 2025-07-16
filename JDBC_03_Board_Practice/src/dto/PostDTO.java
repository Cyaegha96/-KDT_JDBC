package dto;

public class PostDTO {
	int postId;
	String post;
	String authorId;
	long timestamp;
	
	public PostDTO() {}
	
	public PostDTO(int postId, String post, String authorId, long timestamp) {
		super();
		this.postId = postId;
		this.post = post;
		this.authorId = authorId;
		this.timestamp = timestamp;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
}