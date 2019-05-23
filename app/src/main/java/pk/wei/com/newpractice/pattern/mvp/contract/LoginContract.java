package pk.wei.com.newpractice.pattern.mvp.contract;

public interface LoginContract {
    interface Model {
        void login(String name, String password, LoginContract.Presenter loginPresenter);
    }

    interface View {
        void showToast(String msg);

        void onSuccess();

        String getName();

        String getPassWord();
    }

    interface Presenter {

        void onSuccess(); // 登陆成功

        void onFail(String msg); // 登陆失败
    }
}
