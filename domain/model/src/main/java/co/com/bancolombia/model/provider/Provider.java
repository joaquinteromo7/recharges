package co.com.bancolombia.model.provider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Provider {
    private int id;
    private int type;
    private String description;
    private boolean state;
}
