package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import dto.PostDTO;

public class PostDAO {

	private static PostDAO instance;

	public static PostDAO getInstance() {
		if (instance == null) {
			instance = new PostDAO();
		}
		return instance;

	}

	private Connection getConnection() throws SQLException {
		return SingleToneDataSource.getConnection();
	}

	// 포스트 아이디로 검색

	public boolean searchByPostID(int postId) throws SQLException {
		String query = "select * from posts_list where postId=?";

		try (Connection conn = getConnection(); PreparedStatement pstat = conn.prepareStatement(query);

		) {
			pstat.setInt(1, postId);
			try (ResultSet rs = pstat.executeQuery()) {
				return rs.next();
			}
		}
	}

	// 글작성자가 로그인한 아이디와 같은지
	public boolean postMatchedLocalId(int postId, String localId) throws SQLException {

		String query = "select authorId from posts_list where postId = ?";

		try (Connection conn = getConnection(); PreparedStatement pstat = conn.prepareStatement(query);) {
				pstat.setInt(1, postId);
			try (ResultSet rs = pstat.executeQuery()) {
				if(rs.next()) {
					return rs.getString("authorId").equals(localId);
				}else {
					return false;
				}
				
			}

		}

	}

	// 작성자 id으로 검색 (부분 문자열)
	public List<PostDTO> searchByAuthorID(String Id) throws SQLException {

		List<PostDTO> posts = new ArrayList<>();
		String query = "select * from posts_list where authorId like ?";
		try (Connection conn = getConnection(); PreparedStatement pstat = conn.prepareStatement(query);

		) {
			pstat.setString(1, "%"+Id+"%");

			try (ResultSet rs = pstat.executeQuery()) {
				while (rs.next()) {

					PostDTO post = new PostDTO();

					post.setPostId(rs.getInt("postId"));
					post.setAuthorId(rs.getString("authorId"));
					post.setPost(rs.getString("postContent"));
					post.setTimestamp(rs.getTimestamp("postTime").getTime());
					posts.add(post);

				}
			}

		}

		return posts;
	}

	// 포스트 개수 새기
	public int getPostsCount() throws SQLException {

		String query = "select count(*) from posts_list";

		try (Connection conn = getConnection(); PreparedStatement pstat = conn.prepareStatement(query);) {
			try (ResultSet rs = pstat.executeQuery()) {
				rs.next();
				return rs.getInt("count(*)");
			}

		}

	}

	// C 글작성

	public int addPost(String loginedClientId, String postText) throws SQLException {

		String query = "insert into posts_list values(postid_sequence.nextVal,?, ?, sysdate)";

		try (Connection conn = getConnection(); PreparedStatement pstat = conn.prepareStatement(query);

		) {

			pstat.setString(1, loginedClientId);
			pstat.setString(2, postText);

			int result = pstat.executeUpdate();
			return result;

		}

	}

	// R 글 전체 불러오기

	public List<PostDTO> getPosts() throws SQLException {
		List<PostDTO> posts = new ArrayList<>();
		String query = "select * from posts_list";
		try (Connection conn = getConnection();
				PreparedStatement pstat = conn.prepareStatement(query);
				ResultSet rs = pstat.executeQuery();) {

			while (rs.next()) {

				PostDTO post = new PostDTO();

				post.setPostId(rs.getInt("postId"));
				post.setAuthorId(rs.getString("authorId"));
				post.setPost(rs.getString("postContent"));
				post.setTimestamp(rs.getTimestamp("postTime").getTime());
				posts.add(post);

			}
		}

		return posts;
	}

	// U 글 수정

	public int UpdateByPostId(int postId, String postContent) throws SQLException {
		String query = "update posts_list set postContent = ? where postId = ?";
		try (Connection conn = getConnection(); PreparedStatement pstat = conn.prepareStatement(query);) {
			pstat.setString(1, postContent);
			pstat.setInt(2, postId);
			return pstat.executeUpdate();

		}

	}

	// D 글 삭제

	public int deleteByPostID(int postId) throws SQLException {

		String query = "delete from posts_list where postId = ?";

		try (Connection conn = getConnection(); PreparedStatement pstat = conn.prepareStatement(query);) {
			pstat.setInt(1, postId);
			return pstat.executeUpdate();

		}

	}

}
