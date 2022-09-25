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
 * @date 2022/9/24 22:50
 * @description: 缴费实体类
 */
@Entity(nameInDb = "tb_cost")
public class CostEntity {
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
     * 住户名称
     */
    @Property(nameInDb = "name")
    private String name;

    /**
     * 地址
     */
    @Property(nameInDb = "address")
    private String address;

    /**
     * 缴费类型
     */
    @Property(nameInDb = "type")
    private String type;
    /**
     * 插入时间
     */
    @Property(nameInDb = "time")
    private String time;
    @Generated(hash = 845030160)
    public CostEntity(Long id, String number, String name, String address,
            String type, String time) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.address = address;
        this.type = type;
        this.time = time;
    }
    @Generated(hash = 1244719688)
    public CostEntity() {
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
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    

}
