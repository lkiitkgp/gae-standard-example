package com.example.demo;

import com.amazon.pwain.PWAINBackendSDK;
import com.amazon.pwain.PWAINException;
import com.amazon.pwain.types.MerchantConfiguration;
import com.amazon.pwain.types.OperationName;
import com.amazon.pwain.types.PWAINConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class signAndEncryptForOperation {



    public static String signAndEncrypt(HttpServletRequest request, HttpServletResponse response) {



        Properties prop = new Properties();

        InputStream input;

        try {



            String sellerId = "AZ4WQCLDT2DF0";

            String accessKey = "AKIAJAKG6LME27HVCD3A";

            String secretKey = "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";

            String amazonOrderId = request.getParameter(PWAINConstants.TRANSACTION_ID);
            String transactionType = request.getParameter(PWAINConstants.TRANSACTION_ID_TYPE);



            HashMap<String, String> getChargeParameters = new HashMap<String, String>();
            getChargeParameters.put(PWAINConstants.TRANSACTION_ID,amazonOrderId);
            getChargeParameters.put(PWAINConstants.TRANSACTION_ID_TYPE,transactionType);
            getChargeParameters.put(PWAINConstants.OPERATION_NAME,"SIGN_AND_ENCRYPT_GET_CHARGE_STATUS_REQUEST");





            PWAINBackendSDK backendSDK = new PWAINBackendSDK(new MerchantConfiguration.Builder()

                    .withAwsAccessKeyId(accessKey).withAwsSecretKeyId(secretKey).withSellerId(sellerId).build());

            String signNEncryptOperation = backendSDK.signAndEncryptParameters(getChargeParameters);

            System.out.println("returning verification as true");

            // Signature verification successful please return "true" as response

            return signNEncryptOperation;

        } catch (PWAINException e) {

            // Signature verification failed please return "false" as response

            System.out.println("returning verification as false");

            return e.toString();

        } catch (Exception ex) {

            ex.printStackTrace();

            return ex.toString();

        }



    }



}

