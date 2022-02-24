package co.com.bancolombia.model.account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private int type;
    private long numberAccount;
    private boolean state;
    private double balance;
}
