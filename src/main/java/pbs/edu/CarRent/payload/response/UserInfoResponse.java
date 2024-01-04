package pbs.edu.CarRent.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserInfoResponse {
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
    private String accessToken;
    private String tokenType = "Bearer";
    private Long employeeId;


    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public UserInfoResponse(Long id, String username, String email, List<String> roles, String accessToken, Long employeeId) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.accessToken = accessToken;
        this.employeeId = employeeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
