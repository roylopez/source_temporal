package roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.busqueda;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import roy.cvuq.uniquindio.edu.co.cvuqv1.R;
import roy.cvuq.uniquindio.edu.co.cvuqv1.vo.Grupo;
import roy.cvuq.uniquindio.edu.co.cvuqv1.vo.Investigador;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchDialogFragment extends DialogFragment implements View.OnClickListener {

    private ArrayList<Grupo> resultadoGrupos;
    private ArrayList<Investigador> resultadoInvestigadores;
    private Button buscar;
    private EditText txtGrupo;
    private EditText txtInvestigador;
    private EditText txtLineas;

    ArrayList<Grupo> grupos;
    List<Investigador> investigadores;

    private OnSearchListener listener;

    public SearchDialogFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.fragment_search_dialog, null);
        buscar = (Button) view.findViewById(R.id.button_search);
        buscar.setOnClickListener(this);
        txtGrupo = (EditText) view.findViewById(R.id.grupo_search);
        txtLineas = (EditText) view.findViewById(R.id.lineas_search);
        txtInvestigador = (EditText) view.findViewById(R.id.investigador_search);
        resultadoGrupos = new ArrayList<>();
        resultadoInvestigadores = new ArrayList<>();

        crearGrupos();

        builder.setView(view);
        return builder.create();
    }

    public interface OnSearchListener {
        void onSearchListener(ArrayList<Grupo> grupos, ArrayList<Investigador> investigadores);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity;
        if (context instanceof Activity) {
            activity = (Activity) context;
            try {
                listener = (OnSearchListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + "Implemente la interfaz OnSearchListener");
            }
        }
    }

    private void crearGrupos() {

        grupos = new ArrayList();

        investigadores = new ArrayList();
        List<String> lineasG1 = new ArrayList<>(), lineasG2 = new ArrayList<>(), lineasG3 = new ArrayList<>();

        Investigador lider = new Investigador();
        lider.setNombre("Manu");
        lider.setApellido("Lalu");
        lider.setGenero("Masculino");
        lider.setFormacion("Ing Sistemas");
        lider.setEmail("pepito@uq.com");
        lider.setLink("www.cvlac.com/pepito");
        lider.setCategoria("Categoria 2");
        lider.setNacionalidad("Colombia");
        lider.setLineasInvestigacion((ArrayList) lineasG1);

        Grupo g1 = new Grupo();
        g1.setCategoria("Categoria C");
        g1.setNombre("Grupo de Investigación en Redes, Información y Distribuciones GRID");
        //g1.setNombre("GEOIDE-G62");
        g1.setEmail("grid@micorreo.com");
        g1.setSigla("GRID");
        g1.setFoto(12300);
        g1.setLider(lider);
        lider.setGrupo(g1);
        g1.setInvestigadores(investigadores);
        g1.setLink("www.cvlac.com/grid");
        g1.setLineasInvestigacion(lineasG1);


        Grupo g2 = new Grupo();
        g2.setNombre("GEOIDE-G62");
        g2.setSigla("GEOIDE-G62");
        g2.setCategoria("Categoria D");
        g2.setLink("www.cvlac.com/geoide");
        g2.setLineasInvestigacion(lineasG2);
        g2.setLider(lider);
        g2.setInvestigadores(investigadores);

        Grupo g3 = new Grupo();
        g3.setNombre("SINFOCI");
        g3.setSigla("SINFOCI");
        g3.setCategoria("Categoria D");
        g3.setLink("www.cvlac.com/SINFOCI");
        g3.setLineasInvestigacion(lineasG2);
        g3.setLider(lider);
        g3.setInvestigadores(investigadores);

        Grupo g4 = new Grupo();
        g4.setNombre("CIDERA");
        g4.setSigla("CIDERA");
        g4.setCategoria("Categoria D");
        g4.setLink("www.cvlac.com/CIDERA");
        g4.setLineasInvestigacion(lineasG2);
        g4.setLider(lider);
        g4.setInvestigadores(investigadores);


        grupos.add(g1);
        grupos.add(g2);
        grupos.add(g3);
        grupos.add(g4);


        lineasG1.add("Bases de datos");
        lineasG1.add("Redes de computadoras");
        lineasG1.add("Mineria");

        lineasG2.add("Bases de datos");
        lineasG2.add("Mineria");
        lineasG2.add("Gestion del conocimiento");

        lineasG3.add("Redes de computadoras");
        lineasG3.add("Ingenieria de software");
        lineasG3.add("Mineria");

        Investigador i1 = new Investigador();
        Investigador i2 = new Investigador();
        Investigador i3 = new Investigador();
        Investigador i4 = new Investigador();
        Investigador i5 = new Investigador();

        i1.setNombre("Pepito");
        i1.setApellido("Perez");
        i1.setGrupo(g1);
        i1.setGenero("Masculino");
        i1.setFormacion("Ing Sistemas");
        i1.setEmail("pepito@uq.com");
        i1.setLink("www.cvlac.com/pepito");
        i1.setCategoria("Categoria 2");
        i1.setNacionalidad("Colombia");
        i1.setLineasInvestigacion((ArrayList) lineasG1);

        i2.setNombre("Pepita");
        i2.setGenero("Femenino");
        i2.setFormacion("Ing Sistemas y yap");
        i2.setGrupo(g1);
        i2.setLink("www.cvlac.com/pepita");
        i2.setEmail("pepita@uq.com");
        i2.setCategoria("Categoria 1");
        i2.setNacionalidad("Colombia");
        i2.setApellido("Lopez");
        i2.setLineasInvestigacion((ArrayList) lineasG1);

        i3.setNombre("Lupita");
        i3.setApellido("Luna");
        i3.setGenero("Femenino");
        i3.setCategoria("Categoria 3");
        i3.setGrupo(g1);
        i3.setFormacion("Ing de Sistemas");
        i3.setLink("www.cvlac.com/lupita");
        i3.setEmail("lupita@uq.com");
        i3.setNacionalidad("Colombia");
        i3.setLineasInvestigacion((ArrayList) lineasG1);

        i4.setNombre("Marcus");
        i4.setApellido("Cornelious");
        i4.setEmail("marcus@uq.com");
        i4.setGenero("Masculino");
        i4.setNacionalidad("Colombia");
        i4.setGrupo(g1);
        i4.setCategoria("Categoria 2");
        i4.setFormacion("Ing de Sistemas");
        i4.setLink("www.cvlac.com/marcus");
        i4.setLineasInvestigacion((ArrayList) lineasG2);

        i5.setNombre("Serena");
        i5.setNacionalidad("Colombia");
        i5.setGenero("Femenino");
        i5.setEmail("serena@uq.com");
        i5.setGrupo(g1);
        i5.setFormacion("Ing de Sistemas");
        i5.setCategoria("Categoria 1");
        i5.setLink("www.cvlac.com/serena");
        i5.setApellido("Williams");
        i5.setLineasInvestigacion((ArrayList) lineasG2);

        investigadores.add(lider);
        investigadores.add(i1);
        investigadores.add(i2);
        investigadores.add(i3);
        investigadores.add(i4);
        investigadores.add(i5);

    }

    private boolean buscarEnListas(String grupo, String investigador, String linea) {

        if (grupo.equals("") && investigador.equals("") && linea.equals("")) {
            Toast.makeText(getActivity(), "Ingrese algún termino de búsqueda", Toast.LENGTH_LONG).show();
            return false;
        } else if (!investigador.equals("") && grupo.equals("") && linea.equals("")) {
            //Búsqueda solo investigador
            for (int i = 0; i < investigadores.size(); i++) {
                if (investigadores.get(i).getNombre().contains(investigador)) {
                    resultadoInvestigadores.add(investigadores.get(i));
                }
            }
        } else if (!grupo.equals("") && investigador.equals("") && linea.equals("")) {
            //Búsqueda solo grupo
            for (int i = 0; i < grupos.size(); i++) {
                if (grupos.get(i).getNombre().contains(grupo)) {
                    resultadoGrupos.add(grupos.get(i));
                }
            }
        } else if (!linea.equals("") && investigador.equals("") && grupo.equals("")) {
            for (int i = 0; i < grupos.size(); i++) {
                boolean contiene = buscarLineas(grupos.get(i).getLineasInvestigacion(), linea);
                if (contiene) {
                    resultadoGrupos.add(grupos.get(i));
                }
            }
            for (int i = 0; i < investigadores.size(); i++) {
                boolean contiene = buscarLineas(investigadores.get(i).getLineasInvestigacion(), linea);
                if (contiene){
                    resultadoInvestigadores.add(investigadores.get(i));
                }
            }
        }else if (!grupo.equals("") && !linea.equals("") && investigador.equals("")){
            for (int i = 0; i < grupos.size(); i++) {
                boolean contiene = buscarLineas(grupos.get(i).getLineasInvestigacion(),linea);
                if (contiene && grupos.get(i).getNombre().contains(grupo)){
                    resultadoGrupos.add(grupos.get(i));
                }
            }
        }else if (!grupo.equals("") && !investigador.equals("") && linea.equals("")){
            for (int i = 0; i < grupos.size(); i++) {
                boolean contiene = buscarInvestigador(grupos.get(i).getInvestigadores(),investigador);
                if (contiene && grupos.get(i).getNombre().contains(grupo)){
                    resultadoGrupos.add(grupos.get(i));
                }
            }
            for (int i = 0; i < investigadores.size(); i++) {
                if (investigadores.get(i).getNombre().equals(investigador) &&
                        investigadores.get(i).getGrupo().getNombre().contains(grupo)){
                    resultadoInvestigadores.add(investigadores.get(i));
                }
            }
        }else if (!investigador.equals("") && !linea.equals("") && grupo.equals("")){
            for (int i = 0; i < investigadores.size(); i++) {
                boolean contiene = buscarLineas(investigadores.get(i).getLineasInvestigacion(),linea);
                if (contiene && investigadores.get(i).getNombre().contains(investigador)){
                    resultadoInvestigadores.add(investigadores.get(i));
                }
            }
        }else{
            for (int i = 0; i < grupos.size(); i++) {
                boolean contieneL = buscarLineas(grupos.get(i).getLineasInvestigacion(),linea);
                boolean contieneI = buscarInvestigador(grupos.get(i).getInvestigadores(), investigador);
                if (contieneI && contieneL && grupos.get(i).getNombre().contains(grupo)){
                    resultadoGrupos.add(grupos.get(i));
                }
            }
            for (int i = 0; i < investigadores.size(); i++) {
                boolean contieneL = buscarLineas(investigadores.get(i).getLineasInvestigacion(),linea);
                if (contieneL && investigadores.get(i).getNombre().contains(investigador) &&
                        investigadores.get(i).getGrupo().getNombre().contains(grupo)){
                    resultadoInvestigadores.add(investigadores.get(i));
                }
            }
        }
        return true;
    }

    public boolean buscarLineas(List<String> lista, String linea) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).contains(linea)) {
                return true;
            }
        }
        return false;
    }

    public boolean buscarInvestigador(List<Investigador> lista, String investigador){
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombre().contains(investigador)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        boolean f = buscarEnListas(txtGrupo.getText().toString(), txtInvestigador.getText().toString(), txtLineas.getText().toString());
        if (resultadoGrupos.size() == 0 && resultadoInvestigadores.size() == 0) {
            Toast.makeText(getActivity(), "No existen resultados para la búsqueda", Toast.LENGTH_LONG).show();
        } else if (f) {
            listener.onSearchListener(resultadoGrupos, resultadoInvestigadores);
            Log.v("Grupos", resultadoGrupos.size() + "");
            Log.v("Investigadores", resultadoInvestigadores.size() + "");
            getDialog().dismiss();
        }
    }
}