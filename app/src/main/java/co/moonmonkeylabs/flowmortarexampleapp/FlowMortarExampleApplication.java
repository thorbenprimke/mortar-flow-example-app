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

import co.moonmonkeylabs.flowmortarexampleapp.common.dagger.DaggerService;
import co.moonmonkeylabs.flowmortarexampleapp.di.component.ApplicationComponent;
import co.moonmonkeylabs.flowmortarexampleapp.di.component.DaggerApplicationComponent;
import co.moonmonkeylabs.flowmortarexampleapp.setting.SettingsModule;
import mortar.MortarScope;

public class FlowMortarExampleApplication extends Application {

  private MortarScope mortarScope;

  @Override
  public Object getSystemService(String name) {
    return mortarScope.hasService(name) ? mortarScope.getService(name) : super.getSystemService(name);
  }

  @Override
  public void onCreate() {
    super.onCreate();

    ApplicationComponent component = DaggerApplicationComponent
        .builder()
        .applicationModule(new ApplicationModule(this))
        .settingsModule(new SettingsModule())
        .build();
    component.inject(this);

    mortarScope = MortarScope.buildRootScope()
        .withService(DaggerService.SERVICE_NAME, component)
        .build("Root");
  }
}
