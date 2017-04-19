// Generated code from Butter Knife. Do not modify!
package com.dangjian.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class UnionWorkAdapter$UnionViewHolder$$ViewBinder<T extends com.dangjian.adapter.UnionWorkAdapter.UnionViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427837, "field 'itemUnionWorkIv'");
    target.itemUnionWorkIv = finder.castView(view, 2131427837, "field 'itemUnionWorkIv'");
    view = finder.findRequiredView(source, 2131427838, "field 'itemUnionWorkTv'");
    target.itemUnionWorkTv = finder.castView(view, 2131427838, "field 'itemUnionWorkTv'");
    view = finder.findRequiredView(source, 2131427836, "field 'itemUnionWorkBtnMessCount'");
    target.itemUnionWorkBtnMessCount = finder.castView(view, 2131427836, "field 'itemUnionWorkBtnMessCount'");
  }

  @Override public void unbind(T target) {
    target.itemUnionWorkIv = null;
    target.itemUnionWorkTv = null;
    target.itemUnionWorkBtnMessCount = null;
  }
}
