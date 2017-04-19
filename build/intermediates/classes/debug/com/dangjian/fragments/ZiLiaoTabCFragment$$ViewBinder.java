// Generated code from Butter Knife. Do not modify!
package com.dangjian.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ZiLiaoTabCFragment$$ViewBinder<T extends com.dangjian.fragments.ZiLiaoTabCFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427756, "field 'emptyLoadingLayoutIv'");
    target.emptyLoadingLayoutIv = finder.castView(view, 2131427756, "field 'emptyLoadingLayoutIv'");
    view = finder.findRequiredView(source, 2131427757, "field 'emptyLoadingLayoutTvName'");
    target.emptyLoadingLayoutTvName = finder.castView(view, 2131427757, "field 'emptyLoadingLayoutTvName'");
    view = finder.findRequiredView(source, 2131427755, "field 'emptyLoadingLayoutLl'");
    target.emptyLoadingLayoutLl = finder.castView(view, 2131427755, "field 'emptyLoadingLayoutLl'");
    view = finder.findRequiredView(source, 2131427753, "field 'ziLiaoTabCRecyclerView'");
    target.ziLiaoTabCRecyclerView = finder.castView(view, 2131427753, "field 'ziLiaoTabCRecyclerView'");
    view = finder.findRequiredView(source, 2131427752, "field 'ziLiaoTabCRefresh'");
    target.ziLiaoTabCRefresh = finder.castView(view, 2131427752, "field 'ziLiaoTabCRefresh'");
  }

  @Override public void unbind(T target) {
    target.emptyLoadingLayoutIv = null;
    target.emptyLoadingLayoutTvName = null;
    target.emptyLoadingLayoutLl = null;
    target.ziLiaoTabCRecyclerView = null;
    target.ziLiaoTabCRefresh = null;
  }
}
