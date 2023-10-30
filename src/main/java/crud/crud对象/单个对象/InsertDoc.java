package crud.crud对象.单个对象;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;
import util.ConnectElasticsearch;

import java.io.IOException;

/**
 * @author DJwei
 * @since 2023/10/26
 */
public class InsertDoc {

    public static void main(String[] args) throws IOException {
        ConnectElasticsearch.connect(client -> {
            // 新增文档 - 请求对象
            IndexRequest request = new IndexRequest();
            // 设置索引及唯一性标识
            request.index("user").id("10090");

            // 创建数据对象
            User user = new User();
            user.setName("zhangsan");
            user.setAge(202);
            user.setSex("2022-08-18 11:08:04");

            ObjectMapper objectMapper = new ObjectMapper();
            String productJson = "{\"pageNo\":1,\"pageSize\":10,\"id\":\"156010119300562540123\",\"provCode\":\"43\",\"insureRegCode\":\"439900\",\"itemMark\":\"103\",\"itemCode\":\"T111105214\",\"itemName\":\"金银花炭菊花茶叶\",\"itemType\":\"102\",\"itemDosage\":\"\",\"itemSpec\":\"\",\"itemUnitCode\":\"\",\"prod\":\"\",\"deductible\":\"\",\"directory\":\"\",\"pym\":\"\",\"wbm\":\"\",\"isValid\":\"1\",\"crteId\":\"1290816158941450240\",\"crteName\":\"管理员\",\"crteTime\":\"2022-08-18 11:08:04\",\"ver\":\"\",\"verName\":\"V0000\",\"size\":3000,\"num\":1,\"recordCounts\":15330,\"whetherInjury\":\"0\",\"downLoadType\":\"1302\"}";//objectMapper.writeValueAsString(user);
            request.source(productJson, XContentType.JSON);
            // 客户端发送请求，获取响应对象
            IndexResponse response = client.index(request, RequestOptions.DEFAULT);
            //3.打印结果信息
            System.out.println("_index:" + response.getIndex());
            System.out.println("_id:" + response.getId());
            System.out.println("_result:" + response.getResult());
        });
    }
}

