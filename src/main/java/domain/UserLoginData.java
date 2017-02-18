package domain;

public class UserLoginData {


	private String login;
	private String password;

	public UserLoginData(){

	}

	public UserLoginData(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

    public String getPassword() {
		return password;
	}

}
