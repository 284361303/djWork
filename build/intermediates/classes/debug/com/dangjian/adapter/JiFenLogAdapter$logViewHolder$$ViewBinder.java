// Generated code from Butter Knife. Do not modify!
package com.dangjian.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class JiFenLogAdapter$logViewHolder$$ViewBinder<T extends com.dangjian.adapter.JiFenLogAdapter.logViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427785, "field 'jiFenLogTvTypeName'");
    target.jiFenLogTvTypeName = finder.castView(view, 2131427785, "field 'jiFenLogTvTypeName'");
    view = finder.findRequiredView(source, 2131427786, "field 'jiFenLogTvPoints'");
    target.jiFenLogTvPoints = finder.castView(view, 2131427786, "field 'jiFenLogTvPoints'");
    view = finder.findRequiredView(source, 2131427787, "field 'jiFenLogTvSubTypeName'");
    target.jiFenLogTvSubTypeName = finder.castView(view, 2131427787, "field 'jiFenLogTvSubTypeName'");
    view = finder.findRequiredView(source, 2131427788, "field 'jiFenLogTvDate'");
    target.jiFenLogTvDate = finder.castView(view, 2131427788, "field 'jiFenLogTvDate'");
    view = finder.findRequiredView(source, 2131427784, "field 'jiFenLogLlAll'");
    target.jiFenLogLlAll = finder.castView(view, 2131427784, "field 'jiFenLogLlAll'");
  }

  @Override public void unbind(T target) {
    target.jiFenLogTvTypeName = null;
    target.jiFenLogTvPoints = null;
    target.jiFenLogTvSubTypeName = null;
    target.jiFenLogTvDate = null;
    target.jiFenLogLlAll = null;
  }
}
