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
//        FamilyMemberRepository forObject = restTemplate.getForObject("http://localhost:9092/getFamilyMembers", FamilyMemberRepository.class);
//        FamilyMemberRepository forObject = restTemplate.getForObject("http://localhost:9092/getFamilyMembers", FamilyMemberRepository.class);
        List<FamilyMember> result = new ArrayList<>();
        result.add(new FamilyMember("Lemański", "Michał", 1, 1L));
        result.add(new FamilyMember("Lemański", "Jan", 13, 1L));
        result.add(new FamilyMember("Lemańska", "Ada", 44, 1L));

//        return forObject.getFamilyMembers();
        return result;
    }

    public void save(FamilyMember familyMember) {
        lastFamilyMember = familyMember;
    }

    public FamilyMember getFamilyMember() {
        return lastFamilyMember;
    }


}
