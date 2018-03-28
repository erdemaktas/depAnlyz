/**
 * @author U079754 A. Emre Zorlu
 * @since Jul 24, 2016
 * @return ServerStatusController
 * 
 */
package com.sgo.depanalyze.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.StringUtils;

import com.sgo.depanalyze.enums.Environment;
import com.sgo.depanalyze.enums.OnlineServer;
import com.sgo.depanalyze.enums.ServerStatus;

/**
 * @author U079754 A. Emre Zorlu
 * @since Jul 24, 2016
 */
public class ServerStatusController {
    /** The Constant SEPERATOR. */
    public static final String SEPERATOR = "\\;";

    public static String getOnlineServer() {
        if (Environment.TEST.toString().equals(SystemProperties.getEnvironment()) || Environment.UAT.toString().equals(SystemProperties.getEnvironment())) {
            // server 1 control
            String server1Urls = SystemProperties.getParameterValue(Constants.APP_SERVER1_URLS);
            List<String> server1Status = new ArrayList<String>();
            if (StringUtils.hasText(server1Urls)) {
                String[] server1UrlList = server1Urls.split(SEPERATOR);
                for (String serverUrl : server1UrlList) {
                    server1Status.add(executePost(serverUrl));
                }
            }
            // server 2 control
            String server2Urls = SystemProperties.getParameterValue(Constants.APP_SERVER2_URLS);
            List<String> server2Status = new ArrayList<String>();
            if (StringUtils.hasText(server2Urls)) {
                String[] server2UrlList = server2Urls.split(SEPERATOR);
                for (String serverUrl : server2UrlList) {
                    server2Status.add(executePost(serverUrl));
                }
            }
            return compareServers(server1Status, server2Status);
        }
        return OnlineServer.SERVER_1.name();
    }

    private static String compareServers(List<String> server1Status, List<String> server2Status) {
        if (CollectionUtils.isNotEmpty(server1Status) && CollectionUtils.isEmpty(server2Status)) {
            return OnlineServer.SERVER_1.name();
        } else if (CollectionUtils.isNotEmpty(server2Status) && CollectionUtils.isEmpty(server1Status)) {
            return OnlineServer.SERVER_2.name();
        }
        String server1 = "", server2 = "", tempStatus1 = server1Status.get(0).trim(), tempStatus2 = server2Status.get(0).trim();
        for (int i = 0; i < server1Status.size(); i++) {
            server1 = server1Status.get(i).trim();
            server2 = server2Status.get(i).trim();
            if (!(tempStatus1.equals(server1) && tempStatus2.equals(server2))) {
                return OnlineServer.SERVER_1.name();
            }
        }
        if (ServerStatus.ONLINE.getServerStatus().equals(server1) && ServerStatus.OFFLINE.getServerStatus().equals(server2)) {
            return OnlineServer.SERVER_1.name();
        } else if (ServerStatus.ONLINE.getServerStatus().equals(server2) && ServerStatus.OFFLINE.getServerStatus().equals(server1)) {
            return OnlineServer.SERVER_2.name();
        } else {
            return OnlineServer.SERVER_1.name();
        }
    }

    private static String executePost(String targetURL) {
        HttpURLConnection connection = null;
        try {
            String urlParameters = "fName=" + URLEncoder.encode("???", "UTF-8") + "&lName=" + URLEncoder.encode("???", "UTF-8");
            URL url = new URL(targetURL);
            // open connection
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            // Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.close();
            // Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
