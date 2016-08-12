package tools.lpf.com.login.biz;

import tools.lpf.com.login.bean.User;

/**
 * liupf1213@gmail.com
 * Created by liupf5 on 16/8/12.
 * Description:
 */
public interface OnLoginListener {

    void loginSuccess(User user);

    void loginFailed();
}
