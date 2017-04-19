// Generated code from Butter Knife. Do not modify!
package com.dangjian.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class PopTitleSelectAdapter$TitleSelectViewHolder$$ViewBinder<T extends com.dangjian.adapter.PopTitleSelectAdapter.TitleSelectViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427816, "field 'itemPopTitleSelectTv'");
    target.itemPopTitleSelectTv = finder.castView(view, 2131427816, "field 'itemPopTitleSelectTv'");
  }

  @Override public void unbind(T target) {
    target.itemPopTitleSelectTv = null;
  }
}
