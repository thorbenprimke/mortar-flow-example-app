package co.moonmonkeylabs.flowmortarexampleapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import co.moonmonkeylabs.flowmortarexampleapp.R;
import co.moonmonkeylabs.flowmortarexampleapp.common.dagger.DaggerService;
import co.moonmonkeylabs.flowmortarexampleapp.screen.MainScreen;

public class MainView extends LinearLayout {

  @Inject
  MainScreen.Presenter presenter;

  public MainView(Context context, AttributeSet attrs) {
    super(context, attrs);
    DaggerService.<MainScreen.Component>getDaggerComponent(context).inject(this);
  }

  @OnClick(R.id.main_buttons_rotation_screen)
  public void rotationScreenButtonClicked() {
    presenter.handleRotationScreenButtonClicked();
  }

  @OnClick(R.id.main_buttons_photo_display_screen)
  public void photoDisplayScreenButtonClicked() {
    presenter.handlePhotoDisplayScreenButtonClicked();
  }

  @OnClick(R.id.main_buttons_settings_screen)
  public void settingsScreenButtonClicked() {
    presenter.handleSettingsScreenButtonClicked();
  }

  @OnClick(R.id.main_buttons_wizard_screen)
  public void wizardScreenButtonClicked() {
    presenter.handleWizardScreenButtonClicked();
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
