//package co.moonmonkeylabs.flowmortarexampleapp.view;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.widget.TextView;
//
//import javax.inject.Inject;
//
//import butterknife.InjectView;
//import butterknife.OnClick;
//import co.moonmonkeylabs.flowmortarexampleapp.R;
//import co.moonmonkeylabs.flowmortarexampleapp.common.widget.CustomLinearLayout;
//import co.moonmonkeylabs.flowmortarexampleapp.screen.Wizard2Screen;
//
//public class Wizard2View extends CustomLinearLayout<Wizard2Screen.Presenter> {
//
//  @Inject
//  Wizard2Screen.Presenter presenter;
//
//  @InjectView(R.id.wizard_2_count)
//  TextView wizardCounter;
//
//  public Wizard2View(Context context, AttributeSet attrs) {
//    super(context, attrs);
//  }
//
//  @Override
//  public Wizard2Screen.Presenter getPresenter() {
//    return presenter;
//  }
//
//  @OnClick(R.id.wizard_2_next_button)
//  public void nextButtonClicked() {
//    presenter.handleNextButtonClicked();
//  }
//
//  public void updateWizardCount(int count) {
//    wizardCounter.setText("Wizard Step Counter: " + count);
//  }
//}
