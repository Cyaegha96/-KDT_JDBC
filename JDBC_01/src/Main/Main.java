package Main;

import java.sql.SQLException;
import java.util.List;

import dao.CafeDAO;
import dto.CafeDTO;

public class Main {
	
	//main은 예외를 전가하는 마지막 보루
	//main에서 throws 구문을 썼다는건, 예외를 처리하지 않겠다는 의미.
	public static void main(String[] args) {
		CafeDAO dao = new CafeDAO();
		
		try {
			//dao.insert(new CafeDTO(0,"Apple Juice", 4000,"y"));
			
			//애플주스의 가격을 500원 올리는 코드를 작성.
			//dao.update(new CafeDTO(1012,"Apple Juice", 4500,"y"));
			
			//Americano를 삭제하는 기능 작성 및 실행
			//dao.deleteByID(1010);
			
			
			List<CafeDTO> list =  dao.selectAll();
			for(CafeDTO dto : list) {
				System.out.println(dto.getId() + ": "+
									dto.getNameString()+"\t"+
									dto.getPrice()+":"+
									dto.getIceAble());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("데이터 입력 도중 에러가 발생햇습니다.");
			System.out.println("같은 오류가 반복될 시에는 관리자에게 문의주세요");
			System.out.println("Email : admin@admin.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
