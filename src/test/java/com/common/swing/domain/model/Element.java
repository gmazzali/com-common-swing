package com.common.swing.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.common.swing.view.bean.FormBean;
import com.common.swing.view.bean.RowBean;
import com.common.swing.view.bean.SearchBean;
import com.common.util.domain.model.Entity;

/**
 * La clase de pruebas.
 * 
 * @since 30/04/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class Element extends Entity<Integer> implements RowBean, SearchBean, FormBean {
	private static final long serialVersionUID = 1L;

	public interface Attribute extends Entity.Attributes {
		public static final String NAME = "name";
		public static final String FECHA = "fecha";
		public static final String SALARIO = "salario";
	}

	private String name;

	private Date fecha;

	private BigDecimal salario;

	public Element() {
		this("");
	}

	public Element(String name) {
		this(null, name);
	}

	public Element(Integer id, String name) {
		this.id = id;
		this.name = name;
		this.fecha = Math.random() > 0.4 ? new Date((long) (10000000000000L * Math.random())) : null;
		this.salario = new BigDecimal(100000L * Math.random()).setScale(2, RoundingMode.FLOOR);
	}

	@Override
	public String toString() {
		return "Element [name=" + this.name + ", salario=" + this.salario + ", fecha="
				+ (this.fecha != null ? new SimpleDateFormat("dd/MM/yyyy").format(this.fecha) : "") + "]";
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Element target = new Element();
		Element source = this;
		BeanUtils.copyProperties(source, target);
		return target;
	}

	@Override
	public Integer getId() {
		return this.id;
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

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
}