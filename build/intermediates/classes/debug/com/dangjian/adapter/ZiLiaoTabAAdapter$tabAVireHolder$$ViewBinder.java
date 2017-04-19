// Generated code from Butter Knife. Do not modify!
package com.dangjian.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ZiLiaoTabAAdapter$tabAVireHolder$$ViewBinder<T extends com.dangjian.adapter.ZiLiaoTabAAdapter.tabAVireHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427846, "field 'itemZiLiaoTabATvNew'");
    target.itemZiLiaoTabATvNew = finder.castView(view, 2131427846, "field 'itemZiLiaoTabATvNew'");
    view = finder.findRequiredView(source, 2131427847, "field 'itemZiLiaoTabATvTitle'");
    target.itemZiLiaoTabATvTitle = finder.castView(view, 2131427847, "field 'itemZiLiaoTabATvTitle'");
    view = finder.findRequiredView(source, 2131427848, "field 'itemZiLiaoTabATvTime'");
    target.itemZiLiaoTabATvTime = finder.castView(view, 2131427848, "field 'itemZiLiaoTabATvTime'");
  }

  @Override public void unbind(T target) {
    target.itemZiLiaoTabATvNew = null;
    target.itemZiLiaoTabATvTitle = null;
    target.itemZiLiaoTabATvTime = null;
  }
}
