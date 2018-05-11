package com.example.alejandra.datos.datosApi;

import com.example.alejandra.datos.models.DatosRespuesta;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Alejandra on 23/04/2018.
 */

public interface DatosService
{
    @GET("3b3j-wg9u.json")
    Call<ArrayList<DatosRespuesta>>obtenerLista();
}
