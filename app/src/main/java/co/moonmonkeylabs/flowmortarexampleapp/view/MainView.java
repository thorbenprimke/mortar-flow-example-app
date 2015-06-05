package co.moonmonkeylabs.flowmortarexampleapp.view;

import android.content.Context;
import android.util.AttributeSet;

import javax.inject.Inject;

import butterknife.OnClick;
import co.moonmonkeylabs.flowmortarexampleapp.R;
import co.moonmonkeylabs.flowmortarexampleapp.common.widget.CustomLinearLayout;
import co.moonmonkeylabs.flowmortarexampleapp.screen.MainScreen;

public class MainView extends CustomLinearLayout<MainScreen.Presenter> {

  @Inject
  MainScreen.Presenter presenter;

  public MainView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public MainScreen.Presenter getPresenter() {
    return presenter;
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
}
