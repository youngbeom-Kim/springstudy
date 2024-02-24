package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
//상품 서비스같은 경우에는 상품 리포지토리에 단순히 위임만 하는 클래스 > 경우에 따라서 굳이 서비스를 만들지 않아도 될 수도있음
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional //readOnly true면 저장이 안되기 때문에 오버라이딩
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(Id);
    }
}
