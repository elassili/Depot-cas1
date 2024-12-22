package ma.example.microcommande.service;


import ma.example.microcommande.model.Commande;
import ma.example.microcommande.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {
    @Autowired
    private CommandeRepository commandeRepository;

    public List<Commande> findAll() {
        return commandeRepository.findAll();
    }

    public Commande save(Commande commande) {
        return commandeRepository.save(commande);
    }

    public void deleteById(Long id) {
        commandeRepository.deleteById(id);
    }

    public Optional<Commande> findById(Long id) {
        return commandeRepository.findById(id);
    }
    public long count() {
        return commandeRepository.count();
    }
}