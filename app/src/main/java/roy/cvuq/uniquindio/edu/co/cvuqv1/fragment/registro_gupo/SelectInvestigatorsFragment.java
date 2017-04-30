package roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.registro_gupo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import roy.cvuq.uniquindio.edu.co.cvuqv1.R;
import roy.cvuq.uniquindio.edu.co.cvuqv1.adapter.AdaptadorSeleccionInvestigadores;
import roy.cvuq.uniquindio.edu.co.cvuqv1.vo.Investigador;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectInvestigatorsFragment extends DialogFragment implements
        View.OnClickListener,
        AdaptadorSeleccionInvestigadores.OnClickAdapterInvestigator {

    private Button setInvestigators;
    private RecyclerView rvInvestigadores;
    private ArrayList<Investigador> investigadores;
    private ArrayList<Investigador> investigadoresSeleccionados;

    public SelectInvestigatorsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        investigadores = obtenerInvestigadores();
        investigadoresSeleccionados = new ArrayList<Investigador>();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AdaptadorSeleccionInvestigadores adaptadorInvestigadores = new AdaptadorSeleccionInvestigadores(this, investigadores, investigadoresSeleccionados);
        rvInvestigadores.setAdapter(adaptadorInvestigadores);
        rvInvestigadores.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_select_investigators, container, false);

        rvInvestigadores = (RecyclerView) vista.findViewById(R.id.lista_investigadores);
        setInvestigators = (Button) vista.findViewById(R.id.button_set_investigators);
        setInvestigators.setOnClickListener(this);

        investigadoresSeleccionados = getArguments().getParcelableArrayList("investigadoresSeleccionados");

        return vista;
    }

    @Override
    public void onClickPositionInvestigator(int position) {
        investigadoresSeleccionados.add(investigadores.get(position));
    }

    @Override
    public void onClickRemoveInvestigator(Investigador investigador) {
        for (int i = 0; i < investigadoresSeleccionados.size(); i++) {
            if (investigadoresSeleccionados.get(i).getEmail().equals(investigador.getEmail())) {
                investigadoresSeleccionados.remove(investigadoresSeleccionados.get(i));
                break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        getDialog().dismiss();
        ((AddGroupFragment) getFragmentManager().findFragmentByTag(getResources().getString(R.string.tag_fragment_addgroup))).showInvestigators(investigadoresSeleccionados);
    }

    private ArrayList<Investigador> obtenerInvestigadores() {
        Investigador i1 = new Investigador();
        Investigador i2 = new Investigador();
        Investigador i3 = new Investigador();
        Investigador i4 = new Investigador();
        Investigador i5 = new Investigador();

        i1.setNombre("Pepito");
        i1.setApellido("Perez");
        i1.setGenero("Masculino");
        i1.setFormacion("Ing Sistemas");
        i1.setEmail("pepito@uq.com");
        i1.setLink("www.cvlac.com/pepito");
        i1.setCategoria("Categoria 2");
        i1.setNacionalidad("Colombia");

        i2.setNombre("Pepita");
        i2.setGenero("Femenino");
        i2.setFormacion("Ing Sistemas y yap");
        i2.setLink("www.cvlac.com/pepita");
        i2.setEmail("pepita@uq.com");
        i2.setCategoria("Categoria 1");
        i2.setNacionalidad("Colombia");
        i2.setApellido("Lopez");

        i3.setNombre("Lupita");
        i3.setApellido("Luna");
        i3.setGenero("Femenino");
        i3.setCategoria("Categoria 3");
        i3.setFormacion("Ing de Sistemas");
        i3.setLink("www.cvlac.com/lupita");
        i3.setEmail("lupita@uq.com");
        i3.setNacionalidad("Colombia");

        i4.setNombre("Marcus");
        i4.setApellido("Cornelious");
        i4.setEmail("marcus@uq.com");
        i4.setGenero("Masculino");
        i4.setNacionalidad("Colombia");
        i4.setCategoria("Categoria 2");
        i4.setFormacion("Ing de Sistemas");
        i4.setLink("www.cvlac.com/marcus");

        i5.setNombre("Serena");
        i5.setNacionalidad("Colombia");
        i5.setGenero("Femenino");
        i5.setEmail("serena@uq.com");
        i5.setFormacion("Ing de Sistemas");
        i5.setCategoria("Categoria 1");
        i5.setLink("www.cvlac.com/serena");
        i5.setApellido("Williams");

        ArrayList<Investigador> investigadores = new ArrayList<Investigador>();
        investigadores.add(i1);
        investigadores.add(i2);
        investigadores.add(i3);
        investigadores.add(i4);
        investigadores.add(i5);

        return investigadores;
    }

}
