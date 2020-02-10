package com.example.api.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.example.api.entity.Categorie;
import com.example.api.repository.CategorieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
@RequestMapping("/api/Categories")
public class CategorieController {

    @Autowired
    private CategorieRepository CategorieRepository;

    @GetMapping("")
    public List<Categorie> getAllCategories() {
        return CategorieRepository.findAll();
    }

    @GetMapping("/{id}")
    public Categorie getCategorie(@PathVariable(value = "id") long id) {
        return CategorieRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categorie does not exist")
        );
    }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Categorie createCategorie(@Valid @RequestBody Categorie Categorie) {
        
        return CategorieRepository.save(Categorie);
    }

    @PutMapping("/{id}")
    public Categorie updateCategorie(
        @Valid @RequestBody Categorie newCategorie,
        @PathVariable(value = "id") long id
    ) {
        Categorie Categorie = CategorieRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categorie does not exist")
        );
        if (newCategorie.getId() != 0 && newCategorie.getId() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categorie id does not match requested resource id");
        }
        
       
        return CategorieRepository.save(Categorie);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCategorie(@PathVariable(value = "id") long id) {
        CategorieRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categorie does not exist")
        );
        CategorieRepository.deleteById(id);
    }
}
