package com.example.PhotoNetwork.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PhotoNetwork.Model.PhotoModel;
import com.example.PhotoNetwork.repository.PhotoRepository;

@Service
public class PhotoService {
    @Autowired
    private PhotoRepository repository;

    public List<PhotoModel> listPhoto(){
        return repository.findAll();
    }

    public Optional<PhotoModel>buscarPorID(long id){
        return repository.findById(id);
    }

    public PhotoModel save(PhotoModel photoModel){
        return repository.save(photoModel);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }
}
