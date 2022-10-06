package pl.ml.model.familyMember;

import java.util.concurrent.atomic.AtomicInteger;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

}
