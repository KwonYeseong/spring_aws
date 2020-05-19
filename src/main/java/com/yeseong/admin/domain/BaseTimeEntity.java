package com.yeseong.admin.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA Entity클래스들이 BaseTimeEntity을 상속 할 경우 필드(creadtedDate, modifiedDate)를 Column으로 인식하도록 함.
@EntityListeners(AuditingEntityListener.class) // BaseTimeEntity클래스에 Auditing기능을 포함시킨다.
//CreateTime과 ModifiedTime을 자동으로 관리하는 역할
public abstract class BaseTimeEntity {
    @CreatedDate // Entity가 생성되어 저장될 때 시간을 자동으로 저장
    private LocalDateTime creadtedDate;

    @LastModifiedDate // 조회한 Entity의 값을 변경할 때 시간을 자동으로 저장
    private LocalDateTime modifiedDate;
}
