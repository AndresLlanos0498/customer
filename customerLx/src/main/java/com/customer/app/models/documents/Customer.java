package com.customer.app.models.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * My <b>lombok</b>.
 * My annotations of lombok
 *
 * @Data
 */
@Data
@Builder
@Document(collection = "customerdb3test")
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @NotNull
    private String id;
    @NotBlank
    @NotNull
    private String name;
    @Email
    @NotBlank
    @NotNull
    private String email;
    @NotBlank
    @NotNull
    private String gender;
    @NotBlank
    @NotNull
    private String status;

    public boolean isActive() {
        return "active".equalsIgnoreCase(status);  // Método para verificar si el estado es "active"
    }
}
