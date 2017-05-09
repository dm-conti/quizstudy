package it.mexican.quizstudy.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
* @author Domenico Conti
*/

@Entity
@Table(name="lessons")
public class Lesson
{
	
	@Id
	@Column(name="pk_lessons")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="number")
	private int numero;

	@Column(name="description", length = 1000)
	private String description;

	@ManyToOne
	@JoinColumn(name="pk_exams")
	private Exam exam;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getNumero()
	{
		return numero;
	}

	public void setNumero(int numero)
	{
		this.numero = numero;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Exam getExam()
	{
		return exam;
	}

	public void setExam(Exam exam)
	{
		this.exam = exam;
	}
}