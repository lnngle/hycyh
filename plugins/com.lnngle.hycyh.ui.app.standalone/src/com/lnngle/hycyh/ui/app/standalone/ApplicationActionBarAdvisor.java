package com.lnngle.hycyh.ui.app.standalone;

import java.util.ResourceBundle;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.osgi.framework.Bundle;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	@Override
	protected void fillMenuBar(IMenuManager menuBar) {
		Bundle bundle = Platform.getBundle(StandaloneConstants.BUNDLE_NAME);
		ResourceBundle resourceBundle = Platform.getResourceBundle(bundle);
		MenuManager fileMenu = new MenuManager(resourceBundle.getString(StandaloneConstants.KEY_MENU_FILE_LABEL),
				IWorkbenchActionConstants.M_FILE);

		menuBar.add(fileMenu);
	}
}
