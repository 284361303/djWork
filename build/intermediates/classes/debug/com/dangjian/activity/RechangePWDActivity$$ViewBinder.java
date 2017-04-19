// Generated code from Butter Knife. Do not modify!
package com.dangjian.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class RechangePWDActivity$$ViewBinder<T extends com.dangjian.activity.RechangePWDActivity> implements ViewBinder<T> {
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
    view = finder.findRequiredView(source, 2131427606, "field 'rechangePWDEtOld'");
    target.rechangePWDEtOld = finder.castView(view, 2131427606, "field 'rechangePWDEtOld'");
    view = finder.findRequiredView(source, 2131427607, "field 'rechangePWDEtNew'");
    target.rechangePWDEtNew = finder.castView(view, 2131427607, "field 'rechangePWDEtNew'");
    view = finder.findRequiredView(source, 2131427608, "field 'rechangePWDEtNew2'");
    target.rechangePWDEtNew2 = finder.castView(view, 2131427608, "field 'rechangePWDEtNew2'");
    view = finder.findRequiredView(source, 2131427609, "field 'rechangePWDBtnSave' and method 'onClick'");
    target.rechangePWDBtnSave = finder.castView(view, 2131427609, "field 'rechangePWDBtnSave'");
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
    target.rechangePWDEtOld = null;
    target.rechangePWDEtNew = null;
    target.rechangePWDEtNew2 = null;
    target.rechangePWDBtnSave = null;
  }
}
