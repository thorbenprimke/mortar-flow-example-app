package co.moonmonkeylabs.flowmortarexampleapp.common.setting;

import android.content.SharedPreferences;

public class BooleanLocalSetting extends AbstractLocalSetting<Boolean> {

  public BooleanLocalSetting(SharedPreferences preferences, String key) {
    super(preferences, key);
  }

  @Override
  protected Boolean doGet() {
    return preferences.getBoolean(key, false);
  }

  @Override
  public void set(Boolean value) {
    apply(preferences.edit().putBoolean(key, value));
  }
}
