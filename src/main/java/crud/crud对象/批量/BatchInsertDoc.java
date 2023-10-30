package crud.crud对象.批量;

/**
 * 对一个索引user进行批量新增
 * @author DJwei
 * @since 2023/10/26
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import crud.crud对象.单个对象.User;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;
import util.ConnectElasticsearch;

import java.io.IOException;

public class BatchInsertDoc {

    public static void main(String[] args) throws IOException {
        ConnectElasticsearch.connect(client -> {

            //创建批量新增请求对象
            BulkRequest request = new BulkRequest();

            request.add(new IndexRequest().index("user").id("1001").source(XContentType.JSON, "name", "zhangsan", "age", "10", "sex","女"));
            request.add(new IndexRequest().index("user").id("1002").source(XContentType.JSON, "name", "lisi", "age", "30", "sex","女"));
            request.add(new IndexRequest().index("user").id("1003").source(XContentType.JSON, "name", "wangwu1", "age", "40", "sex","男"));
            request.add(new IndexRequest().index("user").id("1004").source(XContentType.JSON, "name", "wangwu2", "age", "20", "sex","女"));
            request.add(new IndexRequest().index("user").id("1005").source(XContentType.JSON, "name", "wangwu3", "age", "50", "sex","男"));
            request.add(new IndexRequest().index("user").id("1006").source(XContentType.JSON, "name", "wangwu4", "age", "20", "sex","男"));
            request.add(new IndexRequest().index("user").id("1007").source(XContentType.JSON, "name", "小一"));
            /**
             * 用对象的形式进行新增
             */
            User user = new User();
            user.setName("dot类型");
            user.setAge(20);
            user.setSex("男");
            ObjectMapper objectMapper = new ObjectMapper();
            String productJson = objectMapper.writeValueAsString(user);
            request.add(new IndexRequest().index("user").id("1008").source(productJson,XContentType.JSON));

            //客户端发送请求，获取响应对象
            BulkResponse responses = client.bulk(request, RequestOptions.DEFAULT);
            //打印结果信息
            System.out.println("took:" + responses.getTook());
            System.out.println("items:" + responses.getItems());
        });
    }
}

