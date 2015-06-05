package co.moonmonkeylabs.flowmortarexampleapp.common.lifecycle;

import android.app.Activity;
import android.content.Intent;

public abstract class LifecycleActivity extends Activity {

  public abstract LifecycleOwner getLifecycleOwner();

  @Override
  protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    setIntent(intent);
  }
  @Override
  protected void onStart() {
    super.onStart();
    getLifecycleOwner().onActivityStart();
  }

  @Override
  protected void onResume() {
    super.onResume();
    getLifecycleOwner().onActivityResume();
  }

  @Override
  protected void onPause() {
    super.onPause();
    getLifecycleOwner().onActivityPause();
  }

  @Override
  protected void onStop() {
    super.onStop();
    getLifecycleOwner().onActivityStop();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    getLifecycleOwner().onActivityResult(requestCode, resultCode, data);
  }
}
