package com.redrumming.thecreaturehub.retrofit.utils;

/**
 * Created by ME on 3/25/2016.
 */
public class SimpleJoiner {

    public static String join(String[] strings){

        StringBuffer stringBuffer = new StringBuffer();

        for(int i = 0; i < strings.length; i++){

            stringBuffer.append(strings[i]);

            if(i != (strings.length -1)){

                stringBuffer.append(",");
            }
        }

        return stringBuffer.toString();
    }
}
