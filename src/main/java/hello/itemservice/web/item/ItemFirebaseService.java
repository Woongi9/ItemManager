package hello.itemservice.web.item;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.member.Member;

public interface ItemFirebaseService {
    public Item getItemDetail(String id) throws Exception;
    public String updateItem(Item item) throws Exception;
    public String deleteItem(String id) throws Exception;
}
