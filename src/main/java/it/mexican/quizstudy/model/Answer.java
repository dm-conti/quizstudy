package it.mexican.quizstudy.model;

import javax.persistence.*;

/**
* @author Domenico Conti
*/

@Entity
@Table(name="answers")
public class Answer
{
	
	@Id
	@Column(name="pk_answers")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="code")
	private String codice;

	@Column(name="text", length = 2000)
	private String test;

	@ManyToOne
	@JoinColumn(name="pk_questions")
	private Question domanda;
	
	@Column(name="flg_correct")
	private boolean corretta;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getCodice()
	{
		return codice;
	}

	public void setCodice(String codice)
	{
		this.codice = codice;
	}

	public String getTest()
	{
		return test;
	}

	public void setTest(String test)
	{
		this.test = test;
	}

	public Question getDomanda()
	{
		return domanda;
	}

	public void setDomanda(Question domanda)
	{
		this.domanda = domanda;
	}

	public boolean isCorretta()
	{
		return corretta;
	}

	public void setCorretta(boolean corretta)
	{
		this.corretta = corretta;
	}
}