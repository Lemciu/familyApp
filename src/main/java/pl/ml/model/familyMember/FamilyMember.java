package pl.ml.model.familyMember;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Setter
@Getter
@NoArgsConstructor
public class FamilyMember {
    private Long id;
    private String familyName;
    private String givenName;
    private Integer age;
    private Long familyId;
    private static final AtomicInteger count = new AtomicInteger(-1);

    public FamilyMember(String familyName, String givenName, Integer age, Long familyId) {
        this.id = (long) count.incrementAndGet();
        this.familyName = familyName;
        this.givenName = givenName;
        this.age = age;
        this.familyId = familyId;
    }

    public FamilyMember(String familyName, String givenName, Integer age) {
        this.familyName = familyName;
        this.givenName = givenName;
        this.age = age;
    }
}
