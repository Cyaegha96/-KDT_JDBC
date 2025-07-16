package main;

import java.sql.SQLException;
import java.util.Scanner;

import dao.UserDAO;


public class LoginMain {
	
	static String nameString= null;
	public static void beforeLoginMenuPrint() {
		System.out.println("<<인증 시스템>>");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("0.종료");
	}
	
	public static void afterLoginMenuPrint() {
		System.out.println(nameString+"님 환영합니다!");
		System.out.println("1.로그아웃");
		System.out.println("종료");
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		UserDAO userdao = UserDAO.getInstance();
		
		while(true) {
			if(nameString == null) {

				beforeLoginMenuPrint();
				System.out.print(">>");
				String menuString = sc.nextLine();
				switch (menuString) {
				case "1":
					//로그인
					try {
						if(!userdao.hasOtherUsers()) {
							System.out.println("아직 DB에 유저 정보가 존재하지 않습니다.");
							continue;
						}
					}catch (Exception e) {
						e.printStackTrace();
					}
					
					
					System.out.println("로그인을 진행합니다");
					System.out.print("입력할 ID:");
					String userIdForLogin = sc.nextLine();
					if(userdao.incorrectId(userIdForLogin)) {
						System.out.println("로그인에 사용할 수 없는 문자열이 입력되었습니다.");
						continue;
					}
					System.out.print("입력할 PW:");
					String userPasswordForLogin=sc.nextLine();
					userPasswordForLogin = Util.encrypt(userPasswordForLogin);
					
				
					try {
						nameString = userdao.login(userIdForLogin, userPasswordForLogin);
						if(nameString != null) {
							System.out.println("로그인 성공!");
						}else {
							System.out.println("ID 또는 PW가 일치하지 않습니다.");
							System.out.println("아이디, 비밀번호를 확인해주세요.");
						}
					
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
					
					
					
					
					break;
				case "2":
					//회원가입
					try {
						System.out.println("회원가입을 진행합니다.");
						System.out.println("입력할 ID:");
						String userIdForRegister = sc.nextLine();
						if(userdao.incorrectId(userIdForRegister)) {
							System.out.println("회원가입에 사용할 수 없는 문자열이 입력되었습니다.");
							continue;
						}
						if(userdao.hasId(userIdForRegister)) {
							System.out.println("해당 아이디는 이미 존재합니다.");
							 continue;
						}
						System.out.println("입력할 PW:");
						String userPasswordForRegister = sc.nextLine();
						//SHA-512 암호화
						userPasswordForRegister = Util.encrypt(userPasswordForRegister);
						
						System.out.println("입력할 이름:");
						String userNameforRegister= sc.nextLine();
						if(userdao.incorrectName(userNameforRegister)) {
							System.out.println("회원가입에 사용할 수 없는 문자열이 입력되었습니다.");
							continue;
						}
						
						
						int result = userdao.addUser(userIdForRegister, userPasswordForRegister, userNameforRegister);
						if(result >0) {
							System.out.println("회원가입에 성공하였습니다.");
						}
					}catch(Exception e) {
						e.printStackTrace();
						
					}
					
					
					break;
				case "0":
					//시스템 종료
					System.out.println("인증 시스템을 종료합니다.");
					System.exit(0);
				default:
					System.out.println("제대로 된 메뉴 번호를 입력해주세요");
					break;
				}
			
			}else {
				afterLoginMenuPrint();
				System.out.print(">>");
				String menuString = sc.nextLine();
				switch (menuString) {
				case "1":
					System.out.println("로그아웃을 진행합니다.");
					nameString = null;
					break;
				case "0":
					System.out.println("인증 시스템을 종료합니다.");
					System.exit(0);
				default:
					System.out.println("제대로 된 메뉴 번호를 입력해주세요");
					break;
				}
				
			}
		
		}
	}
}
