package co.moonmonkeylabs.flowmortarexampleapp.wizard;

import android.content.Context;

import mortar.MortarScope;

/**
 * Created by thorben on 6/4/15.
 */
public class WizardHelper {

  public static void addWizardScope(Context context) {
    MortarScope scope = MortarScope.getScope(context);
    if (scope != null) {
    }
  }
}
