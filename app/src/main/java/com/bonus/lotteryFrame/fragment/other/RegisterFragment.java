
package com.bonus.lotteryFrame.fragment.other;


import androidx.annotation.NonNull;

import android.content.DialogInterface;
import android.graphics.Color;
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
import com.huantansheng.easyphotos.models.puzzle.template.straight.StraightLayoutHelper;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.utils.ThemeUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.button.SmoothCheckBox;
import com.xuexiang.xui.widget.dialog.DialogLoader;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheet;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheetItemView;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.dialog.strategy.impl.MaterialDialogStrategy;
import com.xuexiang.xui.widget.toast.XToast;
import com.xuexiang.xutil.app.ActivityUtils;
import com.xuexiang.xutil.tip.ToastUtils;

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
        TitleBar titleBar = super.initTitle()
                .setImmersive(true);
        titleBar.setBackgroundColor(Color.TRANSPARENT);
        titleBar.setTitle("");
        titleBar.setLeftImageDrawable(ResUtils.getVectorDrawable(getContext(), R.drawable.ic_login_close));
        titleBar.setActionTextColor(ThemeUtils.resolveColor(getContext(), R.attr.colorAccent));
        return titleBar;
    }

    @Override
    protected void initViews() {
        binding.btnLogin.setOnClickListener(this);
    }

    @Override
    protected void initListeners() {
        binding.user.setChecked(true, true);
        binding.admin.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                if (isChecked) {
                    binding.user.setChecked(false, true);
                }
            }
        });
        binding.user.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                if (isChecked) {
                    binding.admin.setChecked(false, true);
                }
            }
        });
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_login) {
            String name = binding.etName.getText().toString().trim();
            String phone = binding.etPhoneNumber.getText().toString().trim();
            String passWord = binding.etPassword.getText().toString().trim();
            String passWordAgain = binding.etPasswordAgain.getText().toString().trim();
            if (StringHelper.isEmpty(name)) {
                XToastUtils.warning("请输入用户名称！！！");
            } else if (StringHelper.isEmpty(phone)) {
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
                userEntity.setName(name);
                userEntity.setPhone(phone);
                userEntity.setPassWord(passWord);
                userEntity.setType(binding.admin.isChecked() ? "1" : "0");
                userEntity.setTime(DateHelper.getLongDateStr());
                List<UserEntity> list = userDao.queryByQueryBuilder(UserEntityDao.Properties.Phone.eq(phone));
                if (list.size() == 0) {
                    insert = userDao.insert(userEntity);
                } else {
                    userEntity = list.get(0);
                    userEntity.setName(name);
                    userEntity.setPassWord(passWord);
                    userEntity.setType(binding.admin.isChecked() ? "1" : "0");
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
        } else if (id == R.id.admin) {
            binding.admin.setChecked(true);
            binding.user.setCheckedSilent(binding.admin.isChecked());
        } else if (id == R.id.user) {
            binding.admin.setCheckedSilent(binding.user.isChecked());
        }
    }
}

