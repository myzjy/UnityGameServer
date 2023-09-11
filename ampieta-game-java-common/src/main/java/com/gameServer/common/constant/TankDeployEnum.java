package com.gameServer.common.constant;

/**
 * @author Administrator
 * @version 1.0
 * @since 2022/8/14 23:10
 */
public enum TankDeployEnum {
    dev,
    pro,
    ;

    public static void InitDefaultEnv() {
        var profile = "spring.profiles.active";
        if (System.getProperty(profile) == null) {
            System.setProperty(profile, "dev");
        }
    }
}
