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

package com.bonus.lotteryFrame.fragment.news.components;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bonus.lotteryFrame.core.BaseFragment;
import com.bonus.lotteryFrame.databinding.FragmentHydropowerBinding;
import com.bonus.lotteryFrame.databinding.FragmentUserBinding;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;

import org.jetbrains.annotations.NotNull;

/**
 * @author 菜旗
 * @date 2022/9/24 19:42
 * @description: 水电缴费
 */
@Page(name = "用户管理", anim = CoreAnim.none)
public class UserFragment extends BaseFragment<FragmentUserBinding> {

    @NotNull
    @Override
    protected FragmentUserBinding viewBindingInflate(LayoutInflater inflater, ViewGroup container) {
        return FragmentUserBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initViews() {

    }
}
