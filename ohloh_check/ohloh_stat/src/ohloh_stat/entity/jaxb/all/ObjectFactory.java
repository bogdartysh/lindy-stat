//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.08 at 06:55:36 PM EEST 
//


package ohloh_stat.entity.jaxb.all;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ohloh_stat.entity.jaxb.all package. 
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

    private final static QName _ResponseResultProjectLicensesLicense_QNAME = new QName("", "license");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ohloh_stat.entity.jaxb.all
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link Response.Result }
     * 
     */
    public Response.Result createResponseResult() {
        return new Response.Result();
    }

    /**
     * Create an instance of {@link Response.Result.Project }
     * 
     */
    public Response.Result.Project createResponseResultProject() {
        return new Response.Result.Project();
    }

    /**
     * Create an instance of {@link Response.Result.Project.Licenses }
     * 
     */
    public Response.Result.Project.Licenses createResponseResultProjectLicenses() {
        return new Response.Result.Project.Licenses();
    }

    /**
     * Create an instance of {@link Response.Result.Project.Tags }
     * 
     */
    public Response.Result.Project.Tags createResponseResultProjectTags() {
        return new Response.Result.Project.Tags();
    }

    /**
     * Create an instance of {@link Response.Result.Project.ProjectActivityIndex }
     * 
     */
    public Response.Result.Project.ProjectActivityIndex createResponseResultProjectProjectActivityIndex() {
        return new Response.Result.Project.ProjectActivityIndex();
    }

    /**
     * Create an instance of {@link Response.Result.Project.Licenses.License }
     * 
     */
    public Response.Result.Project.Licenses.License createResponseResultProjectLicensesLicense() {
        return new Response.Result.Project.Licenses.License();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Response.Result.Project.Licenses.License }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "license", scope = Response.Result.Project.Licenses.class)
    public JAXBElement<Response.Result.Project.Licenses.License> createResponseResultProjectLicensesLicense(Response.Result.Project.Licenses.License value) {
        return new JAXBElement<Response.Result.Project.Licenses.License>(_ResponseResultProjectLicensesLicense_QNAME, Response.Result.Project.Licenses.License.class, Response.Result.Project.Licenses.class, value);
    }

}
