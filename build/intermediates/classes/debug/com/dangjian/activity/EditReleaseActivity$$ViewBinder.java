// Generated code from Butter Knife. Do not modify!
package com.dangjian.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class EditReleaseActivity$$ViewBinder<T extends com.dangjian.activity.EditReleaseActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427484, "field 'editReleaseLlBack' and method 'onClick'");
    target.editReleaseLlBack = finder.castView(view, 2131427484, "field 'editReleaseLlBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427486, "field 'editReleaseTvTitleName'");
    target.editReleaseTvTitleName = finder.castView(view, 2131427486, "field 'editReleaseTvTitleName'");
    view = finder.findRequiredView(source, 2131427485, "field 'editReleaseLlTitleSelect' and method 'onClick'");
    target.editReleaseLlTitleSelect = finder.castView(view, 2131427485, "field 'editReleaseLlTitleSelect'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427487, "field 'editReleaseEtTitle'");
    target.editReleaseEtTitle = finder.castView(view, 2131427487, "field 'editReleaseEtTitle'");
    view = finder.findRequiredView(source, 2131427488, "field 'editReleaseEtContent'");
    target.editReleaseEtContent = finder.castView(view, 2131427488, "field 'editReleaseEtContent'");
    view = finder.findRequiredView(source, 2131427490, "field 'editReleaseGridView'");
    target.editReleaseGridView = finder.castView(view, 2131427490, "field 'editReleaseGridView'");
    view = finder.findRequiredView(source, 2131427491, "field 'editReleaseBtnDetele' and method 'onClick'");
    target.editReleaseBtnDetele = finder.castView(view, 2131427491, "field 'editReleaseBtnDetele'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427492, "field 'editReleaseBtnSave' and method 'onClick'");
    target.editReleaseBtnSave = finder.castView(view, 2131427492, "field 'editReleaseBtnSave'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427489, "field 'editReleaseLlGridView'");
    target.editReleaseLlGridView = finder.castView(view, 2131427489, "field 'editReleaseLlGridView'");
  }

  @Override public void unbind(T target) {
    target.editReleaseLlBack = null;
    target.editReleaseTvTitleName = null;
    target.editReleaseLlTitleSelect = null;
    target.editReleaseEtTitle = null;
    target.editReleaseEtContent = null;
    target.editReleaseGridView = null;
    target.editReleaseBtnDetele = null;
    target.editReleaseBtnSave = null;
    target.editReleaseLlGridView = null;
  }
}
