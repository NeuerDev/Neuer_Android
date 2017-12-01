package com.neu.neuer.presenter;

import com.neu.neuer.model.IMainModel;
import com.neu.neuer.model.MainModel;
import com.neu.neuer.util.LogUtil;
import com.neu.neuer.view.IMainView;

/**
 * Created by fengyuluo on 2017/11/27.
 */

public class MainPresenterCompl implements IMainModel.NetListener {
    IMainView iMainView;
    IMainModel iMainModel;

    public MainPresenterCompl(IMainView iMainView){
        this.iMainView = iMainView;
        iMainModel = new MainModel();
    }
    /**
     * 获取网络状态以及操作view
     */
    public void getNetState(){
        LogUtil.d("666","getNetState");
        iMainModel.getNetState(this);
    }

    @Override
    public void popNetState(int flag) {
        switch (flag){
            case 0:
                iMainView.popNetState("没有网络");
                break;
            case 1:
                iMainView.popNetState("没有连接neu");
                break;
            case 2:
                iMainView.popNetState("已连接neu");
                break;
            default:
                break;
        }
    }
}
