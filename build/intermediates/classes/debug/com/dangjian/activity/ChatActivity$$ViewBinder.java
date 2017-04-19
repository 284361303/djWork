// Generated code from Butter Knife. Do not modify!
package com.dangjian.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ChatActivity$$ViewBinder<T extends com.dangjian.activity.ChatActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427760, "field 'titleRightTextLlBack' and method 'onClick'");
    target.titleRightTextLlBack = finder.castView(view, 2131427760, "field 'titleRightTextLlBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427761, "field 'titleRightTextTvTitleName'");
    target.titleRightTextTvTitleName = finder.castView(view, 2131427761, "field 'titleRightTextTvTitleName'");
    view = finder.findRequiredView(source, 2131427762, "field 'titleRightTextTvRightName' and method 'onClick'");
    target.titleRightTextTvRightName = finder.castView(view, 2131427762, "field 'titleRightTextTvRightName'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427436, "field 'chatTvA'");
    target.chatTvA = finder.castView(view, 2131427436, "field 'chatTvA'");
    view = finder.findRequiredView(source, 2131427437, "field 'chatViewA'");
    target.chatViewA = view;
    view = finder.findRequiredView(source, 2131427435, "field 'chatLlA' and method 'onClick'");
    target.chatLlA = finder.castView(view, 2131427435, "field 'chatLlA'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427439, "field 'chatTvB'");
    target.chatTvB = finder.castView(view, 2131427439, "field 'chatTvB'");
    view = finder.findRequiredView(source, 2131427440, "field 'chatViewB'");
    target.chatViewB = view;
    view = finder.findRequiredView(source, 2131427438, "field 'chatLlB' and method 'onClick'");
    target.chatLlB = finder.castView(view, 2131427438, "field 'chatLlB'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427442, "field 'chatTvC'");
    target.chatTvC = finder.castView(view, 2131427442, "field 'chatTvC'");
    view = finder.findRequiredView(source, 2131427443, "field 'chatViewC'");
    target.chatViewC = view;
    view = finder.findRequiredView(source, 2131427441, "field 'chatLlC' and method 'onClick'");
    target.chatLlC = finder.castView(view, 2131427441, "field 'chatLlC'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427444, "field 'chatLlAll'");
    target.chatLlAll = finder.castView(view, 2131427444, "field 'chatLlAll'");
  }

  @Override public void unbind(T target) {
    target.titleRightTextLlBack = null;
    target.titleRightTextTvTitleName = null;
    target.titleRightTextTvRightName = null;
    target.chatTvA = null;
    target.chatViewA = null;
    target.chatLlA = null;
    target.chatTvB = null;
    target.chatViewB = null;
    target.chatLlB = null;
    target.chatTvC = null;
    target.chatViewC = null;
    target.chatLlC = null;
    target.chatLlAll = null;
  }
}
