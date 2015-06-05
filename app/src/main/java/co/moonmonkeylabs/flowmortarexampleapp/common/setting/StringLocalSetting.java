package co.moonmonkeylabs.flowmortarexampleapp.common.setting;

import android.content.SharedPreferences;

/**
 * Local setting for any values of type {@link String}.
 */
public class StringLocalSetting extends AbstractLocalSetting<String> {

  public StringLocalSetting(SharedPreferences preferences, String key) {
    super(preferences, key);
  }

  @Override
  protected String doGet() {
    return preferences.getString(key, null);
  }

  @Override
  public void set(String value) {
    if (value == null) {
      apply(preferences.edit().remove(key));
    } else {
      apply(preferences.edit().putString(key, value));
    }
  }
}
