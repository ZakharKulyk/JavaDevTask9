package httpService;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HttpStatusChecker {

    public String getStatusImage(int statusCode) {
        OkHttpClient client = new OkHttpClient();
        String url = "https://http.cat//" + statusCode + ".jpg";

        Request request = new Request.Builder()
                .url(url)
                .head()
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return url;
            } else if (response.code() == 404) {
                throw new IllegalArgumentException(" status code is not valid in this case");
            }
            throw new IllegalArgumentException(" status code is not valid in this case");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
