// Generated code from Butter Knife. Do not modify!
package com.dangjian.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ZiLiaoTabCAdapter$tabCViewHolder$$ViewBinder<T extends com.dangjian.adapter.ZiLiaoTabCAdapter.tabCViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427849, "field 'itemZiLiaoTabCTvTitle'");
    target.itemZiLiaoTabCTvTitle = finder.castView(view, 2131427849, "field 'itemZiLiaoTabCTvTitle'");
    view = finder.findRequiredView(source, 2131427850, "field 'itemZiLiaoTabCTvStates'");
    target.itemZiLiaoTabCTvStates = finder.castView(view, 2131427850, "field 'itemZiLiaoTabCTvStates'");
  }

  @Override public void unbind(T target) {
    target.itemZiLiaoTabCTvTitle = null;
    target.itemZiLiaoTabCTvStates = null;
  }
}
