package com.sovon9.process_order_service.controller;

import com.sovon9.process_order_service.entities.ProcessOrder;
import com.sovon9.process_order_service.repository.ProcessOrderRepository;
import com.sovon9.process_order_service.util.QueryBuilderUtil;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Window;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.query.ScrollSubrange;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        Sort sort = QueryBuilderUtil.buildSort(order,"processOrderId", Sort.Direction.ASC);

        Specification<ProcessOrder> spec = QueryBuilderUtil.buildSpecification(where);

        if(spec==null) {
            return repository.findBy(position, Limit.of(limit), sort);
        }

        return repository.findBy(spec, q->q.limit(limit).sortBy(sort).scroll(position));
    }


    private Sort.Order toSortOrder(String field, Object direction, boolean forward) {

        Sort.Order order;
        if(direction.equals("ASC"))
        {
            if(forward)
            {
                order = Sort.Order.asc(field);
            }
            else
            {
                order = Sort.Order.desc(field);
            }
        }
        else
        {
            if(forward) {
                order = Sort.Order.desc(field);
            }
            else
            {
                order = Sort.Order.asc(field);
            }
        }

        return order;
    }
}
