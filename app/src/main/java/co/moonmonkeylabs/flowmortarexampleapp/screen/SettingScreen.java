package co.moonmonkeylabs.flowmortarexampleapp.screen;

import android.os.Bundle;

import javax.inject.Inject;

import co.moonmonkeylabs.flowmortarexampleapp.FlowMortarExampleActivity;
import co.moonmonkeylabs.flowmortarexampleapp.R;
import co.moonmonkeylabs.flowmortarexampleapp.common.flow.Layout;
import co.moonmonkeylabs.flowmortarexampleapp.common.mortar.ScreenComponentFactory;
import co.moonmonkeylabs.flowmortarexampleapp.common.setting.StringLocalSetting;
import co.moonmonkeylabs.flowmortarexampleapp.di.PerScreen;
import co.moonmonkeylabs.flowmortarexampleapp.setting.UserPreferredName;
import co.moonmonkeylabs.flowmortarexampleapp.view.SettingView;
import flow.path.Path;
import mortar.ViewPresenter;

@Layout(R.layout.settings_layout)
public class SettingScreen extends Path
    implements ScreenComponentFactory<FlowMortarExampleActivity.Component> {

  @Override
  public Object createComponent(FlowMortarExampleActivity.Component parent) {
    return DaggerSettingScreen_Component.builder()
        .component(parent)
        .build();
  }

  @PerScreen
  @dagger.Component(
      dependencies = {
          FlowMortarExampleActivity.Component.class
      }
  )
  public interface Component {
    void inject(SettingView view);
  }

  public static class Presenter extends ViewPresenter<SettingView> {

    private final StringLocalSetting userPreferredName;

    @Inject
    public Presenter(@UserPreferredName StringLocalSetting userPreferredName) {
      this.userPreferredName = userPreferredName;
    }

    @Override
    protected void onLoad(Bundle savedInstanceState) {
      super.onLoad(savedInstanceState);

      getView().updatePreferredName(userPreferredName.get());
    }

    public void handleSaveUserPreferredName(String newUserPreferredNameValue) {
      userPreferredName.set(newUserPreferredNameValue);
    }
  }
}
