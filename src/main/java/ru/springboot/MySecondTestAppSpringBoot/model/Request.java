package ru.springboot.MySecondTestAppSpringBoot.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @NotBlank
    @Length(max = 32)
    @Pattern(regexp = "^(?!123$).*")
    private String uid;

    @NotBlank
    @Length(max = 32)
    private String operationUid;

    private String systemName;

    @NotBlank
    private String systemTime;
    private String source;

    @Min(1)
    @Max(100000)
    private int communicationId;
    private int templateId;
    private int productCode;
    private int smsCode;
}
