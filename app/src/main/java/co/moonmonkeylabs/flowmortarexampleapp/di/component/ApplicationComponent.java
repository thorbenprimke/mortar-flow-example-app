package co.moonmonkeylabs.flowmortarexampleapp.di.component;

import javax.inject.Singleton;

import co.moonmonkeylabs.flowmortarexampleapp.ApplicationModule;
import co.moonmonkeylabs.flowmortarexampleapp.FlowMortarExampleApplication;
import co.moonmonkeylabs.flowmortarexampleapp.common.actionbar.ActionBarOwner;
import co.moonmonkeylabs.flowmortarexampleapp.common.lifecycle.LifecycleOwner;
import co.moonmonkeylabs.flowmortarexampleapp.common.setting.StringLocalSetting;
import co.moonmonkeylabs.flowmortarexampleapp.setting.UserPreferredName;
import dagger.Component;

@Singleton
@Component(
    modules = {
        ApplicationModule.class
    }
)
public interface ApplicationComponent {

  void inject(FlowMortarExampleApplication flowMortarExampleApplication);

  @UserPreferredName StringLocalSetting userPreferredName2();

  ActionBarOwner provideActionBarOwner();

  LifecycleOwner lifecycleOwner();
}
