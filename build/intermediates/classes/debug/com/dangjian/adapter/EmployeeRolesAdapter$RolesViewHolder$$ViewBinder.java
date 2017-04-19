// Generated code from Butter Knife. Do not modify!
package com.dangjian.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class EmployeeRolesAdapter$RolesViewHolder$$ViewBinder<T extends com.dangjian.adapter.EmployeeRolesAdapter.RolesViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427830, "field 'itemSelectadministratorTvName'");
    target.itemSelectadministratorTvName = finder.castView(view, 2131427830, "field 'itemSelectadministratorTvName'");
    view = finder.findRequiredView(source, 2131427831, "field 'itemSelectadministratorView'");
    target.itemSelectadministratorView = view;
  }

  @Override public void unbind(T target) {
    target.itemSelectadministratorTvName = null;
    target.itemSelectadministratorView = null;
  }
}
