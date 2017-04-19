// Generated code from Butter Knife. Do not modify!
package com.dangjian.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class OrganizationManagerAdapter$zuZhiViewHolder$$ViewBinder<T extends com.dangjian.adapter.OrganizationManagerAdapter.zuZhiViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427806, "field 'itemOrganizationUserPic'");
    target.itemOrganizationUserPic = finder.castView(view, 2131427806, "field 'itemOrganizationUserPic'");
    view = finder.findRequiredView(source, 2131427807, "field 'itemOrganizationTvName'");
    target.itemOrganizationTvName = finder.castView(view, 2131427807, "field 'itemOrganizationTvName'");
    view = finder.findRequiredView(source, 2131427808, "field 'itemOrganizationTvSex'");
    target.itemOrganizationTvSex = finder.castView(view, 2131427808, "field 'itemOrganizationTvSex'");
    view = finder.findRequiredView(source, 2131427809, "field 'itemOrganizationTvMinZu'");
    target.itemOrganizationTvMinZu = finder.castView(view, 2131427809, "field 'itemOrganizationTvMinZu'");
    view = finder.findRequiredView(source, 2131427810, "field 'itemOrganizationTvTime'");
    target.itemOrganizationTvTime = finder.castView(view, 2131427810, "field 'itemOrganizationTvTime'");
  }

  @Override public void unbind(T target) {
    target.itemOrganizationUserPic = null;
    target.itemOrganizationTvName = null;
    target.itemOrganizationTvSex = null;
    target.itemOrganizationTvMinZu = null;
    target.itemOrganizationTvTime = null;
  }
}
