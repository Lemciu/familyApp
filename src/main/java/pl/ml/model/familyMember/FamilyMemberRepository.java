package pl.ml.model.familyMember;

import java.util.ArrayList;
import java.util.List;

public class FamilyMemberRepository {
    List<FamilyMember> familyMembers;

    public FamilyMemberRepository(List<FamilyMember> familyMembers) {
        this.familyMembers = familyMembers;
    }

    public List<FamilyMember> getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(List<FamilyMember> familyMembers) {
        this.familyMembers = familyMembers;
    }
}
