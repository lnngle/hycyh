package com.lnngle.hycyh.ui.core.ui.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class ProjectInfoWizardPage extends WizardPage {
	public static final String PROJECT_INFO_WIZARD_PAGE_NAME = "ProjectInfoWizardPage";

	public ProjectInfoWizardPage(String pageName) {
		super(pageName);
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		
		setControl(composite);
	}

}
