package pk.wei.com.newpractice.thirdParty.retrofit;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 定义直接返回 String(不是ResponseBody的String) 接口
 * 需要自定义 CallAdapterFactory, ConverterFactory
 * 参考 StringCallAdapterFactory, StringConverterFactory
 **/
public interface TestClient {
    @GET("test_url")
    String getName(@Query("id") long id);
}