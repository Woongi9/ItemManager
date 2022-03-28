package hello.itemservice.web.member;

import hello.itemservice.domain.member.Member;
import hello.itemservice.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberRepository memberRepository;
    @Autowired
    MemberFirebaseService firebaseService;

    @GetMapping("/addMemberForm")
    public String addMemberForm(@ModelAttribute("member") Member member) {
        return "members/addMemberForm";
    }

    @PostMapping("/addMemberForm")
    public String save(@Valid @ModelAttribute Member member, BindingResult bindingResult) throws Exception{
        if (bindingResult.hasErrors()) {
            return "members/addMemberForm";
        }

        memberRepository.save(member);
        firebaseService.updateMember(member);

        return "redirect:/";
    }

    @ResponseBody
    @GetMapping("/getMemberDetail")
    public Member getMemberDetail(@RequestParam String  id) throws Exception {
        return firebaseService.getMemberDetail(id);
    }
}
