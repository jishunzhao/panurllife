package panurllife;

import java.io.IOException;

public abstract class PanLinkCheck {
    //链接是否有效
    public abstract boolean isLife(String url) throws IOException;
    //获得匹配的host
    public abstract String getHost();
}
