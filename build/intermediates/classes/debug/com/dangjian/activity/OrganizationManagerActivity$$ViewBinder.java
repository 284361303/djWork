// Generated code from Butter Knife. Do not modify!
package com.dangjian.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class OrganizationManagerActivity$$ViewBinder<T extends com.dangjian.activity.OrganizationManagerActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427592, "field 'OrganizationMTvTitleName'");
    target.OrganizationMTvTitleName = finder.castView(view, 2131427592, "field 'OrganizationMTvTitleName'");
    view = finder.findRequiredView(source, 2131427590, "field 'OrganizationMLlBack' and method 'onClick'");
    target.OrganizationMLlBack = finder.castView(view, 2131427590, "field 'OrganizationMLlBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427591, "field 'OrganizationMLlTitleName' and method 'onClick'");
    target.OrganizationMLlTitleName = finder.castView(view, 2131427591, "field 'OrganizationMLlTitleName'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427594, "field 'OrganizationMTvAdd' and method 'onClick'");
    target.OrganizationMTvAdd = finder.castView(view, 2131427594, "field 'OrganizationMTvAdd'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427756, "field 'emptyLoadingLayoutIv'");
    target.emptyLoadingLayoutIv = finder.castView(view, 2131427756, "field 'emptyLoadingLayoutIv'");
    view = finder.findRequiredView(source, 2131427757, "field 'emptyLoadingLayoutTvName'");
    target.emptyLoadingLayoutTvName = finder.castView(view, 2131427757, "field 'emptyLoadingLayoutTvName'");
    view = finder.findRequiredView(source, 2131427755, "field 'emptyLoadingLayoutLl'");
    target.emptyLoadingLayoutLl = finder.castView(view, 2131427755, "field 'emptyLoadingLayoutLl'");
    view = finder.findRequiredView(source, 2131427596, "field 'OrganizationMRecyclerView'");
    target.OrganizationMRecyclerView = finder.castView(view, 2131427596, "field 'OrganizationMRecyclerView'");
    view = finder.findRequiredView(source, 2131427595, "field 'OrganizationMRefresh'");
    target.OrganizationMRefresh = finder.castView(view, 2131427595, "field 'OrganizationMRefresh'");
    view = finder.findRequiredView(source, 2131427589, "field 'OrganizationMRlAll'");
    target.OrganizationMRlAll = finder.castView(view, 2131427589, "field 'OrganizationMRlAll'");
    view = finder.findRequiredView(source, 2131427593, "field 'OrganizationMIvDown'");
    target.OrganizationMIvDown = finder.castView(view, 2131427593, "field 'OrganizationMIvDown'");
  }

  @Override public void unbind(T target) {
    target.OrganizationMTvTitleName = null;
    target.OrganizationMLlBack = null;
    target.OrganizationMLlTitleName = null;
    target.OrganizationMTvAdd = null;
    target.emptyLoadingLayoutIv = null;
    target.emptyLoadingLayoutTvName = null;
    target.emptyLoadingLayoutLl = null;
    target.OrganizationMRecyclerView = null;
    target.OrganizationMRefresh = null;
    target.OrganizationMRlAll = null;
    target.OrganizationMIvDown = null;
  }
}
