package crud.crud索引;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @author DJwei
 * @since 2023/10/26
 */
public class DeleteIndex {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = null;
        try {
            client = new RestHighLevelClient(
                    RestClient.builder(new HttpHost("localhost", 9200, "http")));
            // 删除索引 - 请求对象
            DeleteIndexRequest request = new DeleteIndexRequest("user54234");
            // 发送请求，获取响应
            AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
            // 操作结果
            System.out.println("操作结果 ： " + response.isAcknowledged());
        } catch (Exception e) {
            throw new RuntimeException("不存在");
        } finally {
            client.close();
        }
    }
}
