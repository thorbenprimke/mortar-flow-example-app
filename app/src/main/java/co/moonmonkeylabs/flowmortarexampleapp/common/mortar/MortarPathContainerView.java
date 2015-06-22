package co.moonmonkeylabs.flowmortarexampleapp.common.mortar;

import android.content.Context;
import android.util.AttributeSet;

import co.moonmonkeylabs.flowmortarexampleapp.R;
import co.moonmonkeylabs.flowmortarexampleapp.common.flow.FramePathContainerView;
import co.moonmonkeylabs.flowmortarexampleapp.common.flow.SimplePathContainer;
import flow.path.Path;

/**
 * @author Lukasz Piliszczuk <lukasz.pili@gmail.com>
 */
public class MortarPathContainerView extends FramePathContainerView {

    public MortarPathContainerView(Context context, AttributeSet attrs) {
        super(
            context,
            attrs,
            new SimplePathContainer(
                R.id.screen_switcher_tag,
                Path.contextFactory(new BasicMortarContextFactory(new ScreenScoper()))));
    }
}
