package com.example.baldursguidetogatekeeping.util;

import com.example.baldursguidetogatekeeping.dto.Spell;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SpellService {
    @GET("/spells")
    Call<List<Spell>> getAll();

}
