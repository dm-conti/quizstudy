package it.mexican.quizstudy.model;

import javax.persistence.*;

/**
* @author Domenico Conti
*/

@Entity
@Table(name="exams")
public class Exam
{
	
	@Id
	@Column(name="pk_exams")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="description", unique = true, nullable = false)
	private String description;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
}