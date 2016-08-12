package tools.lpf.com.login.bean;

/**
 * liupf1213@gmail.com
 * Created by liupf5 on 16/8/12.
 * Description:
 */
public class User {

    private String userName;
    private String passWord;

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
