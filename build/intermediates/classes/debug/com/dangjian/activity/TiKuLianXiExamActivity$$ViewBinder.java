// Generated code from Butter Knife. Do not modify!
package com.dangjian.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TiKuLianXiExamActivity$$ViewBinder<T extends com.dangjian.activity.TiKuLianXiExamActivity> implements ViewBinder<T> {
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
    view = finder.findRequiredView(source, 2131427649, "field 'tklxRecyclerView'");
    target.tklxRecyclerView = finder.castView(view, 2131427649, "field 'tklxRecyclerView'");
    view = finder.findRequiredView(source, 2131427653, "field 'tklxBtnDown' and method 'onClick'");
    target.tklxBtnDown = finder.castView(view, 2131427653, "field 'tklxBtnDown'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427651, "field 'tklxTvLookAnswer' and method 'onClick'");
    target.tklxTvLookAnswer = finder.castView(view, 2131427651, "field 'tklxTvLookAnswer'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427650, "field 'tklxBtnTest' and method 'onClick'");
    target.tklxBtnTest = finder.castView(view, 2131427650, "field 'tklxBtnTest'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427646, "field 'tklxLlContent'");
    target.tklxLlContent = finder.castView(view, 2131427646, "field 'tklxLlContent'");
    view = finder.findRequiredView(source, 2131427652, "field 'tklxBtnUp' and method 'onClick'");
    target.tklxBtnUp = finder.castView(view, 2131427652, "field 'tklxBtnUp'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427647, "field 'tklxTvSequence'");
    target.tklxTvSequence = finder.castView(view, 2131427647, "field 'tklxTvSequence'");
    view = finder.findRequiredView(source, 2131427648, "field 'tklxTvTitleName'");
    target.tklxTvTitleName = finder.castView(view, 2131427648, "field 'tklxTvTitleName'");
  }

  @Override public void unbind(T target) {
    target.titleOneTextLlBack = null;
    target.titleOneTextTvTitleName = null;
    target.tklxRecyclerView = null;
    target.tklxBtnDown = null;
    target.tklxTvLookAnswer = null;
    target.tklxBtnTest = null;
    target.tklxLlContent = null;
    target.tklxBtnUp = null;
    target.tklxTvSequence = null;
    target.tklxTvTitleName = null;
  }
}
