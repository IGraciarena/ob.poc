package ob.poc.msproducts.entity.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDtoResponse {
    private String message;
    private Integer errorCode;
}
