// Generated code from Butter Knife. Do not modify!
package com.dangjian.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FunctionActivity$$ViewBinder<T extends com.dangjian.activity.FunctionActivity> implements ViewBinder<T> {
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
    view = finder.findRequiredView(source, 2131427513, "method 'onClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427514, "method 'onClick'");
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
    target.titleOneTextLlBack = null;
    target.titleOneTextTvTitleName = null;
  }
}
