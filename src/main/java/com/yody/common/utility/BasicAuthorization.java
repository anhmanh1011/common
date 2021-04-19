package com.yody.common.utility;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

public class BasicAuthorization {
    public static String encodeBasicAuthorization(String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(
            auth.getBytes(StandardCharsets.US_ASCII));
        return "Basic " + new String(encodedAuth);
    }

    public static String[] decodeBasicAuthorization(String basicAuth) {

        byte[] auth = Base64.decodeBase64(basicAuth.replace("Basic ", ""));
        String basicDecode = new String(auth);
        return basicDecode.split(":");

    }

    public static boolean checkBasicAuthorization(String basicAuth , String userName, String password) {
        String[] userNameAndPass = decodeBasicAuthorization(basicAuth);

        if (userNameAndPass.length != 2){
            return false;
        }

        if (userName.equals(userNameAndPass[0]) && password.equals(userNameAndPass[1])){
            return true;
        }else {
            return false;
        }
    }
}
