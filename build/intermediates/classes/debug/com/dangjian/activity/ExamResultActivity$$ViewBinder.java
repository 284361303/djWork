// Generated code from Butter Knife. Do not modify!
package com.dangjian.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ExamResultActivity$$ViewBinder<T extends com.dangjian.activity.ExamResultActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427758, "field 'titleOneTextLlBack' and method 'onClick'");
    target.titleOneTextLlBack = finder.castView(view, 2131427758, "field 'titleOneTextLlBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427759, "field 'titleOneTextTvTitleName'");
    target.titleOneTextTvTitleName = finder.castView(view, 2131427759, "field 'titleOneTextTvTitleName'");
    view = finder.findRequiredView(source, 2131427507, "field 'examResultTvSum'");
    target.examResultTvSum = finder.castView(view, 2131427507, "field 'examResultTvSum'");
    view = finder.findRequiredView(source, 2131427509, "field 'examResultTvTotal'");
    target.examResultTvTotal = finder.castView(view, 2131427509, "field 'examResultTvTotal'");
    view = finder.findRequiredView(source, 2131427511, "field 'examResultBtnBack' and method 'onClick'");
    target.examResultBtnBack = finder.castView(view, 2131427511, "field 'examResultBtnBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427512, "field 'examResultBtnRanking' and method 'onClick'");
    target.examResultBtnRanking = finder.castView(view, 2131427512, "field 'examResultBtnRanking'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427504, "field 'examResultTvThanks'");
    target.examResultTvThanks = finder.castView(view, 2131427504, "field 'examResultTvThanks'");
    view = finder.findRequiredView(source, 2131427508, "field 'examResultLlBottom'");
    target.examResultLlBottom = finder.castView(view, 2131427508, "field 'examResultLlBottom'");
    view = finder.findRequiredView(source, 2131427510, "field 'examResultTvJiFen'");
    target.examResultTvJiFen = finder.castView(view, 2131427510, "field 'examResultTvJiFen'");
    view = finder.findRequiredView(source, 2131427506, "field 'examResultTvAll'");
    target.examResultTvAll = finder.castView(view, 2131427506, "field 'examResultTvAll'");
    view = finder.findRequiredView(source, 2131427505, "field 'examResultLlAll'");
    target.examResultLlAll = finder.castView(view, 2131427505, "field 'examResultLlAll'");
  }

  @Override public void unbind(T target) {
    target.titleOneTextLlBack = null;
    target.titleOneTextTvTitleName = null;
    target.examResultTvSum = null;
    target.examResultTvTotal = null;
    target.examResultBtnBack = null;
    target.examResultBtnRanking = null;
    target.examResultTvThanks = null;
    target.examResultLlBottom = null;
    target.examResultTvJiFen = null;
    target.examResultTvAll = null;
    target.examResultLlAll = null;
  }
}
