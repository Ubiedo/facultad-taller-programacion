
package publicar;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dataClienteVuelo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dataClienteVuelo">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="fechaReserva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="pasajes" type="{http://publicar/}dataPasaje" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="tipoAsiento" type="{http://publicar/}Asiento" minOccurs="0"/>
 *         <element name="cantPasajes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="cantEquipajeExtra" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="costo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="vuelo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="ruta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="aerolinea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nickAerolinea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataClienteVuelo", propOrder = {
    "fechaReserva",
    "pasajes",
    "tipoAsiento",
    "cantPasajes",
    "cantEquipajeExtra",
    "costo",
    "vuelo",
    "ruta",
    "aerolinea",
    "nickAerolinea"
})
public class DataClienteVuelo {

    protected String fechaReserva;
    @XmlElement(nillable = true)
    protected List<DataPasaje> pasajes;
    @XmlSchemaType(name = "string")
    protected Asiento tipoAsiento;
    protected int cantPasajes;
    protected int cantEquipajeExtra;
    protected float costo;
    protected String vuelo;
    protected String ruta;
    protected String aerolinea;
    protected String nickAerolinea;

    /**
     * Obtiene el valor de la propiedad fechaReserva.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaReserva() {
        return fechaReserva;
    }

    /**
     * Define el valor de la propiedad fechaReserva.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaReserva(String value) {
        this.fechaReserva = value;
    }

    /**
     * Gets the value of the pasajes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the pasajes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPasajes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataPasaje }
     * 
     * 
     * @return
     *     The value of the pasajes property.
     */
    public List<DataPasaje> getPasajes() {
        if (pasajes == null) {
            pasajes = new ArrayList<>();
        }
        return this.pasajes;
    }

    /**
     * Obtiene el valor de la propiedad tipoAsiento.
     * 
     * @return
     *     possible object is
     *     {@link Asiento }
     *     
     */
    public Asiento getTipoAsiento() {
        return tipoAsiento;
    }

    /**
     * Define el valor de la propiedad tipoAsiento.
     * 
     * @param value
     *     allowed object is
     *     {@link Asiento }
     *     
     */
    public void setTipoAsiento(Asiento value) {
        this.tipoAsiento = value;
    }

    /**
     * Obtiene el valor de la propiedad cantPasajes.
     * 
     */
    public int getCantPasajes() {
        return cantPasajes;
    }

    /**
     * Define el valor de la propiedad cantPasajes.
     * 
     */
    public void setCantPasajes(int value) {
        this.cantPasajes = value;
    }

    /**
     * Obtiene el valor de la propiedad cantEquipajeExtra.
     * 
     */
    public int getCantEquipajeExtra() {
        return cantEquipajeExtra;
    }

    /**
     * Define el valor de la propiedad cantEquipajeExtra.
     * 
     */
    public void setCantEquipajeExtra(int value) {
        this.cantEquipajeExtra = value;
    }

    /**
     * Obtiene el valor de la propiedad costo.
     * 
     */
    public float getCosto() {
        return costo;
    }

    /**
     * Define el valor de la propiedad costo.
     * 
     */
    public void setCosto(float value) {
        this.costo = value;
    }

    /**
     * Obtiene el valor de la propiedad vuelo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVuelo() {
        return vuelo;
    }

    /**
     * Define el valor de la propiedad vuelo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVuelo(String value) {
        this.vuelo = value;
    }

    /**
     * Obtiene el valor de la propiedad ruta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * Define el valor de la propiedad ruta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuta(String value) {
        this.ruta = value;
    }

    /**
     * Obtiene el valor de la propiedad aerolinea.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAerolinea() {
        return aerolinea;
    }

    /**
     * Define el valor de la propiedad aerolinea.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAerolinea(String value) {
        this.aerolinea = value;
    }

    /**
     * Obtiene el valor de la propiedad nickAerolinea.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNickAerolinea() {
        return nickAerolinea;
    }

    /**
     * Define el valor de la propiedad nickAerolinea.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNickAerolinea(String value) {
        this.nickAerolinea = value;
    }

}
