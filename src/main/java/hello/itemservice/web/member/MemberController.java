package hello.itemservice.web.member;

import hello.itemservice.domain.member.Member;
import hello.itemservice.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberRepository memberRepository;
    private List<Member> members;
    private int count = 0;
    @Autowired
    MemberFirebaseService firebaseService;

    //ID 저장을 위해 판단후 회원가입 페이지 불러오기
    //홈에서 처음 온 경우 파이어베이스 데베에 정보를 리포지토리에 저장 후 불러오기
    @GetMapping("/addMemberForm")
    public String addMemberForm(@ModelAttribute("member") Member member) throws Exception {

        if (count == 0 && memberRepository.findAll().size() == 0) {
            members = memberRepository.findAll();
            count = members.size() + 1;
            Member tem = firebaseService.getMemberDetail(String.valueOf(count));

            while (tem != null) {
                members.add(tem);
                memberRepository.save(tem);
                count++;
                tem = firebaseService.getMemberDetail(String.valueOf(count));
            }
        }
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
