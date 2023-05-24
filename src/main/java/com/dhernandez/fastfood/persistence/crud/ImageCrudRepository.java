package com.dhernandez.fastfood.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhernandez.fastfood.persistence.entities.Image;

public interface ImageCrudRepository  extends JpaRepository<Image, Long>{

}
