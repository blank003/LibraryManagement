package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	String id;

	@NotNull
	@Column(name = "user_id")
	String userId;

	@NotNull
	@Column(name = "password")
	String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
