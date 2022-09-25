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

package com.bonus.lotteryFrame.fragment.other;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.bonus.lotteryFrame.MyApp;
import com.bonus.lotteryFrame.R;
import com.bonus.lotteryFrame.activity.MainActivity;
import com.bonus.lotteryFrame.core.BaseFragment;
import com.bonus.lotteryFrame.databinding.FragmentLoginBinding;
import com.bonus.lotteryFrame.entity.UserEntity;
import com.bonus.lotteryFrame.sqlite.CommonDaoUtils;
import com.bonus.lotteryFrame.sqlite.dao.UserEntityDao;
import com.bonus.lotteryFrame.utils.MMKVUtils;
import com.bonus.lotteryFrame.utils.RandomUtils;
import com.bonus.lotteryFrame.utils.SharedPreferencesUtils;
import com.bonus.lotteryFrame.utils.StringHelper;
import com.bonus.lotteryFrame.utils.TokenUtils;
import com.bonus.lotteryFrame.utils.XToastUtils;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.utils.ViewUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xutil.app.ActivityUtils;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.HashMap;
import java.util.List;


/**
 * 登录页面
 *
 * @author xuexiang
 * @since 2019-11-17 22:15
 */
@Page(anim = CoreAnim.none)
public class LoginFragment extends BaseFragment<FragmentLoginBinding> implements View.OnClickListener {
    private CommonDaoUtils userDao = MyApp.getMySqLite(UserEntity.class);

    public static LoginFragment loginFragment;


    @NonNull
    @Override
    protected FragmentLoginBinding viewBindingInflate(LayoutInflater inflater, ViewGroup container) {
        return FragmentLoginBinding.inflate(inflater, container, false);
    }

    @Override
    protected TitleBar initTitle() {
        return null;
    }

    @Override
    protected void initViews() {
        SharedPreferencesUtils helper = new SharedPreferencesUtils(getActivity(), "name");
        loginFragment = this;
        binding.etPhoneNumber.setText(MMKVUtils.getString("phone", ""));
        if (MMKVUtils.containsKey("rememberPassword")) {
            binding.rememberPassword.setChecked(true);
            binding.etPassWord.setText(MMKVUtils.getString("passWord", ""));
        }

    }

    public void getBundle(String phone, String passWord) {
        binding.etPhoneNumber.setText(phone);
        binding.etPassWord.setText(passWord);
    }

    @Override
    protected void initListeners() {
        binding.btnLogin.setOnClickListener(this);
        binding.rememberPassword.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            //refreshButton(isChecked);
        }));
        binding.tvForgetPassword.setOnClickListener(this);
        binding.cbProtocol.setChecked(true);
        binding.cbProtocol.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            refreshButton(isChecked);
        }));
    }

    private void refreshButton(boolean isChecked) {
        ViewUtils.setEnabled(binding.btnLogin, isChecked);
        ViewUtils.setEnabled(binding.tvForgetPassword, isChecked);
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_login) {
            loginByVerifyCode(binding.etPhoneNumber.getEditValue(), binding.etPassWord.getEditValue());
        } else if (id == R.id.tv_forget_password) {
            openPage("注册");
        }

    }


    /**
     * 根据验证码登录
     *
     * @param phone    手机号
     * @param passWord 密码
     */
    private void loginByVerifyCode(String phone, String passWord) {
        if (StringHelper.isEmpty(phone)) {
            XToastUtils.warning("请输入手机号！！！");
        } else if (StringHelper.isEmpty(passWord)) {
            XToastUtils.warning("请输入密码！！！");
        } else {
            List list = userDao.queryByQueryBuilder(UserEntityDao.Properties.Phone.eq(phone), UserEntityDao.Properties.PassWord.eq(passWord));
            if (list.size() == 0) {
                XToastUtils.error("手机号或或密码错误");
            } else {
                SharedPreferencesUtils helper = new SharedPreferencesUtils(getActivity(), "name");
                HashMap<String, Object> map = new HashMap<>();
                map.put("rememberPassword", binding.rememberPassword.isChecked());
                map.put("phone", phone);
                map.put("passWord", passWord);
                MMKVUtils.put(map);
                onLoginSuccess();
            }
        }


    }

    /**
     * 登录成功的处理
     */
    private void onLoginSuccess() {
        popToBack();
        ActivityUtils.startActivity(MainActivity.class);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}

