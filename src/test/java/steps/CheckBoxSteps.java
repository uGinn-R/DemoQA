package steps;

import common.CommonActions;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.Elements.CheckBoxPage;

public class CheckBoxSteps {
    CheckBoxPage page = new CheckBoxPage();

    public CheckBoxSteps verifyExpandAndCollapse() {
        page.expandAll();
        page.verifyExpanded();
        page.collapseAll();
        page.verifyCollapsed();
        return this;
    }

    public CheckBoxSteps verifyAllSelected() {
        page.expandAll();
        page.verifyExpanded();
        page.checkAll();
        page.verifyChecked();
        return this;
    }

    public CheckBoxSteps clickGroupCheckbox(CheckBoxPage.CheckBoxGroup group) {
        page.expandAll();
        page.clickGroupCheckbox(group);
        page.verifyChecked();
        return this;
    }
}
