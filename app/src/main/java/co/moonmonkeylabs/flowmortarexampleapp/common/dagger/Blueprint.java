package co.moonmonkeylabs.flowmortarexampleapp.common.dagger;

/**
 * Defines a scope to be built via {@link ObjectGraphService#requireChild(mortar.MortarScope, Blueprint)}
 * or {@link ObjectGraphService#requireActivityScope(mortar.MortarScope, Blueprint)}.
 *
 * @deprecated see deprecation note on {@link ObjectGraphService#requireChild(mortar.MortarScope,
 * Blueprint)}
 */
@Deprecated public interface Blueprint {
  /**
   * Returns the name of the new scope. This can be used later to {@link
   * mortar.MortarScope#findChild(String) find} it in its parent. If {@link
   * ObjectGraphService#requireChild(mortar.MortarScope, Blueprint)} is called again with a {@link
   * Blueprint} of the same name, the original instance will be returned unless it has been
   * {@link mortar.MortarScope#destroy}  destroyed}.
   */
  String getMortarScopeName();

  /**
   * Returns the {@literal @}{@link dagger.Module Module} that will define the scope
   * of the new graph by being added to that of its parent. If the returned value
   * is an instance of {@link java.util.Collection} its contents will be used as modules.
   * Returns null if this scope needs no modules.
   *
   * @see dagger.ObjectGraph#plus(Object...)
   */
  Object getDaggerModule();
}
