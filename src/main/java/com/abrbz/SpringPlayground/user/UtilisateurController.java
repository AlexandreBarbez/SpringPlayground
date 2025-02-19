package com.abrbz.SpringPlayground.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class UtilisateurController {

    private final UtilisateurRepository utilisateurRepository;
    private static final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

    public UtilisateurController(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping("/utilisateurs/{id}")
    public ResponseEntity<Utilisateur> getUtilisateur(@PathVariable int id){
        return ResponseEntity.ok(utilisateurRepository.findById(id));
    }

    //sur un bench en local 5thread/500connections/20sec : 15MB de mémoire / 5.71ms de temps de CPU / 69,6 msde latence
    @PostMapping("/utilisateurs")
    public CompletableFuture<ResponseEntity<Utilisateur>> createUtilisateur(@RequestBody Utilisateur utilisateur) {
        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok(utilisateurRepository.save(utilisateur)),
                executor);
    }

    //sur un bench en local 5thread/500connections/20sec : 38,7MB de mémoire / 4.72ms de temps de CPU / 77ms de latence
    @PostMapping("/utilisateursold")
    public ResponseEntity<Utilisateur> createUtilisateurold(@RequestBody Utilisateur utilisateur) {
        return ResponseEntity.ok(utilisateurRepository.save(utilisateur));
    }
}
