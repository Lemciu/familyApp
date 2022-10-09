package pl.ml.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.ml.model.family.Family;
import pl.ml.model.familyMember.FamilyMember;

import java.util.ArrayList;
import java.util.List;

public class FamilyServiceTest {
    FamilyService familyService;

    @BeforeEach
    public void init() {
        familyService = new FamilyService(null);
    }

    @DisplayName("Should add 2 adults and return true")
    @Test
    void shouldAdd2AdultsAndReturnTrue() {
        // given
        Family family = new Family("Nowak", 2, 0, 0);
        List<FamilyMember> members = List.of(
                new FamilyMember("Nowak", "Ada", 17),
                new FamilyMember("Nowak", "Marcin", 32)
        );

        // when
        boolean result = familyService.validateFamilyData(family, members);

        // then
        Assertions.assertTrue(result);
    }

    @DisplayName("Should add 1 adult and 2 children and return false")
    @Test
    void shouldAdd1AdultAnd2ChildrenAndReturnFalse() {
        // given
        Family family = new Family("Nowak", 1, 2, 1);
        List<FamilyMember> members = List.of(
                new FamilyMember("Nowak", "Marcin", 32),
                new FamilyMember("Nowak", "Agata", 6),
                new FamilyMember("Nowak", "Brajan", 5)
        );

        // when
        boolean result = familyService.validateFamilyData(family, members);

        // then
        Assertions.assertFalse(result);
    }

    @DisplayName("Should add no familyMember and return true")
    @Test
    void shouldAdd0FamilyMembersAndReturnTrue() {
        // given
        Family family = new Family("Nowak", 0, 0, 0);
        List<FamilyMember> members = new ArrayList<>();

        // when
        boolean result = familyService.validateFamilyData(family, members);

        // then
        Assertions.assertTrue(result);
    }

}
