// Generated code from Butter Knife. Do not modify!
package com.dangjian.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ExamAdapter$ExamViewHolder$$ViewBinder<T extends com.dangjian.adapter.ExamAdapter.ExamViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427773, "field 'itemExamTvNumber'");
    target.itemExamTvNumber = finder.castView(view, 2131427773, "field 'itemExamTvNumber'");
    view = finder.findRequiredView(source, 2131427775, "field 'itemExamTvContent'");
    target.itemExamTvContent = finder.castView(view, 2131427775, "field 'itemExamTvContent'");
    view = finder.findRequiredView(source, 2131427772, "field 'itemExamIv'");
    target.itemExamIv = finder.castView(view, 2131427772, "field 'itemExamIv'");
    view = finder.findRequiredView(source, 2131427774, "field 'itemExamTv'");
    target.itemExamTv = finder.castView(view, 2131427774, "field 'itemExamTv'");
  }

  @Override public void unbind(T target) {
    target.itemExamTvNumber = null;
    target.itemExamTvContent = null;
    target.itemExamIv = null;
    target.itemExamTv = null;
  }
}
