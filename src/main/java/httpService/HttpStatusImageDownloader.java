package httpService;


import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import java.io.*;


public class HttpStatusImageDownloader {

    public void downloadStatusImage(int code){
        String url = "https://http.cat/"+code+".jpg";
        OkHttpClient client = new OkHttpClient();
        if(defineIfCodeValid(client, url)){
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            try {
                Response response = client.newCall(request).execute();
                InputStream inputStream = response.body().byteStream();
                File file = new File("cat"+code+".jpg");
                if (!file.exists()){
                    file.mkdirs();
                }
                BufferedImage image = ImageIO.read(inputStream);
                ImageIO.write(image,"jpg", file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            throw  new IllegalArgumentException("code is not valid");
        }


    }

    private boolean defineIfCodeValid(OkHttpClient client, String url){
        Request request = new Request.Builder()
                .url(url)
                .head()
                .build();

        try {
            Response execute = client.newCall(request).execute();
            if (execute.code()==200){
                return true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}