package pl.ml.model.family;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.ml.model.familyMember.FamilyMember;

import java.util.List;
import java.util.Optional;

@Service
public class FamilyService {
    private FamilyRepository familyRepository;

    public FamilyService(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    public Long createFamily() {
        return null;
    }

    public Family getFamily() {
        return null;
    }

    public boolean validateFamilyData(Family family, List<FamilyMember> members) {
        long amountOfInfants = members.stream()
                .filter(m -> m.getAge() >= 0 && m.getAge() <= 4)
                .count();
        long amountOfChildren = members.stream()
                .filter(m -> m.getAge() > 4 && m.getAge() <= 16)
                .count();
        long amountOfOfAdults = members.stream()
                .filter(m -> m.getAge() > 16)
                .count();

        return amountOfInfants == family.getNrOfInfants()
                && amountOfChildren == family.getNrOfChildren()
                && amountOfOfAdults == family.getNrOfAdults();
    }

    public Long save(Family family) {
        return familyRepository.save(family).getId();
    }

    public Optional<Family> findById(Long id) {
        return familyRepository.findById(id);
    }

//    public List<FamilyMember> getFamilyMembers() {
//        RestTemplate restTemplate = new RestTemplate();
////        FamilyMembers forObject = restTemplate.getForObject("http://localhost:9092/familySuccess?id=1", FamilyMembers.class);
////        List<FamilyMember> data = forObject.getData();
////        return data;
//        return null;
//    }

//    public FamilyMember getFamilyMember(String url) {
//        RestTemplate restTemplate = new RestTemplate();
//        FamilyMember member = restTemplate.getForObject(url, FamilyMember.class);
//        return member;
//    }
}
