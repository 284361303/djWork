// Generated code from Butter Knife. Do not modify!
package com.dangjian.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ReleaseContainPicActivity$$ViewBinder<T extends com.dangjian.activity.ReleaseContainPicActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427619, "field 'releasePicLlBack' and method 'onClick'");
    target.releasePicLlBack = finder.castView(view, 2131427619, "field 'releasePicLlBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427621, "field 'releasePicTvTitleName'");
    target.releasePicTvTitleName = finder.castView(view, 2131427621, "field 'releasePicTvTitleName'");
    view = finder.findRequiredView(source, 2131427620, "field 'releasePicLlTitleSelect' and method 'onClick'");
    target.releasePicLlTitleSelect = finder.castView(view, 2131427620, "field 'releasePicLlTitleSelect'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427623, "field 'releasePicEtTitle'");
    target.releasePicEtTitle = finder.castView(view, 2131427623, "field 'releasePicEtTitle'");
    view = finder.findRequiredView(source, 2131427624, "field 'releasePicEtContent'");
    target.releasePicEtContent = finder.castView(view, 2131427624, "field 'releasePicEtContent'");
    view = finder.findRequiredView(source, 2131427625, "field 'releasePicGridView'");
    target.releasePicGridView = finder.castView(view, 2131427625, "field 'releasePicGridView'");
    view = finder.findRequiredView(source, 2131427626, "field 'releasePicBtnCancel' and method 'onClick'");
    target.releasePicBtnCancel = finder.castView(view, 2131427626, "field 'releasePicBtnCancel'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427627, "field 'releasePicBtnSave' and method 'onClick'");
    target.releasePicBtnSave = finder.castView(view, 2131427627, "field 'releasePicBtnSave'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427622, "field 'releasePicIvDown'");
    target.releasePicIvDown = finder.castView(view, 2131427622, "field 'releasePicIvDown'");
  }

  @Override public void unbind(T target) {
    target.releasePicLlBack = null;
    target.releasePicTvTitleName = null;
    target.releasePicLlTitleSelect = null;
    target.releasePicEtTitle = null;
    target.releasePicEtContent = null;
    target.releasePicGridView = null;
    target.releasePicBtnCancel = null;
    target.releasePicBtnSave = null;
    target.releasePicIvDown = null;
  }
}
