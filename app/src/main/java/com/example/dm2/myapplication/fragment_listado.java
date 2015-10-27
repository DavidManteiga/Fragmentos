package com.example.dm2.myapplication;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
public class fragment_listado extends Fragment {
    private Correo[] datos =
            new Correo[]{
                    new Correo("Persona 1", "Asunto del correo 1", "Texto del correo 1"),
                    new Correo("Persona 2", "Asunto del correo 2", "Texto del correo 2"),
                    new Correo("Persona 3", "Asunto del correo 3", "Texto del correo 3"),
                    new Correo("Persona 4", "Asunto del correo 4", "Texto del correo 4"),
                    new Correo("Persona 5", "Asunto del correo 5", "Texto del correo 5"),
                    new Correo("Persona 6", "Asunto del correo 6", "Texto del correo 6"),
                    new Correo("Persona 7", "Asunto del correo 7", "Texto del correo 7"),
                    new Correo("Persona 8", "Asunto del correo 8", "Texto del correo 8"),
                    new Correo("Persona 9", "Asunto del correo 9", "Texto del correo 9")};
    private ListView lstListado;
    private CorreosListener listener;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listado, container, false);
    }
    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        lstListado = (ListView)getView().findViewById(R.id.LstListado);
        lstListado.setAdapter(new AdaptadorCorreos(this));
        lstListado.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                if (listener!=null) {
                    listener.onCorreoSeleccionado((Correo)lstListado.getAdapter().getItem(pos));
                }
            }
        });
    }
    class AdaptadorCorreos extends ArrayAdapter<Correo> {
        Activity context;
        AdaptadorCorreos(Fragment context) {
            super(context.getActivity(), R.layout.listitem_correo, datos);
            this.context = context.getActivity();
        }
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.listitem_correo, null);
            TextView lblDe = (TextView)item.findViewById(R.id.LblDe);
            lblDe.setText(datos[position].getDe());
            TextView lblAsunto = (TextView)item.findViewById(R.id.LblAsunto);
            lblAsunto.setText(datos[position].getAsunto());
            return(item);
        }
    }
    public interface CorreosListener {
        void onCorreoSeleccionado(Correo c);
    }
    public void setCorreosListener(CorreosListener listener) {
        this.listener=listener;
    }
}