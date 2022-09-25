package com.bonus.lotteryFrame.adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.bonus.lotteryFrame.MyApp;
import com.bonus.lotteryFrame.R;
import com.bonus.lotteryFrame.entity.CostEntity;
import com.bonus.lotteryFrame.entity.CostRecordEntity;
import com.bonus.lotteryFrame.sqlite.CommonDaoUtils;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.widget.dialog.DialogLoader;

import java.util.List;

/**
 * @author XUE
 * @date 2017/9/10 15:28
 */
public class CostRecordItemRecordAdapter extends BaseRecyclerAdapter<CostRecordEntity> {

    public CostRecordItemRecordAdapter(List<CostRecordEntity> list) {
        super(list);
    }


    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.layout_cost_recor_item;
    }

    @Override
    public void bindData(@NonNull RecyclerViewHolder holder, int position, CostRecordEntity item) {
        holder.text(R.id.item_time, item.getTime());
        holder.text(R.id.item_amount, item.getAmount() + "元");
    }

}
