package ittalents.workplan.model.POJO;

public class User {
	private int id;
	private String username;
	private String email;
	private String password;
	private String avatarPath;
	
	
	private Integer admin=0;
	private Integer organizationId=null;
	
	
	public User() {
		
	}

	public User(int id, String username, String email, String password,
			String avatarPath) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.avatarPath = avatarPath;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatarPath() {
		return avatarPath;
	}

	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}

	public void setAdmin(Integer admin) {
		this.admin = admin;
	}

	public Integer getAdmin() {
		return admin;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", avatarPath=" + avatarPath + ", admin=" + admin + ", organizationId=" + organizationId + "]";
	}

}
