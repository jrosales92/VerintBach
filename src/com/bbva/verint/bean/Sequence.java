package com.bbva.verint.bean;

public class Sequence {

	public int cd_aplicacion;
	public int cd_value;

	public Sequence() {
		this.cd_aplicacion = -1;
		this.cd_value = -1;
	}

	public Sequence(int cd_aplicacion, int cd_value) {
		this.cd_aplicacion = cd_aplicacion;
		this.cd_value = cd_aplicacion;
	}

	public int getName() {
		return cd_aplicacion;
	}

	public void setName(int cd_aplicacion) {
		this.cd_aplicacion = cd_aplicacion;
	}

	public int getValue() {
		return cd_value;
	}

	public void setValue(int cd_value) {
		this.cd_value = cd_value;
	}
}
