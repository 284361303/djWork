// Generated code from Butter Knife. Do not modify!
package com.dangjian.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class PicAdapter$picViewHolder$$ViewBinder<T extends com.dangjian.adapter.PicAdapter.picViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427815, "field 'itemPicIv'");
    target.itemPicIv = finder.castView(view, 2131427815, "field 'itemPicIv'");
  }

  @Override public void unbind(T target) {
    target.itemPicIv = null;
  }
}
