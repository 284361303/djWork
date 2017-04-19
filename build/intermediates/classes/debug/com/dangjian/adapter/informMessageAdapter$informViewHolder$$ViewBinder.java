// Generated code from Butter Knife. Do not modify!
package com.dangjian.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class informMessageAdapter$informViewHolder$$ViewBinder<T extends com.dangjian.adapter.informMessageAdapter.informViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427776, "field 'itemInformMessageTvTitle'");
    target.itemInformMessageTvTitle = finder.castView(view, 2131427776, "field 'itemInformMessageTvTitle'");
    view = finder.findRequiredView(source, 2131427777, "field 'itemInformMessageTvCreator'");
    target.itemInformMessageTvCreator = finder.castView(view, 2131427777, "field 'itemInformMessageTvCreator'");
    view = finder.findRequiredView(source, 2131427778, "field 'itemInformMessageTvDate'");
    target.itemInformMessageTvDate = finder.castView(view, 2131427778, "field 'itemInformMessageTvDate'");
    view = finder.findRequiredView(source, 2131427779, "field 'itemInformMessageTvStates'");
    target.itemInformMessageTvStates = finder.castView(view, 2131427779, "field 'itemInformMessageTvStates'");
  }

  @Override public void unbind(T target) {
    target.itemInformMessageTvTitle = null;
    target.itemInformMessageTvCreator = null;
    target.itemInformMessageTvDate = null;
    target.itemInformMessageTvStates = null;
  }
}
