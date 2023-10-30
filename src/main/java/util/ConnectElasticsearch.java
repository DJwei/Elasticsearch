package util;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @author DJwei
 * @since 2023/10/26
 */
public class ConnectElasticsearch{

    public static void connect(ElasticsearchTask task)  {
        // 创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));
        try {
            task.doSomething(client);
// 关闭客户端连接
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 关闭客户端连接
            //client.close();
        }
    }
}

