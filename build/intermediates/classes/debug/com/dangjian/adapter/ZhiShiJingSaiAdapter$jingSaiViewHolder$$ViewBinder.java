// Generated code from Butter Knife. Do not modify!
package com.dangjian.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ZhiShiJingSaiAdapter$jingSaiViewHolder$$ViewBinder<T extends com.dangjian.adapter.ZhiShiJingSaiAdapter.jingSaiViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427790, "field 'jingSaiTvTitle'");
    target.jingSaiTvTitle = finder.castView(view, 2131427790, "field 'jingSaiTvTitle'");
    view = finder.findRequiredView(source, 2131427792, "field 'jingSaiTvStates'");
    target.jingSaiTvStates = finder.castView(view, 2131427792, "field 'jingSaiTvStates'");
    view = finder.findRequiredView(source, 2131427793, "field 'jingSaiTvTime'");
    target.jingSaiTvTime = finder.castView(view, 2131427793, "field 'jingSaiTvTime'");
    view = finder.findRequiredView(source, 2131427789, "field 'jingSaiIv'");
    target.jingSaiIv = finder.castView(view, 2131427789, "field 'jingSaiIv'");
    view = finder.findRequiredView(source, 2131427791, "field 'jingSaiTvIsNew'");
    target.jingSaiTvIsNew = finder.castView(view, 2131427791, "field 'jingSaiTvIsNew'");
    view = finder.findRequiredView(source, 2131427794, "field 'jingSaiTvEndTime'");
    target.jingSaiTvEndTime = finder.castView(view, 2131427794, "field 'jingSaiTvEndTime'");
  }

  @Override public void unbind(T target) {
    target.jingSaiTvTitle = null;
    target.jingSaiTvStates = null;
    target.jingSaiTvTime = null;
    target.jingSaiIv = null;
    target.jingSaiTvIsNew = null;
    target.jingSaiTvEndTime = null;
  }
}
