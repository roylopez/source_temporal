package roy.cvuq.uniquindio.edu.co.cvuqv1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import roy.cvuq.uniquindio.edu.co.cvuqv1.R;
import roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.registro_gupo.LiderFragment;
import roy.cvuq.uniquindio.edu.co.cvuqv1.vo.Investigador;

/**
 * Created by USUARIO on 24/04/2017.
 */

public class AdaptadorSeleccionLider extends RecyclerView.Adapter<AdaptadorSeleccionLider.LiderViewHolder>{

    public ArrayList<Investigador> investigadores;
    public static OnClickAdapterSeleccionLider listener;

    public AdaptadorSeleccionLider(LiderFragment liderFragment, ArrayList<Investigador> investigadores){
        this.investigadores = investigadores;
        this.listener = (OnClickAdapterSeleccionLider) liderFragment;
    }

    @Override
    public LiderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.resumen_lider, parent, false);
        LiderViewHolder liderVH = new LiderViewHolder(itemView);
        return liderVH;
    }

    @Override
    public void onBindViewHolder(LiderViewHolder holder, int position) {
        Investigador investigador = investigadores.get(position);
        holder.bindLider(investigador);
    }

    @Override
    public int getItemCount() {
        return investigadores.size();
    }


    public static class LiderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView txtLider;
        private CheckBox chkLider;

        public LiderViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtLider = (TextView) itemView.findViewById(R.id.nombre_investigador);
            chkLider = (CheckBox) itemView.findViewById(R.id.check_lider);
            chkLider.setOnClickListener(this);
        }

        public void bindLider(Investigador investigador){
            txtLider.setText(investigador.getNombre().concat(" ").concat(investigador.getApellido()));
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.check_lider:
                    listener.onClickPosition(getAdapterPosition());
                    break;

                case R.id.resumen_lider:
                    chkLider.setChecked(true);
                    listener.onClickPosition(getAdapterPosition());
                    break;

                default:
                    break;
            }
        }
    }

    public interface OnClickAdapterSeleccionLider{
        public void onClickPosition(int position);
    }
}
