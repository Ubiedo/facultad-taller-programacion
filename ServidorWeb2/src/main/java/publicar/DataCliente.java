
package publicar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dataCliente complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dataCliente">
 *   <complexContent>
 *     <extension base="{http://publicar/}dataUsuario">
 *       <sequence>
 *         <element name="apellido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaNac" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="tipoDocumento" type="{http://publicar/}documento" minOccurs="0"/>
 *         <element name="nroDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nacionalidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nicknameC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nombreC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="emailC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="contrasenhaC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="imagenC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="esCliente" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataCliente", propOrder = {
    "apellido",
    "fechaNac",
    "tipoDocumento",
    "nroDocumento",
    "nacionalidad",
    "nicknameC",
    "nombreC",
    "emailC",
    "contrasenhaC",
    "imagenC",
    "esCliente"
})
public class DataCliente
    extends DataUsuario
{

    protected String apellido;
    protected String fechaNac;
    @XmlSchemaType(name = "string")
    protected Documento tipoDocumento;
    protected String nroDocumento;
    protected String nacionalidad;
    protected String nicknameC;
    protected String nombreC;
    protected String emailC;
    protected String contrasenhaC;
    protected String imagenC;
    protected boolean esCliente;

    /**
     * Obtiene el valor de la propiedad apellido.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Define el valor de la propiedad apellido.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellido(String value) {
        this.apellido = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaNac.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaNac() {
        return fechaNac;
    }

    /**
     * Define el valor de la propiedad fechaNac.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaNac(String value) {
        this.fechaNac = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoDocumento.
     * 
     * @return
     *     possible object is
     *     {@link Documento }
     *     
     */
    public Documento getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Define el valor de la propiedad tipoDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link Documento }
     *     
     */
    public void setTipoDocumento(Documento value) {
        this.tipoDocumento = value;
    }

    /**
     * Obtiene el valor de la propiedad nroDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroDocumento() {
        return nroDocumento;
    }

    /**
     * Define el valor de la propiedad nroDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroDocumento(String value) {
        this.nroDocumento = value;
    }

    /**
     * Obtiene el valor de la propiedad nacionalidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Define el valor de la propiedad nacionalidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacionalidad(String value) {
        this.nacionalidad = value;
    }

    /**
     * Obtiene el valor de la propiedad nicknameC.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNicknameC() {
        return nicknameC;
    }

    /**
     * Define el valor de la propiedad nicknameC.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNicknameC(String value) {
        this.nicknameC = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreC.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreC() {
        return nombreC;
    }

    /**
     * Define el valor de la propiedad nombreC.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreC(String value) {
        this.nombreC = value;
    }

    /**
     * Obtiene el valor de la propiedad emailC.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailC() {
        return emailC;
    }

    /**
     * Define el valor de la propiedad emailC.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailC(String value) {
        this.emailC = value;
    }

    /**
     * Obtiene el valor de la propiedad contrasenhaC.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrasenhaC() {
        return contrasenhaC;
    }

    /**
     * Define el valor de la propiedad contrasenhaC.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrasenhaC(String value) {
        this.contrasenhaC = value;
    }

    /**
     * Obtiene el valor de la propiedad imagenC.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImagenC() {
        return imagenC;
    }

    /**
     * Define el valor de la propiedad imagenC.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImagenC(String value) {
        this.imagenC = value;
    }

    /**
     * Obtiene el valor de la propiedad esCliente.
     * 
     */
    public boolean isEsCliente() {
        return esCliente;
    }

    /**
     * Define el valor de la propiedad esCliente.
     * 
     */
    public void setEsCliente(boolean value) {
        this.esCliente = value;
    }

}
