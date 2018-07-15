package com.petzoo.petzoo.DataAccess;

import android.content.Context;

import com.petzoo.petzoo.Service.ApiService;
import com.petzoo.petzoo.constants.ApiServiceConstants;
import com.petzoo.petzoo.models.Mascota;

public class MascotaDA {
    public void GetAllMascotas(Context context){
        ApiService<Mascota> apiService = new ApiService<>(context);
        apiService.getList(ApiServiceConstants.URL_BASE,"/api","/Mascota");
    }
}
