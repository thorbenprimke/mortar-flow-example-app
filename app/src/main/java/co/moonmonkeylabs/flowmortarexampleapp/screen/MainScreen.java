package co.moonmonkeylabs.flowmortarexampleapp.screen;

import android.app.Activity;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import co.moonmonkeylabs.flowmortarexampleapp.FlowMortarExampleActivity;
import co.moonmonkeylabs.flowmortarexampleapp.R;
import co.moonmonkeylabs.flowmortarexampleapp.common.actionbar.ActionBarOwner;
import co.moonmonkeylabs.flowmortarexampleapp.common.flow.ActivityHelper;
import co.moonmonkeylabs.flowmortarexampleapp.common.flow.Layout;
import co.moonmonkeylabs.flowmortarexampleapp.common.mortar.ScreenComponentFactory;
import co.moonmonkeylabs.flowmortarexampleapp.common.setting.StringLocalSetting;
import co.moonmonkeylabs.flowmortarexampleapp.di.PerScreen;
import co.moonmonkeylabs.flowmortarexampleapp.setting.UserPreferredName;
import co.moonmonkeylabs.flowmortarexampleapp.view.MainView;
import flow.Flow;
import flow.path.Path;
import mortar.ViewPresenter;

@Layout(R.layout.main_layout)
public class MainScreen extends Path
    implements ScreenComponentFactory<FlowMortarExampleActivity.Component> {

  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }
    return this.getClass().getSimpleName().equals(o.getClass().getSimpleName());
  }

  @Override
  public Object createComponent(FlowMortarExampleActivity.Component parent) {
    return DaggerMainScreen_Component.builder()
        .component(parent)
        .build();
  }

  @PerScreen
  @dagger.Component(dependencies = FlowMortarExampleActivity.Component.class)
  public interface Component {
    void inject(MainView view);
  }

  public static class Presenter extends ViewPresenter<MainView> {

    private final ActivityHelper activityHelper;
    private final StringLocalSetting userPreferredName;
    private final ActionBarOwner actionBarOwner;

    @Inject
    public Presenter(
        ActivityHelper activityHelper,
        @UserPreferredName StringLocalSetting userPreferredName,
        ActionBarOwner actionBarOwner) {
      this.activityHelper = activityHelper;
      this.userPreferredName = userPreferredName;
      this.actionBarOwner = actionBarOwner;
    }

    @Override
    protected void onLoad(Bundle savedInstanceState) {
      super.onLoad(savedInstanceState);

      String s = userPreferredName.get();
      if (s == null) {
        s = "userPreferredName was null";
      } else if (s.equals("")) {
        s = "userPreferredName was empty";
      }
      Toast.makeText(getView().getContext(), s, Toast.LENGTH_SHORT).show();
   }

    @Override
    protected void onSave(Bundle outState) {
      super.onSave(outState);
    }

    public void handleRotationScreenButtonClicked() {
      Flow.get(getView()).set(new RotationScreen());
    }

    public void handlePhotoDisplayScreenButtonClicked() {
      Flow.get(getView()).set(new PhotoDisplayScreen());
    }

    public void handleSettingsScreenButtonClicked() {
      Flow.get(getView()).set(new SettingScreen());
    }

    public void handleWizardScreenButtonClicked() {
      Activity activity = activityHelper.findActivity((ContextWrapper) getView().getContext());
      ((FlowMortarExampleActivity) activity).addWizardScope();

      Flow.get(getView()).set(new Wizard1Screen());
    }
  }
}