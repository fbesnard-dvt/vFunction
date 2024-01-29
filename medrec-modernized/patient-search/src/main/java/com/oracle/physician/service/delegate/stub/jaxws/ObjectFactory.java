
package com.oracle.physician.service.delegate.stub.jaxws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.oracle.physician.service.delegate.stub.jaxws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FindApprovedPatientBySsn_QNAME = new QName("http://www.oracle.com/medrec", "findApprovedPatientBySsn");
    private final static QName _GetPatient_QNAME = new QName("http://www.oracle.com/medrec", "getPatient");
    private final static QName _SetRecordFacade_QNAME = new QName("http://www.oracle.com/medrec", "setRecordFacade");
    private final static QName _FindApprovedPatientBySsnResponse_QNAME = new QName("http://www.oracle.com/medrec", "findApprovedPatientBySsnResponse");
    private final static QName _FindApprovedPatientsByLastName_QNAME = new QName("http://www.oracle.com/medrec", "findApprovedPatientsByLastName");
    private final static QName _GetPatientResponse_QNAME = new QName("http://www.oracle.com/medrec", "getPatientResponse");
    private final static QName _FindApprovedPatientsByLastNameResponse_QNAME = new QName("http://www.oracle.com/medrec", "findApprovedPatientsByLastNameResponse");
    private final static QName _FuzzyFindApprovedPatientsByLastnameAndSsn_QNAME = new QName("http://www.oracle.com/medrec", "fuzzyFindApprovedPatientsByLastnameAndSsn");
    private final static QName _FuzzyFindApprovedPatientsByLastnameAndSsnResponse_QNAME = new QName("http://www.oracle.com/medrec", "fuzzyFindApprovedPatientsByLastnameAndSsnResponse");
    private final static QName _SetRecordFacadeResponse_QNAME = new QName("http://www.oracle.com/medrec", "setRecordFacadeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.oracle.physician.service.delegate.stub.jaxws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FindApprovedPatientsByLastNameResponse }
     * 
     */
    public FindApprovedPatientsByLastNameResponse createFindApprovedPatientsByLastNameResponse() {
        return new FindApprovedPatientsByLastNameResponse();
    }

    /**
     * Create an instance of {@link GetPatientResponse }
     * 
     */
    public GetPatientResponse createGetPatientResponse() {
        return new GetPatientResponse();
    }

    /**
     * Create an instance of {@link FuzzyFindApprovedPatientsByLastnameAndSsnResponse }
     * 
     */
    public FuzzyFindApprovedPatientsByLastnameAndSsnResponse createFuzzyFindApprovedPatientsByLastnameAndSsnResponse() {
        return new FuzzyFindApprovedPatientsByLastnameAndSsnResponse();
    }

    /**
     * Create an instance of {@link SetRecordFacadeResponse }
     * 
     */
    public SetRecordFacadeResponse createSetRecordFacadeResponse() {
        return new SetRecordFacadeResponse();
    }

    /**
     * Create an instance of {@link FuzzyFindApprovedPatientsByLastnameAndSsn }
     * 
     */
    public FuzzyFindApprovedPatientsByLastnameAndSsn createFuzzyFindApprovedPatientsByLastnameAndSsn() {
        return new FuzzyFindApprovedPatientsByLastnameAndSsn();
    }

    /**
     * Create an instance of {@link FindApprovedPatientBySsnResponse }
     * 
     */
    public FindApprovedPatientBySsnResponse createFindApprovedPatientBySsnResponse() {
        return new FindApprovedPatientBySsnResponse();
    }

    /**
     * Create an instance of {@link FindApprovedPatientsByLastName }
     * 
     */
    public FindApprovedPatientsByLastName createFindApprovedPatientsByLastName() {
        return new FindApprovedPatientsByLastName();
    }

    /**
     * Create an instance of {@link FindApprovedPatientBySsn }
     * 
     */
    public FindApprovedPatientBySsn createFindApprovedPatientBySsn() {
        return new FindApprovedPatientBySsn();
    }

    /**
     * Create an instance of {@link GetPatient }
     * 
     */
    public GetPatient createGetPatient() {
        return new GetPatient();
    }

    /**
     * Create an instance of {@link SetRecordFacade }
     * 
     */
    public SetRecordFacade createSetRecordFacade() {
        return new SetRecordFacade();
    }

    /**
     * Create an instance of {@link Address }
     * 
     */
    public Address createAddress() {
        return new Address();
    }

    /**
     * Create an instance of {@link FoundPatient }
     * 
     */
    public FoundPatient createFoundPatient() {
        return new FoundPatient();
    }

    /**
     * Create an instance of {@link PersonName }
     * 
     */
    public PersonName createPersonName() {
        return new PersonName();
    }

    /**
     * Create an instance of {@link Patient }
     * 
     */
    public Patient createPatient() {
        return new Patient();
    }

    /**
     * Create an instance of {@link TransferObject }
     * 
     */
    public TransferObject createTransferObject() {
        return new TransferObject();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindApprovedPatientBySsn }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.oracle.com/medrec", name = "findApprovedPatientBySsn")
    public JAXBElement<FindApprovedPatientBySsn> createFindApprovedPatientBySsn(FindApprovedPatientBySsn value) {
        return new JAXBElement<FindApprovedPatientBySsn>(_FindApprovedPatientBySsn_QNAME, FindApprovedPatientBySsn.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPatient }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.oracle.com/medrec", name = "getPatient")
    public JAXBElement<GetPatient> createGetPatient(GetPatient value) {
        return new JAXBElement<GetPatient>(_GetPatient_QNAME, GetPatient.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetRecordFacade }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.oracle.com/medrec", name = "setRecordFacade")
    public JAXBElement<SetRecordFacade> createSetRecordFacade(SetRecordFacade value) {
        return new JAXBElement<SetRecordFacade>(_SetRecordFacade_QNAME, SetRecordFacade.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindApprovedPatientBySsnResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.oracle.com/medrec", name = "findApprovedPatientBySsnResponse")
    public JAXBElement<FindApprovedPatientBySsnResponse> createFindApprovedPatientBySsnResponse(FindApprovedPatientBySsnResponse value) {
        return new JAXBElement<FindApprovedPatientBySsnResponse>(_FindApprovedPatientBySsnResponse_QNAME, FindApprovedPatientBySsnResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindApprovedPatientsByLastName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.oracle.com/medrec", name = "findApprovedPatientsByLastName")
    public JAXBElement<FindApprovedPatientsByLastName> createFindApprovedPatientsByLastName(FindApprovedPatientsByLastName value) {
        return new JAXBElement<FindApprovedPatientsByLastName>(_FindApprovedPatientsByLastName_QNAME, FindApprovedPatientsByLastName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPatientResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.oracle.com/medrec", name = "getPatientResponse")
    public JAXBElement<GetPatientResponse> createGetPatientResponse(GetPatientResponse value) {
        return new JAXBElement<GetPatientResponse>(_GetPatientResponse_QNAME, GetPatientResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindApprovedPatientsByLastNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.oracle.com/medrec", name = "findApprovedPatientsByLastNameResponse")
    public JAXBElement<FindApprovedPatientsByLastNameResponse> createFindApprovedPatientsByLastNameResponse(FindApprovedPatientsByLastNameResponse value) {
        return new JAXBElement<FindApprovedPatientsByLastNameResponse>(_FindApprovedPatientsByLastNameResponse_QNAME, FindApprovedPatientsByLastNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FuzzyFindApprovedPatientsByLastnameAndSsn }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.oracle.com/medrec", name = "fuzzyFindApprovedPatientsByLastnameAndSsn")
    public JAXBElement<FuzzyFindApprovedPatientsByLastnameAndSsn> createFuzzyFindApprovedPatientsByLastnameAndSsn(FuzzyFindApprovedPatientsByLastnameAndSsn value) {
        return new JAXBElement<FuzzyFindApprovedPatientsByLastnameAndSsn>(_FuzzyFindApprovedPatientsByLastnameAndSsn_QNAME, FuzzyFindApprovedPatientsByLastnameAndSsn.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FuzzyFindApprovedPatientsByLastnameAndSsnResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.oracle.com/medrec", name = "fuzzyFindApprovedPatientsByLastnameAndSsnResponse")
    public JAXBElement<FuzzyFindApprovedPatientsByLastnameAndSsnResponse> createFuzzyFindApprovedPatientsByLastnameAndSsnResponse(FuzzyFindApprovedPatientsByLastnameAndSsnResponse value) {
        return new JAXBElement<FuzzyFindApprovedPatientsByLastnameAndSsnResponse>(_FuzzyFindApprovedPatientsByLastnameAndSsnResponse_QNAME, FuzzyFindApprovedPatientsByLastnameAndSsnResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetRecordFacadeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.oracle.com/medrec", name = "setRecordFacadeResponse")
    public JAXBElement<SetRecordFacadeResponse> createSetRecordFacadeResponse(SetRecordFacadeResponse value) {
        return new JAXBElement<SetRecordFacadeResponse>(_SetRecordFacadeResponse_QNAME, SetRecordFacadeResponse.class, null, value);
    }

}
