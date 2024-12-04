
package publicar;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dataRuta complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dataRuta">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="descCorta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="imagen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="origen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="destino" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="hora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaAlta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="turistaBase" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         <element name="ejecutivoBase" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         <element name="unidadEquipajeExtra" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         <element name="estado" type="{http://publicar/}estadoRuta" minOccurs="0"/>
 *         <element name="categorias" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataRuta", propOrder = {
    "nombre",
    "descripcion",
    "descCorta",
    "imagen",
    "origen",
    "destino",
    "hora",
    "fechaAlta",
    "turistaBase",
    "ejecutivoBase",
    "unidadEquipajeExtra",
    "estado",
    "categorias",
    "video"
})
public class DataRuta {

    protected String nombre;
    protected String descripcion;
    protected String descCorta;
    protected String imagen;
    protected String origen;
    protected String destino;
    protected String hora;
    protected String fechaAlta;
    protected Float turistaBase;
    protected Float ejecutivoBase;
    protected Float unidadEquipajeExtra;
    protected String video;
    @XmlSchemaType(name = "string")
    protected EstadoRuta estado;
    @XmlElement(nillable = true)
    protected List<String> categorias;

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Obtiene el valor de la propiedad video.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVideo() {
        return video;
    }
    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad descCorta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescCorta() {
        return descCorta;
    }

    /**
     * Define el valor de la propiedad descCorta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescCorta(String value) {
        this.descCorta = value;
    }

    /**
     * Obtiene el valor de la propiedad imagen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Define el valor de la propiedad imagen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImagen(String value) {
        this.imagen = value;
    }

    /**
     * Obtiene el valor de la propiedad origen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * Define el valor de la propiedad origen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigen(String value) {
        this.origen = value;
    }

    /**
     * Obtiene el valor de la propiedad destino.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestino() {
        return destino;
    }

    /**
     * Define el valor de la propiedad destino.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestino(String value) {
        this.destino = value;
    }

    /**
     * Obtiene el valor de la propiedad hora.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHora() {
        return hora;
    }

    /**
     * Define el valor de la propiedad hora.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHora(String value) {
        this.hora = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaAlta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Define el valor de la propiedad fechaAlta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaAlta(String value) {
        this.fechaAlta = value;
    }

    /**
     * Obtiene el valor de la propiedad turistaBase.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getTuristaBase() {
        return turistaBase;
    }

    /**
     * Define el valor de la propiedad turistaBase.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setTuristaBase(Float value) {
        this.turistaBase = value;
    }

    /**
     * Obtiene el valor de la propiedad ejecutivoBase.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getEjecutivoBase() {
        return ejecutivoBase;
    }

    /**
     * Define el valor de la propiedad ejecutivoBase.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setEjecutivoBase(Float value) {
        this.ejecutivoBase = value;
    }

    /**
     * Obtiene el valor de la propiedad unidadEquipajeExtra.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getUnidadEquipajeExtra() {
        return unidadEquipajeExtra;
    }

    /**
     * Define el valor de la propiedad unidadEquipajeExtra.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setUnidadEquipajeExtra(Float value) {
        this.unidadEquipajeExtra = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link EstadoRuta }
     *     
     */
    public EstadoRuta getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoRuta }
     *     
     */
    public void setEstado(EstadoRuta value) {
        this.estado = value;
    }

    /**
     * Gets the value of the categorias property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the categorias property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategorias().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return
     *     The value of the categorias property.
     */
    public List<String> getCategorias() {
        if (categorias == null) {
            categorias = new ArrayList<>();
        }
        return this.categorias;
    }
    
    public void setCategorias(List<String> value) {
        this.categorias = value;
    }


}
