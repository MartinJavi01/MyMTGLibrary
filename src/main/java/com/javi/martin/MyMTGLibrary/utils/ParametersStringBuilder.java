package com.javi.martin.MyMTGLibrary.utils;

import java.util.HashMap;

public class ParametersStringBuilder {

    public static String getParamsString(HashMap<String, String> params) {
        StringBuilder finalString = new StringBuilder("?");

        params.forEach((v, k) -> {
            finalString.append(v).append("=").append(k);
            finalString.append("&");
        });

        return (finalString.isEmpty()) ? finalString.toString() : finalString.deleteCharAt(finalString.length() -1).toString();
    }
}
