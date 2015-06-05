package co.moonmonkeylabs.flowmortarexampleapp.common.setting;

/**
 * Interface for local settings.
 */
public interface LocalSetting<T> {

  /**
   * Returns the value. If the value has never been set the default varies per implementation.
   * For certainty in this case use {@link #get(T)}.
   */
  T get();

  T get(T defaultValue);

  void set(T value);

  void remove();
}