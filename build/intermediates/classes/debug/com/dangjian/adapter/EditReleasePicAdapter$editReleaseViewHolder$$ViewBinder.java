// Generated code from Butter Knife. Do not modify!
package com.dangjian.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class EditReleasePicAdapter$editReleaseViewHolder$$ViewBinder<T extends com.dangjian.adapter.EditReleasePicAdapter.editReleaseViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427770, "field 'itemEditPicIv'");
    target.itemEditPicIv = finder.castView(view, 2131427770, "field 'itemEditPicIv'");
    view = finder.findRequiredView(source, 2131427771, "field 'itemEditPicIvDetele'");
    target.itemEditPicIvDetele = finder.castView(view, 2131427771, "field 'itemEditPicIvDetele'");
  }

  @Override public void unbind(T target) {
    target.itemEditPicIv = null;
    target.itemEditPicIvDetele = null;
  }
}
