// Generated code from Butter Knife. Do not modify!
package com.dangjian.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LoginActivity$$ViewBinder<T extends com.dangjian.activity.LoginActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427545, "field 'loginEtAccount'");
    target.loginEtAccount = finder.castView(view, 2131427545, "field 'loginEtAccount'");
    view = finder.findRequiredView(source, 2131427546, "field 'loginEtPWD'");
    target.loginEtPWD = finder.castView(view, 2131427546, "field 'loginEtPWD'");
    view = finder.findRequiredView(source, 2131427547, "field 'loginBtnLogin' and method 'onClick'");
    target.loginBtnLogin = finder.castView(view, 2131427547, "field 'loginBtnLogin'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427549, "field 'loginIvType' and method 'onClick'");
    target.loginIvType = finder.castView(view, 2131427549, "field 'loginIvType'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427548, "field 'loginLlType' and method 'onClick'");
    target.loginLlType = finder.castView(view, 2131427548, "field 'loginLlType'");
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
    target.loginEtAccount = null;
    target.loginEtPWD = null;
    target.loginBtnLogin = null;
    target.loginIvType = null;
    target.loginLlType = null;
  }
}
