package h4g.iot4all;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by JosePC on 09/03/2018.
 */

//Clase Adapter para el recycler view
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.DispositivoViewHolder>{


    public static class DispositivoViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView name;
        TextView hora_inicio;
        ImageView photo;

        DispositivoViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            name = (TextView)itemView.findViewById(R.id.person_name);
            hora_inicio = (TextView)itemView.findViewById(R.id.person_age);
            photo = (ImageView)itemView.findViewById(R.id.person_photo);
        }
    }

    List<dispositivo> dispositivos;

    RVAdapter(List<dispositivo> dispositivos){
        this.dispositivos = dispositivos;
    }

    public int getItemCount() {
        return dispositivos.size();
    }

    @Override
    public DispositivoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
        DispositivoViewHolder pvh = new DispositivoViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(DispositivoViewHolder dispositivoViewHolder, final int i) {
        dispositivoViewHolder.name.setText(dispositivos.get(i).getNombre());
        dispositivoViewHolder.hora_inicio.setText(dispositivos.get(i).getHora_inicio());
        dispositivoViewHolder.photo.setImageResource(dispositivos.get(i).getImagen());
        final String nombre = dispositivos.get(i).getNombre();
        final String hora = dispositivos.get(i).getHora_inicio();
        dispositivoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i == 0) {
                    Intent intent = new Intent(view.getContext(), DispositivoActivity.class);
                   // intent.putExtra("nombre", nombre);
                   // intent.putExtra("hora", hora);
                    view.getContext().startActivity(intent);
                }
                if(i == 1) {
                    Intent intent = new Intent(view.getContext(), VentiladorActivity.class);
                   // intent.putExtra("nombre", nombre);
                  //  intent.putExtra("hora", hora);
                    view.getContext().startActivity(intent);
                }
                if(i == 2) {
                    Intent intent = new Intent(view.getContext(), BombillaActivity.class);
                   // intent.putExtra("nombre", nombre);
                   // intent.putExtra("hora", hora);
                    view.getContext().startActivity(intent);
                }
            }
        });
    }

}