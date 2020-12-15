package com.example.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 名字
     */
    private String name;

    /**
     * 真实姓名
     */
    private String realName;

    private Byte sex;

    /**
     * 用户状态
     */
    private Byte status;

    /**
     * 默认地址 ID
     */
    private Long addressId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}