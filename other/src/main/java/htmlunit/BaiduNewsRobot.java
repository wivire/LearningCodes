package htmlunit;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * User: zhengzhi
 * Date: 2017/3/2
 * Time: 16:21
 * To change this template use File | Settings | File Templates.
 * </pre>
 *
 * @author Administrator
 */
public class BaiduNewsRobot {
    private Log LOG = LogFactory.getLog(BaiduNewsRobot.class);

    private WebClient webClient;

    //爬取新闻方法
    public List<News> getNews(String searchWord, String startTime, String endTime, long pagesize) {


        long pn = 0;
        String url = "http://news.baidu.com/ns?cl=2&ct=1&tn=newsdy&ie=utf-8";
        StringBuilder sburl = new StringBuilder();
        sburl.append(url);
        sburl.append("&word=");
        sburl.append(searchWord);
        sburl.append("&bt=");
        sburl.append(startTime);
        sburl.append("&et=");
        sburl.append(endTime);
        sburl.append("&rn=");
        sburl.append(pagesize);  //rn(record number) 每页包含的搜索结果数目
        sburl.append("&pn="); //pn(page number) 网页页码
        url = sburl.toString();

        long size = getSourceSize(getPage(webClient, url + pn), 500);
    }

    //使用指定的浏览器，根据url获取页面；
    public HtmlPage getPage(WebClient webClient, String url) {
        HtmlPage page;
        if (webClient == null) {
            return null;
        }
        try {
            page = webClient.getPage(url);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return page;
    }


    private long getSourceSize(HtmlPage page, long maxsize) {
        if (page == null) {
            return 0;
        }
        long size = 0;
        try {
            /**
             * 从htmlpage页面中获取ID为“header_top_bar”div块
             *
             * <div id="header_top_bar">
             <span class="nums">找到相关新闻约4,090,000篇</span>
             */

            HtmlDivision div = (HtmlDivision) page.getElementById("header_top_bar");
            String sizes = div.getFirstChild().getTextContent().replace("找到相关新闻", "").replace("篇", "").replace("约", "").replace(",", "").trim();
            if (sizes.length() > 0) {
                size = Long.valueOf(sizes);
            }
            LOG.info(div.getFirstChild().getTextContent());
//            System.out.println(div.getFirstChild().getTextContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (size > maxsize) {
            size = maxsize;
            LOG.info("记录数过大，仅记录：" + maxsize + "篇");
//            System.out.println("记录数过大，仅记录：" + maxsize + "篇");
        }
        return size;
    }

}
