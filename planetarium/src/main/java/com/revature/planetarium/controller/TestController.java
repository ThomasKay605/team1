package com.revature.planetarium.controller;

import io.javalin.http.Context;

import com.revature.planetarium.utility.Setup;

public class TestController {
    
    public void resetDatabase(Context ctx) {
        Setup.main(new String[]{});
        ctx.status(200);
    }

}
