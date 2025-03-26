package exercise.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;

import static jakarta.persistence.GenerationType.IDENTITY;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

// BEGIN
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "tasks")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Task {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String title;
    private String description;
    
    @LastModifiedDate
    private LocalDate updatedAt;

    @CreatedDate
    private LocalDate createdAt;
}
// END