package cn.wahaha.test.dataStructure.temp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    /**

     * 卖家用户id

     */

    long sellerId;

    /**

     * 商品价格，单位分

     */

    long price;

}


