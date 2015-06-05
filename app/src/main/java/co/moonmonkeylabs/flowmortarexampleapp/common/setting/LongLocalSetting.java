package co.moonmonkeylabs.flowmortarexampleapp.common.setting;

import android.content.SharedPreferences;

/**
 * Local setting for any values of type {@link Long}.
 */
public class LongLocalSetting extends AbstractLocalSetting<Long> {

  public LongLocalSetting(SharedPreferences preferences, String key) {
    super(preferences, key);
  }

  @Override
  protected Long doGet() {
    return preferences.getLong(key, 0L);
  }

  @Override
  public void set(Long value) {
    apply(preferences.edit().putLong(key, value));
  }
}
