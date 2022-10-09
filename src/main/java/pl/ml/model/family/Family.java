package pl.ml.model.family;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String familyName;
    private Integer nrOfAdults;
    private Integer nrOfChildren;
    private Integer nrOfInfants;
    private static Long currentFamilyId;

    public Family(String familyName, Integer nrOfAdults, Integer nrOfChildren, Integer nrOfInfants) {
        this.familyName = familyName;
        this.nrOfAdults = nrOfAdults;
        this.nrOfChildren = nrOfChildren;
        this.nrOfInfants = nrOfInfants;
    }

    public static Long getCurrentFamilyId() {
        return currentFamilyId;
    }

    public static void setCurrentFamilyId(Long currentFamilyId) {
        Family.currentFamilyId = currentFamilyId;
    }
}
