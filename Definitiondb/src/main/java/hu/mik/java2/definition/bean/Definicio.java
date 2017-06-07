package hu.mik.java2.definition.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "t_definition")
public class Definicio {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
	@SequenceGenerator(name = "SEQ_GEN", sequenceName = "s_definition", allocationSize = 1, initialValue = 50)
	private Integer id;
	@Column(name = "subject")
	private String subject;
	@Column(name = "definicio")
	private String definition;
	@Column(name = "description")
	private String description;
	@Column(name = "registrydate")
	private Date registrydate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getRegistrydate() {
		return registrydate;
	}

	public void setRegistrydate(Date registrydate) {
		this.registrydate = registrydate;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", subject=" + subject + ", definition=" + definition + ", description=" + description
				+ ", registrydate=" + registrydate + "]";
	}
}
