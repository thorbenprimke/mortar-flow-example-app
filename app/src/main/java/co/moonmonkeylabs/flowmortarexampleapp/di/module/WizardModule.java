package co.moonmonkeylabs.flowmortarexampleapp.di.module;

import co.moonmonkeylabs.flowmortarexampleapp.di.WizardSingleton;
import co.moonmonkeylabs.flowmortarexampleapp.model.WizardState;
import dagger.Module;
import dagger.Provides;

@Module
public class WizardModule {

  @Provides
  @WizardSingleton
  public WizardState providesWizardState() {
    return new WizardState();
  }
}
