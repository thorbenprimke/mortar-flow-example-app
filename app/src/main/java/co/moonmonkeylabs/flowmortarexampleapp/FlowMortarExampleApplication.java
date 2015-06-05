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

import co.moonmonkeylabs.flowmortarexampleapp.common.dagger.ObjectGraphService;
import dagger.ObjectGraph;
import mortar.MortarScope;

public class FlowMortarExampleApplication extends Application {
  private MortarScope rootScope;

  @Override public Object getSystemService(String name) {
    if (rootScope == null) {
      rootScope = MortarScope.buildRootScope()
          .withService(
              ObjectGraphService.SERVICE_NAME,
              ObjectGraph.create(new ApplicationModule(this)))
          .build("Root");
    }

    if (rootScope.hasService(name)) return rootScope.getService(name);

    return super.getSystemService(name);
  }
}
