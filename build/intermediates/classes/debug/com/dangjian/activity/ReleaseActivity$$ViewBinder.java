// Generated code from Butter Knife. Do not modify!
package com.dangjian.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ReleaseActivity$$ViewBinder<T extends com.dangjian.activity.ReleaseActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427610, "field 'releaseLlBack' and method 'onClick'");
    target.releaseLlBack = finder.castView(view, 2131427610, "field 'releaseLlBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427612, "field 'releaseTvTitleName'");
    target.releaseTvTitleName = finder.castView(view, 2131427612, "field 'releaseTvTitleName'");
    view = finder.findRequiredView(source, 2131427611, "field 'releaseLlTitleSelect' and method 'onClick'");
    target.releaseLlTitleSelect = finder.castView(view, 2131427611, "field 'releaseLlTitleSelect'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427613, "field 'releaseEtTitle'");
    target.releaseEtTitle = finder.castView(view, 2131427613, "field 'releaseEtTitle'");
    view = finder.findRequiredView(source, 2131427615, "field 'releaseEtRole'");
    target.releaseEtRole = finder.castView(view, 2131427615, "field 'releaseEtRole'");
    view = finder.findRequiredView(source, 2131427614, "field 'releaseLlSelect' and method 'onClick'");
    target.releaseLlSelect = finder.castView(view, 2131427614, "field 'releaseLlSelect'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427616, "field 'releaseEtContent'");
    target.releaseEtContent = finder.castView(view, 2131427616, "field 'releaseEtContent'");
    view = finder.findRequiredView(source, 2131427617, "field 'releaseBtnCancel' and method 'onClick'");
    target.releaseBtnCancel = finder.castView(view, 2131427617, "field 'releaseBtnCancel'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427618, "field 'releaseBtnSave' and method 'onClick'");
    target.releaseBtnSave = finder.castView(view, 2131427618, "field 'releaseBtnSave'");
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
    target.releaseLlBack = null;
    target.releaseTvTitleName = null;
    target.releaseLlTitleSelect = null;
    target.releaseEtTitle = null;
    target.releaseEtRole = null;
    target.releaseLlSelect = null;
    target.releaseEtContent = null;
    target.releaseBtnCancel = null;
    target.releaseBtnSave = null;
  }
}
