package com.sovon9.process_order_service.controller;

import com.sovon9.process_order_service.service.ProcessOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/process-order")
public class ProcessOrderController {

    private ProcessOrderService processOrderService;

    public ProcessOrderController(ProcessOrderService processOrderService) {
        this.processOrderService = processOrderService;
    }

    @GetMapping("/v1/process-order/{id}")
    public ResponseEntity<?> getProcessOrder(@PathVariable("id") Long id)
    {
        if(id==null || id==0)
        {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(processOrderService.getProcessOrderData(id));
    }

}
