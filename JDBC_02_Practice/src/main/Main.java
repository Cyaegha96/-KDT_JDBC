package main;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.MovieDAO;
import dto.MovieDTO;

public class Main {
	
	static Scanner scanner = new Scanner(System.in);
	
	public static void menuCommand() {
		System.out.println("<<Netflix 영화 관리>>");
		System.out.println("1> 신규 영화 등록");
		System.out.println("2> 영화 목록 출력");
		System.out.println("3> 영화 업데이트");
		System.out.println("4> 영화 삭제(by id)");
		System.out.println("0> 시스템 종료");
	}
	
	public static MovieDTO inputMovie(int id) {
		System.out.print("영화 제목:");
		String movieTitle= scanner.nextLine(); 
		System.out.print("영화 장르:");
		String movieGenre = scanner.nextLine();
		
		
		MovieDTO dto = new MovieDTO(id,movieTitle,movieGenre);
		return dto;
	}
	
	public static void errorMessage(Exception e) {
		e.printStackTrace();
		System.out.println("데이터 처리 중 문제가 발생했습니다");
		System.out.println("가장 빠른 담당자에게 문의바랍니다.");
	}
	
	public static boolean printCheck(MovieDAO dao) throws SQLException {
		if(dao.getMovieListSize() <=0) {
			System.out.println("목록에 존재하는 영화 리스트가 없습니다.");
			return true;
		}
		return false;
	}
	
	public static int idCheck(MovieDAO dao) {
		int id=0;
		while(true) {
			try {
				System.out.print("영화 id:");
				id = Integer.parseInt(scanner.nextLine());
				if(dao.hasId(id )) {
					return id;
				}else {
					System.out.println("해당 아이디를 찾을 없습니다");
				}
				
				
			} catch (Exception e) {
				errorMessage(e);
			}
		}
	}
	
	static void printAllMovie(MovieDAO dao) throws SQLException {
		List<MovieDTO> movies = dao.selectAllMovie();
		System.out.println("ID\tTitle\tGenre\t");
		for(MovieDTO movie:movies) {
			System.out.println( String.format("%d\t%s\t%s\t", movie.getId(), movie.getTitle(), movie.getGenre()) );
			
		}
	}
	
	public static void main(String[] args) {
		
		MovieDAO dao = MovieDAO.getInstance();
		
		
		while(true) {
			menuCommand();
			String menu = scanner.nextLine();
			
			switch (menu) {
			case "1": 
				//영화 입력
				MovieDTO createMovie = inputMovie(0);
				try {
					int result = dao.addMovie(createMovie);
					System.out.println(result+"개(행)의 영화가 삽입되었습니다.");
					
				} catch (SQLException e) {
					errorMessage(e);
				}
				
				break;
			case "2":
				//영화 목록 출력
				
				try {
					
					if(printCheck(dao)) continue;
					
					printAllMovie(dao);
					
				} catch (SQLException e) {
					errorMessage(e);
				}
				
				break;
			case "3":// 영화 수정
	
				try {
					
					if(printCheck(dao)) continue;
					
					printAllMovie(dao);
					
					System.out.println("<<영화 수정>>");
					
					int updateId=idCheck(dao);
					MovieDTO updateMovie = inputMovie(updateId);
					
					if(dao.updateMovie(updateMovie)> 0) {
						System.out.println("수정에 성공하였습니다.");
					}
					
					
				} catch (SQLException e) {
					errorMessage(e);
				}
				
				
				break;
			
			case "4":
				try {
					
					if(printCheck(dao)) continue;

					printAllMovie(dao);
					
					System.out.println("<<영화 삭제>>");
					
					
					int removeid = idCheck(dao);
					
					dao.deleteByID(removeid);
					System.out.println("삭제가 완료되었습니다.");
					
				
				} catch (SQLException e) {
					errorMessage(e);
				}
	
				break;
			
			case "0":
				
				System.out.println("넷플릭스 시스템을 종료합니다.");
				System.exit(0);
				
				break;
			}
			
			
		}
		
	}
}
