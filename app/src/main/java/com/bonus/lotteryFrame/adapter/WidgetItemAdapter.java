package com.bonus.lotteryFrame.adapter;

import androidx.annotation.NonNull;

import com.bonus.lotteryFrame.R;
import com.bonus.lotteryFrame.utils.AppPageInfo;
import com.xuexiang.xpage.model.PageInfo;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;

import java.util.List;

/**
 * @author XUE
 * @date 2017/9/10 15:28
 */
public class WidgetItemAdapter extends BaseRecyclerAdapter<AppPageInfo> {

    public WidgetItemAdapter(List<AppPageInfo> list) {
        super(list);
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.layout_widget_item;
    }

    @Override
    public void bindData(@NonNull RecyclerViewHolder holder, int position, AppPageInfo item) {
        holder.text(R.id.item_name, item.getName());
        if (item.getExtra() != 0) {
            holder.image(R.id.item_icon, item.getExtra());
        }
    }

}
