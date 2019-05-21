package pk.wei.com.newpractice.pattern;

public class BaseModelImpl implements BaseModel {

    @Override
    public void getMessageWord(BaseListener listener) {
        listener.onSuccess("Hi, man.");
    }
}
