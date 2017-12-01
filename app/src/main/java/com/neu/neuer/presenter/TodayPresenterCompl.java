package com.neu.neuer.presenter;

import com.neu.neuer.model.ITodayModel;
import com.neu.neuer.model.TodayModel;
import com.neu.neuer.view.ITodayView;

/**
 * Created by fengyuluo on 2017/11/13.
 */

public class TodayPresenterCompl implements ITodayPresenter,ITodayModel.TodayListener {
    ITodayView iTodayView;
    ITodayModel iTodayModel;

    public TodayPresenterCompl(ITodayView iTodayView){
        this.iTodayView = iTodayView;
        iTodayModel = new TodayModel();
    }

    @Override
    public void initView() {

    }

    @Override
    public void updatePicture(String[] urls) {

    }

    @Override
    public void updateTimetable(String timetable) {

    }

    @Override
    public void updateNews(String news) {

    }

    @Override
    public void onError() {

    }
}
