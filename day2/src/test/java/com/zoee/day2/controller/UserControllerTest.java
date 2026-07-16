package com.zoee.day2.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * ============ MockMvc 测试的套路（背这一个公式就够了） ============
 *
 *   mockMvc.perform( 怎么发请求 )
 *          .andExpect( 期望什么 )
 *          .andExpect( 期望什么 )...   // 可以连着写多个期望
 *
 * “怎么针对自己的代码写测试”——三步：
 *   1. 打开自己写的 Controller，看每个接口：地址、GET 还是 POST、要什么参数、返回什么
 *   2. 正着测：传正常参数 → 断言状态码 200 + 返回内容对
 *   3. 反着测：传错的/不存在的 → 断言 404 等错误码
 *   每个接口至少一正一反，就是合格的测试。
 *
 * 位置规则：测试放 src/test/java，包名和被测类相同，类名 = 被测类 + Test
 * 运行方式：点类名/方法名左边的绿色 ▶ 即可，绿=通过，红=挂了
 *
 * 注意：Spring Boot 4 里 @AutoConfigureMockMvc 换了包名
 * （org.springframework.boot.webmvc.test.autoconfigure），
 * 网上大部分教程是 Boot 2/3 的老包名，直接抄会报“找不到符号”。
 */
@SpringBootTest          // 启动完整 Spring 容器（你所有的 Bean 都会被创建）
@AutoConfigureMockMvc    // 给你一个“假浏览器”MockMvc，不用真开 8080 端口
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;   // 假浏览器：能发请求、拿到响应

    // ① 手册要求 1：接口通不通？只看状态码
    @Test
    @DisplayName("GET /list 应该返回 200")
    void listShouldReturn200() throws Exception {
        mockMvc.perform(get("/list"))           // 模拟浏览器访问 GET /list
                .andExpect(status().isOk());    // 断言：HTTP 状态码 = 200
    }

    // ② 手册要求 2：内容对不对？用 jsonPath 检查响应体
    //    jsonPath 语法：$ 代表整个 JSON，$[0] 是数组第 1 个元素，$[0].username 是它的字段
    @Test
    @DisplayName("GET /list 返回的 JSON 应该和 user.txt 里的数据一致")
    void listShouldReturnUsersFromTxt() throws Exception {
        mockMvc.perform(get("/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))             // user.txt 有 3 行
                .andExpect(jsonPath("$[0].username").value("shangsan")) // 第 1 个用户
                .andExpect(jsonPath("$[2].name").value("guangzhou"));   // 第 3 个用户的 name
    }

    // ③ 手册要求 3：反面用例——不存在的地址必须 404
    @Test
    @DisplayName("请求不存在的路径应该返回 404")
    void notExistPathShouldReturn404() throws Exception {
        mockMvc.perform(get("/this-path-does-not-exist"))
                .andExpect(status().isNotFound());
    }

    // ④ 加餐：路径参数接口，断言返回的字符串内容
    @Test
    @DisplayName("GET /list3/5 应该返回含“第5个用户”的问候")
    void list3ShouldGreetById() throws Exception {
        mockMvc.perform(get("/list3/{id}", 5))  // {id} 会被 5 替换
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("第5个用户")));
    }

    // ⑤ 加餐：POST + JSON 请求体（对应你今天学的 @RequestBody）
    @Test
    @DisplayName("POST /list5 发 JSON 应该返回 json hello!")
    void list5ShouldAcceptJson() throws Exception {
        String json = """
                {"id":1,"username":"tom","password":"123","name":"汤姆","age":10}
                """;
        mockMvc.perform(post("/list5")
                        .contentType("application/json")  // 告诉服务器：body 是 JSON
                        .content(json))                   // 请求体
                .andExpect(status().isOk())
                .andExpect(content().string("json hello!"));
    }
}
