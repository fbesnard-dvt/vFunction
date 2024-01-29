
package com.oracle.physician.service.delegate.stub.jaxws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10-b140319.1121
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "PatientFacadeService", targetNamespace = "http://www.oracle.com/medrec", wsdlLocation = "file:/home/franck/Applications/oracle/weblogic/user_projects/applications/medrec/modules/medrec/medrec-jaxws-facade-broker/target/JaxWsPatientFacadeBroker/PatientFacadeService.wsdl")
public class PatientFacadeService
    extends Service
{

    private final static URL PATIENTFACADESERVICE_WSDL_LOCATION;
    private final static WebServiceException PATIENTFACADESERVICE_EXCEPTION;
    private final static QName PATIENTFACADESERVICE_QNAME = new QName("http://www.oracle.com/medrec", "PatientFacadeService");

    static {
        WebServiceException e = null;
        URL url = null;
        try {
            url = new URL(com.oracle.physician.service.delegate.stub.jaxws.PatientFacadeService.class.getResource("."), "file:/home/franck/Applications/oracle/weblogic/user_projects/applications/medrec/modules/medrec/medrec-jaxws-facade-broker/target/JaxWsPatientFacadeBroker/PatientFacadeService.wsdl");
        } catch (MalformedURLException murl) {
            e = new WebServiceException(murl);
        }
        PATIENTFACADESERVICE_WSDL_LOCATION = url;
        PATIENTFACADESERVICE_EXCEPTION = e;
    }

    public PatientFacadeService() {
        super(__getWsdlLocation(), PATIENTFACADESERVICE_QNAME);
    }

    public PatientFacadeService(WebServiceFeature... features) {
        super(__getWsdlLocation(), PATIENTFACADESERVICE_QNAME, features);
    }

    public PatientFacadeService(URL wsdlLocation) {
        super(wsdlLocation, PATIENTFACADESERVICE_QNAME);
    }

    public PatientFacadeService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, PATIENTFACADESERVICE_QNAME, features);
    }

    public PatientFacadeService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PatientFacadeService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns PatientFacade
     */
    @WebEndpoint(name = "PatientFacadePort")
    public PatientFacade getPatientFacadePort() {
        return super.getPort(new QName("http://www.oracle.com/medrec", "PatientFacadePort"), PatientFacade.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PatientFacade
     */
    @WebEndpoint(name = "PatientFacadePort")
    public PatientFacade getPatientFacadePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.oracle.com/medrec", "PatientFacadePort"), PatientFacade.class, features);
    }

    private static URL __getWsdlLocation() {
        if (PATIENTFACADESERVICE_EXCEPTION!= null) {
            throw PATIENTFACADESERVICE_EXCEPTION;
        }
        return PATIENTFACADESERVICE_WSDL_LOCATION;
    }

}
