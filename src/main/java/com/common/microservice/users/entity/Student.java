package com.common.microservice.users.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="students")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "should not be empty")
	private String name;
	
	@NotEmpty
	private String lastName;
	
	@NotEmpty
	@Email
	private String email;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt; 
	
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}
	
	@Lob
	@JsonIgnore
	private byte[] photo;

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {
			return true;
		}
		
		if(!(obj instanceof Student)) {
			return false;
		}
		
		Student student = (Student) obj;
		
		return this.id != null && this.id.equals(student.getId());
	}
	
	//to get hash code of the photo
	public Integer getPhotoHashCode(){
		return (this.photo != null)? this.photo.hashCode():null;
	}
	
	

}
