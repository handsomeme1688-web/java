package com.zoee.day2.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 不放在 Entity 包下，因为这个类不是数据库实体类
 * 因此一般放在 pojo 包下或者domain 包下
 *
 * 用户信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private String name;

    private Integer age;

    private LocalDateTime  updateTime;

}
