package me.weekbelt.naverreservation.domain;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class BaseEntity {
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
}
