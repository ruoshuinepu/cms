package com.smarthaier.shiro.session;

import org.apache.shiro.session.mgt.SessionValidationScheduler;

public class SpringSessionValidationScheduler implements SessionValidationScheduler {
    public boolean isEnabled() {
        return false;
    }

    public void enableSessionValidation() {

    }

    public void disableSessionValidation() {

    }
}
