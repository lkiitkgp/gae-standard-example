package com.example.demo;


import com.amazon.pwain.PWAINBackendSDK;
import com.amazon.pwain.PWAINException;
import com.amazon.pwain.types.PWAINConstants;
import com.amazon.pwain.types.MerchantConfiguration;

import java.time.LocalDate;
import java.util.HashMap;


public class Signin {

    private String sellerId = "AZ4WQCLDT2DF0";
    private String ResponseData = "test";
    public String iv;
    public String key;
    public String payload;

    public  String getResponseData(){
        return ResponseData;
    }

    public static LocalDate getLocalDate() {
        return LocalDate.now();
    }


    public Signin() {

    }

    public  void method(String Amount, String SellerOrderId) {


        String accessKey = "AKIAJAKG6LME27HVCD3A";
        String secretKey = "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";

        PWAINBackendSDK backendSDK = null;
        try {
            backendSDK = new PWAINBackendSDK(new MerchantConfiguration.Builder()
                    .withAwsAccessKeyId(accessKey).withAwsSecretKeyId(secretKey).withSellerId(sellerId).build());
        } catch (PWAINException ex) {
            ex.printStackTrace();
        }
        try {
            assert backendSDK != null;
            String response = backendSDK.signAndEncryptParameters(new HashMap<String, String>() {
                {
                    put(PWAINConstants.ORDER_TOTAL_AMOUNT, Amount);
                    put(PWAINConstants.ORDER_TOTAL_CURRENCY_CODE, "INR");
//                    put(PWAINConstants.MERCHANT_ID, sellerId);
                    put(PWAINConstants.SELLER_ORDER_ID, SellerOrderId);
                    /*Set optional parameters*/
                    //Default environment is Production.
                    //For testing in Sandbox mode, Remove when going live
                    put(PWAINConstants.IS_SANDBOX, "true");
                    //Transaction timeout in seconds
                    put(PWAINConstants.TRANSACTION_TIMEOUT, "100000");
                }
            });
            System.out.println(Amount);
            System.out.println(SellerOrderId);
            System.out.println(getLocalDate());

            ResponseData = response;

            String[] parts = response.split("&");
            String[] parts0 = parts[0].split("=");
            String[] parts1 = parts[1].split("=");
            String[] parts2 = parts[2].split("=");
            iv = parts0[1];
            key = parts1[1];
            payload = parts2[1];

        } catch (PWAINException ex) {
            ex.printStackTrace();
        }

        //Pass the signed and encrypted payload generated above back to your app



    }

}