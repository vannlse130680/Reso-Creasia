package com.creasia.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class LoginRequest {

    private LoginRequest() {
        // This class is not publicly instantiable
    }

    public static class ServerLoginRequest {
        @Expose
        @SerializedName("ca_user_name")
        private String userName;

        @Expose
        @SerializedName("ca_user_password")
        private String password;

        public ServerLoginRequest(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;

            ServerLoginRequest that = (ServerLoginRequest) object;
            if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
            return password != null ? password.equals(that.password) : that.password == null;
        }

        @Override
        public int hashCode() {
            int result = userName != null ? userName.hashCode() : 0;
            result = 31 * result + (password != null ? password.hashCode() : 0);
            return result;
        }
    }
}
