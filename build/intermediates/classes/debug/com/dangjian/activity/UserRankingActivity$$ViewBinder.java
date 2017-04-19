// Generated code from Butter Knife. Do not modify!
package com.dangjian.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class UserRankingActivity$$ViewBinder<T extends com.dangjian.activity.UserRankingActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427756, "field 'emptyLoadingLayoutIv'");
    target.emptyLoadingLayoutIv = finder.castView(view, 2131427756, "field 'emptyLoadingLayoutIv'");
    view = finder.findRequiredView(source, 2131427757, "field 'emptyLoadingLayoutTvName'");
    target.emptyLoadingLayoutTvName = finder.castView(view, 2131427757, "field 'emptyLoadingLayoutTvName'");
    view = finder.findRequiredView(source, 2131427755, "field 'emptyLoadingLayoutLl'");
    target.emptyLoadingLayoutLl = finder.castView(view, 2131427755, "field 'emptyLoadingLayoutLl'");
    view = finder.findRequiredView(source, 2131427689, "field 'rankingRecyclerView'");
    target.rankingRecyclerView = finder.castView(view, 2131427689, "field 'rankingRecyclerView'");
    view = finder.findRequiredView(source, 2131427688, "field 'rankingRefresh'");
    target.rankingRefresh = finder.castView(view, 2131427688, "field 'rankingRefresh'");
    view = finder.findRequiredView(source, 2131427683, "field 'rankingLl'");
    target.rankingLl = finder.castView(view, 2131427683, "field 'rankingLl'");
    view = finder.findRequiredView(source, 2131427690, "field 'rankingTvSum'");
    target.rankingTvSum = finder.castView(view, 2131427690, "field 'rankingTvSum'");
    view = finder.findRequiredView(source, 2131427691, "field 'rankingUserPic'");
    target.rankingUserPic = finder.castView(view, 2131427691, "field 'rankingUserPic'");
    view = finder.findRequiredView(source, 2131427692, "field 'rankingTvName'");
    target.rankingTvName = finder.castView(view, 2131427692, "field 'rankingTvName'");
    view = finder.findRequiredView(source, 2131427693, "field 'rankingTvAll'");
    target.rankingTvAll = finder.castView(view, 2131427693, "field 'rankingTvAll'");
    view = finder.findRequiredView(source, 2131427694, "field 'rankingTvSubmit' and method 'onClick'");
    target.rankingTvSubmit = finder.castView(view, 2131427694, "field 'rankingTvSubmit'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427684, "field 'userRankingLlBack' and method 'onClick'");
    target.userRankingLlBack = finder.castView(view, 2131427684, "field 'userRankingLlBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427686, "field 'userRankingTvTitleName'");
    target.userRankingTvTitleName = finder.castView(view, 2131427686, "field 'userRankingTvTitleName'");
    view = finder.findRequiredView(source, 2131427685, "field 'userRankingLlTitleName' and method 'onClick'");
    target.userRankingLlTitleName = finder.castView(view, 2131427685, "field 'userRankingLlTitleName'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427687, "field 'userRankingTvRight' and method 'onClick'");
    target.userRankingTvRight = finder.castView(view, 2131427687, "field 'userRankingTvRight'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.emptyLoadingLayoutIv = null;
    target.emptyLoadingLayoutTvName = null;
    target.emptyLoadingLayoutLl = null;
    target.rankingRecyclerView = null;
    target.rankingRefresh = null;
    target.rankingLl = null;
    target.rankingTvSum = null;
    target.rankingUserPic = null;
    target.rankingTvName = null;
    target.rankingTvAll = null;
    target.rankingTvSubmit = null;
    target.userRankingLlBack = null;
    target.userRankingTvTitleName = null;
    target.userRankingLlTitleName = null;
    target.userRankingTvRight = null;
  }
}
