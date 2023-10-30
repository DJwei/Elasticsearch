package crud.crud对象.单个对象;

/**
 * @author DJwei
 * @since 2023/10/26
 */
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import util.ConnectElasticsearch;

import java.io.IOException;

public class GetDoc {

    public static void main(String[] args) throws IOException {
        ConnectElasticsearch.connect(client -> {
            //1.创建请求对象
            GetRequest request = new GetRequest().index("user").id("10090");
            //2.客户端发送请求，获取响应对象
            GetResponse response = client.get(request, RequestOptions.DEFAULT);
            //3.打印结果信息
            System.out.println("_index:" + response.getIndex());
            System.out.println("_type:" + response.getType());
            System.out.println("_id:" + response.getId());
            System.out.println("source:" + response.getSourceAsString());
            if(response.getSourceAsString()==null){
                System.out.println("没值");
            }
        });
    }
}


