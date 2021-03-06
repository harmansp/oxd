package org.xdi.oxd.common.params;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Yuriy Zabrovarnyy
 * @version 0.9, 06/06/2016
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class RsCheckAccessParams implements HasProtectionAccessTokenParams {

    @JsonProperty(value = "oxd_id")
    private String oxdId;
    @JsonProperty(value = "rpt")
    private String rpt;
    @JsonProperty(value = "path")
    private String path;
    @JsonProperty(value = "http_method")
    private String httpMethod;
    @JsonProperty(value = "protection_access_token")
    private String protectionAccessToken;

    public RsCheckAccessParams() {
    }

    public String getProtectionAccessToken() {
        return protectionAccessToken;
    }

    public void setProtectionAccessToken(String protectionAccessToken) {
        this.protectionAccessToken = protectionAccessToken;
    }

    public String getOxdId() {
        return oxdId;
    }

    public void setOxdId(String oxdId) {
        this.oxdId = oxdId;
    }

    public String getRpt() {
        return rpt;
    }

    public void setRpt(String rpt) {
        this.rpt = rpt;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("RsCheckAccessParams");
        sb.append("{oxdId='").append(oxdId).append('\'');
        sb.append(", rpt='").append(rpt).append('\'');
        sb.append(", path='").append(path).append('\'');
        sb.append(", http_method='").append(httpMethod).append('\'');
        sb.append(", protectionAccessToken='").append(protectionAccessToken).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
