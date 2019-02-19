//package com.hytx.chapterService;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.util.List;
//import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
//import org.elasticsearch.action.delete.DeleteResponse;
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.action.search.SearchRequestBuilder;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.action.search.SearchType;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.index.query.BoolQueryBuilder;
//import org.elasticsearch.index.query.ExistsQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.index.query.TermQueryBuilder;
//import org.elasticsearch.index.query.WildcardQueryBuilder;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.SearchHits;
//import org.elasticsearch.search.aggregations.AggregationBuilders;
//import org.elasticsearch.search.aggregations.bucket.terms.Terms;
//import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
//import org.elasticsearch.search.aggregations.metrics.sum.Sum;
//import org.elasticsearch.search.sort.SortOrder;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
// 
//import com.dongao.phoenix.framework.util.JsonUtil;
// 
//public class myesTest2 {
// 
//	public final static String HOST = "127.0.0.1";
//	// http请求的端口是9200，客户端是9300
//    public final static int PORT = 9300; 
//    static TransportClient client=null;
//    
//    @Before
//    public void beforeMethod(){
//    	 try {
// 	    	//获取es连接
// 			Settings setting = Settings.builder()
// 					.put("cluster.name", "ec-elasticsearch")
// 					.put("client.transport.sniff", true)//启动嗅探功能
// 					.build();
// 			 // 创建client
// 			client = new PreBuiltTransportClient(setting)
// 			        .addTransportAddresses(new InetSocketTransportAddress(InetAddress.getByName(HOST), PORT));
// 			System.out.println("连接成功！");
// 		} catch (UnknownHostException e) {
// 			e.printStackTrace();
// 		}
//    }
//    
//    //向es中插入一条数据
//    @Test
//	public  void insert() throws Exception {
//		//获取es连接
//         EsTestVo esTestVo = new EsTestVo(2l, "创建客户端1测试数据", "这是一条测试数据，可以用来进行测试");
//          EsTestVo esvo=new EsTestVo(3l, "张一", 5, "张一是个好人");
//          EsTestVo esvo1=new EsTestVo(4l, "张二", 5, "张二是个坏人");
//          EsTestVo esvo2=new EsTestVo(5l, "张三", 20, "张三是个好人");
//          EsTestVo esvo3=new EsTestVo(6l, "张四", 30, "张四是个坏人");
//          EsTestVo esvo5=new EsTestVo(6l, "张五", 30, "张五有点傻");
//         String json = JsonUtil.toJson(esvo);
//        //插入数据时不给id赋值，自动生成id。
//        IndexResponse response =client.prepareIndex("accesstest", "test01").setSource(json).execute().actionGet();
//        //多次index这个版本号会变  
//        System.out.println("response.version():"+response.getVersion());  
//	}
//	//根据id查询方法测试
//	@Test   
//	public void getTest() throws Exception{
//         GetResponse response = client.prepareGet("accesstest", "test01", "1").execute().actionGet();
//         System.out.println(response.getId());
//         System.out.println(response.getSourceAsString());
//	}
//	
//	//根据条件进行查询
//	@Test
//	public void searchByQuery(){
//		//设置搜索的范围（index和type）
//		SearchRequestBuilder searchBuilder = client.prepareSearch("accesstest").setTypes("test01");
//		//单词匹配
////		MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("name", "创建");
//		TermQueryBuilder matchQuery = QueryBuilders.termQuery("name", "张一");
//		//SearchResponse myresponse=responsebuilder.setQuery(QueryBuilders.matchPhraseQuery("title", "molong1208 blog"))  
//		//.setFrom(0).setSize(10).setExplain(true).execute().actionGet();  分页不包头，包尾
//		SearchResponse reponse = searchBuilder.setQuery(matchQuery).setFrom(0).setSize(6).setExplain(true).execute().actionGet();
//		//结果展示
//		SearchHits hits = reponse.getHits();
//		for (SearchHit searchHit : hits) {
//			System.out.println(searchHit.getSourceAsString());
//		}
//		System.out.println("根据条件查询测试");
//	}
//	   //根据条件进行查询--多词条查询
//		@Test
//		public void searchByQuery1(){
//			//设置搜索的范围（index和type）
//			SearchRequestBuilder searchBuilder = client.prepareSearch("accesstest").setTypes("test01");
//			//单词匹配
//			//SearchResponse myresponse=responsebuilder.setQuery(QueryBuilders.matchPhraseQuery("title", "molong1208 blog"))  
//			//.setFrom(0).setSize(10).setExplain(true).execute().actionGet();  分页不包头，包尾
//			SearchResponse reponse = searchBuilder.setQuery(QueryBuilders.multiMatchQuery("测试","name","des")).execute().actionGet();
////			SearchResponse reponse = searchBuilder.setQuery(QueryBuilders.matchAllQuery()).execute().actionGet();
//			//结果展示
//			SearchHits hits = reponse.getHits();
//			for (SearchHit searchHit : hits) {
//				System.out.println(searchHit.getSourceAsString());
//			}
//			System.out.println("根据条件查询测试");
//		}
//		
//	@Test  //多索引多类型查询，可以同时查询两个索引中的两个type中的数据
//	public void searchByQuery2(){
//		SearchRequestBuilder searchBulders = client.prepareSearch("accesstest","accesstest_01").setTypes("test01","test01");
//		SearchResponse searchResponse = 
//				searchBulders.setQuery(QueryBuilders.matchAllQuery())
//				             .setSearchType(SearchType.QUERY_THEN_FETCH)
//				             .setFrom(0).setSize(20)
//				             .execute().actionGet();
//		SearchHits hits = searchResponse.getHits();
//		for (SearchHit searchHit : hits) {
//			System.out.println(searchHit.getSourceAsString());
//		}
//	}
//	//范围查询   +排序    
//	/*
//	 * 经查询发现范围查询有两种方法。一个是使用QueryBuilders直接调用使用，还有一个是使用 .setPostFilter(FilterBuilders.rangeFilter("age").gte(18).lte(22))
//	 * 第二种方法没有试验
//	 */
//	@Test
//	public void searchByquery3(){
//		SearchRequestBuilder builder = client.prepareSearch("accesstest").setTypes("test01");
//		//第一种直接调用
//		SearchResponse searchResponse = 
//				builder.setQuery(QueryBuilders.rangeQuery("age").gt(10))
//				 .addSort("id",SortOrder.DESC)
//				.get();
//		SearchHits hits = searchResponse.getHits();
//		for (SearchHit searchHit : hits) {
//			System.out.println(searchHit.getSourceAsString());
//		}
//	}
// 
//  @After
//	public void close(){
//		client.close();
//		System.out.println("关闭成功");
//	}
//	//根据id删除数据测试
//	@Test  
//	public void deleteTest() throws Exception{
//		//获取es连接
//		Settings setting = Settings.builder().put("cluster.name", "ec-elasticsearch").build();
//		 // 创建client
//        TransportClient client = new PreBuiltTransportClient(setting)
//                .addTransportAddresses(new InetSocketTransportAddress(InetAddress.getByName(HOST), PORT));
//         DeleteResponse response = client.prepareDelete("accesstest", "test01", "2").execute().actionGet();
//         System.out.println(response.getId());
//         System.out.println(response.getResult());
//         client.close();  
//	}
//	 //删除索引
//	@SuppressWarnings("unchecked")
//	@Test  
//	public void deleteIndex() throws Exception{
//        //删除的方法
//        DeleteIndexResponse response = client.admin().indices().prepareDelete("accesslog").execute().actionGet();
//         //判断是否删除成功，成功为true,失败是false
//        System.out.println( response.isAcknowledged());;
//        client.close();  
//	}
//}
//--------------------- 
//作者：★星光雨★ 
//来源：CSDN 
//原文：https://blog.csdn.net/m15732622413/article/details/83344843 
//版权声明：本文为博主原创文章，转载请附上博文链接！