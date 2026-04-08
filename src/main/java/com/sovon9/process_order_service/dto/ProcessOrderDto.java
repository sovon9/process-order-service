package com.sovon9.process_order_service.dto;

public class ProcessOrderDto {

    private Long id;
    private String orderNo;
    private Long productId;
    private Long productionUnitId;

    public ProcessOrderDto() {
    }

    public ProcessOrderDto(Long id, String orderNo, Long productId, Long productionUnitId) {
        this.id = id;
        this.orderNo = orderNo;
        this.productId = productId;
        this.productionUnitId = productionUnitId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductionUnitId() {
        return productionUnitId;
    }

    public void setProductionUnitId(Long productionUnitId) {
        this.productionUnitId = productionUnitId;
    }


}
