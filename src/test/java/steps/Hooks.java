package steps;

import org.junit.After;
import org.junit.Before;

import utils.BaseCommonMethodsClass;

public class Hooks extends BaseCommonMethodsClass {

    @Before
    public void start() throws InterruptedException {
        launchBrowser();
    }

    @After
    public void end(){
        closeBrowser();
    }
}

