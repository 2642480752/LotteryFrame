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

package com.bonus.lotteryFrame.utils;

import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xpage.utils.GsonUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 菜旗
 * @date 2022/9/24 19:54
 * @description: 页面信息
 */

public class AppPageInfo implements Serializable {

    /**
     * 页面名
     */
    private String name;
    /**
     * 页面class
     */
    private String classPath;
    /**
     * 页面传递的参数
     */
    private String params = "";

    /**
     * 页面跳转的动画
     */
    private CoreAnim anim;

    /**
     * 拓展字段
     */
    private int extra;

    public AppPageInfo() {

    }

    public AppPageInfo(Class<?> clazz) {
        this(clazz.getSimpleName(), clazz.getName());
    }

    public AppPageInfo(String name, String classPath) {
        this.name = name;
        this.classPath = classPath;
    }


    public AppPageInfo(String name, Class<?> clazz) {
        this.name = name;
        this.classPath = clazz.getName();
    }

    public AppPageInfo(String name, String classPath, String params) {
        this.name = name;
        this.classPath = classPath;
        this.params = params;
    }

    public AppPageInfo(String name, Class<?> clazz, Map<String, Object> params) {
        this.name = name;
        this.classPath = clazz.getName();
        this.params = GsonUtils.toJson(params);
    }

    /**
     * 自动编译生成需要使用的构造函数
     *
     * @param name      页面名
     * @param classPath 页面class
     * @param params    页面传递的参数
     * @param anim      页面跳转的动画
     */
    public AppPageInfo(String name, String classPath, String params, CoreAnim anim, int extra) {
        this.name = name;
        this.classPath = classPath;
        this.params = params;
        this.anim = anim;
        this.extra = extra;
    }

    /**
     * 自动编译生成需要使用的构造函数
     *
     * @param name   页面名
     * @param params 页面传递的参数
     * @param anim   页面跳转的动画
     */
    public AppPageInfo(String name, String params, CoreAnim anim, int extra) {
        this.name = name;
        this.params = params;
        this.anim = anim;
        this.extra = extra;
    }

    public static String getParams(String[] params) {
        if (params != null && params.length > 0) {
            Map<String, Object> paramMaps = new HashMap<>();
            for (String param : params) {
                paramMaps.put(param, "");
            }
            return GsonUtils.toJson(paramMaps);
        }
        return "";
    }

    public String getName() {
        return name;
    }

    public AppPageInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getClassPath() {
        return classPath;
    }

    public AppPageInfo setClassPath(String classPath) {
        this.classPath = classPath;
        return this;
    }

    public AppPageInfo setClassPath(Class<?> clazz) {
        classPath = clazz.getName();
        return this;
    }

    public String getParams() {
        return params;
    }

    public AppPageInfo setParams(String params) {
        this.params = params;
        return this;
    }

    public AppPageInfo setParams(Map<String, Object> params) {
        this.params = GsonUtils.toJson(params);
        return this;
    }

    public AppPageInfo setParams(String[] params) {
        if (params != null && params.length > 0) {
            Map<String, Object> paramMaps = new HashMap<>();
            for (String param : params) {
                paramMaps.put(param, "");
            }
            setParams(paramMaps);
        }
        return this;
    }

    public CoreAnim getAnim() {
        return anim;
    }

    public AppPageInfo setAnim(CoreAnim anim) {
        this.anim = anim;
        return this;
    }

    public int getExtra() {
        return extra;
    }

    public AppPageInfo setExtra(int extra) {
        this.extra = extra;
        return this;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "name='" + name + '\'' +
                ", classPath='" + classPath + '\'' +
                ", params='" + params + '\'' +
                ", anim=" + anim +
                ", extra=" + extra +
                '}';
    }
}
