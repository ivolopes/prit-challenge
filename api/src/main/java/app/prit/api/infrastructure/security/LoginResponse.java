package app.prit.api.infrastructure.security;

public class LoginResponse {
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "{\"access_token\": \""+accessToken+"\"}";
    }
}
