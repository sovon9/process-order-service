package com.sovon9.process_order_service.entities;

public class ProductionUnit {
    private Long productionUnitID;

    public ProductionUnit(Long productionUnitID) {
        this.productionUnitID = productionUnitID;
    }

    public Long getProductionUnitID() {
        return productionUnitID;
    }

    public void setProductionUnitID(Long productionUnitID) {
        this.productionUnitID = productionUnitID;
    }
}
