package ittalents.workplan.model.POJO;

public class Organization {

	private String name;
	private int adminID;

	public Organization(String name, int adminID) {
		super();
		this.name = name;
		this.adminID = adminID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAdminID() {
		return adminID;
	}

	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}

}
