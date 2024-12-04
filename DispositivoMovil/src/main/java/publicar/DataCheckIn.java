
package publicar;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dataCheckIn complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dataCheckIn">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="fechaEmbarque" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="horaEmbarque" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="pasajesConCheckIn" type="{http://publicar/}dataPasaje" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="fechaCheckIn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataCheckIn", propOrder = {
    "fechaEmbarque",
    "horaEmbarque",
    "pasajesConCheckIn",
    "fechaCheckIn"
})
public class DataCheckIn {

    protected String fechaEmbarque;
    protected String horaEmbarque;
    @XmlElement(nillable = true)
    protected List<DataPasaje> pasajesConCheckIn;
    protected String fechaCheckIn;

    /**
     * Obtiene el valor de la propiedad fechaEmbarque.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaEmbarque() {
        return fechaEmbarque;
    }

    /**
     * Define el valor de la propiedad fechaEmbarque.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaEmbarque(String value) {
        this.fechaEmbarque = value;
    }

    /**
     * Obtiene el valor de la propiedad horaEmbarque.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoraEmbarque() {
        return horaEmbarque;
    }

    /**
     * Define el valor de la propiedad horaEmbarque.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoraEmbarque(String value) {
        this.horaEmbarque = value;
    }

    /**
     * Gets the value of the pasajesConCheckIn property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the pasajesConCheckIn property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPasajesConCheckIn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataPasaje }
     * 
     * 
     * @return
     *     The value of the pasajesConCheckIn property.
     */
    public List<DataPasaje> getPasajesConCheckIn() {
        if (pasajesConCheckIn == null) {
            pasajesConCheckIn = new ArrayList<>();
        }
        return this.pasajesConCheckIn;
    }

    /**
     * Obtiene el valor de la propiedad fechaCheckIn.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaCheckIn() {
        return fechaCheckIn;
    }

    /**
     * Define el valor de la propiedad fechaCheckIn.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaCheckIn(String value) {
        this.fechaCheckIn = value;
    }

}
