package com.example.alejandra.datos.models;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.alejandra.datos.R;

import java.util.ArrayList;

public class AdaptadorDatos extends RecyclerView.Adapter<AdaptadorDatos.DireccionViewHolder>{

    ArrayList<DatosRespuesta>datos;
    private  DatosRespuesta datosColombia;
    private Context myContext;


    public AdaptadorDatos(Context context)
    {
        this.myContext=context;
        datos= new ArrayList<>();
    }

    AdaptadorDatos(ArrayList<DatosRespuesta> datosCol){

        this.datos=datosCol;
    }


    public DireccionViewHolder onCreateViewHolder( ViewGroup viewGroup, int j) {

        View myView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);
        DireccionViewHolder direccionVi= new DireccionViewHolder(myView);
        return direccionVi;

    }

    @Override
    public void onBindViewHolder( DireccionViewHolder holder, int pos) {
        datosColombia=datos.get(pos);
        holder.direTextView.setText(datosColombia.getMunicipio());
        holder.text0.setText("Municipio: "+datosColombia.getMunicipio());
        holder.text1.setText("Porcentaje: "+datosColombia.getPorcentaje());
        holder.text2.setText("Presenciales: "+datosColombia.getPresenciales());
        holder.text3.setText("Virtuales: "+datosColombia.getVirtuales());


        Glide.with(myContext);
    }


    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void agregar(ArrayList<DatosRespuesta>listaDatos)
    {
        datos.addAll(listaDatos);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class DireccionViewHolder extends RecyclerView.ViewHolder {


        private TextView text0, text1, text2, text3;



        CardView myCardV;
        TextView direTextView;

        DireccionViewHolder(View itemView)
        {
            super(itemView);
            myCardV =(CardView)itemView.findViewById(R.id.myCardView);
            direTextView =(TextView)itemView.findViewById(R.id.txt1);
            text0 =(TextView)itemView.findViewById(R.id.txt0);
            text1 =(TextView)itemView.findViewById(R.id.txt1);
            text2 =(TextView)itemView.findViewById(R.id.txt2);
            text3 =(TextView)itemView.findViewById(R.id.txt3);





        }

    }
}
