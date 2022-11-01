package com.lnngle.hycyh.ui.app.standalone;

import java.util.ResourceBundle;

import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.osgi.framework.Bundle;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	@Override
	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		Rectangle displaySize = Display.getCurrent().getPrimaryMonitor().getBounds();
		configurer.setInitialSize(new Point(displaySize.width * 3 / 4, displaySize.height * 3 / 4));
		configurer.setShowCoolBar(false);
		configurer.setShowStatusLine(true);
		configurer.setShowPerspectiveBar(true);
		configurer.setShowProgressIndicator(true);
		Bundle bundle = Platform.getBundle(StandaloneConstants.BUNDLE_NAME);
		ResourceBundle resourceBundle = Platform.getResourceBundle(bundle);
		configurer.setTitle(resourceBundle.getString(StandaloneConstants.KEY_PRODUCT_NAME) + " " + bundle.getVersion());
	}
	
	@Override
	public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
		return new ApplicationActionBarAdvisor(configurer);
	}
}
