package pk.wei.com.newpractice;

import org.junit.Before;
import org.junit.Test;

import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import pk.wei.com.newpractice.thirdParty.retrofit.TestClient;
import pk.wei.com.newpractice.thirdParty.retrofit.StringCallAdapterFactory;
import pk.wei.com.newpractice.thirdParty.retrofit.StringConverterFactory;
import retrofit2.Retrofit;

public class RetrofitUnitTest {

    TestClient restClientV1;

    @Before
    public void create() {
        MockWebServer mockWebServer = new MockWebServer();
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

        OkHttpClient client = new OkHttpClient.Builder().build();
        restClientV1 = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .client(client)
                .addCallAdapterFactory(new StringCallAdapterFactory())
                .addConverterFactory(new StringConverterFactory())
                .build()
                .create(TestClient.class);

    }

    @Test
    public void test() {
        String detail = restClientV1.getName(1);
        System.out.println(detail);
    }
}
