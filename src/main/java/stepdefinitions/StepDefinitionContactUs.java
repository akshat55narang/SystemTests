package stepdefinitions;

import managers.RootInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pageobjects.ContactUsPage;

public class StepDefinitionContactUs {
    private RootInitializer rootInitializer;
    private ContactUsPage contactUsPage;

    private static final Logger log = LogManager.getLogger(StepDefinitionContactUs.class);

    public StepDefinitionContactUs(RootInitializer rootInitializer) {
        this.rootInitializer = rootInitializer;
        contactUsPage = rootInitializer.getPageManager().getContactUsPage();
    }

}
