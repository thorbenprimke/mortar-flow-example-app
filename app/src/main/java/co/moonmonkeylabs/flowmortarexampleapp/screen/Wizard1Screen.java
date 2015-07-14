package co.moonmonkeylabs.flowmortarexampleapp.screen;

import android.app.Activity;
import android.content.ContextWrapper;
import android.os.Bundle;

import javax.inject.Inject;

import co.moonmonkeylabs.flowmortarexampleapp.FlowMortarExampleActivity;
import co.moonmonkeylabs.flowmortarexampleapp.R;
import co.moonmonkeylabs.flowmortarexampleapp.common.flow.ActivityHelper;
import co.moonmonkeylabs.flowmortarexampleapp.common.flow.Layout;
import co.moonmonkeylabs.flowmortarexampleapp.common.mortar.ScreenComponentFactory;
import co.moonmonkeylabs.flowmortarexampleapp.di.PerScreen;
import co.moonmonkeylabs.flowmortarexampleapp.di.component.WizardComponent;
import co.moonmonkeylabs.flowmortarexampleapp.model.WizardState;
import co.moonmonkeylabs.flowmortarexampleapp.view.Wizard1View;
import flow.path.Path;
import mortar.ViewPresenter;

@Layout(R.layout.wizard_1_layout)
public class Wizard1Screen extends Path
  implements ScreenComponentFactory<WizardComponent> {

  @Override
  public Object createComponent(WizardComponent parent) {
    return DaggerWizard1Screen_Component.builder()
        .wizardComponent(parent)
        .build();
  }

  @PerScreen
  @dagger.Component(
      dependencies = {
          WizardComponent.class
      }
  )
  public interface Component {
    void inject(Wizard1View view);
  }

  public static class Presenter extends ViewPresenter<Wizard1View> {

    private final WizardState wizardState;
    private final ActivityHelper activityHelper;

    @Inject
    public Presenter(WizardState wizardState, ActivityHelper activityHelper) {
      this.wizardState = wizardState;
      this.activityHelper = activityHelper;
    }

    @Override
    protected void onLoad(Bundle savedInstanceState) {
      super.onLoad(savedInstanceState);
      getView().updateWizardCount(++wizardState.count);
    }

    public void handleNextButtonClicked() {
//      Flow.get(getView()).set(new Wizard2Screen());
    }

    public void handleBackPressed() {
      Activity activity = activityHelper.findActivity((ContextWrapper) getView().getContext());
      ((FlowMortarExampleActivity) activity).removeWizardScope();
    }
  }
}