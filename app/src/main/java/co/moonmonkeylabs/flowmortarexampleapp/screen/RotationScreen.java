package co.moonmonkeylabs.flowmortarexampleapp.screen;

import android.os.Bundle;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.moonmonkeylabs.flowmortarexampleapp.ActivityModule;
import co.moonmonkeylabs.flowmortarexampleapp.R;
import co.moonmonkeylabs.flowmortarexampleapp.common.flow.Layout;
import co.moonmonkeylabs.flowmortarexampleapp.common.mortarscreen.WithModule;
import co.moonmonkeylabs.flowmortarexampleapp.view.RotationView;
import flow.path.Path;
import mortar.ViewPresenter;

@Layout(R.layout.rotation_layout)
@WithModule(RotationScreen.Module.class)
public class RotationScreen extends Path {

  @dagger.Module(injects = RotationView.class, addsTo = ActivityModule.class)
  public class Module {
  }

  @Singleton
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