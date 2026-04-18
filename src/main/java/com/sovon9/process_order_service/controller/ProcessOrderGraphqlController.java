package com.sovon9.process_order_service.controller;

import com.sovon9.process_order_service.entities.ProcessOrder;
import com.sovon9.process_order_service.entities.Product;
import com.sovon9.process_order_service.entities.ProductionUnit;
import com.sovon9.process_order_service.repository.ProcessOrderRepository;
import com.sovon9.process_order_service.util.QueryBuilderUtil;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Window;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.query.ScrollSubrange;
import org.springframework.stereotype.Controller;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class ProcessOrderGraphqlController {

    private ProcessOrderRepository repository;
    public ProcessOrderGraphqlController(ProcessOrderRepository repository) {
        this.repository = repository;
    }

    @QueryMapping(name = "processOrders")
    public Window<ProcessOrder> processOrders(@Argument Map<String, Object> where, ScrollSubrange subrange, @Argument Map<String, Object> order)
    {
        ScrollPosition position = subrange.position().orElse(ScrollPosition.offset());
        int limit = subrange.count().orElse(10);

        // When scrolling backwards (using 'last' or 'before'), subrange.forward() is false.
        // We must reverse the sort direction to query the database correctly for backwards pagination.
        Sort.Direction direction = Sort.Direction.ASC;
        if (!subrange.forward()) {
            direction = Sort.Direction.DESC;
        }

        Sort sort = QueryBuilderUtil.buildSort(order,"processOrderId", Sort.Direction.ASC);

        Specification<ProcessOrder> spec = QueryBuilderUtil.buildSpecification(where);

        if(spec==null) {
            return repository.findBy(position, Limit.of(limit), sort);
        }

        return repository.findBy(spec, q->q.limit(limit).sortBy(sort).scroll(position));
    }

    @BatchMapping(typeName = "ProcessOrder", field = "product")
    public List<Optional<Product>> getProducts(List<ProcessOrder> processOrders)
    {
        return processOrders.stream().map(po->po.getProductId()==null ? Optional.<Product>empty()
                : Optional.of(new Product(po.getProductId()))).collect(Collectors.toList());

    }

    @BatchMapping(typeName = "ProcessOrder", field = "productionUnit")
    public List<Optional<ProductionUnit>> getProductionUnits(List<ProcessOrder> processOrders)
    {
        return processOrders.stream().map(po->po.getProductionUnitId()==null ? Optional.<ProductionUnit>empty()
                : Optional.of(new ProductionUnit(po.getProductionUnitId()))).collect(Collectors.toList());
    }

}
