
package publicar;

import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dataColeccion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dataColeccion">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="ConjString" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="ConjDataClienteVuelo" type="{http://publicar/}dataClienteVuelo" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="ConjDataClientePaquete" type="{http://publicar/}dataClientePaquete" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="ConjDataRuta" type="{http://publicar/}dataRuta" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="MapStringDataRutaAsiento">
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
 *                             <element name="value" type="{http://publicar/}dataRutaAsiento" minOccurs="0"/>
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
 *         <element name="ConjDataReserva" type="{http://publicar/}dataReserva" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="ConjDataPaqueteRuta" type="{http://publicar/}dataPaqueteRuta" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="ConjDataPaquete" type="{http://publicar/}dataPaquete" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="ConjDataPasaje" type="{http://publicar/}dataPasaje" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="ConjDataImagenNombreType" type="{http://publicar/}dataImagenNombreType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="MapStringListString">
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
 *                             <element name="value" type="{http://publicar/}arrayList" minOccurs="0"/>
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
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataColeccion", propOrder = {
    "conjString",
    "conjDataClienteVuelo",
    "conjDataClientePaquete",
    "conjDataRuta",
    "mapStringDataRutaAsiento",
    "conjDataReserva",
    "conjDataPaqueteRuta",
    "conjDataPaquete",
    "conjDataPasaje",
    "ConjDataImagenNombreType",
    "mapStringListString"
})
public class DataColeccion {

    @XmlElement(name = "ConjString", nillable = true)
    protected List<String> conjString;
    @XmlElement(name = "ConjDataClienteVuelo", nillable = true)
    protected List<DataClienteVuelo> conjDataClienteVuelo;
    @XmlElement(name = "ConjDataClientePaquete", nillable = true)
    protected List<DataClientePaquete> conjDataClientePaquete;
    @XmlElement(name = "ConjDataRuta", nillable = true)
    protected List<DataRuta> conjDataRuta;
    @XmlElement(name = "MapStringDataRutaAsiento", required = true)
    protected DataColeccion.MapStringDataRutaAsiento mapStringDataRutaAsiento;
    @XmlElement(name = "ConjDataReserva", nillable = true)
    protected List<DataReserva> conjDataReserva;
    @XmlElement(name = "ConjDataPaqueteRuta", nillable = true)
    protected List<DataPaqueteRuta> conjDataPaqueteRuta;
    @XmlElement(name = "ConjDataPaquete", nillable = true)
    protected List<DataPaquete> conjDataPaquete;
    @XmlElement(name = "ConjDataPasaje", nillable = true)
    protected List<DataPasaje> conjDataPasaje;
    @XmlElement(name = "MapStringListString", required = true)
    protected DataColeccion.MapStringListString mapStringListString;
    @XmlElement(name = "ConjDataImagenNombreType", nillable = true)
	private List<DataImagenNombreType> ConjDataImagenNombreType;

    /**
     * Gets the value of the conjString property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the conjString property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConjString().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return
     *     The value of the conjString property.
     */
    public List<String> getConjString() {
        if (conjString == null) {
            conjString = new java.util.ArrayList<>();
        }
        return this.conjString;
    }

    /**
     * Gets the value of the conjDataClienteVuelo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the conjDataClienteVuelo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConjDataClienteVuelo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataClienteVuelo }
     * 
     * 
     * @return
     *     The value of the conjDataClienteVuelo property.
     */
    public List<DataClienteVuelo> getConjDataClienteVuelo() {
        if (conjDataClienteVuelo == null) {
            conjDataClienteVuelo = new java.util.ArrayList<>();
        }
        return this.conjDataClienteVuelo;
    }

    /**
     * Gets the value of the conjDataClientePaquete property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the conjDataClientePaquete property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConjDataClientePaquete().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataClientePaquete }
     * 
     * 
     * @return
     *     The value of the conjDataClientePaquete property.
     */
    public List<DataClientePaquete> getConjDataClientePaquete() {
        if (conjDataClientePaquete == null) {
            conjDataClientePaquete = new java.util.ArrayList<>();
        }
        return this.conjDataClientePaquete;
    }

    /**
     * Gets the value of the conjDataRuta property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the conjDataRuta property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConjDataRuta().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataRuta }
     * 
     * 
     * @return
     *     The value of the conjDataRuta property.
     */
    public List<DataRuta> getConjDataRuta() {
        if (conjDataRuta == null) {
            conjDataRuta = new java.util.ArrayList<>();
        }
        return this.conjDataRuta;
    }

    /**
     * Obtiene el valor de la propiedad mapStringDataRutaAsiento.
     * 
     * @return
     *     possible object is
     *     {@link DataColeccion.MapStringDataRutaAsiento }
     *     
     */
    public DataColeccion.MapStringDataRutaAsiento getMapStringDataRutaAsiento() {
        return mapStringDataRutaAsiento;
    }

    /**
     * Define el valor de la propiedad mapStringDataRutaAsiento.
     * 
     * @param value
     *     allowed object is
     *     {@link DataColeccion.MapStringDataRutaAsiento }
     *     
     */
    public void setMapStringDataRutaAsiento(DataColeccion.MapStringDataRutaAsiento value) {
        this.mapStringDataRutaAsiento = value;
    }

