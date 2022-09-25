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

package com.bonus.lotteryFrame.fragment.news;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.bonus.lotteryFrame.R;
import com.bonus.lotteryFrame.adapter.WidgetItemAdapter;
import com.bonus.lotteryFrame.core.BaseFragment;
import com.bonus.lotteryFrame.databinding.FragmentNewsBinding;
import com.bonus.lotteryFrame.fragment.other.LoginFragment;
import com.bonus.lotteryFrame.utils.AppPageConfig;
import com.bonus.lotteryFrame.utils.AppPageInfo;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xpage.model.PageInfo;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.utils.DensityUtils;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.utils.ThemeUtils;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 首页动态
 *
 * @author xuexiang
 * @since 2019-10-30 00:15
 */
@Page(anim = CoreAnim.none)
public class NewsFragment extends BaseFragment<FragmentNewsBinding> implements RecyclerViewHolder.OnItemClickListener<AppPageInfo> {
    @NonNull
    @Override
    protected FragmentNewsBinding viewBindingInflate(LayoutInflater inflater, ViewGroup container) {
        return FragmentNewsBinding.inflate(inflater, container, false);

    }

    /**
     * @return 返回为 null意为不需要导航栏
     */
    @Override
    protected TitleBar initTitle() {
        return null;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
        WidgetUtils.initGridRecyclerView(binding.recyclerView, 3, DensityUtils.dp2px(2), ThemeUtils.resolveColor(getContext(), R.attr.xui_config_color_separator_light, ResUtils.getColor(R.color.xui_config_color_separator_light_phone)));
        WidgetItemAdapter mWidgetItemAdapter = new WidgetItemAdapter(AppPageConfig.getInstance().getComponents());
        mWidgetItemAdapter.setOnItemClickListener(this);
        binding.recyclerView.setAdapter(mWidgetItemAdapter);
    }

    @Override
    public void onItemClick(View itemView, AppPageInfo item, int position) {
        if (item != null) {
            openNewPage(item.getName());
        }
    }
}
