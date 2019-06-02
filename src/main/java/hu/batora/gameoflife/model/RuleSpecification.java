package hu.batora.gameoflife.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class RuleSpecification {
    List<Integer> survivalRule;
    List<Integer> birthRule;

    public static RuleSpecification defaultSpecification(){
        return new RuleSpecification(asList(2, 3), singletonList(3));
    }

}
