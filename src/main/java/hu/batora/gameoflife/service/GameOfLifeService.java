package hu.batora.gameoflife.service;

import hu.batora.gameoflife.model.LifePattern;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class GameOfLifeService {

    private final ParserService parserService;

    public GameOfLifeService(ParserService parserService) {
        this.parserService = parserService;
    }

    public LifePattern uploadAndParseFile(MultipartFile file) {
        try {
            return parserService.parse(file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException("IOException occurred during file parsing", e);
        }
    }
}
