package pk.wei.com.newpractice.pattern.mvp.model;

import pk.wei.com.newpractice.pattern.mvp.contract.LoginContract;

public class LoginModel implements LoginContract.Model {
    @Override
    public void login(String name, String password, LoginContract.Presenter loginPresenter) {
        UserBean ub = getBean();
        if (name.isEmpty() || password.isEmpty()) {
            loginPresenter.onFail("账号密码不能为空");
        } else if (name.equals(ub.accountNumber) && password.equals(ub.password)) {
            loginPresenter.onSuccess();
        } else {
            loginPresenter.onFail("账号密码错误");
        }
    }

    public UserBean getBean() {
        UserBean bean = new UserBean();
        bean.accountNumber = "admin";
        bean.password = "123456";
        return bean;
    }

    public class UserBean {
        public String accountNumber;
        public String password;

    }
}
