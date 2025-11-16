package mx.fca.aviones;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AvionAdapter extends RecyclerView.Adapter<AvionHolder> {

    private List<Object> elementos; // puede contener Avion, Colision o null

    public AvionAdapter(List<Object> elementos) {
        this.elementos = elementos;
    }

    @NonNull
    @Override
    public AvionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.avion_view, parent, false);
        return new AvionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AvionHolder holder, int position) {
        Object item = elementos.get(position);

        if (item instanceof Avion) {
            Avion avion = (Avion) item;
            holder.imgAvion.setImageResource(avion.getImage());
            holder.imgAvion.setVisibility(View.VISIBLE);
        } else if (item instanceof Colision) {
            Colision colision = (Colision) item;
            holder.imgAvion.setImageResource(colision.getImage());
            holder.imgAvion.setVisibility(View.VISIBLE);
        } else {
            // Celda vacía → fondo transparente
            holder.imgAvion.setImageResource(android.R.color.transparent);
            holder.imgAvion.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return elementos.size();
    }
}

