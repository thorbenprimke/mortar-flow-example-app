package co.moonmonkeylabs.flowmortarexampleapp.screen;

import android.os.Bundle;

import javax.inject.Inject;

import co.moonmonkeylabs.flowmortarexampleapp.FlowMortarExampleActivity;
import co.moonmonkeylabs.flowmortarexampleapp.R;
import co.moonmonkeylabs.flowmortarexampleapp.common.flow.Layout;
import co.moonmonkeylabs.flowmortarexampleapp.common.mortar.ScreenComponentFactory;
import co.moonmonkeylabs.flowmortarexampleapp.di.PerScreen;
import co.moonmonkeylabs.flowmortarexampleapp.view.RotationView;
import flow.path.Path;
import mortar.ViewPresenter;

@Layout(R.layout.rotation_layout)
public class RotationScreen extends Path
    implements ScreenComponentFactory<FlowMortarExampleActivity.Component> {

  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }
    return this.getClass().getSimpleName().equals(o.getClass().getSimpleName());
  }

  @Override
  public Object createComponent(FlowMortarExampleActivity.Component parent) {
    return DaggerRotationScreen_Component.builder()
        .component(parent)
        .build();
  }

  @PerScreen
  @dagger.Component(dependencies = FlowMortarExampleActivity.Component.class)
  public interface Component {
    void inject(RotationView view);
  }

  public static class Presenter extends ViewPresenter<RotationView> {

    private int onLoadCount = 0;

    @Inject
    public Presenter() {
    }

    @Override
    protected void onLoad(Bundle savedInstanceState) {
      super.onLoad(savedInstanceState);

      if (onLoadCount == 0 && savedInstanceState != null) {
        onLoadCount = savedInstanceState.getInt("onLoadCount", 0);
      }

      getView().updateCount(++onLoadCount);
    }


    @Override
    protected void onSave(Bundle outState) {
      outState.putInt("onLoadCount", onLoadCount);
    }
  }
}