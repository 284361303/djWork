// Generated code from Butter Knife. Do not modify!
package com.dangjian.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AddUserActivity$$ViewBinder<T extends com.dangjian.activity.AddUserActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427759, "field 'titleOneTextTvTitleName'");
    target.titleOneTextTvTitleName = finder.castView(view, 2131427759, "field 'titleOneTextTvTitleName'");
    view = finder.findRequiredView(source, 2131427416, "field 'addUserEtUserName'");
    target.addUserEtUserName = finder.castView(view, 2131427416, "field 'addUserEtUserName'");
    view = finder.findRequiredView(source, 2131427417, "field 'addUserEtPwd'");
    target.addUserEtPwd = finder.castView(view, 2131427417, "field 'addUserEtPwd'");
    view = finder.findRequiredView(source, 2131427419, "field 'addUserEtZhiBu'");
    target.addUserEtZhiBu = finder.castView(view, 2131427419, "field 'addUserEtZhiBu'");
    view = finder.findRequiredView(source, 2131427422, "field 'addUserEtQuanXian'");
    target.addUserEtQuanXian = finder.castView(view, 2131427422, "field 'addUserEtQuanXian'");
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
    view = finder.findRequiredView(source, 2131427418, "field 'addUserLlSelectZhiBu' and method 'onClick'");
    target.addUserLlSelectZhiBu = finder.castView(view, 2131427418, "field 'addUserLlSelectZhiBu'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427421, "field 'addUserLlSelectQuanXian' and method 'onClick'");
    target.addUserLlSelectQuanXian = finder.castView(view, 2131427421, "field 'addUserLlSelectQuanXian'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427420, "field 'addUserLlQuanXian'");
    target.addUserLlQuanXian = finder.castView(view, 2131427420, "field 'addUserLlQuanXian'");
    view = finder.findRequiredView(source, 2131427423, "field 'addUserBtnAdd' and method 'onClick'");
    target.addUserBtnAdd = finder.castView(view, 2131427423, "field 'addUserBtnAdd'");
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
    target.titleOneTextTvTitleName = null;
    target.addUserEtUserName = null;
    target.addUserEtPwd = null;
    target.addUserEtZhiBu = null;
    target.addUserEtQuanXian = null;
    target.titleOneTextLlBack = null;
    target.addUserLlSelectZhiBu = null;
    target.addUserLlSelectQuanXian = null;
    target.addUserLlQuanXian = null;
    target.addUserBtnAdd = null;
  }
}
