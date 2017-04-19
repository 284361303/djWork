// Generated code from Butter Knife. Do not modify!
package com.dangjian.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class JingSaiStartActivity$$ViewBinder<T extends com.dangjian.activity.JingSaiStartActivity> implements ViewBinder<T> {
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
    view = finder.findRequiredView(source, 2131427529, "field 'jingSaiStartTvStartTime'");
    target.jingSaiStartTvStartTime = finder.castView(view, 2131427529, "field 'jingSaiStartTvStartTime'");
    view = finder.findRequiredView(source, 2131427530, "field 'jingSaiStartTvEndTime'");
    target.jingSaiStartTvEndTime = finder.castView(view, 2131427530, "field 'jingSaiStartTvEndTime'");
    view = finder.findRequiredView(source, 2131427531, "field 'jingSaiStartBtnStart' and method 'onClick'");
    target.jingSaiStartBtnStart = finder.castView(view, 2131427531, "field 'jingSaiStartBtnStart'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427528, "field 'jingSaiStartTvTitle'");
    target.jingSaiStartTvTitle = finder.castView(view, 2131427528, "field 'jingSaiStartTvTitle'");
  }

  @Override public void unbind(T target) {
    target.titleRightTextLlBack = null;
    target.titleRightTextTvTitleName = null;
    target.titleRightTextTvRightName = null;
    target.jingSaiStartTvStartTime = null;
    target.jingSaiStartTvEndTime = null;
    target.jingSaiStartBtnStart = null;
    target.jingSaiStartTvTitle = null;
  }
}
