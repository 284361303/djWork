// Generated code from Butter Knife. Do not modify!
package com.dangjian.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class RankingsAdapter$RankingsViewHolder$$ViewBinder<T extends com.dangjian.adapter.RankingsAdapter.RankingsViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427821, "field 'itemRankingsTvPosition'");
    target.itemRankingsTvPosition = finder.castView(view, 2131427821, "field 'itemRankingsTvPosition'");
    view = finder.findRequiredView(source, 2131427824, "field 'itemRankingsLlSingleTvName'");
    target.itemRankingsLlSingleTvName = finder.castView(view, 2131427824, "field 'itemRankingsLlSingleTvName'");
    view = finder.findRequiredView(source, 2131427825, "field 'itemRankingsLlSingleTvSum'");
    target.itemRankingsLlSingleTvSum = finder.castView(view, 2131427825, "field 'itemRankingsLlSingleTvSum'");
    view = finder.findRequiredView(source, 2131427823, "field 'itemRankingsLlSingle'");
    target.itemRankingsLlSingle = finder.castView(view, 2131427823, "field 'itemRankingsLlSingle'");
    view = finder.findRequiredView(source, 2131427827, "field 'itemRankingsIvName'");
    target.itemRankingsIvName = finder.castView(view, 2131427827, "field 'itemRankingsIvName'");
    view = finder.findRequiredView(source, 2131427828, "field 'itemRankingsTvFenSum'");
    target.itemRankingsTvFenSum = finder.castView(view, 2131427828, "field 'itemRankingsTvFenSum'");
    view = finder.findRequiredView(source, 2131427829, "field 'itemRankingsTvFen'");
    target.itemRankingsTvFen = finder.castView(view, 2131427829, "field 'itemRankingsTvFen'");
    view = finder.findRequiredView(source, 2131427826, "field 'itemRankingsLlMore'");
    target.itemRankingsLlMore = finder.castView(view, 2131427826, "field 'itemRankingsLlMore'");
    view = finder.findRequiredView(source, 2131427822, "field 'itemRankingsIv'");
    target.itemRankingsIv = finder.castView(view, 2131427822, "field 'itemRankingsIv'");
  }

  @Override public void unbind(T target) {
    target.itemRankingsTvPosition = null;
    target.itemRankingsLlSingleTvName = null;
    target.itemRankingsLlSingleTvSum = null;
    target.itemRankingsLlSingle = null;
    target.itemRankingsIvName = null;
    target.itemRankingsTvFenSum = null;
    target.itemRankingsTvFen = null;
    target.itemRankingsLlMore = null;
    target.itemRankingsIv = null;
  }
}
