package co.moonmonkeylabs.flowmortarexampleapp.common.setting;

import android.content.SharedPreferences;

/**
 * Base class for any {@link LocalSetting} classes.
 */
public abstract class AbstractLocalSetting<T> implements LocalSetting<T> {
  protected final SharedPreferences preferences;
  protected final String key;

  public AbstractLocalSetting(SharedPreferences preferences, String key) {
    this.preferences = preferences;
    this.key = key;
  }

  @Override public T get(T defaultValue) {
    if (!preferences.contains(key)) {
      return defaultValue;
    }

    return get();
  }

  @Override public T get() {
    return doGet();
  }

  protected abstract T doGet();

  @Override public void remove() {
    apply(preferences.edit().remove(key));
  }

  protected void apply(SharedPreferences.Editor editor) {
    editor.apply();
  }
}