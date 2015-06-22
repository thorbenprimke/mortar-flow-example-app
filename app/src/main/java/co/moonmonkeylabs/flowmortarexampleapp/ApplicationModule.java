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
package co.moonmonkeylabs.flowmortarexampleapp;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import co.moonmonkeylabs.flowmortarexampleapp.common.actionbar.ActionBarOwner;
import co.moonmonkeylabs.flowmortarexampleapp.common.flow.GsonParceler;
import co.moonmonkeylabs.flowmortarexampleapp.setting.SettingsModule;
import dagger.Module;
import dagger.Provides;
import flow.StateParceler;

/**
 * Defines app-wide singletons
 */
@Module(
    includes = {
        SettingsModule.class,
        ActionBarOwner.ActionBarModule.class
    }
)
public class ApplicationModule {

  private final Application application;

  public ApplicationModule(Application application) {
    this.application = application;
  }

  @Provides
  Context provideApplicationContext() {
    return this.application;
  }

  @Provides
  Application providesApplication() {
    return application;
  }

  @Provides
  Gson provideGson() {
    return new GsonBuilder().create();
  }

  @Provides
  StateParceler provideParcer(Gson gson) {
    return new GsonParceler(gson);
  }

  @Singleton
  @Provides
  @Named("someString")
  String providesSomeString() {
    return "someStringWithMore " + System.currentTimeMillis();
  }
}
