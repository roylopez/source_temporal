package roy.cvuq.uniquindio.edu.co.cvuqv1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import roy.cvuq.uniquindio.edu.co.cvuqv1.R;
import roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.registro_investigador.NationalityFragment;

/**
 * Created by USUARIO on 20/04/2017.
 */
public class AdaptadorNacionalidad extends RecyclerView.Adapter<AdaptadorNacionalidad.NationalityViewHolder> {

    private ArrayList<String> nacionalidades;
    private static OnClickAdapterNationality listener;

    public AdaptadorNacionalidad(NationalityFragment nationalityFragment, ArrayList<String> nacionalidades) {
        this.nacionalidades = nacionalidades;
        this.listener = (OnClickAdapterNationality) nationalityFragment;
    }

    @Override
    public NationalityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.resumen_nacionalidad, parent, false);
        NationalityViewHolder nationalityVH = new NationalityViewHolder(itemView);
        return nationalityVH;
    }

    @Override
    public void onBindViewHolder(NationalityViewHolder holder, int position) {
        String nacionalidad = nacionalidades.get(position);
        holder.bindNationality(nacionalidad);
    }

    @Override
    public int getItemCount() {
        return nacionalidades.size();
    }

    public static class NationalityViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView txtNacionalidad;
        private CheckBox chkNacionalidad;

        public NationalityViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtNacionalidad = (TextView) itemView.findViewById(R.id.nombre_nacionalidad);
            chkNacionalidad = (CheckBox) itemView.findViewById(R.id.check_nacionalidad);
            chkNacionalidad.setOnClickListener(this);
        }

        public void bindNationality(String nacionalidad) {
            txtNacionalidad.setText(nacionalidad);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.check_nacionalidad:
                    listener.onClickPosition(getAdapterPosition());
                    break;

                case R.id.resumen_nacionalidad:
                    chkNacionalidad.setChecked(true);
                    listener.onClickPosition(getAdapterPosition());
                    break;

                default:
                    break;
            }
        }
    }

    public interface OnClickAdapterNationality {
        public void onClickPosition(int position);
    }
}
