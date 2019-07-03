package com.example.demo;

import com.amazon.pwain.PWAINBackendSDK;
import com.amazon.pwain.PWAINException;
import com.amazon.pwain.types.MerchantConfiguration;
import com.amazon.pwain.types.PWAINConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class VerifySignature {



    public static boolean verifyResponse(HttpServletRequest request, HttpServletResponse response) {



        Properties prop = new Properties();

        InputStream input;

        try {



            String sellerId = "AZ4WQCLDT2DF0";

            String accessKey = "AKIAJAKG6LME27HVCD3A";

            String secretKey = "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";



            HashMap<String, String> verificationParameters = new HashMap<String, String>();

            Enumeration<String> parameterNames = request.getParameterNames();



            while (parameterNames.hasMoreElements()) {

                String paramName = parameterNames.nextElement();

                String[] paramValue = request.getParameterValues(paramName);

                verificationParameters.put(paramName, paramValue[0]);

            }



            PWAINBackendSDK backendSDK = new PWAINBackendSDK(new MerchantConfiguration.Builder()

                    .withAwsAccessKeyId(accessKey).withAwsSecretKeyId(secretKey).withSellerId(sellerId).build());



            backendSDK.verifySignature(verificationParameters);

            System.out.println("returning verification as true");

            // Signature verification successful please return "true" as response

            return true;

        } catch (PWAINException e) {

            // Signature verification failed please return "false" as response

            System.out.println("returning verification as false");

            return false;

        } catch (Exception ex) {

            ex.printStackTrace();

            return false;

        }



    }



}

