package co.moonmonkeylabs.flowmortarexampleapp.screen;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.moonmonkeylabs.flowmortarexampleapp.ActivityModule;
import co.moonmonkeylabs.flowmortarexampleapp.R;
import co.moonmonkeylabs.flowmortarexampleapp.common.flow.ActivityHelper;
import co.moonmonkeylabs.flowmortarexampleapp.common.flow.Layout;
import co.moonmonkeylabs.flowmortarexampleapp.common.lifecycle.LifecycleOwner;
import co.moonmonkeylabs.flowmortarexampleapp.common.lifecycle.LifecycleViewPresenter;
import co.moonmonkeylabs.flowmortarexampleapp.common.mortarscreen.WithModule;
import co.moonmonkeylabs.flowmortarexampleapp.view.PhotoDisplayView;
import flow.path.Path;
import flow.path.PathContext;

@Layout(R.layout.photo_display_layout)
@WithModule(PhotoDisplayScreen.Module.class)
public class PhotoDisplayScreen extends Path {

  @dagger.Module(injects = PhotoDisplayView.class, addsTo = ActivityModule.class)
  public class Module {
  }

  @Singleton
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
      if (requestCode == SELECT_PICTURE) {
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