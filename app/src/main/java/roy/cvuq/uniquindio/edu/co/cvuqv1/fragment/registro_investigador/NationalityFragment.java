package roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.registro_investigador;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

import roy.cvuq.uniquindio.edu.co.cvuqv1.R;
import roy.cvuq.uniquindio.edu.co.cvuqv1.adapter.AdaptadorNacionalidad;

/**
 * A simple {@link Fragment} subclass.
 */
public class NationalityFragment extends DialogFragment implements
        AdaptadorNacionalidad.OnClickAdapterNationality {

    private RecyclerView rvNacionalidades;
    private ArrayList<String> nacionalidades;

    public NationalityFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nacionalidades = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.nacionalidades)));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AdaptadorNacionalidad adaptadorNacionalidad = new AdaptadorNacionalidad(this, nacionalidades);
        rvNacionalidades.setAdapter(adaptadorNacionalidad);
        rvNacionalidades.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_nationality, container, false);
        rvNacionalidades = (RecyclerView) v.findViewById(R.id.lista_nacionalidades);
        return v;
    }

    @Override
    public void onClickPosition(int position) {
        getDialog().dismiss();
        String selectedNationality = nacionalidades.get(position);
        ((AddInvestigatorFragment) getFragmentManager().findFragmentByTag(getResources().getString(R.string.tag_fragment_addinvestigator))).showNationality(selectedNationality);
    }
}
