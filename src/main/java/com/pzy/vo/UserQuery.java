package com.pzy.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @Author Nice
 * @Date 2021/7/16 14:13
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserQuery {

    private Long id;
    private String nickname;
    private String username;
    private String email;
    private String avatar;
    @Temporal(TemporalType.TIMESTAMP)       //数据库日期生成类型
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)       //数据库日期生成类型
    private Date updateTime;
}
