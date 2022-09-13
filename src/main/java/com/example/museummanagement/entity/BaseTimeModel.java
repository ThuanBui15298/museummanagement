package com.example.museummanagement.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class BaseTimeModel {

    @CreationTimestamp
    @Column(name = "create_Time")
    private LocalDateTime creatDate;

    @UpdateTimestamp
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "update_at")
    private LocalDateTime UpdateBy;

    @Column(name = "status")
    private LocalDateTime status;

}
