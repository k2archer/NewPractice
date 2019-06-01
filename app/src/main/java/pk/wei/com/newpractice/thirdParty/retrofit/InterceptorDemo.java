package pk.wei.com.newpractice.thirdParty.retrofit;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

public class InterceptorDemo implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        RequestBody requestBody = request.body();
        String body = null;

        if(requestBody != null) {
            okio.Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);

            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(Charset.forName("UTF-8"));
            }
            body = buffer.readString(charset);
        }

        String text = String.format("发送请求 method：%s url：%s\nheaders: %sbody：%s\n",
                request.method(), request.url(), request.headers(), body);
        System.out.print("intercept: " + text);

        Response response = chain.proceed(request);

        return response;
    }
}
