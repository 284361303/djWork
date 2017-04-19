// Generated code from Butter Knife. Do not modify!
package com.dangjian.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ChatAdapter$chatViewHolder$$ViewBinder<T extends com.dangjian.adapter.ChatAdapter.chatViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427763, "field 'chatUserPic'");
    target.chatUserPic = finder.castView(view, 2131427763, "field 'chatUserPic'");
    view = finder.findRequiredView(source, 2131427764, "field 'chatTvName'");
    target.chatTvName = finder.castView(view, 2131427764, "field 'chatTvName'");
    view = finder.findRequiredView(source, 2131427765, "field 'chatTvTitle'");
    target.chatTvTitle = finder.castView(view, 2131427765, "field 'chatTvTitle'");
    view = finder.findRequiredView(source, 2131427766, "field 'chatTvDate'");
    target.chatTvDate = finder.castView(view, 2131427766, "field 'chatTvDate'");
    view = finder.findRequiredView(source, 2131427767, "field 'chatTvSum'");
    target.chatTvSum = finder.castView(view, 2131427767, "field 'chatTvSum'");
    view = finder.findRequiredView(source, 2131427768, "field 'chatIvRight'");
    target.chatIvRight = finder.castView(view, 2131427768, "field 'chatIvRight'");
    view = finder.findRequiredView(source, 2131427769, "field 'chatTvCollectionSum'");
    target.chatTvCollectionSum = finder.castView(view, 2131427769, "field 'chatTvCollectionSum'");
  }

  @Override public void unbind(T target) {
    target.chatUserPic = null;
    target.chatTvName = null;
    target.chatTvTitle = null;
    target.chatTvDate = null;
    target.chatTvSum = null;
    target.chatIvRight = null;
    target.chatTvCollectionSum = null;
  }
}
