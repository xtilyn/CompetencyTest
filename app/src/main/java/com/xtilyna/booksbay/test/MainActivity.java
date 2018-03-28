package com.xtilyna.booksbay.test;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.xtilyna.booksbay.test.Utils.SectionsPagerAdapter;
import com.xtilyna.booksbay.test.Utils.VerticalViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, MyView{

    private static final String TAG = "MainActivity";
    @BindView(R.id.viewpager)
    VerticalViewPager viewPager;

    private Fragment2 fragment2;

    private MyPresenter myPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fragment2 = new BlankFragment2();
        myPresenter = new MyPresenter(MainActivity.this, fragment2);

        setupViewpager();
    }

    @Override
    protected void onStart() {
        myPresenter.onStart();
        super.onStart();
    }

    @Override
    protected void onStop() {
        myPresenter.onStop();
        super.onStop();
    }

    private void setupViewpager() {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new BlankFragment());
        adapter.addFragment((Fragment) fragment2);

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(MainActivity.this);
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 1) viewPager.setCurrentItem(0);
        else super.onBackPressed();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void goToNextPage() {
        viewPager.setCurrentItem(1);
    }

    @Override
    public void onFabClick() {
        viewPager.setCurrentItem(0);
    }

}
