package co.moonmonkeylabs.flowmortarexampleapp.screen;

import android.app.Activity;
import android.content.ContextWrapper;
import android.os.Bundle;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.moonmonkeylabs.flowmortarexampleapp.ActivityModule;
import co.moonmonkeylabs.flowmortarexampleapp.FlowMortarExampleActivity;
import co.moonmonkeylabs.flowmortarexampleapp.R;
import co.moonmonkeylabs.flowmortarexampleapp.common.flow.ActivityHelper;
import co.moonmonkeylabs.flowmortarexampleapp.common.flow.Layout;
import co.moonmonkeylabs.flowmortarexampleapp.common.mortarscreen.WithModule;
import co.moonmonkeylabs.flowmortarexampleapp.view.MainView;
import flow.Flow;
import flow.path.Path;
import mortar.ViewPresenter;

@Layout(R.layout.main_layout)
@WithModule(MainScreen.Module.class)
public class MainScreen extends Path {

  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }
    return this.getClass().getSimpleName().equals(o.getClass().getSimpleName());
  }

  @dagger.Module(injects = MainView.class, addsTo = ActivityModule.class)
  public class Module {
  }

  @Singleton
  public static class Presenter extends ViewPresenter<MainView> {

    private final ActivityHelper activityHelper;

    @Inject
    public Presenter(ActivityHelper activityHelper) {
      this.activityHelper = activityHelper;
    }

    @Override
    protected void onLoad(Bundle savedInstanceState) {
      super.onLoad(savedInstanceState);
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