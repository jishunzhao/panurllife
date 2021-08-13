package panurllife;

import panurllife.pan.ALiPan;
import panurllife.pan.BDPan;
import panurllife.pan.HCYPan;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

public class PanUtil {

    private static LinkedList<PanLinkCheck> panList = new LinkedList<PanLinkCheck>();

    //有效百度云链接
    static String bdT = "https://pan.baidu.com/share/init?surl=lxtpm-R9dtKUMG1sUdk2Sw";
    //无效百度链接
    static String bdF = "https://pan.baidu.com/s/1zT_jAUVvqZMdrWUaFVGAmg";
    //有效和彩云
    static String hcyT = "https://caiyun.139.com/m/i?0r5CLohYeKNcQ";
    //无效和彩云
    static String hcyF = "https://caiyun.139.com/w/i/0r5C9ZfCOwdzG";
    //有效阿里
    static String aliT = "https://www.aliyundrive.com/s/D1Dp2r1tvKs";
    //无效阿里
    static String aliF = "https://www.aliyundrive.com/s/3LsHRpZbjM4";

    static {
        panList.add(new BDPan());
        panList.add(new HCYPan());
        panList.add(new ALiPan());
    }


    //检测网盘链接是否有效
    public static boolean isLife(String url) throws IOException {
        //判断链接属于什么网盘
        URL u;
        try {
            u = new URL(url);
        }catch (MalformedURLException e){
            System.out.println("无效的链接");
            return false;
        }
        String host = u.getHost();// 获取主机名
        for (PanLinkCheck panEntity : panList) {
            if(host.equals(panEntity.getHost())){
                return process(panEntity, url);
            }
        }
        System.out.println("未匹配");
        return false;
    }

    public static void main(String[] args) throws IOException {
        boolean life = isLife("https://caiyun.139.com/w/i/0r5CLohYeKNcQ");
        System.out.println(life);
    }

    public static boolean process(PanLinkCheck isPanUrlLife, String url) throws IOException {
        return isPanUrlLife.isLife(url);
    }
}
