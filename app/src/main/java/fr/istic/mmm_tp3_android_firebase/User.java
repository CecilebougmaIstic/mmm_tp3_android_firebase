package fr.istic.mmm_tp3_android_firebase;

import java.util.Date;

public class User {
    protected String firstName;
    protected String lastName;
    protected String birthday;
    protected String birthdayPlaceArray;

    public User(String firstName, String lastName, String birthday, String birthdayPlaceArray) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.birthdayPlaceArray = birthdayPlaceArray;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        birthday = birthday;
    }

    public String getBirthdayPlaceArray() {
        return birthdayPlaceArray;
    }

    public void setBirthdayPlaceArray(String birthdayPlaceArray) {
        this.birthdayPlaceArray = birthdayPlaceArray;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", Birthday=" + birthday +
                ", birthdayPlace='" + birthdayPlaceArray + '\'' +
                '}';
    }
}
