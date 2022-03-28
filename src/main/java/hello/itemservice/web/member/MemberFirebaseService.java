package hello.itemservice.web.member;

import hello.itemservice.domain.member.Member;

public interface MemberFirebaseService {
    public String insertMember(Member member) throws Exception;
    public Member getMemberDetail(String id) throws Exception;
    public String updateMember(Member member) throws Exception;
}
