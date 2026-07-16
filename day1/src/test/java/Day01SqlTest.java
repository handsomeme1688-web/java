/*
    junit：JDBC直连本地库测试
    写任何测试都是同一个套路：准备 → 执行 → 断言（Arrange-Act-Assert），
    外加一条铁律：测试要能反复跑，跑第二遍不能因为第一遍留下的数据而失败。

 */

// 第9行改成
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

public class Day01SqlTest {
    //JDBC 连接串格式固定：jdbc:mysql://主机:端口/库名。3306 是 MySQL 默认端口，database 是你建的库。
    static final String URL="jdbc:mysql://localhost:3306/database?useSSL=false&serverTimezone=UTC";
    static final String USER ="root";
    //从环境变量读密码。
    static final String password=System.getenv("MYSQL_PASSWORD");

    Connection conn;

    /**
     * @BeforeEach ： 每个 @Test 方法运行前自动执行。
     * 每个测试方法执行前都会先执行这个方法。
     * @throws Exception
     */
    @BeforeEach
    void setUp() throws Exception{//每个测试前后的固定动作
        // 获取连接
        conn=java.sql.DriverManager.getConnection(URL,USER,password);
        // 清场，保证可重复跑。把上次测试留下的 test_u 删掉，否则第二次跑测试①时数据已存在，插入会撞唯一约束、测试误报失败。
        conn.createStatement().execute("DELETE FROM user WHERE username='test_u'");
    }

    /**
     * @AfterEach ： 每个 @Test 方法运行后自动执行。
     * 每个测试跑完挂断电话线。
     * 连接是有限资源，不关会耗尽——这就是 Day 3 八股"finally 用于关资源"的实物。
     * @throws Exception
     */
    @AfterEach
    void tearDown() throws Exception{
        // 关闭连接
        conn.close();
    }

    @Test
        // 测试① 插入后查回，断言字段一致
    void insertThenQueryBack() throws Exception {
        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO user(username, password, nickname) VALUES(?,?,?)");
        ps.setString(1, "test_u"); ps.setString(2, "123456"); ps.setString(3, "小明");
        assertEquals(1, ps.executeUpdate());

        ResultSet rs = conn.createStatement()
                .executeQuery("SELECT nickname FROM user WHERE username='test_u'");
        assertTrue(rs.next());
        assertEquals("小明", rs.getString("nickname"));
    }

    @Test  // 测试② 重复 username 触发唯一约束
    void duplicateUsernameThrows() throws Exception {
        conn.createStatement().execute(
                "INSERT INTO user(username, password, nickname) VALUES('test_u','1','a')");
        assertThrows(SQLIntegrityConstraintViolationException.class, () ->
                conn.createStatement().execute(
                        "INSERT INTO user(username, password, nickname) VALUES('test_u','2','b')"));
    }

    @Test  // 测试③ 把手写 20 条里的一条 SELECT 变成断言（示例：LIMIT 分页条数）
    void selectWithLimit() throws Exception {
        ResultSet rs = conn.createStatement()
                .executeQuery("SELECT id FROM user ORDER BY id LIMIT 5");
        int count = 0;
        while (rs.next()) count++;
        assertTrue(count <= 5);
    }



}
