package panurllife.pan;

import com.alibaba.fastjson.JSON;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import panurllife.PanLinkCheck;

import java.io.IOException;


public class HCYPan extends PanLinkCheck {

    public String host = "caiyun.139.com";

    @Override
    public String getHost() {
        return host;
    }

    public boolean isLife(String url) throws IOException {
        String substring = "";
        if (url.contains("?")){
            substring = url.substring(url.lastIndexOf("?")+1);
        }else {
            substring = url.substring(url.lastIndexOf("/")+1);
        }
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "linkId="+substring);
        Request request = new Request.Builder()
                .url("https://caiyun.139.com/stapi/custom/outlink/brief")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        String msg = JSON.parseObject(client.newCall(request).execute().body().string()).getString("msg");
        return msg.equals("success");
    }
}
