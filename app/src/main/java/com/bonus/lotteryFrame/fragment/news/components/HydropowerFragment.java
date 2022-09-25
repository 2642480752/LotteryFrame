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
import com.bonus.lotteryFrame.adapter.CostItemAdapter;
import com.bonus.lotteryFrame.adapter.WidgetItemAdapter;
import com.bonus.lotteryFrame.core.BaseFragment;
import com.bonus.lotteryFrame.databinding.FragmentHydropowerBinding;
import com.bonus.lotteryFrame.databinding.FragmentNewsBinding;
import com.bonus.lotteryFrame.entity.CostEntity;
import com.bonus.lotteryFrame.entity.HouseholdEntity;
import com.bonus.lotteryFrame.entity.UserEntity;
import com.bonus.lotteryFrame.sqlite.CommonDaoUtils;
import com.bonus.lotteryFrame.sqlite.dao.CostEntityDao;
import com.bonus.lotteryFrame.sqlite.dao.HouseholdEntityDao;
import com.bonus.lotteryFrame.sqlite.dao.UserEntityDao;
import com.bonus.lotteryFrame.utils.AppPageConfig;
import com.bonus.lotteryFrame.utils.AppPageInfo;
import com.bonus.lotteryFrame.utils.DateHelper;
import com.bonus.lotteryFrame.utils.MMKVUtils;
import com.bonus.lotteryFrame.utils.SettingUtils;
import com.bonus.lotteryFrame.utils.StringHelper;
import com.bonus.lotteryFrame.utils.XToastUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.utils.DensityUtils;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.utils.ThemeUtils;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.dialog.DialogLoader;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheet;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheetItemView;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.dialog.strategy.InputCallback;
import com.xuexiang.xui.widget.dialog.strategy.InputInfo;
import com.xuexiang.xui.widget.toast.XToast;
import com.xuexiang.xutil.tip.ToastUtils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author 菜旗
 * @date 2022/9/24 19:42
 * @description: 水电缴费
 */
@Page(name = "水电缴费", anim = CoreAnim.none)
public class HydropowerFragment extends BaseFragment<FragmentHydropowerBinding> implements View.OnClickListener, RecyclerViewHolder.OnItemClickListener<CostEntity> {

    private CommonDaoUtils householdDao = MyApp.getMySqLite(HouseholdEntity.class);

    private CommonDaoUtils costDao = MyApp.getMySqLite(CostEntity.class);

    private CostEntity costEntity = new CostEntity();
    CostItemAdapter mWidgetItemAdapter;

    @NotNull
    @Override
    protected FragmentHydropowerBinding viewBindingInflate(LayoutInflater inflater, ViewGroup container) {
        return FragmentHydropowerBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initViews() {
        binding.add.setOnClickListener(this);
        WidgetUtils.initRecyclerView(binding.recyclerView, 0);
        mWidgetItemAdapter = new CostItemAdapter(costDao.queryAll()).CostItemAdapter(getActivity());
        mWidgetItemAdapter.setOnItemClickListener(this);
        binding.recyclerView.setAdapter(mWidgetItemAdapter);

        if (SettingUtils.isFirstOpen()) {
            for (int i = 0; i < 100; i++) {
                HouseholdEntity entity = new HouseholdEntity(null, "000" + i, "15398184589", "admin", "100", "100", "北京", DateHelper.getLongDateStr());
                householdDao.insert(entity);
            }
        }
        SettingUtils.setIsFirstOpen(false);
    }

    //显示带输入框的对话框
    private void showInputDialog(String type, Integer integer) {
        //对用户需要输入的内容做一些限制
        InputInfo inputInfo = new InputInfo(InputType.TYPE_CLASS_NUMBER);
        inputInfo.setHint("请在输入户号");
        //或者继承CustomMaterialDialog实现
        DialogLoader.getInstance().showInputDialog(getActivity()
                , integer == 1 ? R.drawable.ic_water : R.drawable.ic_electricity,
                type, "请在输入户号", inputInfo,
                new InputCallback() {
                    @Override
                    public void onInput(@NonNull DialogInterface dialog, CharSequence input) {
                        List<HouseholdEntity> householdlist = householdDao.queryByQueryBuilder(HouseholdEntityDao.Properties.Number.eq(input.toString()));
                        List costList = costDao.queryByQueryBuilder(CostEntityDao.Properties.Number.eq(input.toString()), CostEntityDao.Properties.Type.eq(integer.toString()));
                        if (householdlist.size() == 0) {
                            XToastUtils.warning("缴费号有误！！！");
                        } else if (costList.size() > 0) {
                            XToastUtils.warning("缴费号已添加！！！");
                        } else {
                            costEntity.setNumber(householdlist.get(0).getNumber());
                            costEntity.setName(householdlist.get(0).getName());
                            costEntity.setType(integer.toString());
                            costEntity.setAddress(householdlist.get(0).getAddress());
                            costEntity.setTime(DateHelper.getLongDateStr());
                            mWidgetItemAdapter.add(costEntity);
                            costDao.insert(costEntity);
                        }
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                BottomSheet.BottomGridSheetBuilder builder = new BottomSheet.BottomGridSheetBuilder(getActivity());
                builder.addItem(R.drawable.ic_water, "水费", 1, BottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                        .addItem(R.drawable.ic_electricity, "电费", 2, BottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                        .setOnSheetItemClickListener(new BottomSheet.BottomGridSheetBuilder.OnSheetItemClickListener() {
                            @Override
                            public void onClick(BottomSheet dialog, BottomSheetItemView itemView) {
                                dialog.dismiss();
                                int tag = (int) itemView.getTag();
                                showInputDialog(itemView.toString(), tag);
                            }
                        }).build().show();
                break;

        }

    }

    @Override
    public void onItemClick(View itemView, CostEntity item, int position) {
        Bundle bundle = new Bundle();
        bundle.putString("number", item.getNumber());
        bundle.putString("type", item.getType());
        openPage("缴费账单", bundle);
    }
}
