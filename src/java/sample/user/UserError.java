/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

/**
 *
 * @author ACER
 */
public class UserError {
    private String userIdError;
    private String fullNameError;
    private String roleIdError;
    private String passwordError;
    private String confirmError;
    private String error;

    public UserError() {
        this.userIdError = "";
        this.fullNameError = "";
        this.roleIdError = "";
        this.passwordError = "";
        this.confirmError = "";
        this.error = "";
    }

    public UserError(String userIdError, String fullNameError, String roleIdError, String passwordError, String confirmError, String error) {
        this.userIdError = userIdError;
        this.fullNameError = fullNameError;
        this.roleIdError = roleIdError;
        this.passwordError = passwordError;
        this.confirmError = confirmError;
        this.error = error;
    }

    

    
    
    
    public String getUserIdError() {
        return userIdError;
    }

    public void setUserIdError(String userIdError) {
        this.userIdError = userIdError;
    }

    public String getFullNameError() {
        return fullNameError;
    }

    public void setFullNameError(String fullNameError) {
        this.fullNameError = fullNameError;
    }

    public String getRoleIdError() {
        return roleIdError;
    }

    public void setRoleIdError(String roleIdError) {
        this.roleIdError = roleIdError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    
    
}
