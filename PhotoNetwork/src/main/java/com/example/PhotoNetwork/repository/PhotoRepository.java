package com.example.PhotoNetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PhotoNetwork.Model.PhotoModel;

@Repository
public interface PhotoRepository extends JpaRepository<PhotoModel, Long> {
}