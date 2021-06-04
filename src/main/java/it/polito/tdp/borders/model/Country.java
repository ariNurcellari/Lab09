package it.polito.tdp.borders.model;

public class Country {
	
	String name ;
	int cod;
	String nameAbb;
	public Country(String name, int cod, String nameAbb) {
		super();
		this.name = name;
		this.cod = cod;
		this.nameAbb = nameAbb;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public String getNameAbb() {
		return nameAbb;
	}
	public void setNameAbb(String nameAbb) {
		this.nameAbb = nameAbb;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cod;
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
		Country other = (Country) obj;
		if (cod != other.cod)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return name;
	}
}
