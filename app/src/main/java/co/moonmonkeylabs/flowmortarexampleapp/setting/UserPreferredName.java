package co.moonmonkeylabs.flowmortarexampleapp.setting;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation for the user's preferred name setting.
 */
@Documented
@Retention(RUNTIME)
@Qualifier
public @interface UserPreferredName {
}
