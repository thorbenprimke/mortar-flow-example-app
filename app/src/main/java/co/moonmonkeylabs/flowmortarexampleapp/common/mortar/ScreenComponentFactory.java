package co.moonmonkeylabs.flowmortarexampleapp.common.mortar;

/**
 * @author Lukasz Piliszczuk <lukasz.pili@gmail.com>
 */
public interface ScreenComponentFactory<T> {

    Object createComponent(T parent);
}
