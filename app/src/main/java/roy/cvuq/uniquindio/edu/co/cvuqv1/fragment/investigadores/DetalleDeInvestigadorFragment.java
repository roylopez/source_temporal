package roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.investigadores;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import roy.cvuq.uniquindio.edu.co.cvuqv1.R;
import roy.cvuq.uniquindio.edu.co.cvuqv1.vo.Investigador;


public class DetalleDeInvestigadorFragment extends Fragment {
    private TextView txtNombre;
    private TextView txtGenero;
    private TextView txtNacionalidad;
    private TextView txtEmail;
    private TextView txtCategoria;
    private TextView txtFormacion;
    private TextView txtLink;
    private Investigador investigador;

    public DetalleDeInvestigadorFragment() {
        // Required empty public constructor
    }

    public static DetalleDeInvestigadorFragment newInstance(Investigador inv) {
        DetalleDeInvestigadorFragment detalle = new DetalleDeInvestigadorFragment();
        detalle.setInvestigador(inv);
        Bundle bundle = new Bundle();
        detalle.setArguments(bundle);
        detalle.setRetainInstance(true);
        return detalle;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detalle_de_investigador, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        txtNombre = (TextView) getView().findViewById(R.id.txtNombre);
        txtNombre.setText(investigador.getNombre().concat(" ").concat(investigador.getApellido()));

        txtGenero = (TextView) getView().findViewById(R.id.txtGenero);
        txtGenero.setText(investigador.getGenero());

        txtCategoria = (TextView) getView().findViewById(R.id.txtCategoria);
        txtCategoria.setText(investigador.getCategoria());

        txtEmail = (TextView) getView().findViewById(R.id.txtEmail);
        txtEmail.setText(investigador.getEmail());

        txtFormacion = (TextView) getView().findViewById(R.id.txtFormacion);
        txtFormacion.setText(investigador.getFormacion());

        txtLink = (TextView) getView().findViewById(R.id.txtLinkcvlac);
        txtLink.setText(investigador.getLink());

        txtNacionalidad = (TextView) getView().findViewById(R.id.txtNacionalidad);
        txtNacionalidad.setText(investigador.getNacionalidad());
    }

    public Investigador getInvestigador() {
        return investigador;
    }

    public void setInvestigador(Investigador investigador) {
        this.investigador = investigador;
    }
}
