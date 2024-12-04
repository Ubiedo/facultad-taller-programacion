
package publicar;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para TipoSesion.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>{@code
 * <simpleType name="TipoSesion">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="INVITADO"/>
 *     <enumeration value="CLIENTE"/>
 *     <enumeration value="AEROLINEA"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "TipoSesion")
@XmlEnum
public enum TipoSesion {

    INVITADO,
    CLIENTE,
    AEROLINEA;

    public String value() {
        return name();
    }

    public static TipoSesion fromValue(String v) {
        return valueOf(v);
    }

}