    /**
     * Gets the value of the conjDataReserva property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the conjDataReserva property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConjDataReserva().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataReserva }
     * 
     * 
     * @return
     *     The value of the conjDataReserva property.
     */
    public List<DataReserva> getConjDataReserva() {
        if (conjDataReserva == null) {
            conjDataReserva = new java.util.ArrayList<>();
        }
        return this.conjDataReserva;
    }

    /**
     * Gets the value of the conjDataPaqueteRuta property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the conjDataPaqueteRuta property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConjDataPaqueteRuta().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataPaqueteRuta }
     * 
     * 
     * @return
     *     The value of the conjDataPaqueteRuta property.
     */
    public List<DataPaqueteRuta> getConjDataPaqueteRuta() {
        if (conjDataPaqueteRuta == null) {
            conjDataPaqueteRuta = new java.util.ArrayList<>();
        }
        return this.conjDataPaqueteRuta;
    }
    

    /**
     * Gets the value of the conjDataPaquete property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the conjDataPaquete property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConjDataPaquete().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataPaquete }
     * 
     * 
     * @return
     *     The value of the conjDataPaquete property.
     */
    public List<DataPaquete> getConjDataPaquete() {
        if (conjDataPaquete == null) {
            conjDataPaquete = new java.util.ArrayList<>();
        }
        return this.conjDataPaquete;
    }

    /**
     * Gets the value of the conjDataPasaje property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the conjDataPasaje property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConjDataPasaje().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataPasaje }
     * 
     * 
     * @return
     *     The value of the conjDataPasaje property.
     */
    public List<DataPasaje> getConjDataPasaje() {
        if (conjDataPasaje == null) {
            conjDataPasaje = new java.util.ArrayList<>();
        }
        return this.conjDataPasaje;
    }

    

    public void setConjDataPasaje(List<DataPasaje> pasajes) {
        
       this.conjDataPasaje = pasajes ;
    }
	
	public void setConjDataImagenNombreType(List<DataImagenNombreType> conjDataImagenNombreType) {
		ConjDataImagenNombreType = conjDataImagenNombreType;
	}
	
	public List<DataImagenNombreType> getConjDataImagenNombreType() {
		return ConjDataImagenNombreType;
	}
    
    /**
     * Obtiene el valor de la propiedad mapStringListString.
     * 
     * @return
     *     possible object is
     *     {@link DataColeccion.MapStringListString }
     *     
     */
    public DataColeccion.MapStringListString getMapStringListString() {
        return mapStringListString;
    }

    /**
     * Define el valor de la propiedad mapStringListString.
     * 
     * @param value
     *     allowed object is
     *     {@link DataColeccion.MapStringListString }
     *     
     */
    public void setMapStringListString(DataColeccion.MapStringListString value) {
        this.mapStringListString = value;
    }
    
    public void setconjString(List<String> value) {
        this.conjString = value;
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
     *                   <element name="value" type="{http://publicar/}dataRutaAsiento" minOccurs="0"/>
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
    public static class MapStringDataRutaAsiento {

        protected List<DataColeccion.MapStringDataRutaAsiento.Entry> entry;

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
         * {@link DataColeccion.MapStringDataRutaAsiento.Entry }
         * 
         * 
         * @return
         *     The value of the entry property.
         */
        public List<DataColeccion.MapStringDataRutaAsiento.Entry> getEntry() {
            if (entry == null) {
                entry = new java.util.ArrayList<>();
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
         *         <element name="value" type="{http://publicar/}dataRutaAsiento" minOccurs="0"/>
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
            protected DataRutaAsiento value;

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
             *     {@link DataRutaAsiento }
             *     
             */
            public DataRutaAsiento getValue() {
                return value;
            }

            /**
             * Define el valor de la propiedad value.
             * 
             * @param value
             *     allowed object is
             *     {@link DataRutaAsiento }
             *     
             */
            public void setValue(DataRutaAsiento value) {
                this.value = value;
            }

        }

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
     *                   <element name="value" type="{http://publicar/}arrayList" minOccurs="0"/>
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
    public static class MapStringListString {

        protected List<DataColeccion.MapStringListString.Entry> entry;

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
         * {@link DataColeccion.MapStringListString.Entry }
         * 
         * 
         * @return
         *     The value of the entry property.
         */
        public List<DataColeccion.MapStringListString.Entry> getEntry() {
            if (entry == null) {
                entry = new java.util.ArrayList<>();
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
         *         <element name="value" type="{http://publicar/}arrayList" minOccurs="0"/>
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
            protected publicar.ArrayList value;

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
             *     {@link publicar.ArrayList }
             *     
             */
            public publicar.ArrayList getValue() {
                return value;
            }

            /**
             * Define el valor de la propiedad value.
             * 
             * @param value
             *     allowed object is
             *     {@link publicar.ArrayList }
             *     
             */
            public void setValue(publicar.ArrayList value) {
                this.value = value;
            }

        }

    }

}
