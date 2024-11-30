package jpa.project.service;

import jpa.project.domain.Member;
import jpa.project.domain.Order;
import jpa.project.domain.OrderItem;
import jpa.project.domain.item.Item;
import jpa.project.repository.ItemRepository;
import jpa.project.repository.MemberRepository;
import jpa.project.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member, LocalDateTime.now(), orderItem);

        orderRepository.save(order);

        return order.getId();
    }



}
