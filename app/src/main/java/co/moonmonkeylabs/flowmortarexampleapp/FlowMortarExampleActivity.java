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

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Named;

import co.moonmonkeylabs.flowmortarexampleapp.common.actionbar.ActionBarOwner;
import co.moonmonkeylabs.flowmortarexampleapp.common.dagger.DaggerService;
import co.moonmonkeylabs.flowmortarexampleapp.common.flow.FlowHistoryDevHelper;
import co.moonmonkeylabs.flowmortarexampleapp.common.flow.GsonParceler;
import co.moonmonkeylabs.flowmortarexampleapp.common.flow.HandlesBack;
import co.moonmonkeylabs.flowmortarexampleapp.common.lifecycle.LifecycleActivity;
import co.moonmonkeylabs.flowmortarexampleapp.common.lifecycle.LifecycleOwner;
import co.moonmonkeylabs.flowmortarexampleapp.common.setting.StringLocalSetting;
import co.moonmonkeylabs.flowmortarexampleapp.di.PerActivity;
import co.moonmonkeylabs.flowmortarexampleapp.di.component.ApplicationComponent;
import co.moonmonkeylabs.flowmortarexampleapp.di.component.DaggerWizardComponent;
import co.moonmonkeylabs.flowmortarexampleapp.di.component.WizardComponent;
import co.moonmonkeylabs.flowmortarexampleapp.screen.MainScreen;
import flow.Flow;
import flow.FlowDelegate;
import flow.History;
import flow.path.Path;
import flow.path.PathContainerView;
import mortar.MortarScope;
import mortar.MortarScopeDevHelper;
import mortar.bundler.BundleServiceRunner;

import static android.view.MenuItem.SHOW_AS_ACTION_ALWAYS;
import static mortar.bundler.BundleServiceRunner.getBundleServiceRunner;

/**
 * Activity is mostly based on the Mortar demo activity. Added support for lifecycle handling in
 * presenters and added wizard scope.
 */
