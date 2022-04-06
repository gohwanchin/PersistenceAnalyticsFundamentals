package sg.edu.nus.day23.model;

import java.sql.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Friend {
    private String email;
    private String name;
    private String phone;
    private String status;
    private Date dob;
    private String passphrase;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPassphrase() {
        return passphrase;
    }

    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
    }

    public static Friend create(SqlRowSet rs) {
        Friend f = new Friend();
        f.setEmail(rs.getString("email"));
        f.setName(rs.getString("name"));
        f.setPhone(rs.getString("phone"));
        f.setStatus(rs.getString("status"));
        f.setDob(rs.getDate("dob"));
        f.setPassphrase(rs.getString("passphrase"));
        return f;
    }
}
