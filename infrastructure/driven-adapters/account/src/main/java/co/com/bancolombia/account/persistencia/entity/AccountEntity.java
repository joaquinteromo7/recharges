package co.com.bancolombia.account.persistencia.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ACCOUNTS")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

    @Id
    @Column(name="NUMBER_ACCOUNT")
    private Long numberAccount;
    private int type;
    private boolean state;
    private double balance;
}
