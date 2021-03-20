package cn.wahaha.test.dataStructure.temp;

import lombok.Data;
import org.aspectj.weaver.ast.Or;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class Order{
    /**
     * 该订单对应的商品
     */

    List<Item> orderItems;

    /**
     * 该订单金额，单位分
     */
    long totalPrice;

    /**
     * 该订单对应的卖家userId
     */
    long sellerId;

    public List<Order> packageItemsToOrders(List<Item> items){
        // key为sellerId，map为sellerId和商品集合映射
        Map<Long, List<Item>> idItemMap = new HashMap<>();

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            Long sellerId = item.getSellerId();

            if (Objects.isNull(idItemMap.get(sellerId))) {
                List<Item> itemList = new ArrayList<>();
                itemList.add(item);
                idItemMap.put(sellerId, itemList);
            } else {
                List<Item> itemList1 = idItemMap.get(sellerId);
                itemList1.add(item);
            }

        }

        List<Order> orders = getOrdersByIdItemMap(idItemMap);
        int size =  orders.size();
        System.out.println("订单总数：" + size);

        for (int i = 0; i < size; i++) {
            System.out.println("商品商家id： " + orders.get(i).sellerId + " 商品总价：" + orders.get(i).getTotalPrice());

        }


        return orders;

    }

    // todo 代码有bug，这个问题我一定要解决！！！
    public List<Order> getOrdersByIdItemMap(Map<Long, List<Item>> idItemMap) {
        List<Order> orders = new ArrayList<>();

        for (Long sellerId : idItemMap.keySet()) {
            List<Item> items = idItemMap.get(sellerId);
            items.sort((o1, o2) -> {
                if (o1.getPrice() > o2.price) {
                    return -1;
                } else if (o1.getPrice() < o2.price) {
                    return 1;
                } else {
                    return 0;
                }

            });

            Order order;
            Iterator<Item> iterator = items.iterator();
            List<Item> itemList = new ArrayList<>();
            long priceSum = 0L;

            while (iterator.hasNext()) {
                    long theItemPrice = iterator.next().getPrice();
//                    priceSum += theItemPrice;
                    if (priceSum + theItemPrice < 1000) {
                        priceSum += theItemPrice;
                        itemList.add(iterator.next());
                    } else {
                        order = new Order();
                        order.setSellerId(sellerId);
                        order.setTotalPrice(priceSum);
                        order.setOrderItems(itemList);
                        orders.add(order);

//                        itemList = new ArrayList<>();
//                        priceSum = 0L;

                    }
            }

        }
        return orders;

    }


    public static void main(String[] args) {
        List<Item> items = new ArrayList<Item>(){{
            add(new Item(1, 300));
            add(new Item(1, 400));
            add(new Item(1, 500));
            add(new Item(1, 600));
            add(new Item(1, 700));
            add(new Item(2, 300));
            add(new Item(2, 400));
            add(new Item(2, 500));
            add(new Item(2, 600));
            add(new Item(2, 700));
            add(new Item(3, 300));
            add(new Item(3, 400));
            add(new Item(3, 500));
            add(new Item(3, 600));
            add(new Item(3, 700));
        }};
        new Order().packageItemsToOrders(items);
    }

}
