package BoardService.Board.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate
    @Column(name = "created_Date")
    private String createdDate ;

    @LastModifiedBy
    @Column(name = "modified_Date")
    private String modifiedDate;

    @PrePersist
    public void PrePersist(){
        this.createdDate= LocalDate.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
        this.modifiedDate=this.createdDate;
    }
    @PreUpdate
    public void PreUpdate(){
        this.modifiedDate=LocalDate.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
    }
}
