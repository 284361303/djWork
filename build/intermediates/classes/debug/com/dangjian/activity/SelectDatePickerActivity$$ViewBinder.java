// Generated code from Butter Knife. Do not modify!
package com.dangjian.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SelectDatePickerActivity$$ViewBinder<T extends com.dangjian.activity.SelectDatePickerActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427630, "field 'dataPickerTvCancel' and method 'onClick'");
    target.dataPickerTvCancel = finder.castView(view, 2131427630, "field 'dataPickerTvCancel'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427631, "field 'dataPickerTvTrue' and method 'onClick'");
    target.dataPickerTvTrue = finder.castView(view, 2131427631, "field 'dataPickerTvTrue'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427634, "field 'dataPicker'");
    target.dataPicker = finder.castView(view, 2131427634, "field 'dataPicker'");
    view = finder.findRequiredView(source, 2131427632, "field 'selelctDateTvOne'");
    target.selelctDateTvOne = finder.castView(view, 2131427632, "field 'selelctDateTvOne'");
    view = finder.findRequiredView(source, 2131427633, "field 'selelctDateTvTwo'");
    target.selelctDateTvTwo = finder.castView(view, 2131427633, "field 'selelctDateTvTwo'");
  }

  @Override public void unbind(T target) {
    target.dataPickerTvCancel = null;
    target.dataPickerTvTrue = null;
    target.dataPicker = null;
    target.selelctDateTvOne = null;
    target.selelctDateTvTwo = null;
  }
}
