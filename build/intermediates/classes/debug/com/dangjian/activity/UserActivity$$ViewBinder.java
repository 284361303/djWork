// Generated code from Butter Knife. Do not modify!
package com.dangjian.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class UserActivity$$ViewBinder<T extends com.dangjian.activity.UserActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427656, "field 'userLlBack' and method 'onClick'");
    target.userLlBack = finder.castView(view, 2131427656, "field 'userLlBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427657, "field 'userUserPic'");
    target.userUserPic = finder.castView(view, 2131427657, "field 'userUserPic'");
    view = finder.findRequiredView(source, 2131427660, "field 'userTvCurrentSum'");
    target.userTvCurrentSum = finder.castView(view, 2131427660, "field 'userTvCurrentSum'");
    view = finder.findRequiredView(source, 2131427661, "field 'userTvAllSum'");
    target.userTvAllSum = finder.castView(view, 2131427661, "field 'userTvAllSum'");
    view = finder.findRequiredView(source, 2131427662, "field 'userTvLevel'");
    target.userTvLevel = finder.castView(view, 2131427662, "field 'userTvLevel'");
    view = finder.findRequiredView(source, 2131427663, "field 'userLlMyCollect' and method 'onClick'");
    target.userLlMyCollect = finder.castView(view, 2131427663, "field 'userLlMyCollect'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427664, "field 'userLlCounter' and method 'onClick'");
    target.userLlCounter = finder.castView(view, 2131427664, "field 'userLlCounter'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427665, "field 'userLlList' and method 'onClick'");
    target.userLlList = finder.castView(view, 2131427665, "field 'userLlList'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427668, "field 'userLlSet' and method 'onClick'");
    target.userLlSet = finder.castView(view, 2131427668, "field 'userLlSet'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427658, "field 'userTvUserName'");
    target.userTvUserName = finder.castView(view, 2131427658, "field 'userTvUserName'");
    view = finder.findRequiredView(source, 2131427659, "field 'userTvUserZhiBu'");
    target.userTvUserZhiBu = finder.castView(view, 2131427659, "field 'userTvUserZhiBu'");
    view = finder.findRequiredView(source, 2131427666, "method 'onClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427667, "method 'onClick'");
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
    target.userLlBack = null;
    target.userUserPic = null;
    target.userTvCurrentSum = null;
    target.userTvAllSum = null;
    target.userTvLevel = null;
    target.userLlMyCollect = null;
    target.userLlCounter = null;
    target.userLlList = null;
    target.userLlSet = null;
    target.userTvUserName = null;
    target.userTvUserZhiBu = null;
  }
}
