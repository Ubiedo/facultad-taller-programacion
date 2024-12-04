
package publicar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para parPaqueteRutas complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="parPaqueteRutas">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="paquete" type="{http://publicar/}dataPaquete" minOccurs="0"/>
 *         <element name="nombreImgRutas">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="entry" maxOccurs="unbounded" minOccurs="0">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <sequence>
 *                             <element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             <element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           </sequence>
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="cantRutas" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         <element name="cantEjecutivo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         <element name="cantTurista" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parPaqueteRutas", propOrder = {
    "paquete",
    "nombreImgRutas",
    "cantRutas",
    "cantEjecutivo",
    "cantTurista"
})
public class ParPaqueteRutas {

    protected DataPaquete paquete;
    @XmlElement(required = true)
    protected Map<String,String> nombreImgRutas;
    protected Integer cantRutas;
    protected Integer cantEjecutivo;
    protected Integer cantTurista;

    /**
     * Obtiene el valor de la propiedad paquete.
     * 
     * @return
     *     possible object is
     *     {@link DataPaquete }
     *     
     */
    public DataPaquete getPaquete() {
        return paquete;
    }

    /**
     * Define el valor de la propiedad paquete.
     * 
     * @param value
     *     allowed object is
     *     {@link DataPaquete }
     *     
     */
    public void setPaquete(DataPaquete value) {
        this.paquete = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreImgRutas.
     * 
     * @return
     *     possible object is
     *     {@link ParPaqueteRutas.NombreImgRutas }
     *     
     */
    public Map<String,String> getNombreImgRutas() {
        return nombreImgRutas;
    }

    /**
     * Define el valor de la propiedad nombreImgRutas.
     * 
     * @param value
     *     allowed object is
     *     {@link ParPaqueteRutas.NombreImgRutas }
     *     
     */
    public void setNombreImgRutas(Map<String,String> value) {
        this.nombreImgRutas = value;
    }

    /**
     * Obtiene el valor de la propiedad cantRutas.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCantRutas() {
        return cantRutas;
    }

    /**
     * Define el valor de la propiedad cantRutas.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCantRutas(Integer value) {
        this.cantRutas = value;
    }

    /**
     * Obtiene el valor de la propiedad cantEjecutivo.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCantEjecutivo() {
        return cantEjecutivo;
    }

    /**
     * Define el valor de la propiedad cantEjecutivo.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCantEjecutivo(Integer value) {
        this.cantEjecutivo = value;
    }

    /**
     * Obtiene el valor de la propiedad cantTurista.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCantTurista() {
        return cantTurista;
    }

    /**
     * Define el valor de la propiedad cantTurista.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCantTurista(Integer value) {
        this.cantTurista = value;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element name="entry" maxOccurs="unbounded" minOccurs="0">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <sequence>
     *                   <element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   <element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                 </sequence>
     *               </restriction>
     *             </complexContent>
     *           </complexType>
     *         </element>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "entry"
    })
    public static class NombreImgRutas {

        protected List<ParPaqueteRutas.NombreImgRutas.Entry> entry;

        /**
         * Gets the value of the entry property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a {@code set} method for the entry property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEntry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ParPaqueteRutas.NombreImgRutas.Entry }
         * 
         * 
         * @return
         *     The value of the entry property.
         */
        public List<ParPaqueteRutas.NombreImgRutas.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<>();
            }
            return this.entry;
        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>{@code
         * <complexType>
         *   <complexContent>
         *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       <sequence>
         *         <element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         <element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *       </sequence>
         *     </restriction>
         *   </complexContent>
         * </complexType>
         * }</pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "key",
            "value"
        })
        public static class Entry {

            protected String key;
            protected String value;

            /**
             * Obtiene el valor de la propiedad key.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getKey() {
                return key;
            }

            /**
             * Define el valor de la propiedad key.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setKey(String value) {
                this.key = value;
            }

            /**
             * Obtiene el valor de la propiedad value.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Define el valor de la propiedad value.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

        }

    }

}
