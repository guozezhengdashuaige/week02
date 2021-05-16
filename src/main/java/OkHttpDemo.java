import com.google.common.base.Throwables;
import okhttp3.*;

import java.io.IOException;

public class OkHttpDemo {

    public static void main(String[] args) {
        async();
        sync();
    }


    private static void async() {
        String url = "http://localhost:8088/test";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("has fail. e :" + Throwables.getStackTraceAsString(e));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("onResponse: " + response.body().string());
            }
        });
    }

    private static void sync() {
        String url = "http://localhost:8088/others";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        final Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println("run: " + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
