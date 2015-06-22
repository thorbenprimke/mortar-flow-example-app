//package co.moonmonkeylabs.flowmortarexampleapp.screen;
//
//import android.app.Activity;
//import android.content.ContextWrapper;
//import android.os.Bundle;
//
//import javax.inject.Inject;
//import javax.inject.Singleton;
//
//import co.moonmonkeylabs.flowmortarexampleapp.FlowMortarExampleActivity;
//import co.moonmonkeylabs.flowmortarexampleapp.R;
//import co.moonmonkeylabs.flowmortarexampleapp.WizardModule;
//import co.moonmonkeylabs.flowmortarexampleapp.common.flow.ActivityHelper;
//import co.moonmonkeylabs.flowmortarexampleapp.common.flow.Layout;
//import co.moonmonkeylabs.flowmortarexampleapp.common.mortarscreen.WithModule;
//import co.moonmonkeylabs.flowmortarexampleapp.model.WizardState;
//import co.moonmonkeylabs.flowmortarexampleapp.view.Wizard2View;
//import flow.Flow;
//import flow.path.Path;
//import mortar.ViewPresenter;
//
//@Layout(R.layout.wizard_2_layout)
//@WithModule(Wizard2Screen.Module.class)
//public class Wizard2Screen extends Path {
//
//  public Wizard2Screen() {
//  }
//
//  @dagger.Module(injects = Wizard2View.class, addsTo = WizardModule.class)
//  public class Module {
//  }
//
//  @Singleton
//  public static class Presenter extends ViewPresenter<Wizard2View> {
//
//    private final WizardState wizardState;
//    private final ActivityHelper activityHelper;
//
//    @Inject
//    public Presenter(WizardState wizardState, ActivityHelper activityHelper) {
//      this.wizardState = wizardState;
//      this.activityHelper = activityHelper;
//    }
//
//    @Override
//    protected void onLoad(Bundle savedInstanceState) {
//      super.onLoad(savedInstanceState);
//
//      getView().updateWizardCount(++wizardState.count);
//    }
//
//    public void handleNextButtonClicked() {
//      Activity activity = activityHelper.findActivity((ContextWrapper) getView().getContext());
//      ((FlowMortarExampleActivity) activity).removeWizardScope();
//      Flow.get(getView()).set(new MainScreen());
//    }
//  }
//}