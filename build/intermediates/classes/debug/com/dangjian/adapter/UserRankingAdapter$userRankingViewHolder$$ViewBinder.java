// Generated code from Butter Knife. Do not modify!
package com.dangjian.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class UserRankingAdapter$userRankingViewHolder$$ViewBinder<T extends com.dangjian.adapter.UserRankingAdapter.userRankingViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427839, "field 'itemUserRankingTvPosition'");
    target.itemUserRankingTvPosition = finder.castView(view, 2131427839, "field 'itemUserRankingTvPosition'");
    view = finder.findRequiredView(source, 2131427840, "field 'itemUserRankingUserPic'");
    target.itemUserRankingUserPic = finder.castView(view, 2131427840, "field 'itemUserRankingUserPic'");
    view = finder.findRequiredView(source, 2131427841, "field 'itemUserRankingTvName'");
    target.itemUserRankingTvName = finder.castView(view, 2131427841, "field 'itemUserRankingTvName'");
    view = finder.findRequiredView(source, 2131427842, "field 'itemUserRankingTvSum'");
    target.itemUserRankingTvSum = finder.castView(view, 2131427842, "field 'itemUserRankingTvSum'");
  }

  @Override public void unbind(T target) {
    target.itemUserRankingTvPosition = null;
    target.itemUserRankingUserPic = null;
    target.itemUserRankingTvName = null;
    target.itemUserRankingTvSum = null;
  }
}
