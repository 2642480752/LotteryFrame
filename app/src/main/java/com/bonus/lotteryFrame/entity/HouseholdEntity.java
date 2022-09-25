/*
 * Copyright (C) 2022 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.bonus.lotteryFrame.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author 菜旗
 * @date 2022/9/24 22:47
 * @description: 住户实体类
 */
@Entity(nameInDb = "tb_household")
public class HouseholdEntity {
    /**
     * 唯一主键
     */
    @Property(nameInDb = "id")
    @Id(autoincrement = true)
    private Long id;
    /**
     * 户号
     */
    @Property(nameInDb = "number")
    private String number;
    /**
     * 电话
     */
    @Property(nameInDb = "phone")
    private String phone;

    /**
     * 住户名称
     */
    @Property(nameInDb = "name")
    private String name;
    /**
     * 水费金额
     */
    @Property(nameInDb = "water")
    private String water;

    /**
     * 电费金额
     */
    @Property(nameInDb = "electricity")
    private String electricity;
    /**
     * 地址
     */
    @Property(nameInDb = "address")
    private String address;
    /**
     * 插入时间
     */
    @Property(nameInDb = "time")
    private String time;
    @Generated(hash = 596759457)
    public HouseholdEntity(Long id, String number, String phone, String name,
            String water, String electricity, String address, String time) {
        this.id = id;
        this.number = number;
        this.phone = phone;
        this.name = name;
        this.water = water;
        this.electricity = electricity;
        this.address = address;
        this.time = time;
    }
    @Generated(hash = 2017591052)
    public HouseholdEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNumber() {
        return this.number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getWater() {
        return this.water;
    }
    public void setWater(String water) {
        this.water = water;
    }
    public String getElectricity() {
        return this.electricity;
    }
    public void setElectricity(String electricity) {
        this.electricity = electricity;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }

}
