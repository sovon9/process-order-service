package com.sovon9.process_order_service.entities;


import com.sovon9.process_order_service.util.GlobalUtil;
import jakarta.persistence.*;

@Entity
@Table(name = "process_order")
public class ProcessOrder {

    @Transient
    private String id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long processOrderId;

    @Column(name = "order_no")
    private String orderNo;
    @Column(name = "product_id")
    private Long productId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id")
    @Transient
    private Product product;

    @Column(name = "production_unit_id")
    private Long productionUnitId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "production_unit_id")
    @Transient
    private ProductionUnit productionUnit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getProcessOrderId() {
        return processOrderId;
    }

    public void setProcessOrderId(Long processOrderId) {
        this.processOrderId = processOrderId;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getProductionUnitId() {
        return productionUnitId;
    }

    public void setProductionUnitId(Long productionUnitId) {
        this.productionUnitId = productionUnitId;
    }

    public ProductionUnit getProductionUnit() {
        return productionUnit;
    }

    public void setProductionUnit(ProductionUnit productionUnit) {
        this.productionUnit = productionUnit;
    }

    @PostLoad
    public void postLoad()
    {
        this.id=GlobalUtil.toGlobalId("ProcessOrder", processOrderId);
    }
}
