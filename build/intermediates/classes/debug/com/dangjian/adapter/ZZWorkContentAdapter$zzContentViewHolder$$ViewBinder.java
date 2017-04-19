// Generated code from Butter Knife. Do not modify!
package com.dangjian.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ZZWorkContentAdapter$zzContentViewHolder$$ViewBinder<T extends com.dangjian.adapter.ZZWorkContentAdapter.zzContentViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427854, "field 'itemZZWorkContentTvTitle'");
    target.itemZZWorkContentTvTitle = finder.castView(view, 2131427854, "field 'itemZZWorkContentTvTitle'");
    view = finder.findRequiredView(source, 2131427855, "field 'itemZZWorkContentTvWork'");
    target.itemZZWorkContentTvWork = finder.castView(view, 2131427855, "field 'itemZZWorkContentTvWork'");
    view = finder.findRequiredView(source, 2131427856, "field 'itemZZWorkContentTvDate'");
    target.itemZZWorkContentTvDate = finder.castView(view, 2131427856, "field 'itemZZWorkContentTvDate'");
    view = finder.findRequiredView(source, 2131427857, "field 'itemZZWorkContentTvStates'");
    target.itemZZWorkContentTvStates = finder.castView(view, 2131427857, "field 'itemZZWorkContentTvStates'");
  }

  @Override public void unbind(T target) {
    target.itemZZWorkContentTvTitle = null;
    target.itemZZWorkContentTvWork = null;
    target.itemZZWorkContentTvDate = null;
    target.itemZZWorkContentTvStates = null;
  }
}
