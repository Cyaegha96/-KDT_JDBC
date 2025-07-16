package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Client {
	
	public static Scanner scanner = new Scanner(System.in);
	public static String userID = "";
	public static String userToken = "";
	public static final String PRINT_STRING = String.format("%-6s %-20s %-10s %-8s",
    "글번호", "글내용", "작성자", "작성시간");
	
	public static String menuBeforeLogin() {

		System.out.println("<< 인증시스템 >>");
		System.out.println(" 1. 로그인 ");
		System.out.println(" 2. 회원가입 ");
		System.out.println(" 0. 종료");
		
		System.out.print(">>");
		return scanner.nextLine();

	}
	
	public static String menuAfterLogin() {
		
		System.out.println("<< 방명록 >>");
		System.out.println(" 1. 방명록 작성하기 ");
		System.out.println(" 2. 방명록 목록보기 ");
		System.out.println(" 3. 방명록 수정하기 (글번호로 수정하기)");
		System.out.println(" 4. 방명록 삭제하기 (글번호로 삭제하기)");
		System.out.println(" 5. 방명록 검색하기 (작성자 ID로 검색하기)");
		System.out.println(" 6. 로그아웃");
		System.out.print(">>");
		return scanner.nextLine();

	}
	
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("10.5.5.11", 19329);

		InputStream iStream = socket.getInputStream();
		DataInputStream dis = new DataInputStream(iStream);


		OutputStream os= socket.getOutputStream(); 
		DataOutputStream dos = new DataOutputStream(os);
		
		
		while (true) {
			
			dos.writeUTF(userID);
			dos.flush();
			
			if(userID.equals("")) {
				
				//로그인 되지 않았을 경우
				String menuSelect= menuBeforeLogin();
				
				dos.writeUTF(menuSelect);
				dos.flush();
				
				if(menuSelect.equals("1")) {
					//로그인
					
					System.out.println("로그인을 진행합니다");
					System.out.print("입력할 ID:");
					userID = scanner.nextLine();
					dos.writeUTF(userID);
					dos.flush();
	
					System.out.print("입력할 PW:");
					dos.writeUTF(scanner.nextLine());
					dos.flush();
					
			
					if(dis.readBoolean()) {
						
			
						System.out.println("로그인 성공!");
						System.out.println("["+userID+"]님 환영합니다!");
			
						
					}else {
						System.out.println("로그인 실패!");
						userID = "";
					}
				}else if(menuSelect.equals("2")) {
					// 회원가입
					System.out.println("<<회원가입을 진행합니다>>");
					
					System.out.print("아이디(ID):");
					dos.writeUTF(scanner.nextLine());
					dos.flush();
					
					// 중복되는 아이디가 없다면
					if(!dis.readBoolean()) {
						System.out.print("비밀번호(Password):");
						dos.writeUTF(scanner.nextLine());
						dos.flush();
						
						System.out.print("이름(Name):");
						dos.writeUTF(scanner.nextLine());
						dos.flush();
						
						if(dis.readInt() > 0 ) {
							System.out.println("회원가입 성공!");
						}
						
					}else {
						System.out.println("이미 일치하는 아이디가 있습니다");
						
					}
				}else if(menuSelect.equals("0")) {
					//시스템 종료

					System.out.println("<< 온라인 방명록 >>을 종료합니다.");
					System.exit(0);
				}else {
					//이상한 입력시 (예외처리)
					System.out.println("정확한 메뉴 번호를 입력해주세요");
				}
			
			
			}else {
				//로그인 상태라면
				String menuSelect=menuAfterLogin();
				
				dos.writeUTF(menuSelect);
				dos.flush();
				
				if(menuSelect.equals("1")) {
					// 1. 방명록 남기기
					System.out.printf("[ %s ]님 방명록에 남기실 글을 작성해주세요", userID);
					System.out.print(">>");
					String postText = scanner.nextLine();
					dos.writeUTF(postText);
					dos.flush();
					
					
				}else if(menuSelect.equals("2")) {
					//2. 방명록 보기
					int postsCount = dis.readInt();
					
					if(postsCount>0) {
						System.out.println(PRINT_STRING);
						for(int i=0; i<postsCount;i++) {
							String post = dis.readUTF();
							System.out.println(post);
						}
					}else {
						System.out.println("현재 서버에 남아있는 방명록이 없습니다.");
						
					}
					
				}else if(menuSelect.equals("3")) {
					//3. 방명록 수정
					int postsCount = dis.readInt();
					
					if(postsCount>0) {
						System.out.println(PRINT_STRING);
						for(int i=0; i<postsCount;i++) {
							String post = dis.readUTF();
							System.out.println(post);
						}
					}else {
						System.out.println("현재 서버에 남아있는 방명록이 없습니다.");
						continue;
					}
					int updateId = 0;
					while(true) {
						System.out.print("수정하고 싶은 글ID:");
						
						try {
							updateId=Integer.parseInt(scanner.nextLine());
							break;
						} catch (Exception e) {
							System.out.println("숫자로 된 ID를 입력해주세요");
						}

					}
					dos.writeInt(updateId);
					dos.flush();
					
					if(dis.readBoolean()) {
						System.out.print("수정할 글내용:");
						String updatePostContent = scanner.nextLine();
						dos.writeUTF(updatePostContent);
						dos.flush();
						
						if(dis.readInt() > 0) {
							System.out.println("글수정에 성공했습니다");
							
						}else {
							System.out.println("글수정에 실패했습니다");
							
						}
					}else {
						System.out.println("해당하는 게시글이 없거나,해당 포스트에 접근 권한이 없습니다. ");
					}
					
				}else if(menuSelect.equals("4")) {
					//4. 방명록 삭제 (글번호로 삭제)
					int postsCount = dis.readInt();
					
					if(postsCount>0) {
						System.out.println(PRINT_STRING);
						for(int i=0; i<postsCount;i++) {
							String post = dis.readUTF();
							System.out.println(post);
						}
					}else {
						System.out.println("현재 서버에 남아있는 방명록이 없습니다.");
						continue;
					}
					int deleteId = 0;
					while(true) {
						System.out.print("삭제하고 싶은 글ID:");
						
						try {
							deleteId=Integer.parseInt(scanner.nextLine());
							break;
						} catch (Exception e) {
							System.out.println("숫자로 된 ID를 입력해주세요");
						}

					}
					dos.writeInt(deleteId);
					
					if(dis.readInt()>0) {
						System.out.println("삭제 성공!");
					}else {
						System.out.println("해당하는 게시글이 없거나, 해당 포스트에 접근 권한이 없습니다.");
					}
					
					
				}else if(menuSelect.equals("5")) {
					//5. 방명록 검색 (작성자로 검색)
					
					int postsCount = dis.readInt();
					
					if(postsCount>0) {
						System.out.println(PRINT_STRING);
						for(int i=0; i<postsCount;i++) {
							String post = dis.readUTF();
							System.out.println(post);
						}
					}else {
						System.out.println("현재 서버에 남아있는 방명록이 없습니다.");
						continue;
					}
					
					System.out.print("검색할 작성자ID:");
					String searchID = scanner.nextLine();
					
					dos.writeUTF(searchID);
					dos.flush();
					
					postsCount = dis.readInt();
					
					if(postsCount>0) {
						System.out.println(PRINT_STRING);
						for(int i=0; i<postsCount;i++) {
							String post = dis.readUTF();
							System.out.println(post);
						}
					}else {
						System.out.println("현재 서버에 남아있는"+searchID+"의 방명록이 없습니다.");
					}
					
					
					
				}else if(menuSelect.equals("6")) {
					//6. 로그아웃 (id 정보 말소)
					userID = "";
					System.out.println("로그아웃!");
					
				}
				
			}
			
		}
		
	}

}