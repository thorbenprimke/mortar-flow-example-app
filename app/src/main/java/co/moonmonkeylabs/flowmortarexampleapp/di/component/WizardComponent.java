package co.moonmonkeylabs.flowmortarexampleapp.di.component;

import co.moonmonkeylabs.flowmortarexampleapp.FlowMortarExampleActivity;
import co.moonmonkeylabs.flowmortarexampleapp.di.WizardSingleton;
import co.moonmonkeylabs.flowmortarexampleapp.di.module.WizardModule;
import co.moonmonkeylabs.flowmortarexampleapp.model.WizardState;
import dagger.Component;

@WizardSingleton
@Component(
    modules = {
        WizardModule.class
    },
    dependencies = FlowMortarExampleActivity.Component.class
)
public interface WizardComponent {

  WizardState wizardState();
}
