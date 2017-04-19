// Generated code from Butter Knife. Do not modify!
package com.dangjian.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class JiFenDuiHuanAdapter$jiFenViewHolder$$ViewBinder<T extends com.dangjian.adapter.JiFenDuiHuanAdapter.jiFenViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427780, "field 'itemJiFenUserPic'");
    target.itemJiFenUserPic = finder.castView(view, 2131427780, "field 'itemJiFenUserPic'");
    view = finder.findRequiredView(source, 2131427781, "field 'itemJiFenTvName'");
    target.itemJiFenTvName = finder.castView(view, 2131427781, "field 'itemJiFenTvName'");
    view = finder.findRequiredView(source, 2131427782, "field 'itemJiFenTvTime'");
    target.itemJiFenTvTime = finder.castView(view, 2131427782, "field 'itemJiFenTvTime'");
    view = finder.findRequiredView(source, 2131427783, "field 'itemJiFenBtnTrue'");
    target.itemJiFenBtnTrue = finder.castView(view, 2131427783, "field 'itemJiFenBtnTrue'");
  }

  @Override public void unbind(T target) {
    target.itemJiFenUserPic = null;
    target.itemJiFenTvName = null;
    target.itemJiFenTvTime = null;
    target.itemJiFenBtnTrue = null;
  }
}
