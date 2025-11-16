package mx.fca.aviones;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // UI
    private RecyclerView listaAviones;
    private Button btnNext;
    private Button btnPrev;
    private TextView txtInfo;

    // Lógica
    private Plano plano;
    private AvionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias UI
        listaAviones = findViewById(R.id.listaAviones);
        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);
        txtInfo = findViewById(R.id.txtInfo);

        // Estado inicial del plano
        plano = Planificador.crearRutaInicial();

        // Configurar GridLayoutManager con número de columnas = plano.col + 1
        listaAviones.setLayoutManager(new GridLayoutManager(this, plano.col + 1));

        // Mostrar estado inicial
        actualizarGrid();
        actualizarInfo();

        // Botón Siguiente paso
        btnNext.setOnClickListener(v -> {
            plano = plano.next();
            actualizarGrid();
            actualizarInfo();
        });

        // Botón Paso anterior
        btnPrev.setOnClickListener(v -> {
            plano = plano.prev(); // retroceder simulación
            actualizarGrid();     // reconstruir tablero con el estado recuperado
            actualizarInfo();
        });
    }

    // Construye la cuadrícula completa (aviones, colisiones y celdas vacías)
    private void actualizarGrid() {
        List<Object> elementos = new ArrayList<>();
        for (int fila = 0; fila <= plano.row; fila++) {
            for (int col = 0; col <= plano.col; col++) {
                Object celda = null;

                // Buscar avión en esta celda
                for (Avion avion : plano.aviones) {
                    if (avion.getX() == col && avion.getY() == fila) {
                        celda = avion;
                        break;
                    }
                }

                // Buscar colisión en esta celda
                for (Colision colision : plano.colisiones) {
                    if (colision.getX() == col && colision.getY() == fila) {
                        celda = colision;
                        break;
                    }
                }

                elementos.add(celda); // puede ser Avion, Colision o null
            }
        }

        adapter = new AvionAdapter(elementos);
        listaAviones.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    // Actualiza el TextView con pasos y colisiones
    private void actualizarInfo() {
        txtInfo.setText("Pasos: " + plano.getPasos() +
                " | Colisiones: " + plano.getNumeroColisiones());
    }
}
