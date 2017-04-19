// Generated code from Butter Knife. Do not modify!
package com.dangjian.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SelectAdministratorActivity$$ViewBinder<T extends com.dangjian.activity.SelectAdministratorActivity> implements ViewBinder<T> {
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
    view = finder.findRequiredView(source, 2131427628, "field 'selectAdministratorRecyclerView'");
    target.selectAdministratorRecyclerView = finder.castView(view, 2131427628, "field 'selectAdministratorRecyclerView'");
    view = finder.findRequiredView(source, 2131427629, "field 'selectAdministratorBtnTrue' and method 'onClick'");
    target.selectAdministratorBtnTrue = finder.castView(view, 2131427629, "field 'selectAdministratorBtnTrue'");
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
    target.selectAdministratorRecyclerView = null;
    target.selectAdministratorBtnTrue = null;
  }
}
