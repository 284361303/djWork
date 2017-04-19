// Generated code from Butter Knife. Do not modify!
package com.dangjian.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ChatTabCFragment$$ViewBinder<T extends com.dangjian.fragments.ChatTabCFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427749, "field 'chatTabCWebView'");
    target.chatTabCWebView = finder.castView(view, 2131427749, "field 'chatTabCWebView'");
    view = finder.findRequiredView(source, 2131427748, "field 'chatTabCRefresh'");
    target.chatTabCRefresh = finder.castView(view, 2131427748, "field 'chatTabCRefresh'");
  }

  @Override public void unbind(T target) {
    target.chatTabCWebView = null;
    target.chatTabCRefresh = null;
  }
}
