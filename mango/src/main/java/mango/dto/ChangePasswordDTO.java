package mango.dto;

public class ChangePasswordDTO {
    String newPassword;
    String oldPassword;

    public ChangePasswordDTO(String newPassword, String oldPassword) {
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getVerification() {
        return oldPassword;
    }

    public void setVerification(String verification) {
        this.oldPassword = verification;
    }
}
