// Generated code from Butter Knife. Do not modify!
package com.dangjian.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LearningRankActivity$$ViewBinder<T extends com.dangjian.activity.LearningRankActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427536, "field 'learningRankLlBack' and method 'onClick'");
    target.learningRankLlBack = finder.castView(view, 2131427536, "field 'learningRankLlBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427538, "field 'learningRankTvTitleName'");
    target.learningRankTvTitleName = finder.castView(view, 2131427538, "field 'learningRankTvTitleName'");
    view = finder.findRequiredView(source, 2131427537, "field 'learningRankLlTitleSelect' and method 'onClick'");
    target.learningRankLlTitleSelect = finder.castView(view, 2131427537, "field 'learningRankLlTitleSelect'");
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
    view = finder.findRequiredView(source, 2131427540, "field 'learningRankRecyclerView'");
    target.learningRankRecyclerView = finder.castView(view, 2131427540, "field 'learningRankRecyclerView'");
    view = finder.findRequiredView(source, 2131427539, "field 'learningRankRefresh'");
    target.learningRankRefresh = finder.castView(view, 2131427539, "field 'learningRankRefresh'");
    view = finder.findRequiredView(source, 2131427535, "field 'llAll'");
    target.llAll = finder.castView(view, 2131427535, "field 'llAll'");
    view = finder.findRequiredView(source, 2131427541, "field 'learningRankTvSum'");
    target.learningRankTvSum = finder.castView(view, 2131427541, "field 'learningRankTvSum'");
    view = finder.findRequiredView(source, 2131427542, "field 'learningRankUserPic'");
    target.learningRankUserPic = finder.castView(view, 2131427542, "field 'learningRankUserPic'");
    view = finder.findRequiredView(source, 2131427543, "field 'learningRankTvName'");
    target.learningRankTvName = finder.castView(view, 2131427543, "field 'learningRankTvName'");
    view = finder.findRequiredView(source, 2131427544, "field 'learningRankTvAll'");
    target.learningRankTvAll = finder.castView(view, 2131427544, "field 'learningRankTvAll'");
  }

  @Override public void unbind(T target) {
    target.learningRankLlBack = null;
    target.learningRankTvTitleName = null;
    target.learningRankLlTitleSelect = null;
    target.emptyLoadingLayoutIv = null;
    target.emptyLoadingLayoutTvName = null;
    target.emptyLoadingLayoutLl = null;
    target.learningRankRecyclerView = null;
    target.learningRankRefresh = null;
    target.llAll = null;
    target.learningRankTvSum = null;
    target.learningRankUserPic = null;
    target.learningRankTvName = null;
    target.learningRankTvAll = null;
  }
}
