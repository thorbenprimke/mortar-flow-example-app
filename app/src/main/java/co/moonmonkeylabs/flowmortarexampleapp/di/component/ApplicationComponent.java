package co.moonmonkeylabs.flowmortarexampleapp.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import co.moonmonkeylabs.flowmortarexampleapp.ApplicationModule;
import co.moonmonkeylabs.flowmortarexampleapp.FlowMortarExampleApplication;
import co.moonmonkeylabs.flowmortarexampleapp.common.actionbar.ActionBarOwner;
import co.moonmonkeylabs.flowmortarexampleapp.common.setting.StringLocalSetting;
import co.moonmonkeylabs.flowmortarexampleapp.setting.SettingsModule;
import dagger.Component;
import dagger.Provides;

/**
 * Created by thorben on 6/17/15.
 */
@Singleton
@Component(
    modules = {ApplicationModule.class })
public interface ApplicationComponent {
  void inject(FlowMortarExampleApplication flowMortarExampleApplication);

  @Named("userPreferredName") StringLocalSetting userPreferredName();
  @Named("someString") String someString();

  ActionBarOwner provideActionBarOwner();
}