package co.moonmonkeylabs.flowmortarexampleapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import co.moonmonkeylabs.flowmortarexampleapp.R;
import co.moonmonkeylabs.flowmortarexampleapp.common.dagger.DaggerService;
import co.moonmonkeylabs.flowmortarexampleapp.screen.SettingScreen;

public class SettingView extends LinearLayout {

  @Inject
  SettingScreen.Presenter presenter;

  @InjectView(R.id.settings_name_input)
  EditText settingsNameInput;

  public SettingView(Context context, AttributeSet attrs) {
    super(context, attrs);
    DaggerService.<SettingScreen.Component>getDaggerComponent(context).inject(this);
  }

  @OnClick(R.id.settings_update_name_button)
  public void settingsUpdateNameButtonClicked() {
    presenter.handleSaveUserPreferredName(settingsNameInput.getText().toString());
  }

  public void updatePreferredName(String userPreferredName) {
    settingsNameInput.setText(userPreferredName);
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
