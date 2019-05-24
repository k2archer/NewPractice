package pk.wei.com.newpractice.thirdParty.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetRequest_Interface {

    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    Call<Translation> getCall();
    // 注解里传入 网络请求 的部分 URL 地址
    // Retrofit 把网络请求的URL分成了两部分：一部分放在 Retrofit 对象里，另一部分放在网络请求接口里
    // 如果接口里的 url 是一个完整的网址，那么放在 Retrofit 对象里的 URL 可以忽略
    // getCall()是接受网络请求数据的方法
}