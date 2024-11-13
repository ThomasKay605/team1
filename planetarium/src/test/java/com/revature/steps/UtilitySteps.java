package com.revature.steps;

import com.revature.utility.Setup;

import io.cucumber.java.Before;

public class UtilitySteps {
    
    @Before
    public void setupForEach() {
        Setup.main(new String[]{});
    }

}
