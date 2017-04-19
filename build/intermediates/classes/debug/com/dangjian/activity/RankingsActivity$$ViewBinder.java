// Generated code from Butter Knife. Do not modify!
package com.dangjian.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class RankingsActivity$$ViewBinder<T extends com.dangjian.activity.RankingsActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427602, "field 'rankingsLlBack' and method 'onClick'");
    target.rankingsLlBack = finder.castView(view, 2131427602, "field 'rankingsLlBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427604, "field 'rankingsTvTitleName'");
    target.rankingsTvTitleName = finder.castView(view, 2131427604, "field 'rankingsTvTitleName'");
    view = finder.findRequiredView(source, 2131427603, "field 'rankingsLlTitleName' and method 'onClick'");
    target.rankingsLlTitleName = finder.castView(view, 2131427603, "field 'rankingsLlTitleName'");
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
    view = finder.findRequiredView(source, 2131427605, "field 'rankingsRecyclerView'");
    target.rankingsRecyclerView = finder.castView(view, 2131427605, "field 'rankingsRecyclerView'");
  }

  @Override public void unbind(T target) {
    target.rankingsLlBack = null;
    target.rankingsTvTitleName = null;
    target.rankingsLlTitleName = null;
    target.emptyLoadingLayoutIv = null;
    target.emptyLoadingLayoutTvName = null;
    target.emptyLoadingLayoutLl = null;
    target.rankingsRecyclerView = null;
  }
}
