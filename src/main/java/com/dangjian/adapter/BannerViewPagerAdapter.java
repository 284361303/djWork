package com.dangjian.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by sks on 2016/4/18.
 * 轮播图adapter
 */
public class BannerViewPagerAdapter extends PagerAdapter {

    private List<ImageView> imageViews; // 存放广告图片的列表
    private ViewPager myViewPager;
    private static final String TAG = "YouthWorkActivity";

    public BannerViewPagerAdapter(ViewPager viewPager, List<ImageView> imageViews) {
        this.myViewPager = viewPager;
        this.imageViews = imageViews;
    }

    @Override
    public int getCount() {
        if (imageViews != null && imageViews.size() > 0) {
            return imageViews.size();
        } else {
            return 0;
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewParent parent = imageViews.get(position % imageViews.size()).getParent();
        if (parent != null) {
            myViewPager.removeView(imageViews.get(position % imageViews.size()));
        }
        container.addView(imageViews.get(position % imageViews.size()));
        return imageViews.get(position % imageViews.size());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageViews.get(position % imageViews.size()));
    }
}
