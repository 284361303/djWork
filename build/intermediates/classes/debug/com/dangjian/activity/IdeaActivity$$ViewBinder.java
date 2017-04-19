// Generated code from Butter Knife. Do not modify!
package com.dangjian.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class IdeaActivity$$ViewBinder<T extends com.dangjian.activity.IdeaActivity> implements ViewBinder<T> {
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
    view = finder.findRequiredView(source, 2131427515, "field 'ideaEt'");
    target.ideaEt = finder.castView(view, 2131427515, "field 'ideaEt'");
    view = finder.findRequiredView(source, 2131427516, "field 'ideaTvSum'");
    target.ideaTvSum = finder.castView(view, 2131427516, "field 'ideaTvSum'");
    view = finder.findRequiredView(source, 2131427517, "field 'ideaBtnSubmit' and method 'onClick'");
    target.ideaBtnSubmit = finder.castView(view, 2131427517, "field 'ideaBtnSubmit'");
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
    target.ideaEt = null;
    target.ideaTvSum = null;
    target.ideaBtnSubmit = null;
  }
}
