package com.sovon9.process_order_service.repository;

import com.sovon9.process_order_service.entities.ProcessOrder;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Window;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessOrderRepository extends JpaRepository<ProcessOrder, Long>, JpaSpecificationExecutor<ProcessOrder> {
    Window<ProcessOrder> findBy(ScrollPosition position, Limit limit, Sort sort);
}
