package co.moonmonkeylabs.flowmortarexampleapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.InjectView;
import co.moonmonkeylabs.flowmortarexampleapp.R;
import co.moonmonkeylabs.flowmortarexampleapp.common.widget.CustomFrameLayout;
import co.moonmonkeylabs.flowmortarexampleapp.screen.RotationScreen;

public class RotationView extends CustomFrameLayout<RotationScreen.Presenter> {

  @Inject
  RotationScreen.Presenter presenter;

  @InjectView(R.id.rotation_count)
  TextView counterText;

  public RotationView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public RotationScreen.Presenter getPresenter() {
    return presenter;
  }

  public void updateCount(int count) {
    counterText.setText("onLoad count: " + count);
  }
}
