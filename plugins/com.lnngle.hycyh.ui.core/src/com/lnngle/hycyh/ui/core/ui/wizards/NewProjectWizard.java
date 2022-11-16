package com.lnngle.hycyh.ui.core.ui.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class NewProjectWizard extends Wizard implements INewWizard {

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		
	}

	@Override
	public boolean performFinish() {
		return false;
	}
	
	@Override
	public void addPages() {
		this.addPage(new ProjectInfoWizardPage(ProjectInfoWizardPage.PROJECT_INFO_WIZARD_PAGE_NAME));
		super.addPages();
	}

}
