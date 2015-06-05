package co.moonmonkeylabs.flowmortarexampleapp.common.mortarscreen;

import android.content.res.Resources;

/** @see WithModuleFactory */
public abstract class ModuleFactory<T> {
  protected abstract Object createDaggerModule(Resources resources, T screen);
}
