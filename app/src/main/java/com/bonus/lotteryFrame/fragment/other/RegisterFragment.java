
package com.bonus.lotteryFrame.fragment.other;


import androidx.annotation.NonNull;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bonus.lotteryFrame.MyApp;
import com.bonus.lotteryFrame.R;
import com.bonus.lotteryFrame.activity.MainActivity;
import com.bonus.lotteryFrame.core.BaseFragment;
import com.bonus.lotteryFrame.databinding.FragmentLoginBinding;
import com.bonus.lotteryFrame.databinding.FragmentRegisterBinding;
import com.bonus.lotteryFrame.entity.UserEntity;
import com.bonus.lotteryFrame.sqlite.CommonDaoUtils;
import com.bonus.lotteryFrame.sqlite.dao.UserEntityDao;
import com.bonus.lotteryFrame.utils.DateHelper;
import com.bonus.lotteryFrame.utils.RandomUtils;
import com.bonus.lotteryFrame.utils.StringHelper;
import com.bonus.lotteryFrame.utils.TokenUtils;
import com.bonus.lotteryFrame.utils.XToastUtils;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xutil.app.ActivityUtils;

import java.util.List;

@Page(name = "注册", anim = CoreAnim.none)
public class RegisterFragment extends BaseFragment<FragmentRegisterBinding> implements View.OnClickListener {

    private CommonDaoUtils userDao = MyApp.getMySqLite(UserEntity.class);

    @NonNull
    @Override
    protected FragmentRegisterBinding viewBindingInflate(LayoutInflater inflater, ViewGroup container) {
        return FragmentRegisterBinding.inflate(inflater, container, false);
    }

    @Override
    protected TitleBar initTitle() {
        return null;
    }

    @Override
    protected void initViews() {
        binding.btnLogin.setOnClickListener(this);
    }

    @Override
    protected void initListeners() {
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_login) {
            String phone = binding.etPhoneNumber.getText().toString().trim();
            String passWord = binding.etPassword.getText().toString().trim();
            String passWordAgain = binding.etPasswordAgain.getText().toString().trim();
            if (StringHelper.isEmpty(phone)) {
                XToastUtils.warning("请输入电话号码！！！");
            } else if (StringHelper.isEmpty(passWord)) {
                XToastUtils.warning("请输入密码！！！");
            } else if (StringHelper.isEmpty(passWordAgain)) {
                XToastUtils.warning("请输确认密码！！！");
            } else if (!passWord.equals(passWordAgain)) {
                XToastUtils.warning("密码不一样！！！");
            } else {
                boolean insert = true;
                UserEntity userEntity = new UserEntity();
                userEntity.setPhone(phone);
                userEntity.setPassWord(passWord);
                userEntity.setTime(DateHelper.getLongDateStr());
                List<UserEntity> list = userDao.queryByQueryBuilder(UserEntityDao.Properties.Phone.eq(phone));
                if (list.size() == 0) {
                    insert = userDao.insert(userEntity);
                } else {
                    userEntity = list.get(0);
                    userEntity.setPassWord(passWord);
                    insert = userDao.update(userEntity);
                }
                if (insert) {
                    XToastUtils.success("注册成功！！！");
                    Bundle bundle = new Bundle();
                    bundle.putString("phone", phone);
                    bundle.putString("passWord", passWord);
                    popToBack("登录", bundle);
                    LoginFragment.loginFragment.getBundle(phone, passWord);
                }
            }
        }
    }
}

