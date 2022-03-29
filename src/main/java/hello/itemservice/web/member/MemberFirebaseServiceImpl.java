package hello.itemservice.web.member;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import hello.itemservice.domain.member.Member;
import org.springframework.stereotype.Service;

@Service
public class MemberFirebaseServiceImpl implements MemberFirebaseService{

    public static final String COLLECTION_NAME = "member";

    //파이어베이스 데이터베이스에 값이 있는지 비교
    @Override
    public Member getMemberDetail(String id) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection(COLLECTION_NAME).document(String.valueOf(id));
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();
        Member member = null;
        if (documentSnapshot.exists()) {
            member = documentSnapshot.toObject(Member.class);
            System.out.println("id = " + id + ", member : " + member);
            return member;
        }else{
            return null;
        }
    }

    //새로운 멤버 파이어베이스에 업데이트
    @Override
    public String updateMember(Member member) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> apiFuture = firestore.collection(COLLECTION_NAME).document(String.valueOf(member.getLoginId())).set(member);
        return apiFuture.get().getUpdateTime().toString();
    }
}
