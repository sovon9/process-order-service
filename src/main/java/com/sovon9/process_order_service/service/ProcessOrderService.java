package com.sovon9.process_order_service.service;

import com.sovon9.process_order_service.dto.ProcessOrderDto;
import com.sovon9.process_order_service.entities.ProcessOrder;
import com.sovon9.process_order_service.repository.ProcessOrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProcessOrderService {

    private ProcessOrderRepository repository;

    public ProcessOrderService(ProcessOrderRepository repository) {
        this.repository = repository;
    }

    public ProcessOrderDto getProcessOrderData(Long id)
    {
        Optional<ProcessOrder> optionalProcessOrder = repository.findById(id);
        if(!optionalProcessOrder.isPresent())
        {
            return null;
        }
        ProcessOrder processOrder = optionalProcessOrder.get();
        ProcessOrderDto processOrderDto = new ProcessOrderDto();
        processOrderDto.setId(processOrder.getProcessOrderId());
        processOrderDto.setOrderNo(processOrder.getOrderNo());
        return processOrderDto;
    }
}
