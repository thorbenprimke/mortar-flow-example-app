package co.moonmonkeylabs.flowmortarexampleapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import co.moonmonkeylabs.flowmortarexampleapp.R;
import co.moonmonkeylabs.flowmortarexampleapp.common.dagger.DaggerService;
import co.moonmonkeylabs.flowmortarexampleapp.common.flow.HandlesBack;
import co.moonmonkeylabs.flowmortarexampleapp.screen.Wizard1Screen;

public class Wizard1View extends LinearLayout implements HandlesBack {

  @Inject
  Wizard1Screen.Presenter presenter;

  @InjectView(R.id.wizard_1_count)
  TextView wizardCounter;

  public Wizard1View(Context context, AttributeSet attrs) {
    super(context, attrs);
    DaggerService.<Wizard1Screen.Component>getDaggerComponent(context).inject(this);
  }

  @OnClick(R.id.wizard_1_next_button)
  public void nextButtonClicked() {
    presenter.handleNextButtonClicked();
  }

  public void updateWizardCount(int count) {
    wizardCounter.setText("Wizard Step Counter: " + count);
  }

  @Override
  public boolean onBackPressed() {
    presenter.handleBackPressed();
    return false;
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
