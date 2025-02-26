
package com.sofiafranco.sales.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SaleDTO {
    private Long saleCode;
    private Double total;
    private int size;
    private String clientFname;
    private String clientLname;

    public SaleDTO() {
    }

    public SaleDTO(Long saleCode, Double total, int size, String clientFname, String clientLname) {
        this.saleCode = saleCode;
        this.total = total;
        this.size = size;
        this.clientFname = clientFname;
        this.clientLname = clientLname;
    }

   
    
    
}
