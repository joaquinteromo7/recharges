package co.com.bancolombia.model.recarga;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Recarga {

    private int type;
    private int idProvider;
    private String descripcionProvider;
    private int accountType;
    private long accountNumber;
    private double creditAmount;

}
