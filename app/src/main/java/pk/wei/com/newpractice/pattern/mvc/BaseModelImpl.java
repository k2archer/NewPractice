package pk.wei.com.newpractice.pattern.mvc;

public class BaseModelImpl implements BaseModel {

    @Override
    public void getMessageWord(BaseListener listener) {
        listener.onSuccess("Hi, man.");
    }
}
