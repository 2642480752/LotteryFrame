package com.bonus.lotteryFrame.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * 这是一个SharePreference的根据类，使用它可以更方便的数据进行简单存储
 * 这里只要知道基本调用方法就可以了
 * 1.通过构造方法来传入上下文和文件名
 * 2.通过putValue方法传入一个或多个自定义的ContentValue对象，进行数据存储
 * 3.通过get方法来获取数据
 * 4.通过clear方法来清除这个文件的数据
 * 这里没有提供清除单个key的数据，是因为存入相同的数据会自动覆盖，没有必要去理会
 *
 * @author Administrator
 */
public class SharedPreferencesUtils {
    /**
     * 定义一个SharePreference对象
     */
    SharedPreferences sharedPreferences;

    /**
     * 定义一个上下文对象
     * 创建SharePreference对象时要上下文和存储的模式
     * 通过构造方法传入一个上下文
     * @param context
     * @param fileName
     */

    public SharedPreferencesUtils(Context context, String fileName) {
        //实例化SharePreference对象，使用的是get方法，而不是new创建
        //第一个参数是文件的名字
        //第二个参数是存储的模式，一般都是使用私有方式：Context.MODE_PRIVATE
        sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    /**
     * 存储数据
     * 这里要对存储的数据进行判断在存储
     * 只能存储简单的几种数据
     * 这里使用的是自定义的ContentValue类，来进行对多个数据的处理
     * 创建一个内部类使用，里面有key和value这两个值
     */
    public static class ContentValue {
        String key;
        Object value;

        //通过构造方法来传入key和value
        public ContentValue(String key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * 一次可以传入多个ContentValue对象的值
     *
     * @param map
     */
    public void putValues(HashMap<String, Object> map) {
        //获取SharePreference对象的编辑对象，才能进行数据的存储
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //数据分类和存储
        map.forEach((key, value) -> {
            //如果是字符型类型
            if (value instanceof String) {
                editor.putString(key,value.toString()).apply();
            }
            //如果是int类型
            if (value instanceof Integer) {
                editor.putInt(key, Integer.parseInt(value.toString())).commit();
            }
            //如果是Long类型
            if (value instanceof Long) {
                editor.putLong(key, Long.parseLong(value.toString())).commit();
            }
            //如果是布尔类型
            if (value instanceof Boolean) {
                editor.putBoolean(key, Boolean.parseBoolean(value.toString())).commit();
            }
        });
    }


    /**
     * 获取数据的方法
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        return sharedPreferences.getString(key, null);
    }

    public boolean getBoolean(String key, Boolean b) {
        return sharedPreferences.getBoolean(key, b);
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, -1);
    }

    public long getLong(String key) {
        return sharedPreferences.getLong(key, -1);
    }


    /**
     * 清除当前文件的所有的数据
     */
    public void clear() {
        sharedPreferences.edit().clear().apply();
    }

}
