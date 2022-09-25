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

package com.bonus.lotteryFrame.fragment.profile;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.bonus.lotteryFrame.R;
import com.bonus.lotteryFrame.core.BaseFragment;
import com.bonus.lotteryFrame.databinding.FragmentProfileBinding;
import com.bonus.lotteryFrame.utils.TokenUtils;
import com.bonus.lotteryFrame.utils.XToastUtils;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.dialog.DialogLoader;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

/**
 * @author xuexiang
 * @since 2019-10-30 00:18
 */
@Page(anim = CoreAnim.none)
public class ProfileFragment extends BaseFragment<FragmentProfileBinding> implements SuperTextView.OnSuperTextViewClickListener {


    @NonNull
    @Override
    protected FragmentProfileBinding viewBindingInflate(LayoutInflater inflater, ViewGroup container) {
        return FragmentProfileBinding.inflate(inflater, container, false);
    }

    @Override
    protected TitleBar initTitle() {
        return null;
    }

    @Override
    protected void initViews() {
        binding.menuCommon.setOnSuperTextViewClickListener(this);
        binding.menuPrivacy.setOnSuperTextViewClickListener(this);
        binding.menuPush.setOnSuperTextViewClickListener(this);
        binding.menuHelper.setOnSuperTextViewClickListener(this);
        binding.menuChangeAccount.setOnSuperTextViewClickListener(this);
        binding.menuLogout.setOnSuperTextViewClickListener(this);
    }

    @SingleClick
    @Override
    public void onClick(SuperTextView superTextView) {
        int id = superTextView.getId();
        if (id == R.id.menu_common || id == R.id.menu_privacy || id == R.id.menu_push || id == R.id.menu_helper) {
            XToastUtils.toast(superTextView.getLeftString());
        } else if (id == R.id.menu_change_account) {
            XToastUtils.toast(superTextView.getCenterString());
        } else if (id == R.id.menu_logout) {
            DialogLoader.getInstance().showConfirmDialog(
                    getActivity(),
                    getString(R.string.lab_logout_confirm),
                    getString(R.string.lab_yes),
                    (dialog, which) -> {
                        dialog.dismiss();
                        TokenUtils.handleLogoutSuccess();
                        popToBack();
                    },
                    getString(R.string.lab_no),
                    (dialog, which) -> dialog.dismiss()
            );
        }
    }

}
