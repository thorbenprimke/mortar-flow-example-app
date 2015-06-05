package co.moonmonkeylabs.flowmortarexampleapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;
import co.moonmonkeylabs.flowmortarexampleapp.R;
import co.moonmonkeylabs.flowmortarexampleapp.common.widget.CustomLinearLayout;
import co.moonmonkeylabs.flowmortarexampleapp.screen.SettingScreen;

public class SettingView extends CustomLinearLayout<SettingScreen.Presenter> {

  @Inject
  SettingScreen.Presenter presenter;

  @InjectView(R.id.settings_name_input)
  EditText settingsNameInput;

  public SettingView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public SettingScreen.Presenter getPresenter() {
    return presenter;
  }

  @OnClick(R.id.settings_update_name_button)
  public void settingsUpdateNameButtonClicked() {
    presenter.handleSaveUserPreferredName(settingsNameInput.getText().toString());
  }

  public void updatePreferredName(String userPreferredName) {
    settingsNameInput.setText(userPreferredName);
  }
}
