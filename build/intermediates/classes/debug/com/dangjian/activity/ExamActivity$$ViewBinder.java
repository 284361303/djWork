// Generated code from Butter Knife. Do not modify!
package com.dangjian.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ExamActivity$$ViewBinder<T extends com.dangjian.activity.ExamActivity> implements ViewBinder<T> {
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
    view = finder.findRequiredView(source, 2131427495, "field 'examTvSequence'");
    target.examTvSequence = finder.castView(view, 2131427495, "field 'examTvSequence'");
    view = finder.findRequiredView(source, 2131427496, "field 'examTvTitleName'");
    target.examTvTitleName = finder.castView(view, 2131427496, "field 'examTvTitleName'");
    view = finder.findRequiredView(source, 2131427497, "field 'examRecyclerView'");
    target.examRecyclerView = finder.castView(view, 2131427497, "field 'examRecyclerView'");
    view = finder.findRequiredView(source, 2131427499, "field 'examTvHour'");
    target.examTvHour = finder.castView(view, 2131427499, "field 'examTvHour'");
    view = finder.findRequiredView(source, 2131427500, "field 'examTvMinute'");
    target.examTvMinute = finder.castView(view, 2131427500, "field 'examTvMinute'");
    view = finder.findRequiredView(source, 2131427501, "field 'examTvSecond'");
    target.examTvSecond = finder.castView(view, 2131427501, "field 'examTvSecond'");
    view = finder.findRequiredView(source, 2131427502, "field 'examBtnUp' and method 'onClick'");
    target.examBtnUp = finder.castView(view, 2131427502, "field 'examBtnUp'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427503, "field 'examBtnDown' and method 'onClick'");
    target.examBtnDown = finder.castView(view, 2131427503, "field 'examBtnDown'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427498, "field 'examTvTotle'");
    target.examTvTotle = finder.castView(view, 2131427498, "field 'examTvTotle'");
    view = finder.findRequiredView(source, 2131427494, "field 'examLlContent'");
    target.examLlContent = finder.castView(view, 2131427494, "field 'examLlContent'");
  }

  @Override public void unbind(T target) {
    target.titleOneTextLlBack = null;
    target.titleOneTextTvTitleName = null;
    target.examTvSequence = null;
    target.examTvTitleName = null;
    target.examRecyclerView = null;
    target.examTvHour = null;
    target.examTvMinute = null;
    target.examTvSecond = null;
    target.examBtnUp = null;
    target.examBtnDown = null;
    target.examTvTotle = null;
    target.examLlContent = null;
  }
}
