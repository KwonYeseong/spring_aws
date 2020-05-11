package com.yeseong.admin.web;

import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfileControllerUnitTest {

    @Test
    public void inquiry_real_profile() {
        String expectedProfile = "real";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("oauth");
        env.addActiveProfile("real-db");

        ProfileContoller contoller = new ProfileContoller(env);

        String profile = contoller.profile();

        assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    public void inquiry_real_profile_first() {
        String expectedProfile = "oauth";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("real-db");

        ProfileContoller contoller = new ProfileContoller(env);

        String profile = contoller.profile();

        assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    public void inquiry_real_profile_default() {
        String expectedProfile = "default";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);

        ProfileContoller contoller = new ProfileContoller(env);

        String profile = contoller.profile();

        assertThat(profile).isEqualTo(expectedProfile);
    }
}
