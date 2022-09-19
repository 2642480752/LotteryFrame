package com.bonus.lotteryFrame.sqlite;

import org.greenrobot.greendao.AbstractDao;
/**
 * @author 菜旗
 * @date 2022/4/4 23:39
 * @description 用于存放及提取DaoUtils
 */
public class DaoUtilsStore<T> {

    private final CommonDaoUtils daoUtil;

    public DaoUtilsStore(Class<T> pEntityClass) {
        DaoManager mManager = DaoManager.getInstance();
        AbstractDao dao = mManager.getDaoSession().getDao(pEntityClass);
        daoUtil = new CommonDaoUtils(pEntityClass, dao);
    }

    public CommonDaoUtils getDaoUtils() {
        return daoUtil;
    }

}
