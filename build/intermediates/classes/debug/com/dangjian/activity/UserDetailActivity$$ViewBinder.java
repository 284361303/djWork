// Generated code from Butter Knife. Do not modify!
package com.dangjian.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class UserDetailActivity$$ViewBinder<T extends com.dangjian.activity.UserDetailActivity> implements ViewBinder<T> {
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
    view = finder.findRequiredView(source, 2131427669, "field 'userDetailLlRechangePic' and method 'onClick'");
    target.userDetailLlRechangePic = finder.castView(view, 2131427669, "field 'userDetailLlRechangePic'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427671, "field 'userDetailTvUserName'");
    target.userDetailTvUserName = finder.castView(view, 2131427671, "field 'userDetailTvUserName'");
    view = finder.findRequiredView(source, 2131427672, "field 'userDetailTvZhiBu'");
    target.userDetailTvZhiBu = finder.castView(view, 2131427672, "field 'userDetailTvZhiBu'");
    view = finder.findRequiredView(source, 2131427676, "field 'userDetailTvSex'");
    target.userDetailTvSex = finder.castView(view, 2131427676, "field 'userDetailTvSex'");
    view = finder.findRequiredView(source, 2131427675, "field 'userDetailLlSex' and method 'onClick'");
    target.userDetailLlSex = finder.castView(view, 2131427675, "field 'userDetailLlSex'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427678, "field 'userDetailTvBirth'");
    target.userDetailTvBirth = finder.castView(view, 2131427678, "field 'userDetailTvBirth'");
    view = finder.findRequiredView(source, 2131427677, "field 'userDetailLlBirth' and method 'onClick'");
    target.userDetailLlBirth = finder.castView(view, 2131427677, "field 'userDetailLlBirth'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427680, "field 'userDetailTvNation'");
    target.userDetailTvNation = finder.castView(view, 2131427680, "field 'userDetailTvNation'");
    view = finder.findRequiredView(source, 2131427679, "field 'userDetailLlNation' and method 'onClick'");
    target.userDetailLlNation = finder.castView(view, 2131427679, "field 'userDetailLlNation'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427682, "field 'userDetailTvInTime'");
    target.userDetailTvInTime = finder.castView(view, 2131427682, "field 'userDetailTvInTime'");
    view = finder.findRequiredView(source, 2131427681, "field 'userDetailLlInTime' and method 'onClick'");
    target.userDetailLlInTime = finder.castView(view, 2131427681, "field 'userDetailLlInTime'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427670, "field 'userDetailIvPic'");
    target.userDetailIvPic = finder.castView(view, 2131427670, "field 'userDetailIvPic'");
    view = finder.findRequiredView(source, 2131427674, "field 'userDetailTvNickName'");
    target.userDetailTvNickName = finder.castView(view, 2131427674, "field 'userDetailTvNickName'");
    view = finder.findRequiredView(source, 2131427673, "field 'userDetailLlNickName' and method 'onClick'");
    target.userDetailLlNickName = finder.castView(view, 2131427673, "field 'userDetailLlNickName'");
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
    target.userDetailLlRechangePic = null;
    target.userDetailTvUserName = null;
    target.userDetailTvZhiBu = null;
    target.userDetailTvSex = null;
    target.userDetailLlSex = null;
    target.userDetailTvBirth = null;
    target.userDetailLlBirth = null;
    target.userDetailTvNation = null;
    target.userDetailLlNation = null;
    target.userDetailTvInTime = null;
    target.userDetailLlInTime = null;
    target.userDetailIvPic = null;
    target.userDetailTvNickName = null;
    target.userDetailLlNickName = null;
  }
}