public class FlowMortarExampleActivity extends LifecycleActivity
    implements ActionBarOwner.Activity, Flow.Dispatcher {

  private MortarScope activityScope;
  private MortarScope wizardScope;

  private ActionBarOwner.MenuAction actionBarMenuAction;

//  @Inject
//  ActionBarOwner actionBarOwner;

  @Inject
  LifecycleOwner lifecycleOwner;

  private PathContainerView container;
  private HandlesBack containerAsHandlesBack;
  private FlowDelegate flowDelegate;

  @Override
  public LifecycleOwner getLifecycleOwner() {
    return lifecycleOwner;
  }

  @Override
  public Context getContext() {
    return this;
  }

  @Override
  public void dispatch(Flow.Traversal traversal, Flow.TraversalCallback callback) {
    Path newScreen = traversal.destination.top();
    String title = newScreen.getClass().getSimpleName();
//    actionBarOwner.setConfig(
//        new ActionBarOwner.Config(false, !(newScreen instanceof MainScreen), title, null));

    container.dispatch(traversal, callback);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    GsonParceler parceler = new GsonParceler(new Gson());
    @SuppressWarnings("deprecation") FlowDelegate.NonConfigurationInstance nonConfig =
        (FlowDelegate.NonConfigurationInstance) getLastNonConfigurationInstance();

//    MortarScope parentScope = MortarScope.getScope(getApplication());
//
//    String scopeName = getLocalClassName() + "-task-" + getTaskId();
//
//    activityScope = parentScope.findChild(scopeName);
//    if (activityScope == null) {
//      activityScope = parentScope.buildChild()
//          .withService(BundleServiceRunner.SERVICE_NAME, new BundleServiceRunner())
//          .withService(
//              DaggerService.SERVICE_NAME,
//              ObjectGraphService.create(parentScope, new ActivityModule(this)))
//          .build(scopeName);
//    }
//    ObjectGraphService.inject(this, this);
//
//    getBundleServiceRunner(activityScope).onCreate(savedInstanceState);
    activityScope = MortarScope.findChild(getApplicationContext(), getClass().getName());

    if (activityScope == null) {
      Component component = DaggerFlowMortarExampleActivity_Component.builder()
          .applicationComponent(DaggerService.<ApplicationComponent>getDaggerComponent(getApplicationContext()))
          .build();

      activityScope = MortarScope.buildChild(getApplicationContext())
          .withService(BundleServiceRunner.SERVICE_NAME, new BundleServiceRunner())
          .withService(DaggerService.SERVICE_NAME, component)
          .build(getClass().getName());
    }

    DaggerService.<Component>getDaggerComponent(this).inject(this);

    BundleServiceRunner.getBundleServiceRunner(this).onCreate(savedInstanceState);

//    actionBarOwner.takeView(this);

    setContentView(R.layout.root_layout);
    container = (PathContainerView) findViewById(R.id.container);
    containerAsHandlesBack = (HandlesBack) container;
    flowDelegate = FlowDelegate.onCreate(
        nonConfig,
        getIntent(),
        savedInstanceState,
        parceler,
        History.single(new MainScreen()), this);
  }

  public void addWizardScope() {
    if (activityScope == null) {
      return;
    }
    wizardScope = activityScope.findChild("WizardScope");
    if (wizardScope != null && !wizardScope.isDestroyed()) {
      return;
    }

    WizardComponent wizardComponent = DaggerWizardComponent
        .builder()
        .component(DaggerService.<Component>getDaggerComponent(getContext()))
        .build();

    wizardScope = activityScope.buildChild()
        .withService(DaggerService.SERVICE_NAME, wizardComponent)
        .build("WizardScope");

//    wizardScope = activityScope.buildChild()
//        .withService(
//            ObjectGraphService.SERVICE_NAME,
//            ObjectGraphService.create(activityScope, new WizardModule()))
//        .wizardComponent("WizardScope");
  }

  public void removeWizardScope() {
    if (wizardScope == null || wizardScope.isDestroyed()) {
      wizardScope = null;
      return;
    }
    wizardScope.destroy();
    wizardScope = null;
  }

  @Override
  protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    flowDelegate.onNewIntent(intent);
  }

  @Override
  protected void onResume() {
    super.onResume();
    flowDelegate.onResume();
  }

  @Override
  protected void onPause() {
    flowDelegate.onPause();
    super.onPause();
  }

  @SuppressWarnings("deprecation") // https://code.google.com/p/android/issues/detail?id=151346
  @Override
  public Object onRetainNonConfigurationInstance() {
    return flowDelegate.onRetainNonConfigurationInstance();
  }

  @Override
  public Object getSystemService(String name) {
    if (flowDelegate != null) {
      Object flowService = flowDelegate.getSystemService(name);
      if (flowService != null) return flowService;
    }

    if (wizardScope != null && wizardScope.hasService(name)) {
      return wizardScope.getService(name);
    }

    return activityScope != null && activityScope.hasService(name) ? activityScope.getService(name)
        : super.getSystemService(name);
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    flowDelegate.onSaveInstanceState(outState);
    getBundleServiceRunner(this).
        onSaveInstanceState(outState);
  }

  /**
   * Inform the view about back events.
   */
  @Override
  public void onBackPressed() {
    if (!containerAsHandlesBack.onBackPressed()) super.onBackPressed();
  }

  /**
   * Inform the view about up events.
   */
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) return containerAsHandlesBack.onBackPressed();
    return super.onOptionsItemSelected(item);
  }

  /**
   * Configure the action bar menu as required by {@link ActionBarOwner.Activity}.
   */
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    if (actionBarMenuAction != null) {
      menu.add(actionBarMenuAction.title)
          .setShowAsActionFlags(SHOW_AS_ACTION_ALWAYS)
          .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
              actionBarMenuAction.action.call();
              return true;
            }
          });
    }
    menu.add("Log Scope Hierarchy")
        .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
          @Override
          public boolean onMenuItemClick(MenuItem item) {
            Log.d(
                FlowMortarExampleActivity.class.getSimpleName(),
                MortarScopeDevHelper.scopeHierarchyToString(activityScope));
            return true;
          }
        });
    menu.add("Log Flow History")
        .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
          @Override
          public boolean onMenuItemClick(MenuItem item) {
            Log.d(
                FlowMortarExampleActivity.class.getSimpleName(),
                FlowHistoryDevHelper.flowHistoryToString(Flow.get(getContext()).getHistory()));
            return true;
          }
        });
    return true;
  }

  @Override
  protected void onDestroy() {
//    actionBarOwner.dropView(this);
//    actionBarOwner.setConfig(null);

    // activityScope may be null in case isWrongInstance() returned true in onCreate()
    if (isFinishing() && activityScope != null) {
      activityScope.destroy();
      activityScope = null;
    }

    super.onDestroy();
  }

  @Override
  public void setShowHomeEnabled(boolean enabled) {
    ActionBar actionBar = getActionBar();
    actionBar.setDisplayShowHomeEnabled(false);
  }

  @Override
  public void setUpButtonEnabled(boolean enabled) {
    ActionBar actionBar = getActionBar();
    actionBar.setDisplayHomeAsUpEnabled(enabled);
    actionBar.setHomeButtonEnabled(enabled);
  }

  @Override
  public void setTitle(CharSequence title) {
    getActionBar().setTitle(title);
  }

  @Override
  public void setMenu(ActionBarOwner.MenuAction action) {
    if (action != actionBarMenuAction) {
      actionBarMenuAction = action;
      invalidateOptionsMenu();
    }
  }

  @PerActivity
  @dagger.Component(dependencies = ApplicationComponent.class)
  public interface Component extends ApplicationComponent {
    void inject(FlowMortarExampleActivity activity);
  }
}
