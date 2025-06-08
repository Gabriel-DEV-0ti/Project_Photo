package com.example.PhotoNetwork.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PhotoNetwork.Model.PhotoModel;
import com.example.PhotoNetwork.service.PhotoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/Photo_NetWork")
@CrossOrigin(origins = "*")
public class PhotoController {
    @Autowired
    private PhotoService service;

     @GetMapping
    public List<PhotoModel> listar() {
        return service.listPhoto();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoModel>buscarPorId(@PathVariable Long id){
        return service.buscarPorID(id)
            .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PhotoModel save(@RequestBody PhotoModel photoModel){
        return service.save(photoModel);
    }

    @PutMapping("/{id}")
   public ResponseEntity<PhotoModel>atualizar(@PathVariable Long id, @RequestBody PhotoModel photoModel) {
        Optional<PhotoModel> photoOpt = service.buscarPorID(id);
        if (!photoOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        PhotoModel existingPhoto = photoOpt.get();
        existingPhoto.setName(photoModel.getName());
        existingPhoto.setUrl(photoModel.getUrl());

        PhotoModel updatePhoto = service.save(existingPhoto);

        return ResponseEntity.ok(updatePhoto);
  }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deletar(@PathVariable Long id){
        if (!service.buscarPorID(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
