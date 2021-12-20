package fr.lernejo.navy_battle;
import fr.lernejo.Sample;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Tests {
    @Test
    void create_Plateau() {
        int n = 6; // (1)
        Sample sample = new Sample();
        int result = sample.fact(n); // (2)
        Assertions.assertThat(result).as("fact 6 equal 720")
            .isEqualTo(720); // (3)
    }

}
