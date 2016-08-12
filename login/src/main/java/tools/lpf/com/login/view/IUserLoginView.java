package tools.lpf.com.login.view;

import tools.lpf.com.login.bean.User;

/**
 * liupf1213@gmail.com
 * Created by liupf5 on 16/8/12.
 * Description:
 */
public interface IUserLoginView {

    String getUserName();
    String getPassword();

    void clearUserName();
    void clearPassword();
    void showLoading();
    void hideLoading();
    void toMainActivity(User user);
    void showFailedError();
}
