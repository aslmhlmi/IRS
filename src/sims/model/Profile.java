package sims.model;

public class Profile {
	private String userID; //utk staff id
	private String roleUser;  // category staff @ admin
	private String nameUser;  //nama staff
	private String emailUser; //email staff
	private String telUser;   //tel staff
	private String addressUser; //address staff
	private Profile addressUse;
	private String usernameUser; //username staff
	private String passwordUser; //password staff
	private boolean valid;

	public Profile() {
		super();
	}
	
	public Profile(String userID, String roleUser, String nameUser, String emailUser, String telUser, String addressUser, String usernameUser, String passwordUser) {
		super();
		this.userID = userID;
		this.roleUser = roleUser;
		this.nameUser = nameUser;
		this.emailUser = emailUser;
		this.telUser = telUser;
		this.addressUser = addressUser;
		this.usernameUser = usernameUser;
		this.passwordUser = passwordUser;				
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String getRoleUser() {
		return roleUser;
	}

	public void setRoleUser(String roleUser) {
		this.roleUser = roleUser;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public String getTelUser() {
		return telUser;
	}

	public void setTelUser(String telUser) {
		this.telUser = telUser;
	}

	public String getAddressUser() {
		return addressUser;
	}

	public void setAddressUser(String addressUser) {
		this.addressUser = addressUser;
	}

	public String getUsernameUser() {
		return usernameUser;
	}

	public void setUsernameUser(String usernameUser) {
		this.usernameUser = usernameUser;
	}

	public String getPasswordUser() {
		return passwordUser;
	}

	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}
	public Profile getAddressUse() {
		return addressUse;
	}

	public void setAddressUse(Profile addressUse) {
		this.addressUse = addressUse;
	}
		
	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;

}

}


