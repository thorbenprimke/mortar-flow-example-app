package co.moonmonkeylabs.flowmortarexampleapp.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import co.moonmonkeylabs.flowmortarexampleapp.common.dagger.ObjectGraphService;
import mortar.Presenter;

/**
 * Custom {@link android.widget.RelativeLayout} that has support for {@link mortar.Mortar} and
 * {@link butterknife.ButterKnife}.
 */
public abstract class CustomRelativeLayout<T extends Presenter> extends RelativeLayout {

  public CustomRelativeLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    ObjectGraphService.inject(context, this);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    ButterKnife.inject(this);
  }

  @Override
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    getPresenter().takeView(this);
  }

  @Override
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    getPresenter().dropView(this);
  }

  public abstract T getPresenter();
}
