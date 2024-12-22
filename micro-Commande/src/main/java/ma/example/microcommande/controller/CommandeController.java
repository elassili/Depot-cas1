package ma.example.microcommande.controller;

import ma.example.microcommande.model.Commande;
import ma.example.microcommande.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    // Injection de la propriété personnalisée depuis le Config Server
    @Value("${mes-config-ms.commandes-last:10}")
    private int commandesLast;

    @GetMapping
    public List<Commande> getAllCommandes() {
        return commandeService.findAll();
    }

    @PostMapping
    public Commande createCommande(@RequestBody Commande commande) {
        return commandeService.save(commande);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable Long id) {
        return commandeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteCommande(@PathVariable Long id) {
        commandeService.deleteById(id);
    }

    // Endpoint pour afficher la valeur actuelle de commandesLast
    @GetMapping("/config")
    public String getCommandesLast() {
        return "Commandes des derniers jours = " + commandesLast;
    }
}
