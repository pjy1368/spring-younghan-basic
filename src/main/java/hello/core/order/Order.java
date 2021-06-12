package hello.core.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Order {

    private final Long memberId;
    private final String itemName;
    private final int itemPrice;
    private final int discountPrice;

    public int calculatePrice() {
        return itemPrice - discountPrice;
    }
}
