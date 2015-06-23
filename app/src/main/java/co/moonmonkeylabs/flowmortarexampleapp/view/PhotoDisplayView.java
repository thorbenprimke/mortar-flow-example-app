package co.moonmonkeylabs.flowmortarexampleapp.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import co.moonmonkeylabs.flowmortarexampleapp.R;
import co.moonmonkeylabs.flowmortarexampleapp.common.dagger.DaggerService;
import co.moonmonkeylabs.flowmortarexampleapp.screen.PhotoDisplayScreen;

public class PhotoDisplayView extends FrameLayout {

  @Inject
  PhotoDisplayScreen.Presenter presenter;

  @InjectView(R.id.photo_display_image)
  ImageView photoDisplayImage;

  public PhotoDisplayView(Context context, AttributeSet attrs) {
    super(context, attrs);
    DaggerService.<PhotoDisplayScreen.Component>getDaggerComponent(context).inject(this);
  }

  @OnClick(R.id.photo_display_pick_button)
  public void photoDisplayPickButtonClicked() {
    presenter.handlePhotoDisplayPickButtonClicked();
  }

  public void setImage(Bitmap selectedImage) {
    photoDisplayImage.setImageBitmap(selectedImage);
  }

  @Override
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    presenter.takeView(this);
  }

  @Override
  protected void onDetachedFromWindow() {
    presenter.dropView(this);
    super.onDetachedFromWindow();
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    ButterKnife.inject(this);
  }
}
