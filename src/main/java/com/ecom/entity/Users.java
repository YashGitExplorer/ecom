package com.ecom.entity;

import java.security.Timestamp;

import com.ecom.utility.Constants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users",schema=Constants.schema)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @Column(name = "user_id")
    private Long userId; 
    
    @Column(length = 50, nullable = false, unique = true)
    private String username;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 15)
    private String phone;

    @Column(length = 255, nullable = false, unique = true)
    private String password;

    @Column(columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean isvalid = true;

    @Column(name = "entry_date", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp entryDate;

    @Column(name = "last_modify_date", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp lastModifyDate;

}
