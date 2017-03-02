package Entity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * User: zhengzhi
 * Date: 2017/3/2
 * Time: 9:31
 * To change this template use File | Settings | File Templates.
 * </pre>
 *
 * @author Administrator
 */

/**
 * 新闻任务实体类，对应数据库中的一张表 kbyq_topic_task_info
 */
public class NewsTask implements RowMapper<NewsTask> {

    private long task_id;
    private long site_id;
    private String task_name;
    private String task_disc;  //任务描述
    private Date start;  //开始时间
    private Date end;  //结束时间
    private String keyword;
    private String no_keyword;  //不包含关键字
    private String rd_keyword;  //任意关键字
    private String task_status;  //任务状态  running stopped broken
    private String update_date;  //最后一次任务更新时间
    private String pprate;           //'气泡活动频率',


    public static final String TASK_STATUS_RUNNING = "running";
    public static final String TASK_STATUS_STOPED = "stoped";
    public static final String TASK_STATUS_BROKEN = "broken";

    public String getPprate() {

        return pprate;
    }

    public long getTask_id() {
        return task_id;
    }

    public String getRd_keyword() {
        return rd_keyword;
    }

    public long getSite_id() {
        return site_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public String getTask_disc() {
        return task_disc;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getNo_keyword() {
        return no_keyword;
    }

    public String getTask_status() {
        return task_status;
    }

    public void setTask_id(long task_id) {
        this.task_id = task_id;
    }

    public void setSite_id(long site_id) {
        this.site_id = site_id;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public void setTask_disc(String task_disc) {
        this.task_disc = task_disc;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setNo_keyword(String no_keyword) {
        this.no_keyword = no_keyword;
    }

    public void setRd_keyword(String rd_keyword) {
        this.rd_keyword = rd_keyword;
    }

    public void setTask_status(String task_status) {
        this.task_status = task_status;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setPprate(String pprate) {
        this.pprate = pprate;
    }


    /**
     * 实现RowMapper接口；将结果结合中的每个元素，即每一行数据封装成用户定义的类
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */

    @Override
    public NewsTask mapRow(ResultSet rs, int rowNum) throws SQLException {
        NewsTask newsTask = new NewsTask();
        newsTask.setTask_id(rs.getLong("task_id"));
        newsTask.setSite_id(rs.getLong("site_id"));
        newsTask.setTask_name(rs.getString("task_name"));
        newsTask.setTask_disc(rs.getString("task_disc"));
        newsTask.setStart(rs.getDate("start_Date"));
        newsTask.setEnd(rs.getDate("end_Date"));
        newsTask.setPprate(rs.getString("pprate"));
        newsTask.setKeyword(rs.getString("keyword"));
        newsTask.setRd_keyword(rs.getString("rd_keyword"));
        newsTask.setNo_keyword(rs.getString("no_keyword"));
        newsTask.setTask_status(rs.getString("task_status"));
        newsTask.setUpdate_date(rs.getString("update_date"));

        return newsTask;
    }
}
