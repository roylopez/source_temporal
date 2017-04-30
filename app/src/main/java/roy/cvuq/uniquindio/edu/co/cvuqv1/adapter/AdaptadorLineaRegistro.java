package roy.cvuq.uniquindio.edu.co.cvuqv1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.ArrayList;

import roy.cvuq.uniquindio.edu.co.cvuqv1.R;
import roy.cvuq.uniquindio.edu.co.cvuqv1.fragment.registro_investigador.LineFragment;

public class AdaptadorLineaRegistro extends RecyclerView.Adapter<AdaptadorLineaRegistro.LineViewHolder> {

    private static ArrayList<String> lineas;
    private static OnClickAdapterLine listener;
    private static ArrayList<String> lineasSeleccionadas;

    public AdaptadorLineaRegistro(LineFragment lineFragment, ArrayList<String> lineas, ArrayList<String> lineasSeleccionadas) {
        this.lineas = lineas;
        this.lineasSeleccionadas = lineasSeleccionadas;
        this.listener = (OnClickAdapterLine) lineFragment;
    }

    @Override
    public LineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.resumen_linea_investigacion, parent, false);
        LineViewHolder lineVH = new LineViewHolder(itemView);
        lineVH.setIsRecyclable(false);
        return lineVH;
    }

    @Override
    public void onBindViewHolder(LineViewHolder holder, int position) {
        String linea = lineas.get(position);
        holder.bindLine(linea);
    }

    @Override
    public int getItemCount() {
        return lineas.size();
    }

    public static class LineViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView txtLinea;
        private CheckBox chkLinea;

        public LineViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtLinea = (TextView) itemView.findViewById(R.id.nombre_linea);
            chkLinea = (CheckBox) itemView.findViewById(R.id.check_linea);
            chkLinea.setOnClickListener(this);
        }

        public void bindLine(String linea) {
            if (lineasSeleccionadas.contains(linea)) {
                chkLinea.setChecked(true);
            }
            txtLinea.setText(linea);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.check_linea:
                    if(chkLinea.isChecked()){
                        listener.onClickPositionLine(getAdapterPosition());
                    }else {
                        listener.onClickRemoveLine(lineas.get(getAdapterPosition()));
                    }
                    break;

                default:
                    break;
            }
        }
    }

    public interface OnClickAdapterLine {
        public void onClickPositionLine(int position);
        public void onClickRemoveLine(String line);
    }
}

