// Generated code from Butter Knife. Do not modify!
package com.dangjian.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SettingShowPicActivity$$ViewBinder<T extends com.dangjian.activity.SettingShowPicActivity> implements ViewBinder<T> {
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
    view = finder.findRequiredView(source, 2131427643, "field 'showPicIvAll'");
    target.showPicIvAll = finder.castView(view, 2131427643, "field 'showPicIvAll'");
    view = finder.findRequiredView(source, 2131427642, "field 'showPicLlAll' and method 'onClick'");
    target.showPicLlAll = finder.castView(view, 2131427642, "field 'showPicLlAll'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427645, "field 'showPicIvWIFI'");
    target.showPicIvWIFI = finder.castView(view, 2131427645, "field 'showPicIvWIFI'");
    view = finder.findRequiredView(source, 2131427644, "field 'showPicLlWIFI' and method 'onClick'");
    target.showPicLlWIFI = finder.castView(view, 2131427644, "field 'showPicLlWIFI'");
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
    target.showPicIvAll = null;
    target.showPicLlAll = null;
    target.showPicIvWIFI = null;
    target.showPicLlWIFI = null;
  }
}
