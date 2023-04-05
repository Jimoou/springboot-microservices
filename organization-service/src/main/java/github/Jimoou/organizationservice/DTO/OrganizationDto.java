package github.Jimoou.organizationservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDto {
  private Long id;
  private String organizationName;
  private String organizationDescription;
  private String organizationCode;
  private LocalDateTime createdDate;
}
