package crud.crud对象.查询;

/**
 * 条件查询精准查询
 * QueryDocCondition
 * @author DJwei
 * @since 2023/10/26
 */
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import util.ConnectElasticsearch;
import util.ElasticsearchTask;

import java.io.IOException;

public class QueryDocCondition {

    public static final ElasticsearchTask SEARCH_BY_CONDITION = client -> {
        // 创建搜索请求对象
        SearchRequest request = new SearchRequest();
        request.indices("user");
        // 构建查询的请求体
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //具体查询，是精准查询的不是模糊查询
        sourceBuilder.query(QueryBuilders.termQuery("sex", "男"));
        sourceBuilder.query(QueryBuilders.termQuery("name", "wangwu3"));
        request.source(sourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 查询匹配
        SearchHits hits = response.getHits();
        System.out.println("took:" + response.getTook());
        System.out.println("timeout:" + response.isTimedOut());
        System.out.println("total:" + hits.getTotalHits());
        System.out.println("MaxScore:" + hits.getMaxScore());
        System.out.println("hits========>>");
        for (SearchHit hit : hits) {
            //输出每条查询的结果信息
            System.out.println(hit.getSourceAsString());
        }
        System.out.println("<<========");
    };

    public static void main(String[] args) throws IOException {
        ConnectElasticsearch.connect(SEARCH_BY_CONDITION);
    }
}

