package com.kkwonsy.sample.web.web;

import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfileControllerUnitTest {


    @Test
    public void real_profile() throws Exception {
        // given
        String expectedProfile = "real";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("oauth");
        env.addActiveProfile("real-db");

        ProfileController profileController = new ProfileController(env);

        // when
        String profile = profileController.profile();
        // then

        assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    public void real_profile_없으면_첫번째가조회() throws Exception {
        // given
        String expectedProfile = "oauth";
        MockEnvironment environment = new MockEnvironment();

        environment.addActiveProfile(expectedProfile);
        environment.addActiveProfile("real-db");

        ProfileController profileController = new ProfileController(environment);

        // when
        String profile = profileController.profile();
        // then
        assertThat(profile).isEqualTo(expectedProfile);
    }
}