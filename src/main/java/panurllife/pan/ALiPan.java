package panurllife.pan;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import panurllife.PanLinkCheck;

import java.io.IOException;

public class ALiPan extends PanLinkCheck {

    private String host = "www.aliyundrive.com";

    @Override
    public String getHost() {
        return host;
    }

    public boolean isLife(String url) throws IOException {
        String substring = url.substring(url.lastIndexOf("/") + 1);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"share_id\":\""+substring+"\"}");
        Request request = new Request.Builder()
                .url("https://api.aliyundrive.com/adrive/v3/share_link/get_share_by_anonymous")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        String creator_id = JSON.parseObject(client.newCall(request).execute().body().string()).getString("creator_id");
        return creator_id != null;
    }
}
