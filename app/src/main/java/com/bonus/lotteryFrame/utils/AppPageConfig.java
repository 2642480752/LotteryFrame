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

import com.bonus.lotteryFrame.R;
import com.bonus.lotteryFrame.fragment.news.components.HydropowerFragment;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xpage.model.PageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 菜旗
 * @date 2022/9/24 19:33
 * @description: 页面配置
 */
public class AppPageConfig {
    private static AppPageConfig sInstance;

    private List<AppPageInfo> mComponents;

    private AppPageConfig() {
        mComponents = new ArrayList<>();
        mComponents.add(new AppPageInfo("水电缴费", "{\"\":\"\"}", CoreAnim.none, R.drawable.ic_hydropower));
        mComponents.add(new AppPageInfo("用户管理", "{\"\":\"\"}", CoreAnim.none, R.drawable.ic_user));
    }

    public static AppPageConfig getInstance() {
        if (sInstance == null) {
            synchronized (AppPageConfig.class) {
                if (sInstance == null) {
                    sInstance = new AppPageConfig();
                }
            }
        }
        return sInstance;
    }

    public List<AppPageInfo> getComponents() {
        return mComponents;
    }

}
