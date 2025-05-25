package za.co.rideloop.Domain;

import jakarta.persistence.*;


/**
 * Admin.java
 * Admin Model Class
 *
 * @Author: Ndyebo Qole 210018615
 * @Student Number: 210018615
 **/
@Entity
@Table(name = "admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private int adminID;

    @Column(name = "username"/*, nullable = false, unique = true*/)
    private String userName;

    @Column(name = "password"/*, nullable = false*/)
    private String password;


    protected Admin() {

    }
    private  Admin(adminBuilder builder) {
        this.adminID= builder.adminID;
        this.userName = builder.userName;
        this.password = builder.password;



    }

      public int getAdminID() {
        return adminID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }


    public static class adminBuilder {
        private int adminID;
        private String userName;
        private String password;

        public adminBuilder(){

        }
        public adminBuilder(int adminID, String userName, String password) {
            this.adminID = adminID;
            this.userName = userName;
            this.password = password;

        }

        public adminBuilder setAdminID(int adminID) {
            this.adminID = adminID;
            return this;
        }

        public adminBuilder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public adminBuilder setPassword(String password) {
            this.password = password;
            return this;
        }



        public Admin build(){
            return new Admin(this);
        }
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminID=" + adminID +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
