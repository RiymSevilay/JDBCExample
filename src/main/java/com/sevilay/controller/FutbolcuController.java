package com.sevilay.controller;

import com.sevilay.repository.ICrud;
import com.sevilay.repository.entity.Futbolcu;
import com.sevilay.service.FutbolcuService;


import java.util.List;
import java.util.Optional;

public class FutbolcuController implements ICrud<Futbolcu> {

    FutbolcuService futbolcuService;

    public FutbolcuController() {
        this.futbolcuService = new FutbolcuService();
    }

    @Override
    public void save(Futbolcu futbolcu) {
        futbolcuService.save(futbolcu);

    }

    @Override
    public void update(Futbolcu futbolcu) {
        futbolcuService.update(futbolcu);

    }

    @Override
    public void delete(Long id) {
        futbolcuService.delete(id);

    }

    @Override
    public List<Futbolcu> findAll() {
        return  futbolcuService.findAll();
    }

    @Override
    public Optional<Futbolcu> findById(Long id) {
        return futbolcuService.findById(id);
    }
}


