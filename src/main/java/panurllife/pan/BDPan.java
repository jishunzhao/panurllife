package panurllife.pan;

import org.jsoup.Jsoup;
import panurllife.PanLinkCheck;

import java.io.IOException;

public class BDPan extends PanLinkCheck {

    public String host = "pan.baidu.com";

    @Override
    public String getHost() {
        return host;
    }

    public boolean isLife(String url) throws IOException {
        return Jsoup.connect(url).get().title().contains("请输入提取码");
    }
}
