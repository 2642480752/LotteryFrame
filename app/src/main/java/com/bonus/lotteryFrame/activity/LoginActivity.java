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

package com.bonus.lotteryFrame.activity;

import android.os.Bundle;
import android.view.KeyEvent;

import com.bonus.lotteryFrame.core.BaseActivity;
import com.bonus.lotteryFrame.fragment.other.LoginFragment;
import com.bonus.lotteryFrame.utils.XToastUtils;
import com.xuexiang.xui.utils.KeyboardUtils;
import com.xuexiang.xui.utils.StatusBarUtils;
import com.xuexiang.xutil.common.ClickUtils;
import com.xuexiang.xutil.display.Colors;

/**
 * 登录页面
 *
 * @author xuexiang
 * @since 2019-11-17 22:21
 */
public class LoginActivity extends BaseActivity implements ClickUtils.OnClick2ExitListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openPage(LoginFragment.class, getIntent().getExtras());
    }

    @Override
    protected void initStatusBarStyle() {
        StatusBarUtils.initStatusBarStyle(this, false, Colors.TRANSPARENT);
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ClickUtils.exitBy2Click(2000, this);
        }
        return true;
    }

    @Override
    public void onRetry() {
        XToastUtils.toast("再按一次退出程序");
    }

    /**
     * 退出
     */
    @Override
    public void onExit() {
        moveTaskToBack(true);
    }

}
