package crud.crud对象.查询;

/**
 * 分页查询
 * QueryDocPage
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
import util.ConnectElasticsearch;
import util.ElasticsearchTask;


public class QueryDocPage {

    public static final ElasticsearchTask SEARCH_BY_PAGING = client -> {
        // 创建搜索请求对象
        SearchRequest request = new SearchRequest();
        request.indices("user");
        // 构建查询的请求体
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchAllQuery());

        /**
         * 分页细节
         */
        // 分页查询下面这个写法的from(0)是指所有数据的索引为0的数据开始向后查询两条数据
        // 当前页其实索引(第一条数据的顺序号)， from
        sourceBuilder.from(0);
        // 每页显示多少条 size
        sourceBuilder.size(2);

        //按页分页的查询是下面这种写法
        int page = 4; // 当前页数
        int pageSize = 2; // 每页显示的条数
        int startFrom = (page - 1) * pageSize;
        sourceBuilder.from(startFrom);
        sourceBuilder.size(pageSize);

        /**
         * 配合条件分页查询
         */
        //sourceBuilder.query(QueryBuilders.termQuery("sex", "男"));

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
        ConnectElasticsearch.connect(SEARCH_BY_PAGING);
    }

}

