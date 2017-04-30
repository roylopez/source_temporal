package roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.registro_investigador;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import roy.cvuq.uniquindio.edu.co.cvuqv1.R;

/**
 * Aun se reinician: la nacionalidad y la lista de lines
 */

public class AddInvestigatorFragment extends Fragment implements
        View.OnClickListener {

    public static final int PICK_IMAGE = 1;

    private Spinner grupoInvestigacion;
    private Spinner categoriaInvestigador;
    private TextView txtNacionalidad;
    private TextView txtLineasInvestigacion;
    private ArrayList<String> lineasSeleccionadas;

    private TextView nacionalidad;

    private LinearLayout agregarImagen;
    private TextView textoImagen;
    private ImageView imagenInvestigador;
    private Uri selectedImagePath;

    public AddInvestigatorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lineasSeleccionadas = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_add_investigator, container, false);

        grupoInvestigacion = (Spinner) vista.findViewById(R.id.spinner_grupo_investigacion);
        categoriaInvestigador = (Spinner) vista.findViewById(R.id.spinner_categoria);

        String[] categorias = getResources().getStringArray(R.array.categorias_investigador);
        ArrayAdapter<String> adaptadorCategorias = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categorias);
        adaptadorCategorias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriaInvestigador.setAdapter(adaptadorCategorias);

        /*Obtener los grupos de investigacion registrados*/
        String[] gruposInvestigacion = {"Grupo de prueba 1", "Grupo de prueba 2", "Grupo de prueba 3"};
        ArrayAdapter<String> adaptadorGrupos = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, gruposInvestigacion);
        adaptadorGrupos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        grupoInvestigacion.setAdapter(adaptadorGrupos);

        txtNacionalidad = (TextView) vista.findViewById(R.id.nacionalidad_investigador);
        txtLineasInvestigacion = (TextView) vista.findViewById(R.id.lineas_investigacion);
        txtNacionalidad.setOnClickListener(this);
        txtLineasInvestigacion.setOnClickListener(this);

        nacionalidad = (TextView) vista.findViewById(R.id.nacionalidad_investigador);
        agregarImagen = (LinearLayout) vista.findViewById(R.id.agregar_imagen);
        agregarImagen.setOnClickListener(this);
        imagenInvestigador = (ImageView) vista.findViewById(R.id.image_investigator);
        textoImagen = (TextView) vista.findViewById(R.id.texto_fotografia_add_investigator);

        return vista;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nacionalidad_investigador:
                showDialogNationality(AddInvestigatorFragment.class.getName());
                break;
            case R.id.lineas_investigacion:
                showDialogLine(AddInvestigatorFragment.class.getName());
                break;
            case R.id.agregar_imagen:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.texto_seleccionar_imagen)), PICK_IMAGE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            selectedImagePath = data.getData();
            showImage();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            nacionalidad.setText(savedInstanceState.getString("nacionalidad"));

            lineasSeleccionadas = new ArrayList<String>(savedInstanceState.getStringArrayList("lineasSeleccionadas"));
            showLines(lineasSeleccionadas);

            if (savedInstanceState.getString("referenciaFotografia") != null) {
                selectedImagePath = Uri.parse(savedInstanceState.getString("referenciaFotografia"));
                showImage();
            }

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("nacionalidad", nacionalidad.getText().toString());
        outState.putStringArrayList("lineasSeleccionadas", lineasSeleccionadas);
        if (selectedImagePath != null) {
            outState.putString("referenciaFotografia", selectedImagePath.toString());
        }
    }

    private void showImage() {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImagePath);
            imagenInvestigador.setImageBitmap(bitmap);
            textoImagen.setText(getResources().getString(R.string.texto_cambiar_fotografia));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showDialogNationality(String nombreClase) {
        NationalityFragment dialogNationality = new NationalityFragment();
        dialogNationality.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle);
        dialogNationality.show(getFragmentManager(), nombreClase);
    }

    private void showDialogLine(String nombreClase) {
        LineFragment dialogLine = new LineFragment();
        dialogLine.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("lineasSeleccionadas", lineasSeleccionadas);
        dialogLine.setArguments(bundle);
        dialogLine.show(getFragmentManager(), nombreClase);
    }

    public void showNationality(String nacionalidad) {
        txtNacionalidad.setText(nacionalidad);
    }

    public void showLines(ArrayList<String> lineas) {
        String textoLineas = new String("");
        for (int i = 0; i < lineas.size(); i++) {
            if (i == (lineas.size()) - 1) {
                textoLineas = textoLineas.concat(lineas.get(i));
            } else {
                textoLineas = textoLineas.concat(lineas.get(i)).concat(",");
            }
        }

        if (lineas.size() == 0) {
            textoLineas = getResources().getString(R.string.texto_lineas_investigacion);
        }
        txtLineasInvestigacion.setText(textoLineas);
        lineasSeleccionadas = lineas;
    }

}
