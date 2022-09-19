package com.bonus.lotteryFrame.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;

/**
 * @author 菜旗
 * @date 2022/4/4 20:33
 * @description 账单记录实体类
 */
@Entity(nameInDb = "tb_user")
public class UserEntity{
    /**
     * 唯一主键
     */
    @Property(nameInDb = "id")
    @Id(autoincrement = true)
    private Long id;

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
     * 插入时间
     */
    @Property(nameInDb = "time")
    private String time;

    @Generated(hash = 1798757231)
    public UserEntity(Long id, String phone, String passWord, String time) {
        this.id = id;
        this.phone = phone;
        this.passWord = passWord;
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



   
   

   


}
