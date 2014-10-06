package br.com.jopss.pagseguro.assinaturas.modelos.suporte.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum(String.class)
public enum PeriodoPreAprovacao {
	
	@XmlEnumValue("WEEKLY")
	SEMENAL,
	
	@XmlEnumValue("MONTHLY")
	MENSAL,
	
	@XmlEnumValue("BIMONTHLY")
	BIMESTRAL,
	
	@XmlEnumValue("TRIMONTHLY")
	TRIMESTRAL,
	
	@XmlEnumValue("SEMIANNUALLY")
	SEMESTRAL,
	
	@XmlEnumValue("YEARLY")
	ANUAL;
}
