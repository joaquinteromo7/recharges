package co.com.bancolombia.provider.persistencia.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "providers")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProviderEntity {

    @Id
    private int id;
    private int type;
    private String description;
    private boolean state;

}
