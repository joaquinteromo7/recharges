package co.com.bancolombia.service.persistencia.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "services")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ServiceEntity {

    @Id
    private int type;
    private  long idCliente;
    private  int idProvider;
    private double creditAmount;

}
