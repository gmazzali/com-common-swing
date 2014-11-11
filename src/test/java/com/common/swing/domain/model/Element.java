package com.common.swing.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.common.swing.view.bean.EditBean;
import com.common.swing.view.bean.RowBean;
import com.common.swing.view.bean.SearchBean;
import com.common.util.domain.model.entity.Entity;

/**
 * La clase de pruebas.
 * 
 * @since 30/04/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class Element extends Entity<Long> implements RowBean, SearchBean, EditBean {
	private static final long serialVersionUID = 1L;

	public interface Attribute {
		public static final String CODE = "code";
		public static final String NAME = "name";
		public static final String FECHA = "fecha";
		public static final String SEXO = "sexo";
		public static final String SALARIO = "salario";
		public static final String ACTIVO = "activo";
	}

	private Long code;

	private String name;

	private Date fecha;

	private Sexo sexo;

	private BigDecimal salario;

	private boolean activo;

	public Element() {
		this("");
	}

	public Element(String name) {
		this(null, name);
	}

	public Element(Long code, String name) {
		this.code = code;
		this.name = name;
		this.fecha = Math.random() > 0.4 ? new Date((long) (10000000000000L * Math.random())) : null;
		this.salario = new BigDecimal(100000L * Math.random()).setScale(2, RoundingMode.FLOOR);
		this.activo = this.salario.compareTo(new BigDecimal(50000)) > 0;
	}

	@Override
	public String toString() {
		return "Element [code=" + this.code + ", name=" + this.name + ", sexo=" + this.sexo + ", salario=" + this.salario + ", fecha="
				+ (this.fecha != null ? new SimpleDateFormat("dd/MM/yyyy").format(this.fecha) : "") + ", activo=" + this.activo + "]";
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Element target = new Element();
		Element source = this;
		BeanUtils.copyProperties(source, target);
		return target;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}