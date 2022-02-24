package co.com.bancolombia.model.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Service {
    private int type;
    private long idCliente;
    private int idProvider;
    private double creditAmount;
}
