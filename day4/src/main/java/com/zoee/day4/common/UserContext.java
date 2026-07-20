package com.zoee.day4.common;


//  /me 只是 UserContext 的其中一个消费者。这个架构是为"所有需要知道'当前是谁在请求'的接口"设计的，不是专为 /me 设计的。

/**
 *  请求一到达 Tomcat 的瞬间就分配了——从线程池里抽一条现成的。
 *  之后的一切（DispatcherServlet、拦截器、Controller、Service、Mapper）全都跑在这一条线程上。
 *  不是拦截后才分配，拦截器本身就已经在这条线程上跑了。
 *
 *
 */
public class UserContext {
    /**
     * 它是一个容器/工具对象（更准确地说是当前线程 Map 的 Key）
     * 它是个静态工具类，自己没有"执行时机"，只有三个被调用的时刻：
     * preHandle 验签成功后调 setUserId()（存）；
     * Controller/Service 里调 getUserId()（取）；
     * afterCompletion 里调 remove()（清）
     *
     *
     * 线程和请求的"绑定"不需要任何人做——Tomcat 派工那一刻天然成立："这次请求由线程 T 全程负责"。
     * USER_ID 不是线程的编号，它是储物格的"格位名"。
     * 想象每条线程天生自带一排储物柜；
     * USER_ID 这个 ThreadLocal 对象就是"第 3 格"这个格位；
     * set(7) = 往当前线程的第 3 格放一张写着 7 的纸条（7 是当前登录用户的数据库 id）；
     * get() = 从当前线程的第 3 格取纸条。
     * 线程 A 和线程 B 用同一个格位名，打开的是各自柜子的格子，所以互不干扰。
     */

    //ThreadLocal 里面不装数据，它是个"空壳"，数据存在线程身上。
    private static final ThreadLocal<Long> User_ID = new ThreadLocal<>();


    /**
     *  set 方法内部干的事（简化版源码）：
     * Thread t = Thread.currentThread();   // ① 找到"当前是哪条线程"
     * t 的私人Map.put(USER_ID, 7);         // ② 往这条线程的Map里存：key=USER_ID这把钥匙, value=7
     * @param id
     */
    public static void setUserId(Long id){
        User_ID.set(id);}
    public static Long getUserId(){
        return User_ID.get();
    }
    public static void remove(){
        User_ID.remove();
    }
}
