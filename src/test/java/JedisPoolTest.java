import model.User;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.search.Document;
import redis.clients.jedis.search.FTCreateParams;
import redis.clients.jedis.search.IndexDataType;
import redis.clients.jedis.search.Query;
import redis.clients.jedis.search.schemafields.NumericField;
import redis.clients.jedis.search.schemafields.TagField;
import redis.clients.jedis.search.schemafields.TextField;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JedisPoolTest {
    private JedisPooled jedisPooled;

//    @Before
//    public void initItems() {
//        jedisPooled = new JedisPooled("CNSHAVMPPMOAP01.EY.NET", 6379);
//        User user1 = new User("Paul John", "paul.john@example.com", 42, "London");
//        User user2 = new User("Eden Zamir", "eden.zamir@example.com", 29, "Tel Aviv");
//        User user3 = new User("Paul Zamir", "paul.zamir@example.com", 35, "Tel Aviv");
////        jedisPooled.ftCreate("idx:users",
////                FTCreateParams.createParams()
////                        .on(IndexDataType.JSON)
////                        .addPrefix("user:"),
////                TextField.of("$.name").as("name"),
////                TagField.of("$.city").as("city"),
////                NumericField.of("$.age").as("age"));
//
//        jedisPooled.ftCreate("idx:users",
//                FTCreateParams.createParams()
//                        .on(IndexDataType.JSON)
//                        .addPrefix("user:"),
//                TextField.of("$.name").as("name"),
//                TextField.of("$.email").as("email"),
//                TagField.of("$.city").as("city"),
//                NumericField.of("$.age").as("age")
//        );
//        jedisPooled.jsonSetWithEscape("user:1", user1);
//        jedisPooled.jsonSetWithEscape("user:2", user2);
//        jedisPooled.jsonSetWithEscape("user:3", user3);
//    }

    @Test
    public void jedisPoolTest() {
        JedisPool pool = new JedisPool("10.199.118.139", 6379);

        try (Jedis jedis = pool.getResource()) {
            // Store & Retrieve a simple string
            jedis.set("foo", "bar");
            System.out.println(jedis.get("foo"));

            // Store & Retrieve a HashMap
            Map<String, String> hash = new HashMap<>();
            hash.put("name", "John");
            hash.put("surname", "Smith");
            hash.put("company", "Redis");
            hash.put("age", "29");
            jedis.hmset("user-session:123", hash);
            System.out.println(jedis.hgetAll("user-session:123"));
        }
    }


//    @Test
//    public void prefixFilterTest() {
//        Query query = new Query("Paul @age:[30 40]");
//        List<Document> result = jedisPooled.ftSearch("idx:users", query).getDocuments();
//        System.out.println(result);
//    }
}
