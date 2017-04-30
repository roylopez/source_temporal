package roy.cvuq.uniquindio.edu.co.cvuqv1.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import roy.cvuq.uniquindio.edu.co.cvuqv1.R;


public class AdaptadorDeLinea extends RecyclerView.Adapter<AdaptadorDeLinea.LineaViewHolder> {

    private ArrayList<String> lineas;
    private static OnClickAdaptadorDeLinea listener;


    public AdaptadorDeLinea(ArrayList<String> lineas, Fragment lineasInvestigacionFragment) {
        this.lineas = lineas;
        listener = (OnClickAdaptadorDeLinea) lineasInvestigacionFragment;
    }

    @Override
    public AdaptadorDeLinea.LineaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listado_lineas, parent, false);
        AdaptadorDeLinea.LineaViewHolder lineaVH = new AdaptadorDeLinea.LineaViewHolder(itemView);
        return lineaVH;
    }


    @Override
    public void onBindViewHolder(AdaptadorDeLinea.LineaViewHolder holder, int position) {
        String linea = lineas.get(position);
        holder.binLinea(linea);
    }

    public interface OnClickAdaptadorDeLinea {
        public void onClickPosition(int pos);
    }

    @Override
    public int getItemCount() {
        return lineas.size();
    }

    public static class LineaViewHolder extends RecyclerView.ViewHolder {

        private TextView txtLinea;

        public LineaViewHolder(View itemView) {
            super(itemView);
            txtLinea = (TextView)
                    itemView.findViewById(R.id.linea_investigacion);
        }

        public void binLinea(String linea) {
            txtLinea.setText(linea);
        }
    }

    public ArrayList<String> getLineas() {
        return lineas;
    }

    public void setLineas(ArrayList<String> lineas) {
        this.lineas = lineas;
    }
}
