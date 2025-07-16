package server;

import util.Util;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;



import dao.PostDAO;
import dao.UserDAO;
import dto.PostDTO;


public class ServerThread extends Thread {
	
	UserDAO userDAO = UserDAO.getInstance();
	PostDAO postDAO = PostDAO.getInstance();
	
	
	Socket socket;
	
	public ServerThread(Socket clientSocket) {
		this.socket = clientSocket;
	}
	
	
	@Override
	public void run() {
		
		
		//접속한 클라이언트 마다 해당 내용이 실행됨
		try {
			InputStream iStream = socket.getInputStream();
			DataInputStream dis = new DataInputStream(iStream);

			OutputStream os = socket.getOutputStream(); // 기본 스트림 개설
			DataOutputStream dos = new DataOutputStream(os);
			
			String idBuffer = "";

			String passwordBuffer = "";
			String nameBuffer = "";
			
			
			String loginedClient = "";
			
			while(true) {
				
				loginedClient = dis.readUTF();
				String menuSelect = dis.readUTF();
				System.out.println(loginedClient + "가 "+menuSelect+"번 메뉴를 누름");
				
				if(loginedClient.equals("")) {
					System.out.println("로그인 클라이언트가 빈문자열임" );
					
					switch (menuSelect) {
					case "1":
						// 로그인 정보 입력받기
						idBuffer = dis.readUTF();
						passwordBuffer = Util.encrypt(dis.readUTF());

						// 로그
						System.out.println("==로그인 로그==");
						System.out.println(idBuffer);
						System.out.println(passwordBuffer);
						
						//입력 받은 정보로 로그인 시도 ~결과처리
						if(userDAO.login(idBuffer, passwordBuffer)) {
							System.out.println("==로그인 성공==");
							
							dos.writeBoolean(true);
							dos.flush();
							
						}else { 
							System.out.println("==로그인 실패==");
							dos.writeBoolean(false);
							dos.flush();
							
						}
						
						break;
					case "2":
						// 회원가입
						boolean idCheck=false;
						do {
							idBuffer = dis.readUTF();
							
							idCheck = userDAO.searchById(idBuffer);
							dos.writeBoolean(idCheck);
							dos.flush();
							if(idCheck) {
								System.out.println("사용자가 중복되는 아이디를 입력함");
								break;
							}
							
							else{
								passwordBuffer = Util.encrypt(dis.readUTF());
								
								nameBuffer = dis.readUTF();
					

								int result =userDAO.addUser(idBuffer, passwordBuffer, nameBuffer);
								dos.writeInt(result);
								dos.flush();
								break;
							}
						} while(idCheck);

						
						//로그
						System.out.printf("%s %s %s\n", idBuffer, passwordBuffer, nameBuffer);
						System.out.println("==회원 가입 성공==");
					break;
					case "0":
						System.out.println(socket.getInetAddress()+"님이 종료하셨습니다.");
						return;
					}
					
					
				}else {
					//클라이언트가 로그인 한 상태라면
					System.out.println("로그인 클라이언트가 빈문자열이 아님" );
					
					switch (menuSelect) {
					case "1": 
						//방명록 남기기.
						String postText = dis.readUTF();
						
						if(postDAO.addPost(loginedClient,postText) >0)
						{
							System.out.println("방명록이 성공적으로 남겨졌습니다.");
						}
						break;
						
						
					case "2":
						//방명록 보기
						
						//서버로그
						System.out.println(loginedClient+"가 방명록 봄");
						
						dos.writeInt(postDAO.getPostsCount());
						dos.flush();
						
						List<PostDTO> posts = postDAO.getPosts();

						if(postDAO.getPostsCount() >0) {
						
							
							
							for(int i=0;i<posts.size();i++) {
								
								PostDTO post = posts.get(i);
								
								String postBuffer = String.format("%-6d %-20s %-10s %-8s", 
										post.getPostId(),
										post.getPost(),
										post.getAuthorId(),
										Util.timeStampFormatString(post.getTimestamp()));
								dos.writeUTF(postBuffer);
							}
							dos.flush();
						}
						
						break;
					case "3":
						//방명록 수정
						System.out.println(loginedClient+"가 방명록 수정할라캄");
						dos.writeInt(postDAO.getPostsCount());
						dos.flush();
						
						posts = postDAO.getPosts();

						if(postDAO.getPostsCount() >0) {
				
							
							for(int i=0;i<posts.size();i++) {
								
								PostDTO post = posts.get(i);
								
								String postBuffer = String.format("%-6d %-20s %-10s %-8s", 
										post.getPostId(),
										post.getPost(),
										post.getAuthorId(),
										Util.timeStampFormatString(post.getTimestamp()));
								dos.writeUTF(postBuffer);
							}
							dos.flush();
						}else {
							continue;
						}
						int updateId = dis.readInt();
						boolean matched= (postDAO.postMatchedLocalId(updateId, loginedClient));
						if(matched) {//로그
							System.out.println("글작성자와, 수정 요청 아이디가 일치함");
						}
						boolean hasID = postDAO.searchByPostID(updateId);
						if(hasID) {//로그
							System.out.println("수정하려는 포스트 아이디가 존재함");
						}
						
						dos.writeBoolean(hasID&& matched);
						dos.flush();
						
						if(hasID&& matched) {
							//이제 수정할 내용을 입력받자
							String updatedStringContent = dis.readUTF();
							int result = postDAO.UpdateByPostId(updateId, updatedStringContent);
							dos.writeInt(result);
							dos.flush();
						}
						
						
						break;
					case "4":
						//방명록 삭제
						System.out.println(loginedClient+"가 방명록 삭제할라캄");
						
						dos.writeInt(postDAO.getPostsCount());
						dos.flush();
						
						posts = postDAO.getPosts();

						if(postDAO.getPostsCount() >0) {
				
							
							for(int i=0;i<posts.size();i++) {
								
								PostDTO post = posts.get(i);
								
								
								String postBuffer = String.format("%-6d %-20s %-10s %-8s", 
											post.getPostId(),
											post.getPost(),
											post.getAuthorId(),
											Util.timeStampFormatString(post.getTimestamp()));
								
													
								
								dos.writeUTF(postBuffer);
							}
							dos.flush();
						}else {
							continue;
						}
						
						int deleteID = dis.readInt();
						
						matched= (postDAO.postMatchedLocalId(deleteID, loginedClient));
						if(matched) {//로그
							System.out.println("글작성자와, 삭제 요청 아이디가 일치함");
						}
						hasID = postDAO.searchByPostID(deleteID);
						if(hasID) {//로그
							System.out.println("삭제하려는 포스트 아이디가 존재함");
						}
						
						int result =0;
						if(hasID&& matched) {
							result=postDAO.deleteByPostID(deleteID);
						}
						
						dos.writeInt(result);
						dos.flush();

						break;
					case "5":
						//방명록 검색
						
						dos.writeInt(postDAO.getPostsCount());
						dos.flush();
						
						posts = postDAO.getPosts();

						if(postDAO.getPostsCount() >0) {
						
							
							
							for(int i=0;i<posts.size();i++) {
								
								PostDTO post = posts.get(i);
								
								String postBuffer = post.getPostId()+"\t"
													+post.getPost()+"\t"
													+post.getAuthorId()+"\t"
													+Util.timeStampFormatString(post.getTimestamp());
													
								
								dos.writeUTF(postBuffer);
							}
							dos.flush();
						}else {
							continue;
						}
						
						String searchId =  dis.readUTF();
						//서버로그
						
						
						System.out.println(searchId+"문자열 가진 Id 검색중...");
						List<PostDTO> searchPosts = postDAO.searchByAuthorID(searchId);
						
						System.out.println("검색완료");
						
						dos.writeInt(searchPosts.size());
						dos.flush();

						if(searchPosts.size()>0) {

							for(int i=0;i<searchPosts.size();i++) {
								
								PostDTO post = searchPosts.get(i);
								
								String postBuffer = String.format("%-6d %-20s %-10s %-8s", 
										post.getPostId(),
										post.getPost(),
										post.getAuthorId(),
										Util.timeStampFormatString(post.getTimestamp()));
								dos.writeUTF(postBuffer);
							}
							dos.flush();
						}
						
						
						break;
					case "6":
						System.out.println("사용자가 로그아웃 하였습니다");
						loginedClient="";
						break;
					}
					
				}
				
				
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();

		}
	
	}
}