package com.sgo.depanalyze.ftp;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.sgo.depanalyze.enums.OnlineServer;
import com.sgo.depanalyze.util.Constants;
import com.sgo.depanalyze.util.ServerStatusController;
import com.sgo.depanalyze.util.SystemProperties;

/**
 * A factory for creating AppJarDownloader objects.
 */
public class AppJarDownloaderFactory {
    static Logger logger = Logger.getLogger(AppJarDownloaderFactory.class);

    /**
     * Creates a new AppJarDownloader object.
     * 
     * @return the abstract app jar downloader
     */
    public static AbstractAppJarDownloader createDefaultAppJarDownloader() {
        AbstractAppJarDownloader appJarDownloader = new DefaultAppJarDownloader();
        String paramValue = null;
        String onlineServer = ServerStatusController.getOnlineServer();
        logger.info("ONLINE SERVER FOUND : " + onlineServer);
        if (OnlineServer.SERVER_1.toString().equals(onlineServer)) {
            paramValue = SystemProperties.getParameterValue(Constants.APP_BE_SERVER1_HOSTNAME);
            if (StringUtils.hasText(paramValue)) {
                appJarDownloader.setBeHostName(paramValue);
            }
            //
            paramValue = SystemProperties.getParameterValue(Constants.APP_BE_SERVER1_USERNAME);
            if (StringUtils.hasText(paramValue)) {
                appJarDownloader.setBeUserName(paramValue);
            }
            //
            paramValue = SystemProperties.getParameterValue(Constants.APP_BE_SERVER1_PASSWORD);
            if (StringUtils.hasText(paramValue)) {
                appJarDownloader.setBePassword(paramValue);
            }
            paramValue = SystemProperties.getParameterValue(Constants.APP_FE_SERVER1_HOSTNAME);
            if (StringUtils.hasText(paramValue)) {
                appJarDownloader.setFeHostName(paramValue);
            }
            //
            paramValue = SystemProperties.getParameterValue(Constants.APP_FE_SERVER1_USERNAME);
            if (StringUtils.hasText(paramValue)) {
                appJarDownloader.setFeUserName(paramValue);
            }
            //
            paramValue = SystemProperties.getParameterValue(Constants.APP_FE_SERVER1_PASSWORD);
            if (StringUtils.hasText(paramValue)) {
                appJarDownloader.setFePassword(paramValue);
            }
            //
            paramValue = SystemProperties.getParameterValue(Constants.FELIBS_REMOTE_PATH_1);
            if (StringUtils.hasText(paramValue)) {
                appJarDownloader.setFeLibsRemotePathname(paramValue);
            }
            //
        } else {
            paramValue = SystemProperties.getParameterValue(Constants.APP_BE_SERVER2_HOSTNAME);
            if (StringUtils.hasText(paramValue)) {
                appJarDownloader.setBeHostName(paramValue);
            }
            //
            paramValue = SystemProperties.getParameterValue(Constants.APP_BE_SERVER2_USERNAME);
            if (StringUtils.hasText(paramValue)) {
                appJarDownloader.setBeUserName(paramValue);
            }
            //
            paramValue = SystemProperties.getParameterValue(Constants.APP_BE_SERVER2_PASSWORD);
            if (StringUtils.hasText(paramValue)) {
                appJarDownloader.setBePassword(paramValue);
            }
            paramValue = SystemProperties.getParameterValue(Constants.APP_FE_SERVER2_HOSTNAME);
            if (StringUtils.hasText(paramValue)) {
                appJarDownloader.setFeHostName(paramValue);
            }
            //
            paramValue = SystemProperties.getParameterValue(Constants.APP_FE_SERVER2_USERNAME);
            if (StringUtils.hasText(paramValue)) {
                appJarDownloader.setFeUserName(paramValue);
            }
            //
            paramValue = SystemProperties.getParameterValue(Constants.APP_FE_SERVER2_PASSWORD);
            if (StringUtils.hasText(paramValue)) {
                appJarDownloader.setFePassword(paramValue);
            }
            //
            paramValue = SystemProperties.getParameterValue(Constants.FELIBS_REMOTE_PATH_2);
            if (StringUtils.hasText(paramValue)) {
                appJarDownloader.setFeLibsRemotePathname(paramValue);
            }
            //
        }
        //
        paramValue = SystemProperties.getParameterValue(Constants.PRM_FELIBS_LOCAL_PATH);
        if (StringUtils.hasText(paramValue)) {
            appJarDownloader.setFeLibsLocalPathName(paramValue);
        }        
        //
        paramValue = SystemProperties.getParameterValue(Constants.PRM_BELIBS_REMOTE_PATH);
        if (StringUtils.hasText(paramValue)) {
            appJarDownloader.setBeLibsRemotePathname(paramValue);
        }
        //
        paramValue = SystemProperties.getParameterValue(Constants.PRM_BELIBS_LOCAL_PATH);
        if (StringUtils.hasText(paramValue)) {
            appJarDownloader.setBeLibsLocalPathName(paramValue);
        }
        //
        paramValue = SystemProperties.getParameterValue(Constants.PRM_THIRDPARTY_REMOTE_PATH);
        if (StringUtils.hasText(paramValue)) {
            appJarDownloader.setThirdPartyLibsRemotePathname(paramValue);
        }
        //
        paramValue = SystemProperties.getParameterValue(Constants.PRM_THIRDPARTY_LOCAL_PATH);
        if (StringUtils.hasText(paramValue)) {
            appJarDownloader.setThirdPartyLocalPathName(paramValue);
        }
        //
        paramValue = SystemProperties.getParameterValue(Constants.PRM_BEINF_REMOTE_PATH);
        if (StringUtils.hasText(paramValue)) {
            appJarDownloader.setBeInfRemotePathName(paramValue);
        }
        //
        paramValue = SystemProperties.getParameterValue(Constants.PRM_BEINF_LOCAL_PATH);
        if (StringUtils.hasText(paramValue)) {
            appJarDownloader.setBeInfLocalPathName(paramValue);
        }      
        //
        return appJarDownloader;
    }
}
