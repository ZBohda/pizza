package com.pizza.domain.dto;

public class RegisterFormDTO {

    private String login;
    private String password;
    private String confirmPassword;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String city;
    private String address;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegisterFormDTO)) return false;

        RegisterFormDTO that = (RegisterFormDTO) o;

        if (!login.equals(that.login)) return false;
        if (!password.equals(that.password)) return false;
        if (!email.equals(that.email)) return false;
        if (!firstName.equals(that.firstName)) return false;
        if (!lastName.equals(that.lastName)) return false;
        if (!phone.equals(that.phone)) return false;
        if (!city.equals(that.city)) return false;
        return address.equals(that.address);

    }

    @Override
    public int hashCode() {
        int result = login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + address.hashCode();
        return result;
    }
}
