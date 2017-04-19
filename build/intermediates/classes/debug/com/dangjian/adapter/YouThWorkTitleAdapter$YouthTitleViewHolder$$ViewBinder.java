// Generated code from Butter Knife. Do not modify!
package com.dangjian.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class YouThWorkTitleAdapter$YouthTitleViewHolder$$ViewBinder<T extends com.dangjian.adapter.YouThWorkTitleAdapter.YouthTitleViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427843, "field 'itemYouthWorkTitleIv'");
    target.itemYouthWorkTitleIv = finder.castView(view, 2131427843, "field 'itemYouthWorkTitleIv'");
    view = finder.findRequiredView(source, 2131427844, "field 'itemYouthWorkTitleTv'");
    target.itemYouthWorkTitleTv = finder.castView(view, 2131427844, "field 'itemYouthWorkTitleTv'");
    view = finder.findRequiredView(source, 2131427845, "field 'itemYouthWorkTitleBtnMessCount'");
    target.itemYouthWorkTitleBtnMessCount = finder.castView(view, 2131427845, "field 'itemYouthWorkTitleBtnMessCount'");
  }

  @Override public void unbind(T target) {
    target.itemYouthWorkTitleIv = null;
    target.itemYouthWorkTitleTv = null;
    target.itemYouthWorkTitleBtnMessCount = null;
  }
}
