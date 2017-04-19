// Generated code from Butter Knife. Do not modify!
package com.dangjian.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ChangeUserActivity$$ViewBinder<T extends com.dangjian.activity.ChangeUserActivity> implements ViewBinder<T> {
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
    view = finder.findRequiredView(source, 2131427426, "field 'changeUserEtUserName'");
    target.changeUserEtUserName = finder.castView(view, 2131427426, "field 'changeUserEtUserName'");
    view = finder.findRequiredView(source, 2131427427, "field 'changeUserEtPwd'");
    target.changeUserEtPwd = finder.castView(view, 2131427427, "field 'changeUserEtPwd'");
    view = finder.findRequiredView(source, 2131427429, "field 'changeUserEtZhiBu'");
    target.changeUserEtZhiBu = finder.castView(view, 2131427429, "field 'changeUserEtZhiBu'");
    view = finder.findRequiredView(source, 2131427428, "field 'changeUserLlSelectZhiBu' and method 'onClick'");
    target.changeUserLlSelectZhiBu = finder.castView(view, 2131427428, "field 'changeUserLlSelectZhiBu'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427432, "field 'changeUserEtQuanXian'");
    target.changeUserEtQuanXian = finder.castView(view, 2131427432, "field 'changeUserEtQuanXian'");
    view = finder.findRequiredView(source, 2131427431, "field 'changeUserLlSelectQuanXian'");
    target.changeUserLlSelectQuanXian = finder.castView(view, 2131427431, "field 'changeUserLlSelectQuanXian'");
    view = finder.findRequiredView(source, 2131427430, "field 'changeUserLlQuanXian' and method 'onClick'");
    target.changeUserLlQuanXian = finder.castView(view, 2131427430, "field 'changeUserLlQuanXian'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427433, "field 'changeUserBtnCancel' and method 'onClick'");
    target.changeUserBtnCancel = finder.castView(view, 2131427433, "field 'changeUserBtnCancel'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427434, "field 'changeUserBtnAdd' and method 'onClick'");
    target.changeUserBtnAdd = finder.castView(view, 2131427434, "field 'changeUserBtnAdd'");
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
    target.changeUserEtUserName = null;
    target.changeUserEtPwd = null;
    target.changeUserEtZhiBu = null;
    target.changeUserLlSelectZhiBu = null;
    target.changeUserEtQuanXian = null;
    target.changeUserLlSelectQuanXian = null;
    target.changeUserLlQuanXian = null;
    target.changeUserBtnCancel = null;
    target.changeUserBtnAdd = null;
  }
}
