package com.wilkins.showcase;

import com.wilkins.showcase.domain.*;
import org.junit.jupiter.api.Test;

import java.util.TreeSet;

import static java.util.Comparator.comparing;

public class VisitorTest {
    @Test
    void canGetPartyNoticePeriods() {
        var robin = new Contractor();
        var laura = new Perm();
        var oli = new Temp();

        var parties = new TreeSet<>(comparing(Party::noticePeriod).reversed());
        parties.add(robin);
        parties.add(laura);
        parties.add(oli);

        var visitor = new PartyVisitor();

        parties.stream()
                .map(party -> party.accept(visitor))
                .forEach(System.out::println);
    }
}
