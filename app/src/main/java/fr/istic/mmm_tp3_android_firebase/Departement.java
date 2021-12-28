package fr.istic.mmm_tp3_android_firebase;

public class Departement {
    private long id;
    private String departementName;

    public Departement() {
    }

    public Departement(long id, String departementName) {
        this.id = id;
        this.departementName = departementName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartementName() {
        return departementName;
    }

    public void setDepartementName(String departementName) {
        this.departementName = departementName;
    }

    @Override
    public String toString() {
        return "Departement{" +
                "id=" + id +
                ", departementName='" + departementName + '\'' +
                '}';
    }
}
