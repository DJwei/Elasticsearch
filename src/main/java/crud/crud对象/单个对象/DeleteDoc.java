package crud.crud对象.单个对象;

/**
 * @author DJwei
 * @since 2023/10/26
 */
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;
import util.ConnectElasticsearch;

import java.io.IOException;

public class DeleteDoc {
    public static void main(String[] args) throws IOException {
        ConnectElasticsearch.connect(client -> {
            //创建请求对象
            DeleteRequest request = new DeleteRequest().index("user").id("10090");
            //客户端发送请求，获取响应对象
            DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
            //打印信息
            System.out.println(response.toString());
            System.out.println(response.getShardInfo().getSuccessful());
        });
    }
}

