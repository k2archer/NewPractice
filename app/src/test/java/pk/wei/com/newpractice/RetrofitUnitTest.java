package pk.wei.com.newpractice;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import pk.wei.com.newpractice.thirdParty.retrofit.InterceptorDemo;
import pk.wei.com.newpractice.thirdParty.retrofit.StringCallAdapterFactory;
import pk.wei.com.newpractice.thirdParty.retrofit.StringConverterFactory;
import pk.wei.com.newpractice.thirdParty.retrofit.TestClient;
import retrofit2.Retrofit;

public class RetrofitUnitTest {

    MockWebServer mockWebServer;

    @Before
    public void create() {
        mockWebServer = new MockWebServer();
        mockWebServer.setDispatcher(new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                String url = request.getPath();
                System.out.println(url);
                switch (request.getPath()) {
                    case "/test_url?id=1":
                        return new MockResponse().setResponseCode(200).setBody("OK");
                    default:
                        return new MockResponse().setResponseCode(404);
                }
            }
        });

    }

    @Test
    public void test() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        TestClient testClient = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .client(client)
                .addCallAdapterFactory(new StringCallAdapterFactory())
                .addConverterFactory(new StringConverterFactory())
                .build()
                .create(TestClient.class);

        String name = testClient.getName(1);
        System.out.println(name);
    }

    @Test
    public void testInterceptor() {
        int DEFAULT_TIMEOUT = 30; // 秒
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS) // 设置超时时间
                .retryOnConnectionFailure(true);

        // 添加拦截
        client.addInterceptor(new InterceptorDemo());

        TestClient testClient = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .client(client.build())
                .addCallAdapterFactory(new StringCallAdapterFactory())
                .addConverterFactory(new StringConverterFactory())
                .build()
                .create(TestClient.class);

        String name = testClient.getName(1);
        System.out.println(name);

        try {
            mockWebServer.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
