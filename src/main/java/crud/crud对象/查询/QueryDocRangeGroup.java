package crud.crud对象.查询;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import util.ConnectElasticsearch;
import util.ElasticsearchTask;

/**
 * @author DJwei
 * @since 2023/10/26
 */
public class QueryDocRangeGroup {

    public static final ElasticsearchTask SEARCH_BY_RANGE = client -> {
        // 创建搜索请求对象
        SearchRequest request = new SearchRequest();
        request.indices("user");
        /**
         * 范围查询
         */
        // 构建查询的请求体
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age");
        // 大于等于
        rangeQuery.gte("20");
        // 小于等于
        rangeQuery.lte("40");
        sourceBuilder.query(rangeQuery);
        /**
         * 范围查询之后进行过滤
         */
        //性别只可以是男的
        TermQueryBuilder termQuery = QueryBuilders.termQuery("sex", "男");
        // 过滤姓名是wangwu1的数据
        TermQueryBuilder termQueryName = QueryBuilders.termQuery("name", "wangwu1");

        sourceBuilder.postFilter(
                QueryBuilders.boolQuery()
                        .must(termQuery)
                        .mustNot(termQueryName)
        );
        /**
         * 模糊查询
         */
        sourceBuilder.query(QueryBuilders.fuzzyQuery("name","wangwu").fuzziness(Fuzziness.ONE));

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

    public static void main(String[] args) {
        ConnectElasticsearch.connect(SEARCH_BY_RANGE);
    }

}
