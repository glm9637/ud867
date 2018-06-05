package com.udacity.gradle.builditbigger.backend;

import com.example.jokedispenser.JokeDispenser;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /** A simple endpoint method that returns a Joke */
    @ApiMethod(name = "dispenseJoke")
    public MyBean dispenseJoke() {
        MyBean response = new MyBean();
        JokeDispenser dispenser = new JokeDispenser();
        response.setData(dispenser.getJoke());
        return response;
    }

}
