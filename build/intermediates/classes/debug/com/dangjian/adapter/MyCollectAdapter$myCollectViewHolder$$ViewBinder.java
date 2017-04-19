// Generated code from Butter Knife. Do not modify!
package com.dangjian.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MyCollectAdapter$myCollectViewHolder$$ViewBinder<T extends com.dangjian.adapter.MyCollectAdapter.myCollectViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427802, "field 'mycollectIv'");
    target.mycollectIv = finder.castView(view, 2131427802, "field 'mycollectIv'");
    view = finder.findRequiredView(source, 2131427803, "field 'mycollectTvTitle'");
    target.mycollectTvTitle = finder.castView(view, 2131427803, "field 'mycollectTvTitle'");
    view = finder.findRequiredView(source, 2131427804, "field 'mycollectTvZZ'");
    target.mycollectTvZZ = finder.castView(view, 2131427804, "field 'mycollectTvZZ'");
    view = finder.findRequiredView(source, 2131427805, "field 'mycollectTvDate'");
    target.mycollectTvDate = finder.castView(view, 2131427805, "field 'mycollectTvDate'");
  }

  @Override public void unbind(T target) {
    target.mycollectIv = null;
    target.mycollectTvTitle = null;
    target.mycollectTvZZ = null;
    target.mycollectTvDate = null;
  }
}
