// Generated code from Butter Knife. Do not modify!
package com.dangjian.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ZZWorkAdapter$ZZWorkViewHolder$$ViewBinder<T extends com.dangjian.adapter.ZZWorkAdapter.ZZWorkViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427851, "field 'itemZZWorkIv'");
    target.itemZZWorkIv = finder.castView(view, 2131427851, "field 'itemZZWorkIv'");
    view = finder.findRequiredView(source, 2131427852, "field 'itemZZWorkTvTitle'");
    target.itemZZWorkTvTitle = finder.castView(view, 2131427852, "field 'itemZZWorkTvTitle'");
    view = finder.findRequiredView(source, 2131427853, "field 'itemZZWorkTvStates'");
    target.itemZZWorkTvStates = finder.castView(view, 2131427853, "field 'itemZZWorkTvStates'");
  }

  @Override public void unbind(T target) {
    target.itemZZWorkIv = null;
    target.itemZZWorkTvTitle = null;
    target.itemZZWorkTvStates = null;
  }
}
