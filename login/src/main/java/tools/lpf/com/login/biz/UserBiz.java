package tools.lpf.com.login.biz;

import tools.lpf.com.login.bean.User;
import tools.lpf.com.login.view.IUserLoginView;

/**
 * liupf1213@gmail.com
 * Created by liupf5 on 16/8/12.
 * Description:
 */
public class UserBiz implements IUserBiz{

    private static final String USERNAME = "123";
    private static final String PASSWORD = "123";

    @Override
    public void login(final String username, final String password, final OnLoginListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(USERNAME.equals(username) && PASSWORD.equals(password)){
                    User user = new User(username,password);
                    listener.loginSuccess(user);
                }else{
                    listener.loginFailed();
                }
            }
        }).start();
    }
}
