// Generated code from Butter Knife. Do not modify!
package com.dangjian.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LearingContentAdapter$ContentViewHolder$$ViewBinder<T extends com.dangjian.adapter.LearingContentAdapter.ContentViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427795, "field 'learingContentIv'");
    target.learingContentIv = finder.castView(view, 2131427795, "field 'learingContentIv'");
    view = finder.findRequiredView(source, 2131427796, "field 'learingContentTvTitle'");
    target.learingContentTvTitle = finder.castView(view, 2131427796, "field 'learingContentTvTitle'");
    view = finder.findRequiredView(source, 2131427797, "field 'learingContentTvZZ'");
    target.learingContentTvZZ = finder.castView(view, 2131427797, "field 'learingContentTvZZ'");
    view = finder.findRequiredView(source, 2131427798, "field 'learingContentTvDate'");
    target.learingContentTvDate = finder.castView(view, 2131427798, "field 'learingContentTvDate'");
    view = finder.findRequiredView(source, 2131427799, "field 'learingContentTvStates'");
    target.learingContentTvStates = finder.castView(view, 2131427799, "field 'learingContentTvStates'");
  }

  @Override public void unbind(T target) {
    target.learingContentIv = null;
    target.learingContentTvTitle = null;
    target.learingContentTvZZ = null;
    target.learingContentTvDate = null;
    target.learingContentTvStates = null;
  }
}
