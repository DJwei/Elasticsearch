package util;


import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author DJwei
 * @since 2023/10/26
 */
public interface ElasticsearchTask {

    void doSomething(RestHighLevelClient client) throws Exception;

}

