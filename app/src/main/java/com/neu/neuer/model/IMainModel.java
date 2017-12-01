package com.neu.neuer.model;

/**
 * Created by fengyuluo on 2017/11/27.
 */

public interface IMainModel {
    interface NetListener{
        void popNetState(int flag);
    }
    /**
     * 获取网络状态
     * @return flag
     * 0 未连接网络
     * 1 未连接neu
     * 2 已连接neu
     */
    public void getNetState(final NetListener netListener);
}
