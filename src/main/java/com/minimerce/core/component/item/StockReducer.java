package com.minimerce.core.component.item;

import com.minimerce.core.support.object.type.ProductType;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by gemini on 03/06/2017.
 */
@Component
public class StockReducer {
    private final UsableStockReducer usableStockReducer;

    @Inject
    public StockReducer(UsableStockReducer usableStockReducer) {
        this.usableStockReducer = usableStockReducer;
    }

    public void reduce(List<Stock> stocks) {
        for (Stock stock : stocks) {
            if(ProductType.USABLE == stock.getType()) usableStockReducer.reduce(stock);
        }
    }
}
