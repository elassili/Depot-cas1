package ma.example.microcommande.health;


import ma.example.microcommande.service.CommandeService;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CommandeHealthIndicator implements HealthIndicator {

    private final CommandeService commandeService;

    public CommandeHealthIndicator(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @Override
    public Health health() {
        long count = commandeService.count();
        if (count > 0) {
            return Health.up()
                    .withDetail("message", "Des commandes existent dans la table.")
                    .withDetail("nombre_de_commandes", count)
                    .build();
        } else {
            return Health.down()
                    .withDetail("message", "Aucune commande trouvée dans la table.")
                    .build();
        }
    }
}