package hu.batora.gameoflife.controller;

import hu.batora.gameoflife.model.LifePattern;
import hu.batora.gameoflife.service.GameOfLifeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class GameOfLifeController {

    private final GameOfLifeService gameOfLifeService;

    public GameOfLifeController(GameOfLifeService gameOfLifeService) {
        this.gameOfLifeService = gameOfLifeService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/api/upload-and-parse")
    public ResponseEntity<LifePattern> uploadAndParseFile(MultipartFile file0) {
        LifePattern lifePattern = gameOfLifeService.uploadAndParseFile(file0);
        return ok(lifePattern);
    }
}
