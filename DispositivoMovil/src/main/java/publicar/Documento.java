
package publicar;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para documento.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>{@code
 * <simpleType name="documento">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="CI"/>
 *     <enumeration value="PASAPORTE"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "documento")
@XmlEnum
public enum Documento {

    CI,
    PASAPORTE;

    public String value() {
        return name();
    }

    public static Documento fromValue(String v) {
        return valueOf(v);
    }

}
