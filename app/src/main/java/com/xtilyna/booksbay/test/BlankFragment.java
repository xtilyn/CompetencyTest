package com.xtilyna.booksbay.test;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    private static final String TAG = "BlankFrament";

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        ButterKnife.bind(this, view);
        view.findViewById(R.id.my_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postEvent(Event.ON_MYBUTTON_CLICK);
            }
        });
        return view;
    }

//    @OnClick(R.id.my_button)
//    public void onMyButtonClick(Button button) {
//        Log.d(TAG, "onMyButtonClick: posting event...");
//        postEvent(Event.ON_MYBUTTON_CLICK);
//    }

    private void postEvent(int type) {
        Event event = new Event();
        event.setEventType(type);

        EventBus eventBus = EventBus.getDefault();
        eventBus.post(event);
    }

}
