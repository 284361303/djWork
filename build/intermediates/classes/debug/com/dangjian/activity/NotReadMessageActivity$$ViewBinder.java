// Generated code from Butter Knife. Do not modify!
package com.dangjian.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class NotReadMessageActivity$$ViewBinder<T extends com.dangjian.activity.NotReadMessageActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427587, "field 'notReadMessageWebView'");
    target.notReadMessageWebView = finder.castView(view, 2131427587, "field 'notReadMessageWebView'");
    view = finder.findRequiredView(source, 2131427588, "field 'notReadMessageBtnClose' and method 'onClick'");
    target.notReadMessageBtnClose = finder.castView(view, 2131427588, "field 'notReadMessageBtnClose'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick();
        }
      });
  }

  @Override public void unbind(T target) {
    target.notReadMessageWebView = null;
    target.notReadMessageBtnClose = null;
  }
}
