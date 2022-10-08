package pl.ml.service;

import org.springframework.stereotype.Service;
import pl.ml.model.familyMember.FamilyMember;

@Service
public class FamilyMemberService {
    private FamilyMember lastFamilyMember;

    public void save(FamilyMember familyMember) {
        lastFamilyMember = familyMember;
    }

    public FamilyMember getFamilyMember() {
        return lastFamilyMember;
    }


}
