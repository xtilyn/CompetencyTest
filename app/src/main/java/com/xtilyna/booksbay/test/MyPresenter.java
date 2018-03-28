package com.xtilyna.booksbay.test;


import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MyPresenter {

    private static final String TAG = "MyPresenter";
    private EventBus eventBus;
    private MyView view;
    private Fragment2 fragment2;

    public MyPresenter(MyView view, Fragment2 fragment2) {
        this.view = view;
        this.fragment2 = fragment2;
        this.eventBus = EventBus.getDefault();
    }

    public void onStart() {
        eventBus.register(this);
    }

    public void onStop() {
        eventBus.unregister(this);
    }

    @Subscribe
    public void onEventMainThread(Event event) {
        switch (event.getEventType()) {
            case Event.ON_MYBUTTON_CLICK:
                Log.d(TAG, "onEventMainThread: ON_MYBUTTON_CLICK");
                view.goToNextPage();
                break;
            case Event.ON_FAB_CLICK:
                Log.d(TAG, "onEventMainThread: ON_FAB_CLICK");
                view.onFabClick();
                break;
            case Event.ON_DATA_FETCHED:
                Log.d(TAG, "onEventMainThread: ON_DATA_FETCHED");
                fragment2.setData(event.getMessageMap(), event.getPhotos());
        }
    }
}
