package com.example.api.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.example.api.entity.Livre;
import com.example.api.repository.LivreRepository;

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
@RequestMapping("/api/Livres")
public class LivreController {

    @Autowired
    private LivreRepository LivreRepository;

    @GetMapping("")
    public List<Livre> getAllLivres() {
        return LivreRepository.findAll();
    }

    @GetMapping("/{id}")
    public Livre getLivre(@PathVariable(value = "id") long id) {
        return LivreRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livre does not exist")
        );
    }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Livre createLivre(@Valid @RequestBody Livre Livre) {
        if (Livre.getDate() == null) {
            Livre.setDate(new Date());
        }
        return LivreRepository.save(Livre);
    }

    @PutMapping("/{id}")
    public Livre updateLivre(
        @Valid @RequestBody Livre newLivre,
        @PathVariable(value = "id") long id
    ) {
        Livre Livre = LivreRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livre does not exist")
        );
        if (newLivre.getId() != 0 && newLivre.getId() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Livre id does not match requested resource id");
        }
        Livre.setAuthor(newLivre.getAuthor());
        Livre.setTitle(newLivre.getTitle());
        Livre.setContenu(newLivre.getContenu());
        Livre.setDate(newLivre.getDate());
        Livre.setJaime(newLivre.getJaime());
       
        return LivreRepository.save(Livre);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteLivre(@PathVariable(value = "id") long id) {
        LivreRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livre does not exist")
        );
        LivreRepository.deleteById(id);
    }
}
