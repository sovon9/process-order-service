CREATE TABLE process_order (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_no VARCHAR(255),
    product_id BIGINT,
    production_unit_id BIGINT,
    CONSTRAINT fk_process_order_product FOREIGN KEY (product_id) REFERENCES product(id),
    CONSTRAINT fk_process_order_production_unit FOREIGN KEY (production_unit_id) REFERENCES production_unit(id)
);
