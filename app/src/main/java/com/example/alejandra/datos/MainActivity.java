package com.example.alejandra.datos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.alejandra.datos.models.AdaptadorDatos;
import com.example.alejandra.datos.datosApi.DatosService;
import com.example.alejandra.datos.models.DatosRespuesta;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static  final  String TAG="DATA";
    private Retrofit myRetrofit;
    private RecyclerView myRecyclerView;
    private boolean aptadorCargaDeDatos;
    private AdaptadorDatos adaptador;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myRetrofit=new Retrofit.Builder()
                .baseUrl("https://www.datos.gov.co/resource/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myRecyclerView =(RecyclerView) findViewById(R.id.idrecyclerView);
        adaptador=new AdaptadorDatos(this);
        myRecyclerView.setAdapter(adaptador);
        myRecyclerView.setHasFixedSize(true);

        final GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        myRecyclerView.setLayoutManager(layoutManager);

        myRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                if(dy>0){

                    int visibleItem=layoutManager.getChildCount();
                    int totalItem=layoutManager.getItemCount();
                    int pastVisible = layoutManager.findFirstVisibleItemPosition();

                    if(aptadorCargaDeDatos){

                        if((visibleItem+pastVisible)>= totalItem){
                            Log.i(TAG,"Fin de los datos");
                            aptadorCargaDeDatos=false;

                            procesadorDatos();
                        }

                    }

                }
            }
        });

        aptadorCargaDeDatos=true;
        procesadorDatos();

    }

    private void  procesadorDatos(){

        DatosService myService =myRetrofit.create(DatosService.class);
        Call<ArrayList<DatosRespuesta>>dArrayListCall=myService.obtenerLista();
        dArrayListCall.enqueue(new Callback<ArrayList<DatosRespuesta>>() {
            @Override
            public void onResponse(Call<ArrayList<DatosRespuesta>> call, Response<ArrayList<DatosRespuesta>> response) {
                if(response.isSuccessful())
                {
                    ArrayList<DatosRespuesta> datosLista=response.body();
                    adaptador.agregar(datosLista);
                }
                else {
                    Log.e(TAG,"onResponse"+response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<ArrayList<DatosRespuesta>> call, Throwable t) {

                Log.e(TAG,"onFailure"+t.getMessage());

            }
        });

    }
}
