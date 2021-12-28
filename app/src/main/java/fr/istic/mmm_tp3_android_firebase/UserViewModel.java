package fr.istic.mmm_tp3_android_firebase;

import fr.istic.mmm_tp3_android_firebase.BR;
import androidx.databinding.Bindable;
import androidx.databinding.BaseObservable;

public class UserViewModel extends BaseObservable {

    protected String firstName;
    protected String lastName;
    protected String birthday;
    protected String birthdayPlaceArray;

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);

    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);

    }


    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
        //notifyPropertyChanged(BR.birthday);
    }

    @Bindable
    public String getBirthdayPlaceArray() {
        return birthdayPlaceArray;
    }

    public void setBirthdayPlaceArray(String birthdayPlace) {
        this.birthdayPlaceArray = birthdayPlace;
        notifyPropertyChanged(BR.birthdayPlaceArray);

    }

}
