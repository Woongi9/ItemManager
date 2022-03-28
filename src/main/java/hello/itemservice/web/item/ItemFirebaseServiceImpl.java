package hello.itemservice.web.item;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.member.Member;
import org.springframework.stereotype.Service;

@Service
public class ItemFirebaseServiceImpl implements ItemFirebaseService {

    public static final String COLLECTION_NAME = "item";

    //입력값 id 파이어베이스 데베와 비교
    @Override
    public Item getItemDetail(String id) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference =
                firestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();
        Item item = null;
        if(documentSnapshot.exists()){
            item = documentSnapshot.toObject(Item.class);
            return item;
        }
        else{
            return null;
        }
    }

    //새로운 아이템 파이어베이스에 업데이트
    @Override
    public String updateItem(Item item) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<com.google.cloud.firestore.WriteResult> apiFuture
                = firestore.collection(COLLECTION_NAME).document(String.valueOf(item.getId())).set(item);
        return apiFuture.get().getUpdateTime().toString();
    }

    @Override
    public String deleteItem(String id) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> apiFuture
                = firestore.collection(COLLECTION_NAME).document(id).delete();
        return "Document id: "+id+" delete";
    }
}
