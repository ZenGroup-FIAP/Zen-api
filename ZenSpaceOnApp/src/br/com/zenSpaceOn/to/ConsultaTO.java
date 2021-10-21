package br.com.zenSpaceOn.to;

public class ConsultaTO {
	private Integer codigo;
	private Integer codigoAgendamento;
	private Integer codigoPaciente;
	private Integer codigoPsicologo;
	private Double valorConsulta;
	
	public ConsultaTO() {
		
	}
	
	public ConsultaTO(Integer codigo, Integer codigoAgendamento, Integer codigoPaciente, Integer codigoPsicologo,
			Double valorConsulta) {
		this.codigo = codigo;
		this.codigoAgendamento = codigoAgendamento;
		this.codigoPaciente = codigoPaciente;
		this.codigoPsicologo = codigoPsicologo;
		this.valorConsulta = valorConsulta;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigoAgendamento() {
		return codigoAgendamento;
	}

	public void setCodigoAgendamento(Integer codigoAgendamento) {
		this.codigoAgendamento = codigoAgendamento;
	}

	public Integer getCodigoPaciente() {
		return codigoPaciente;
	}

	public void setCodigoPaciente(Integer codigoPaciente) {
		this.codigoPaciente = codigoPaciente;
	}

	public Integer getCodigoPsicologo() {
		return codigoPsicologo;
	}

	public void setCodigoPsicologo(Integer codigoPsicologo) {
		this.codigoPsicologo = codigoPsicologo;
	}

	public Double getValorConsulta() {
		return valorConsulta;
	}

	public void setValorConsulta(Double valorConsulta) {
		this.valorConsulta = valorConsulta;
	}

	@Override
	public String toString() {
		return "ConsultaTO [codigo=" + codigo + ", codigoAgendamento=" + codigoAgendamento + ", codigoPaciente="
				+ codigoPaciente + ", codigoPsicologo=" + codigoPsicologo + ", valorConsulta=" + valorConsulta + "]";
	}	
}
