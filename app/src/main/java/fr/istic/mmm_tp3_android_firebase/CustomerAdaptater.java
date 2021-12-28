package fr.istic.mmm_tp3_android_firebase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomerAdaptater extends BaseAdapter {
    private LayoutInflater flater;
    private List<Departement> list = new ArrayList<>();
    //private String departementName;
    private int listItemLayoutResource;
    private int departementItemNameId;

    public CustomerAdaptater(FirstFragment context, int listItemLayoutResource,
                             int departementItemNameId, List<Departement> list) {
        this.listItemLayoutResource = listItemLayoutResource;
        this.departementItemNameId=departementItemNameId;
        this.list = list;
        this.flater = context.getLayoutInflater();
    }

    @Override
    public int getCount() {
        if(this.list == null)  {
            return 0;
        }
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public long getItemId(int position) {
        Departement departement = (Departement) this.getItem(position);
        return departement.getId();
        // return position; (Return position if you need).
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Departement oneDepartement = (Departement) getItem(position);

        // Example: @listItemLayoutResource: R.layout.spinner_item_layout_resource
        // (File: layout/spinner_item_layout_resourcerce.xml)
        View rowView = this.flater.inflate(this.listItemLayoutResource, null,true);


        TextView textViewItemName = (TextView) rowView.findViewById(this.departementItemNameId);
        textViewItemName.setText(oneDepartement.getDepartementName());
        return rowView;
    }
}
