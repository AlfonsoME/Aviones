package mx.fca.aviones;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AvionHolder extends RecyclerView.ViewHolder {
    ImageView imgAvion;
    public AvionHolder(@NonNull View itemView) {
        super(itemView);
        imgAvion = itemView.findViewById(R.id.imgAvion);
    }
}
