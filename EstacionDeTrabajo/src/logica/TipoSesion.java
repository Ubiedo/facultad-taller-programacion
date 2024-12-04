package logica;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "TipoSesion")
@XmlEnum
public enum TipoSesion {
	INVITADO,
	CLIENTE,
	AEROLINEA
}
