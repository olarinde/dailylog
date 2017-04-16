/**
 * Koweg Software Solutions Limited
 *
 */

package org.koweg.demo.dailylog.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author olarinde.ajai@gmail.com
 *
 */
public class ApplicationInfo {

    @JsonProperty(value = "name")
    private final String appName;

    @JsonProperty("version")
    private final String appVersion;

    @JsonProperty("status")
    private final String appStatus;

    public ApplicationInfo(String appName, String apppVersion, String appStatus) {
        this.appName = appName;
        this.appVersion = apppVersion;
        this.appStatus = appStatus;
    }

    public String getAppName() {
        return appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public String getAppStatus() {
        return appStatus;
    }

    @Override
    public String toString() {
        return "ApplicationInfo [appName=" + appName + ", appVersion=" + appVersion + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((appName == null) ? 0 : appName.hashCode());
        result = prime * result + ((appVersion == null) ? 0 : appVersion.hashCode());
        return result;
    }

    public static class Builder {
        private String appName;

        private String appVersion;
        
        private String appStatus;

        public Builder withAppName(String appName) {
            this.appName = appName;
            return this;
        }

        public Builder withAppVersion(String appVersion) {
            this.appVersion = appVersion;
            return this;
        }

        public Builder withAppStatus(String appStatus) {
            this.appStatus = appStatus;
            return this;
        }

        public ApplicationInfo build() {
            return new ApplicationInfo(appName, appVersion, appStatus);
        }
    }

}
