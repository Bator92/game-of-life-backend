package hu.batora.gameoflife.service;

import hu.batora.gameoflife.model.Block;
import hu.batora.gameoflife.model.LifePattern;
import hu.batora.gameoflife.model.Position;
import hu.batora.gameoflife.model.RuleSpecification;
import hu.batora.gameoflife.validator.FileValidator;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ParserService {

    LifePattern parse(InputStream inputStream) {
        StringBuilder descriptionBuilder = new StringBuilder();
        RuleSpecification ruleSpecification = RuleSpecification.defaultSpecification();
        List<Block> blocks = new ArrayList<>();
        List<String> lines = FileScanner.scanLines(inputStream);
        FileValidator.checkVersion(lines);
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.startsWith("#D")) {
                descriptionBuilder
                        .append(line.substring(2))
                        .append("\n");
            }
            if (line.startsWith("#R")) {
                ruleSpecification = parseRuleSpecification(line);
            }
            if (line.startsWith("#P")) {
                Position position = parsePosition(line);
                List<List<Boolean>> aliveCells = parseAliveCells(i, lines);
                Block block = new Block(position, aliveCells);
                blocks.add(block);
            }
        }
        return LifePattern.builder()
                .description(descriptionBuilder.toString())
                .ruleSpecification(ruleSpecification)
                .blocks(blocks)
                .build();
    }

    private RuleSpecification parseRuleSpecification(String line) {
        RuleSpecification ruleSpecification;
        String[] rules = line.substring(2).split("/");
        ruleSpecification = new RuleSpecification(readNumbers(rules[0]), readNumbers(rules[1]));
        return ruleSpecification;
    }

    private List<List<Boolean>> parseAliveCells(int actualIndex, List<String> lines) {
        List<List<Boolean>> aliveCells = new ArrayList<>();
        while (actualIndex + 1 < lines.size() && !lines.get(actualIndex + 1).matches("^#P.*")) {
            String cellLine = lines.get(++actualIndex);
            aliveCells.add(
                    cellLine.chars()
                            .mapToObj(character -> (char) character)
                            .map(mapCell())
                            .collect(Collectors.toList())
            );
        }
        return aliveCells;
    }

    private List<Integer> readNumbers(String rule) {
        Scanner scanner = new Scanner(rule);
        List<Integer> list = new ArrayList<>();
        while (scanner.hasNextInt()) {
            list.add(scanner.nextInt());
        }
        return list;
    }

    private Position parsePosition(String line) {
        String[] positionLine = line.split(" ");
        return new Position(Integer.parseInt(positionLine[1]), Integer.parseInt(positionLine[2]));
    }

    private Function<Character, Boolean> mapCell() {
        return cell -> {
            switch (cell) {
                case '.':
                    return false;
                case '*':
                    return true;
                default:
                    throw new IllegalStateException("Invalid character found: " + cell);
            }
        };
    }
}
