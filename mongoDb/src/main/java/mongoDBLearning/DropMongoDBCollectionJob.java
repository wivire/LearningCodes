package mongoDBLearning;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.joda.time.DateTime;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 删除mongo中的数据库,每天暴力执行一到两次,以确保删除成功
 */

public class DropMongoDBCollectionJob implements Callable {

    public static Log LOG = LogFactory.getLog(DropMongoDBCollectionJob.class);

    protected transient MongoTemplate mongoTemplate;

    protected List<String> mongoDbList = null;

    public DropMongoDBCollectionJob() {
    }

    public DropMongoDBCollectionJob(Map<String, Object> params) {
        final String[] confs = ((Configuration) params.get("conf")).getStrings("mongo.output.uri");
        this.mongoTemplate = (MongoTemplate) params.get("mongoTemplate");
        this.mongoDbList = Arrays.asList(confs);
    }

    @Override
    public Object call() throws Exception {
        final DateTime now = DateTime.now();
        //要删除的数据库
        final String index2 = mongoDbList.get(now.plusDays(1).getDayOfYear() % mongoDbList.size());

        //应该判断表是否存在
       /*if(this.mongoTemplate.collectionExists(index2)) { //测试判断的时候一直返回false
            this.mongoTemplate.dropCollection(index2);
            LOG.info("删除的数据库为:" + index2);
        }*/

        this.mongoTemplate.dropCollection(index2);
        LOG.info("删除的数据库为:" + index2);

        return "";
    }


    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        Map param = context.getBean("commonJobParam", Map.class);

        DropMongoDBCollectionJob job = new DropMongoDBCollectionJob(param);

        try {
            job.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
