package tools.lpf.com.login.presenter;

import android.os.Handler;

import tools.lpf.com.login.bean.User;
import tools.lpf.com.login.biz.IUserBiz;
import tools.lpf.com.login.biz.OnLoginListener;
import tools.lpf.com.login.biz.UserBiz;
import tools.lpf.com.login.view.IUserLoginView;

/**
 * liupf1213@gmail.com
 * Created by liupf5 on 16/8/12.
 * Description:
 */
public class UserLoginPresenter {

    private IUserBiz userBiz;
    private IUserLoginView userLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.userBiz = new UserBiz();
        this.userLoginView = userLoginView;
    }

    public void login(){
        userLoginView.showLoading();
        userBiz.login(userLoginView.getUserName(), userLoginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(final User user) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.toMainActivity(user);
                        userLoginView.hideLoading();
                    }
                });
            }

            @Override
            public void loginFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.showFailedError();
                        userLoginView.hideLoading();
                    }
                });
            }
        });
    }

    public void clear(){
        userLoginView.clearUserName();
        userLoginView.clearPassword();
    }
}
