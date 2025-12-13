package steps;

import org.junit.After;
import org.junit.Before;

import utils.BaseCommonMethodsClass;

import java.net.MalformedURLException;

public class Hooks extends BaseCommonMethodsClass {

    @Before
    public void start() throws Exception {
        launchBrowser();
    }

    @After
    public void end(){
        closeBrowser();
    }
}

