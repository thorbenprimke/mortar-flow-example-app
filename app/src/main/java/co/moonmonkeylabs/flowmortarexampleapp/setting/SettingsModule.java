/*
 * Copyright 2013 Square Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package co.moonmonkeylabs.flowmortarexampleapp.setting;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import co.moonmonkeylabs.flowmortarexampleapp.common.setting.StringLocalSetting;
import dagger.Module;
import dagger.Provides;

/**
 * Defines app-wide singletons.
 */
@Module
public class SettingsModule {

  private static final String PREFERENCES_NAME = "mortar_flow_example_preferences";

  @Provides
  @Singleton
  public SharedPreferences providesSharedPreferences(Application application) {
    return application.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
  }

  @Provides
  @Singleton
  @Named("userPreferredName")
  StringLocalSetting providesUserPreferredName(SharedPreferences preferences) {
    return new StringLocalSetting(preferences, "userPreferredName");
  }

  @Provides
  @Named("brokenSettings")
  StringLocalSetting providesSomeLocalSetting() {
    return new StringLocalSetting(null, "sdom");
  }
}
