// Generated code from Butter Knife. Do not modify!
package com.dangjian.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AboutUsAvtivity$$ViewBinder<T extends com.dangjian.activity.AboutUsAvtivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427758, "field 'titleOneTextLlBack' and method 'onClick'");
    target.titleOneTextLlBack = finder.castView(view, 2131427758, "field 'titleOneTextLlBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick();
        }
      });
    view = finder.findRequiredView(source, 2131427759, "field 'titleOneTextTvTitleName'");
    target.titleOneTextTvTitleName = finder.castView(view, 2131427759, "field 'titleOneTextTvTitleName'");
    view = finder.findRequiredView(source, 2131427414, "field 'aboutUsTvVersion'");
    target.aboutUsTvVersion = finder.castView(view, 2131427414, "field 'aboutUsTvVersion'");
    view = finder.findRequiredView(source, 2131427415, "field 'aboutUsIvDowloadCode'");
    target.aboutUsIvDowloadCode = finder.castView(view, 2131427415, "field 'aboutUsIvDowloadCode'");
  }

  @Override public void unbind(T target) {
    target.titleOneTextLlBack = null;
    target.titleOneTextTvTitleName = null;
    target.aboutUsTvVersion = null;
    target.aboutUsIvDowloadCode = null;
  }
}
