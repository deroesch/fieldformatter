package org.deroesch.fieldformatter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FieldformatterApplicationTests {

    @Test
    void contextLoads() {
        new FieldformatterApplication();

        String[] args = {};
        FieldformatterApplication.main(args);
    }

}
