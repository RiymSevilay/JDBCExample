package com.sevilay.service;

import com.sevilay.repository.FutbolcuRepository;
import com.sevilay.repository.entity.Futbolcu;

import java.util.List;
import java.util.Optional;

public class FutbolcuService implements ICrudService<Futbolcu> {

    FutbolcuRepository futbolcuRepository;

    public FutbolcuService() {
        this.futbolcuRepository = new FutbolcuRepository();
    }

    @Override
    public void save(Futbolcu futbolcu) {
        futbolcuRepository.save(futbolcu);

    }

    @Override
    public void update(Futbolcu futbolcu) {
        if (findById(futbolcu.getId()).isPresent()) {
            futbolcuRepository.update(futbolcu);
        }

    }

    @Override
    public void delete(Long id) {
        if (findById(id).isPresent()) {
            futbolcuRepository.delete(id);
        }

    }

    @Override
    public List<Futbolcu> findAll() {
        List<Futbolcu> futbolcular = futbolcuRepository.findAll();
        if (futbolcular.size() == 0) {
            System.out.println("Database'de veri bulunmamaktadır.");
        }
        return futbolcular;
    }

    @Override
    public Optional<Futbolcu> findById(Long id) {
        Optional<Futbolcu> futbolcu = futbolcuRepository.findById(id);
        if (futbolcu.isEmpty()){
            System.out.println("Boyle bir futbolcu bulunamadi...");
        }
        return futbolcu;
    }
}
