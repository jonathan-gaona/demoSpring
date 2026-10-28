package com.example.demo.service;

import com.example.demo.model.Author;
import com.example.demo.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    public Author create(Author author) {
        author.setId(null);
        return authorRepository.save(author);
    }

    public Optional<Author> update(Long id, Author authorDetails) {
        return authorRepository.findById(id).map(existing -> {
            existing.setName(authorDetails.getName());
            return authorRepository.save(existing);
        });
    }

    public boolean delete(Long id) {
        if (!authorRepository.existsById(id)) {
            return false;
        }
        authorRepository.deleteById(id);
        return true;
    }
}