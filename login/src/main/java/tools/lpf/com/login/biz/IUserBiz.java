package tools.lpf.com.login.biz;

/**
 * liupf1213@gmail.com
 * Created by liupf5 on 16/8/12.
 * Description:
 */
public interface IUserBiz {

    public void login(String username, String password,OnLoginListener listener);
}
