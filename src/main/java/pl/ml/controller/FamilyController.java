package pl.ml.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.ml.model.family.Family;
import pl.ml.service.FamilyService;
import pl.ml.model.familyMember.FamilyMember;
import pl.ml.service.FamilyMemberService;

import java.util.List;

@Controller
public class FamilyController {
    private FamilyService familyService;
    private FamilyMemberService familyMemberService;

    public FamilyController(FamilyService familyService, FamilyMemberService familyMemberService) {
        this.familyService = familyService;
        this.familyMemberService = familyMemberService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/createNewFamily")
    public String createNewFamily(Family family) {
        Long id = familyService.save(family);
        Family.setCurrentFamilyId(id);
        return "redirect:/createFamilyMember?id=" + id + "&familyName=" + family.getFamilyName();
    }

    @GetMapping("/createFamily")
    public String createFamily(Model model) {
        model.addAttribute("family", new Family("", 0, 0, 0));
        return "familyForm";
    }

    @GetMapping("/getFamilyId")
    @ResponseBody
    public Long getFamilyNumber() {
        return Family.getCurrentFamilyId();
    }

    @GetMapping("/createFamilyMember")
    public String home7(@RequestParam Long id, @RequestParam String familyName, Model model) {
        model.addAttribute("familyMember", new FamilyMember(familyName, "", 0, id));
        model.addAttribute("familyId", id);
        model.addAttribute("familyName", familyName);
        model.addAttribute("familyMembers", familyService.getFamilyMembers());
        Family.setCurrentFamilyId(id);
        return "familyMemberForm";
    }

    @PostMapping("/newFamilyMember")
    public String createFamilyMember(FamilyMember familyMember, @RequestParam Long familyId) {
        familyMember.setFamilyId(familyId);
        familyMemberService.save(familyMember);
        familyService.saveFamilyMember();
        return "redirect:/createFamilyMember?id=" + familyId + "&familyName=" + familyMember.getFamilyName();
    }

    @GetMapping("/getFamilyMember")
    @ResponseBody
    public FamilyMember getFamilyMember() {
        return familyMemberService.getFamilyMember();
    }

    @GetMapping("/getFamily")
    public String getFamily(Long id) {
        Family.setCurrentFamilyId(id);
        if (familyService.getFamilyById(id).isPresent()) {
            return "redirect:/familyInfo";
        } else {
            return "familyError";
        }
    }

    @GetMapping("/success")
    public String idk(@RequestParam String familyName,
                      @RequestParam String givenName,
                      @RequestParam Long familyId,
                      @RequestParam Integer age,
                      Model model) {
        model.addAttribute("member", new FamilyMember(familyName, givenName, age, familyId));
        return "success";
    }

    @GetMapping("/findFamily")
    public String findFamily(Model model) {
        model.addAttribute("currentId", Family.getCurrentFamilyId());
        return "findFamily";
    }

    @GetMapping("/familyInfo")
    public String getFamilyInfo(Model model) {
        if (familyService.findById(Family.getCurrentFamilyId()).isPresent()) {
            model.addAttribute("family", familyService.findById(Family.getCurrentFamilyId()).get());
            List<FamilyMember> members = familyService.getFamilyMembers();
            model.addAttribute("familyMembers", members);
            return "familyInfo";
        } else {
            return "familyError";
        }

    }

    @GetMapping("/getFamilyMembers")
    public String validateFamily(@RequestParam Long id, Model model) {
        List<FamilyMember> familyMembers = familyService.getFamilyMembers();
        boolean validate = familyService.validateFamilyData(familyService.findById(id).orElseThrow(), familyMembers);
        model.addAttribute("correctData", validate);
        model.addAttribute("familyNumber", id);
        return "familySuccess";
    }

}
