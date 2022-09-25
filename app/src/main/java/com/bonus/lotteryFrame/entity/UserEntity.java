package com.bonus.lotteryFrame.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;

/**
 * @author 菜旗
 * @date 2022/4/4 20:33
 * @description 用户实体类
 */
@Entity(nameInDb = "tb_user")
public class UserEntity {
    /**
     * 唯一主键
     */
    @Property(nameInDb = "id")
    @Id(autoincrement = true)
    private Long id;
    /**
     * 名称
     */
    @Property(nameInDb = "name")
    private String name;
    /**
     * 电话
     */
    @Property(nameInDb = "phone")
    private String phone;

    /**
     * 密码
     */
    @Property(nameInDb = "passWord")
    private String passWord;

    /**
     * 用户类型
     */
    @Property(nameInDb = "type")
    private String type;

    /**
     * 插入时间
     */
    @Property(nameInDb = "time")
    private String time;

    @Generated(hash = 985332811)
    public UserEntity(Long id, String name, String phone, String passWord,
            String type, String time) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.passWord = passWord;
        this.type = type;
        this.time = time;
    }

    @Generated(hash = 1433178141)
    public UserEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassWord() {
        return this.passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
