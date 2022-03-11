package bermuda.database.managers;

import bermuda.MainCloverMMO;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

public class Api {

    private static String URL = "https://kirogames.com/clovermmo/"; // URL GOES HERE

    /**
     * Send all the data to the Api.
     */
    public void sendAllSeverData() {
        HashMap<String, String> allData = MainCloverMMO.getDbHelper().getDataForApi();
        sendHttpRequest("index/uploadServerData", allData, false);
    }

    /**
     * Method to make an HTTP request to the API
     * @param path to where we want to make the Http call CAN BE NULL
     * @param params params we want to send with the request, can be NULL
     * @return result : String
     */
    public String sendHttpRequest(String path, HashMap<String, String> params, boolean sendAuthToken){
        String response = "";
        try {
            URL url = new URL(null, (path != null ? URL+path : URL),  new sun.net.www.protocol.https.Handler());
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
//            SSLSocketFactory sslSocketFactory = buildSslSocketFactory();
//            conn.setSSLSocketFactory(sslSocketFactory);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            if(sendAuthToken){
                //todo add access token
//                conn.setRequestProperty("Authorization", "Bearer " + );
            }

            if(params != null){
                conn.setDoOutput(true);
                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                out.writeBytes(buildParams(params));
                out.flush();
                out.close();
            }
            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                response = buildResponse(conn.getInputStream());
            } else {
                response = buildResponse(conn.getErrorStream());
            }
//            conn.disconnect();
        } catch(IOException e ){
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Build self-signed SSLSocketFactory so we can use our own SSL certificate which allows us to use HTTPS.
     * @return SSLFactorySocket
     */
    private SSLSocketFactory buildSslSocketFactory(){
        try {
            InputStream stream = getClass().getResourceAsStream("/keystore/server.jks");
            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(stream, "LHhyxofjw3Tg".toCharArray());

            String algo = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(algo);
            tmf.init(keyStore);

            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, tmf.getTrustManagers(), null);

            return context.getSocketFactory();
        } catch(KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException | KeyManagementException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Simple function to build a param map into a string.
     * @param params map of params you want to refactor to a string.
     * @return result : String
     *
     * @author Daryl
     */
    private String buildParams(HashMap<String, String> params){
        StringBuilder result = new StringBuilder();
        try {
            for(Map.Entry<String, String> entry : params.entrySet()){
                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                result.append("&");
            }
        } catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return result.toString().length() > 0
                ? result.toString().substring(0, result.toString().length() - 1)
                : result.toString();
    }

    /**
     * Build response string from given input.
     * @param stream of either Input or Error stream.
     * @return response : String
     *
     * @author Daryl
     */
    private String buildResponse(InputStream stream){
        String response = "";
        try {
            StringBuilder result = new StringBuilder();
            BufferedReader rd = new BufferedReader(new InputStreamReader(stream));
            String line;
            while((line = rd.readLine()) != null){
                result.append(line);
            }
            rd.close();
            response = result.toString();
        } catch(IOException e){
            e.printStackTrace();
        }
        return response;
    }
}
