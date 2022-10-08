package pl.ml.model.familyMember;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FamilyMemberService {
    private FamilyMember lastFamilyMember;

    public List<FamilyMember> getFamilyMembers() {
        RestTemplate restTemplate = new RestTemplate();
        FamilyMemberRepository forObject = restTemplate.getForObject("http://localhost:8080/searchFamilyMember", FamilyMemberRepository.class);
        List<FamilyMember> familyMembers = forObject.familyMembers;
        return familyMembers;
    }

    public void save(FamilyMember familyMember) {
        lastFamilyMember = familyMember;
    }

    public FamilyMember getFamilyMember() {
        return lastFamilyMember;
    }


}
