package hello.itemservice.web.item;

import hello.itemservice.domain.item.DeliveryCode;
import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import hello.itemservice.domain.item.ItemType;
import hello.itemservice.domain.member.Member;
import hello.itemservice.web.item.form.ItemSaveForm;
import hello.itemservice.web.item.form.ItemUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private List<Item> items;
    private int count = 1;
    @Autowired
    ItemFirebaseService firebaseService;

    @ModelAttribute("regions")
    public Map<String, String> regions(){
        Map<String, String> regions = new LinkedHashMap<>();
        regions.put("SEOUL", "서울");
        regions.put("BUSAN", "부산");
        regions.put("JEJU", "제주");
        return regions;
    }

    @ModelAttribute("itemTypes")
    public ItemType[] itemTypes(){
        return ItemType.values();
    }

    @ModelAttribute("deliveryCodes")
    public List<DeliveryCode> deliveryCodes(){
        List<DeliveryCode> deliveryCodes = new ArrayList<>();
        deliveryCodes.add(new DeliveryCode("FAST", "빠른 배송"));
        deliveryCodes.add(new DeliveryCode("NORMAL", "일반 배송"));
        deliveryCodes.add(new DeliveryCode("SLOW", "느린 배송"));
        return deliveryCodes;
    }

    //파이어베이스 데베의 값 모두 출력
    //맨처음 들어올 때만 파이어베이스 데이터베이스 불러오기
    @GetMapping
    public String items(Model model) throws Exception{

        //테스트 아이템 2가지 추가
        items = itemRepository.findAll();
        count = items.size()+1;
        System.out.println(count);
        model.addAttribute("items", items);
        Item tem = firebaseService.getItemDetail(String.valueOf(count));

        //반복문을 통한 개수 확인 및 리스트 추가
        while(tem != null)
        {
            items.add(tem);
            itemRepository.save(tem);
            count++;
            tem = firebaseService.getItemDetail(String.valueOf(count));
        }
        return "items/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "items/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "items/addForm";
    }

    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute("item") ItemSaveForm form,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) throws Exception{

        //특정 필드 예외가 아닌 전체 예외
        if (form.getPrice() != null && form.getQuantity() != null) {
            int resultPrice = form.getPrice() * form.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "items/addForm";
        }

        //성공 로직
        Item item = new Item();
        item.setId(Long.valueOf(items.size()+1));
        item.setItemName(form.getItemName());
        item.setPrice(form.getPrice());
        item.setQuantity(form.getQuantity());

        item.setOpen(form.getOpen());
        item.setRegions(form.getRegions());
        item.setItemType(form.getItemType());
        item.setDeliveryCode(form.getDeliveryCode());

        //회원가입 성공시 리포지토리에 추가와 동시에 데베 업데이트
        firebaseService.updateItem(item);
        Item savedItem = itemRepository.save(item);

        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "items/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId,
                       @Validated @ModelAttribute("item") ItemUpdateForm form,
                       BindingResult bindingResult) throws Exception {

        Long tem;

        //특정 필드 예외가 아닌 전체 예외
        if (form.getPrice() != null && form.getQuantity() != null) {
            int resultPrice = form.getPrice() * form.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "items/editForm";
        }


        //먼저 삭제
        tem = form.getId();
        firebaseService.deleteItem(String.valueOf(tem));
        items.remove(form.getId());

        //해당 아이디 값으로 재생성
        Item itemParam = new Item();
        itemParam.setId(Long.valueOf(tem));
        itemParam.setItemName(form.getItemName());
        itemParam.setPrice(form.getPrice());
        itemParam.setQuantity(form.getQuantity());
        itemParam.setOpen(form.getOpen());
        itemParam.setRegions(form.getRegions());
        itemParam.setItemType(form.getItemType());
        itemParam.setDeliveryCode(form.getDeliveryCode());

        firebaseService.updateItem(itemParam);


        itemRepository.update(itemId, itemParam);
        return "redirect:/items/{itemId}";
    }

    @ResponseBody
    @GetMapping("/getItemDetail")
    public Item getItemDetail(@RequestParam String  id) throws Exception {
        return firebaseService.getItemDetail(id);
    }
}
