package roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.investigadores;


import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import roy.cvuq.uniquindio.edu.co.cvuqv1.R;
import roy.cvuq.uniquindio.edu.co.cvuqv1.adapter.AdaptadorDeInvestigador;
import roy.cvuq.uniquindio.edu.co.cvuqv1.vo.Grupo;
import roy.cvuq.uniquindio.edu.co.cvuqv1.vo.Investigador;

public class ListaInvestigadoresFragment extends Fragment implements
        AdaptadorDeInvestigador.OnClickAdaptadorDeInvestigador,
        View.OnClickListener {

    private AdaptadorDeInvestigador adaptador;
    private RecyclerView listadoDeInvestigadores;
    private ArrayList<Investigador> investigadores;
    private OnInvestigadorSeleccionadoListener listener;
    private FloatingActionButton agregarInvestigador;

    public ListaInvestigadoresFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        crearInvestigadores();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lista_investigadores, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        agregarInvestigador = (FloatingActionButton) getView().findViewById(R.id.add_searcher);
        agregarInvestigador.setOnClickListener(this);
        agregarInvestigador.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#144d0b")));

        listadoDeInvestigadores = (RecyclerView) getView().findViewById(R.id.listaInvestigadores);
        adaptador = new AdaptadorDeInvestigador(investigadores, this, "integrante");
        listadoDeInvestigadores.setAdapter(adaptador);
        listadoDeInvestigadores.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        if (getView().findViewById(R.id.detalle_investigador) != null) {
            TabDetalleDeInvestigadorFragment fragmentoDetalle = new TabDetalleDeInvestigadorFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("investigador", investigadores.get(0));
            fragmentoDetalle.setArguments(bundle);
            drawFragmentDetalle(fragmentoDetalle);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_searcher) {
            listener.onAddInvestigator();
        }
    }

    @Override
    public void onClickInvestigatorPosition(int pos, String tipo) {
        if (getView().findViewById(R.id.detalle_investigador) != null) {
            TabDetalleDeInvestigadorFragment fragmentoDetalle = new TabDetalleDeInvestigadorFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("investigador", investigadores.get(pos));
            fragmentoDetalle.setArguments(bundle);
            drawFragmentDetalle(fragmentoDetalle);
        } else {
            listener.onInvestigadorSeleccionado(investigadores.get(pos));
        }
    }

    private void drawFragmentDetalle(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(R.id.detalle_investigador, fragment)
                .commit();
    }

    public interface OnInvestigadorSeleccionadoListener {
        void onInvestigadorSeleccionado(Investigador investigador);

        void onAddInvestigator();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity;
        if (context instanceof Activity) {
            activity = (Activity) context;
            try {
                listener = (OnInvestigadorSeleccionadoListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " debe implementar la interfaz OnInvestigadorSeleccionadoListener");
            }
        }
    }

    private void crearInvestigadores() {

        investigadores = new ArrayList();
        List<String> lineasG1 = new ArrayList<>(), lineasG2 = new ArrayList<>(), lineasG3 = new ArrayList<>();

        Grupo g1 = new Grupo();
        g1.setCategoria("Categoria C");
        g1.setNombre("Grupo de Investigación en Redes, Información y Distribuciones GRID");
        //g1.setNombre("GEOIDE-G62");
        g1.setEmail("grid@micorreo.com");
        g1.setSigla("GRID");
        g1.setLineasInvestigacion(lineasG2);

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

        investigadores.add(i1);
        investigadores.add(i2);
        investigadores.add(i3);
        investigadores.add(i4);
        investigadores.add(i5);


        g1.getInvestigadores().add(i2);
        g1.getInvestigadores().add(i3);
        g1.getInvestigadores().add(i4);

        i2.setGrupo(g1);

    }
}
