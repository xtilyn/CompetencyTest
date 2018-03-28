package com.xtilyna.booksbay.test;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.xtilyna.booksbay.test.Utils.CustomAdapter;
import com.xtilyna.booksbay.test.entities.Photo;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment2 extends Fragment implements Fragment2{

    private final String TAG = "BlankFragment2";

    @BindView(R.id.tv_address)
    TextView address;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.operation_hours)
    TextView tv_market_hours;
    @BindView(R.id.tv_rating)
    TextView tv_rating;
    @BindView(R.id.rec_view)
    RecyclerView recView;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_website)
    TextView tv_website;

    private ArrayList<Photo> recyclerViewData;
    private CustomAdapter adapter;

    public BlankFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
        ButterKnife.bind(this, view);
        view.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postEvent(Event.ON_FAB_CLICK);
            }
        });

        recyclerViewData = new ArrayList<>();
        FetchData process = new FetchData(getContext());
        process.execute();

        return view;
    }

    private void setupRecyclerView() {
        adapter = new CustomAdapter(getContext(), recyclerViewData);
        recView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//        adapter.setClickListener(this);
        recView.setAdapter(adapter);
        recView.setHasFixedSize(true);
        recView.setItemViewCacheSize(20);
        recView.setDrawingCacheEnabled(true);
        recView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recView.setNestedScrollingEnabled(false);
    }

    private void postEvent(int type) {
        Event event = new Event();
        event.setEventType(type);

        EventBus eventBus = EventBus.getDefault();
        eventBus.post(event);
    }

    @Override
    public void setData(HashMap<String, String> data, Photo[] photos) {
        Log.d(TAG, "setData: setting data..." );
        String hoursStr = "";
        String[] hours = data.get("opening_hours").split(",");
        for (int i=0; i<hours.length; i++) {
            hoursStr += hours[i] + ((i==(hours.length-1)) ? "" : "\n");
        }
        for (int j=0; j<photos.length; j++) {
            Log.d(TAG, "setData: " + photos[j].toString());
            recyclerViewData.add(photos[j]);
        }
        setupRecyclerView();

        address.setText(data.get("formatted_address"));
        tv_phone.setText(data.get("formatted_phone_number"));
        tv_name.setText(data.get("name"));
        tv_rating.setText(data.get("rating"));
        tv_market_hours.setText(hoursStr);
        tv_website.setText(data.get("website"));

    }
}
