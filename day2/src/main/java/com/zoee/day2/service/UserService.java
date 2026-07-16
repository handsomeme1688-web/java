package com.zoee.day2.service;

import com.zoee.day2.pojo.User;

import java.util.List;

/**
 * 为什么需要接口？
 * 1. 模块化设计：接口定义了模块之间的通信方式，而不需要实现细节。利用MyBatis，我们可以将SQL语句和Java代码解耦。
 * 2. 抽象：接口提供了一种抽象的方式，可以忽略实现细节，只关注方法签名。
 * 3. 多态：接口支持多态，即同一个接口可以被不同的类实现，从而实现不同的功能。
 * 4. 解耦：接口促进了解耦，因为类之间的依赖关系是通过接口定义的，而不是实现细节。
 * 5. 可扩展性：接口提供了可扩展性，因为新的实现类可以很容易地添加到系统中，而不会影响现有的代码。
 * 6. 单元测试：接口可以方便地进行单元测试，因为可以模拟实现类。
 */
public interface UserService {
    List<User> findAll();
}
