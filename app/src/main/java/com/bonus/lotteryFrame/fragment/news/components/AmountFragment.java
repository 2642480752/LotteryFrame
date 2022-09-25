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

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.bonus.lotteryFrame.MyApp;
import com.bonus.lotteryFrame.R;
import com.bonus.lotteryFrame.adapter.CostRecordItemRecordAdapter;
import com.bonus.lotteryFrame.core.BaseFragment;
import com.bonus.lotteryFrame.databinding.FragmentAmountBinding;
import com.bonus.lotteryFrame.entity.CostRecordEntity;
import com.bonus.lotteryFrame.entity.HouseholdEntity;
import com.bonus.lotteryFrame.sqlite.CommonDaoUtils;
import com.bonus.lotteryFrame.sqlite.dao.CostRecordEntityDao;
import com.bonus.lotteryFrame.sqlite.dao.HouseholdEntityDao;
import com.bonus.lotteryFrame.utils.DateHelper;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.dialog.DialogLoader;
import com.xuexiang.xui.widget.dialog.strategy.InputCallback;
import com.xuexiang.xui.widget.dialog.strategy.InputInfo;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author 菜旗
 * @date 2022/9/24 19:42
 * @description: 缴费账单
 */
@Page(name = "缴费账单", anim = CoreAnim.none)
public class AmountFragment extends BaseFragment<FragmentAmountBinding> implements RecyclerViewHolder.OnItemClickListener<CostRecordEntity> {

    private CommonDaoUtils householdDao = MyApp.getMySqLite(HouseholdEntity.class);

    private CommonDaoUtils costRecordDao = MyApp.getMySqLite(CostRecordEntity.class);

    private String number;
    private String type;
    private List<HouseholdEntity> householdList;
    CostRecordItemRecordAdapter mWidgetItemAdapter;

    @NotNull
    @Override
    protected FragmentAmountBinding viewBindingInflate(LayoutInflater inflater, ViewGroup container) {
        return FragmentAmountBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initViews() {
        Bundle bundle = getArguments();
        number = bundle.getString("number");
        type = bundle.getString("type");
        initData();
        WidgetUtils.initRecyclerView(binding.recyclerView, 0);
        mWidgetItemAdapter = new CostRecordItemRecordAdapter(costRecordDao.queryByQueryBuilder(CostRecordEntityDao.Properties.Number.eq(number), CostRecordEntityDao.Properties.Type.eq(type)));
        mWidgetItemAdapter.setOnItemClickListener(this);
        binding.recyclerView.setAdapter(mWidgetItemAdapter);
    }

    /**
     * 数据加载
     */
    protected void initData() {
        householdList = householdDao.queryByQueryBuilder(HouseholdEntityDao.Properties.Number.eq(number));
        if (householdList.size() > 0) {
            String amount = type.equals("1") ? householdList.get(0).getWater() : householdList.get(0).getElectricity();
            binding.itemAmount.setText(amount + "元");
            binding.itemName.setText(householdList.get(0).getNumber() + " | " + householdList.get(0).getName());
            binding.itemAddress.setText(householdList.get(0).getAddress());
        }
    }

    @Override
    protected TitleBar initTitle() {
        TitleBar titleBar = super.initTitle();
        titleBar.addAction(new TitleBar.TextAction("缴费") {
            @Override
            public void performAction(View view) {
                showInputDialog();
            }
        });
        return titleBar;
    }

    //显示带输入框的对话框
    private void showInputDialog() {
        //对用户需要输入的内容做一些限制
        InputInfo inputInfo = new InputInfo(InputType.TYPE_CLASS_NUMBER);
        inputInfo.setHint("请输入金额");
        //或者继承CustomMaterialDialog实现
        DialogLoader.getInstance().showInputDialog(getActivity()
                , R.drawable.ic_amount,
                "金额", "请输入金额", inputInfo,
                new InputCallback() {
                    @Override
                    public void onInput(@NonNull DialogInterface dialog, CharSequence input) {
                        CostRecordEntity entity = new CostRecordEntity();
                        entity.setNumber(number);
                        entity.setType(type);
                        entity.setAmount(input.toString());
                        entity.setTime(DateHelper.getLongDateStr());
                        HouseholdEntity householdEntity = householdList.get(0);
                        int balance = 0;
                        int amount = Integer.parseInt(input.toString());
                        if (type.equals("1")) {
                            balance = Integer.parseInt(householdEntity.getWater());
                            householdEntity.setWater(String.valueOf(balance + amount));
                            binding.itemAmount.setText(String.valueOf(balance + amount) + "元");
                        } else {
                            balance = Integer.parseInt(householdEntity.getElectricity());
                            householdEntity.setElectricity(String.valueOf(balance + amount));
                            binding.itemAmount.setText(String.valueOf(balance + amount) + "元");
                        }
                        householdDao.update(householdEntity);
                        costRecordDao.insert(entity);
                        mWidgetItemAdapter.refresh(costRecordDao.queryByQueryBuilder(CostRecordEntityDao.Properties.Number.eq(number), CostRecordEntityDao.Properties.Type.eq(type)));
                    }
                }, "确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
    }


    @Override
    public void onItemClick(View itemView, CostRecordEntity item, int position) {

    }
}
