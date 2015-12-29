package com.redrumming.thecreaturehub.util;

import java.math.BigInteger;
import java.text.NumberFormat;

/**
 * Created by ME on 11/20/2015.
 */
public class NumberFormatterUtil {

    private final int THOUSAND = 1000;
    private final int MILLION = 1000000;
    private final int BILLION = 1000000000;

    private final String PLURAL_VIEWS = " views";
    String SINGULAR_VIEW = " view";

    public String formatViewCount(BigInteger value){

        if(value.intValue() > 1){

            return prettyNumber(value) + PLURAL_VIEWS;

        }else{

            return value + SINGULAR_VIEW;
        }
    }

    public String formatSubscriberCount(BigInteger value){

        String plural = " subscribers";
        String singular = " subscriber";

        if(value.intValue() > 1){

            return prettyNumber(value) + plural;

        }else{

            return value + singular;
        }
    }

    public String formatLikeCount(BigInteger value){

        // thousand
        if(value.intValue() > THOUSAND - 1){

            return String.valueOf(value.intValue() / THOUSAND) + "K";

            //Million
        }else if( value.intValue() > MILLION - 1){

            return String.valueOf(value.intValue() / MILLION) + "M";

            //billion
        }else if(value.intValue() > BILLION - 1){

            return String.valueOf(value.intValue() / BILLION) + "B";
        }

        //below a thousand
        return String.valueOf(value.intValue());
    }

    public String formatShortView(BigInteger value){

        if(value.intValue() > 1){

            return formatLikeCount(value) + PLURAL_VIEWS;

        }else {

            return String.valueOf(value.intValue()) + SINGULAR_VIEW;
        }
    }

    private String prettyNumber(BigInteger value) {

        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setGroupingUsed(true);

        return numberFormat.format(value);
    }
}
