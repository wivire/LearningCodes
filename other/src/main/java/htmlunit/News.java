package htmlunit;

import org.springframework.jdbc.core.PreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class News implements PreparedStatementSetter {
    private long topic_id = 0;           //热点id
    private long task_id;            //任务id
    private Date dw_create_date;     //新闻日期
    private String news_title;          //话题标题
    private long news_index;         //话题序号
    private String sections;            //媒体集
    private Set<String> tempsections = new HashSet<String>();
    private String news_url;             //新闻链接

    public long getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(long topic_id) {
        this.topic_id = topic_id;
    }

    public long getTask_id() {
        return task_id;
    }

    public void setTask_id(long task_id) {
        this.task_id = task_id;
    }

    public Date getDw_create_date() {
        return dw_create_date;
    }

    public void setDw_create_date(Date dw_create_date) {
        this.dw_create_date = dw_create_date;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public long getNews_index() {
        return news_index;
    }

    public void setNews_index(long news_index) {
        this.news_index = news_index;
    }

    public String getSections() {
        return sections;
    }

    public void setSections(String sections) {
        this.sections = sections;
    }

    public Set<String> getTempsections() {
        return tempsections;
    }

    public String getNews_url() {
        return news_url;
    }

    public void setNews_url(String news_url) {
        this.news_url = news_url;
    }

    public String getInsertSQL() {
        String sql = "INSERT INTO news(topic_id,task_id,dw_create_date,news_title,news_index,sections,news_url) VALUES(?,?,?,?,?,?,?)";
        return sql;
    }

    public void setValues(PreparedStatement ps) throws SQLException {

        ps.setLong(1, topic_id);
        ps.setLong(2, task_id);
        ps.setDate(3, new java.sql.Date(dw_create_date.getTime()));
        ps.setString(4, news_title);
        ps.setLong(5, news_index);
        ps.setString(6, sections);
        ps.setString(7, news_url);
    }

    @Override
    public boolean equals(Object obj) {
        News tnews = (News) obj;
        String msg = "完全重复";
        Iterator<String> it = tnews.tempsections.iterator();
        Boolean isTitleSame = (task_id + news_title).replace(" ", "").equals((tnews.task_id + tnews.news_title).replace(" ", ""));
        if (isTitleSame && !tempsections.contains(it.hasNext() ? it.next() : "")) {
            msg = "仅标题重复";
            tnews.sections = tnews.sections + sections;
            tnews.tempsections.add(tempsections.iterator().next());
        }
        System.out.println("INF : " + news_index + "与" + tnews.news_index + msg);
        return isTitleSame;
    }

    @Override
    public int hashCode() {
        return (task_id + news_title).replace(" ", "").hashCode();
    }

}
