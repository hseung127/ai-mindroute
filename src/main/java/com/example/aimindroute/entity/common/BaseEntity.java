package com.example.aimindroute.entity.common;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

import java.time.LocalDateTime;

@MappedSuperclass // DB 컬럼으로 자동 포함
@Getter
public abstract class BaseEntity {

    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private LocalDateTime deleteDate;

    private Long createId;
    private Long updateId;
    private Long deleteId;



    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateDate = LocalDateTime.now();
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public void setDeleteId(Long deleteId) {
        this.deleteId = deleteId;
    }
}
