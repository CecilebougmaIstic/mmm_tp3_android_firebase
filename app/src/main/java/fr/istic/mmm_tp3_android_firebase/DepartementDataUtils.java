package fr.istic.mmm_tp3_android_firebase;

import java.util.ArrayList;
import java.util.List;

public class DepartementDataUtils {
    public static List<Departement> getDeparteement( ) {
        Departement ileEtVilaine = new Departement(1,"ile_Et_Vilaine");
        Departement alpesDeHauteProvence = new Departement(1,"Alpes-de-Haute-Provence");
        Departement ardeche = new Departement(1,"Ardèche");
        Departement ardennes = new Departement(1,"Ardennes");
        Departement ain = new Departement(1,"Ain");

        List<Departement> list = new ArrayList<Departement>();
        list.add(ileEtVilaine);
        list.add(alpesDeHauteProvence);
        list.add(ardeche);
        list.add(ardennes);
        list.add(ain);

        return list;
    }
}
