package co.moonmonkeylabs.flowmortarexampleapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import co.moonmonkeylabs.flowmortarexampleapp.R;
import co.moonmonkeylabs.flowmortarexampleapp.common.dagger.DaggerService;
import co.moonmonkeylabs.flowmortarexampleapp.screen.RotationScreen;

public class RotationView extends LinearLayout {

  @Inject
  RotationScreen.Presenter presenter;

  @InjectView(R.id.rotation_count)
  TextView counterText;

  public RotationView(Context context, AttributeSet attrs) {
    super(context, attrs);
    DaggerService.<RotationScreen.Component>getDaggerComponent(context).inject(this);
  }

  public void updateCount(int count) {
    counterText.setText("onLoad count: " + count);
  }

  @Override
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    presenter.takeView(this);
  }

  @Override
  protected void onDetachedFromWindow() {
    presenter.dropView(this);
    super.onDetachedFromWindow();
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    ButterKnife.inject(this);
  }
}
