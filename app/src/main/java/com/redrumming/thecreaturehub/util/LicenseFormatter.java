package com.redrumming.thecreaturehub.util;

/**
 * Created by ME on 12/18/2015.
 */
public class LicenseFormatter {

    private static final String STANDARD_LICENSE_VALUE = "youtube";
    private static final String CREATIVE_COMMON_LICENSE_VALUE = "creativeCommon";

    private static final String STANDARD_LICENSE_READABLE = "Standard YouTube License";
    private static final String CREATIVE_COMMONS_LICENSE_READABLE = "Creative Commons Attribution license (reuse allowed)";

    public static String formatLicense(String value){

        String license = "";

        if(value.equals(STANDARD_LICENSE_VALUE)){

            license = STANDARD_LICENSE_READABLE;
        }

        if(value.equals(CREATIVE_COMMON_LICENSE_VALUE)){

           license = CREATIVE_COMMONS_LICENSE_READABLE;
        }

        return license;
    }
}
