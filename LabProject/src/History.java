import java.util.Date;

public class History {
	private String teacher;
	private String item;
	private int quantity; 
	private Date pickupDate;
	private Date returnDate;
	
	public History(String teacher, String item, int quantity) {
		this.teacher = teacher;
		this.item = item;
		this.quantity = quantity;
		this.pickupDate = new Date();
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public String getStatus() {
		if (returnDate == null)
			return "Picked up";
		else
			return "Returned";
	}
	
	
	
	
}
