
package publicar;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Asiento.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>{@code
 * <simpleType name="Asiento">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="ejecutivo"/>
 *     <enumeration value="turista"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "Asiento")
@XmlEnum
public enum Asiento {

    @XmlEnumValue("ejecutivo")
    EJECUTIVO("ejecutivo"),
    @XmlEnumValue("turista")
    TURISTA("turista");
    private final String value;

    Asiento(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Asiento fromValue(String v) {
        for (Asiento c: Asiento.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
