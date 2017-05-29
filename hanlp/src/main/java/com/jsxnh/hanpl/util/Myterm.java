package com.jsxnh.hanpl.util;

import com.hankcs.hanlp.corpus.tag.Nature;

public class Myterm {

	private String word;
	private Nature nature;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Myterm other = (Myterm) obj;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}
	
	public Myterm(){
		
	}
	public Myterm(String word, Nature nature) {
		super();
		this.word = word;
		this.nature = nature;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public Nature getNature() {
		return nature;
	}
	public void setNature(Nature nature) {
		this.nature = nature;
	}
}
