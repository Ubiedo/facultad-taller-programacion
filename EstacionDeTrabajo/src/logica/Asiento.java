package logica;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "Asiento")
@XmlEnum
public enum Asiento {
	ejecutivo,
	turista
}
