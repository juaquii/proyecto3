package app.model.entity;

import app.model.serializator.Security;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    protected String dni;
    protected String name;
    protected String surnames;
    protected String phone;
    protected String mail;
    protected String password;
    protected int admin;

    public User(String dni, String name, String surnames, String phone, String mail, String password, int admin) {
        this.dni = dni;
        this.name = name;
        this.surnames = surnames;
        this.phone = phone;
        this.mail = mail;
        try {
            setPassword(password);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        this.admin = admin;
    }

    public User() {
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws NoSuchAlgorithmException {
        this.password = Security.hashPassword(password);
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public static boolean validatePassword(String password) {
        boolean validateResult;
        Pattern passwordPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!.#_()%*?&])[A-Za-z\\d@$!.#_()%*?&]{8,}$");
        Matcher passwordMatcher = passwordPattern.matcher(password);
        if (passwordMatcher.matches()) {
            validateResult = true;
        } else {
            validateResult = false;
        }
        return validateResult;
    }

    public static boolean validateMail(String mail) {
        boolean validateResult;
        Pattern mailPattern = Pattern.compile("[A-Za-z0-9]+@+(gmail|outlook|hotmail)\\.(com|es)");
        Matcher mailMatcher = mailPattern.matcher(mail);
        if (mailMatcher.matches()) {
            validateResult = true;
        } else {
            validateResult = false;
        }
        return validateResult;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEquals;
        if (this == obj) {
            isEquals = true;
        } else if (obj == null || getClass() != obj.getClass()) {
            isEquals = false;
        } else {
            User user = (User) obj;
            return Objects.equals(dni, user.dni) && Objects.equals(mail, user.mail) && Objects.equals(phone, user.phone);
        }
        return isEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, name, surnames, phone, mail, password, admin);
    }

    @Override
    public String toString() {
        return "User[" +
                "dni = " + dni +
                ", name = " + name +
                ", surnames = " + surnames +
                ", phone = " + phone +
                ", mail = " + mail +
                ", password = " + password +
                ", isAdmin = " + admin +
                ']';
    }
}


