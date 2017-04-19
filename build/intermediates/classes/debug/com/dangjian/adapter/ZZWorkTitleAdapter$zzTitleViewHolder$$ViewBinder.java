// Generated code from Butter Knife. Do not modify!
package com.dangjian.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ZZWorkTitleAdapter$zzTitleViewHolder$$ViewBinder<T extends com.dangjian.adapter.ZZWorkTitleAdapter.zzTitleViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427858, "field 'itemzzWorkTitleIv'");
    target.itemzzWorkTitleIv = finder.castView(view, 2131427858, "field 'itemzzWorkTitleIv'");
    view = finder.findRequiredView(source, 2131427859, "field 'itemzzWorkTitleTv'");
    target.itemzzWorkTitleTv = finder.castView(view, 2131427859, "field 'itemzzWorkTitleTv'");
    view = finder.findRequiredView(source, 2131427860, "field 'itemzzWorkTitleBtnMessCount'");
    target.itemzzWorkTitleBtnMessCount = finder.castView(view, 2131427860, "field 'itemzzWorkTitleBtnMessCount'");
  }

  @Override public void unbind(T target) {
    target.itemzzWorkTitleIv = null;
    target.itemzzWorkTitleTv = null;
    target.itemzzWorkTitleBtnMessCount = null;
  }
}
