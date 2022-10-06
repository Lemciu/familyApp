package pl.ml.model.family;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.ml.model.familyMember.FamilyMember;
import pl.ml.model.familyMember.FamilyMemberService;

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
    public String home(Model model) {
        model.addAttribute("correctData", false);
        return "home";
    }

    @PostMapping("/createNewFamily")
    public String createNewFamily(Family family) {
        Long id = familyService.save(family);
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
        Family.setCurrentFamilyId(id);
        return "familyMemberForm";
    }

    @PostMapping("/newFamilyMember")
    public String createFamilyMember(FamilyMember familyMember, @RequestParam Long familyId, Model model) {
        familyMember.setFamilyId(familyId);
        familyMemberService.save(familyMember);
        return "redirect:/getFamilyMember";
    }

    @GetMapping("/getFamilyMember")
    @ResponseBody
    public FamilyMember getFamilyMember() {
        return familyMemberService.getFamilyMember();
    }

    @GetMapping("/getFamily")
    @ResponseBody
    public Long getFamily(@RequestParam Long id) {
        return id;
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
    public String findFamily() {
        return "findFamily";
    }

    @GetMapping("/familyInfo")
    public String getFamilyInfo(@RequestParam Long id, Model model) {
        if (familyService.findById(id).isPresent()) {
            model.addAttribute("family", familyService.findById(id).get());
            return "familyInfo";
        } else {
            return "familyError";
        }

    }

    @GetMapping("/getFamilyMembers")
    public String validateFamily(Model model) {
        List<FamilyMember> familyMembers = familyMemberService.getFamilyMembers();
        Long familyId = familyMemberService.getFamilyMember().getFamilyId();
        boolean validate = familyService.validateFamilyData(familyService.findById(familyId).orElseThrow(), familyMembers);
        model.addAttribute("correctData", validate);
        model.addAttribute("familyNumber", familyId);
        return "familySuccess";
    }

}
