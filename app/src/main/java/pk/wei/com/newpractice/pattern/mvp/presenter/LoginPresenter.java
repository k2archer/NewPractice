package pk.wei.com.newpractice.pattern.mvp.presenter;

import pk.wei.com.newpractice.pattern.mvp.contract.LoginContract;
import pk.wei.com.newpractice.pattern.mvp.model.LoginModel;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View loginView;
    private LoginContract.Model loginModel;

    public LoginPresenter(LoginContract.View loginView) {
        this.loginView = loginView;
        loginModel = new LoginModel();
    }

    public void login() {
        loginModel.login(loginView.getName(), loginView.getPassWord(), this);
    }

    @Override
    public void onSuccess() {
        loginView.onSuccess();
    }

    @Override
    public void onFail(String msg) {
        loginView.showToast(msg);
    }
}
