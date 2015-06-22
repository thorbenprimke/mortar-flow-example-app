//package co.moonmonkeylabs.flowmortarexampleapp.screen;
//
//import android.os.Bundle;
//
//import javax.inject.Inject;
//import javax.inject.Singleton;
//
//import co.moonmonkeylabs.flowmortarexampleapp.ActivityModule;
//import co.moonmonkeylabs.flowmortarexampleapp.R;
//import co.moonmonkeylabs.flowmortarexampleapp.common.flow.Layout;
//import co.moonmonkeylabs.flowmortarexampleapp.common.mortarscreen.WithModule;
//import co.moonmonkeylabs.flowmortarexampleapp.common.setting.StringLocalSetting;
//import co.moonmonkeylabs.flowmortarexampleapp.setting.UserPreferredName;
//import co.moonmonkeylabs.flowmortarexampleapp.view.SettingView;
//import flow.path.Path;
//import mortar.ViewPresenter;
//
//@Layout(R.layout.settings_layout)
//@WithModule(SettingScreen.Module.class)
//public class SettingScreen extends Path {
//
//  @dagger.Module(injects = SettingView.class, addsTo = ActivityModule.class)
//  public class Module {
//  }
//
//  @Singleton
//  public static class Presenter extends ViewPresenter<SettingView> {
//
//    private final StringLocalSetting userPreferredName;
//
//    @Inject
//    public Presenter(@UserPreferredName StringLocalSetting userPreferredName) {
//      this.userPreferredName = userPreferredName;
//    }
//
//    @Override
//    protected void onLoad(Bundle savedInstanceState) {
//      super.onLoad(savedInstanceState);
//
//      getView().updatePreferredName(userPreferredName.get());
//    }
//
//    public void handleSaveUserPreferredName(String newUserPreferredNameValue) {
//      userPreferredName.set(newUserPreferredNameValue);
//    }
//  }
//}