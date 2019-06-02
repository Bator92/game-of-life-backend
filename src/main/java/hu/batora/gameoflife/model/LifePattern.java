package hu.batora.gameoflife.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@FieldDefaults(level = PRIVATE)
public class LifePattern {
    String description;
    RuleSpecification ruleSpecification;
    List<Block> blocks;
}
