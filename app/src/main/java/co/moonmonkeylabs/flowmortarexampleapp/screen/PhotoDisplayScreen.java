package co.moonmonkeylabs.flowmortarexampleapp.screen;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.inject.Inject;

import co.moonmonkeylabs.flowmortarexampleapp.FlowMortarExampleActivity;
import co.moonmonkeylabs.flowmortarexampleapp.R;
import co.moonmonkeylabs.flowmortarexampleapp.common.flow.ActivityHelper;
import co.moonmonkeylabs.flowmortarexampleapp.common.flow.Layout;
import co.moonmonkeylabs.flowmortarexampleapp.common.lifecycle.LifecycleOwner;
import co.moonmonkeylabs.flowmortarexampleapp.common.lifecycle.LifecycleViewPresenter;
import co.moonmonkeylabs.flowmortarexampleapp.common.mortar.ScreenComponentFactory;
import co.moonmonkeylabs.flowmortarexampleapp.di.PerScreen;
import co.moonmonkeylabs.flowmortarexampleapp.view.PhotoDisplayView;
import flow.path.Path;

@Layout(R.layout.photo_display_layout)
public class PhotoDisplayScreen extends Path
  implements ScreenComponentFactory<FlowMortarExampleActivity.Component> {

  @Override
  public Object createComponent(FlowMortarExampleActivity.Component parent) {
    return DaggerPhotoDisplayScreen_Component.builder()
        .component(parent)
        .build();
  }

  @PerScreen
  @dagger.Component(dependencies = FlowMortarExampleActivity.Component.class)
  public interface Component {
    void inject(PhotoDisplayView view);
  }

  public static class Presenter extends LifecycleViewPresenter<PhotoDisplayView> {

    private static final int SELECT_PICTURE = 1;

    private final ActivityHelper activityHelper;

    @Inject
    public Presenter(LifecycleOwner lifecycleOwner, ActivityHelper activityHelper) {
      super(lifecycleOwner);
      this.activityHelper = activityHelper;
    }

    public void handlePhotoDisplayPickButtonClicked() {
      Intent intent = new Intent();
      intent.setType("image/*");
      intent.setAction(Intent.ACTION_GET_CONTENT);
      Activity activity = activityHelper.findActivity((ContextWrapper) getView().getContext());
      activity.startActivityForResult(
          Intent.createChooser(intent, "Select Picture"),
          SELECT_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
      if (requestCode == SELECT_PICTURE && data != null) {
        try {
          final Uri imageUri = data.getData();
          Activity activity = activityHelper.findActivity((ContextWrapper) getView().getContext());
          final InputStream imageStream =
              activity.getContentResolver().openInputStream(imageUri);
          final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
          getView().setImage(selectedImage);
        } catch (FileNotFoundException e) {
          Toast.makeText(getView().getContext(), "Failed to load image", Toast.LENGTH_SHORT).show();
        }
      }
    }
  }
}