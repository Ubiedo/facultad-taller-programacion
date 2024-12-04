
package publicar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dataAerolinea complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dataAerolinea">
 *   <complexContent>
 *     <extension base="{http://publicar/}dataUsuario">
 *       <sequence>
 *         <element name="web" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nicknameA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nombreA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="emailA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="contrasenhaA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="imagenA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "dataAerolinea", propOrder = {
    "web",
    "descripcion",
    "nicknameA",
    "nombreA",
    "emailA",
    "contrasenhaA",
    "imagenA",
    "esCliente"
})
public class DataAerolinea
    extends DataUsuario
{

    protected String web;
    protected String descripcion;
    protected String nicknameA;
    protected String nombreA;
    protected String emailA;
    protected String contrasenhaA;
    protected String imagenA;
    protected boolean esCliente;

    /**
     * Obtiene el valor de la propiedad web.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWeb() {
        return web;
    }

    /**
     * Define el valor de la propiedad web.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWeb(String value) {
        this.web = value;
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
     * Obtiene el valor de la propiedad nicknameA.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNicknameA() {
        return nicknameA;
    }

    /**
     * Define el valor de la propiedad nicknameA.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNicknameA(String value) {
        this.nicknameA = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreA.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreA() {
        return nombreA;
    }

    /**
     * Define el valor de la propiedad nombreA.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreA(String value) {
        this.nombreA = value;
    }

    /**
     * Obtiene el valor de la propiedad emailA.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailA() {
        return emailA;
    }

    /**
     * Define el valor de la propiedad emailA.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailA(String value) {
        this.emailA = value;
    }

    /**
     * Obtiene el valor de la propiedad contrasenhaA.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrasenhaA() {
        return contrasenhaA;
    }

    /**
     * Define el valor de la propiedad contrasenhaA.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrasenhaA(String value) {
        this.contrasenhaA = value;
    }

    /**
     * Obtiene el valor de la propiedad imagenA.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImagenA() {
        return imagenA;
    }

    /**
     * Define el valor de la propiedad imagenA.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImagenA(String value) {
        this.imagenA = value;
    }

    /**
     * Obtiene el valor de la propiedad esCliente.
     * 
     */
    public boolean getEsCliente() {
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
