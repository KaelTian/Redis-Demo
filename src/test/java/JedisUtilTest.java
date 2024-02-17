import org.example.RedisUtils;
import org.junit.Test;

public class JedisUtilTest {
    @Test
    public void simpleTest() {
        RedisUtils jedis = RedisUtils.INSTANCE;
        jedis.set("kael-tian-key-0926", "这是卡尔田的第一个redis utils 的测试");
        System.out.println(jedis.get("kael-tian-key-0926"));
    }
}
