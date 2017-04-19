// Generated code from Butter Knife. Do not modify!
package com.dangjian.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class XueXiZiLiaoActivity$$ViewBinder<T extends com.dangjian.activity.XueXiZiLiaoActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427760, "field 'titleRightTextLlBack' and method 'onClick'");
    target.titleRightTextLlBack = finder.castView(view, 2131427760, "field 'titleRightTextLlBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427761, "field 'titleRightTextTvTitleName'");
    target.titleRightTextTvTitleName = finder.castView(view, 2131427761, "field 'titleRightTextTvTitleName'");
    view = finder.findRequiredView(source, 2131427762, "field 'titleRightTextTvRightName' and method 'onClick'");
    target.titleRightTextTvRightName = finder.castView(view, 2131427762, "field 'titleRightTextTvRightName'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427718, "field 'ziliaoTvA'");
    target.ziliaoTvA = finder.castView(view, 2131427718, "field 'ziliaoTvA'");
    view = finder.findRequiredView(source, 2131427719, "field 'ziliaoViewA'");
    target.ziliaoViewA = view;
    view = finder.findRequiredView(source, 2131427717, "field 'ziliaoLlA' and method 'onClick'");
    target.ziliaoLlA = finder.castView(view, 2131427717, "field 'ziliaoLlA'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427721, "field 'ziliaoTvB'");
    target.ziliaoTvB = finder.castView(view, 2131427721, "field 'ziliaoTvB'");
    view = finder.findRequiredView(source, 2131427722, "field 'ziliaoViewB'");
    target.ziliaoViewB = view;
    view = finder.findRequiredView(source, 2131427720, "field 'ziliaoLlB' and method 'onClick'");
    target.ziliaoLlB = finder.castView(view, 2131427720, "field 'ziliaoLlB'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427724, "field 'ziliaoTvC'");
    target.ziliaoTvC = finder.castView(view, 2131427724, "field 'ziliaoTvC'");
    view = finder.findRequiredView(source, 2131427725, "field 'ziliaoViewC'");
    target.ziliaoViewC = view;
    view = finder.findRequiredView(source, 2131427723, "field 'ziliaoLlC' and method 'onClick'");
    target.ziliaoLlC = finder.castView(view, 2131427723, "field 'ziliaoLlC'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427726, "field 'ziliaoLlAll'");
    target.ziliaoLlAll = finder.castView(view, 2131427726, "field 'ziliaoLlAll'");
  }

  @Override public void unbind(T target) {
    target.titleRightTextLlBack = null;
    target.titleRightTextTvTitleName = null;
    target.titleRightTextTvRightName = null;
    target.ziliaoTvA = null;
    target.ziliaoViewA = null;
    target.ziliaoLlA = null;
    target.ziliaoTvB = null;
    target.ziliaoViewB = null;
    target.ziliaoLlB = null;
    target.ziliaoTvC = null;
    target.ziliaoViewC = null;
    target.ziliaoLlC = null;
    target.ziliaoLlAll = null;
  }
}
