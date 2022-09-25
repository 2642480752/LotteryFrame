package com.bonus.lotteryFrame.adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.bonus.lotteryFrame.MyApp;
import com.bonus.lotteryFrame.R;
import com.bonus.lotteryFrame.entity.CostEntity;
import com.bonus.lotteryFrame.sqlite.CommonDaoUtils;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.widget.dialog.DialogLoader;

import java.util.List;

/**
 * @author XUE
 * @date 2017/9/10 15:28
 */
public class CostItemAdapter extends BaseRecyclerAdapter<CostEntity> {
    private CommonDaoUtils costDao = MyApp.getMySqLite(CostEntity.class);
    private Context context;

    public CostItemAdapter(List<CostEntity> list) {
        super(list);
    }

    public CostItemAdapter CostItemAdapter(Context context) {
        this.context = context;
        return this;
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.layout_cost_item;
    }

    @Override
    public void bindData(@NonNull RecyclerViewHolder holder, int position, CostEntity item) {
        holder.text(R.id.item_type, (item.getType().equals("1") ? "水费" : "电费") + "(" + item.getNumber() + ")");
        holder.image(R.id.item_icon, item.getType().equals("1") ? R.drawable.ic_water : R.drawable.ic_electricity);
        holder.text(R.id.item_name, item.getName() + " | " + item.getAddress());
        holder.click(R.id.item_delete, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogLoader.getInstance().showConfirmDialog(
                        context,
                        "是否删除",
                        "是",
                        (dialog, which) -> {
                            dialog.dismiss();
                            costDao.delete(item);
                            refresh(costDao.queryAll());
                        },
                        "否",
                        (dialog, which) -> dialog.dismiss()
                );
            }
        });

    }

}
