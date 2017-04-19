// Generated code from Butter Knife. Do not modify!
package com.dangjian.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class WeekExamActivity$$ViewBinder<T extends com.dangjian.activity.WeekExamActivity> implements ViewBinder<T> {
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
    view = finder.findRequiredView(source, 2131427698, "field 'weekExamrecyclerView'");
    target.weekExamrecyclerView = finder.castView(view, 2131427698, "field 'weekExamrecyclerView'");
    view = finder.findRequiredView(source, 2131427699, "field 'weekExamtvTotle'");
    target.weekExamtvTotle = finder.castView(view, 2131427699, "field 'weekExamtvTotle'");
    view = finder.findRequiredView(source, 2131427700, "field 'weekExamtvHour'");
    target.weekExamtvHour = finder.castView(view, 2131427700, "field 'weekExamtvHour'");
    view = finder.findRequiredView(source, 2131427701, "field 'weekExamtvMinute'");
    target.weekExamtvMinute = finder.castView(view, 2131427701, "field 'weekExamtvMinute'");
    view = finder.findRequiredView(source, 2131427702, "field 'weekExamtvSecond'");
    target.weekExamtvSecond = finder.castView(view, 2131427702, "field 'weekExamtvSecond'");
    view = finder.findRequiredView(source, 2131427703, "field 'weekExambtnUp' and method 'onClick'");
    target.weekExambtnUp = finder.castView(view, 2131427703, "field 'weekExambtnUp'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427704, "field 'weekExambtnDown' and method 'onClick'");
    target.weekExambtnDown = finder.castView(view, 2131427704, "field 'weekExambtnDown'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427695, "field 'weekExamLlContent'");
    target.weekExamLlContent = finder.castView(view, 2131427695, "field 'weekExamLlContent'");
    view = finder.findRequiredView(source, 2131427696, "field 'weekExamTvSequence'");
    target.weekExamTvSequence = finder.castView(view, 2131427696, "field 'weekExamTvSequence'");
    view = finder.findRequiredView(source, 2131427697, "field 'weekExamTvTitleName'");
    target.weekExamTvTitleName = finder.castView(view, 2131427697, "field 'weekExamTvTitleName'");
  }

  @Override public void unbind(T target) {
    target.titleOneTextLlBack = null;
    target.titleOneTextTvTitleName = null;
    target.weekExamrecyclerView = null;
    target.weekExamtvTotle = null;
    target.weekExamtvHour = null;
    target.weekExamtvMinute = null;
    target.weekExamtvSecond = null;
    target.weekExambtnUp = null;
    target.weekExambtnDown = null;
    target.weekExamLlContent = null;
    target.weekExamTvSequence = null;
    target.weekExamTvTitleName = null;
  }
}
