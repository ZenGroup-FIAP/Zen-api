package br.com.zenSpaceOn.to;

import java.sql.Date;

public class AgendamentoTO {
	private Integer codigo;
	private Integer codgiPaciente;
	private Integer codigoPsicologo;
	private Date dataConsulta;
	private Date horaConsulta;
	
	public AgendamentoTO() {
		
	}
	
	public AgendamentoTO(Integer codigo, Integer codgiPaciente, Integer codigoPsicologo, Date dataConsulta,
			Date horaConsulta) {
		this.codigo = codigo;
		this.codgiPaciente = codgiPaciente;
		this.codigoPsicologo = codigoPsicologo;
		this.dataConsulta = dataConsulta;
		this.horaConsulta = horaConsulta;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodgiPaciente() {
		return codgiPaciente;
	}

	public void setCodgiPaciente(Integer codgiPaciente) {
		this.codgiPaciente = codgiPaciente;
	}

	public Integer getCodigoPsicologo() {
		return codigoPsicologo;
	}

	public void setCodigoPsicologo(Integer codigoPsicologo) {
		this.codigoPsicologo = codigoPsicologo;
	}

	public Date getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public Date getHoraConsulta() {
		return horaConsulta;
	}

	public void setHoraConsulta(Date horaConsulta) {
		this.horaConsulta = horaConsulta;
	}

	@Override
	public String toString() {
		return "AgendamentoTO [codigo=" + codigo + ", codgiPaciente=" + codgiPaciente + ", codigoPsicologo="
				+ codigoPsicologo + ", dataConsulta=" + dataConsulta + ", horaConsulta=" + horaConsulta + "]";
	}
}
