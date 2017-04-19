// Generated code from Butter Knife. Do not modify!
package com.dangjian.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ZZWorkTitleDetailsActivity$$ViewBinder<T extends com.dangjian.activity.ZZWorkTitleDetailsActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427760, "field 'titleRightTextLlBack' and method 'onClick'");
    target.titleRightTextLlBack = finder.castView(view, 2131427760, "field 'titleRightTextLlBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427761, "field 'titleRightTextTvTitleName'");
    target.titleRightTextTvTitleName = finder.castView(view, 2131427761, "field 'titleRightTextTvTitleName'");
    view = finder.findRequiredView(source, 2131427762, "field 'titleRightTextTvRightName' and method 'onClick'");
    target.titleRightTextTvRightName = finder.castView(view, 2131427762, "field 'titleRightTextTvRightName'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427756, "field 'emptyLoadingLayoutIv'");
    target.emptyLoadingLayoutIv = finder.castView(view, 2131427756, "field 'emptyLoadingLayoutIv'");
    view = finder.findRequiredView(source, 2131427757, "field 'emptyLoadingLayoutTvName'");
    target.emptyLoadingLayoutTvName = finder.castView(view, 2131427757, "field 'emptyLoadingLayoutTvName'");
    view = finder.findRequiredView(source, 2131427755, "field 'emptyLoadingLayoutLl'");
    target.emptyLoadingLayoutLl = finder.castView(view, 2131427755, "field 'emptyLoadingLayoutLl'");
    view = finder.findRequiredView(source, 2131427743, "field 'zzTitleDetailRecyclerView'");
    target.zzTitleDetailRecyclerView = finder.castView(view, 2131427743, "field 'zzTitleDetailRecyclerView'");
    view = finder.findRequiredView(source, 2131427742, "field 'zzTitleDetailRefresh'");
    target.zzTitleDetailRefresh = finder.castView(view, 2131427742, "field 'zzTitleDetailRefresh'");
  }

  @Override public void unbind(T target) {
    target.titleRightTextLlBack = null;
    target.titleRightTextTvTitleName = null;
    target.titleRightTextTvRightName = null;
    target.emptyLoadingLayoutIv = null;
    target.emptyLoadingLayoutTvName = null;
    target.emptyLoadingLayoutLl = null;
    target.zzTitleDetailRecyclerView = null;
    target.zzTitleDetailRefresh = null;
  }
}
