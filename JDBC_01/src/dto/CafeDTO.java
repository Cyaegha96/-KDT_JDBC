package dto;

public class CafeDTO {
	private int id;
	private String nameString;
	private int price;
	private String iceAble;
	
	public CafeDTO() {
		
	}
	
	public CafeDTO(int id, String nameString, int price, String iceAble) {
		super();
		this.id = id;
		this.nameString = nameString;
		this.price = price;
		this.iceAble = iceAble;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameString() {
		return nameString;
	}
	public void setNameString(String nameString) {
		this.nameString = nameString;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getIceAble() {
		return iceAble;
	}
	public void setIceAble(String iceAble) {
		this.iceAble = iceAble;
	}
}
