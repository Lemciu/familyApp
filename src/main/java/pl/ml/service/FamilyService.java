package pl.ml.service;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.ml.model.family.Family;
import pl.ml.repository.FamilyRepository;
import pl.ml.model.familyMember.FamilyMember;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class FamilyService {
    private FamilyRepository familyRepository;
//    serverValue String URL (value)

    public FamilyService(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    public Long createFamily() {
        return null;
    }

    public Optional<Family> getFamilyById(Long id) {
        return familyRepository.findById(id);
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

    public List<FamilyMember> getFamilyMembers() {
        RestTemplate restTemplate = new RestTemplate();
        FamilyMember[] forObject = restTemplate.getForObject("http://localhost:8080/searchFamilyMember", FamilyMember[].class);
        List<FamilyMember> members = Arrays.asList(forObject);
        return members;
    }

    public void saveFamilyMember() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.execute("http://localhost:8080/createFamilyMember", HttpMethod.GET, null, null);
    }

}
