package it.mexican.quizstudy.model;

import javax.persistence.*;

/**
* @author Domenico Conti
*/

@Entity
@Table(name="questions")
public class Question
{
	
	@Id
	@Column(name="pk_questions")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="text", length = 2000)
	private String testo;

	@ManyToOne
	@JoinColumn(name="pk_lessons")
	private Lesson lesson;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getTesto()
	{
		return testo;
	}

	public void setTesto(String testo)
	{
		this.testo = testo;
	}

	public Lesson getLesson()
	{
		return lesson;
	}

	public void setLesson(Lesson lesson)
	{
		this.lesson = lesson;
	}
}