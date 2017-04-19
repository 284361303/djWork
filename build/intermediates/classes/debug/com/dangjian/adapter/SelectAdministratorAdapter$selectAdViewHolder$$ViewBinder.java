// Generated code from Butter Knife. Do not modify!
package com.dangjian.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SelectAdministratorAdapter$selectAdViewHolder$$ViewBinder<T extends com.dangjian.adapter.SelectAdministratorAdapter.selectAdViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427818, "field 'itemQuanXianTvName'");
    target.itemQuanXianTvName = finder.castView(view, 2131427818, "field 'itemQuanXianTvName'");
    view = finder.findRequiredView(source, 2131427820, "field 'itemQuanXianView'");
    target.itemQuanXianView = view;
    view = finder.findRequiredView(source, 2131427819, "field 'itemQuanXianIvStates'");
    target.itemQuanXianIvStates = finder.castView(view, 2131427819, "field 'itemQuanXianIvStates'");
  }

  @Override public void unbind(T target) {
    target.itemQuanXianTvName = null;
    target.itemQuanXianView = null;
    target.itemQuanXianIvStates = null;
  }
}
